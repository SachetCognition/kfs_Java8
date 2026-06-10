package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class MilestoneTest extends KfsUnitTestBase {

    private Milestone milestone;

    @BeforeEach
    void setUp() {
        milestone = new Milestone();
    }

    @Test
    void testMilestoneNumber() {
        milestone.setMilestoneNumber(1L);
        assertThat(milestone.getMilestoneNumber()).isEqualTo(1L);
    }

    @Test
    void testMilestoneIdentifier() {
        milestone.setMilestoneIdentifier(100L);
        assertThat(milestone.getMilestoneIdentifier()).isEqualTo(100L);
    }

    @Test
    void testProposalNumber() {
        milestone.setProposalNumber(5000L);
        assertThat(milestone.getProposalNumber()).isEqualTo(5000L);
    }

    @Test
    void testMilestoneDescription() {
        milestone.setMilestoneDescription("Phase 1 Complete");
        assertThat(milestone.getMilestoneDescription()).isEqualTo("Phase 1 Complete");
    }

    @Test
    void testMilestoneAmount() {
        KualiDecimal amount = new KualiDecimal(10000);
        milestone.setMilestoneAmount(amount);
        assertThat(milestone.getMilestoneAmount()).isEqualTo(amount);
    }

    @Test
    void testMilestoneExpectedCompletionDate() {
        Date date = Date.valueOf("2024-06-30");
        milestone.setMilestoneExpectedCompletionDate(date);
        assertThat(milestone.getMilestoneExpectedCompletionDate()).isEqualTo(date);
    }

    @Test
    void testMilestoneActualCompletionDate() {
        Date date = Date.valueOf("2024-07-15");
        milestone.setMilestoneActualCompletionDate(date);
        assertThat(milestone.getMilestoneActualCompletionDate()).isEqualTo(date);
    }

    @Test
    void testBilled() {
        milestone.setBilled(false);
        assertThat(milestone.isBilled()).isFalse();

        milestone.setBilled(true);
        assertThat(milestone.isBilled()).isTrue();
    }

    @Test
    void testActive() {
        milestone.setActive(true);
        assertThat(milestone.isActive()).isTrue();

        milestone.setActive(false);
        assertThat(milestone.isActive()).isFalse();
    }
}
