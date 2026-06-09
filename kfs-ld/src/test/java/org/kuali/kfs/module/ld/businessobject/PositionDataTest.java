package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class PositionDataTest extends KfsUnitTestBase {

    private PositionData positionData;

    @BeforeEach
    void setUp() {
        positionData = new PositionData();
    }

    @Test
    void testSetAndGetPositionNumber() {
        positionData.setPositionNumber("00012345");
        assertThat(positionData.getPositionNumber()).isEqualTo("00012345");
    }

    @Test
    void testSetAndGetJobCode() {
        positionData.setJobCode("JOB01");
        assertThat(positionData.getJobCode()).isEqualTo("JOB01");
    }

    @Test
    void testSetAndGetEffectiveDate() {
        Date date = Date.valueOf("2024-01-01");
        positionData.setEffectiveDate(date);
        assertThat(positionData.getEffectiveDate()).isEqualTo(date);
    }

    @Test
    void testSetAndGetBusinessUnit() {
        positionData.setBusinessUnit("UITS");
        assertThat(positionData.getBusinessUnit()).isEqualTo("UITS");
    }

    @Test
    void testSetAndGetDepartmentId() {
        positionData.setDepartmentId("DEPT01");
        assertThat(positionData.getDepartmentId()).isEqualTo("DEPT01");
    }

    @Test
    void testSetAndGetPositionStatus() {
        positionData.setPositionStatus("A");
        assertThat(positionData.getPositionStatus()).isEqualTo("A");
    }

    @Test
    void testSetAndGetDescription() {
        positionData.setDescription("Senior Accountant");
        assertThat(positionData.getDescription()).isEqualTo("Senior Accountant");
    }

    @Test
    void testSetAndGetPositionGradeDefault() {
        positionData.setPositionGradeDefault("15");
        assertThat(positionData.getPositionGradeDefault()).isEqualTo("15");
    }

    @Test
    void testSetAndGetPositionSalaryPlanDefault() {
        positionData.setPositionSalaryPlanDefault("SAP1");
        assertThat(positionData.getPositionSalaryPlanDefault()).isEqualTo("SAP1");
    }

    @Test
    void testSetAndGetPositionRegularTemporary() {
        positionData.setPositionRegularTemporary("R");
        assertThat(positionData.getPositionRegularTemporary()).isEqualTo("R");
    }

    @Test
    void testSetAndGetPositionFullTimeEquivalency() {
        BigDecimal fte = new BigDecimal("1.00");
        positionData.setPositionFullTimeEquivalency(fte);
        assertThat(positionData.getPositionFullTimeEquivalency()).isEqualByComparingTo(fte);
    }

    @Test
    void testSetAndGetShortDescription() {
        positionData.setShortDescription("Sr Acct");
        assertThat(positionData.getShortDescription()).isEqualTo("Sr Acct");
    }

    @Test
    void testSetAndGetPositionEffectiveStatus() {
        positionData.setPositionEffectiveStatus("A");
        assertThat(positionData.getPositionEffectiveStatus()).isEqualTo("A");
    }

    @Test
    void testSetAndGetStatusDate() {
        Date date = Date.valueOf("2024-06-01");
        positionData.setStatusDate(date);
        assertThat(positionData.getStatusDate()).isEqualTo(date);
    }
}
