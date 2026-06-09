package org.kuali.kfs.fp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class TravelMileageRateTest extends KfsUnitTestBase {

    private TravelMileageRate rate;

    @BeforeEach
    void setUp() {
        rate = new TravelMileageRate();
    }

    @Test
    void defaultConstructorCreatesEmptyObject() {
        assertThat(rate.getMileageLimitAmount()).isNull();
        assertThat(rate.getMileageRate()).isNull();
        assertThat(rate.getDisbursementVoucherMileageEffectiveDate()).isNull();
    }

    @Test
    void setAndGetMileageLimitAmount() {
        rate.setMileageLimitAmount(500);
        assertThat(rate.getMileageLimitAmount()).isEqualTo(500);
    }

    @Test
    void setAndGetMileageRate() {
        BigDecimal mileageRate = new BigDecimal("0.585");
        rate.setMileageRate(mileageRate);
        assertThat(rate.getMileageRate()).isEqualByComparingTo(mileageRate);
    }

    @Test
    void setAndGetDisbursementVoucherMileageEffectiveDate() {
        Date effectiveDate = Date.valueOf("2024-01-01");
        rate.setDisbursementVoucherMileageEffectiveDate(effectiveDate);
        assertThat(rate.getDisbursementVoucherMileageEffectiveDate()).isEqualTo(effectiveDate);
    }
}
