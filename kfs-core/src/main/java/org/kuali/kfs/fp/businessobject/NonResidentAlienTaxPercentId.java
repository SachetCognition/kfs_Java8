package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class NonResidentAlienTaxPercentId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String incomeClassCode;
    private String incomeTaxTypeCode;
    private KualiDecimal incomeTaxPercent;

    public NonResidentAlienTaxPercentId() {}

    public NonResidentAlienTaxPercentId(String incomeClassCode, String incomeTaxTypeCode, KualiDecimal incomeTaxPercent) {
        this.incomeClassCode = incomeClassCode;
        this.incomeTaxTypeCode = incomeTaxTypeCode;
        this.incomeTaxPercent = incomeTaxPercent;
    }

    public String getIncomeClassCode() { return incomeClassCode; }
    public void setIncomeClassCode(String incomeClassCode) { this.incomeClassCode = incomeClassCode; }

    public String getIncomeTaxTypeCode() { return incomeTaxTypeCode; }
    public void setIncomeTaxTypeCode(String incomeTaxTypeCode) { this.incomeTaxTypeCode = incomeTaxTypeCode; }

    public KualiDecimal getIncomeTaxPercent() { return incomeTaxPercent; }
    public void setIncomeTaxPercent(KualiDecimal incomeTaxPercent) { this.incomeTaxPercent = incomeTaxPercent; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonResidentAlienTaxPercentId that = (NonResidentAlienTaxPercentId) o;
        return Objects.equals(incomeClassCode, that.incomeClassCode) && Objects.equals(incomeTaxTypeCode, that.incomeTaxTypeCode) && Objects.equals(incomeTaxPercent, that.incomeTaxPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incomeClassCode, incomeTaxTypeCode, incomeTaxPercent);
    }
}
