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

package org.kuali.kfs.module.ld.businessobject;

import java.util.LinkedHashMap;

import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.integration.ld.LaborLedgerObject;
import org.kuali.kfs.integration.ld.LaborLedgerPositionObjectGroup;
import org.kuali.kfs.sys.businessobject.FiscalYearBasedBusinessObject;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.kuali.kfs.module.ld.persistence.converter.OjbCharBooleanConverter;

@Entity
@Table(name = "LD_LABOR_OBJ_T")
@IdClass(LaborObjectId.class)
/**
 * Labor business object for LaborObject.
 */
public class LaborObject extends PersistableBusinessObjectBase implements LaborLedgerObject, MutableInactivatable, FiscalYearBasedBusinessObject {
    @Id

    @Column(name = "UNIV_FISCAL_YR")

    private Integer universityFiscalYear;
    @Id

    @Column(name = "FIN_COA_CD")

    private String chartOfAccountsCode;
    @Id

    @Column(name = "FIN_OBJECT_CD")

    private String financialObjectCode;
    @Column(name = "DTL_POS_REQ_CD")

    @Convert(converter = OjbCharBooleanConverter.class)

    private boolean detailPositionRequiredIndicator;
    @Column(name = "FIN_OBJ_HRS_REQ_CD")

    @Convert(converter = OjbCharBooleanConverter.class)

    private boolean financialObjectHoursRequiredIndicator;
    @Column(name = "FIN_OBJ_PAY_TYP_CD")

    private String financialObjectPayTypeCode;
    @Column(name = "FINOBJ_FRNGSLRY_CD")

    private String financialObjectFringeOrSalaryCode;
    @Column(name = "POS_OBJ_GRP_CD")

    private String positionObjectGroupCode;
    @Column(name = "ACTV_IND")

    @Convert(converter = OjbCharBooleanConverter.class)

    private boolean active;
    
