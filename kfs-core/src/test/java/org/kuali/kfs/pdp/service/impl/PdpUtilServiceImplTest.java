package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.businessobject.PaymentAccountDetail;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PdpUtilServiceImplTest extends KfsUnitTestBase {

    private PdpUtilServiceImpl pdpUtilService;

    @BeforeEach
    void setUp() {
        pdpUtilService = new PdpUtilServiceImpl();
    }

    @Test
    void isDebit_positiveAmount_notReversal_returnsTrue() {
        PaymentAccountDetail detail = new PaymentAccountDetail();
        detail.setAccountNetAmount(new KualiDecimal(100));

        assertThat(pdpUtilService.isDebit(detail, false)).isTrue();
    }

    @Test
    void isDebit_zeroAmount_notReversal_returnsTrue() {
        PaymentAccountDetail detail = new PaymentAccountDetail();
        detail.setAccountNetAmount(KualiDecimal.ZERO);

        assertThat(pdpUtilService.isDebit(detail, false)).isTrue();
    }

    @Test
    void isDebit_negativeAmount_notReversal_returnsFalse() {
        PaymentAccountDetail detail = new PaymentAccountDetail();
        detail.setAccountNetAmount(new KualiDecimal(-100));

        assertThat(pdpUtilService.isDebit(detail, false)).isFalse();
    }

    @Test
    void isDebit_positiveAmount_reversal_returnsFalse() {
        PaymentAccountDetail detail = new PaymentAccountDetail();
        detail.setAccountNetAmount(new KualiDecimal(100));

        assertThat(pdpUtilService.isDebit(detail, true)).isFalse();
    }

    @Test
    void isDebit_negativeAmount_reversal_returnsTrue() {
        PaymentAccountDetail detail = new PaymentAccountDetail();
        detail.setAccountNetAmount(new KualiDecimal(-100));

        assertThat(pdpUtilService.isDebit(detail, true)).isTrue();
    }
}
