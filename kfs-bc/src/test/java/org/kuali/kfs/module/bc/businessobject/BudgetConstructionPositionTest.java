package org.kuali.kfs.module.bc.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.BCConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetConstructionPositionTest extends KfsUnitTestBase {

    @Test
    void constructor_initializesEmptyLists() {
        BudgetConstructionPosition pos = new BudgetConstructionPosition();

        assertThat(pos.getBudgetConstructionPositionSelect()).isNotNull().isEmpty();
        assertThat(pos.getPendingBudgetConstructionAppointmentFunding()).isNotNull().isEmpty();
    }

    @Test
    void constructor_setsActiveTrue() {
        BudgetConstructionPosition pos = new BudgetConstructionPosition();
        assertThat(pos.isActive()).isTrue();
    }

    @Test
    void isEffective_returnsTrue_whenStatusNotInactive() {
        BudgetConstructionPosition pos = new BudgetConstructionPosition();
        pos.setPositionEffectiveStatus("A");

        assertThat(pos.isEffective()).isTrue();
    }

    @Test
    void isEffective_returnsFalse_whenStatusInactive() {
        BudgetConstructionPosition pos = new BudgetConstructionPosition();
        pos.setPositionEffectiveStatus(BCConstants.POSITION_CODE_INACTIVE);

        assertThat(pos.isEffective()).isFalse();
    }

    @Test
    void isEffective_returnsTrue_whenStatusEmpty() {
        BudgetConstructionPosition pos = new BudgetConstructionPosition();
        pos.setPositionEffectiveStatus("");

        assertThat(pos.isEffective()).isTrue();
    }

    @Test
    void setAndGetProperties() {
        BudgetConstructionPosition pos = new BudgetConstructionPosition();

        pos.setUniversityFiscalYear(2024);
        pos.setPositionNumber("POS001");
        pos.setPositionDescription("Budget Analyst");
        pos.setPositionDepartmentIdentifier("DEPT01");
        pos.setJobCode("JOB001");
        pos.setJobCodeDescription("Budget Analyst Job");
        pos.setBudgetedPosition(true);
        pos.setConfidentialPosition(false);
        pos.setPositionFullTimeEquivalency(new BigDecimal("1.00"));
        pos.setPositionStandardHoursDefault(new BigDecimal("40.00"));
        pos.setIuNormalWorkMonths(12);
        pos.setIuPayMonths(12);
        pos.setPositionRegularTemporary("R");
        pos.setPositionEffectiveStatus("A");

        assertThat(pos.getUniversityFiscalYear()).isEqualTo(2024);
        assertThat(pos.getPositionNumber()).isEqualTo("POS001");
        assertThat(pos.getPositionDescription()).isEqualTo("Budget Analyst");
        assertThat(pos.getPositionDepartmentIdentifier()).isEqualTo("DEPT01");
        assertThat(pos.getJobCode()).isEqualTo("JOB001");
        assertThat(pos.getJobCodeDescription()).isEqualTo("Budget Analyst Job");
        assertThat(pos.isBudgetedPosition()).isTrue();
        assertThat(pos.isConfidentialPosition()).isFalse();
        assertThat(pos.getPositionFullTimeEquivalency()).isEqualByComparingTo(new BigDecimal("1.00"));
        assertThat(pos.getPositionStandardHoursDefault()).isEqualByComparingTo(new BigDecimal("40.00"));
        assertThat(pos.getIuNormalWorkMonths()).isEqualTo(12);
        assertThat(pos.getIuPayMonths()).isEqualTo(12);
        assertThat(pos.getPositionRegularTemporary()).isEqualTo("R");
        assertThat(pos.getPositionEffectiveStatus()).isEqualTo("A");
    }

    @Test
    void isBudgetedPosition_defaultFalse() {
        BudgetConstructionPosition pos = new BudgetConstructionPosition();
        assertThat(pos.isBudgetedPosition()).isFalse();
    }

    @Test
    void positionLockUserIdentifier_setAndGet() {
        BudgetConstructionPosition pos = new BudgetConstructionPosition();
        pos.setPositionLockUserIdentifier("USER001");
        assertThat(pos.getPositionLockUserIdentifier()).isEqualTo("USER001");
    }

    @Test
    void positionUnionCode_setAndGet() {
        BudgetConstructionPosition pos = new BudgetConstructionPosition();
        pos.setPositionUnionCode("UNION01");
        assertThat(pos.getPositionUnionCode()).isEqualTo("UNION01");
    }
}
