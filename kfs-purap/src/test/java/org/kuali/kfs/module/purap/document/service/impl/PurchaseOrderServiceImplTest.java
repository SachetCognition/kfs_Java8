package org.kuali.kfs.module.purap.document.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.Mock;
import org.kuali.kfs.integration.purap.CapitalAssetSystem;
import org.kuali.kfs.module.purap.PurapConstants;
import org.kuali.kfs.module.purap.businessobject.ItemType;
import org.kuali.kfs.module.purap.businessobject.PurApItem;
import org.kuali.kfs.module.purap.businessobject.PurchaseOrderItem;
import org.kuali.kfs.module.purap.businessobject.PurchaseOrderQuoteStatus;
import org.kuali.kfs.module.purap.businessobject.PurchasingCapitalAssetItem;
import org.kuali.kfs.module.purap.document.PurchaseOrderDocument;
import org.kuali.kfs.module.purap.document.dataaccess.PurchaseOrderDao;
import org.kuali.kfs.module.purap.document.service.B2BPurchaseOrderService;
import org.kuali.kfs.module.purap.document.service.PrintService;
import org.kuali.kfs.module.purap.document.service.PurApWorkflowIntegrationService;
import org.kuali.kfs.module.purap.document.service.PurapService;
import org.kuali.kfs.module.purap.document.service.RequisitionService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.vnd.document.service.VendorService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.service.NoteService;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

@MockitoSettings(strictness = Strictness.LENIENT)
public class PurchaseOrderServiceImplTest extends KfsUnitTestBase {

    @Mock protected BusinessObjectService businessObjectService;
    @Mock protected DateTimeService dateTimeService;
    @Mock protected DocumentService documentService;
    @Mock protected NoteService noteService;
    @Mock protected PurapService purapService;
    @Mock protected PrintService printService;
    @Mock protected PurchaseOrderDao purchaseOrderDao;
    @Mock protected WorkflowDocumentService workflowDocumentService;
    @Mock protected ConfigurationService kualiConfigurationService;
    @Mock protected KualiRuleService kualiRuleService;
    @Mock protected VendorService vendorService;
    @Mock protected RequisitionService requisitionService;
    @Mock protected PurApWorkflowIntegrationService purapWorkflowIntegrationService;
    @Mock protected ParameterService parameterService;
    @Mock protected PersonService personService;
    @Mock protected B2BPurchaseOrderService b2bPurchaseOrderService;

    @InjectMocks
    private PurchaseOrderServiceImpl purchaseOrderService;

    @Test
    public void testGetCurrentPurchaseOrder_nullId_returnsNull() {
        when(purchaseOrderDao.getDocumentNumberForCurrentPurchaseOrder(null)).thenReturn(null);

        PurchaseOrderDocument result = purchaseOrderService.getCurrentPurchaseOrder(null);

        assertThat(result).isNull();
    }

    @Test
    public void testGetCurrentPurchaseOrder_noDocNumber_returnsNull() {
        when(purchaseOrderDao.getDocumentNumberForCurrentPurchaseOrder(999)).thenReturn(null);

        PurchaseOrderDocument result = purchaseOrderService.getCurrentPurchaseOrder(999);

        assertThat(result).isNull();
    }

    @Test
    public void testIsPurchaseOrderOpenForProcessing_closedStatus_returnsFalse() {
        PurchaseOrderDocument po = mock(PurchaseOrderDocument.class);
        when(po.isPurchaseOrderCurrentIndicator()).thenReturn(true);
        when(po.isPendingActionIndicator()).thenReturn(false);
        when(po.getApplicationDocumentStatus()).thenReturn(PurapConstants.PurchaseOrderStatuses.APPDOC_CLOSED);

        boolean result = purchaseOrderService.isPurchaseOrderOpenForProcessing(po);

        assertThat(result).isFalse();
    }

    @Test
    public void testIsPurchaseOrderOpenForProcessing_withDocument_notCurrent_returnsFalse() {
        PurchaseOrderDocument po = mock(PurchaseOrderDocument.class);
        when(po.isPurchaseOrderCurrentIndicator()).thenReturn(false);

        boolean result = purchaseOrderService.isPurchaseOrderOpenForProcessing(po);

        assertThat(result).isFalse();
    }

    @Test
    public void testIsPurchaseOrderOpenForProcessing_withDocument_pending_returnsFalse() {
        PurchaseOrderDocument po = mock(PurchaseOrderDocument.class);
        when(po.isPurchaseOrderCurrentIndicator()).thenReturn(true);
        when(po.isPendingActionIndicator()).thenReturn(true);

        boolean result = purchaseOrderService.isPurchaseOrderOpenForProcessing(po);

        assertThat(result).isFalse();
    }

