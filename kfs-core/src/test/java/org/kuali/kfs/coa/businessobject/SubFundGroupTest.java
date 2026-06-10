package org.kuali.kfs.coa.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class SubFundGroupTest extends KfsUnitTestBase {

    private SubFundGroup sfg;

    @BeforeEach
    void setUp() {
        sfg = new SubFundGroup();
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void subFundGroupCode() {
            sfg.setSubFundGroupCode("HIEDUA");
            assertThat(sfg.getSubFundGroupCode()).isEqualTo("HIEDUA");
        }

        @Test
        void subFundGroupDescription() {
            sfg.setSubFundGroupDescription("Higher Education");
            assertThat(sfg.getSubFundGroupDescription()).isEqualTo("Higher Education");
        }

        @Test
        void active() {
            sfg.setActive(true);
            assertThat(sfg.isActive()).isTrue();
            sfg.setActive(false);
            assertThat(sfg.isActive()).isFalse();
        }

        @Test
        void subFundGroupTypeCode() {
            sfg.setSubFundGroupTypeCode("CS");
            assertThat(sfg.getSubFundGroupTypeCode()).isEqualTo("CS");
        }

        @Test
        void financialReportingSortCode() {
            sfg.setFinancialReportingSortCode("A");
            assertThat(sfg.getFinancialReportingSortCode()).isEqualTo("A");
        }

        @Test
        void subFundGroupWagesIndicator() {
            sfg.setSubFundGroupWagesIndicator(true);
            assertThat(sfg.isSubFundGroupWagesIndicator()).isTrue();
        }

        @Test
        void fundGroupCode() {
            sfg.setFundGroupCode("CG");
            assertThat(sfg.getFundGroupCode()).isEqualTo("CG");
        }

        @Test
        void fundGroupBudgetAdjustmentRestrictionLevelCode() {
            sfg.setFundGroupBudgetAdjustmentRestrictionLevelCode("A");
            assertThat(sfg.getFundGroupBudgetAdjustmentRestrictionLevelCode()).isEqualTo("A");
        }

        @Test
        void accountRestrictedStatusCode() {
            sfg.setAccountRestrictedStatusCode("R");
            assertThat(sfg.getAccountRestrictedStatusCode()).isEqualTo("R");
        }
    }

    @Nested
    @DisplayName("Reference objects")
    class ReferenceObjectTests {

        @Test
        void fundGroup() {
            FundGroup fg = new FundGroup();
            sfg.setFundGroup(fg);
            assertThat(sfg.getFundGroup()).isSameAs(fg);
        }
    }
}
