package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionObjectDumpId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String organizationChartOfAccountsCode;
    private String organizationCode;
    private String subFundGroupCode;
    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String financialObjectCode;

    public BudgetConstructionObjectDumpId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getOrganizationChartOfAccountsCode() { return organizationChartOfAccountsCode; }
    public void setOrganizationChartOfAccountsCode(String organizationChartOfAccountsCode) { this.organizationChartOfAccountsCode = organizationChartOfAccountsCode; }

    public String getOrganizationCode() { return organizationCode; }
    public void setOrganizationCode(String organizationCode) { this.organizationCode = organizationCode; }

    public String getSubFundGroupCode() { return subFundGroupCode; }
    public void setSubFundGroupCode(String subFundGroupCode) { this.subFundGroupCode = subFundGroupCode; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionObjectDumpId that = (BudgetConstructionObjectDumpId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(organizationChartOfAccountsCode, that.organizationChartOfAccountsCode) && Objects.equals(organizationCode, that.organizationCode) && Objects.equals(subFundGroupCode, that.subFundGroupCode) && Objects.equals(universityFiscalYear, that.universityFiscalYear) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(subAccountNumber, that.subAccountNumber) && Objects.equals(financialObjectCode, that.financialObjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, organizationChartOfAccountsCode, organizationCode, subFundGroupCode, universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber, financialObjectCode);
    }
}
