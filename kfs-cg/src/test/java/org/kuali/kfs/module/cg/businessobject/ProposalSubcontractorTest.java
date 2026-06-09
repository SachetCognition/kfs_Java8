package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProposalSubcontractorTest extends KfsUnitTestBase {

    private ProposalSubcontractor sub;

    @BeforeEach
    void setUp() {
        sub = new ProposalSubcontractor();
    }

    @Test
    void testProposalNumber() {
        sub.setProposalNumber(77777L);
        assertThat(sub.getProposalNumber()).isEqualTo(77777L);
    }

    @Test
    void testProposalSubcontractorNumber() {
        sub.setProposalSubcontractorNumber("PSC001");
        assertThat(sub.getProposalSubcontractorNumber()).isEqualTo("PSC001");
    }

    @Test
    void testSubcontractorNumber() {
        sub.setSubcontractorNumber("SC12345");
        assertThat(sub.getSubcontractorNumber()).isEqualTo("SC12345");
    }

    @Test
    void testProposalSubcontractorAmount() {
        KualiDecimal amount = new KualiDecimal(75000);
        sub.setProposalSubcontractorAmount(amount);
        assertThat(sub.getProposalSubcontractorAmount()).isEqualTo(amount);
    }

    @Test
    void testProposalSubcontractorDescription() {
        sub.setProposalSubcontractorDescription("Lab services contract");
        assertThat(sub.getProposalSubcontractorDescription()).isEqualTo("Lab services contract");
    }
}
