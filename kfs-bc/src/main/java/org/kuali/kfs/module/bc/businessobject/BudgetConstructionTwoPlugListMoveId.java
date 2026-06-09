package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionTwoPlugListMoveId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String organizationChartOfAccountsCode;
    private String organizationCode;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;

    public BudgetConstructionTwoPlugListMoveId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getOrganizationChartOfAccountsCode() { return organizationChartOfAccountsCode; }
    public void setOrganizationChartOfAccountsCode(String organizationChartOfAccountsCode) { this.organizationChartOfAccountsCode = organizationChartOfAccountsCode; }

    public String getOrganizationCode() { return organizationCode; }
    public void setOrganizationCode(String organizationCode) { this.organizationCode = organizationCode; }

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
        BudgetConstructionTwoPlugListMoveId that = (BudgetConstructionTwoPlugListMoveId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(organizationChartOfAccountsCode, that.organizationChartOfAccountsCode) && Objects.equals(organizationCode, that.organizationCode) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(subAccountNumber, that.subAccountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, organizationChartOfAccountsCode, organizationCode, chartOfAccountsCode, accountNumber, subAccountNumber);
    }
}
