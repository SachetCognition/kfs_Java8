package org.kuali.kfs.module.bc.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiInteger;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PendingBudgetConstructionGeneralLedgerTest extends KfsUnitTestBase {

    @Test
    void getPrimaryKeyFields_returnsExpectedFields() {
        List<String> keys = PendingBudgetConstructionGeneralLedger.getPrimaryKeyFields();

        assertThat(keys).containsExactly(
                KFSPropertyConstants.DOCUMENT_NUMBER,
                KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR,
                KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE,
                KFSPropertyConstants.ACCOUNT_NUMBER,
                KFSPropertyConstants.SUB_ACCOUNT_NUMBER,
                KFSPropertyConstants.FINANCIAL_OBJECT_CODE,
                KFSPropertyConstants.FINANCIAL_SUB_OBJECT_CODE,
                KFSPropertyConstants.FINANCIAL_BALANCE_TYPE_CODE,
                KFSPropertyConstants.FINANCIAL_OBJECT_TYPE_CODE
        );
    }

    @Test
    void constructor_initializesDefaults() {
        PendingBudgetConstructionGeneralLedger gl = new PendingBudgetConstructionGeneralLedger();

        assertThat(gl.getDocumentNumber()).isNull();
        assertThat(gl.getFinancialObjectCode()).isNull();
    }

    @Test
    void setAndGetProperties() {
        PendingBudgetConstructionGeneralLedger gl = new PendingBudgetConstructionGeneralLedger();

        gl.setDocumentNumber("DOC001");
        gl.setUniversityFiscalYear(2024);
        gl.setChartOfAccountsCode("UA");
        gl.setAccountNumber("1234567");
        gl.setSubAccountNumber("-----");
        gl.setFinancialObjectCode("5000");
        gl.setFinancialSubObjectCode("000");
        gl.setFinancialBalanceTypeCode("BB");
        gl.setFinancialObjectTypeCode("IN");
        gl.setAccountLineAnnualBalanceAmount(new KualiInteger(50000));
        gl.setFinancialBeginningBalanceLineAmount(new KualiInteger(45000));

        assertThat(gl.getDocumentNumber()).isEqualTo("DOC001");
        assertThat(gl.getUniversityFiscalYear()).isEqualTo(2024);
        assertThat(gl.getChartOfAccountsCode()).isEqualTo("UA");
        assertThat(gl.getAccountNumber()).isEqualTo("1234567");
        assertThat(gl.getSubAccountNumber()).isEqualTo("-----");
        assertThat(gl.getFinancialObjectCode()).isEqualTo("5000");
        assertThat(gl.getFinancialSubObjectCode()).isEqualTo("000");
        assertThat(gl.getFinancialBalanceTypeCode()).isEqualTo("BB");
        assertThat(gl.getFinancialObjectTypeCode()).isEqualTo("IN");
        assertThat(gl.getAccountLineAnnualBalanceAmount()).isEqualTo(new KualiInteger(50000));
        assertThat(gl.getFinancialBeginningBalanceLineAmount()).isEqualTo(new KualiInteger(45000));
    }

    @Test
    void getPercentChange_calculatedFromAmounts() {
        PendingBudgetConstructionGeneralLedger gl = new PendingBudgetConstructionGeneralLedger();
        gl.setFinancialBeginningBalanceLineAmount(new KualiInteger(100));
        gl.setAccountLineAnnualBalanceAmount(new KualiInteger(110));

        assertThat(gl.getPercentChange()).isEqualTo(new org.kuali.rice.core.api.util.type.KualiDecimal(10));
    }
}
