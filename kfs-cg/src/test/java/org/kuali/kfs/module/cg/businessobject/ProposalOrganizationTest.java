package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ProposalOrganizationTest extends KfsUnitTestBase {

    private ProposalOrganization proposalOrganization;

    @BeforeEach
    void setUp() {
        proposalOrganization = new ProposalOrganization();
    }

    @Test
    void testChartOfAccountsCode() {
        proposalOrganization.setChartOfAccountsCode("UA");
        assertThat(proposalOrganization.getChartOfAccountsCode()).isEqualTo("UA");
    }

    @Test
    void testOrganizationCode() {
        proposalOrganization.setOrganizationCode("CHEM");
        assertThat(proposalOrganization.getOrganizationCode()).isEqualTo("CHEM");
    }

    @Test
    void testProposalNumber() {
        proposalOrganization.setProposalNumber(55555L);
        assertThat(proposalOrganization.getProposalNumber()).isEqualTo(55555L);
    }

    @Test
    void testProposalPrimaryOrganizationIndicator() {
        proposalOrganization.setProposalPrimaryOrganizationIndicator(true);
        assertThat(proposalOrganization.isProposalPrimaryOrganizationIndicator()).isTrue();
        assertThat(proposalOrganization.isPrimary()).isTrue();

        proposalOrganization.setProposalPrimaryOrganizationIndicator(false);
        assertThat(proposalOrganization.isProposalPrimaryOrganizationIndicator()).isFalse();
        assertThat(proposalOrganization.isPrimary()).isFalse();
    }

    @Test
    void testActive() {
        assertThat(proposalOrganization.isActive()).isTrue(); // default is true

        proposalOrganization.setActive(false);
        assertThat(proposalOrganization.isActive()).isFalse();
    }
}
