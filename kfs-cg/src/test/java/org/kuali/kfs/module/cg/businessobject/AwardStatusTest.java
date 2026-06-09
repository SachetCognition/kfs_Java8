package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AwardStatusTest extends KfsUnitTestBase {

    private AwardStatus awardStatus;

    @BeforeEach
    void setUp() {
        awardStatus = new AwardStatus();
    }

    @Test
    void testAwardStatusCode() {
        awardStatus.setAwardStatusCode("A");
        assertThat(awardStatus.getAwardStatusCode()).isEqualTo("A");
    }

    @Test
    void testAwardStatusDescription() {
        awardStatus.setAwardStatusDescription("Active");
        assertThat(awardStatus.getAwardStatusDescription()).isEqualTo("Active");
    }

    @Test
    void testActive() {
        awardStatus.setActive(true);
        assertThat(awardStatus.isActive()).isTrue();

        awardStatus.setActive(false);
        assertThat(awardStatus.isActive()).isFalse();
    }
}
