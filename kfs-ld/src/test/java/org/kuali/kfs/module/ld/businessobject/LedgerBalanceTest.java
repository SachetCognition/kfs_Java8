package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectType;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class LedgerBalanceTest extends KfsUnitTestBase {

    private LedgerBalance ledgerBalance;

    @BeforeEach
    void setUp() {
        ledgerBalance = new LedgerBalance();
    }

    @Test
    void testDefaultConstructorInitializesAmountsToZero() {
        assertThat(ledgerBalance.getAccountLineAnnualBalanceAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(ledgerBalance.getFinancialBeginningBalanceLineAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(ledgerBalance.getContractsGrantsBeginningBalanceAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testSetAndGetEmplid() {
        ledgerBalance.setEmplid("0000001234");
        assertThat(ledgerBalance.getEmplid()).isEqualTo("0000001234");
    }

    @Test
    void testSetAndGetFinancialBalanceTypeCode() {
        ledgerBalance.setFinancialBalanceTypeCode("AC");
        assertThat(ledgerBalance.getFinancialBalanceTypeCode()).isEqualTo("AC");
    }

    @Test
    void testSetAndGetFinancialObjectCode() {
        ledgerBalance.setFinancialObjectCode("5000");
        assertThat(ledgerBalance.getFinancialObjectCode()).isEqualTo("5000");
    }

    @Test
    void testSetAndGetFinancialSubObjectCode() {
        ledgerBalance.setFinancialSubObjectCode("001");
        assertThat(ledgerBalance.getFinancialSubObjectCode()).isEqualTo("001");
    }

    @Test
    void testSetAndGetFinancialObjectTypeCode() {
        ledgerBalance.setFinancialObjectTypeCode("EX");
        assertThat(ledgerBalance.getFinancialObjectTypeCode()).isEqualTo("EX");
    }

    @Test
    void testSetAndGetPositionNumber() {
        ledgerBalance.setPositionNumber("00012345");
        assertThat(ledgerBalance.getPositionNumber()).isEqualTo("00012345");
    }

    @Test
    void testSetAndGetTransactionDateTimeStamp() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ledgerBalance.setTransactionDateTimeStamp(ts);
        assertThat(ledgerBalance.getTransactionDateTimeStamp()).isEqualTo(ts);
    }

    @Test
    void testSetAndGetFinancialBeginningBalanceLineAmount() {
        KualiDecimal amount = new KualiDecimal(500);
        ledgerBalance.setFinancialBeginningBalanceLineAmount(amount);
        assertThat(ledgerBalance.getFinancialBeginningBalanceLineAmount()).isEqualTo(amount);
    }

    @Test
    void testBalanceTypeCodeDelegatesToFinancialBalanceTypeCode() {
        ledgerBalance.setFinancialBalanceTypeCode("AC");
        assertThat(ledgerBalance.getBalanceTypeCode()).isEqualTo("AC");

        ledgerBalance.setBalanceTypeCode("CB");
        assertThat(ledgerBalance.getFinancialBalanceTypeCode()).isEqualTo("CB");
    }

    @Test
    void testChartDelegatesToChartOfAccounts() {
        Chart chart = new Chart();
        chart.setChartOfAccountsCode("BL");
        ledgerBalance.setChartOfAccounts(chart);
        assertThat(ledgerBalance.getChart()).isSameAs(chart);
        assertThat(ledgerBalance.getChartOfAccounts().getChartOfAccountsCode()).isEqualTo("BL");
    }

    @Test
    void testSetChartDelegatesToSetChartOfAccounts() {
        Chart chart = new Chart();
        ledgerBalance.setChart(chart);
        assertThat(ledgerBalance.getChartOfAccounts()).isSameAs(chart);
    }

    @Test
    void testSetAndGetFinancialObjectType() {
        ObjectType ot = new ObjectType();
        ledgerBalance.setFinancialObjectType(ot);
        assertThat(ledgerBalance.getFinancialObjectType()).isSameAs(ot);
    }

    @Test
    void testAddAmountForMonth1() {
        KualiDecimal amount = new KualiDecimal(100);
        ledgerBalance.setMonth1Amount(KualiDecimal.ZERO);
        ledgerBalance.addAmount(KFSConstants.MONTH1, amount);
        assertThat(ledgerBalance.getMonth1Amount()).isEqualTo(amount);
        assertThat(ledgerBalance.getAccountLineAnnualBalanceAmount()).isEqualTo(amount);
    }

    @Test
    void testAddAmountForMonth6() {
        KualiDecimal amount = new KualiDecimal(250);
        ledgerBalance.setMonth6Amount(KualiDecimal.ZERO);
        ledgerBalance.addAmount(KFSConstants.MONTH6, amount);
        assertThat(ledgerBalance.getMonth6Amount()).isEqualTo(amount);
        assertThat(ledgerBalance.getAccountLineAnnualBalanceAmount()).isEqualTo(amount);
    }

    @Test
    void testAddAmountForMonth12() {
        KualiDecimal amount = new KualiDecimal(300);
        ledgerBalance.setMonth12Amount(KualiDecimal.ZERO);
        ledgerBalance.addAmount(KFSConstants.MONTH12, amount);
        assertThat(ledgerBalance.getMonth12Amount()).isEqualTo(amount);
        assertThat(ledgerBalance.getAccountLineAnnualBalanceAmount()).isEqualTo(amount);
    }

    @Test
    void testAddAmountForMonth13() {
        KualiDecimal amount = new KualiDecimal(50);
        ledgerBalance.setMonth13Amount(KualiDecimal.ZERO);
        ledgerBalance.addAmount(KFSConstants.MONTH13, amount);
        assertThat(ledgerBalance.getMonth13Amount()).isEqualTo(amount);
        assertThat(ledgerBalance.getAccountLineAnnualBalanceAmount()).isEqualTo(amount);
    }

    @Test
    void testAddAmountForAnnualBalance() {
        KualiDecimal amount = new KualiDecimal(1000);
        ledgerBalance.addAmount(KFSConstants.PERIOD_CODE_ANNUAL_BALANCE, amount);
        assertThat(ledgerBalance.getAccountLineAnnualBalanceAmount()).isEqualTo(amount);
    }

    @Test
    void testAddAmountForBeginningBalance() {
        KualiDecimal amount = new KualiDecimal(750);
        ledgerBalance.addAmount(KFSConstants.PERIOD_CODE_BEGINNING_BALANCE, amount);
        assertThat(ledgerBalance.getFinancialBeginningBalanceLineAmount()).isEqualTo(amount);
    }

    @Test
    void testAddAmountForCgBeginningBalance() {
        KualiDecimal amount = new KualiDecimal(200);
        ledgerBalance.addAmount(KFSConstants.PERIOD_CODE_CG_BEGINNING_BALANCE, amount);
        assertThat(ledgerBalance.getContractsGrantsBeginningBalanceAmount()).isEqualTo(amount);
    }

    @Test
    void testAddAmountAccumulatesForSamePeriod() {
        ledgerBalance.setMonth1Amount(KualiDecimal.ZERO);
        ledgerBalance.addAmount(KFSConstants.MONTH1, new KualiDecimal(100));
        ledgerBalance.addAmount(KFSConstants.MONTH1, new KualiDecimal(200));
        assertThat(ledgerBalance.getMonth1Amount()).isEqualTo(new KualiDecimal(300));
        assertThat(ledgerBalance.getAccountLineAnnualBalanceAmount()).isEqualTo(new KualiDecimal(300));
    }

    @Test
    void testSetAndGetLaborObject() {
        LaborObject lo = new LaborObject();
        lo.setFinancialObjectCode("5000");
        ledgerBalance.setLaborObject(lo);
        assertThat(ledgerBalance.getLaborObject()).isSameAs(lo);
    }

    @Test
    void testConstructorFromLaborTransaction() {
        LaborOriginEntry txn = new LaborOriginEntry();
        txn.setChartOfAccountsCode("BL");
        txn.setAccountNumber("1234567");
        txn.setFinancialBalanceTypeCode("AC");
        txn.setEmplid("0000001234");
        txn.setFinancialObjectCode("5000");
        txn.setFinancialObjectTypeCode("EX");
        txn.setFinancialSubObjectCode("001");
        txn.setPositionNumber("00012345");
        txn.setUniversityFiscalYear(2024);
        txn.setSubAccountNumber("12345");

        LedgerBalance balance = new LedgerBalance(txn);

        assertThat(balance.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(balance.getAccountNumber()).isEqualTo("1234567");
        assertThat(balance.getFinancialBalanceTypeCode()).isEqualTo("AC");
        assertThat(balance.getEmplid()).isEqualTo("0000001234");
        assertThat(balance.getFinancialObjectCode()).isEqualTo("5000");
        assertThat(balance.getFinancialObjectTypeCode()).isEqualTo("EX");
        assertThat(balance.getFinancialSubObjectCode()).isEqualTo("001");
        assertThat(balance.getPositionNumber()).isEqualTo("00012345");
        assertThat(balance.getUniversityFiscalYear()).isEqualTo(2024);
        assertThat(balance.getSubAccountNumber()).isEqualTo("12345");
        assertThat(balance.getAccountLineAnnualBalanceAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getFinancialBeginningBalanceLineAmount()).isEqualTo(KualiDecimal.ZERO);
    }
}
