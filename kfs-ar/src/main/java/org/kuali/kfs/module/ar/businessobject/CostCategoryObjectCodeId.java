package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CostCategoryObjectCodeId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String categoryCode;
    private String chartOfAccountsCode;
    private String financialObjectCode;

    public CostCategoryObjectCodeId() {}

    public CostCategoryObjectCodeId(String categoryCode, String chartOfAccountsCode, String financialObjectCode) {
        this.categoryCode = categoryCode;
        this.chartOfAccountsCode = chartOfAccountsCode;
        this.financialObjectCode = financialObjectCode;
    }

    public String getCategoryCode() { return categoryCode; }
    public void setCategoryCode(String categoryCode) { this.categoryCode = categoryCode; }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getFinancialObjectCode() { return financialObjectCode; }
    public void setFinancialObjectCode(String financialObjectCode) { this.financialObjectCode = financialObjectCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostCategoryObjectCodeId that = (CostCategoryObjectCodeId) o;
        return Objects.equals(categoryCode, that.categoryCode) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(financialObjectCode, that.financialObjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryCode, chartOfAccountsCode, financialObjectCode);
    }
}
