package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionPayRateHoldingId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String emplid;
    private String positionNumber;
    private String principalId;

    public BudgetConstructionPayRateHoldingId() {}

    public String getEmplid() { return emplid; }
    public void setEmplid(String emplid) { this.emplid = emplid; }

    public String getPositionNumber() { return positionNumber; }
    public void setPositionNumber(String positionNumber) { this.positionNumber = positionNumber; }

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionPayRateHoldingId that = (BudgetConstructionPayRateHoldingId) o;
        return Objects.equals(emplid, that.emplid) && Objects.equals(positionNumber, that.positionNumber) && Objects.equals(principalId, that.principalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emplid, positionNumber, principalId);
    }
}
