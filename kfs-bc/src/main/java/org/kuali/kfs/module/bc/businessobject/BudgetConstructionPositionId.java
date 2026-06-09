package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionPositionId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String positionNumber;
    private Integer universityFiscalYear;

    public BudgetConstructionPositionId() {}

    public String getPositionNumber() { return positionNumber; }
    public void setPositionNumber(String positionNumber) { this.positionNumber = positionNumber; }

    public Integer getUniversityFiscalYear() { return universityFiscalYear; }
    public void setUniversityFiscalYear(Integer universityFiscalYear) { this.universityFiscalYear = universityFiscalYear; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionPositionId that = (BudgetConstructionPositionId) o;
        return Objects.equals(positionNumber, that.positionNumber) && Objects.equals(universityFiscalYear, that.universityFiscalYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionNumber, universityFiscalYear);
    }
}
