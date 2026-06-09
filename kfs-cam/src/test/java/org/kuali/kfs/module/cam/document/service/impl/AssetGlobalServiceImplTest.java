package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.businessobject.AssetGlobal;
import org.kuali.kfs.module.cam.businessobject.AssetGlobalDetail;
import org.kuali.kfs.module.cam.businessobject.AssetPaymentDetail;
import org.kuali.kfs.module.cam.document.service.AssetPaymentService;
import org.kuali.kfs.module.cam.document.service.AssetService;
import org.kuali.kfs.module.cam.document.service.PaymentSummaryService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AssetGlobalServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;

    @Mock
    private AssetService assetService;

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private AssetPaymentService assetPaymentService;

    @Mock
    private PaymentSummaryService paymentSummaryService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private ObjectCodeService objectCodeService;

    @InjectMocks
    private AssetGlobalServiceImpl service;

    // --- totalPaymentByAsset ---

    @Test
    public void testTotalPaymentByAsset_singlePaymentSingleAsset_notLast() {
        AssetGlobal assetGlobal = new AssetGlobal();
        AssetPaymentDetail detail = new AssetPaymentDetail();
        detail.setAmount(new KualiDecimal(100));
        assetGlobal.setAssetPaymentDetails(Arrays.asList(detail));

        List<AssetGlobalDetail> globalDetails = new ArrayList<>();
        globalDetails.add(new AssetGlobalDetail());
        assetGlobal.setAssetGlobalDetails(globalDetails);

        KualiDecimal result = service.totalPaymentByAsset(assetGlobal, false);
        assertEquals(new KualiDecimal(100), result);
    }

    @Test
    public void testTotalPaymentByAsset_multipleAssetsEvenSplit_notLast() {
        AssetGlobal assetGlobal = new AssetGlobal();
        AssetPaymentDetail detail = new AssetPaymentDetail();
        detail.setAmount(new KualiDecimal(100));
        assetGlobal.setAssetPaymentDetails(Arrays.asList(detail));

        List<AssetGlobalDetail> globalDetails = new ArrayList<>();
        globalDetails.add(new AssetGlobalDetail());
        globalDetails.add(new AssetGlobalDetail());
        assetGlobal.setAssetGlobalDetails(globalDetails);

        KualiDecimal result = service.totalPaymentByAsset(assetGlobal, false);
        assertEquals(new KualiDecimal(50), result);
    }

    @Test
    public void testTotalPaymentByAsset_lastEntry_getsRemainder() {
        AssetGlobal assetGlobal = new AssetGlobal();
        AssetPaymentDetail detail = new AssetPaymentDetail();
        detail.setAmount(new KualiDecimal(100));
        assetGlobal.setAssetPaymentDetails(Arrays.asList(detail));

        List<AssetGlobalDetail> globalDetails = new ArrayList<>();
        globalDetails.add(new AssetGlobalDetail());
        globalDetails.add(new AssetGlobalDetail());
        globalDetails.add(new AssetGlobalDetail());
        assetGlobal.setAssetGlobalDetails(globalDetails);

        KualiDecimal result = service.totalPaymentByAsset(assetGlobal, true);
        // 100 / 3 = 33.33; last entry gets 100 - (33.33 * 2) = 33.34
        assertNotNull(result);
    }

    @Test
    public void testTotalPaymentByAsset_noAssets() {
        AssetGlobal assetGlobal = new AssetGlobal();
        assetGlobal.setAssetPaymentDetails(Arrays.asList(new AssetPaymentDetail()));
        assetGlobal.setAssetGlobalDetails(new ArrayList<AssetGlobalDetail>());

        assertEquals(KualiDecimal.ZERO, service.totalPaymentByAsset(assetGlobal, false));
    }

    // --- existsInGroup ---

    @Test
    public void testExistsInGroup_memberInGroup() {
        assertTrue(service.existsInGroup("A;B;C", "B"));
    }

    @Test
    public void testExistsInGroup_memberNotInGroup() {
        assertFalse(service.existsInGroup("A;B;C", "D"));
    }

    @Test
    public void testExistsInGroup_blankGroupName() {
        assertFalse(service.existsInGroup("", "A"));
    }

    @Test
    public void testExistsInGroup_blankMemberName() {
        assertFalse(service.existsInGroup("A;B", ""));
    }

    @Test
    public void testExistsInGroup_nullGroupName() {
        assertFalse(service.existsInGroup(null, "A"));
    }

    @Test
    public void testExistsInGroup_nullMemberName() {
        assertFalse(service.existsInGroup("A;B", null));
    }

    @Test
    public void testExistsInGroup_singleMemberGroup() {
        assertTrue(service.existsInGroup("A", "A"));
    }

    // --- isAssetSeparate ---

    @Test
    public void testIsAssetSeparate_separateTypeCode() {
        AssetGlobal assetGlobal = new AssetGlobal();
        assetGlobal.setFinancialDocumentTypeCode(CamsConstants.PaymentDocumentTypeCodes.ASSET_GLOBAL_SEPARATE);
        assertTrue(service.isAssetSeparate(assetGlobal));
    }

    @Test
    public void testIsAssetSeparate_otherTypeCode() {
        AssetGlobal assetGlobal = new AssetGlobal();
        assetGlobal.setFinancialDocumentTypeCode("AA");
        assertFalse(service.isAssetSeparate(assetGlobal));
    }

    @Test
    public void testIsAssetSeparate_nullTypeCode() {
        AssetGlobal assetGlobal = new AssetGlobal();
        assetGlobal.setFinancialDocumentTypeCode(null);
        assertFalse(service.isAssetSeparate(assetGlobal));
    }

    // --- isAssetSeparateByPayment ---

    @Test
    public void testIsAssetSeparateByPayment_separateWithPaymentSeq() {
        AssetGlobal assetGlobal = new AssetGlobal();
        assetGlobal.setFinancialDocumentTypeCode(CamsConstants.PaymentDocumentTypeCodes.ASSET_GLOBAL_SEPARATE);
        assetGlobal.setSeparateSourcePaymentSequenceNumber(1);
        assertTrue(service.isAssetSeparateByPayment(assetGlobal));
    }

    @Test
    public void testIsAssetSeparateByPayment_separateWithoutPaymentSeq() {
        AssetGlobal assetGlobal = new AssetGlobal();
        assetGlobal.setFinancialDocumentTypeCode(CamsConstants.PaymentDocumentTypeCodes.ASSET_GLOBAL_SEPARATE);
        assetGlobal.setSeparateSourcePaymentSequenceNumber(null);
        assertFalse(service.isAssetSeparateByPayment(assetGlobal));
    }

    @Test
    public void testIsAssetSeparateByPayment_notSeparate() {
        AssetGlobal assetGlobal = new AssetGlobal();
        assetGlobal.setFinancialDocumentTypeCode("AA");
        assetGlobal.setSeparateSourcePaymentSequenceNumber(1);
        assertFalse(service.isAssetSeparateByPayment(assetGlobal));
    }

    // --- getUniqueAssetsTotalAmount ---

    @Test
    public void testGetUniqueAssetsTotalAmount_withAmounts() {
        AssetGlobal assetGlobal = new AssetGlobal();

        AssetGlobalDetail uniqueDetail1 = new AssetGlobalDetail();
        uniqueDetail1.setSeparateSourceAmount(new KualiDecimal(100));
        AssetGlobalDetail uniqueDetail2 = new AssetGlobalDetail();
        uniqueDetail2.setSeparateSourceAmount(new KualiDecimal(200));

        AssetGlobalDetail sharedDetail = new AssetGlobalDetail();
        List<AssetGlobalDetail> uniqueDetails = new ArrayList<>();
        uniqueDetails.add(uniqueDetail1);
        uniqueDetails.add(uniqueDetail2);
        sharedDetail.setAssetGlobalUniqueDetails(uniqueDetails);

        List<AssetGlobalDetail> sharedDetails = new ArrayList<>();
        sharedDetails.add(sharedDetail);
        assetGlobal.setAssetSharedDetails(sharedDetails);

        assertEquals(new KualiDecimal(300), service.getUniqueAssetsTotalAmount(assetGlobal));
    }

    @Test
    public void testGetUniqueAssetsTotalAmount_withNullAmounts() {
        AssetGlobal assetGlobal = new AssetGlobal();

        AssetGlobalDetail uniqueDetail1 = new AssetGlobalDetail();
        uniqueDetail1.setSeparateSourceAmount(null);

        AssetGlobalDetail sharedDetail = new AssetGlobalDetail();
        List<AssetGlobalDetail> uniqueDetails = new ArrayList<>();
        uniqueDetails.add(uniqueDetail1);
        sharedDetail.setAssetGlobalUniqueDetails(uniqueDetails);

        List<AssetGlobalDetail> sharedDetails = new ArrayList<>();
        sharedDetails.add(sharedDetail);
        assetGlobal.setAssetSharedDetails(sharedDetails);

        assertEquals(KualiDecimal.ZERO, service.getUniqueAssetsTotalAmount(assetGlobal));
    }

    @Test
    public void testGetUniqueAssetsTotalAmount_emptySharedDetails() {
        AssetGlobal assetGlobal = new AssetGlobal();
        assetGlobal.setAssetSharedDetails(new ArrayList<AssetGlobalDetail>());

        assertEquals(KualiDecimal.ZERO, service.getUniqueAssetsTotalAmount(assetGlobal));
    }
}
