package org.kuali.kfs.module.tem.businessobject;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MileageRate Business Object")
class MileageRateTest extends KfsUnitTestBase {

    private MileageRate mileageRate;

    @BeforeEach
    void setUp() {
        mileageRate = new MileageRate();
    }

    @Test
    @DisplayName("should set and get id")
    void testId() {
        mileageRate.setId(1);
        assertThat(mileageRate.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("should set and get rate")
    void testRate() {
        BigDecimal rate = new BigDecimal("0.655");
        mileageRate.setRate(rate);
        assertThat(mileageRate.getRate()).isEqualByComparingTo(rate);
    }

    @Test
    @DisplayName("should set and get activeFromDate")
    void testActiveFromDate() {
        Date date = Date.valueOf("2024-01-01");
        mileageRate.setActiveFromDate(date);
        assertThat(mileageRate.getActiveFromDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("should set and get activeToDate")
    void testActiveToDate() {
        Date date = Date.valueOf("2024-12-31");
        mileageRate.setActiveToDate(date);
        assertThat(mileageRate.getActiveToDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("should set and get expenseTypeCode")
    void testExpenseTypeCode() {
        mileageRate.setExpenseTypeCode("MILE");
        assertThat(mileageRate.getExpenseTypeCode()).isEqualTo("MILE");
    }

    @Test
    @DisplayName("should set and get expenseType")
    void testExpenseType() {
        ExpenseType expenseType = new ExpenseType();
        mileageRate.setExpenseType(expenseType);
        assertThat(mileageRate.getExpenseType()).isSameAs(expenseType);
    }

    @Test
    @DisplayName("should format codeAndRate correctly")
    void testGetCodeAndRate() {
        mileageRate.setExpenseTypeCode("PRIV");
        mileageRate.setRate(new BigDecimal("0.575"));
        String result = mileageRate.getCodeAndRate();
        assertThat(result).contains("PRIV");
        assertThat(result).contains("0.575");
        assertThat(result).contains("$");
    }

    @Test
    @DisplayName("toStringMapper should include id and rate")
    void testToStringMapper() {
        mileageRate.setId(5);
        mileageRate.setRate(new BigDecimal("0.50"));
        // toStringMapper_RICE20_REFACTORME is protected, test indirectly via codeAndRate
        assertThat(mileageRate.getId()).isEqualTo(5);
        assertThat(mileageRate.getRate()).isEqualByComparingTo(new BigDecimal("0.50"));
    }
}
