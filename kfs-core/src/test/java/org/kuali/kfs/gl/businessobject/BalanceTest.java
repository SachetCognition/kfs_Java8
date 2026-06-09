package org.kuali.kfs.gl.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class BalanceTest extends KfsUnitTestBase {

    private Balance balance;

    @Mock
    private Transaction transaction;

    @Mock
    private BalanceHistory balanceHistory;

    @BeforeEach
    void setUp() {
        balance = new Balance();
    }

    @Test
    void constructor_defaultInitializesAllAmountsToZero() {
        assertThat(balance.getAccountLineAnnualBalanceAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getBeginningBalanceLineAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getContractsGrantsBeginningBalanceAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth1Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth2Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth3Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth4Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth5Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth6Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth7Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth8Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth9Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth10Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth11Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth12Amount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(balance.getMonth13Amount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void constructorFromTransaction_copiesFields() {
        when(transaction.getUniversityFiscalYear()).thenReturn(2024);
        when(transaction.getChartOfAccountsCode()).thenReturn("BL");
        when(transaction.getAccountNumber()).thenReturn("1234567");
        when(transaction.getSubAccountNumber()).thenReturn("---");
        when(transaction.getFinancialObjectCode()).thenReturn("5000");
        when(transaction.getFinancialSubObjectCode()).thenReturn("---");
        when(transaction.getFinancialBalanceTypeCode()).thenReturn("AC");
        when(transaction.getFinancialObjectTypeCode()).thenReturn("EX");

        Balance fromTx = new Balance(transaction);

        assertThat(fromTx.getUniversityFiscalYear()).isEqualTo(2024);
        assertThat(fromTx.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(fromTx.getAccountNumber()).isEqualTo("1234567");
        assertThat(fromTx.getSubAccountNumber()).isEqualTo("---");
        assertThat(fromTx.getObjectCode()).isEqualTo("5000");
        assertThat(fromTx.getSubObjectCode()).isEqualTo("---");
        assertThat(fromTx.getBalanceTypeCode()).isEqualTo("AC");
        assertThat(fromTx.getObjectTypeCode()).isEqualTo("EX");
        assertThat(fromTx.getMonth1Amount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void constructorFromBalanceHistory_copiesFields() {
        when(balanceHistory.getChartOfAccountsCode()).thenReturn("UA");
        when(balanceHistory.getAccountNumber()).thenReturn("9999999");
        when(balanceHistory.getBalanceTypeCode()).thenReturn("AC");
        when(balanceHistory.getObjectCode()).thenReturn("4000");
        when(balanceHistory.getObjectTypeCode()).thenReturn("IN");
        when(balanceHistory.getSubObjectCode()).thenReturn("001");
        when(balanceHistory.getUniversityFiscalYear()).thenReturn(2023);
        when(balanceHistory.getSubAccountNumber()).thenReturn("SUB1");

        Balance fromHistory = new Balance(balanceHistory);

        assertThat(fromHistory.getChartOfAccountsCode()).isEqualTo("UA");
        assertThat(fromHistory.getAccountNumber()).isEqualTo("9999999");
        assertThat(fromHistory.getBalanceTypeCode()).isEqualTo("AC");
        assertThat(fromHistory.getObjectCode()).isEqualTo("4000");
        assertThat(fromHistory.getObjectTypeCode()).isEqualTo("IN");
        assertThat(fromHistory.getSubObjectCode()).isEqualTo("001");
        assertThat(fromHistory.getUniversityFiscalYear()).isEqualTo(2023);
        assertThat(fromHistory.getSubAccountNumber()).isEqualTo("SUB1");
    }

    @ParameterizedTest
    @CsvSource({
        "AB, accountLineAnnualBalanceAmount",
        "BB, beginningBalanceLineAmount",
        "CB, contractsGrantsBeginningBalanceAmount",
        "01, month1Amount",
        "02, month2Amount",
        "03, month3Amount",
        "04, month4Amount",
        "05, month5Amount",
        "06, month6Amount",
        "07, month7Amount",
        "08, month8Amount",
        "09, month9Amount",
        "10, month10Amount",
        "11, month11Amount",
        "12, month12Amount",
        "13, month13Amount"
    })
    void getAmount_returnCorrectAmountForPeriod(String period, String fieldDescription) {
        KualiDecimal testAmount = new KualiDecimal(100);
        setAmountForPeriod(balance, period, testAmount);

        assertThat(balance.getAmount(period)).isEqualTo(testAmount);
    }

    @Test
    void getAmount_returnsNullForUnknownPeriod() {
        assertThat(balance.getAmount("XX")).isNull();
    }

    @Test
    void addAmount_annualBalance_addsWithoutAffectingMonthly() {
        KualiDecimal initial = new KualiDecimal(50);
        balance.setAccountLineAnnualBalanceAmount(initial);

        balance.addAmount(KFSConstants.PERIOD_CODE_ANNUAL_BALANCE, new KualiDecimal(25));

        assertThat(balance.getAccountLineAnnualBalanceAmount()).isEqualTo(new KualiDecimal(75));
        assertThat(balance.getMonth1Amount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void addAmount_beginningBalance_addsToBeginningBalance() {
        balance.addAmount(KFSConstants.PERIOD_CODE_BEGINNING_BALANCE, new KualiDecimal(200));

        assertThat(balance.getBeginningBalanceLineAmount()).isEqualTo(new KualiDecimal(200));
    }

    @Test
    void addAmount_cgBeginningBalance_addsToCgBalance() {
        balance.addAmount(KFSConstants.PERIOD_CODE_CG_BEGINNING_BALANCE, new KualiDecimal(300));

        assertThat(balance.getContractsGrantsBeginningBalanceAmount()).isEqualTo(new KualiDecimal(300));
    }

    @Test
    void addAmount_monthlyPeriod_addsToMonthAndAnnual() {
        balance.addAmount(KFSConstants.MONTH1, new KualiDecimal(100));

        assertThat(balance.getMonth1Amount()).isEqualTo(new KualiDecimal(100));
        assertThat(balance.getAccountLineAnnualBalanceAmount()).isEqualTo(new KualiDecimal(100));
    }

    @Test
    void addAmount_multipleMonths_accumulatesAnnualBalance() {
        balance.addAmount(KFSConstants.MONTH1, new KualiDecimal(10));
        balance.addAmount(KFSConstants.MONTH2, new KualiDecimal(20));
        balance.addAmount(KFSConstants.MONTH3, new KualiDecimal(30));

        assertThat(balance.getMonth1Amount()).isEqualTo(new KualiDecimal(10));
        assertThat(balance.getMonth2Amount()).isEqualTo(new KualiDecimal(20));
        assertThat(balance.getMonth3Amount()).isEqualTo(new KualiDecimal(30));
        assertThat(balance.getAccountLineAnnualBalanceAmount()).isEqualTo(new KualiDecimal(60));
    }

    @Test
    void addAmount_month13_addsToMonth13AndAnnual() {
        balance.addAmount(KFSConstants.MONTH13, new KualiDecimal(500));

        assertThat(balance.getMonth13Amount()).isEqualTo(new KualiDecimal(500));
        assertThat(balance.getAccountLineAnnualBalanceAmount()).isEqualTo(new KualiDecimal(500));
    }

    @Test
    void getCombinedBeginningBalanceAmount_sumsBothBeginningAmounts() {
        balance.setBeginningBalanceLineAmount(new KualiDecimal(100));
        balance.setContractsGrantsBeginningBalanceAmount(new KualiDecimal(200));

        assertThat(balance.getCombinedBeginningBalanceAmount()).isEqualTo(new KualiDecimal(300));
    }

    @Test
    void getCombinedBeginningBalanceAmount_defaultsToZero() {
        assertThat(balance.getCombinedBeginningBalanceAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void settersAndGetters_workCorrectly() {
        balance.setUniversityFiscalYear(2025);
        balance.setChartOfAccountsCode("BL");
        balance.setAccountNumber("1234567");
        balance.setSubAccountNumber("SUB");
        balance.setObjectCode("5000");
        balance.setSubObjectCode("001");
        balance.setBalanceTypeCode("AC");
        balance.setObjectTypeCode("EX");

        assertThat(balance.getUniversityFiscalYear()).isEqualTo(2025);
        assertThat(balance.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(balance.getAccountNumber()).isEqualTo("1234567");
        assertThat(balance.getSubAccountNumber()).isEqualTo("SUB");
        assertThat(balance.getObjectCode()).isEqualTo("5000");
        assertThat(balance.getSubObjectCode()).isEqualTo("001");
        assertThat(balance.getBalanceTypeCode()).isEqualTo("AC");
        assertThat(balance.getObjectTypeCode()).isEqualTo("EX");
    }

    private void setAmountForPeriod(Balance b, String period, KualiDecimal amount) {
        switch (period) {
            case "AB" -> b.setAccountLineAnnualBalanceAmount(amount);
            case "BB" -> b.setBeginningBalanceLineAmount(amount);
            case "CB" -> b.setContractsGrantsBeginningBalanceAmount(amount);
            case "01" -> b.setMonth1Amount(amount);
            case "02" -> b.setMonth2Amount(amount);
            case "03" -> b.setMonth3Amount(amount);
            case "04" -> b.setMonth4Amount(amount);
            case "05" -> b.setMonth5Amount(amount);
            case "06" -> b.setMonth6Amount(amount);
            case "07" -> b.setMonth7Amount(amount);
            case "08" -> b.setMonth8Amount(amount);
            case "09" -> b.setMonth9Amount(amount);
            case "10" -> b.setMonth10Amount(amount);
            case "11" -> b.setMonth11Amount(amount);
            case "12" -> b.setMonth12Amount(amount);
            case "13" -> b.setMonth13Amount(amount);
        }
    }
}
