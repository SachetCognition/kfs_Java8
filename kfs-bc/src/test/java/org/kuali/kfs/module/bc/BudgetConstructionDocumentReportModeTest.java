package org.kuali.kfs.module.bc;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetConstructionDocumentReportModeTest extends KfsUnitTestBase {

    @Test
    void allModesArePresent() {
        assertThat(BudgetConstructionDocumentReportMode.values()).hasSize(6);
    }

    @Test
    void documentObjectDetailReport_hasCorrectProperties() {
        BudgetConstructionDocumentReportMode mode = BudgetConstructionDocumentReportMode.DOCUMENT_OBJECT_DETAIL_REPORT;
        assertThat(mode.getReportModeName()).isEqualTo("DocumentObjectDetailReport");
        assertThat(mode.getReportDesc()).isEqualTo("Account Object Detail Report");
        assertThat(mode.getJasperFileName()).isEqualTo("DocumentAccountObjectDetail");
        assertThat(mode.isDump()).isFalse();
    }

    @Test
    void documentFundingDetailReport_hasCorrectProperties() {
        BudgetConstructionDocumentReportMode mode = BudgetConstructionDocumentReportMode.DOCUMENT_FUNDING_DETAIL_REPORT;
        assertThat(mode.getReportModeName()).isEqualTo("DocumentFundingDetailReport");
        assertThat(mode.getReportDesc()).isEqualTo("Account Salary Detail Report");
        assertThat(mode.getJasperFileName()).isEqualTo("DocumentAccountFundingDetail");
        assertThat(mode.isDump()).isFalse();
    }

    @Test
    void documentMonthlyDetailReport_hasCorrectProperties() {
        BudgetConstructionDocumentReportMode mode = BudgetConstructionDocumentReportMode.DOCUMENT_MONTHLY_DETAIL_REPORT;
        assertThat(mode.getReportModeName()).isEqualTo("DocumentMonthlyDetailReport");
        assertThat(mode.getReportDesc()).isEqualTo("Account Monthly Detail Report");
        assertThat(mode.getJasperFileName()).isEqualTo("DocumentAccountMonthlyDetail");
        assertThat(mode.isDump()).isFalse();
    }

    @Test
    void documentAccountDump_isDumpMode() {
        BudgetConstructionDocumentReportMode mode = BudgetConstructionDocumentReportMode.DOCUMENT_ACCOUNT_DUMP;
        assertThat(mode.isDump()).isTrue();
        assertThat(mode.getJasperFileName()).isEmpty();
        assertThat(mode.getReportDesc()).isEqualTo("Budgeted Revenue/Expenditure Export");
    }

    @Test
    void documentFundingDump_isDumpMode() {
        BudgetConstructionDocumentReportMode mode = BudgetConstructionDocumentReportMode.DOCUMENT_FUNDING_DUMP;
        assertThat(mode.isDump()).isTrue();
        assertThat(mode.getReportDesc()).isEqualTo("Budgeted Salary Lines Export");
    }

    @Test
    void documentMonthlyDump_isDumpMode() {
        BudgetConstructionDocumentReportMode mode = BudgetConstructionDocumentReportMode.DOCUMENT_MONTHLY_DUMP;
        assertThat(mode.isDump()).isTrue();
        assertThat(mode.getReportDesc()).isEqualTo("Monthly Budget Export");
    }

    @Test
    void valueOf_returnsCorrectEnum() {
        assertThat(BudgetConstructionDocumentReportMode.valueOf("DOCUMENT_OBJECT_DETAIL_REPORT"))
                .isEqualTo(BudgetConstructionDocumentReportMode.DOCUMENT_OBJECT_DETAIL_REPORT);
    }
}
