package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionSalarySocialSecurityNumberId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String organizationChartOfAccountsCode;
    private String organizationCode;
    private String name;
    private String emplid;

    public BudgetConstructionSalarySocialSecurityNumberId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getOrganizationChartOfAccountsCode() { return organizationChartOfAccountsCode; }
    public void setOrganizationChartOfAccountsCode(String organizationChartOfAccountsCode) { this.organizationChartOfAccountsCode = organizationChartOfAccountsCode; }

    public String getOrganizationCode() { return organizationCode; }
    public void setOrganizationCode(String organizationCode) { this.organizationCode = organizationCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmplid() { return emplid; }
    public void setEmplid(String emplid) { this.emplid = emplid; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionSalarySocialSecurityNumberId that = (BudgetConstructionSalarySocialSecurityNumberId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(organizationChartOfAccountsCode, that.organizationChartOfAccountsCode) && Objects.equals(organizationCode, that.organizationCode) && Objects.equals(name, that.name) && Objects.equals(emplid, that.emplid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, organizationChartOfAccountsCode, organizationCode, name, emplid);
    }
}
