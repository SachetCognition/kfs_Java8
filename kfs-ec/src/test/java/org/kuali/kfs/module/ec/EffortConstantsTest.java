package org.kuali.kfs.module.ec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class EffortConstantsTest extends KfsUnitTestBase {

    @Test
    @DisplayName("EFFORT_NAMESPACE_CODE should be KFS-EC")
    void effortNamespaceCode() {
        assertThat(EffortConstants.EFFORT_NAMESPACE_CODE).isEqualTo("KFS-EC");
    }

    @Test
    @DisplayName("DASH constants should have expected values")
    void dashConstants() {
        assertThat(EffortConstants.DASH_ACCOUNT_NUMBER).isNotNull();
        assertThat(EffortConstants.DASH_CHART_OF_ACCOUNTS_CODE).isNotNull();
        assertThat(EffortConstants.DASH_POSITION_NUMBER).isNotNull();
    }

    @Test
    @DisplayName("LABOR_OBJECT_SALARY_CODE should not be blank")
    void laborObjectSalaryCode() {
        assertThat(EffortConstants.LABOR_OBJECT_SALARY_CODE).isNotBlank();
    }

    @Test
    @DisplayName("ELIGIBLE_BALANCE_TYPES_FOR_EFFORT_REPORT should not be empty")
    void eligibleBalanceTypes() {
        assertThat(EffortConstants.ELIGIBLE_BALANCE_TYPES_FOR_EFFORT_REPORT).isNotEmpty();
    }

    @Test
    @DisplayName("ELIGIBLE_EXPENSE_SUB_ACCOUNT_TYPE_CODES should not be empty")
    void eligibleExpenseSubAccountTypeCodes() {
        assertThat(EffortConstants.ELIGIBLE_EXPENSE_SUB_ACCOUNT_TYPE_CODES).isNotEmpty();
    }

    @Test
    @DisplayName("ELIGIBLE_COST_SHARE_SUB_ACCOUNT_TYPE_CODES should not be empty")
    void eligibleCostShareSubAccountTypeCodes() {
        assertThat(EffortConstants.ELIGIBLE_COST_SHARE_SUB_ACCOUNT_TYPE_CODES).isNotEmpty();
    }

    @Nested
    @DisplayName("DETAIL_LINES_CONSOLIDATION_FILEDS")
    class ConsolidationFields {

        @Test
        void shouldContainExpectedFields() {
            assertThat(EffortConstants.DETAIL_LINES_CONSOLIDATION_FILEDS)
                    .contains(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE)
                    .contains(KFSPropertyConstants.ACCOUNT_NUMBER)
                    .contains(KFSPropertyConstants.SUB_ACCOUNT_NUMBER);
        }

        @Test
        void shouldHaveThreeElements() {
            assertThat(EffortConstants.DETAIL_LINES_CONSOLIDATION_FILEDS).hasSize(3);
        }
    }

    @Nested
    @DisplayName("DETAIL_LINES_GROUPING_FILEDS")
    class GroupingFields {

        @Test
        void shouldContainExpectedFields() {
            assertThat(EffortConstants.DETAIL_LINES_GROUPING_FILEDS)
                    .contains(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE)
                    .contains(KFSPropertyConstants.ACCOUNT_NUMBER)
                    .contains(KFSPropertyConstants.SUB_ACCOUNT_NUMBER)
                    .contains(EffortPropertyConstants.NEW_LINE_INDICATOR);
        }

        @Test
        void shouldHaveFourElements() {
            assertThat(EffortConstants.DETAIL_LINES_GROUPING_FILEDS).hasSize(4);
        }
    }

    @Nested
    @DisplayName("Inner classes")
    class InnerClasses {

        @Test
        void extractProcessConstants() {
            assertThat(EffortConstants.ExtractProcess.EXPENSE_OBJECT_TYPE).isNotBlank();
        }

        @Test
        void systemParametersConstants() {
            assertThat(EffortConstants.SystemParameters.RUN_IND).isNotBlank();
        }

        @Test
        void effortCertificationEditMode() {
            assertThat(EffortConstants.EffortCertificationEditMode.DETAIL_TAB_ENTRY).isEqualTo("detailTabEntry");
            assertThat(EffortConstants.EffortCertificationEditMode.SUMMARY_TAB_ENTRY).isEqualTo("summaryTabEntry");
        }

        @Test
        void effortDocumentTypes() {
            assertThat(EffortConstants.EffortDocumentTypes.EFFORT_CERTIFICATION_DOCUMENT).isEqualTo("ECD");
        }

        @Test
        void balanceInquiries() {
            assertThat(EffortConstants.BalanceInquiries.BALANCE_TYPE_AC_AND_A21).isEqualTo("AC&A2");
        }
    }
}
