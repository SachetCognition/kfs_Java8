package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AwardSubcontractorTest extends KfsUnitTestBase {

    private AwardSubcontractor awardSubcontractor;

    @BeforeEach
    void setUp() {
        awardSubcontractor = new AwardSubcontractor();
    }

    @Test
    void testProposalNumber() {
        awardSubcontractor.setProposalNumber(44444L);
        assertThat(awardSubcontractor.getProposalNumber()).isEqualTo(44444L);
    }

    @Test
    void testAwardSubcontractorAmendmentNumber() {
        awardSubcontractor.setAwardSubcontractorAmendmentNumber("AMD01");
        assertThat(awardSubcontractor.getAwardSubcontractorAmendmentNumber()).isEqualTo("AMD01");
    }

    @Test
    void testAwardSubcontractorNumber() {
        awardSubcontractor.setAwardSubcontractorNumber("SUB001");
        assertThat(awardSubcontractor.getAwardSubcontractorNumber()).isEqualTo("SUB001");
    }

    @Test
    void testSubcontractorNumber() {
        awardSubcontractor.setSubcontractorNumber("SC12345");
        assertThat(awardSubcontractor.getSubcontractorNumber()).isEqualTo("SC12345");
    }

    @Test
    void testSubcontractorAmount() {
        KualiDecimal amount = new KualiDecimal(15000);
        awardSubcontractor.setSubcontractorAmount(amount);
        assertThat(awardSubcontractor.getSubcontractorAmount()).isEqualTo(amount);
    }

    @Test
    void testAwardSubcontractorDescription() {
        awardSubcontractor.setAwardSubcontractorDescription("Lab equipment testing");
        assertThat(awardSubcontractor.getAwardSubcontractorDescription()).isEqualTo("Lab equipment testing");
    }
}
