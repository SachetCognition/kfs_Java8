package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionIntendedIncumbentInitializationMoveId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String emplid;

    public BudgetConstructionIntendedIncumbentInitializationMoveId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getEmplid() { return emplid; }
    public void setEmplid(String emplid) { this.emplid = emplid; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionIntendedIncumbentInitializationMoveId that = (BudgetConstructionIntendedIncumbentInitializationMoveId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(emplid, that.emplid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, emplid);
    }
}
