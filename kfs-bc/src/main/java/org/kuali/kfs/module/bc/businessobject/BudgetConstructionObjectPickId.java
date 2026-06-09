package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionObjectPickId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String financialObjectCode;

    public BudgetConstructionObjectPickId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getFinancialObjectCode() { return financialObjectCode; }
    public void setFinancialObjectCode(String financialObjectCode) { this.financialObjectCode = financialObjectCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionObjectPickId that = (BudgetConstructionObjectPickId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(financialObjectCode, that.financialObjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, financialObjectCode);
    }
}
