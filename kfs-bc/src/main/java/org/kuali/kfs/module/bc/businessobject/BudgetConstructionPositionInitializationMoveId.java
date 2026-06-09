package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionPositionInitializationMoveId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String positionNumber;
    private Integer universityFiscalYear;

    public BudgetConstructionPositionInitializationMoveId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getPositionNumber() { return positionNumber; }
    public void setPositionNumber(String positionNumber) { this.positionNumber = positionNumber; }

    public Integer getUniversityFiscalYear() { return universityFiscalYear; }
    public void setUniversityFiscalYear(Integer universityFiscalYear) { this.universityFiscalYear = universityFiscalYear; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionPositionInitializationMoveId that = (BudgetConstructionPositionInitializationMoveId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(positionNumber, that.positionNumber) && Objects.equals(universityFiscalYear, that.universityFiscalYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, positionNumber, universityFiscalYear);
    }
}
