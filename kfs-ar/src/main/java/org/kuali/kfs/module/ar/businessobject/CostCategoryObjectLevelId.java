package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CostCategoryObjectLevelId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String categoryCode;
    private String chartOfAccountsCode;
    private String financialObjectLevelCode;

    public CostCategoryObjectLevelId() {}

    public CostCategoryObjectLevelId(String categoryCode, String chartOfAccountsCode, String financialObjectLevelCode) {
        this.categoryCode = categoryCode;
        this.chartOfAccountsCode = chartOfAccountsCode;
        this.financialObjectLevelCode = financialObjectLevelCode;
    }

    public String getCategoryCode() { return categoryCode; }
    public void setCategoryCode(String categoryCode) { this.categoryCode = categoryCode; }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getFinancialObjectLevelCode() { return financialObjectLevelCode; }
    public void setFinancialObjectLevelCode(String financialObjectLevelCode) { this.financialObjectLevelCode = financialObjectLevelCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostCategoryObjectLevelId that = (CostCategoryObjectLevelId) o;
        return Objects.equals(categoryCode, that.categoryCode) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(financialObjectLevelCode, that.financialObjectLevelCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryCode, chartOfAccountsCode, financialObjectLevelCode);
    }
}
