/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 * 
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.module.ar.businessobject;

import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectLevel;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * An object level associated with a contracts & grants category
 */
@Entity
@IdClass(CostCategoryObjectLevelId.class)
@Table(name = "AR_CST_CTGRY_FIN_OBJ_LEVEL_T")
public class CostCategoryObjectLevel extends PersistableBusinessObjectBase implements CostCategoryDetail {
    @Id
    @Column(name = "CTGRY_CD")
    private String categoryCode;
    @Id
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;
    @Id
    @Column(name = "FIN_OBJ_LEVEL_CD")
    private String financialObjectLevelCode;
    @Column(name = "ACTV_IND")
    private boolean active;

    private Chart chart;
    private ObjectLevel objectLevel;

    @Override
    public String getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    @Override
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }
    public String getFinancialObjectLevelCode() {
        return financialObjectLevelCode;
    }
    public void setFinancialObjectLevelCode(String financialObjectLevelCode) {
        this.financialObjectLevelCode = financialObjectLevelCode;
    }
    public Chart getChart() {
        return chart;
    }
    /**
     * @deprecated only ORM should call this method
     */
    @Deprecated
    public void setChart(Chart chart) {
        this.chart = chart;
    }
    public ObjectLevel getObjectLevel() {
        return objectLevel;
    }
    /**
     * @deprecated only ORM should call this method
     */
    @Deprecated
    public void setObjectLevel(ObjectLevel objectLevel) {
        this.objectLevel = objectLevel;
    }
    @Override
    public boolean isActive() {
        return active;
    }
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
