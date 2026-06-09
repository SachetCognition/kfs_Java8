package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class SubContractorTest extends KfsUnitTestBase {

    private SubContractor subContractor;

    @BeforeEach
    void setUp() {
        subContractor = new SubContractor();
    }

    @Test
    void testSubcontractorNumber() {
        subContractor.setSubcontractorNumber("SC001");
        assertThat(subContractor.getSubcontractorNumber()).isEqualTo("SC001");
    }

    @Test
    void testSubcontractorName() {
        subContractor.setSubcontractorName("ABC Labs Inc.");
        assertThat(subContractor.getSubcontractorName()).isEqualTo("ABC Labs Inc.");
    }

    @Test
    void testActive() {
        subContractor.setActive(true);
        assertThat(subContractor.isActive()).isTrue();

        subContractor.setActive(false);
        assertThat(subContractor.isActive()).isFalse();
    }
}
