package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class LaborObjectId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String universityFiscalYear;
    private String chartOfAccountsCode;
    private String financialObjectCode;

    public LaborObjectId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaborObjectId other = (LaborObjectId) o;
        return Objects.equals(universityFiscalYear, other.universityFiscalYear) && Objects.equals(chartOfAccountsCode, other.chartOfAccountsCode) && Objects.equals(financialObjectCode, other.financialObjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, chartOfAccountsCode, financialObjectCode);
    }
}
