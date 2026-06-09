package org.kuali.kfs.module.ec.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.sql.Date;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class EffortCertificationReportDefinitionTest extends KfsUnitTestBase {

    private EffortCertificationReportDefinition reportDef;

    @BeforeEach
    void setUp() {
        reportDef = new EffortCertificationReportDefinition();
    }

    @Test
    @DisplayName("Default constructor initializes empty positions collection")
    void defaultConstructorInitializesPositions() {
        assertThat(reportDef.getEffortCertificationReportPositions()).isNotNull().isEmpty();
    }

    @Nested
    @DisplayName("Getter/Setter tests")
    class GetterSetterTests {

        @Test
        void shouldSetAndGetUniversityFiscalYear() {
            reportDef.setUniversityFiscalYear(2024);
            assertThat(reportDef.getUniversityFiscalYear()).isEqualTo(2024);
        }

        @Test
        void shouldSetAndGetReportNumber() {
            reportDef.setEffortCertificationReportNumber("A01");
            assertThat(reportDef.getEffortCertificationReportNumber()).isEqualTo("A01");
        }

        @Test
        void shouldSetAndGetReportPeriodTitle() {
            reportDef.setEffortCertificationReportPeriodTitle("Spring 2024");
            assertThat(reportDef.getEffortCertificationReportPeriodTitle()).isEqualTo("Spring 2024");
        }

        @Test
        void shouldSetAndGetPeriodStatusCode() {
            reportDef.setEffortCertificationReportPeriodStatusCode("A");
            assertThat(reportDef.getEffortCertificationReportPeriodStatusCode()).isEqualTo("A");
        }

        @Test
        void shouldSetAndGetExpenseTransferFiscalYear() {
            reportDef.setExpenseTransferFiscalYear(2024);
            assertThat(reportDef.getExpenseTransferFiscalYear()).isEqualTo(2024);
        }

        @Test
        void shouldSetAndGetExpenseTransferFiscalPeriodCode() {
            reportDef.setExpenseTransferFiscalPeriodCode("01");
            assertThat(reportDef.getExpenseTransferFiscalPeriodCode()).isEqualTo("01");
        }

        @Test
        void shouldSetAndGetReportTypeCode() {
            reportDef.setEffortCertificationReportTypeCode("A");
            assertThat(reportDef.getEffortCertificationReportTypeCode()).isEqualTo("A");
        }

        @Test
        void shouldSetAndGetReturnDate() {
            Date returnDate = Date.valueOf("2024-06-30");
            reportDef.setEffortCertificationReportReturnDate(returnDate);
            assertThat(reportDef.getEffortCertificationReportReturnDate()).isEqualTo(returnDate);
        }

        @Test
        void shouldSetAndGetBeginFiscalYearAndPeriod() {
            reportDef.setEffortCertificationReportBeginFiscalYear(2023);
            reportDef.setEffortCertificationReportBeginPeriodCode("07");
            assertThat(reportDef.getEffortCertificationReportBeginFiscalYear()).isEqualTo(2023);
            assertThat(reportDef.getEffortCertificationReportBeginPeriodCode()).isEqualTo("07");
        }

        @Test
        void shouldSetAndGetEndFiscalYearAndPeriod() {
            reportDef.setEffortCertificationReportEndFiscalYear(2024);
            reportDef.setEffortCertificationReportEndPeriodCode("06");
            assertThat(reportDef.getEffortCertificationReportEndFiscalYear()).isEqualTo(2024);
            assertThat(reportDef.getEffortCertificationReportEndPeriodCode()).isEqualTo("06");
        }

        @Test
        void shouldSetAndGetActive() {
            reportDef.setActive(true);
            assertThat(reportDef.isActive()).isTrue();
            reportDef.setActive(false);
            assertThat(reportDef.isActive()).isFalse();
        }
    }

    @Nested
    @DisplayName("Report periods calculation")
    class ReportPeriodsTests {

        @Test
        void shouldComputeReportPeriodsWithinSameYear() {
            reportDef.setEffortCertificationReportBeginFiscalYear(2024);
            reportDef.setEffortCertificationReportBeginPeriodCode("01");
            reportDef.setEffortCertificationReportEndFiscalYear(2024);
            reportDef.setEffortCertificationReportEndPeriodCode("06");

            Map<Integer, Set<String>> periods = reportDef.getReportPeriods();
            assertThat(periods).containsKey(2024);
            assertThat(periods.get(2024)).contains("01", "02", "03", "04", "05", "06");
            assertThat(periods.get(2024)).doesNotContain("07", "08");
        }

        @Test
        void shouldComputeReportPeriodsAcrossYears() {
            reportDef.setEffortCertificationReportBeginFiscalYear(2023);
            reportDef.setEffortCertificationReportBeginPeriodCode("07");
            reportDef.setEffortCertificationReportEndFiscalYear(2024);
            reportDef.setEffortCertificationReportEndPeriodCode("06");

            Map<Integer, Set<String>> periods = reportDef.getReportPeriods();
            assertThat(periods).containsKeys(2023, 2024);
            assertThat(periods.get(2023)).contains("07", "08", "09", "10", "11", "12");
            assertThat(periods.get(2024)).contains("01", "02", "03", "04", "05", "06");
        }
    }

    @Nested
    @DisplayName("Reference objects")
    class ReferenceObjects {

        @Test
        void shouldSetAndGetPeriodStatusCode() {
            EffortCertificationPeriodStatusCode statusCode = new EffortCertificationPeriodStatusCode();
            statusCode.setEffortCertificationReportPeriodStatusCode("A");
            reportDef.setEffortCertificationPeriodStatusCode(statusCode);
            assertThat(reportDef.getEffortCertificationPeriodStatusCode()).isSameAs(statusCode);
        }

        @Test
        void shouldSetAndGetReportType() {
            EffortCertificationReportType reportType = new EffortCertificationReportType();
            reportType.setEffortCertificationReportTypeCode("A");
            reportDef.setEffortCertificationReportType(reportType);
            assertThat(reportDef.getEffortCertificationReportType()).isSameAs(reportType);
        }
    }

    @Test
    @DisplayName("buildKeyMap should produce map with year and report number")
    void buildKeyMapShouldContainExpectedKeys() {
        Map<String, String> keyMap = EffortCertificationReportDefinition.buildKeyMap(2024, "A01");
        assertThat(keyMap).containsEntry("universityFiscalYear", "2024");
        assertThat(keyMap).containsEntry("effortCertificationReportNumber", "A01");
    }
}
