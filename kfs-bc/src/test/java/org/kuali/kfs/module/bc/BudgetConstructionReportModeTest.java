package org.kuali.kfs.module.bc;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.BCConstants.Report.BuildMode;
import org.kuali.kfs.module.bc.BCConstants.Report.ReportSelectMode;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetConstructionReportModeTest extends KfsUnitTestBase {

    @Test
    void allReportModesArePresent() {
        assertThat(BudgetConstructionReportMode.values()).hasSize(17);
    }

    @Test
    void accountSummaryReport_hasCorrectProperties() {
        BudgetConstructionReportMode mode = BudgetConstructionReportMode.ACCOUNT_SUMMARY_REPORT;
        assertThat(mode.reportModeName).isEqualTo("AccountSummaryReport");
        assertThat(mode.reportBuildMode).isEqualTo(BuildMode.PBGL);
        assertThat(mode.reportSelectMode).isEqualTo(ReportSelectMode.SUBFUND);
        assertThat(mode.jasperFileName).isEqualTo("BudgetOrgAccountSummary");
        assertThat(mode.lockThreshold).isTrue();
        assertThat(mode.export).isFalse();
    }

    @Test
    void accountExport_isExportMode() {
        BudgetConstructionReportMode mode = BudgetConstructionReportMode.ACCOUNT_EXPORT;
        assertThat(mode.export).isTrue();
        assertThat(mode.jasperFileName).isEmpty();
        assertThat(mode.lockThreshold).isFalse();
    }

    @Test
    void monthlyExport_isExportMode() {
        BudgetConstructionReportMode mode = BudgetConstructionReportMode.MONTHLY_EXPORT;
        assertThat(mode.export).isTrue();
        assertThat(mode.reportBuildMode).isEqualTo(BuildMode.MONTH);
    }

    @Test
    void fundingExport_isExportMode() {
        BudgetConstructionReportMode mode = BudgetConstructionReportMode.FUNDING_EXPORT;
        assertThat(mode.export).isTrue();
        assertThat(mode.reportBuildMode).isEqualTo(BuildMode.BCAF);
    }

    @Test
    void getBudgetConstructionReportModeByName_findsKnownReport() {
        BudgetConstructionReportMode mode =
                BudgetConstructionReportMode.getBudgetConstructionReportModeByName("AccountSummaryReport");
        assertThat(mode).isEqualTo(BudgetConstructionReportMode.ACCOUNT_SUMMARY_REPORT);
    }

    @Test
    void getBudgetConstructionReportModeByName_returnsNullForUnknown() {
        BudgetConstructionReportMode mode =
                BudgetConstructionReportMode.getBudgetConstructionReportModeByName("NonExistentReport");
        assertThat(mode).isNull();
    }

    @Test
    void getBudgetConstructionReportModeByName_returnsNullForNull() {
        BudgetConstructionReportMode mode =
                BudgetConstructionReportMode.getBudgetConstructionReportModeByName(null);
        assertThat(mode).isNull();
    }

    @Test
    void salaryReports_useBcafBuildMode() {
        assertThat(BudgetConstructionReportMode.SALARY_SUMMARY_REPORT.reportBuildMode).isEqualTo(BuildMode.BCAF);
        assertThat(BudgetConstructionReportMode.SALARY_STATISTICS_REPORT.reportBuildMode).isEqualTo(BuildMode.BCAF);
    }

    @Test
    void reasonReports_useReasonSelectMode() {
        assertThat(BudgetConstructionReportMode.REASON_SUMMARY_REPORT.reportSelectMode).isEqualTo(ReportSelectMode.REASON);
        assertThat(BudgetConstructionReportMode.REASON_STATISTICS_REPORT.reportSelectMode).isEqualTo(ReportSelectMode.REASON);
    }

    @Test
    void synchronizationProblemsReport_usesAccountSelectMode() {
        assertThat(BudgetConstructionReportMode.SYNCHRONIZATION_PROBLEMS_REPORT.reportSelectMode)
                .isEqualTo(ReportSelectMode.ACCOUNT);
    }

    @Test
    void accountFundingDetailReport_usesObjectCodeSelectMode() {
        assertThat(BudgetConstructionReportMode.ACCOUNT_FUNDING_DETAIL_REPORT.reportSelectMode)
                .isEqualTo(ReportSelectMode.OBJECT_CODE);
    }
}
