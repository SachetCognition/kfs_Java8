package org.kuali.kfs.module.bc.document;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionDocumentId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;

    public BudgetConstructionDocumentId() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionDocumentId that = (BudgetConstructionDocumentId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(universityFiscalYear, that.universityFiscalYear) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(subAccountNumber, that.subAccountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber);
    }
}
