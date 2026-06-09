package org.kuali.kfs.module.ec.document.validation.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Organization;
import org.kuali.kfs.coa.businessobject.SubFundGroup;
import org.kuali.kfs.integration.ld.LaborLedgerBalance;
import org.kuali.kfs.sys.Message;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class LedgerBalanceFieldValidatorTest extends KfsUnitTestBase {

    /**
     * Sets up a MockedStatic for SpringContext so that MessageBuilder's static
     * initializer (which calls SpringContext.getBean) can complete without a
     * real Spring context. The caller must close the returned scope.
     */
    private static MockedStatic<SpringContext> setupSpringContextMock() {
        ConfigurationService configService = mock(ConfigurationService.class);
        lenient().when(configService.getPropertyValueAsString(anyString())).thenReturn("Test error");

        MockedStatic<SpringContext> springMock = mockStatic(SpringContext.class);
        springMock.when(() -> SpringContext.getBean(any(Class.class))).thenAnswer(inv -> {
            Class<?> type = inv.getArgument(0);
            if (type == ConfigurationService.class) {
                return configService;
            }
            return mock(type);
        });
        return springMock;
    }

    @Nested
    @DisplayName("hasValidAccount")
    class HasValidAccount {

        @Test
        void shouldReturnNullWhenAccountExists() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            Account account = new Account();
            when(balance.getAccount()).thenReturn(account);

            Message result = LedgerBalanceFieldValidator.hasValidAccount(balance);
            assertThat(result).isNull();
        }

        @Test
        void shouldReturnMessageWhenAccountNull() {
            try (MockedStatic<SpringContext> ignored = setupSpringContextMock()) {
                LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
                when(balance.getAccount()).thenReturn(null);
                when(balance.getChartOfAccountsCode()).thenReturn("BL");
                when(balance.getAccountNumber()).thenReturn("1234567");

                Message result = LedgerBalanceFieldValidator.hasValidAccount(balance);
                assertThat(result).isNotNull();
                assertThat(result.getType()).isEqualTo(Message.TYPE_FATAL);
            }
        }
    }

    @Nested
    @DisplayName("getSubFundGroup")
    class GetSubFundGroup {

        @Test
        void shouldReturnSubFundGroupWhenPresent() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            Account account = mock(Account.class);
            SubFundGroup sfg = new SubFundGroup();
            sfg.setSubFundGroupCode("SFG1");
            when(balance.getAccount()).thenReturn(account);
            when(account.getSubFundGroup()).thenReturn(sfg);

            SubFundGroup result = LedgerBalanceFieldValidator.getSubFundGroup(balance);
            assertThat(result).isNotNull();
            assertThat(result.getSubFundGroupCode()).isEqualTo("SFG1");
        }

        @Test
        void shouldReturnNullWhenAccountNull() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            when(balance.getAccount()).thenReturn(null);

            SubFundGroup result = LedgerBalanceFieldValidator.getSubFundGroup(balance);
            assertThat(result).isNull();
        }

        @Test
        void shouldReturnNullWhenSubFundGroupNull() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            Account account = mock(Account.class);
            when(balance.getAccount()).thenReturn(account);
            when(account.getSubFundGroup()).thenReturn(null);

            SubFundGroup result = LedgerBalanceFieldValidator.getSubFundGroup(balance);
            assertThat(result).isNull();
        }
    }

    @Nested
    @DisplayName("isInFundGroups")
    class IsInFundGroups {

        @Test
        void shouldReturnNullWhenFundGroupCodeInList() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            Account account = mock(Account.class);
            SubFundGroup sfg = new SubFundGroup();
            sfg.setFundGroupCode("CG");
            when(balance.getAccount()).thenReturn(account);
            when(account.getSubFundGroup()).thenReturn(sfg);

            List<String> fundGroupCodes = Arrays.asList("CG", "GF");
            Message result = LedgerBalanceFieldValidator.isInFundGroups(balance, fundGroupCodes);
            assertThat(result).isNull();
        }

        @Test
        void shouldReturnMessageWhenFundGroupCodeNotInList() {
            try (MockedStatic<SpringContext> ignored = setupSpringContextMock()) {
                LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
                Account account = mock(Account.class);
                SubFundGroup sfg = new SubFundGroup();
                sfg.setFundGroupCode("XX");
                when(balance.getAccount()).thenReturn(account);
                when(account.getSubFundGroup()).thenReturn(sfg);

                List<String> fundGroupCodes = Arrays.asList("CG", "GF");
                Message result = LedgerBalanceFieldValidator.isInFundGroups(balance, fundGroupCodes);
                assertThat(result).isNotNull();
            }
        }
    }

    @Nested
    @DisplayName("isInSubFundGroups")
    class IsInSubFundGroups {

        @Test
        void shouldReturnNullWhenSubFundGroupCodeInList() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            Account account = mock(Account.class);
            SubFundGroup sfg = new SubFundGroup();
            sfg.setSubFundGroupCode("HIEDUA");
            when(balance.getAccount()).thenReturn(account);
            when(account.getSubFundGroup()).thenReturn(sfg);

            List<String> subFundGroupCodes = Arrays.asList("HIEDUA", "HIEDUB");
            Message result = LedgerBalanceFieldValidator.isInSubFundGroups(balance, subFundGroupCodes);
            assertThat(result).isNull();
        }

        @Test
        void shouldReturnMessageWhenSubFundGroupCodeNotInList() {
            try (MockedStatic<SpringContext> ignored = setupSpringContextMock()) {
                LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
                Account account = mock(Account.class);
                SubFundGroup sfg = new SubFundGroup();
                sfg.setSubFundGroupCode("XX");
                when(balance.getAccount()).thenReturn(account);
                when(account.getSubFundGroup()).thenReturn(sfg);

                List<String> subFundGroupCodes = Arrays.asList("HIEDUA", "HIEDUB");
                Message result = LedgerBalanceFieldValidator.isInSubFundGroups(balance, subFundGroupCodes);
                assertThat(result).isNotNull();
            }
        }
    }

    @Nested
    @DisplayName("hasGrantAccount")
    class HasGrantAccount {

        @Test
        void shouldReturnNullWhenAccountIsForContractsAndGrants() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            Account account = mock(Account.class);
            when(balance.getAccount()).thenReturn(account);
            when(account.isForContractsAndGrants()).thenReturn(true);

            Collection<LaborLedgerBalance> balances = new ArrayList<>();
            balances.add(balance);

            Message result = LedgerBalanceFieldValidator.hasGrantAccount(balances);
            assertThat(result).isNull();
        }

        @Test
        void shouldReturnMessageWhenNoGrantAccount() {
            try (MockedStatic<SpringContext> ignored = setupSpringContextMock()) {
                LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
                Account account = mock(Account.class);
                when(balance.getAccount()).thenReturn(account);
                when(account.isForContractsAndGrants()).thenReturn(false);

                Collection<LaborLedgerBalance> balances = new ArrayList<>();
                balances.add(balance);

                Message result = LedgerBalanceFieldValidator.hasGrantAccount(balances);
                assertThat(result).isNotNull();
                assertThat(result.getType()).isEqualTo(Message.TYPE_FATAL);
            }
        }
    }

    @Nested
    @DisplayName("isFromSingleOrganization")
    class IsFromSingleOrganization {

        @Test
        void shouldReturnNullWhenAllFromSameOrganization() {
            Organization org = new Organization();
            org.setChartOfAccountsCode("BL");
            org.setOrganizationCode("ACCT");

            LaborLedgerBalance balance1 = mock(LaborLedgerBalance.class);
            Account account1 = mock(Account.class);
            when(balance1.getAccount()).thenReturn(account1);
            when(account1.getOrganization()).thenReturn(org);

            LaborLedgerBalance balance2 = mock(LaborLedgerBalance.class);
            Account account2 = mock(Account.class);
            when(balance2.getAccount()).thenReturn(account2);
            when(account2.getOrganization()).thenReturn(org);

            Collection<LaborLedgerBalance> balances = new ArrayList<>();
            balances.add(balance1);
            balances.add(balance2);

            Message result = LedgerBalanceFieldValidator.isFromSingleOrganization(balances);
            assertThat(result).isNull();
        }

        @Test
        void shouldReturnMessageWhenMultipleOrganizations() {
            try (MockedStatic<SpringContext> ignored = setupSpringContextMock()) {
                Organization org1 = new Organization();
                org1.setChartOfAccountsCode("BL");
                org1.setOrganizationCode("ACCT");

                Organization org2 = new Organization();
                org2.setChartOfAccountsCode("UA");
                org2.setOrganizationCode("PHYS");

                LaborLedgerBalance balance1 = mock(LaborLedgerBalance.class);
                Account account1 = mock(Account.class);
                when(balance1.getAccount()).thenReturn(account1);
                when(account1.getOrganization()).thenReturn(org1);

                LaborLedgerBalance balance2 = mock(LaborLedgerBalance.class);
                Account account2 = mock(Account.class);
                when(balance2.getAccount()).thenReturn(account2);
                when(account2.getOrganization()).thenReturn(org2);

                Collection<LaborLedgerBalance> balances = new ArrayList<>();
                balances.add(balance1);
                balances.add(balance2);

                Message result = LedgerBalanceFieldValidator.isFromSingleOrganization(balances);
                assertThat(result).isNotNull();
                assertThat(result.getType()).isEqualTo(Message.TYPE_FATAL);
            }
        }
    }

    @Nested
    @DisplayName("isNonZeroAmountBalanceWithinReportPeriod")
    class IsNonZeroAmount {

        @Test
        void shouldReturnNullWhenAmountNonZero() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            when(balance.getUniversityFiscalYear()).thenReturn(2024);
            when(balance.getAmountByPeriod("01")).thenReturn(new KualiDecimal(100));

            Map<Integer, Set<String>> reportPeriods = new HashMap<>();
            Set<String> periods = new HashSet<>();
            periods.add("01");
            reportPeriods.put(2024, periods);

            Message result = LedgerBalanceFieldValidator.isNonZeroAmountBalanceWithinReportPeriod(balance, reportPeriods);
            assertThat(result).isNull();
        }

        @Test
        void shouldReturnMessageWhenAmountIsZero() {
            try (MockedStatic<SpringContext> ignored = setupSpringContextMock()) {
                LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
                when(balance.getUniversityFiscalYear()).thenReturn(2024);
                when(balance.getAmountByPeriod("01")).thenReturn(KualiDecimal.ZERO);

                Map<Integer, Set<String>> reportPeriods = new HashMap<>();
                Set<String> periods = new HashSet<>();
                periods.add("01");
                reportPeriods.put(2024, periods);

                Message result = LedgerBalanceFieldValidator.isNonZeroAmountBalanceWithinReportPeriod(balance, reportPeriods);
                assertThat(result).isNotNull();
                assertThat(result.getType()).isEqualTo(Message.TYPE_FATAL);
            }
        }
    }

    @Nested
    @DisplayName("isTotalAmountPositive")
    class IsTotalAmountPositive {

        @Test
        void shouldReturnNullWhenTotalIsPositive() {
            LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
            when(balance.getUniversityFiscalYear()).thenReturn(2024);
            when(balance.getAmountByPeriod("01")).thenReturn(new KualiDecimal(500));

            Collection<LaborLedgerBalance> balances = new ArrayList<>();
            balances.add(balance);

            Map<Integer, Set<String>> reportPeriods = new HashMap<>();
            Set<String> periods = new HashSet<>();
            periods.add("01");
            reportPeriods.put(2024, periods);

            Message result = LedgerBalanceFieldValidator.isTotalAmountPositive(balances, reportPeriods);
            assertThat(result).isNull();
        }

        @Test
        void shouldReturnMessageWhenTotalNotPositive() {
            try (MockedStatic<SpringContext> ignored = setupSpringContextMock()) {
                LaborLedgerBalance balance = mock(LaborLedgerBalance.class);
                when(balance.getUniversityFiscalYear()).thenReturn(2024);
                when(balance.getAmountByPeriod("01")).thenReturn(KualiDecimal.ZERO);

                Collection<LaborLedgerBalance> balances = new ArrayList<>();
                balances.add(balance);

                Map<Integer, Set<String>> reportPeriods = new HashMap<>();
                Set<String> periods = new HashSet<>();
                periods.add("01");
                reportPeriods.put(2024, periods);

                Message result = LedgerBalanceFieldValidator.isTotalAmountPositive(balances, reportPeriods);
                assertThat(result).isNotNull();
            }
        }
    }
}
