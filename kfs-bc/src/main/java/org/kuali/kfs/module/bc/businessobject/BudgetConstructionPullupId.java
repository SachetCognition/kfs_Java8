package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionPullupId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String chartOfAccountsCode;
    private String organizationCode;

    public BudgetConstructionPullupId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getOrganizationCode() { return organizationCode; }
    public void setOrganizationCode(String organizationCode) { this.organizationCode = organizationCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionPullupId that = (BudgetConstructionPullupId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(organizationCode, that.organizationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, chartOfAccountsCode, organizationCode);
    }
}
