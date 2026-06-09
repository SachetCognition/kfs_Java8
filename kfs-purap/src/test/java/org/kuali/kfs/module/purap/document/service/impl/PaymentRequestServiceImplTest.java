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
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.Mock;
import org.kuali.kfs.module.purap.PurapConstants;
import org.kuali.kfs.module.purap.businessobject.ItemType;
import org.kuali.kfs.module.purap.businessobject.PaymentRequestItem;
import org.kuali.kfs.module.purap.businessobject.PurchaseOrderItem;
import org.kuali.kfs.module.purap.document.AccountsPayableDocument;
import org.kuali.kfs.module.purap.document.PaymentRequestDocument;
import org.kuali.kfs.module.purap.document.PurchaseOrderDocument;
import org.kuali.kfs.module.purap.document.dataaccess.PaymentRequestDao;
import org.kuali.kfs.module.purap.document.service.AccountsPayableService;
import org.kuali.kfs.module.purap.document.service.NegativePaymentRequestApprovalLimitService;
import org.kuali.kfs.module.purap.document.service.PurApWorkflowIntegrationService;
import org.kuali.kfs.module.purap.document.service.PurapService;
import org.kuali.kfs.module.purap.document.service.PurchaseOrderService;
import org.kuali.kfs.module.purap.service.PurapAccountingService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.kfs.vnd.document.service.VendorService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.service.NoteService;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

@MockitoSettings(strictness = Strictness.LENIENT)
public class PaymentRequestServiceImplTest extends KfsUnitTestBase {

    @Mock protected DateTimeService dateTimeService;
    @Mock protected DocumentService documentService;
    @Mock protected NoteService noteService;
    @Mock protected PurapService purapService;
    @Mock protected PaymentRequestDao paymentRequestDao;
    @Mock protected ParameterService parameterService;
    @Mock protected ConfigurationService configurationService;
    @Mock protected NegativePaymentRequestApprovalLimitService negativePaymentRequestApprovalLimitService;
    @Mock protected PurapAccountingService purapAccountingService;
    @Mock protected BusinessObjectService businessObjectService;
    @Mock protected PurApWorkflowIntegrationService purapWorkflowIntegrationService;
    @Mock protected WorkflowDocumentService workflowDocumentService;
    @Mock protected AccountsPayableService accountsPayableService;
    @Mock protected VendorService vendorService;
    @Mock protected UniversityDateService universityDateService;
    @Mock protected PurchaseOrderService purchaseOrderService;
    @Mock protected KualiRuleService kualiRuleService;
    @Mock protected PersonService personService;

    @InjectMocks
    private PaymentRequestServiceImpl paymentRequestService;

    @Test
    public void testGetPaymentRequestsToExtract_emptyList() {
        Date testDate = new Date(System.currentTimeMillis());
        List<PaymentRequestDocument> emptyList = new ArrayList<PaymentRequestDocument>();
        when(paymentRequestDao.getPaymentRequestsToExtract(false, null, testDate)).thenReturn(emptyList);

        Collection<PaymentRequestDocument> result = paymentRequestService.getPaymentRequestsToExtract(testDate);

        assertThat(result).isEmpty();
    }

    @Test
    public void testGetPaymentRequestsByVendorNumber() {
        Integer headerGenId = 100;
        Integer detailAssignedId = 0;
        List<PaymentRequestDocument> expected = new ArrayList<PaymentRequestDocument>();
        when(paymentRequestDao.getActivePaymentRequestsByVendorNumber(headerGenId, detailAssignedId)).thenReturn(expected);

        List result = paymentRequestService.getPaymentRequestsByVendorNumber(headerGenId, detailAssignedId);

        assertThat(result).isSameAs(expected);
    }

    @Test
    public void testGetPaymentRequestsByVendorNumberInvoiceNumber() {
        Integer headerGenId = 100;
        Integer detailAssignedId = 0;
        String invoiceNumber = "INV-001";
        List<PaymentRequestDocument> expected = new ArrayList<PaymentRequestDocument>();
        when(paymentRequestDao.getActivePaymentRequestsByVendorNumberInvoiceNumber(headerGenId, detailAssignedId, invoiceNumber)).thenReturn(expected);

        List result = paymentRequestService.getPaymentRequestsByVendorNumberInvoiceNumber(headerGenId, detailAssignedId, invoiceNumber);

        assertThat(result).isSameAs(expected);
    }

