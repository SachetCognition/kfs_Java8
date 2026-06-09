package org.kuali.kfs.module.ec.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.ld.LaborLedgerBalance;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LedgerBalanceConsolidationHelperTest extends KfsUnitTestBase {

    @Nested
    @DisplayName("consolidateLedgerBalances with string key")
    class ConsolidateWithStringKey {

        @Test
        void shouldAddNewBalanceWhenKeyDoesNotExist() {
            Map<String, LaborLedgerBalance> map = new HashMap<>();
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);

            LedgerBalanceConsolidationHelper.consolidateLedgerBalances(map, balance, "KEY1");

            assertThat(map).containsKey("KEY1");
            assertThat(map.get("KEY1")).isSameAs(balance);
        }

        @Test
        void shouldConsolidateWhenKeyExists() {
            Map<String, LaborLedgerBalance> map = new HashMap<>();
            LaborLedgerBalance existing = mock(LaborLedgerBalance.class);
            LaborLedgerBalance incoming = mock(LaborLedgerBalance.class);

            map.put("KEY1", existing);

            for (AccountingPeriodMonth period : AccountingPeriodMonth.values()) {
                lenient().when(incoming.getAmountByPeriod(period.periodCode)).thenReturn(new KualiDecimal(100));
            }

            LedgerBalanceConsolidationHelper.consolidateLedgerBalances(map, incoming, "KEY1");

            assertThat(map).containsKey("KEY1");
            assertThat(map.get("KEY1")).isSameAs(existing);
        }
    }

    @Nested
    @DisplayName("groupLedgerBalancesByKeys with string key")
    class GroupWithStringKey {

        @Test
        void shouldCreateNewGroupWhenKeyDoesNotExist() {
            Map<String, List<LaborLedgerBalance>> map = new HashMap<>();
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);

            LedgerBalanceConsolidationHelper.groupLedgerBalancesByKeys(map, balance, "GROUP1");

            assertThat(map).containsKey("GROUP1");
            assertThat(map.get("GROUP1")).hasSize(1);
        }

        @Test
        void shouldAddToExistingGroupWhenKeyExists() {
            Map<String, List<LaborLedgerBalance>> map = new HashMap<>();
            List<LaborLedgerBalance> existingList = new ArrayList<>();
            existingList.add(mock(LaborLedgerBalance.class));
            map.put("GROUP1", existingList);

            LaborLedgerBalance newBalance = mock(LaborLedgerBalance.class);
            LedgerBalanceConsolidationHelper.groupLedgerBalancesByKeys(map, newBalance, "GROUP1");

            assertThat(map.get("GROUP1")).hasSize(2);
        }
    }

    @Nested
    @DisplayName("addLedgerBalanceAmounts")
    class AddAmounts {

        @Test
        void shouldHandleNullSecondBalance() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            LedgerBalanceConsolidationHelper.addLedgerBalanceAmounts(balance, null);
            // no exception thrown
        }

        @Test
        void shouldHandleNullFirstBalance() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            LedgerBalanceConsolidationHelper.addLedgerBalanceAmounts(null, balance);
            // no exception thrown
        }
    }

    @Nested
    @DisplayName("calculateTotalAmountWithinReportPeriod - single balance")
    class CalculateTotalSingle {

        @Test
        void shouldSumPeriodsInRange() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            when(balance.getUniversityFiscalYear()).thenReturn(2024);
            when(balance.getAmountByPeriod("01")).thenReturn(new KualiDecimal(100));
            when(balance.getAmountByPeriod("02")).thenReturn(new KualiDecimal(200));
            when(balance.getAmountByPeriod("03")).thenReturn(new KualiDecimal(300));

            Map<Integer, Set<String>> reportPeriods = new HashMap<>();
            Set<String> periods = new HashSet<>(Arrays.asList("01", "02", "03"));
            reportPeriods.put(2024, periods);

            KualiDecimal result = LedgerBalanceConsolidationHelper.calculateTotalAmountWithinReportPeriod(balance, reportPeriods);

            assertThat(result).isEqualTo(new KualiDecimal(600));
        }
    }

    @Nested
    @DisplayName("calculateTotalAmountWithinReportPeriod - collection")
    class CalculateTotalCollection {

        @Test
        void shouldSumAcrossBalances() {
            LaborLedgerBalance balance1 = mock(LaborLedgerBalance.class);
            when(balance1.getUniversityFiscalYear()).thenReturn(2024);
            when(balance1.getAmountByPeriod("01")).thenReturn(new KualiDecimal(100));

            LaborLedgerBalance balance2 = mock(LaborLedgerBalance.class);
            when(balance2.getUniversityFiscalYear()).thenReturn(2024);
            when(balance2.getAmountByPeriod("01")).thenReturn(new KualiDecimal(200));

            Collection<LaborLedgerBalance> balances = Arrays.asList(balance1, balance2);

            Map<Integer, Set<String>> reportPeriods = new HashMap<>();
            Set<String> periods = new HashSet<>(Arrays.asList("01"));
            reportPeriods.put(2024, periods);

            KualiDecimal result = LedgerBalanceConsolidationHelper.calculateTotalAmountWithinReportPeriod(balances, reportPeriods);

            assertThat(result).isEqualTo(new KualiDecimal(300));
        }

        @Test
        void shouldReturnZeroForEmptyCollection() {
            Collection<LaborLedgerBalance> balances = new ArrayList<>();

            Map<Integer, Set<String>> reportPeriods = new HashMap<>();
            Set<String> periods = new HashSet<>(Arrays.asList("01"));
            reportPeriods.put(2024, periods);

            KualiDecimal result = LedgerBalanceConsolidationHelper.calculateTotalAmountWithinReportPeriod(balances, reportPeriods);

            assertThat(result).isEqualTo(KualiDecimal.ZERO);
        }
    }

    @Nested
    @DisplayName("Deprecated methods delegate correctly")
    class DeprecatedMethods {

        @Test
        void deprecatedSingleBalanceMethodShouldDelegate() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            when(balance.getUniversityFiscalYear()).thenReturn(2024);
            when(balance.getAmountByPeriod("01")).thenReturn(new KualiDecimal(500));

            Map<Integer, Set<String>> reportPeriods = new HashMap<>();
            Set<String> periods = new HashSet<>(Arrays.asList("01"));
            reportPeriods.put(2024, periods);

            @SuppressWarnings("deprecation")
            KualiDecimal result = LedgerBalanceConsolidationHelper.calculateTotalAmountWithinReportPeriod(balance, reportPeriods, true);

            assertThat(result).isEqualTo(new KualiDecimal(500));
        }

        @Test
        void deprecatedCollectionMethodShouldDelegate() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            when(balance.getUniversityFiscalYear()).thenReturn(2024);
            when(balance.getAmountByPeriod("02")).thenReturn(new KualiDecimal(750));

            Collection<LaborLedgerBalance> balances = Arrays.asList(balance);

            Map<Integer, Set<String>> reportPeriods = new HashMap<>();
            Set<String> periods = new HashSet<>(Arrays.asList("02"));
            reportPeriods.put(2024, periods);

            @SuppressWarnings("deprecation")
            KualiDecimal result = LedgerBalanceConsolidationHelper.calculateTotalAmountWithinReportPeriod(balances, reportPeriods, false);

            assertThat(result).isEqualTo(new KualiDecimal(750));
        }
    }
}
