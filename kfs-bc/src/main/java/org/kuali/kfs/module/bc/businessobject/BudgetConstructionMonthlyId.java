package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionMonthlyId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String financialObjectCode;
    private String financialSubObjectCode;
    private String financialBalanceTypeCode;
    private String financialObjectTypeCode;

    public BudgetConstructionMonthlyId() {}

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getUniversityFiscalYear() { return universityFiscalYear; }
    public void setUniversityFiscalYear(Integer universityFiscalYear) { this.universityFiscalYear = universityFiscalYear; }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getSubAccountNumber() { return subAccountNumber; }
    public void setSubAccountNumber(String subAccountNumber) { this.subAccountNumber = subAccountNumber; }

    public String getFinancialObjectCode() { return financialObjectCode; }
    public void setFinancialObjectCode(String financialObjectCode) { this.financialObjectCode = financialObjectCode; }

    public String getFinancialSubObjectCode() { return financialSubObjectCode; }
    public void setFinancialSubObjectCode(String financialSubObjectCode) { this.financialSubObjectCode = financialSubObjectCode; }

    public String getFinancialBalanceTypeCode() { return financialBalanceTypeCode; }
    public void setFinancialBalanceTypeCode(String financialBalanceTypeCode) { this.financialBalanceTypeCode = financialBalanceTypeCode; }

    public String getFinancialObjectTypeCode() { return financialObjectTypeCode; }
    public void setFinancialObjectTypeCode(String financialObjectTypeCode) { this.financialObjectTypeCode = financialObjectTypeCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionMonthlyId that = (BudgetConstructionMonthlyId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(universityFiscalYear, that.universityFiscalYear) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(subAccountNumber, that.subAccountNumber) && Objects.equals(financialObjectCode, that.financialObjectCode) && Objects.equals(financialSubObjectCode, that.financialSubObjectCode) && Objects.equals(financialBalanceTypeCode, that.financialBalanceTypeCode) && Objects.equals(financialObjectTypeCode, that.financialObjectTypeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber, financialObjectCode, financialSubObjectCode, financialBalanceTypeCode, financialObjectTypeCode);
    }
}