    @Test
    public void testGetPaymentRequestByDocumentNumber() throws Exception {
        String docNum = "PREQ-001";
        PaymentRequestDocument mockDoc = mock(PaymentRequestDocument.class);
        when(documentService.getByDocumentHeaderId(docNum)).thenReturn(mockDoc);

        PaymentRequestDocument result = paymentRequestService.getPaymentRequestByDocumentNumber(docNum);

        assertThat(result).isEqualTo(mockDoc);
    }

    @Test
    public void testGetPaymentRequestByDocumentNumber_workflowException_throwsRuntime() throws Exception {
        final String docNum = "BAD-DOC";
        when(documentService.getByDocumentHeaderId(docNum)).thenThrow(new WorkflowException("fail"));

        assertThatThrownBy(new org.assertj.core.api.ThrowableAssert.ThrowingCallable() {
            public void call() {
                paymentRequestService.getPaymentRequestByDocumentNumber(docNum);
            }
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void testGetPaymentRequestById() throws Exception {
        Integer poDocId = 42;
        String docNum = "PREQ-42";
        PaymentRequestDocument mockDoc = mock(PaymentRequestDocument.class);
        when(paymentRequestDao.getDocumentNumberByPaymentRequestId(poDocId)).thenReturn(docNum);
        when(documentService.getByDocumentHeaderId(docNum)).thenReturn(mockDoc);

        PaymentRequestDocument result = paymentRequestService.getPaymentRequestById(poDocId);

        assertThat(result).isEqualTo(mockDoc);
    }

    @Test
    public void testGetPaymentRequestsByPurchaseOrderId() throws Exception {
        Integer poDocId = 100;
        List<String> docNumbers = new ArrayList<String>();
        docNumbers.add("PREQ-100");
        PaymentRequestDocument mockDoc = mock(PaymentRequestDocument.class);
        when(paymentRequestDao.getDocumentNumbersByPurchaseOrderId(poDocId)).thenReturn(docNumbers);
        when(documentService.getByDocumentHeaderId("PREQ-100")).thenReturn(mockDoc);

        List<PaymentRequestDocument> result = paymentRequestService.getPaymentRequestsByPurchaseOrderId(poDocId);

        assertThat(result).hasSize(1);
    }

    @Test
    public void testIsInvoiceDateAfterToday_future_returnsTrue() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date futureDate = new Date(cal.getTimeInMillis());
        when(dateTimeService.getCurrentSqlDateMidnight()).thenReturn(new Date(System.currentTimeMillis()));

        boolean result = paymentRequestService.isInvoiceDateAfterToday(futureDate);

        assertThat(result).isTrue();
    }

    @Test
    public void testIsInvoiceDateAfterToday_past_returnsFalse() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -5);
        Date pastDate = new Date(cal.getTimeInMillis());
        when(dateTimeService.getCurrentSqlDateMidnight()).thenReturn(new Date(System.currentTimeMillis()));

        boolean result = paymentRequestService.isInvoiceDateAfterToday(pastDate);

        assertThat(result).isFalse();
    }

    @Test
    public void testIsExtracted_extractedTimestampNull_returnsFalse() {
        PaymentRequestDocument doc = mock(PaymentRequestDocument.class);
        when(doc.getExtractedTimestamp()).thenReturn(null);

        boolean result = paymentRequestService.isExtracted(doc);

        assertThat(result).isFalse();
    }

    @Test
    public void testIsExtracted_extractedTimestampSet_returnsTrue() {
        PaymentRequestDocument doc = mock(PaymentRequestDocument.class);
        when(doc.getExtractedTimestamp()).thenReturn(new Timestamp(System.currentTimeMillis()));

        boolean result = paymentRequestService.isExtracted(doc);

        assertThat(result).isTrue();
    }

    @Test
    public void testHasDiscountItem_noDiscountItem() {
        PaymentRequestDocument preq = mock(PaymentRequestDocument.class);
        List items = new ArrayList();
        PaymentRequestItem item = mock(PaymentRequestItem.class);
        when(item.getItemTypeCode()).thenReturn(PurapConstants.ItemTypeCodes.ITEM_TYPE_ITEM_CODE);
        items.add(item);
        when(preq.getItems()).thenReturn(items);

        boolean result = paymentRequestService.hasDiscountItem(preq);

        assertThat(result).isFalse();
    }

