package org.kuali.kfs.module.purap.document.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.Mock;
import org.kuali.kfs.module.purap.PurapConstants;
import org.kuali.kfs.module.purap.businessobject.ItemType;
import org.kuali.kfs.module.purap.businessobject.PurchaseOrderItem;
import org.kuali.kfs.module.purap.document.AccountsPayableDocument;
import org.kuali.kfs.module.purap.document.PurchaseOrderDocument;
import org.kuali.kfs.module.purap.document.VendorCreditMemoDocument;
import org.kuali.kfs.module.purap.document.dataaccess.CreditMemoDao;
import org.kuali.kfs.module.purap.document.service.AccountsPayableService;
import org.kuali.kfs.module.purap.document.service.PaymentRequestService;
import org.kuali.kfs.module.purap.document.service.PurapService;
import org.kuali.kfs.module.purap.document.service.PurchaseOrderService;
import org.kuali.kfs.module.purap.service.PurapAccountingService;
import org.kuali.kfs.module.purap.service.PurapGeneralLedgerService;
import org.kuali.kfs.module.purap.util.VendorGroupingHelper;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.document.service.FinancialSystemDocumentService;
import org.kuali.kfs.vnd.document.service.VendorService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.NoteService;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.kuali.rice.kns.service.DataDictionaryService;

@MockitoSettings(strictness = Strictness.LENIENT)
public class CreditMemoServiceImplTest extends KfsUnitTestBase {

    @Mock private AccountsPayableService accountsPayableService;
    @Mock private CreditMemoDao creditMemoDao;
    @Mock private DataDictionaryService dataDictionaryService;
    @Mock private DocumentService documentService;
    @Mock private ConfigurationService kualiConfigurationService;
    @Mock private NoteService noteService;
    @Mock private PaymentRequestService paymentRequestService;
    @Mock private PurapAccountingService purapAccountingService;
    @Mock private PurapGeneralLedgerService purapGeneralLedgerService;
    @Mock private PurapService purapService;
    @Mock private PurchaseOrderService purchaseOrderService;
    @Mock private VendorService vendorService;
    @Mock private WorkflowDocumentService workflowDocumentService;
    @Mock private FinancialSystemDocumentService financialSystemDocumentService;

    @InjectMocks
    private CreditMemoServiceImpl creditMemoService;

    @Test
    public void testGetCreditMemosToExtract_emptyList_returnsEmpty() {
        String chartCode = "BL";
        List<VendorCreditMemoDocument> emptyList = new ArrayList<VendorCreditMemoDocument>();
        when(creditMemoDao.getCreditMemosToExtract(chartCode)).thenReturn(emptyList);

        List<VendorCreditMemoDocument> result = creditMemoService.getCreditMemosToExtract(chartCode);

        assertThat(result).isEmpty();
    }

    @Test
    public void testGetCreditMemosToExtractByVendor_emptyList_returnsEmpty() {
        String chartCode = "BL";
        VendorGroupingHelper vendor = mock(VendorGroupingHelper.class);
        Collection<VendorCreditMemoDocument> emptyList = new ArrayList<VendorCreditMemoDocument>();
        when(creditMemoDao.getCreditMemosToExtractByVendor(chartCode, vendor)).thenReturn(emptyList);

        Collection<VendorCreditMemoDocument> result = creditMemoService.getCreditMemosToExtractByVendor(chartCode, vendor);

        assertThat(result).isEmpty();
    }

    @Test
    public void testGetCreditMemoByDocumentNumber() throws Exception {
        String docNum = "CM-001";
        VendorCreditMemoDocument mockDoc = mock(VendorCreditMemoDocument.class);
        when(documentService.getByDocumentHeaderId(docNum)).thenReturn(mockDoc);

        VendorCreditMemoDocument result = creditMemoService.getCreditMemoByDocumentNumber(docNum);

        assertThat(result).isEqualTo(mockDoc);
    }

