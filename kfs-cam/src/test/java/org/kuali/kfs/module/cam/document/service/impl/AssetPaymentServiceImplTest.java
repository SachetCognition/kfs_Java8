package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.businessobject.Asset;
import org.kuali.kfs.module.cam.businessobject.AssetGlobal;
import org.kuali.kfs.module.cam.businessobject.AssetPayment;
import org.kuali.kfs.module.cam.businessobject.AssetPaymentAllocationType;
import org.kuali.kfs.module.cam.businessobject.AssetPaymentDetail;
import org.kuali.kfs.module.cam.document.dataaccess.AssetPaymentDao;
import org.kuali.kfs.module.cam.document.service.AssetService;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.UniversityDate;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.kfs.sys.service.impl.KfsParameterConstants;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AssetPaymentServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private AssetPaymentDao assetPaymentDao;

    @Mock
    private ParameterService parameterService;

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private ObjectCodeService objectCodeService;

    @Mock
    private AssetService assetService;

    @InjectMocks
    private AssetPaymentServiceImpl service;

    // --- getMaxSequenceNumber ---

    @Test
    public void testGetMaxSequenceNumber() {
        when(assetPaymentDao.getMaxSquenceNumber(100L)).thenReturn(5);
        assertEquals(Integer.valueOf(5), service.getMaxSequenceNumber(100L));
    }

    @Test
    public void testGetMaxSequenceNumber_null() {
        when(assetPaymentDao.getMaxSquenceNumber(200L)).thenReturn(null);
        assertNull(service.getMaxSequenceNumber(200L));
    }

    // --- isPaymentEligibleForAccumDeprGLPosting ---

    @Test
    public void testIsPaymentEligibleForAccumDeprGLPosting_nonZero() {
        AssetPayment payment = new AssetPayment();
        payment.setAccumulatedPrimaryDepreciationAmount(new KualiDecimal(100));
        assertTrue(service.isPaymentEligibleForAccumDeprGLPosting(payment));
    }

    @Test
    public void testIsPaymentEligibleForAccumDeprGLPosting_zero() {
        AssetPayment payment = new AssetPayment();
        payment.setAccumulatedPrimaryDepreciationAmount(KualiDecimal.ZERO);
        assertFalse(service.isPaymentEligibleForAccumDeprGLPosting(payment));
    }

    @Test
    public void testIsPaymentEligibleForAccumDeprGLPosting_null() {
        AssetPayment payment = new AssetPayment();
        payment.setAccumulatedPrimaryDepreciationAmount(null);
        assertFalse(service.isPaymentEligibleForAccumDeprGLPosting(payment));
    }

    // --- isPaymentEligibleForCapitalizationGLPosting ---

    @Test
    public void testIsPaymentEligibleForCapitalizationGLPosting_nonZero() {
        AssetPayment payment = new AssetPayment();
        payment.setAccountChargeAmount(new KualiDecimal(100));
        assertTrue(service.isPaymentEligibleForCapitalizationGLPosting(payment));
    }

    @Test
    public void testIsPaymentEligibleForCapitalizationGLPosting_zero() {
        AssetPayment payment = new AssetPayment();
        payment.setAccountChargeAmount(KualiDecimal.ZERO);
        assertFalse(service.isPaymentEligibleForCapitalizationGLPosting(payment));
    }

    @Test
    public void testIsPaymentEligibleForCapitalizationGLPosting_null() {
        AssetPayment payment = new AssetPayment();
        payment.setAccountChargeAmount(null);
        assertFalse(service.isPaymentEligibleForCapitalizationGLPosting(payment));
    }

    // --- isPaymentEligibleForOffsetGLPosting ---

    @Test
    public void testIsPaymentEligibleForOffsetGLPosting_nonZeroDifference() {
        AssetPayment payment = new AssetPayment();
        payment.setAccountChargeAmount(new KualiDecimal(100));
        payment.setAccumulatedPrimaryDepreciationAmount(new KualiDecimal(50));
        assertTrue(service.isPaymentEligibleForOffsetGLPosting(payment));
    }

    @Test
    public void testIsPaymentEligibleForOffsetGLPosting_zeroDifference() {
        AssetPayment payment = new AssetPayment();
        payment.setAccountChargeAmount(new KualiDecimal(100));
        payment.setAccumulatedPrimaryDepreciationAmount(new KualiDecimal(100));
        assertFalse(service.isPaymentEligibleForOffsetGLPosting(payment));
    }

    @Test
    public void testIsPaymentEligibleForOffsetGLPosting_nullAccumDepr() {
        AssetPayment payment = new AssetPayment();
        payment.setAccountChargeAmount(new KualiDecimal(100));
        payment.setAccumulatedPrimaryDepreciationAmount(null);
        assertTrue(service.isPaymentEligibleForOffsetGLPosting(payment));
    }

    @Test
    public void testIsPaymentEligibleForOffsetGLPosting_nullChargeAmount() {
        AssetPayment payment = new AssetPayment();
        payment.setAccountChargeAmount(null);
        payment.setAccumulatedPrimaryDepreciationAmount(new KualiDecimal(50));
        assertFalse(service.isPaymentEligibleForOffsetGLPosting(payment));
    }

    // --- isPaymentFinancialObjectActive ---

    @Test
    public void testIsPaymentFinancialObjectActive_activeObject() {
        AssetPayment payment = new AssetPayment();
        payment.setChartOfAccountsCode("BL");
        payment.setFinancialObjectCode("7000");

        when(universityDateService.getCurrentFiscalYear()).thenReturn(2024);

        ObjectCode objectCode = new ObjectCode();
        objectCode.setActive(true);
        when(businessObjectService.retrieve(any(ObjectCode.class))).thenReturn(objectCode);

        assertTrue(service.isPaymentFinancialObjectActive(payment));
    }

    @Test
    public void testIsPaymentFinancialObjectActive_inactiveObject() {
        AssetPayment payment = new AssetPayment();
        payment.setChartOfAccountsCode("BL");
        payment.setFinancialObjectCode("7000");

        when(universityDateService.getCurrentFiscalYear()).thenReturn(2024);

        ObjectCode objectCode = new ObjectCode();
        objectCode.setActive(false);
        when(businessObjectService.retrieve(any(ObjectCode.class))).thenReturn(objectCode);

        assertFalse(service.isPaymentFinancialObjectActive(payment));
    }

    @Test
    public void testIsPaymentFinancialObjectActive_notFound() {
        AssetPayment payment = new AssetPayment();
        payment.setChartOfAccountsCode("BL");
        payment.setFinancialObjectCode("9999");

        when(universityDateService.getCurrentFiscalYear()).thenReturn(2024);
        when(businessObjectService.retrieve(any(ObjectCode.class))).thenReturn(null);

        assertFalse(service.isPaymentFinancialObjectActive(payment));
    }

    // --- isNonDepreciableFederallyOwnedObjSubType ---

    @Test
    public void testIsNonDepreciableFederallyOwnedObjSubType_match() {
        when(parameterService.parameterExists(eq(KfsParameterConstants.CAPITAL_ASSETS_BATCH.class),
                eq(CamsConstants.Parameters.NON_DEPRECIABLE_FEDERALLY_OWNED_OBJECT_SUB_TYPES))).thenReturn(true);
        when(parameterService.getParameterValuesAsString(eq(KfsParameterConstants.CAPITAL_ASSETS_BATCH.class),
                eq(CamsConstants.Parameters.NON_DEPRECIABLE_FEDERALLY_OWNED_OBJECT_SUB_TYPES)))
                .thenReturn(Arrays.asList("CF", "CM"));

        assertTrue(service.isNonDepreciableFederallyOwnedObjSubType("CF"));
    }

    @Test
    public void testIsNonDepreciableFederallyOwnedObjSubType_noMatch() {
        when(parameterService.parameterExists(eq(KfsParameterConstants.CAPITAL_ASSETS_BATCH.class),
                eq(CamsConstants.Parameters.NON_DEPRECIABLE_FEDERALLY_OWNED_OBJECT_SUB_TYPES))).thenReturn(true);
        when(parameterService.getParameterValuesAsString(eq(KfsParameterConstants.CAPITAL_ASSETS_BATCH.class),
                eq(CamsConstants.Parameters.NON_DEPRECIABLE_FEDERALLY_OWNED_OBJECT_SUB_TYPES)))
                .thenReturn(Arrays.asList("CF", "CM"));

        assertFalse(service.isNonDepreciableFederallyOwnedObjSubType("BD"));
    }

    @Test
    public void testIsNonDepreciableFederallyOwnedObjSubType_paramNotExists() {
        when(parameterService.parameterExists(eq(KfsParameterConstants.CAPITAL_ASSETS_BATCH.class),
                eq(CamsConstants.Parameters.NON_DEPRECIABLE_FEDERALLY_OWNED_OBJECT_SUB_TYPES))).thenReturn(false);

        assertFalse(service.isNonDepreciableFederallyOwnedObjSubType("CF"));
    }

    // --- extractPostedDatePeriod ---

    @Test
    public void testExtractPostedDatePeriod_found() {
        AssetPaymentDetail detail = new AssetPaymentDetail();
        Date postedDate = Date.valueOf("2024-01-15");
        detail.setExpenditureFinancialDocumentPostedDate(postedDate);

        UniversityDate uDate = new UniversityDate();
        uDate.setUniversityFiscalYear(2024);
        uDate.setUniversityFiscalAccountingPeriod("07");

        when(businessObjectService.findByPrimaryKey(eq(UniversityDate.class), ArgumentMatchers.<java.util.Map<String, Object>>any())).thenReturn(uDate);

        assertTrue(service.extractPostedDatePeriod(detail));
        assertEquals(Integer.valueOf(2024), detail.getPostingYear());
        assertEquals("07", detail.getPostingPeriodCode());
    }

    @Test
    public void testExtractPostedDatePeriod_notFound() {
        AssetPaymentDetail detail = new AssetPaymentDetail();
        detail.setExpenditureFinancialDocumentPostedDate(Date.valueOf("2099-01-01"));

        when(businessObjectService.findByPrimaryKey(eq(UniversityDate.class), ArgumentMatchers.<java.util.Map<String, Object>>any())).thenReturn(null);

        assertFalse(service.extractPostedDatePeriod(detail));
    }

    // --- getAssetPaymentDetailQuantity ---

    @Test
    public void testGetAssetPaymentDetailQuantity() {
        AssetGlobal assetGlobal = new AssetGlobal();
        assetGlobal.setAssetPaymentDetails(Arrays.asList(new AssetPaymentDetail(), new AssetPaymentDetail(), new AssetPaymentDetail()));

        assertEquals(Integer.valueOf(3), service.getAssetPaymentDetailQuantity(assetGlobal));
    }

    @Test
    public void testGetAssetPaymentDetailQuantity_empty() {
        AssetGlobal assetGlobal = new AssetGlobal();
        assetGlobal.setAssetPaymentDetails(new ArrayList<AssetPaymentDetail>());

        assertEquals(Integer.valueOf(0), service.getAssetPaymentDetailQuantity(assetGlobal));
    }

    // --- adjustPaymentAmounts ---
    // Note: adjustPaymentAmounts uses reflection via AssetPayment.getYearToDate()
    // which calls SpringContext.getBean(). Cannot test without Spring context.

    // --- getAssetDistributionType ---

    @Test
    public void testGetAssetDistributionType() {
        AssetPaymentAllocationType allocationType = new AssetPaymentAllocationType();
        when(businessObjectService.findByPrimaryKey(eq(AssetPaymentAllocationType.class), ArgumentMatchers.<java.util.Map<String, Object>>any()))
                .thenReturn(allocationType);

        assertSame(allocationType, service.getAssetDistributionType("1"));
    }

    @Test
    public void testGetAssetDistributionType_notFound() {
        when(businessObjectService.findByPrimaryKey(eq(AssetPaymentAllocationType.class), ArgumentMatchers.<java.util.Map<String, Object>>any()))
                .thenReturn(null);

        assertNull(service.getAssetDistributionType("X"));
    }
}
