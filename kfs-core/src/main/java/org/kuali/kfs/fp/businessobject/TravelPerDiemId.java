package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class TravelPerDiemId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer universityFiscalYear;
    private String perDiemCountryName;

    public TravelPerDiemId() {}

    public TravelPerDiemId(Integer universityFiscalYear, String perDiemCountryName) {
        this.universityFiscalYear = universityFiscalYear;
        this.perDiemCountryName = perDiemCountryName;
    }

    public Integer getUniversityFiscalYear() { return universityFiscalYear; }
    public void setUniversityFiscalYear(Integer universityFiscalYear) { this.universityFiscalYear = universityFiscalYear; }

    public String getPerDiemCountryName() { return perDiemCountryName; }
    public void setPerDiemCountryName(String perDiemCountryName) { this.perDiemCountryName = perDiemCountryName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelPerDiemId that = (TravelPerDiemId) o;
        return Objects.equals(universityFiscalYear, that.universityFiscalYear) && Objects.equals(perDiemCountryName, that.perDiemCountryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, perDiemCountryName);
    }
}
