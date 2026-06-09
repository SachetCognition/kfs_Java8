package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionSubFundPickId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String subFundGroupCode;

    public BudgetConstructionSubFundPickId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getSubFundGroupCode() { return subFundGroupCode; }
    public void setSubFundGroupCode(String subFundGroupCode) { this.subFundGroupCode = subFundGroupCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionSubFundPickId that = (BudgetConstructionSubFundPickId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(subFundGroupCode, that.subFundGroupCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, subFundGroupCode);
    }
}
