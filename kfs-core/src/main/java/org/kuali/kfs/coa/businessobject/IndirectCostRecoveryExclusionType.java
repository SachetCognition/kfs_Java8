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

package org.kuali.kfs.coa.businessobject;

import java.util.LinkedHashMap;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 */
@Entity
@Table(name = "CA_ICR_EXCL_TYPE_T")
public class IndirectCostRecoveryExclusionType extends PersistableBusinessObjectBase implements MutableInactivatable {

    @Id
    @Column(name = "ACCT_ICR_TYP_CD")
    private String accountIndirectCostRecoveryTypeCode;
    @Id
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;
    @Id
    @Column(name = "FIN_OBJECT_CD")
    private String financialObjectCode;
    @Column(name = "ACCT_ICR_EXCL_TYP_ACTV_IND")
    private boolean active; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false)
    private Chart chart;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCT_ICR_TYP_CD", insertable = false, updatable = false)
    private IndirectCostRecoveryType indirectCostRecoveryType;
    @ManyToOne(fetch = FetchType.LAZY)
    private ObjectCode objectCodeCurrent;

    public IndirectCostRecoveryExclusionType() {
        super();
    }

    /**
     * Gets the accountIndirectCostRecoveryTypeCode attribute.
     * 
     * @return Returns the accountIndirectCostRecoveryTypeCode
     */
    public String getAccountIndirectCostRecoveryTypeCode() {
        return accountIndirectCostRecoveryTypeCode;
    }

    /**
     * Sets the accountIndirectCostRecoveryTypeCode attribute.
     * 
     * @param accountIndirectCostRecoveryTypeCode The accountIndirectCostRecoveryTypeCode to set.
     */
    public void setAccountIndirectCostRecoveryTypeCode(String accountIndirectCostRecoveryTypeCode) {
        this.accountIndirectCostRecoveryTypeCode = accountIndirectCostRecoveryTypeCode;
    }


    /**
     * Gets the chartOfAccountsCode attribute.
     * 
     * @return Returns the chartOfAccountsCode
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * Sets the chartOfAccountsCode attribute.
     * 
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }


    /**
     * Gets the financialObjectCode attribute.
     * 
     * @return Returns the financialObjectCode
     */
    public String getFinancialObjectCode() {
        return financialObjectCode;
    }

    /**
     * Sets the financialObjectCode attribute.
     * 
     * @param financialObjectCode The financialObjectCode to set.
     */
    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }

    /**
     * Gets the active attribute. 
     * @return Returns the active.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active attribute value.
     * @param active The active to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets the chart attribute.
     * 
     * @return Returns the chart
     */
    public Chart getChart() {
        return chart;
    }

    /**
     * Sets the chart attribute.
     * 
     * @param chart The chart to set.
     * @deprecated
     */
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    /**
     * @return Returns the indirectCostRecoveryType.
     */
    public IndirectCostRecoveryType getIndirectCostRecoveryType() {
        return indirectCostRecoveryType;
    }

    /**
     * @param indirectCostRecoveryType The indirectCostRecoveryType to set.
     * @deprecated
     */
    public void setIndirectCostRecoveryType(IndirectCostRecoveryType indirectCostRecoveryType) {
        this.indirectCostRecoveryType = indirectCostRecoveryType;
    }

    /**
     * @return Returns the objectCode.
     */
    public ObjectCode getObjectCodeCurrent() {
        return objectCodeCurrent;
    }

    /**
     * @param objectCode The objectCode to set.
     * @deprecated
     */
    public void setObjectCodeCurrent(ObjectCode objectCodeCurrent) {
        this.objectCodeCurrent = objectCodeCurrent;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("accountIndirectCostRecoveryTypeCode", this.accountIndirectCostRecoveryTypeCode);
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("financialObjectCode", this.financialObjectCode);
        return m;
    }


}
