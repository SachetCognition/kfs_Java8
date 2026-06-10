package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class AwardAccountTest extends KfsUnitTestBase {

    private AwardAccount awardAccount;

    @BeforeEach
    void setUp() {
        awardAccount = new AwardAccount();
    }

    @Test
    void testProposalNumber() {
        awardAccount.setProposalNumber(12345L);
        assertThat(awardAccount.getProposalNumber()).isEqualTo(12345L);
    }

    @Test
    void testChartOfAccountsCode() {
        awardAccount.setChartOfAccountsCode("UA");
        assertThat(awardAccount.getChartOfAccountsCode()).isEqualTo("UA");
    }

    @Test
    void testAccountNumber() {
        awardAccount.setAccountNumber("1234567");
        assertThat(awardAccount.getAccountNumber()).isEqualTo("1234567");
    }

    @Test
    void testPrincipalId() {
        awardAccount.setPrincipalId("user123");
        assertThat(awardAccount.getPrincipalId()).isEqualTo("user123");
    }

    @Test
    void testActive() {
        awardAccount.setActive(true);
        assertThat(awardAccount.isActive()).isTrue();

        awardAccount.setActive(false);
        assertThat(awardAccount.isActive()).isFalse();
    }

    @Test
    void testCurrentLastBilledDate() {
        Date date = Date.valueOf("2024-06-01");
        awardAccount.setCurrentLastBilledDate(date);
        assertThat(awardAccount.getCurrentLastBilledDate()).isEqualTo(date);
    }

    @Test
    void testFinalBilledIndicator() {
        awardAccount.setFinalBilledIndicator(true);
        assertThat(awardAccount.isFinalBilledIndicator()).isTrue();

        awardAccount.setFinalBilledIndicator(false);
        assertThat(awardAccount.isFinalBilledIndicator()).isFalse();
    }
}
