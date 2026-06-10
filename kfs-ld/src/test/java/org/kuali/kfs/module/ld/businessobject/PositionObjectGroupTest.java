package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class PositionObjectGroupTest extends KfsUnitTestBase {

    private PositionObjectGroup positionObjectGroup;

    @BeforeEach
    void setUp() {
        positionObjectGroup = new PositionObjectGroup();
    }

    @Test
    void testSetAndGetPositionObjectGroupCode() {
        positionObjectGroup.setPositionObjectGroupCode("AC");
        assertThat(positionObjectGroup.getPositionObjectGroupCode()).isEqualTo("AC");
    }

    @Test
    void testSetAndGetPositionObjectGroupName() {
        positionObjectGroup.setPositionObjectGroupName("Academic Staff");
        assertThat(positionObjectGroup.getPositionObjectGroupName()).isEqualTo("Academic Staff");
    }

    @Test
    void testSetAndGetActive() {
        positionObjectGroup.setActive(true);
        assertThat(positionObjectGroup.isActive()).isTrue();
        positionObjectGroup.setActive(false);
        assertThat(positionObjectGroup.isActive()).isFalse();
    }

    @Test
    void testDefaultValuesAreNull() {
        assertThat(positionObjectGroup.getPositionObjectGroupCode()).isNull();
        assertThat(positionObjectGroup.getPositionObjectGroupName()).isNull();
    }
}
