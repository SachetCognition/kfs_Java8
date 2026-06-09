package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.businessobject.Asset;
import org.kuali.kfs.coa.businessobject.ObjectCodeCurrent;
import org.kuali.kfs.module.cam.businessobject.AssetPayment;
import org.kuali.kfs.sys.businessobject.UniversityDate;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public class PaymentSummaryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private PaymentSummaryServiceImpl service;

    private void stubFederalContributionParam() {
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.FEDERAL_CONTRIBUTIONS_OBJECT_SUB_TYPES)))
                .thenReturn(Arrays.asList("BF"));
    }

    // --- addAmount ---

    @Test
    public void testAddAmount_nonNullAddend() {
        KualiDecimal result = service.addAmount(new KualiDecimal(10), new KualiDecimal(5));
        assertEquals(new KualiDecimal(15), result);
    }

    @Test
    public void testAddAmount_nullAddend() {
        KualiDecimal result = service.addAmount(new KualiDecimal(10), null);
        assertEquals(new KualiDecimal(10), result);
    }

    // --- calculateFederalContribution ---

    @Test
    public void testCalculateFederalContribution_withFederalPayments() {
        stubFederalContributionParam();
        Asset asset = new Asset();
        AssetPayment payment1 = mock(AssetPayment.class);
        ObjectCodeCurrent occ1 = new ObjectCodeCurrent();
        occ1.setFinancialObjectSubTypeCode("BF");
        when(payment1.getObjectCodeCurrent()).thenReturn(occ1);
        when(payment1.getAccountChargeAmount()).thenReturn(new KualiDecimal(100));

        AssetPayment payment2 = mock(AssetPayment.class);
        ObjectCodeCurrent occ2 = new ObjectCodeCurrent();
        occ2.setFinancialObjectSubTypeCode("CM");
        when(payment2.getObjectCodeCurrent()).thenReturn(occ2);

        asset.setAssetPayments(Arrays.asList(payment1, payment2));

        KualiDecimal result = service.calculateFederalContribution(asset);
        assertEquals(new KualiDecimal(100), result);
    }

    @Test
    public void testCalculateFederalContribution_noFederalPayments() {
        stubFederalContributionParam();
        Asset asset = new Asset();
        AssetPayment payment1 = mock(AssetPayment.class);
        ObjectCodeCurrent occ1 = new ObjectCodeCurrent();
        occ1.setFinancialObjectSubTypeCode("CM");
        when(payment1.getObjectCodeCurrent()).thenReturn(occ1);

        asset.setAssetPayments(Arrays.asList(payment1));

        KualiDecimal result = service.calculateFederalContribution(asset);
        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    public void testCalculateFederalContribution_emptyPayments() {
        Asset asset = new Asset();
        asset.setAssetPayments(new ArrayList<AssetPayment>());

        KualiDecimal result = service.calculateFederalContribution(asset);
        assertEquals(KualiDecimal.ZERO, result);
    }

    // --- calculatePaymentTotalCost ---

    @Test
    public void testCalculatePaymentTotalCost() {
        Asset asset = new Asset();
        AssetPayment p1 = new AssetPayment();
        p1.setAccountChargeAmount(new KualiDecimal(100));
        AssetPayment p2 = new AssetPayment();
        p2.setAccountChargeAmount(new KualiDecimal(200));
        AssetPayment p3 = new AssetPayment();
        p3.setAccountChargeAmount(null);
        asset.setAssetPayments(Arrays.asList(p1, p2, p3));

        KualiDecimal result = service.calculatePaymentTotalCost(asset);
        assertEquals(new KualiDecimal(300), result);
    }

    // --- calculatePrimaryAccumulatedDepreciation ---

    @Test
    public void testCalculatePrimaryAccumulatedDepreciation() {
        Asset asset = new Asset();
        AssetPayment p1 = new AssetPayment();
        p1.setAccumulatedPrimaryDepreciationAmount(new KualiDecimal(50));
        AssetPayment p2 = new AssetPayment();
        p2.setAccumulatedPrimaryDepreciationAmount(new KualiDecimal(30));
        AssetPayment p3 = new AssetPayment();
        p3.setAccumulatedPrimaryDepreciationAmount(null);
        asset.setAssetPayments(Arrays.asList(p1, p2, p3));

        assertEquals(new KualiDecimal(80), service.calculatePrimaryAccumulatedDepreciation(asset));
    }

    // --- calculatePrimaryBaseAmount ---

    @Test
    public void testCalculatePrimaryBaseAmount() {
        Asset asset = new Asset();
        AssetPayment p1 = new AssetPayment();
        p1.setPrimaryDepreciationBaseAmount(new KualiDecimal(200));
        AssetPayment p2 = new AssetPayment();
        p2.setPrimaryDepreciationBaseAmount(null);
        asset.setAssetPayments(Arrays.asList(p1, p2));

        assertEquals(new KualiDecimal(200), service.calculatePrimaryBaseAmount(asset));
    }

    // --- calculatePrimaryBookValue ---

    @Test
    public void testCalculatePrimaryBookValue() {
        Asset asset = new Asset();
        AssetPayment p1 = new AssetPayment();
        p1.setPrimaryDepreciationBaseAmount(new KualiDecimal(300));
        p1.setAccumulatedPrimaryDepreciationAmount(new KualiDecimal(100));
        asset.setAssetPayments(Arrays.asList(p1));

        KualiDecimal result = service.calculatePrimaryBookValue(asset);
        assertEquals(new KualiDecimal(200), result);
    }

    // --- calculatePrimaryPrevYearDepreciation ---

    @Test
    public void testCalculatePrimaryPrevYearDepreciation() {
        Asset asset = new Asset();
        AssetPayment p1 = new AssetPayment();
        p1.setPreviousYearPrimaryDepreciationAmount(new KualiDecimal(62));
        AssetPayment p2 = new AssetPayment();
        p2.setPreviousYearPrimaryDepreciationAmount(null);
        asset.setAssetPayments(Arrays.asList(p1, p2));

        assertEquals(new KualiDecimal(62), service.calculatePrimaryPrevYearDepreciation(asset));
    }

    // --- calculateAndSetPaymentSummary ---

    @Test
    public void testCalculateAndSetPaymentSummary_nullAsset() {
        service.calculateAndSetPaymentSummary(null);
        // should not throw
    }

    @Test
    public void testCalculateAndSetPaymentSummary_setsAllFields() {
        stubFederalContributionParam();
        UniversityDate uDate = new UniversityDate();
        uDate.setUniversityFiscalAccountingPeriod("05");
        when(universityDateService.getCurrentUniversityDate()).thenReturn(uDate);

        Asset asset = new Asset();
        AssetPayment p = new AssetPayment();
        p.setAccountChargeAmount(new KualiDecimal(1000));
        p.setAccumulatedPrimaryDepreciationAmount(new KualiDecimal(200));
        p.setPrimaryDepreciationBaseAmount(new KualiDecimal(800));
        p.setPreviousYearPrimaryDepreciationAmount(new KualiDecimal(50));
        p.setFinancialObjectCode("7100");
        p.setPeriod5Depreciation1Amount(new KualiDecimal(15));
        ObjectCodeCurrent occ = new ObjectCodeCurrent();
        occ.setFinancialObjectSubTypeCode("XX");
        p.setObjectCodeCurrent(occ);
        asset.setAssetPayments(Arrays.asList(p));
        asset.setPrimaryDepreciationMethodCode(CamsConstants.Asset.DEPRECIATION_METHOD_STRAIGHT_LINE_CODE);

        service.calculateAndSetPaymentSummary(asset);

        assertNotNull(asset.getPaymentTotalCost());
        assertNotNull(asset.getAccumulatedDepreciation());
        assertNotNull(asset.getBaseAmount());
        assertNotNull(asset.getBookValue());
    }
}
