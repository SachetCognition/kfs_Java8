package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AwardProjectDirectorTest extends KfsUnitTestBase {

    private AwardProjectDirector director;

    @BeforeEach
    void setUp() {
        director = new AwardProjectDirector();
    }

    @Test
    void testProposalNumber() {
        director.setProposalNumber(22222L);
        assertThat(director.getProposalNumber()).isEqualTo(22222L);
    }

    @Test
    void testPrincipalId() {
        director.setPrincipalId("director1");
        assertThat(director.getPrincipalId()).isEqualTo("director1");
    }

    @Test
    void testAwardPrimaryProjectDirectorIndicator() {
        director.setAwardPrimaryProjectDirectorIndicator(true);
        assertThat(director.isAwardPrimaryProjectDirectorIndicator()).isTrue();
        assertThat(director.isPrimary()).isTrue();

        director.setAwardPrimaryProjectDirectorIndicator(false);
        assertThat(director.isAwardPrimaryProjectDirectorIndicator()).isFalse();
        assertThat(director.isPrimary()).isFalse();
    }

    @Test
    void testActive() {
        director.setActive(true);
        assertThat(director.isActive()).isTrue();

        director.setActive(false);
        assertThat(director.isActive()).isFalse();
    }
}
