package org.kuali.kfs.module.ec.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Organization;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class OutstandingCertificationsByReportTest extends KfsUnitTestBase {

    private OutstandingCertificationsByReport report;

    @BeforeEach
    void setUp() {
        report = new OutstandingCertificationsByReport();
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
        void shouldSetAndGetChartOfAccountsCode() {
            report.setChartOfAccountsCode("BL");
            assertThat(report.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void shouldSetAndGetOrganizationCode() {
            report.setOrganizationCode("ORG1");
            assertThat(report.getOrganizationCode()).isEqualTo("ORG1");
        }

        @Test
        void shouldSetAndGetOutstandingCertificationCount() {
            report.setOutstandingCertificationCount(42);
            assertThat(report.getOutstandingCertificationCount()).isEqualTo(42);
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

        @Test
        void shouldSetAndGetOrganization() {
            Organization org = new Organization();
            report.setOrganization(org);
            assertThat(report.getOrganization()).isSameAs(org);
        }
    }

    @Nested
    @DisplayName("Default values")
    class DefaultValues {

        @Test
        void shouldHaveNullDefaultsOnNewInstance() {
            assertThat(report.getUniversityFiscalYear()).isNull();
            assertThat(report.getEffortCertificationReportNumber()).isNull();
            assertThat(report.getChartOfAccountsCode()).isNull();
            assertThat(report.getOrganizationCode()).isNull();
            assertThat(report.getOutstandingCertificationCount()).isNull();
        }
    }
}