    @ManyToOne(fetch = FetchType.LAZY)

    
    @JoinColumns({

    
        @JoinColumn(name = "UNIV_FISCAL_YR", referencedColumnName = "UNIV_FISCAL_YR", insertable = false, updatable = false),

    
        @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false),

    
        @JoinColumn(name = "FIN_OBJECT_CD", referencedColumnName = "FIN_OBJECT_CD", insertable = false, updatable = false)

    
    })

    
    private ObjectCode financialObject;
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false)

    private Chart chartOfAccounts;
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "POS_OBJ_GRP_CD", insertable = false, updatable = false)

    private PositionObjectGroup positionObjectGroup;
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "UNIV_FISCAL_YR", insertable = false, updatable = false)

    private SystemOptions option;

    /**
     * Default constructor.
     */
    public LaborObject() {

    }

    /**
     * Gets the universityFiscalYear
     * 
     * @return Returns the universityFiscalYear
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * Sets the universityFiscalYear
     * 
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    /**
     * Gets the chartOfAccountsCode
     * 
     * @return Returns the chartOfAccountsCode
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * Sets the chartOfAccountsCode
     * 
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    /**
     * Gets the financialObjectCode
     * 
     * @return Returns the financialObjectCode
     */
    public String getFinancialObjectCode() {
        return financialObjectCode;
    }

    /**
     * Sets the financialObjectCode
     * 
     * @param financialObjectCode The financialObjectCode to set.
     */
    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }

    /**
     * Gets the detailPositionRequiredIndicator
     * 
     * @return Returns the detailPositionRequiredIndicator
     */
    public boolean isDetailPositionRequiredIndicator() {
        return detailPositionRequiredIndicator;
    }

    /**
     * Sets the detailPositionRequiredIndicator
     * 
     * @param detailPositionRequiredIndicator The detailPositionRequiredIndicator to set.
     */
    public void setDetailPositionRequiredIndicator(boolean detailPositionRequiredIndicator) {
        this.detailPositionRequiredIndicator = detailPositionRequiredIndicator;
    }

    /**
     * Gets the financialObjectHoursRequiredIndicator
     * 
     * @return Returns the financialObjectHoursRequiredIndicator
     */
    public boolean isFinancialObjectHoursRequiredIndicator() {
        return financialObjectHoursRequiredIndicator;
    }

    /**
     * Sets the financialObjectHoursRequiredIndicator
     * 
     * @param financialObjectHoursRequiredIndicator The financialObjectHoursRequiredIndicator to set.
     */
    public void setFinancialObjectHoursRequiredIndicator(boolean financialObjectHoursRequiredIndicator) {
        this.financialObjectHoursRequiredIndicator = financialObjectHoursRequiredIndicator;
    }

    /**
     * Gets the financialObjectPayTypeCode
     * 
     * @return Returns the financialObjectPayTypeCode
     */
    public String getFinancialObjectPayTypeCode() {
        return financialObjectPayTypeCode;
    }

    /**
     * Sets the financialObjectPayTypeCode
     * 
     * @param financialObjectPayTypeCode The financialObjectPayTypeCode to set.
     */
    public void setFinancialObjectPayTypeCode(String financialObjectPayTypeCode) {
        this.financialObjectPayTypeCode = financialObjectPayTypeCode;
    }

    /**
     * Gets the financialObjectFringeOrSalaryCode
     * 
     * @return Returns the financialObjectFringeOrSalaryCode
     */
    public String getFinancialObjectFringeOrSalaryCode() {
        return financialObjectFringeOrSalaryCode;
    }

    /**
     * Sets the financialObjectFringeOrSalaryCode
     * 
     * @param financialObjectFringeOrSalaryCode The financialObjectFringeOrSalaryCode to set.
     */
    public void setFinancialObjectFringeOrSalaryCode(String financialObjectFringeOrSalaryCode) {
        this.financialObjectFringeOrSalaryCode = financialObjectFringeOrSalaryCode;
    }

    /**
     * Gets the positionObjectGroupCode
     * 
     * @return Returns the positionObjectGroupCode
     */
    public String getPositionObjectGroupCode() {
        return positionObjectGroupCode;
    }

    /**
     * Sets the positionObjectGroupCode
     * 
     * @param positionObjectGroupCode The positionObjectGroupCode to set.
     */
    public void setPositionObjectGroupCode(String positionObjectGroupCode) {
        this.positionObjectGroupCode = positionObjectGroupCode;
    }

    /**
     * Gets the financialObject
     * 
     * @return Returns the financialObject
     */
    public ObjectCode getFinancialObject() {
        return financialObject;
    }

    /**
     * Sets the financialObject
     * 
     * @param financialObject The financialObject to set.
     */
    @Deprecated
    public void setFinancialObject(ObjectCode financialObject) {
        this.financialObject = financialObject;
    }

    /**
     * Gets the chartOfAccounts
     * 
     * @return Returns the chartOfAccounts
     */
    public Chart getChartOfAccounts() {
        return chartOfAccounts;
    }

    /**
     * Sets the chartOfAccounts
     * 
     * @param chartOfAccounts The chartOfAccounts to set.
     */
    @Deprecated
    public void setChartOfAccounts(Chart chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
    }

    /**
     * Gets the positionObjectGroup attribute.
     * 
     * @return Returns the positionObjectGroup.
     */
    public PositionObjectGroup getPositionObjectGroup() {
        return positionObjectGroup;
    }

    /**
     * Sets the positionObjectGroup attribute value.
     * 
     * @param positionObjectGroup The positionObjectGroup to set.
     */
    @Deprecated
    public void setPositionObjectGroup(PositionObjectGroup positionObjectGroup) {
        this.positionObjectGroup = positionObjectGroup;
    }

    /**
     * @see org.kuali.kfs.bo.LaborLedgerObject#getLaborLedgerPositionObjectGroup()
     */
    public LaborLedgerPositionObjectGroup getLaborLedgerPositionObjectGroup() {
        return this.positionObjectGroup;
    }

    /**
     * @see org.kuali.kfs.bo.LaborLedgerObject#setLaborLedgerPositionObjectGroup(org.kuali.kfs.bo.LaborLedgerPositionObjectGroup)
     */
    @Deprecated
    public void setLaborLedgerPositionObjectGroup(LaborLedgerPositionObjectGroup laborLedgerPositionObjectGroup) {
        this.positionObjectGroup = (PositionObjectGroup) laborLedgerPositionObjectGroup;
    }

    /**
     * Gets the option
     * 
     * @return Returns the option.
     */
    public SystemOptions getOption() {
        return option;
    }

    /**
     * Sets the option
     * 
     * @param option The option to set.
     */
    public void setOption(SystemOptions option) {
        this.option = option;
    }

    /**
     * Gets the active attribute.
     * 
     * @return Returns the active.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active attribute value.
     * 
     * @param active The active to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * This method (a hack by any other name...) returns a string so that an Labor Object Code can have a link to view its own
     * inquiry page after a look up
     * 
     * @return the String "View Labor Object Code"
     */
    public String getLaborObjectCodeViewer() {
        return "View Labor Object Code";
    }

    /**
     * construct the key list of the business object.
     * 
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        if (this.universityFiscalYear != null) {
            m.put("universityFiscalYear", this.universityFiscalYear.toString());
        }
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("financialObjectCode", this.financialObjectCode);

        return m;
    }
}
