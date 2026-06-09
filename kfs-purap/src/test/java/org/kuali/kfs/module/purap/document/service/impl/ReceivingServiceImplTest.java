package org.kuali.kfs.module.purap.document.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.Mock;
import org.kuali.kfs.module.purap.PurapConstants;
import org.kuali.kfs.module.purap.document.LineItemReceivingDocument;
import org.kuali.kfs.module.purap.document.PurchaseOrderDocument;
import org.kuali.kfs.module.purap.document.dataaccess.ReceivingDao;
import org.kuali.kfs.module.purap.document.service.PurapService;
import org.kuali.kfs.module.purap.document.service.PurchaseOrderService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.document.service.FinancialSystemDocumentService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.NoteService;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

@MockitoSettings(strictness = Strictness.LENIENT)
public class ReceivingServiceImplTest extends KfsUnitTestBase {

    @Mock private PurchaseOrderService purchaseOrderService;
    @Mock private ReceivingDao receivingDao;
    @Mock private DocumentService documentService;
    @Mock private WorkflowDocumentService workflowDocumentService;
    @Mock private ConfigurationService configurationService;
    @Mock private PurapService purapService;
    @Mock private NoteService noteService;
    @Mock private FinancialSystemDocumentService financialSystemDocumentService;

    @InjectMocks
    private ReceivingServiceImpl receivingService;

    @Test
    public void testCanCreateLineItemReceivingDocument_openPO_true() {
        PurchaseOrderDocument po = mock(PurchaseOrderDocument.class);
        when(po.isPurchaseOrderCurrentIndicator()).thenReturn(true);
        when(po.getPurapDocumentIdentifier()).thenReturn(100);
        when(po.getApplicationDocumentStatus()).thenReturn(PurapConstants.PurchaseOrderStatuses.APPDOC_OPEN);
        when(purchaseOrderService.getCurrentPurchaseOrder(100)).thenReturn(po);

        boolean result = receivingService.isPurchaseOrderActiveForLineItemReceivingDocumentCreation(100);

        assertThat(result).isTrue();
    }

    @Test
    public void testCanCreateLineItemReceivingDocument_closedPO_true() {
        PurchaseOrderDocument po = mock(PurchaseOrderDocument.class);
        when(po.isPurchaseOrderCurrentIndicator()).thenReturn(true);
        when(po.getPurapDocumentIdentifier()).thenReturn(200);
        when(po.getApplicationDocumentStatus()).thenReturn(PurapConstants.PurchaseOrderStatuses.APPDOC_CLOSED);
        when(purchaseOrderService.getCurrentPurchaseOrder(200)).thenReturn(po);

        boolean result = receivingService.isPurchaseOrderActiveForLineItemReceivingDocumentCreation(200);

        assertThat(result).isTrue();
    }

    @Test
    public void testCanCreateLineItemReceivingDocument_nullPO_false() {
        when(purchaseOrderService.getCurrentPurchaseOrder(300)).thenReturn(null);

        boolean result = receivingService.isPurchaseOrderActiveForLineItemReceivingDocumentCreation(300);

        assertThat(result).isFalse();
    }

    @Test
    public void testCanCreateLineItemReceivingDocument_voidedPO_false() {
        PurchaseOrderDocument po = mock(PurchaseOrderDocument.class);
        when(po.isPurchaseOrderCurrentIndicator()).thenReturn(true);
        when(po.getPurapDocumentIdentifier()).thenReturn(400);
        when(po.getApplicationDocumentStatus()).thenReturn(PurapConstants.PurchaseOrderStatuses.APPDOC_VOID);
        when(purchaseOrderService.getCurrentPurchaseOrder(400)).thenReturn(po);

        boolean result = receivingService.isPurchaseOrderActiveForLineItemReceivingDocumentCreation(400);

        assertThat(result).isFalse();
    }

    @Test
    public void testGetReceivingDeliveryCampusCode_noRelatedViews_returnsEmpty() {
        PurchaseOrderDocument po = mock(PurchaseOrderDocument.class);
        when(po.getRelatedViews()).thenReturn(null);

        String result = receivingService.getReceivingDeliveryCampusCode(po);

        assertThat(result).isEqualTo("");
    }

    @Test
    public void testGetLineItemReceivingByDocumentNumber() throws Exception {
        String docNumber = "RCV-001";
        LineItemReceivingDocument mockDoc = mock(LineItemReceivingDocument.class);
        when(documentService.getByDocumentHeaderId(docNumber)).thenReturn(mockDoc);

        LineItemReceivingDocument result = receivingService.getLineItemReceivingByDocumentNumber(docNumber);

        assertThat(result).isEqualTo(mockDoc);
    }

    @Test
    public void testHasNewUnorderedItem_noItems_false() {
        LineItemReceivingDocument rlDoc = mock(LineItemReceivingDocument.class);
        when(rlDoc.getItems()).thenReturn(new ArrayList());

        boolean result = receivingService.hasNewUnorderedItem(rlDoc);

        assertThat(result).isFalse();
    }
}
