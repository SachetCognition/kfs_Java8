package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CostCategoryObjectConsolidationId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String categoryCode;
    private String chartOfAccountsCode;
    private String finConsolidationObjectCode;

    public CostCategoryObjectConsolidationId() {}

    public CostCategoryObjectConsolidationId(String categoryCode, String chartOfAccountsCode, String finConsolidationObjectCode) {
        this.categoryCode = categoryCode;
        this.chartOfAccountsCode = chartOfAccountsCode;
        this.finConsolidationObjectCode = finConsolidationObjectCode;
    }

    public String getCategoryCode() { return categoryCode; }
    public void setCategoryCode(String categoryCode) { this.categoryCode = categoryCode; }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getFinConsolidationObjectCode() { return finConsolidationObjectCode; }
    public void setFinConsolidationObjectCode(String finConsolidationObjectCode) { this.finConsolidationObjectCode = finConsolidationObjectCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostCategoryObjectConsolidationId that = (CostCategoryObjectConsolidationId) o;
        return Objects.equals(categoryCode, that.categoryCode) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(finConsolidationObjectCode, that.finConsolidationObjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryCode, chartOfAccountsCode, finConsolidationObjectCode);
    }
}