    @Test
    public void testIsCommodityCodeRequiredOnPurchaseOrder_true() {
        when(parameterService.getParameterValueAsBoolean(any(Class.class), anyString())).thenReturn(true);

        boolean result = purchaseOrderService.isCommodityCodeRequiredOnPurchaseOrder();

        assertThat(result).isTrue();
    }

    @Test
    public void testIsCommodityCodeRequiredOnPurchaseOrder_false() {
        when(parameterService.getParameterValueAsBoolean(any(Class.class), anyString())).thenReturn(false);

        boolean result = purchaseOrderService.isCommodityCodeRequiredOnPurchaseOrder();

        assertThat(result).isFalse();
    }

    @Test
    public void testCreateCamsItem_purchaseOrder() {
        PurchaseOrderDocument purDoc = mock(PurchaseOrderDocument.class);
        PurApItem purapItem = mock(PurApItem.class);
        when(purapItem.getItemIdentifier()).thenReturn(10);
        when(purDoc.getCapitalAssetSystemTypeCode()).thenReturn(PurapConstants.CapitalAssetTabStrings.INDIVIDUAL_ASSETS);

        PurchasingCapitalAssetItem result = purchaseOrderService.createCamsItem(purDoc, purapItem);

        assertThat(result).isNotNull();
        assertThat(result.getItemIdentifier()).isEqualTo(10);
    }

    @Test
    public void testCreateCapitalAssetSystem() {
        CapitalAssetSystem result = purchaseOrderService.createCapitalAssetSystem();

        assertThat(result).isNotNull();
    }

    @Test
    public void testGetPurchaseOrderQuoteStatusCodes() {
        List<PurchaseOrderQuoteStatus> statusCodes = new ArrayList<PurchaseOrderQuoteStatus>();
        statusCodes.add(mock(PurchaseOrderQuoteStatus.class));
        when(businessObjectService.findAll(PurchaseOrderQuoteStatus.class)).thenReturn(statusCodes);

        List<PurchaseOrderQuoteStatus> result = purchaseOrderService.getPurchaseOrderQuoteStatusCodes();

        assertThat(result).hasSize(1);
    }

    @Test
    public void testIsNewUnorderedItem_false_notActive() {
        PurchaseOrderItem poItem = mock(PurchaseOrderItem.class);
        when(poItem.isItemActiveIndicator()).thenReturn(false);

        boolean result = purchaseOrderService.isNewUnorderedItem(poItem);

        assertThat(result).isFalse();
    }

    @Test
    public void testCategorizeItemsForSplit_emptyList() {
        List<PurchaseOrderItem> items = new ArrayList<PurchaseOrderItem>();

        HashMap<String, List<PurchaseOrderItem>> result = purchaseOrderService.categorizeItemsForSplit(items);

        assertThat(result.get(PurapConstants.PODocumentsStrings.ITEMS_MOVING_TO_SPLIT)).isEmpty();
        assertThat(result.get(PurapConstants.PODocumentsStrings.ITEMS_REMAINING)).isEmpty();
    }

    @Test
    public void testCategorizeItemsForSplit_separatesMovingAndRemaining() {
        List<PurchaseOrderItem> items = new ArrayList<PurchaseOrderItem>();
        PurchaseOrderItem movingItem = mock(PurchaseOrderItem.class);
        ItemType movingType = mock(ItemType.class);
        when(movingType.isLineItemIndicator()).thenReturn(true);
        when(movingItem.getItemType()).thenReturn(movingType);
        when(movingItem.isMovingToSplit()).thenReturn(true);
        PurchaseOrderItem remainingItem = mock(PurchaseOrderItem.class);
        ItemType remainingType = mock(ItemType.class);
        when(remainingType.isLineItemIndicator()).thenReturn(true);
        when(remainingItem.getItemType()).thenReturn(remainingType);
        when(remainingItem.isMovingToSplit()).thenReturn(false);
        items.add(movingItem);
        items.add(remainingItem);

        HashMap<String, List<PurchaseOrderItem>> result = purchaseOrderService.categorizeItemsForSplit(items);

        assertThat(result.get(PurapConstants.PODocumentsStrings.ITEMS_MOVING_TO_SPLIT)).hasSize(1);
        assertThat(result.get(PurapConstants.PODocumentsStrings.ITEMS_REMAINING)).hasSize(1);
    }

    @Test
    public void testGetInternalPurchasingDollarLimit_noContractNoManager_returnsNull() {
        PurchaseOrderDocument po = mock(PurchaseOrderDocument.class);
        when(po.getVendorContract()).thenReturn(null);
        when(po.getContractManager()).thenReturn(null);

        KualiDecimal result = purchaseOrderService.getInternalPurchasingDollarLimit(po);

        assertThat(result).isNull();
    }
}
