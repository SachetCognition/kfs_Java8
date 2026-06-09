package org.kuali.kfs.module.ec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class EffortPropertyConstantsTest extends KfsUnitTestBase {

    @Test
    @DisplayName("Property constants should have correct values")
    void propertyConstantsShouldHaveCorrectValues() {
        assertThat(EffortPropertyConstants.EFFORT_CERTIFICATION_REPORT_NUMBER)
                .isEqualTo("effortCertificationReportNumber");
        assertThat(EffortPropertyConstants.EFFORT_CERTIFICATION_PAYROLL_AMOUNT)
                .isEqualTo("effortCertificationPayrollAmount");
        assertThat(EffortPropertyConstants.EFFORT_CERTIFICATION_BUILD_NUMBER)
                .isEqualTo("effortCertificationBuildNumber");
    }

    @Test
    @DisplayName("Source field constants should not be blank")
    void sourceFieldConstants() {
        assertThat(EffortPropertyConstants.SOURCE_CHART_OF_ACCOUNTS_CODE).isNotBlank();
        assertThat(EffortPropertyConstants.SOURCE_ACCOUNT_NUMBER).isNotBlank();
    }

    @Test
    @DisplayName("Report definition property constants should not be blank")
    void reportDefinitionPropertyConstants() {
        assertThat(EffortPropertyConstants.EFFORT_CERTIFICATION_REPORT_PERIOD_STATUS_CODE).isNotBlank();
        assertThat(EffortPropertyConstants.EFFORT_CERTIFICATION_REPORT_TYPE_CODE).isNotBlank();
    }

    @Test
    @DisplayName("NEW_LINE_INDICATOR should be defined")
    void newLineIndicator() {
        assertThat(EffortPropertyConstants.NEW_LINE_INDICATOR).isNotBlank();
    }
}