    @Test
    public void testGetCreditMemoByDocumentNumber_workflowException_throwsRuntime() throws Exception {
        final String docNum = "BAD-CM";
        when(documentService.getByDocumentHeaderId(docNum)).thenThrow(new WorkflowException("fail"));

        assertThatThrownBy(new org.assertj.core.api.ThrowableAssert.ThrowingCallable() {
            public void call() {
                creditMemoService.getCreditMemoByDocumentNumber(docNum);
            }
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void testGetCreditMemoDocumentById() throws Exception {
        Integer cmId = 42;
        String docNum = "CM-42";
        VendorCreditMemoDocument mockDoc = mock(VendorCreditMemoDocument.class);
        when(creditMemoDao.getDocumentNumberByCreditMemoId(cmId)).thenReturn(docNum);
        when(documentService.getByDocumentHeaderId(docNum)).thenReturn(mockDoc);

        VendorCreditMemoDocument result = creditMemoService.getCreditMemoDocumentById(cmId);

        assertThat(result).isEqualTo(mockDoc);
    }

    @Test
    public void testHasActiveCreditMemosForPurchaseOrder_noActiveDocs() {
        Integer poId = 99;
        List<String> emptyDocNumbers = new ArrayList<String>();
        when(creditMemoDao.getActiveCreditMemoDocumentNumbersForPurchaseOrder(poId)).thenReturn(emptyDocNumbers);

        boolean result = creditMemoService.hasActiveCreditMemosForPurchaseOrder(poId);

        assertThat(result).isFalse();
    }

    @Test
    public void testMarkPaid_setsTimestamp() {
        VendorCreditMemoDocument cm = mock(VendorCreditMemoDocument.class);
        Date processDate = new Date(System.currentTimeMillis());

        creditMemoService.markPaid(cm, processDate);

        verify(cm).setCreditMemoPaidTimestamp(any(Timestamp.class));
    }

    @Test
    public void testShouldPurchaseOrderBeReversed_returnsFalse() {
        AccountsPayableDocument apDoc = mock(VendorCreditMemoDocument.class);

        boolean result = creditMemoService.shouldPurchaseOrderBeReversed(apDoc);

        assertThat(result).isFalse();
    }

    @Test
    public void testUpdateStatusByNode_accountReviewNode() {
        String currentNodeName = PurapConstants.CreditMemoStatuses.NODE_ACCOUNT_REVIEW;
        AccountsPayableDocument apDoc = mock(VendorCreditMemoDocument.class);

        String result = creditMemoService.updateStatusByNode(currentNodeName, apDoc);

        assertThat(result).isNotNull();
    }

    @Test
    public void testPoItemEligibleForAp_inactiveItem_returnsFalse() {
        AccountsPayableDocument apDoc = mock(VendorCreditMemoDocument.class);
        PurchaseOrderItem poItem = mock(PurchaseOrderItem.class);
        when(poItem.isItemActiveIndicator()).thenReturn(false);

        boolean result = creditMemoService.poItemEligibleForAp(apDoc, poItem);

        assertThat(result).isFalse();
    }

    @Test
    public void testPoItemEligibleForAp_activeQuantityItem_withInvoice() {
        AccountsPayableDocument apDoc = mock(VendorCreditMemoDocument.class);
        PurchaseOrderItem poItem = mock(PurchaseOrderItem.class);
        when(poItem.isItemActiveIndicator()).thenReturn(true);
        ItemType itemType = mock(ItemType.class);
        when(itemType.isQuantityBasedGeneralLedgerIndicator()).thenReturn(true);
        when(poItem.getItemType()).thenReturn(itemType);
        when(poItem.getItemInvoicedTotalQuantity()).thenReturn(new KualiDecimal(10));

        boolean result = creditMemoService.poItemEligibleForAp(apDoc, poItem);

        assertThat(result).isTrue();
    }

    @Test
    public void testCreditMemoDuplicateMessages_noDuplicate() {
        VendorCreditMemoDocument cmDoc = mock(VendorCreditMemoDocument.class);
        when(cmDoc.getVendorNumber()).thenReturn("1000-0");
        when(cmDoc.getCreditMemoNumber()).thenReturn("CM-UNIQUE");
        when(cmDoc.getCreditMemoDate()).thenReturn(new Date(System.currentTimeMillis()));
        when(cmDoc.getCreditMemoAmount()).thenReturn(new KualiDecimal(100));
        when(creditMemoDao.duplicateExists(1000, 0, "CM-UNIQUE")).thenReturn(false);
        when(creditMemoDao.duplicateExists(any(Integer.class), any(Integer.class), any(Date.class), any(KualiDecimal.class))).thenReturn(false);

        String result = creditMemoService.creditMemoDuplicateMessages(cmDoc);

        assertThat(result).isNull();
    }

    @Test
    public void testCreditMemoDuplicateMessages_noVendorNumber() {
        VendorCreditMemoDocument cmDoc = mock(VendorCreditMemoDocument.class);
        when(cmDoc.getVendorNumber()).thenReturn(null);
        when(cmDoc.getPurApSourceDocumentIfPossible()).thenReturn(null);

        String result = creditMemoService.creditMemoDuplicateMessages(cmDoc);

        assertThat(result).isNull();
    }

    @Test
    public void testGetPersonForCancel_returnsNull() {
        AccountsPayableDocument apDoc = mock(VendorCreditMemoDocument.class);

        Person result = creditMemoService.getPersonForCancel(apDoc);

        assertThat(result).isNull();
    }
}
