package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class PositionObjectBenefitId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String financialObjectCode;
    private String financialObjectBenefitsTypeCode;

    public PositionObjectBenefitId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionObjectBenefitId other = (PositionObjectBenefitId) o;
        return Objects.equals(universityFiscalYear, other.universityFiscalYear) && Objects.equals(chartOfAccountsCode, other.chartOfAccountsCode) && Objects.equals(financialObjectCode, other.financialObjectCode) && Objects.equals(financialObjectBenefitsTypeCode, other.financialObjectBenefitsTypeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, chartOfAccountsCode, financialObjectCode, financialObjectBenefitsTypeCode);
    }
}
