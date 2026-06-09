package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class FiscalYearFunctionControlId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer universityFiscalYear;
    private String financialSystemFunctionControlCode;

    public FiscalYearFunctionControlId() {}

    public FiscalYearFunctionControlId(Integer universityFiscalYear, String financialSystemFunctionControlCode) {
        this.universityFiscalYear = universityFiscalYear;
        this.financialSystemFunctionControlCode = financialSystemFunctionControlCode;
    }

    public Integer getUniversityFiscalYear() { return universityFiscalYear; }
    public void setUniversityFiscalYear(Integer universityFiscalYear) { this.universityFiscalYear = universityFiscalYear; }

    public String getFinancialSystemFunctionControlCode() { return financialSystemFunctionControlCode; }
    public void setFinancialSystemFunctionControlCode(String financialSystemFunctionControlCode) { this.financialSystemFunctionControlCode = financialSystemFunctionControlCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiscalYearFunctionControlId that = (FiscalYearFunctionControlId) o;
        return Objects.equals(universityFiscalYear, that.universityFiscalYear) && Objects.equals(financialSystemFunctionControlCode, that.financialSystemFunctionControlCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, financialSystemFunctionControlCode);
    }
}
