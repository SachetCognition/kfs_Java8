package org.kuali.kfs.coa.businessobject;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AccountDelegateTest extends KfsUnitTestBase {

    private AccountDelegate delegate;

    @BeforeEach
    void setUp() {
        delegate = new AccountDelegate();
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void chartOfAccountsCode() {
            delegate.setChartOfAccountsCode("BL");
            assertThat(delegate.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void accountNumber() {
            delegate.setAccountNumber("1234567");
            assertThat(delegate.getAccountNumber()).isEqualTo("1234567");
        }

        @Test
        void financialDocumentTypeCode() {
            delegate.setFinancialDocumentTypeCode("DI");
            assertThat(delegate.getFinancialDocumentTypeCode()).isEqualTo("DI");
        }

        @Test
        void accountDelegateSystemId() {
            delegate.setAccountDelegateSystemId("P001");
            assertThat(delegate.getAccountDelegateSystemId()).isEqualTo("P001");
        }

        @Test
        void finDocApprovalFromThisAmt() {
            KualiDecimal amount = new KualiDecimal("100.00");
            delegate.setFinDocApprovalFromThisAmt(amount);
            assertThat(delegate.getFinDocApprovalFromThisAmt()).isEqualTo(amount);
        }

        @Test
        void finDocApprovalToThisAmount() {
            KualiDecimal amount = new KualiDecimal("5000.00");
            delegate.setFinDocApprovalToThisAmount(amount);
            assertThat(delegate.getFinDocApprovalToThisAmount()).isEqualTo(amount);
        }

        @Test
        void accountsDelegatePrmrtIndicator() {
            delegate.setAccountsDelegatePrmrtIndicator(true);
            assertThat(delegate.isAccountsDelegatePrmrtIndicator()).isTrue();
        }

        @Test
        void active() {
            delegate.setActive(true);
            assertThat(delegate.isActive()).isTrue();
            delegate.setActive(false);
            assertThat(delegate.isActive()).isFalse();
        }

        @Test
        void accountDelegateStartDate() {
            Date d = Date.valueOf("2025-01-01");
            delegate.setAccountDelegateStartDate(d);
            assertThat(delegate.getAccountDelegateStartDate()).isEqualTo(d);
        }
    }

    @Nested
    @DisplayName("Reference objects")
    class ReferenceObjectTests {

        @Test
        void chart() {
            Chart chart = new Chart();
            delegate.setChart(chart);
            assertThat(delegate.getChart()).isSameAs(chart);
        }

        @Test
        void account() {
            Account acct = new Account();
            delegate.setAccount(acct);
            assertThat(delegate.getAccount()).isSameAs(acct);
        }
    }
}
