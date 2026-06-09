package org.kuali.kfs.module.ec.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class EffortCertificationReportEarnPaygroupTest extends KfsUnitTestBase {

    private EffortCertificationReportEarnPaygroup earnPaygroup;

    @BeforeEach
    void setUp() {
        earnPaygroup = new EffortCertificationReportEarnPaygroup();
    }

    @Test
    @DisplayName("Should set and get universityFiscalYear")
    void shouldSetAndGetFiscalYear() {
        earnPaygroup.setUniversityFiscalYear(2024);
        assertThat(earnPaygroup.getUniversityFiscalYear()).isEqualTo(2024);
    }

    @Test
    @DisplayName("Should set and get reportTypeCode")
    void shouldSetAndGetReportTypeCode() {
        earnPaygroup.setEffortCertificationReportTypeCode("A");
        assertThat(earnPaygroup.getEffortCertificationReportTypeCode()).isEqualTo("A");
    }

    @Test
    @DisplayName("Should set and get earnCode")
    void shouldSetAndGetEarnCode() {
        earnPaygroup.setEarnCode("RGS");
        assertThat(earnPaygroup.getEarnCode()).isEqualTo("RGS");
    }

    @Test
    @DisplayName("Should set and get payGroup")
    void shouldSetAndGetPayGroup() {
        earnPaygroup.setPayGroup("MN");
        assertThat(earnPaygroup.getPayGroup()).isEqualTo("MN");
    }

    @Test
    @DisplayName("Should set and get active flag")
    void shouldSetAndGetActive() {
        earnPaygroup.setActive(true);
        assertThat(earnPaygroup.isActive()).isTrue();
        earnPaygroup.setActive(false);
        assertThat(earnPaygroup.isActive()).isFalse();
    }

    @Test
    @DisplayName("Should set and get report type reference")
    void shouldSetAndGetReportType() {
        EffortCertificationReportType reportType = new EffortCertificationReportType();
        earnPaygroup.setEffortCertificationReportType(reportType);
        assertThat(earnPaygroup.getEffortCertificationReportType()).isSameAs(reportType);
    }

    @Test
    @DisplayName("Should set and get options reference")
    void shouldSetAndGetOptions() {
        earnPaygroup.setOptions(null);
        assertThat(earnPaygroup.getOptions()).isNull();
    }
}
