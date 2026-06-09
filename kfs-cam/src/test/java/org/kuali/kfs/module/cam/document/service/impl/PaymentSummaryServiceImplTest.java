package org.kuali.kfs.module.cam.document.service.impl;

import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.module.cam.businessobject.AssetPayment;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentSummaryServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private PaymentSummaryServiceImpl service;

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private ParameterService parameterService;

    @Test
    @DisplayName("addAmount: null addend returns original amount")
    void addAmount_nullAddend() throws Exception {
        Method addAmount = PaymentSummaryServiceImpl.class.getDeclaredMethod("addAmount", KualiDecimal.class, KualiDecimal.class);
        addAmount.setAccessible(true);

        KualiDecimal result = (KualiDecimal) addAmount.invoke(service, new KualiDecimal(100), null);
        assertThat(result).isEqualTo(new KualiDecimal(100));
    }

    @Test
    @DisplayName("addAmount: non-null addend adds to amount")
    void addAmount_nonNullAddend() throws Exception {
        Method addAmount = PaymentSummaryServiceImpl.class.getDeclaredMethod("addAmount", KualiDecimal.class, KualiDecimal.class);
        addAmount.setAccessible(true);

        KualiDecimal result = (KualiDecimal) addAmount.invoke(service, new KualiDecimal(100), new KualiDecimal(50));
        assertThat(result).isEqualTo(new KualiDecimal(150));
    }

    @Test
    @DisplayName("addAmount: zero addend returns same amount")
    void addAmount_zeroAddend() throws Exception {
        Method addAmount = PaymentSummaryServiceImpl.class.getDeclaredMethod("addAmount", KualiDecimal.class, KualiDecimal.class);
        addAmount.setAccessible(true);

        KualiDecimal result = (KualiDecimal) addAmount.invoke(service, new KualiDecimal(100), KualiDecimal.ZERO);
        assertThat(result).isEqualTo(new KualiDecimal(100));
    }

    @Test
    @DisplayName("addAmount: negative addend subtracts")
    void addAmount_negativeAddend() throws Exception {
        Method addAmount = PaymentSummaryServiceImpl.class.getDeclaredMethod("addAmount", KualiDecimal.class, KualiDecimal.class);
        addAmount.setAccessible(true);

        KualiDecimal result = (KualiDecimal) addAmount.invoke(service, new KualiDecimal(100), new KualiDecimal(-30));
        assertThat(result).isEqualTo(new KualiDecimal(70));
    }

    @Test
    @DisplayName("AssetPayment period depreciation getters are accessible via reflection")
    void assetPaymentPeriodDepreciationGetters() throws Exception {
        AssetPayment payment = new AssetPayment();
        payment.setPeriod1Depreciation1Amount(new KualiDecimal(100));
        payment.setPeriod2Depreciation1Amount(new KualiDecimal(200));
        payment.setPeriod12Depreciation1Amount(new KualiDecimal(1200));

        Method getter1 = AssetPayment.class.getMethod("getPeriod1Depreciation1Amount");
        Method getter2 = AssetPayment.class.getMethod("getPeriod2Depreciation1Amount");
        Method getter12 = AssetPayment.class.getMethod("getPeriod12Depreciation1Amount");

        assertThat((KualiDecimal) getter1.invoke(payment)).isEqualTo(new KualiDecimal(100));
        assertThat((KualiDecimal) getter2.invoke(payment)).isEqualTo(new KualiDecimal(200));
        assertThat((KualiDecimal) getter12.invoke(payment)).isEqualTo(new KualiDecimal(1200));
    }
}
