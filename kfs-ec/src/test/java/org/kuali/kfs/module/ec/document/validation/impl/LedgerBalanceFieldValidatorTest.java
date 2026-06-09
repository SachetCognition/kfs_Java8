package org.kuali.kfs.module.ec.document.validation.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.integration.ld.LaborLedgerBalance;
import org.kuali.kfs.sys.Message;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LedgerBalanceFieldValidatorTest extends KfsUnitTestBase {

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
    }
}
