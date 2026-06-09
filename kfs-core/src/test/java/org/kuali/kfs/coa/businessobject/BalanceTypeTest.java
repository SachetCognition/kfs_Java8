package org.kuali.kfs.coa.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BalanceTypeTest extends KfsUnitTestBase {

    private BalanceType balanceType;

    @BeforeEach
    void setUp() {
        balanceType = new BalanceType();
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void code() {
            balanceType.setCode("AC");
            assertThat(balanceType.getCode()).isEqualTo("AC");
        }

        @Test
        void name() {
            balanceType.setName("Actual");
            assertThat(balanceType.getName()).isEqualTo("Actual");
        }

        @Test
        void active() {
            balanceType.setActive(true);
            assertThat(balanceType.isActive()).isTrue();
            balanceType.setActive(false);
            assertThat(balanceType.isActive()).isFalse();
        }

        @Test
        void finBalanceTypeEncumIndicator() {
            balanceType.setFinBalanceTypeEncumIndicator(true);
            assertThat(balanceType.isFinBalanceTypeEncumIndicator()).isTrue();
        }

        @Test
        void financialBalanceTypeShortNm() {
            balanceType.setFinancialBalanceTypeShortNm("ACT");
            assertThat(balanceType.getFinancialBalanceTypeShortNm()).isEqualTo("ACT");
        }

        @Test
        void financialOffsetGenerationIndicator() {
            balanceType.setFinancialOffsetGenerationIndicator(true);
            assertThat(balanceType.isFinancialOffsetGenerationIndicator()).isTrue();
        }
    }
}
