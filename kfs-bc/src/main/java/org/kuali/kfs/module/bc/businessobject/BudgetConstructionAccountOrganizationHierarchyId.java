package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionAccountOrganizationHierarchyId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private Integer organizationLevelCode;

    public BudgetConstructionAccountOrganizationHierarchyId() {}

    public Integer getUniversityFiscalYear() { return universityFiscalYear; }
    public void setUniversityFiscalYear(Integer universityFiscalYear) { this.universityFiscalYear = universityFiscalYear; }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public Integer getOrganizationLevelCode() { return organizationLevelCode; }
    public void setOrganizationLevelCode(Integer organizationLevelCode) { this.organizationLevelCode = organizationLevelCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionAccountOrganizationHierarchyId that = (BudgetConstructionAccountOrganizationHierarchyId) o;
        return Objects.equals(universityFiscalYear, that.universityFiscalYear) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(organizationLevelCode, that.organizationLevelCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, chartOfAccountsCode, accountNumber, organizationLevelCode);
    }
}
