package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionIntendedIncumbentSelectId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String emplid;
    private String financialObjectCode;

    public BudgetConstructionIntendedIncumbentSelectId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getEmplid() { return emplid; }
    public void setEmplid(String emplid) { this.emplid = emplid; }

    public String getFinancialObjectCode() { return financialObjectCode; }
    public void setFinancialObjectCode(String financialObjectCode) { this.financialObjectCode = financialObjectCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionIntendedIncumbentSelectId that = (BudgetConstructionIntendedIncumbentSelectId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(emplid, that.emplid) && Objects.equals(financialObjectCode, that.financialObjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, emplid, financialObjectCode);
    }
}
