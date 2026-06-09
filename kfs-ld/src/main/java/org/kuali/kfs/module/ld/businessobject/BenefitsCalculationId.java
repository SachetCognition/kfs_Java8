package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BenefitsCalculationId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String universityFiscalYear;
    private String chartOfAccountsCode;
    private String positionBenefitTypeCode;

    public BenefitsCalculationId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BenefitsCalculationId other = (BenefitsCalculationId) o;
        return Objects.equals(universityFiscalYear, other.universityFiscalYear) && Objects.equals(chartOfAccountsCode, other.chartOfAccountsCode) && Objects.equals(positionBenefitTypeCode, other.positionBenefitTypeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, chartOfAccountsCode, positionBenefitTypeCode);
    }
}
