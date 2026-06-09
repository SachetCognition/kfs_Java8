package org.kuali.kfs.module.purap.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ExpiredOrClosedAccountEntryTest extends KfsUnitTestBase {

    @Test
    void defaultConstructorCreatesNonNullAccounts() {
        ExpiredOrClosedAccountEntry entry = new ExpiredOrClosedAccountEntry();
        assertThat(entry.getOriginalAccount()).isNotNull();
        assertThat(entry.getReplacementAccount()).isNotNull();
    }

    @Test
    void setOriginalAccount() {
        ExpiredOrClosedAccountEntry entry = new ExpiredOrClosedAccountEntry();
        ExpiredOrClosedAccount original = new ExpiredOrClosedAccount("BL", "111", "SUB");
        entry.setOriginalAccount(original);
        assertThat(entry.getOriginalAccount().getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(entry.getOriginalAccount().getAccountNumber()).isEqualTo("111");
    }

    @Test
    void setReplacementAccount() {
        ExpiredOrClosedAccountEntry entry = new ExpiredOrClosedAccountEntry();
        ExpiredOrClosedAccount replacement = new ExpiredOrClosedAccount("UA", "222", "S2");
        entry.setReplacementAccount(replacement);
        assertThat(entry.getReplacementAccount().getChartOfAccountsCode()).isEqualTo("UA");
        assertThat(entry.getReplacementAccount().getAccountNumber()).isEqualTo("222");
    }
}
