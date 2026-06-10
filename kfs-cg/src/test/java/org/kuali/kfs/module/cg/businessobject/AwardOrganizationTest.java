package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AwardOrganizationTest extends KfsUnitTestBase {

    private AwardOrganization awardOrganization;

    @BeforeEach
    void setUp() {
        awardOrganization = new AwardOrganization();
    }

    @Test
    void testProposalNumber() {
        awardOrganization.setProposalNumber(11111L);
        assertThat(awardOrganization.getProposalNumber()).isEqualTo(11111L);
    }

    @Test
    void testChartOfAccountsCode() {
        awardOrganization.setChartOfAccountsCode("BL");
        assertThat(awardOrganization.getChartOfAccountsCode()).isEqualTo("BL");
    }

    @Test
    void testOrganizationCode() {
        awardOrganization.setOrganizationCode("CHEM");
        assertThat(awardOrganization.getOrganizationCode()).isEqualTo("CHEM");
    }

    @Test
    void testPrimary() {
        awardOrganization.setAwardPrimaryOrganizationIndicator(true);
        assertThat(awardOrganization.isAwardPrimaryOrganizationIndicator()).isTrue();
        assertThat(awardOrganization.isPrimary()).isTrue();

        awardOrganization.setAwardPrimaryOrganizationIndicator(false);
        assertThat(awardOrganization.isAwardPrimaryOrganizationIndicator()).isFalse();
        assertThat(awardOrganization.isPrimary()).isFalse();
    }

    @Test
    void testActive() {
        awardOrganization.setActive(true);
        assertThat(awardOrganization.isActive()).isTrue();

        awardOrganization.setActive(false);
        assertThat(awardOrganization.isActive()).isFalse();
    }
}
