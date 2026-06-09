package org.kuali.kfs.gl.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class AccountBalanceTest extends KfsUnitTestBase {

    private AccountBalance accountBalance;

    @Mock
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        accountBalance = new AccountBalance();
    }

    @Test
    void defaultConstructor_initializesDummyBusinessObject() {
        assertThat(accountBalance.getDummyBusinessObject()).isNotNull();
    }

    @Test
    void defaultConstructor_initializesFinancialObject() {
        assertThat(accountBalance.getFinancialObject()).isNotNull();
    }

    @Test
    void constructorFromTransaction_copiesFieldsAndInitializesAmountsToZero() {
        when(transaction.getUniversityFiscalYear()).thenReturn(2024);
        when(transaction.getChartOfAccountsCode()).thenReturn("BL");
        when(transaction.getAccountNumber()).thenReturn("1234567");
        when(transaction.getSubAccountNumber()).thenReturn("-----");
        when(transaction.getFinancialObjectCode()).thenReturn("5000");
        when(transaction.getFinancialSubObjectCode()).thenReturn("---");

        AccountBalance fromTx = new AccountBalance(transaction);

        assertThat(fromTx.getUniversityFiscalYear()).isEqualTo(2024);
        assertThat(fromTx.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(fromTx.getAccountNumber()).isEqualTo("1234567");
        assertThat(fromTx.getSubAccountNumber()).isEqualTo("-----");
        assertThat(fromTx.getObjectCode()).isEqualTo("5000");
        assertThat(fromTx.getSubObjectCode()).isEqualTo("---");
        assertThat(fromTx.getCurrentBudgetLineBalanceAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(fromTx.getAccountLineActualsBalanceAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(fromTx.getAccountLineEncumbranceBalanceAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void settersAndGetters_amounts() {
        accountBalance.setCurrentBudgetLineBalanceAmount(new KualiDecimal(1000));
        accountBalance.setAccountLineActualsBalanceAmount(new KualiDecimal(800));
        accountBalance.setAccountLineEncumbranceBalanceAmount(new KualiDecimal(200));

        assertThat(accountBalance.getCurrentBudgetLineBalanceAmount()).isEqualTo(new KualiDecimal(1000));
        assertThat(accountBalance.getAccountLineActualsBalanceAmount()).isEqualTo(new KualiDecimal(800));
        assertThat(accountBalance.getAccountLineEncumbranceBalanceAmount()).isEqualTo(new KualiDecimal(200));
    }

    @Test
    void settersAndGetters_identifiers() {
        accountBalance.setUniversityFiscalYear(2025);
        accountBalance.setChartOfAccountsCode("UA");
        accountBalance.setAccountNumber("9999999");
        accountBalance.setSubAccountNumber("SUB1");
        accountBalance.setObjectCode("4000");
        accountBalance.setSubObjectCode("001");

        assertThat(accountBalance.getUniversityFiscalYear()).isEqualTo(2025);
        assertThat(accountBalance.getChartOfAccountsCode()).isEqualTo("UA");
        assertThat(accountBalance.getAccountNumber()).isEqualTo("9999999");
        assertThat(accountBalance.getSubAccountNumber()).isEqualTo("SUB1");
        assertThat(accountBalance.getObjectCode()).isEqualTo("4000");
        assertThat(accountBalance.getSubObjectCode()).isEqualTo("001");
    }

    @Test
    void typeConstants_haveExpectedValues() {
        assertThat(AccountBalance.TYPE_CONSOLIDATION).isEqualTo("Consolidation");
        assertThat(AccountBalance.TYPE_LEVEL).isEqualTo("Level");
        assertThat(AccountBalance.TYPE_OBJECT).isEqualTo("Object");
    }

    @Test
    void constructorWithTitle_setsTitle() {
        AccountBalance ab = new AccountBalance("Test Title");
        assertThat(ab.getTitle()).isEqualTo("Test Title");
    }
}
