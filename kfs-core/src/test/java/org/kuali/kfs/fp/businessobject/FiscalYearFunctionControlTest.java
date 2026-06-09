package org.kuali.kfs.fp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class FiscalYearFunctionControlTest extends KfsUnitTestBase {

    private FiscalYearFunctionControl control;

    @BeforeEach
    void setUp() {
        control = new FiscalYearFunctionControl();
    }

    @Test
    void setAndGetUniversityFiscalYear() {
        control.setUniversityFiscalYear(2024);
        assertThat(control.getUniversityFiscalYear()).isEqualTo(2024);
    }

    @Test
    void setAndGetFinancialSystemFunctionControlCode() {
        control.setFinancialSystemFunctionControlCode("BAACTV");
        assertThat(control.getFinancialSystemFunctionControlCode()).isEqualTo("BAACTV");
    }

    @Test
    void setAndGetFinancialSystemFunctionActiveIndicator() {
        control.setFinancialSystemFunctionActiveIndicator(true);
        assertThat(control.isFinancialSystemFunctionActiveIndicator()).isTrue();
    }

    @Test
    void activeIndicatorDefaultsToFalse() {
        assertThat(control.isFinancialSystemFunctionActiveIndicator()).isFalse();
    }

    @Test
    void equalsReturnsTrueForSameFiscalYear() {
        control.setUniversityFiscalYear(2024);
        control.setFinancialSystemFunctionControlCode("BAACTV");

        FiscalYearFunctionControl other = new FiscalYearFunctionControl();
        other.setUniversityFiscalYear(2024);
        other.setFinancialSystemFunctionControlCode("BASEAD");

        assertThat(control.equals(other)).isTrue();
    }

    @Test
    void equalsReturnsFalseForDifferentFiscalYear() {
        control.setUniversityFiscalYear(2024);

        FiscalYearFunctionControl other = new FiscalYearFunctionControl();
        other.setUniversityFiscalYear(2025);

        assertThat(control.equals(other)).isFalse();
    }

    @Test
    void equalsReturnsFalseForNull() {
        control.setUniversityFiscalYear(2024);
        assertThat(control.equals(null)).isFalse();
    }

    @Test
    void equalsReturnsFalseForDifferentType() {
        control.setUniversityFiscalYear(2024);
        assertThat(control.equals("not a control")).isFalse();
    }

    @Test
    void setAndGetFunctionControl() {
        FunctionControlCode fcc = new FunctionControlCode();
        control.setFunctionControl(fcc);
        assertThat(control.getFunctionControl()).isSameAs(fcc);
    }

    @Test
    void toStringMapperContainsFiscalYear() {
        control.setUniversityFiscalYear(2024);
        control.setFinancialSystemFunctionControlCode("BAACTV");
        assertThat(control.toStringMapper_RICE20_REFACTORME())
                .containsKey("universityFiscalYear")
                .containsKey("financialSystemFunctionControlCode");
    }

    @Test
    void toStringMapperHandlesNullFiscalYear() {
        control.setFinancialSystemFunctionControlCode("BAACTV");
        assertThat(control.toStringMapper_RICE20_REFACTORME())
                .doesNotContainKey("universityFiscalYear")
                .containsKey("financialSystemFunctionControlCode");
    }
}
