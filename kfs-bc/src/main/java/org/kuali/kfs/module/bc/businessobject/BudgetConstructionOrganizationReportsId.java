package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionOrganizationReportsId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String chartOfAccountsCode;
    private String organizationCode;

    public BudgetConstructionOrganizationReportsId() {}

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getOrganizationCode() { return organizationCode; }
    public void setOrganizationCode(String organizationCode) { this.organizationCode = organizationCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionOrganizationReportsId that = (BudgetConstructionOrganizationReportsId) o;
        return Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(organizationCode, that.organizationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chartOfAccountsCode, organizationCode);
    }
}
