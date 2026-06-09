package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BenefitsTypeTest extends KfsUnitTestBase {

    private BenefitsType benefitsType;

    @BeforeEach
    void setUp() {
        benefitsType = new BenefitsType();
    }

    @Test
    void testSetAndGetPositionBenefitTypeCode() {
        benefitsType.setPositionBenefitTypeCode("HI");
        assertThat(benefitsType.getPositionBenefitTypeCode()).isEqualTo("HI");
    }

    @Test
    void testSetAndGetPositionBenefitTypeDescription() {
        benefitsType.setPositionBenefitTypeDescription("Health Insurance");
        assertThat(benefitsType.getPositionBenefitTypeDescription()).isEqualTo("Health Insurance");
    }

    @Test
    void testSetAndGetActive() {
        benefitsType.setActive(true);
        assertThat(benefitsType.isActive()).isTrue();
        benefitsType.setActive(false);
        assertThat(benefitsType.isActive()).isFalse();
    }

    @Test
    void testSetAndGetPositionBenefitRetirementIndicator() {
        benefitsType.setPositionBenefitRetirementIndicator(true);
        assertThat(benefitsType.isPositionBenefitRetirementIndicator()).isTrue();
    }

    @Test
    void testDefaultValuesAreNull() {
        assertThat(benefitsType.getPositionBenefitTypeCode()).isNull();
        assertThat(benefitsType.getPositionBenefitTypeDescription()).isNull();
    }
}
