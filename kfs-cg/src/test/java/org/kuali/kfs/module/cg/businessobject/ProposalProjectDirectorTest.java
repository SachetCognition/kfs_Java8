package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ProposalProjectDirectorTest extends KfsUnitTestBase {

    private ProposalProjectDirector director;

    @BeforeEach
    void setUp() {
        director = new ProposalProjectDirector();
    }

    @Test
    void testPrincipalId() {
        director.setPrincipalId("director1");
        assertThat(director.getPrincipalId()).isEqualTo("director1");
    }

    @Test
    void testProposalNumber() {
        director.setProposalNumber(66666L);
        assertThat(director.getProposalNumber()).isEqualTo(66666L);
    }

    @Test
    void testProposalPrimaryProjectDirectorIndicator() {
        director.setProposalPrimaryProjectDirectorIndicator(true);
        assertThat(director.isProposalPrimaryProjectDirectorIndicator()).isTrue();
        assertThat(director.isPrimary()).isTrue();

        director.setProposalPrimaryProjectDirectorIndicator(false);
        assertThat(director.isProposalPrimaryProjectDirectorIndicator()).isFalse();
        assertThat(director.isPrimary()).isFalse();
    }

    @Test
    void testProposalProjectDirectorProjectTitle() {
        director.setProposalProjectDirectorProjectTitle("Principal Investigator");
        assertThat(director.getProposalProjectDirectorProjectTitle()).isEqualTo("Principal Investigator");
    }

    @Test
    void testActive() {
        assertThat(director.isActive()).isTrue(); // default is true

        director.setActive(false);
        assertThat(director.isActive()).isFalse();
    }
}
