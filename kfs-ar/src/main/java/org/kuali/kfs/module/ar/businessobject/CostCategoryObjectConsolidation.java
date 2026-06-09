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
import org.kuali.kfs.coa.businessobject.ObjectConsolidation;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@IdClass(CostCategoryObjectConsolidationId.class)
@Table(name = "AR_CST_CTGRY_FIN_CONSOLDTN_T")
public class CostCategoryObjectConsolidation extends PersistableBusinessObjectBase implements CostCategoryDetail {
    @Id
    @Column(name = "CTGRY_CD")
    private String categoryCode;
    @Id
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;
    @Id
    @Column(name = "FIN_CONS_OBJ_CD")
    private String finConsolidationObjectCode;
    @Column(name = "ACTV_IND")
    private boolean active;

    private Chart chart;
    private ObjectConsolidation objectConsolidation;

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
    public String getFinConsolidationObjectCode() {
        return finConsolidationObjectCode;
    }
    public void setFinConsolidationObjectCode(String finConsolidationObjectCode) {
        this.finConsolidationObjectCode = finConsolidationObjectCode;
    }
    public Chart getChart() {
        return chart;
    }
    public void setChart(Chart chart) {
        this.chart = chart;
    }
    public ObjectConsolidation getObjectConsolidation() {
        return objectConsolidation;
    }
    public void setObjectConsolidation(ObjectConsolidation objectConsolidation) {
        this.objectConsolidation = objectConsolidation;
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
