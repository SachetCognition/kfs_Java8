package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class LaborLedgerPendingEntryTest extends KfsUnitTestBase {

    private LaborLedgerPendingEntry entry;

    @BeforeEach
    void setUp() {
        entry = new LaborLedgerPendingEntry();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(entry).isNotNull();
    }

    @Test
    void testSetAndGetPositionNumber() {
        entry.setPositionNumber("00012345");
        assertThat(entry.getPositionNumber()).isEqualTo("00012345");
    }

    @Test
    void testSetAndGetEmplid() {
        entry.setEmplid("0000001234");
        assertThat(entry.getEmplid()).isEqualTo("0000001234");
    }

    @Test
    void testSetAndGetEmployeeRecord() {
        entry.setEmployeeRecord(2);
        assertThat(entry.getEmployeeRecord()).isEqualTo(2);
    }

    @Test
    void testSetAndGetEarnCode() {
        entry.setEarnCode("RGN");
        assertThat(entry.getEarnCode()).isEqualTo("RGN");
    }

    @Test
    void testSetAndGetPayGroup() {
        entry.setPayGroup("MO1");
        assertThat(entry.getPayGroup()).isEqualTo("MO1");
    }

    @Test
    void testSetAndGetSalaryAdministrationPlan() {
        entry.setSalaryAdministrationPlan("SAP1");
        assertThat(entry.getSalaryAdministrationPlan()).isEqualTo("SAP1");
    }

    @Test
    void testSetAndGetGrade() {
        entry.setGrade("15");
        assertThat(entry.getGrade()).isEqualTo("15");
    }

    @Test
    void testSetAndGetRunIdentifier() {
        entry.setRunIdentifier("RUN001");
        assertThat(entry.getRunIdentifier()).isEqualTo("RUN001");
    }

    @Test
    void testSetAndGetTransactionTotalHours() {
        BigDecimal hours = new BigDecimal("40.00");
        entry.setTransactionTotalHours(hours);
        assertThat(entry.getTransactionTotalHours()).isEqualByComparingTo(hours);
    }

    @Test
    void testSetAndGetPayrollEndDateFiscalYear() {
        entry.setPayrollEndDateFiscalYear(2024);
        assertThat(entry.getPayrollEndDateFiscalYear()).isEqualTo(2024);
    }

    @Test
    void testSetAndGetPayrollEndDateFiscalPeriodCode() {
        entry.setPayrollEndDateFiscalPeriodCode("01");
        assertThat(entry.getPayrollEndDateFiscalPeriodCode()).isEqualTo("01");
    }

    @Test
    void testSetAndGetTransactionPostingDate() {
        Date date = Date.valueOf("2024-01-15");
        entry.setTransactionPostingDate(date);
        assertThat(entry.getTransactionPostingDate()).isEqualTo(date);
    }

    @Test
    void testSetAndGetPayPeriodEndDate() {
        Date date = Date.valueOf("2024-01-31");
        entry.setPayPeriodEndDate(date);
        assertThat(entry.getPayPeriodEndDate()).isEqualTo(date);
    }

    @Test
    void testSetAndGetTransactionEntryOffsetCode() {
        entry.setTransactionEntryOffsetCode("N");
        assertThat(entry.getTransactionEntryOffsetCode()).isEqualTo("N");
    }

    @Test
    void testSetAndGetHrmsCompany() {
        entry.setHrmsCompany("IU");
        assertThat(entry.getHrmsCompany()).isEqualTo("IU");
    }

    @Test
    void testSetAndGetSetid() {
        entry.setSetid("IU001");
        assertThat(entry.getSetid()).isEqualTo("IU001");
    }

    @Test
    void testSetAndGetLaborLedgerOriginalFields() {
        entry.setLaborLedgerOriginalChartOfAccountsCode("BL");
        entry.setLaborLedgerOriginalAccountNumber("1234567");
        entry.setLaborLedgerOriginalSubAccountNumber("12345");
        entry.setLaborLedgerOriginalFinancialObjectCode("5000");
        entry.setLaborLedgerOriginalFinancialSubObjectCode("001");

        assertThat(entry.getLaborLedgerOriginalChartOfAccountsCode()).isEqualTo("BL");
        assertThat(entry.getLaborLedgerOriginalAccountNumber()).isEqualTo("1234567");
        assertThat(entry.getLaborLedgerOriginalSubAccountNumber()).isEqualTo("12345");
        assertThat(entry.getLaborLedgerOriginalFinancialObjectCode()).isEqualTo("5000");
        assertThat(entry.getLaborLedgerOriginalFinancialSubObjectCode()).isEqualTo("001");
    }
}
