package org.kuali.kfs.module.ec.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class DuplicateCertificationsReportTest extends KfsUnitTestBase {

    private DuplicateCertificationsReport report;

    @BeforeEach
    void setUp() {
        report = new DuplicateCertificationsReport();
    }

    @Nested
    @DisplayName("Getter/Setter tests")
    class GetterSetterTests {

        @Test
        void shouldSetAndGetUniversityFiscalYear() {
            report.setUniversityFiscalYear(2024);
            assertThat(report.getUniversityFiscalYear()).isEqualTo(2024);
        }

        @Test
        void shouldSetAndGetEffortCertificationReportNumber() {
            report.setEffortCertificationReportNumber("A01");
            assertThat(report.getEffortCertificationReportNumber()).isEqualTo("A01");
        }

        @Test
        void shouldSetAndGetEmplid() {
            report.setEmplid("EMP001");
            assertThat(report.getEmplid()).isEqualTo("EMP001");
        }

        @Test
        void shouldSetAndGetOptions() {
            SystemOptions options = new SystemOptions();
            report.setOptions(options);
            assertThat(report.getOptions()).isSameAs(options);
        }

        @Test
        void shouldSetAndGetEffortCertificationReportDefinition() {
            EffortCertificationReportDefinition def = new EffortCertificationReportDefinition();
            report.setEffortCertificationReportDefinition(def);
            assertThat(report.getEffortCertificationReportDefinition()).isSameAs(def);
        }
    }

    @Nested
    @DisplayName("Default values")
    class DefaultValues {

        @Test
        void shouldHaveNullDefaultsOnNewInstance() {
            assertThat(report.getUniversityFiscalYear()).isNull();
            assertThat(report.getEffortCertificationReportNumber()).isNull();
            assertThat(report.getEmplid()).isNull();
            assertThat(report.getOptions()).isNull();
            assertThat(report.getEffortCertificationReportDefinition()).isNull();
        }
    }
}
