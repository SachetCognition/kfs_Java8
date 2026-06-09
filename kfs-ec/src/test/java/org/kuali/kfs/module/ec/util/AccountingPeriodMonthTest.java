package org.kuali.kfs.module.ec.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AccountingPeriodMonthTest extends KfsUnitTestBase {

    @Nested
    @DisplayName("findAccountingPeriod")
    class FindAccountingPeriod {

        @Test
        void shouldFindMonth1() {
            AccountingPeriodMonth result = AccountingPeriodMonth.findAccountingPeriod("01");
            assertThat(result).isEqualTo(AccountingPeriodMonth.MONTH1);
        }

        @Test
        void shouldFindMonth12() {
            AccountingPeriodMonth result = AccountingPeriodMonth.findAccountingPeriod("12");
            assertThat(result).isEqualTo(AccountingPeriodMonth.MONTH12);
        }

        @Test
        void shouldReturnNullForInvalidCode() {
            AccountingPeriodMonth result = AccountingPeriodMonth.findAccountingPeriod("99");
            assertThat(result).isNull();
        }

        @Test
        void shouldReturnNullForNull() {
            AccountingPeriodMonth result = AccountingPeriodMonth.findAccountingPeriod(null);
            assertThat(result).isNull();
        }
    }

    @Nested
    @DisplayName("findAccountingPeriodsBetween")
    class FindPeriodsBetween {

        @Test
        void shouldReturnPeriodsWithinSameYear() {
            Map<Integer, Set<String>> periods = AccountingPeriodMonth.findAccountingPeriodsBetween(
                    2024, "01", 2024, "06");
            assertThat(periods).containsKey(2024);
            assertThat(periods.get(2024)).containsExactlyInAnyOrder("01", "02", "03", "04", "05", "06");
        }

        @Test
        void shouldReturnPeriodsAcrossYears() {
            Map<Integer, Set<String>> periods = AccountingPeriodMonth.findAccountingPeriodsBetween(
                    2023, "10", 2024, "03");
            assertThat(periods).containsKeys(2023, 2024);
            assertThat(periods.get(2023)).contains("10", "11", "12");
            assertThat(periods.get(2024)).contains("01", "02", "03");
        }

        @Test
        void shouldReturnSinglePeriodWhenStartEqualsEnd() {
            Map<Integer, Set<String>> periods = AccountingPeriodMonth.findAccountingPeriodsBetween(
                    2024, "06", 2024, "06");
            assertThat(periods.get(2024)).containsExactly("06");
        }

        @Test
        void shouldReturnAllPeriodsForFullYear() {
            Map<Integer, Set<String>> periods = AccountingPeriodMonth.findAccountingPeriodsBetween(
                    2024, "01", 2024, "12");
            assertThat(periods.get(2024)).hasSize(12);
        }
    }

    @Nested
    @DisplayName("buildPeriodCodeSetWithinRange")
    class BuildPeriodCodeSet {

        @Test
        void shouldBuildFromRange() {
            Set<String> codes = AccountingPeriodMonth.buildPeriodCodeSetWithinRange(
                    AccountingPeriodMonth.MONTH1, AccountingPeriodMonth.MONTH6);
            assertThat(codes).containsExactlyInAnyOrder("01", "02", "03", "04", "05", "06");
        }

        @Test
        void shouldBuildSingleElement() {
            Set<String> codes = AccountingPeriodMonth.buildPeriodCodeSetWithinRange(
                    AccountingPeriodMonth.MONTH5, AccountingPeriodMonth.MONTH5);
            assertThat(codes).containsExactly("05");
        }
    }

    @Nested
    @DisplayName("Enum properties")
    class EnumProperties {

        @Test
        void shouldHave12Values() {
            assertThat(AccountingPeriodMonth.values()).hasSize(12);
        }

        @Test
        void shouldReturnCorrectPeriodCode() {
            assertThat(AccountingPeriodMonth.MONTH1.periodCode).isEqualTo("01");
            assertThat(AccountingPeriodMonth.MONTH6.periodCode).isEqualTo("06");
            assertThat(AccountingPeriodMonth.MONTH12.periodCode).isEqualTo("12");
        }
    }
}
