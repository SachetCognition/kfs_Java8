package org.kuali.kfs.coa.businessobject;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AccountingPeriodTest extends KfsUnitTestBase {

    private AccountingPeriod period;

    @BeforeEach
    void setUp() {
        period = new AccountingPeriod();
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void universityFiscalYear() {
            period.setUniversityFiscalYear(2025);
            assertThat(period.getUniversityFiscalYear()).isEqualTo(2025);
        }

        @Test
        void universityFiscalPeriodCode() {
            period.setUniversityFiscalPeriodCode("01");
            assertThat(period.getUniversityFiscalPeriodCode()).isEqualTo("01");
        }

        @Test
        void universityFiscalPeriodName() {
            period.setUniversityFiscalPeriodName("July");
            assertThat(period.getUniversityFiscalPeriodName()).isEqualTo("July");
        }

        @Test
        void active() {
            period.setActive(true);
            assertThat(period.isActive()).isTrue();
            period.setActive(false);
            assertThat(period.isActive()).isFalse();
        }

        @Test
        void budgetRolloverIndicator() {
            period.setBudgetRolloverIndicator(true);
            assertThat(period.isBudgetRolloverIndicator()).isTrue();
            period.setBudgetRolloverIndicator(false);
            assertThat(period.isBudgetRolloverIndicator()).isFalse();
        }

        @Test
        void universityFiscalPeriodEndDate() {
            Date endDate = Date.valueOf("2025-07-31");
            period.setUniversityFiscalPeriodEndDate(endDate);
            assertThat(period.getUniversityFiscalPeriodEndDate()).isEqualTo(endDate);
        }
    }

    @Nested
    @DisplayName("isOpen logic")
    class IsOpenTests {

        @Test
        void activeAndOpenPeriodCode() {
            period.setActive(true);
            period.setUniversityFiscalPeriodCode("01");
            assertThat(period.isActive()).isTrue();
        }

        @Test
        void inactivePeriod() {
            period.setActive(false);
            assertThat(period.isActive()).isFalse();
        }
    }
}
