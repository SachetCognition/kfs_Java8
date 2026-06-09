package org.kuali.kfs.module.ec.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class EffortCertificationReportTypeTest extends KfsUnitTestBase {

    private EffortCertificationReportType reportType;

    @BeforeEach
    void setUp() {
        reportType = new EffortCertificationReportType();
    }

    @Test
    @DisplayName("Should set and get reportTypeCode")
    void shouldSetAndGetReportTypeCode() {
        reportType.setEffortCertificationReportTypeCode("A");
        assertThat(reportType.getEffortCertificationReportTypeCode()).isEqualTo("A");
    }

    @Test
    @DisplayName("Should set and get reportDescription")
    void shouldSetAndGetReportDescription() {
        reportType.setEffortCertificationReportDescription("Annual Certification");
        assertThat(reportType.getEffortCertificationReportDescription()).isEqualTo("Annual Certification");
    }

    @Test
    @DisplayName("Should set and get active flag")
    void shouldSetAndGetActive() {
        reportType.setActive(true);
        assertThat(reportType.isActive()).isTrue();
        reportType.setActive(false);
        assertThat(reportType.isActive()).isFalse();
    }

    @Test
    @DisplayName("toStringMapper should contain report type code")
    void toStringMapperShouldContainTypeCode() {
        reportType.setEffortCertificationReportTypeCode("A");
        assertThat(reportType.toStringMapper_RICE20_REFACTORME()).containsKey("effortCertificationLaborReportTypeCode");
        assertThat(reportType.toStringMapper_RICE20_REFACTORME()).containsValue("A");
    }
}
