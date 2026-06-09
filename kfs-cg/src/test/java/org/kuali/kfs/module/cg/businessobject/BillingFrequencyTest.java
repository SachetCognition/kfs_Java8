package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BillingFrequencyTest extends KfsUnitTestBase {

    private BillingFrequency billingFrequency;

    @BeforeEach
    void setUp() {
        billingFrequency = new BillingFrequency();
    }

    @Test
    void testFrequency() {
        billingFrequency.setFrequency("MNTH");
        assertThat(billingFrequency.getFrequency()).isEqualTo("MNTH");
    }

    @Test
    void testFrequencyDescription() {
        billingFrequency.setFrequencyDescription("Monthly");
        assertThat(billingFrequency.getFrequencyDescription()).isEqualTo("Monthly");
    }

    @Test
    void testGracePeriodDays() {
        billingFrequency.setGracePeriodDays(10);
        assertThat(billingFrequency.getGracePeriodDays()).isEqualTo(10);
    }

    @Test
    void testActive() {
        billingFrequency.setActive(true);
        assertThat(billingFrequency.isActive()).isTrue();

        billingFrequency.setActive(false);
        assertThat(billingFrequency.isActive()).isFalse();
    }
}
