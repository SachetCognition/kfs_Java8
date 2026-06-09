package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionAccountSummaryId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String organizationChartOfAccountsCode;
    private String organizationCode;
    private String chartOfAccountsCode;
    private String fundGroupCode;
    private String subFundGroupCode;
    private String accountNumber;
    private String subAccountNumber;
    private String incomeExpenseCode;

    public BudgetConstructionAccountSummaryId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getOrganizationChartOfAccountsCode() { return organizationChartOfAccountsCode; }
    public void setOrganizationChartOfAccountsCode(String organizationChartOfAccountsCode) { this.organizationChartOfAccountsCode = organizationChartOfAccountsCode; }

    public String getOrganizationCode() { return organizationCode; }
    public void setOrganizationCode(String organizationCode) { this.organizationCode = organizationCode; }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getFundGroupCode() { return fundGroupCode; }
    public void setFundGroupCode(String fundGroupCode) { this.fundGroupCode = fundGroupCode; }

    public String getSubFundGroupCode() { return subFundGroupCode; }
    public void setSubFundGroupCode(String subFundGroupCode) { this.subFundGroupCode = subFundGroupCode; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getSubAccountNumber() { return subAccountNumber; }
    public void setSubAccountNumber(String subAccountNumber) { this.subAccountNumber = subAccountNumber; }

    public String getIncomeExpenseCode() { return incomeExpenseCode; }
    public void setIncomeExpenseCode(String incomeExpenseCode) { this.incomeExpenseCode = incomeExpenseCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionAccountSummaryId that = (BudgetConstructionAccountSummaryId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(organizationChartOfAccountsCode, that.organizationChartOfAccountsCode) && Objects.equals(organizationCode, that.organizationCode) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(fundGroupCode, that.fundGroupCode) && Objects.equals(subFundGroupCode, that.subFundGroupCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(subAccountNumber, that.subAccountNumber) && Objects.equals(incomeExpenseCode, that.incomeExpenseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, organizationChartOfAccountsCode, organizationCode, chartOfAccountsCode, fundGroupCode, subFundGroupCode, accountNumber, subAccountNumber, incomeExpenseCode);
    }
}
