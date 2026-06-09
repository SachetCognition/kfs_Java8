package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionMonthSummaryId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String organizationChartOfAccountsCode;
    private String organizationCode;
    private String subFundGroupCode;
    private String chartOfAccountsCode;
    private String incomeExpenseCode;
    private String financialConsolidationSortCode;
    private String financialLevelSortCode;
    private String financialObjectCode;
    private String financialSubObjectCode;

    public BudgetConstructionMonthSummaryId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getOrganizationChartOfAccountsCode() { return organizationChartOfAccountsCode; }
    public void setOrganizationChartOfAccountsCode(String organizationChartOfAccountsCode) { this.organizationChartOfAccountsCode = organizationChartOfAccountsCode; }

    public String getOrganizationCode() { return organizationCode; }
    public void setOrganizationCode(String organizationCode) { this.organizationCode = organizationCode; }

    public String getSubFundGroupCode() { return subFundGroupCode; }
    public void setSubFundGroupCode(String subFundGroupCode) { this.subFundGroupCode = subFundGroupCode; }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getIncomeExpenseCode() { return incomeExpenseCode; }
    public void setIncomeExpenseCode(String incomeExpenseCode) { this.incomeExpenseCode = incomeExpenseCode; }

    public String getFinancialConsolidationSortCode() { return financialConsolidationSortCode; }
    public void setFinancialConsolidationSortCode(String financialConsolidationSortCode) { this.financialConsolidationSortCode = financialConsolidationSortCode; }

    public String getFinancialLevelSortCode() { return financialLevelSortCode; }
    public void setFinancialLevelSortCode(String financialLevelSortCode) { this.financialLevelSortCode = financialLevelSortCode; }

    public String getFinancialObjectCode() { return financialObjectCode; }
    public void setFinancialObjectCode(String financialObjectCode) { this.financialObjectCode = financialObjectCode; }

    public String getFinancialSubObjectCode() { return financialSubObjectCode; }
    public void setFinancialSubObjectCode(String financialSubObjectCode) { this.financialSubObjectCode = financialSubObjectCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionMonthSummaryId that = (BudgetConstructionMonthSummaryId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(organizationChartOfAccountsCode, that.organizationChartOfAccountsCode) && Objects.equals(organizationCode, that.organizationCode) && Objects.equals(subFundGroupCode, that.subFundGroupCode) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(incomeExpenseCode, that.incomeExpenseCode) && Objects.equals(financialConsolidationSortCode, that.financialConsolidationSortCode) && Objects.equals(financialLevelSortCode, that.financialLevelSortCode) && Objects.equals(financialObjectCode, that.financialObjectCode) && Objects.equals(financialSubObjectCode, that.financialSubObjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, organizationChartOfAccountsCode, organizationCode, subFundGroupCode, chartOfAccountsCode, incomeExpenseCode, financialConsolidationSortCode, financialLevelSortCode, financialObjectCode, financialSubObjectCode);
    }
}
