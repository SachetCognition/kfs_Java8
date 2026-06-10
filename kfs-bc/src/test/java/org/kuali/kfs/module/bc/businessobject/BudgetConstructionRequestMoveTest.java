package org.kuali.kfs.module.bc.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiInteger;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetConstructionRequestMoveTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_fieldsAreNull() {
        BudgetConstructionRequestMove move = new BudgetConstructionRequestMove();
        assertThat(move.getPrincipalId()).isNull();
        assertThat(move.getChartOfAccountsCode()).isNull();
        assertThat(move.getAccountNumber()).isNull();
        assertThat(move.getSubAccountNumber()).isNull();
        assertThat(move.getFinancialObjectCode()).isNull();
        assertThat(move.getFinancialSubObjectCode()).isNull();
        assertThat(move.getAccountLineAnnualBalanceAmount()).isNull();
    }

    @Test
    void setAndGetPrincipalId() {
        BudgetConstructionRequestMove move = new BudgetConstructionRequestMove();
        move.setPrincipalId("user123");
        assertThat(move.getPrincipalId()).isEqualTo("user123");
    }

    @Test
    void setAndGetChartOfAccountsCode() {
        BudgetConstructionRequestMove move = new BudgetConstructionRequestMove();
        move.setChartOfAccountsCode("UA");
        assertThat(move.getChartOfAccountsCode()).isEqualTo("UA");
    }

    @Test
    void setAndGetAccountNumber() {
        BudgetConstructionRequestMove move = new BudgetConstructionRequestMove();
        move.setAccountNumber("1234567");
        assertThat(move.getAccountNumber()).isEqualTo("1234567");
    }

    @Test
    void setAndGetSubAccountNumber() {
        BudgetConstructionRequestMove move = new BudgetConstructionRequestMove();
        move.setSubAccountNumber("SUB01");
        assertThat(move.getSubAccountNumber()).isEqualTo("SUB01");
    }

    @Test
    void setAndGetFinancialObjectCode() {
        BudgetConstructionRequestMove move = new BudgetConstructionRequestMove();
        move.setFinancialObjectCode("5000");
        assertThat(move.getFinancialObjectCode()).isEqualTo("5000");
    }

    @Test
    void setAndGetFinancialSubObjectCode() {
        BudgetConstructionRequestMove move = new BudgetConstructionRequestMove();
        move.setFinancialSubObjectCode("001");
        assertThat(move.getFinancialSubObjectCode()).isEqualTo("001");
    }

    @Test
    void setAndGetAccountLineAnnualBalanceAmount() {
        BudgetConstructionRequestMove move = new BudgetConstructionRequestMove();
        KualiInteger amount = new KualiInteger(50000);
        move.setAccountLineAnnualBalanceAmount(amount);
        assertThat(move.getAccountLineAnnualBalanceAmount()).isEqualTo(amount);
    }

    @Test
    void setAndGetMonthlyLineAmounts() {
        BudgetConstructionRequestMove move = new BudgetConstructionRequestMove();
        KualiInteger amount = new KualiInteger(1000);

        move.setFinancialDocumentMonth1LineAmount(amount);
        assertThat(move.getFinancialDocumentMonth1LineAmount()).isEqualTo(amount);

        move.setFinancialDocumentMonth6LineAmount(new KualiInteger(6000));
        assertThat(move.getFinancialDocumentMonth6LineAmount()).isEqualTo(new KualiInteger(6000));

        move.setFinancialDocumentMonth12LineAmount(new KualiInteger(12000));
        assertThat(move.getFinancialDocumentMonth12LineAmount()).isEqualTo(new KualiInteger(12000));
    }

    @Test
    void setAndGetFinancialObjectTypeCode() {
        BudgetConstructionRequestMove move = new BudgetConstructionRequestMove();
        move.setFinancialObjectTypeCode("EX");
        assertThat(move.getFinancialObjectTypeCode()).isEqualTo("EX");
    }

    @Test
    void setAndGetRequestUpdateErrorCode() {
        BudgetConstructionRequestMove move = new BudgetConstructionRequestMove();
        move.setRequestUpdateErrorCode("E01");
        assertThat(move.getRequestUpdateErrorCode()).isEqualTo("E01");
    }
}
