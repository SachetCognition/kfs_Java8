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
import org.kuali.rice.core.api.util.type.KualiDecimal;
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
 * This class is used to represent a Travel Per Diem business object.
 */
@Entity
@Table(name = "FP_DV_DIEM_T")
public class TravelPerDiem extends PersistableBusinessObjectBase implements FiscalYearBasedBusinessObject {
    @Id
    @Column(name = "UNIV_FISCAL_YR")
    protected Integer universityFiscalYear;
    @Id
    @Column(name = "DV_DIEM_CNTRY_NM")
    protected String perDiemCountryName;
    @Column(name = "DV_DIEM_RT")
    protected KualiDecimal perDiemRate;
    @Column(name = "DV_DIEM_CNTRY_TXT")
    protected String perDiemCountryText;

    protected transient SystemOptions fiscalYear;

    /**
     * Default no-arg constructor.
     */
    public TravelPerDiem() {

    }

    /**
     * @return Returns the fiscalYear.
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * @param fiscalYear The fiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer fiscalYear) {
        this.universityFiscalYear = fiscalYear;
    }

    /**
     * @return Returns the perDiemCountryName.
     */
    public String getPerDiemCountryName() {
        return perDiemCountryName;
    }

    /**
     * @param perDiemCountryName The perDiemCountryName to set.
     */
    public void setPerDiemCountryName(String perDiemCountryName) {
        this.perDiemCountryName = perDiemCountryName;
    }

    /**
     * @return Returns the perDiemCountryText.
     */
    public String getPerDiemCountryText() {
        return perDiemCountryText;
    }

    /**
     * @param perDiemCountryText The perDiemCountryText to set.
     */
    public void setPerDiemCountryText(String perDiemCountryText) {
        this.perDiemCountryText = perDiemCountryText;
    }

    /**
     * @return Returns the perDiemRate.
     */
    public KualiDecimal getPerDiemRate() {
        return perDiemRate;
    }

    /**
     * @param perDiemRate The perDiemRate to set.
     */
    public void setPerDiemRate(KualiDecimal perDiemRate) {
        this.perDiemRate = perDiemRate;
    }

    /**
     * Gets the fiscalYear attribute.
     * 
     * @return Returns the fiscalYear.
     */
    public SystemOptions getFiscalYear() {
        return fiscalYear;
    }

    /**
     * Sets the fiscalYear attribute value.
     * 
     * @param fiscalYear The fiscalYear to set.
     */
    public void setFiscalYear(SystemOptions fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    @SuppressWarnings("rawtypes")
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("perDiemCountryName", this.perDiemCountryName);
        return m;
    }
}
