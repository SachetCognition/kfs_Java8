package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class CFDATest extends KfsUnitTestBase {

    private CFDA cfda;

    @BeforeEach
    void setUp() {
        cfda = new CFDA();
    }

    @Test
    void testCfdaNumber() {
        cfda.setCfdaNumber("10.001");
        assertThat(cfda.getCfdaNumber()).isEqualTo("10.001");
    }

    @Test
    void testCfdaProgramTitleName() {
        cfda.setCfdaProgramTitleName("Agricultural Research");
        assertThat(cfda.getCfdaProgramTitleName()).isEqualTo("Agricultural Research");
    }

    @Test
    void testCfdaMaintenanceTypeId() {
        cfda.setCfdaMaintenanceTypeId("AUTOMATIC");
        assertThat(cfda.getCfdaMaintenanceTypeId()).isEqualTo("AUTOMATIC");
    }

    @Test
    void testActive() {
        cfda.setActive(true);
        assertThat(cfda.isActive()).isTrue();

        cfda.setActive(false);
        assertThat(cfda.isActive()).isFalse();
    }

    @Test
    void testCfdaNumberNull() {
        assertThat(cfda.getCfdaNumber()).isNull();
    }

    @Test
    void testCfdaProgramTitleNameNull() {
        assertThat(cfda.getCfdaProgramTitleName()).isNull();
    }
}
