package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionPositionFundingId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String selectedOrganizationChartOfAccountsCode;
    private String selectedOrganizationCode;
    private String name;
    private String emplid;
    private String positionNumber;
    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String financialObjectCode;
    private String financialSubObjectCode;

    public BudgetConstructionPositionFundingId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getSelectedOrganizationChartOfAccountsCode() { return selectedOrganizationChartOfAccountsCode; }
    public void setSelectedOrganizationChartOfAccountsCode(String selectedOrganizationChartOfAccountsCode) { this.selectedOrganizationChartOfAccountsCode = selectedOrganizationChartOfAccountsCode; }

    public String getSelectedOrganizationCode() { return selectedOrganizationCode; }
    public void setSelectedOrganizationCode(String selectedOrganizationCode) { this.selectedOrganizationCode = selectedOrganizationCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmplid() { return emplid; }
    public void setEmplid(String emplid) { this.emplid = emplid; }

    public String getPositionNumber() { return positionNumber; }
    public void setPositionNumber(String positionNumber) { this.positionNumber = positionNumber; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionPositionFundingId that = (BudgetConstructionPositionFundingId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(selectedOrganizationChartOfAccountsCode, that.selectedOrganizationChartOfAccountsCode) && Objects.equals(selectedOrganizationCode, that.selectedOrganizationCode) && Objects.equals(name, that.name) && Objects.equals(emplid, that.emplid) && Objects.equals(positionNumber, that.positionNumber) && Objects.equals(universityFiscalYear, that.universityFiscalYear) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(subAccountNumber, that.subAccountNumber) && Objects.equals(financialObjectCode, that.financialObjectCode) && Objects.equals(financialSubObjectCode, that.financialSubObjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, selectedOrganizationChartOfAccountsCode, selectedOrganizationCode, name, emplid, positionNumber, universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber, financialObjectCode, financialSubObjectCode);
    }
}
