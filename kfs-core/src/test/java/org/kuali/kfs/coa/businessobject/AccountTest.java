package org.kuali.kfs.coa.businessobject;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest extends KfsUnitTestBase {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Nested
    @DisplayName("Constructor defaults")
    class ConstructorTests {
        @Test
        void defaultConstructorSetsActiveTrue() {
            assertThat(account.isActive()).isTrue();
        }

        @Test
        void defaultConstructorInitializesIndirectCostRecoveryAccounts() {
            assertThat(account.getIndirectCostRecoveryAccounts()).isNotNull().isEmpty();
        }
    }

    @Nested
    @DisplayName("isExpired(Calendar)")
    class IsExpiredCalendarTests {

        @Test
        void returnsFalseWhenExpirationDateIsNull() {
            account.setAccountExpirationDate(null);
            Calendar today = Calendar.getInstance();
            assertThat(account.isExpired(today)).isFalse();
        }

        @Test
        void returnsFalseWhenExpirationDateIsSameAsTestDate() {
            Calendar testDate = Calendar.getInstance();
            testDate.set(2025, Calendar.JUNE, 15, 0, 0, 0);
            testDate.set(Calendar.MILLISECOND, 0);
            account.setAccountExpirationDate(new Date(testDate.getTimeInMillis()));
            assertThat(account.isExpired(testDate)).isFalse();
        }

        @Test
        void returnsTrueWhenExpirationDateIsBeforeTestDate() {
            Calendar expDate = Calendar.getInstance();
            expDate.set(2025, Calendar.JUNE, 14, 0, 0, 0);
            expDate.set(Calendar.MILLISECOND, 0);
            account.setAccountExpirationDate(new Date(expDate.getTimeInMillis()));

            Calendar testDate = Calendar.getInstance();
            testDate.set(2025, Calendar.JUNE, 15, 0, 0, 0);
            testDate.set(Calendar.MILLISECOND, 0);

            assertThat(account.isExpired(testDate)).isTrue();
        }

        @Test
        void returnsFalseWhenExpirationDateIsAfterTestDate() {
            Calendar expDate = Calendar.getInstance();
            expDate.set(2025, Calendar.JUNE, 16, 0, 0, 0);
            expDate.set(Calendar.MILLISECOND, 0);
            account.setAccountExpirationDate(new Date(expDate.getTimeInMillis()));

            Calendar testDate = Calendar.getInstance();
            testDate.set(2025, Calendar.JUNE, 15, 0, 0, 0);
            testDate.set(Calendar.MILLISECOND, 0);

            assertThat(account.isExpired(testDate)).isFalse();
        }

        @Test
        void ignoresTimeComponentsInComparison() {
            Calendar expDate = Calendar.getInstance();
            expDate.set(2025, Calendar.JUNE, 14, 23, 59, 59);
            account.setAccountExpirationDate(new Date(expDate.getTimeInMillis()));

            Calendar testDate = Calendar.getInstance();
            testDate.set(2025, Calendar.JUNE, 15, 0, 0, 1);

            assertThat(account.isExpired(testDate)).isTrue();
        }
    }

    @Nested
    @DisplayName("isExpired(Date)")
    class IsExpiredDateTests {

        @Test
        void returnsFalseWhenExpirationDateIsNull() {
            account.setAccountExpirationDate(null);
            Date testDate = Date.valueOf("2025-06-15");
            assertThat(account.isExpired(testDate)).isFalse();
        }

        @Test
        void returnsTrueWhenExpirationDateIsBeforeTestDate() {
            account.setAccountExpirationDate(Date.valueOf("2025-06-14"));
            assertThat(account.isExpired(Date.valueOf("2025-06-15"))).isTrue();
        }

        @Test
        void returnsFalseWhenExpirationDateIsSameAsTestDate() {
            account.setAccountExpirationDate(Date.valueOf("2025-06-15"));
            assertThat(account.isExpired(Date.valueOf("2025-06-15"))).isFalse();
        }
    }

    @Nested
    @DisplayName("isClosed / setClosed")
    class ClosedTests {

        @Test
        void isClosedReturnsFalseWhenActive() {
            account.setActive(true);
            assertThat(account.isClosed()).isFalse();
        }

        @Test
        void isClosedReturnsTrueWhenInactive() {
            account.setActive(false);
            assertThat(account.isClosed()).isTrue();
        }

        @Test
        void setClosedTrueDeactivatesAccount() {
            account.setClosed(true);
            assertThat(account.isActive()).isFalse();
        }

        @Test
        void setClosedFalseActivatesAccount() {
            account.setClosed(false);
            assertThat(account.isActive()).isTrue();
        }
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void chartOfAccountsCode() {
            account.setChartOfAccountsCode("BL");
            assertThat(account.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void accountNumber() {
            account.setAccountNumber("1234567");
            assertThat(account.getAccountNumber()).isEqualTo("1234567");
        }

        @Test
        void accountName() {
            account.setAccountName("Test Account");
            assertThat(account.getAccountName()).isEqualTo("Test Account");
        }

        @Test
        void accountsFringesBnftIndicator() {
            account.setAccountsFringesBnftIndicator(true);
            assertThat(account.isAccountsFringesBnftIndicator()).isTrue();
        }

        @Test
        void organizationCode() {
            account.setOrganizationCode("ORG1");
            assertThat(account.getOrganizationCode()).isEqualTo("ORG1");
        }

        @Test
        void accountTypeCode() {
            account.setAccountTypeCode("EX");
            assertThat(account.getAccountTypeCode()).isEqualTo("EX");
        }

        @Test
        void subFundGroupCode() {
            account.setSubFundGroupCode("HIEDUA");
            assertThat(account.getSubFundGroupCode()).isEqualTo("HIEDUA");
        }

        @Test
        void accountCityName() {
            account.setAccountCityName("Bloomington");
            assertThat(account.getAccountCityName()).isEqualTo("Bloomington");
        }

        @Test
        void accountStateCode() {
            account.setAccountStateCode("IN");
            assertThat(account.getAccountStateCode()).isEqualTo("IN");
        }

        @Test
        void accountStreetAddress() {
            account.setAccountStreetAddress("123 Main St");
            assertThat(account.getAccountStreetAddress()).isEqualTo("123 Main St");
        }

        @Test
        void accountZipCode() {
            account.setAccountZipCode("47401");
            assertThat(account.getAccountZipCode()).isEqualTo("47401");
        }

        @Test
        void budgetRecordingLevelCode() {
            account.setBudgetRecordingLevelCode("A");
            assertThat(account.getBudgetRecordingLevelCode()).isEqualTo("A");
        }

        @Test
        void accountSufficientFundsCode() {
            account.setAccountSufficientFundsCode("H");
            assertThat(account.getAccountSufficientFundsCode()).isEqualTo("H");
        }

        @Test
        void accountEffectiveDate() {
            Date d = Date.valueOf("2020-01-01");
            account.setAccountEffectiveDate(d);
            assertThat(account.getAccountEffectiveDate()).isEqualTo(d);
        }

        @Test
        void accountCreateDate() {
            Date d = Date.valueOf("2019-01-01");
            account.setAccountCreateDate(d);
            assertThat(account.getAccountCreateDate()).isEqualTo(d);
        }

        @Test
        void continuationFinChrtOfAcctCd() {
            account.setContinuationFinChrtOfAcctCd("UA");
            assertThat(account.getContinuationFinChrtOfAcctCd()).isEqualTo("UA");
        }

        @Test
        void continuationAccountNumber() {
            account.setContinuationAccountNumber("9999999");
            assertThat(account.getContinuationAccountNumber()).isEqualTo("9999999");
        }

        @Test
        void reportsToChartOfAccountsCode() {
            account.setReportsToChartOfAccountsCode("BL");
            assertThat(account.getReportsToChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void reportsToAccountNumber() {
            account.setReportsToAccountNumber("5555555");
            assertThat(account.getReportsToAccountNumber()).isEqualTo("5555555");
        }

        @Test
        void accountCfdaNumber() {
            account.setAccountCfdaNumber("84.063");
            assertThat(account.getAccountCfdaNumber()).isEqualTo("84.063");
        }

        @Test
        void accountOffCampusIndicator() {
            account.setAccountOffCampusIndicator(true);
            assertThat(account.isAccountOffCampusIndicator()).isTrue();
        }

        @Test
        void financialHigherEdFunctionCd() {
            account.setFinancialHigherEdFunctionCd("4000");
            assertThat(account.getFinancialHigherEdFunctionCd()).isEqualTo("4000");
        }

        @Test
        void accountRestrictedStatusCode() {
            account.setAccountRestrictedStatusCode("R");
            assertThat(account.getAccountRestrictedStatusCode()).isEqualTo("R");
        }

        @Test
        void accountPhysicalCampusCode() {
            account.setAccountPhysicalCampusCode("BL");
            assertThat(account.getAccountPhysicalCampusCode()).isEqualTo("BL");
        }

        @Test
        void acctIndirectCostRcvyTypeCd() {
            account.setAcctIndirectCostRcvyTypeCd("22");
            assertThat(account.getAcctIndirectCostRcvyTypeCd()).isEqualTo("22");
        }

        @Test
        void financialIcrSeriesIdentifier() {
            account.setFinancialIcrSeriesIdentifier("001");
            assertThat(account.getFinancialIcrSeriesIdentifier()).isEqualTo("001");
        }

        @Test
        void pendingAcctSufficientFundsIndicator() {
            account.setPendingAcctSufficientFundsIndicator(true);
            assertThat(account.isPendingAcctSufficientFundsIndicator()).isTrue();
        }

        @Test
        void extrnlFinEncumSufficntFndIndicator() {
            account.setExtrnlFinEncumSufficntFndIndicator(true);
            assertThat(account.isExtrnlFinEncumSufficntFndIndicator()).isTrue();
        }

        @Test
        void intrnlFinEncumSufficntFndIndicator() {
            account.setIntrnlFinEncumSufficntFndIndicator(true);
            assertThat(account.isIntrnlFinEncumSufficntFndIndicator()).isTrue();
        }

        @Test
        void finPreencumSufficientFundIndicator() {
            account.setFinPreencumSufficientFundIndicator(true);
            assertThat(account.isFinPreencumSufficientFundIndicator()).isTrue();
        }

        @Test
        void financialObjectivePrsctrlIndicator() {
            account.setFinancialObjectivePrsctrlIndicator(true);
            assertThat(account.isFinancialObjectivePrsctrlIndicator()).isTrue();
        }

        @Test
        void accountInFinancialProcessingIndicator() {
            account.setAccountInFinancialProcessingIndicator(true);
            assertThat(account.getAccountInFinancialProcessingIndicator()).isTrue();
        }

        @Test
        void contractsAndGrantsAccountResponsibilityId() {
            account.setContractsAndGrantsAccountResponsibilityId(5);
            assertThat(account.getContractsAndGrantsAccountResponsibilityId()).isEqualTo(5);
        }

        @Test
        void accountCountryCode() {
            account.setAccountCountryCode("US");
            assertThat(account.getAccountCountryCode()).isEqualTo("US");
        }

        @Test
        void defaultCountryCodeIsUS() {
            Account fresh = new Account();
            assertThat(fresh.getAccountCountryCode()).isEqualTo("US");
        }

        @Test
        void laborBenefitRateCategoryCode() {
            account.setLaborBenefitRateCategoryCode("LC");
            assertThat(account.getLaborBenefitRateCategoryCode()).isEqualTo("LC");
        }

        @Test
        void endowmentIncomeAcctFinCoaCd() {
            account.setEndowmentIncomeAcctFinCoaCd("BA");
            assertThat(account.getEndowmentIncomeAcctFinCoaCd()).isEqualTo("BA");
        }

        @Test
        void endowmentIncomeAccountNumber() {
            account.setEndowmentIncomeAccountNumber("1111111");
            assertThat(account.getEndowmentIncomeAccountNumber()).isEqualTo("1111111");
        }

        @Test
        void contractControlFinCoaCode() {
            account.setContractControlFinCoaCode("BL");
            assertThat(account.getContractControlFinCoaCode()).isEqualTo("BL");
        }

        @Test
        void contractControlAccountNumber() {
            account.setContractControlAccountNumber("2222222");
            assertThat(account.getContractControlAccountNumber()).isEqualTo("2222222");
        }

        @Test
        void incomeStreamFinancialCoaCode() {
            account.setIncomeStreamFinancialCoaCode("BL");
            assertThat(account.getIncomeStreamFinancialCoaCode()).isEqualTo("BL");
        }

        @Test
        void incomeStreamAccountNumber() {
            account.setIncomeStreamAccountNumber("3333333");
            assertThat(account.getIncomeStreamAccountNumber()).isEqualTo("3333333");
        }
    }

    @Nested
    @DisplayName("Reference object setters")
    class ReferenceObjectTests {

        @Test
        void chartOfAccounts() {
            Chart chart = new Chart();
            chart.setChartOfAccountsCode("BL");
            account.setChartOfAccounts(chart);
            assertThat(account.getChartOfAccounts().getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void organization() {
            Organization org = new Organization();
            account.setOrganization(org);
            assertThat(account.getOrganization()).isSameAs(org);
        }

        @Test
        void subFundGroup() {
            SubFundGroup sfg = new SubFundGroup();
            account.setSubFundGroup(sfg);
            assertThat(account.getSubFundGroup()).isSameAs(sfg);
        }

        @Test
        void reportsToAccount() {
            Account rta = new Account();
            account.setReportsToAccount(rta);
            assertThat(account.getReportsToAccount()).isSameAs(rta);
        }

        @Test
        void continuationAccount() {
            Account ca = new Account();
            account.setContinuationAccount(ca);
            assertThat(account.getContinuationAccount()).isSameAs(ca);
        }

        @Test
        void accountGuideline() {
            AccountGuideline ag = new AccountGuideline();
            account.setAccountGuideline(ag);
            assertThat(account.getAccountGuideline()).isSameAs(ag);
        }

        @Test
        void accountDescription() {
            AccountDescription ad = new AccountDescription();
            account.setAccountDescription(ad);
            assertThat(account.getAccountDescription()).isSameAs(ad);
        }

        @Test
        void accountType() {
            AccountType at = new AccountType();
            account.setAccountType(at);
            assertThat(account.getAccountType()).isSameAs(at);
        }

        @Test
        void indirectCostRecoveryAccounts() {
            List<IndirectCostRecoveryAccount> list = new java.util.ArrayList<>();
            list.add(new IndirectCostRecoveryAccount());
            account.setIndirectCostRecoveryAccounts(list);
            assertThat(account.getIndirectCostRecoveryAccounts()).hasSize(1);
        }

        @Test
        void accountFiscalOfficerSystemIdentifier() {
            account.setAccountFiscalOfficerSystemIdentifier("P001");
            assertThat(account.getAccountFiscalOfficerSystemIdentifier()).isEqualTo("P001");
        }

        @Test
        void accountsSupervisorySystemsIdentifier() {
            account.setAccountsSupervisorySystemsIdentifier("P002");
            assertThat(account.getAccountsSupervisorySystemsIdentifier()).isEqualTo("P002");
        }

        @Test
        void accountManagerSystemIdentifier() {
            account.setAccountManagerSystemIdentifier("P003");
            assertThat(account.getAccountManagerSystemIdentifier()).isEqualTo("P003");
        }
    }
}
