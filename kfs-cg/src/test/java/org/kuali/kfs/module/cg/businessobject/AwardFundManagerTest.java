package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AwardFundManagerTest extends KfsUnitTestBase {

    private AwardFundManager fundManager;

    @BeforeEach
    void setUp() {
        fundManager = new AwardFundManager();
    }

    @Test
    void testProposalNumber() {
        fundManager.setProposalNumber(33333L);
        assertThat(fundManager.getProposalNumber()).isEqualTo(33333L);
    }

    @Test
    void testPrincipalId() {
        fundManager.setPrincipalId("fundmgr1");
        assertThat(fundManager.getPrincipalId()).isEqualTo("fundmgr1");
    }

    @Test
    void testPrimaryFundManagerIndicator() {
        fundManager.setPrimaryFundManagerIndicator(true);
        assertThat(fundManager.isPrimaryFundManagerIndicator()).isTrue();
        assertThat(fundManager.isPrimary()).isTrue();

        fundManager.setPrimaryFundManagerIndicator(false);
        assertThat(fundManager.isPrimaryFundManagerIndicator()).isFalse();
        assertThat(fundManager.isPrimary()).isFalse();
    }
}
