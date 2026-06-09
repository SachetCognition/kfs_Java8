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

package org.kuali.kfs.fp.businessobject;

import java.util.LinkedHashMap;

import org.kuali.kfs.sys.businessobject.FiscalYearBasedBusinessObject;
import org.kuali.kfs.sys.businessobject.SystemOptions;
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
 * This class is used to represent a fiscal year function control business object.
 */
@Entity
@Table(name = "FP_FSCL_YR_CTRL_T")
public class FiscalYearFunctionControl extends PersistableBusinessObjectBase implements FiscalYearBasedBusinessObject {

    @Id
    @Column(name = "UNIV_FISCAL_YR")
    private Integer universityFiscalYear;
    @Id
    @Column(name = "FS_FUNC_CTRL_CD")
    private String financialSystemFunctionControlCode;
    @Column(name = "FS_FUNC_ACTIVE_IND")
    private boolean financialSystemFunctionActiveIndicator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FS_FUNC_CTRL_CD", insertable = false, updatable = false)
    private FunctionControlCode functionControl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIV_FISCAL_YR", insertable = false, updatable = false)
    private SystemOptions universityFiscal;

    /**
     * Default constructor.
     */
    public FiscalYearFunctionControl() {

    }

    /**
     * Gets the universityFiscalYear attribute.
     * 
     * @return Returns the universityFiscalYear
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * Sets the universityFiscalYear attribute.
     * 
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }


    /**
     * Gets the financialSystemFunctionControlCode attribute.
     * 
     * @return Returns the financialSystemFunctionControlCode
     */
    public String getFinancialSystemFunctionControlCode() {
        return financialSystemFunctionControlCode;
    }

    /**
     * Sets the financialSystemFunctionControlCode attribute.
     * 
     * @param financialSystemFunctionControlCode The financialSystemFunctionControlCode to set.
     */
    public void setFinancialSystemFunctionControlCode(String financialSystemFunctionControlCode) {
        this.financialSystemFunctionControlCode = financialSystemFunctionControlCode;
    }


    /**
     * Gets the financialSystemFunctionActiveIndicator attribute.
     * 
     * @return Returns the financialSystemFunctionActiveIndicator
     */
    public boolean isFinancialSystemFunctionActiveIndicator() {
        return financialSystemFunctionActiveIndicator;
    }


    /**
     * Sets the financialSystemFunctionActiveIndicator attribute.
     * 
     * @param financialSystemFunctionActiveIndicator The financialSystemFunctionActiveIndicator to set.
     */
    public void setFinancialSystemFunctionActiveIndicator(boolean financialSystemFunctionActiveIndicator) {
        this.financialSystemFunctionActiveIndicator = financialSystemFunctionActiveIndicator;
    }


    /**
     * @return Returns the functionControl.
     */
    public FunctionControlCode getFunctionControl() {
        return functionControl;
    }

    /**
     * @param functionControl The functionControl to set.
     * @deprecated
     */
    public void setFunctionControl(FunctionControlCode functionControl) {
        this.functionControl = functionControl;
    }

    /**
     * Implementing equals since I need contains to behave reasonably.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj != null) {
            if (this.getClass().equals(obj.getClass())) {
                FiscalYearFunctionControl fiscalYearFunctionControl = (FiscalYearFunctionControl) obj;

                if (this.getUniversityFiscalYear().equals(fiscalYearFunctionControl.getUniversityFiscalYear())) {
                    equal = true;
                }
            }
        }

        return equal;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        if (this.universityFiscalYear != null) {
            m.put("universityFiscalYear", this.universityFiscalYear.toString());
        }
        m.put("financialSystemFunctionControlCode", this.financialSystemFunctionControlCode);
        return m;
    }

    /**
     * Gets the universityFiscal attribute.
     * 
     * @return Returns the universityFiscal.
     */
    public SystemOptions getUniversityFiscal() {
        return universityFiscal;
    }

    /**
     * Sets the universityFiscal attribute value.
     * 
     * @param universityFiscal The universityFiscal to set.
     */
    public void setUniversityFiscal(SystemOptions universityFiscal) {
        this.universityFiscal = universityFiscal;
    }

}