    @Test
    public void testHasDiscountItem_withDiscountItem() {
        PaymentRequestDocument preq = mock(PaymentRequestDocument.class);
        List items = new ArrayList();
        PaymentRequestItem item = mock(PaymentRequestItem.class);
        when(item.getItemTypeCode()).thenReturn(PurapConstants.ItemTypeCodes.ITEM_TYPE_PMT_TERMS_DISCOUNT_CODE);
        items.add(item);
        when(preq.getItems()).thenReturn(items);

        boolean result = paymentRequestService.hasDiscountItem(preq);

        assertThat(result).isTrue();
    }

    @Test
    public void testHasActivePaymentRequestsForPurchaseOrder_noActivePreqs() {
        Integer poId = 50;
        List<PaymentRequestDocument> emptyList = new ArrayList<PaymentRequestDocument>();
        when(paymentRequestDao.getActivePaymentRequestDocumentNumbersForPurchaseOrder(poId)).thenReturn(emptyList);

        boolean result = paymentRequestService.hasActivePaymentRequestsForPurchaseOrder(poId);

        assertThat(result).isFalse();
    }

    @Test
    public void testPoItemEligibleForAp_inactiveItem_returnsFalse() {
        AccountsPayableDocument apDoc = mock(AccountsPayableDocument.class);
        PurchaseOrderItem poi = mock(PurchaseOrderItem.class);
        when(poi.isItemActiveIndicator()).thenReturn(false);

        boolean result = paymentRequestService.poItemEligibleForAp(apDoc, poi);

        assertThat(result).isFalse();
    }

    @Test
    public void testPoItemEligibleForAp_activeNullItemType_returnsFalse() {
        AccountsPayableDocument apDoc = mock(AccountsPayableDocument.class);
        PurchaseOrderItem poi = mock(PurchaseOrderItem.class);
        when(poi.isItemActiveIndicator()).thenReturn(true);
        when(poi.getItemType()).thenReturn(null);

        boolean result = paymentRequestService.poItemEligibleForAp(apDoc, poi);

        assertThat(result).isFalse();
    }

    @Test
    public void testPoItemEligibleForAp_activeQuantityBased_withRemaining() {
        AccountsPayableDocument apDoc = mock(AccountsPayableDocument.class);
        PurchaseOrderItem poi = mock(PurchaseOrderItem.class);
        when(poi.isItemActiveIndicator()).thenReturn(true);
        ItemType itemType = mock(ItemType.class);
        when(itemType.isQuantityBasedGeneralLedgerIndicator()).thenReturn(true);
        when(poi.getItemType()).thenReturn(itemType);
        when(poi.getItemQuantity()).thenReturn(new KualiDecimal(10));
        when(poi.getItemInvoicedTotalQuantity()).thenReturn(new KualiDecimal(5));

        boolean result = paymentRequestService.poItemEligibleForAp(apDoc, poi);

        assertThat(result).isTrue();
    }

    @Test
    public void testMarkPaid_setsFields() {
        PaymentRequestDocument pr = mock(PaymentRequestDocument.class);
        Date processDate = new Date(System.currentTimeMillis());

        paymentRequestService.markPaid(pr, processDate);

        verify(pr).setPaymentPaidTimestamp(any(Timestamp.class));
    }

    @Test
    public void testShouldPurchaseOrderBeReversed_nullPO_throwsRuntime() {
        final AccountsPayableDocument apDoc = mock(PaymentRequestDocument.class);
        when(apDoc.getPurchaseOrderDocument()).thenReturn(null);

        assertThatThrownBy(new org.assertj.core.api.ThrowableAssert.ThrowingCallable() {
            public void call() {
                paymentRequestService.shouldPurchaseOrderBeReversed(apDoc);
            }
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void testShouldPurchaseOrderBeReversed_openPO_returnsFalse() {
        AccountsPayableDocument apDoc = mock(PaymentRequestDocument.class);
        PurchaseOrderDocument po = mock(PurchaseOrderDocument.class);
        when(apDoc.getPurchaseOrderDocument()).thenReturn(po);
        when(po.getApplicationDocumentStatus()).thenReturn(PurapConstants.PurchaseOrderStatuses.APPDOC_OPEN);
        when(purapService.isFullDocumentEntryCompleted(apDoc)).thenReturn(false);

        boolean result = paymentRequestService.shouldPurchaseOrderBeReversed(apDoc);

        assertThat(result).isFalse();
    }
}
