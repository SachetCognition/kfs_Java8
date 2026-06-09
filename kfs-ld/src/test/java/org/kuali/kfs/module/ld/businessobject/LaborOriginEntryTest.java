package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class LaborOriginEntryTest extends KfsUnitTestBase {

    private LaborOriginEntry entry;

    @BeforeEach
    void setUp() {
        entry = new LaborOriginEntry();
    }

    @Test
    void testDefaultConstructorCreatesEntry() {
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
        entry.setEmployeeRecord(1);
        assertThat(entry.getEmployeeRecord()).isEqualTo(1);
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
    void testSetAndGetLaborLedgerOriginalChartOfAccountsCode() {
        entry.setLaborLedgerOriginalChartOfAccountsCode("BL");
        assertThat(entry.getLaborLedgerOriginalChartOfAccountsCode()).isEqualTo("BL");
    }

    @Test
    void testSetAndGetLaborLedgerOriginalAccountNumber() {
        entry.setLaborLedgerOriginalAccountNumber("1234567");
        assertThat(entry.getLaborLedgerOriginalAccountNumber()).isEqualTo("1234567");
    }

    @Test
    void testSetAndGetLaborLedgerOriginalSubAccountNumber() {
        entry.setLaborLedgerOriginalSubAccountNumber("12345");
        assertThat(entry.getLaborLedgerOriginalSubAccountNumber()).isEqualTo("12345");
    }

    @Test
    void testSetAndGetLaborLedgerOriginalFinancialObjectCode() {
        entry.setLaborLedgerOriginalFinancialObjectCode("5000");
        assertThat(entry.getLaborLedgerOriginalFinancialObjectCode()).isEqualTo("5000");
    }

    @Test
    void testSetAndGetLaborLedgerOriginalFinancialSubObjectCode() {
        entry.setLaborLedgerOriginalFinancialSubObjectCode("001");
        assertThat(entry.getLaborLedgerOriginalFinancialSubObjectCode()).isEqualTo("001");
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
    void testSetAndGetFinancialDocumentApprovedCode() {
        entry.setFinancialDocumentApprovedCode("A");
        assertThat(entry.getFinancialDocumentApprovedCode()).isEqualTo("A");
    }

    @Test
    void testSetAndGetTransactionEntryOffsetCode() {
        entry.setTransactionEntryOffsetCode("N");
        assertThat(entry.getTransactionEntryOffsetCode()).isEqualTo("N");
    }

    @Test
    void testSetAndGetTransactionEntryProcessedTimestamp() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        entry.setTransactionEntryProcessedTimestamp(ts);
        assertThat(entry.getTransactionEntryProcessedTimestamp()).isEqualTo(ts);
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
    void testConstructorWithDocTypeAndOriginCode() {
        LaborOriginEntry entry = new LaborOriginEntry("LLJV", "01");
        assertThat(entry.getFinancialDocumentTypeCode()).isEqualTo("LLJV");
        assertThat(entry.getFinancialSystemOriginationCode()).isEqualTo("01");
    }

    @Test
    void testSetAndGetMultipleFieldsTogether() {
        entry.setPositionNumber("00012345");
        entry.setEmplid("0000001234");
        entry.setEarnCode("RGN");
        entry.setPayGroup("MO1");
        entry.setGrade("15");
        entry.setChartOfAccountsCode("BL");
        entry.setAccountNumber("1234567");

        assertThat(entry.getPositionNumber()).isEqualTo("00012345");
        assertThat(entry.getEmplid()).isEqualTo("0000001234");
        assertThat(entry.getEarnCode()).isEqualTo("RGN");
        assertThat(entry.getPayGroup()).isEqualTo("MO1");
        assertThat(entry.getGrade()).isEqualTo("15");
        assertThat(entry.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(entry.getAccountNumber()).isEqualTo("1234567");
    }
}
