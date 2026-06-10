package org.kuali.kfs.module.ec.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class EffortCertificationReportPositionTest extends KfsUnitTestBase {

    private EffortCertificationReportPosition reportPosition;

    @BeforeEach
    void setUp() {
        reportPosition = new EffortCertificationReportPosition();
    }

    @Test
    @DisplayName("Should set and get universityFiscalYear")
    void shouldSetAndGetFiscalYear() {
        reportPosition.setUniversityFiscalYear(2024);
        assertThat(reportPosition.getUniversityFiscalYear()).isEqualTo(2024);
    }

    @Test
    @DisplayName("Should set and get reportNumber")
    void shouldSetAndGetReportNumber() {
        reportPosition.setEffortCertificationReportNumber("A01");
        assertThat(reportPosition.getEffortCertificationReportNumber()).isEqualTo("A01");
    }

    @Test
    @DisplayName("Should set and get positionObjectGroupCode")
    void shouldSetAndGetPositionObjectGroupCode() {
        reportPosition.setEffortCertificationReportPositionObjectGroupCode("AC");
        assertThat(reportPosition.getEffortCertificationReportPositionObjectGroupCode()).isEqualTo("AC");
    }

    @Test
    @DisplayName("Should set and get active flag")
    void shouldSetAndGetActive() {
        reportPosition.setActive(true);
        assertThat(reportPosition.isActive()).isTrue();
        reportPosition.setActive(false);
        assertThat(reportPosition.isActive()).isFalse();
    }

    @Test
    @DisplayName("Should set and get report definition reference")
    void shouldSetAndGetReportDefinition() {
        EffortCertificationReportDefinition reportDef = new EffortCertificationReportDefinition();
        reportPosition.setEffortCertificationReportDefinition(reportDef);
        assertThat(reportPosition.getEffortCertificationReportDefinition()).isSameAs(reportDef);
    }

    @Test
    @DisplayName("Should set and get options reference")
    void shouldSetAndGetOptions() {
        reportPosition.setOptions(null);
        assertThat(reportPosition.getOptions()).isNull();
    }
}
