package org.kuali.kfs.coa.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class SubAccountTest extends KfsUnitTestBase {

    private SubAccount subAccount;

    @BeforeEach
    void setUp() {
        subAccount = new SubAccount();
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void chartOfAccountsCode() {
            subAccount.setChartOfAccountsCode("BL");
            assertThat(subAccount.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void accountNumber() {
            subAccount.setAccountNumber("1234567");
            assertThat(subAccount.getAccountNumber()).isEqualTo("1234567");
        }

        @Test
        void subAccountNumber() {
            subAccount.setSubAccountNumber("001");
            assertThat(subAccount.getSubAccountNumber()).isEqualTo("001");
        }

        @Test
        void subAccountName() {
            subAccount.setSubAccountName("Test Sub Account");
            assertThat(subAccount.getSubAccountName()).isEqualTo("Test Sub Account");
        }

        @Test
        void active() {
            subAccount.setActive(true);
            assertThat(subAccount.isActive()).isTrue();

            subAccount.setActive(false);
            assertThat(subAccount.isActive()).isFalse();
        }

        @Test
        void financialReportChartCode() {
            subAccount.setFinancialReportChartCode("UA");
            assertThat(subAccount.getFinancialReportChartCode()).isEqualTo("UA");
        }

        @Test
        void finReportOrganizationCode() {
            subAccount.setFinReportOrganizationCode("ORGX");
            assertThat(subAccount.getFinReportOrganizationCode()).isEqualTo("ORGX");
        }

        @Test
        void financialReportingCode() {
            subAccount.setFinancialReportingCode("R001");
            assertThat(subAccount.getFinancialReportingCode()).isEqualTo("R001");
        }
    }

    @Nested
    @DisplayName("Reference objects")
    class ReferenceObjectTests {

        @Test
        void account() {
            Account acct = new Account();
            acct.setAccountNumber("7654321");
            subAccount.setAccount(acct);
            assertThat(subAccount.getAccount().getAccountNumber()).isEqualTo("7654321");
        }

        @Test
        void a21SubAccount() {
            A21SubAccount a21 = new A21SubAccount();
            subAccount.setA21SubAccount(a21);
            assertThat(subAccount.getA21SubAccount()).isSameAs(a21);
        }

        @Test
        void chart() {
            Chart chart = new Chart();
            subAccount.setChart(chart);
            assertThat(subAccount.getChart()).isSameAs(chart);
        }

        @Test
        void reportingCode() {
            ReportingCode rc = new ReportingCode();
            subAccount.setReportingCode(rc);
            assertThat(subAccount.getReportingCode()).isSameAs(rc);
        }
    }
}
