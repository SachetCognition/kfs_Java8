package org.kuali.kfs.coa.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class FundGroupTest extends KfsUnitTestBase {

    private FundGroup fundGroup;

    @BeforeEach
    void setUp() {
        fundGroup = new FundGroup();
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void code() {
            fundGroup.setCode("CG");
            assertThat(fundGroup.getCode()).isEqualTo("CG");
        }

        @Test
        void name() {
            fundGroup.setName("Contracts and Grants");
            assertThat(fundGroup.getName()).isEqualTo("Contracts and Grants");
        }

        @Test
        void active() {
            fundGroup.setActive(true);
            assertThat(fundGroup.isActive()).isTrue();
            fundGroup.setActive(false);
            assertThat(fundGroup.isActive()).isFalse();
        }

        @Test
        void financialReportingSortCode() {
            fundGroup.setFinancialReportingSortCode("A");
            assertThat(fundGroup.getFinancialReportingSortCode()).isEqualTo("A");
        }

        @Test
        void fundGroupBudgetAdjustmentRestrictionLevelCode() {
            fundGroup.setFundGroupBudgetAdjustmentRestrictionLevelCode("A");
            assertThat(fundGroup.getFundGroupBudgetAdjustmentRestrictionLevelCode()).isEqualTo("A");
        }
    }
}
