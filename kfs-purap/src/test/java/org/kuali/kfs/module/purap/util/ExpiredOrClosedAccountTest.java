package org.kuali.kfs.module.purap.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ExpiredOrClosedAccountTest extends KfsUnitTestBase {

    @Test
    void defaultConstructorInitializesFieldsToNull() {
        ExpiredOrClosedAccount account = new ExpiredOrClosedAccount();
        assertThat(account.getChartOfAccountsCode()).isNull();
        assertThat(account.getAccountNumber()).isNull();
        assertThat(account.getSubAccountNumber()).isNull();
        assertThat(account.isClosedIndicator()).isFalse();
        assertThat(account.isExpiredIndicator()).isFalse();
        assertThat(account.isContinuationAccountMissing()).isFalse();
    }

    @Test
    void parameterizedConstructorSetsFields() {
        ExpiredOrClosedAccount account = new ExpiredOrClosedAccount("BL", "1234567", "SUB1");
        assertThat(account.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(account.getAccountNumber()).isEqualTo("1234567");
        assertThat(account.getSubAccountNumber()).isEqualTo("SUB1");
    }

    @Test
    void getAccountStringWithAllFields() {
        ExpiredOrClosedAccount account = new ExpiredOrClosedAccount("BL", "1234567", "SUB1");
        assertThat(account.getAccountString()).isEqualTo("BL-1234567-SUB1");
    }

    @Test
    void getAccountStringWithNullChart() {
        ExpiredOrClosedAccount account = new ExpiredOrClosedAccount();
        account.setAccountNumber("1234567");
        account.setSubAccountNumber("SUB1");
        assertThat(account.getAccountString()).isEqualTo("-1234567-SUB1");
    }

    @Test
    void getAccountStringWithNullAccountNumber() {
        ExpiredOrClosedAccount account = new ExpiredOrClosedAccount();
        account.setChartOfAccountsCode("BL");
        account.setSubAccountNumber("SUB1");
        assertThat(account.getAccountString()).isEqualTo("BL-SUB1");
    }

    @Test
    void getAccountStringWithNullSubAccount() {
        ExpiredOrClosedAccount account = new ExpiredOrClosedAccount();
        account.setChartOfAccountsCode("BL");
        account.setAccountNumber("1234567");
        assertThat(account.getAccountString()).isEqualTo("BL-1234567");
    }

    @Test
    void getAccountStringAllNull() {
        ExpiredOrClosedAccount account = new ExpiredOrClosedAccount();
        assertThat(account.getAccountString()).isEmpty();
    }

    @Test
    void closedIndicatorSetterGetter() {
        ExpiredOrClosedAccount account = new ExpiredOrClosedAccount();
        account.setClosedIndicator(true);
        assertThat(account.isClosedIndicator()).isTrue();
    }

    @Test
    void expiredIndicatorSetterGetter() {
        ExpiredOrClosedAccount account = new ExpiredOrClosedAccount();
        account.setExpiredIndicator(true);
        assertThat(account.isExpiredIndicator()).isTrue();
    }

    @Test
    void continuationAccountMissingSetterGetter() {
        ExpiredOrClosedAccount account = new ExpiredOrClosedAccount();
        account.setContinuationAccountMissing(true);
        assertThat(account.isContinuationAccountMissing()).isTrue();
    }
}
