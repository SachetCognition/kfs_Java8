package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.coa.service.OrganizationService;
import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.businessobject.Asset;
import org.kuali.kfs.module.cam.businessobject.AssetPayment;
import org.kuali.kfs.module.cam.businessobject.AssetRetirementGlobal;
import org.kuali.kfs.module.cam.businessobject.AssetRetirementReason;
import org.kuali.kfs.module.cam.document.service.AssetObjectCodeService;
import org.kuali.kfs.module.cam.document.service.AssetPaymentService;
import org.kuali.kfs.module.cam.document.service.AssetService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AssetRetirementServiceImplTest extends KfsUnitTestBase {

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private AssetObjectCodeService assetObjectCodeService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private AssetPaymentService assetPaymentService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private AssetService assetService;

    @Mock
    private OrganizationService organizationService;

    @Mock
    private ObjectCodeService objectCodeService;

    @InjectMocks
    private AssetRetirementServiceImpl service;

    // --- isAssetRetiredByAuction ---

    @Test
    public void testIsAssetRetiredByAuction_true() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.AUCTION);
        assertTrue(service.isAssetRetiredByAuction(global));
    }

    @Test
    public void testIsAssetRetiredByAuction_false() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.SOLD);
        assertFalse(service.isAssetRetiredByAuction(global));
    }

    // --- isAssetRetiredBySold ---

    @Test
    public void testIsAssetRetiredBySold_true() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.SOLD);
        assertTrue(service.isAssetRetiredBySold(global));
    }

    @Test
    public void testIsAssetRetiredBySold_false() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.THEFT);
        assertFalse(service.isAssetRetiredBySold(global));
    }

    // --- isAssetRetiredByExternalTransferOrGift ---

    @Test
    public void testIsAssetRetiredByExternalTransferOrGift_externalTransfer() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.EXTERNAL_TRANSFER);
        assertTrue(service.isAssetRetiredByExternalTransferOrGift(global));
    }

    @Test
    public void testIsAssetRetiredByExternalTransferOrGift_gift() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.GIFT);
        assertTrue(service.isAssetRetiredByExternalTransferOrGift(global));
    }

    @Test
    public void testIsAssetRetiredByExternalTransferOrGift_notMatch() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.SOLD);
        assertFalse(service.isAssetRetiredByExternalTransferOrGift(global));
    }

    // --- isAssetRetiredByMerged ---

    @Test
    public void testIsAssetRetiredByMerged_true() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.MERGED);
        assertTrue(service.isAssetRetiredByMerged(global));
    }

    @Test
    public void testIsAssetRetiredByMerged_false() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.THEFT);
        assertFalse(service.isAssetRetiredByMerged(global));
    }

    // --- isAssetRetiredByTheft ---

    @Test
    public void testIsAssetRetiredByTheft_true() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.THEFT);
        assertTrue(service.isAssetRetiredByTheft(global));
    }

    @Test
    public void testIsAssetRetiredByTheft_false() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.SOLD);
        assertFalse(service.isAssetRetiredByTheft(global));
    }

    // --- getAssetRetirementReasonName ---

    @Test
    public void testGetAssetRetirementReasonName_withReason() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        AssetRetirementReason reason = new AssetRetirementReason();
        reason.setRetirementReasonName("Sold at Auction");
        global.setRetirementReason(reason);

        assertEquals("Sold at Auction", service.getAssetRetirementReasonName(global));
    }

    @Test
    public void testGetAssetRetirementReasonName_nullReason() {
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setRetirementReason(null);

        assertEquals("", service.getAssetRetirementReasonName(global));
    }

    // --- generateOffsetPaymentsForEachSource ---
    // Note: generateOffsetPaymentsForEachSource and generateNewPaymentForTarget
    // use ObjectValueUtils.copySimpleProperties which requires OJB property
    // introspection unavailable in pure unit tests. Tested via integration tests.

    @Test
    public void testGenerateOffsetPaymentsForEachSource_emptyPayments() {
        Asset sourceAsset = new Asset();
        sourceAsset.setCapitalAssetNumber(1000L);
        sourceAsset.setAssetPayments(new ArrayList<AssetPayment>());

        when(assetPaymentService.getMaxSequenceNumber(1000L)).thenReturn(0);

        List<PersistableBusinessObject> persistables = new ArrayList<PersistableBusinessObject>();
        service.generateOffsetPaymentsForEachSource(sourceAsset, persistables, "DOC001");

        assertTrue(persistables.isEmpty());
    }

    // --- accessor tests ---

    @Test
    public void testGetParameterService() {
        assertSame(parameterService, service.getParameterService());
    }

    @Test
    public void testGetAssetService() {
        assertSame(assetService, service.getAssetService());
    }

    @Test
    public void testGetUniversityDateService() {
        assertSame(universityDateService, service.getUniversityDateService());
    }

    @Test
    public void testGetAssetObjectCodeService() {
        assertSame(assetObjectCodeService, service.getAssetObjectCodeService());
    }

    @Test
    public void testGetBusinessObjectService() {
        assertSame(businessObjectService, service.getBusinessObjectService());
    }

    @Test
    public void testGetAssetPaymentService() {
        assertSame(assetPaymentService, service.getAssetPaymentService());
    }
}
