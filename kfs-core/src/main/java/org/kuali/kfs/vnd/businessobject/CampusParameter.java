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

package org.kuali.kfs.vnd.businessobject;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.service.KualiModuleService;
import org.kuali.rice.krad.service.ModuleService;
import org.kuali.rice.location.api.LocationConstants;
import org.kuali.rice.location.framework.campus.CampusEbo;
import org.kuali.rice.location.framework.country.CountryEbo;
import org.kuali.rice.location.framework.state.StateEbo;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.hibernate.type.YesNoConverter;

/**
 * Campus Parameter Business Object. Maintenance document for campus parameters.
 */
@Entity
@Table(name = "PUR_AP_CMP_PARM_T")
public class CampusParameter extends PersistableBusinessObjectBase implements MutableInactivatable {

    @Id
    @Column(name = "CAMPUS_CD")
    protected String campusCode;
    @Column(name = "CMP_PUR_DRCTR_NM")
    protected String campusPurchasingDirectorName;
    @Column(name = "CMP_PUR_DRCTR_TTL")
    protected String campusPurchasingDirectorTitle;
    @Column(name = "CMP_AP_EMAIL_ADDR")
    protected String campusAccountsPayableEmailAddress;
    @Column(name = "PUR_INST_NM")
    protected String purchasingInstitutionName;
    @Column(name = "PUR_DEPT_NM")
    protected String purchasingDepartmentName;
    @Column(name = "PUR_DEPT_LN1_ADDR")
    protected String purchasingDepartmentLine1Address;
    @Column(name = "PUR_DEPT_LN2_ADDR")
    protected String purchasingDepartmentLine2Address;
    @Column(name = "PUR_DEPT_CTY_NM")
    protected String purchasingDepartmentCityName;
    @Column(name = "PUR_DEPT_ST_CD")
    protected String purchasingDepartmentStateCode;
    @Column(name = "PUR_DEPT_ZIP_CD")
    protected String purchasingDepartmentZipCode;
    @Column(name = "PUR_DEPT_CNTRY_CD")
    protected String purchasingDepartmentCountryCode;
    @Column(name = "ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    protected boolean active;

    @Transient
    protected CampusEbo campus;
    @Transient
    protected StateEbo purchasingDepartmentState;
    @Transient
    protected CountryEbo purchasingDepartmentCountry;

    public CampusEbo getCampus() {
        if ( StringUtils.isBlank(campusCode) ) {
            campus = null;
        } else {
            if ( campus == null || !StringUtils.equals( campus.getCode(),campusCode) ) {
                ModuleService moduleService = SpringContext.getBean(KualiModuleService.class).getResponsibleModuleService(CampusEbo.class);
                if ( moduleService != null ) {
                    Map<String,Object> keys = new HashMap<String, Object>(1);
                    keys.put(LocationConstants.PrimaryKeyConstants.CODE, campusCode);
                    campus = moduleService.getExternalizableBusinessObject(CampusEbo.class, keys);
                } else {
                    throw new RuntimeException( "CONFIGURATION ERROR: No responsible module found for EBO class.  Unable to proceed." );
                }
            }
        }
        return campus;
    }

    @Deprecated
    public void setCampus(CampusEbo campus) {
        this.campus = campus;
    }

    public String getCampusAccountsPayableEmailAddress() {
        return campusAccountsPayableEmailAddress;
    }

    public void setCampusAccountsPayableEmailAddress(String campusAccountsPayableEmailAddress) {
        this.campusAccountsPayableEmailAddress = campusAccountsPayableEmailAddress;
    }

    public String getCampusCode() {
        return campusCode;
    }

    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    public String getCampusPurchasingDirectorName() {
        return campusPurchasingDirectorName;
    }

    public void setCampusPurchasingDirectorName(String campusPurchasingDirectorName) {
        this.campusPurchasingDirectorName = campusPurchasingDirectorName;
    }

    public String getCampusPurchasingDirectorTitle() {
        return campusPurchasingDirectorTitle;
    }

    public void setCampusPurchasingDirectorTitle(String campusPurchasingDirectorTitle) {
        this.campusPurchasingDirectorTitle = campusPurchasingDirectorTitle;
    }

    public String getPurchasingDepartmentCityName() {
        return purchasingDepartmentCityName;
    }

    public void setPurchasingDepartmentCityName(String purchasingDepartmentCityName) {
        this.purchasingDepartmentCityName = purchasingDepartmentCityName;
    }

    public CountryEbo getPurchasingDepartmentCountry() {
        if ( StringUtils.isBlank(purchasingDepartmentCountryCode) ) {
            purchasingDepartmentCountry = null;
        } else {
            if ( purchasingDepartmentCountry == null || !StringUtils.equals( purchasingDepartmentCountry.getCode(),purchasingDepartmentCountryCode) ) {
                ModuleService moduleService = SpringContext.getBean(KualiModuleService.class).getResponsibleModuleService(CountryEbo.class);
                if ( moduleService != null ) {
                    Map<String,Object> keys = new HashMap<String, Object>(1);
                    keys.put(LocationConstants.PrimaryKeyConstants.CODE, purchasingDepartmentCountryCode);
                    purchasingDepartmentCountry = moduleService.getExternalizableBusinessObject(CountryEbo.class, keys);
                } else {
                    throw new RuntimeException( "CONFIGURATION ERROR: No responsible module found for EBO class.  Unable to proceed." );
                }
            }
        }
        return purchasingDepartmentCountry;
    }

    @Deprecated
    public void setPurchasingDepartmentCountry(CountryEbo purchasingDepartmentCountry) {
        this.purchasingDepartmentCountry = purchasingDepartmentCountry;
    }

    public String getPurchasingDepartmentCountryCode() {
        return purchasingDepartmentCountryCode;
    }

    public void setPurchasingDepartmentCountryCode(String purchasingDepartmentCountryCode) {
        this.purchasingDepartmentCountryCode = purchasingDepartmentCountryCode;
    }

    public String getPurchasingDepartmentLine1Address() {
        return purchasingDepartmentLine1Address;
    }

    public void setPurchasingDepartmentLine1Address(String purchasingDepartmentLine1Address) {
        this.purchasingDepartmentLine1Address = purchasingDepartmentLine1Address;
    }

    public String getPurchasingDepartmentLine2Address() {
        return purchasingDepartmentLine2Address;
    }

    public void setPurchasingDepartmentLine2Address(String purchasingDepartmentLine2Address) {
        this.purchasingDepartmentLine2Address = purchasingDepartmentLine2Address;
    }

    public String getPurchasingDepartmentName() {
        return purchasingDepartmentName;
    }

    public void setPurchasingDepartmentName(String purchasingDepartmentName) {
        this.purchasingDepartmentName = purchasingDepartmentName;
    }

    public StateEbo getPurchasingDepartmentState() {
        if ( StringUtils.isBlank(purchasingDepartmentStateCode) || StringUtils.isBlank(purchasingDepartmentCountryCode ) ) {
            purchasingDepartmentState = null;
        } else {
            if ( purchasingDepartmentState == null || !StringUtils.equals( purchasingDepartmentState.getCode(),purchasingDepartmentStateCode) || !StringUtils.equals(purchasingDepartmentState.getCountryCode(), purchasingDepartmentCountryCode ) ) {
                ModuleService moduleService = SpringContext.getBean(KualiModuleService.class).getResponsibleModuleService(StateEbo.class);
                if ( moduleService != null ) {
                    Map<String,Object> keys = new HashMap<String, Object>(2);
                    keys.put(LocationConstants.PrimaryKeyConstants.COUNTRY_CODE, purchasingDepartmentCountryCode);
                    keys.put(LocationConstants.PrimaryKeyConstants.CODE, purchasingDepartmentStateCode);
                    purchasingDepartmentState = moduleService.getExternalizableBusinessObject(StateEbo.class, keys);
                } else {
                    throw new RuntimeException( "CONFIGURATION ERROR: No responsible module found for EBO class.  Unable to proceed." );
                }
            }
        }
        return purchasingDepartmentState;
    }

    /**
     * @deprecated
     */
    public void setPurchasingDepartmentState(StateEbo purchasingDepartmentState) {
        this.purchasingDepartmentState = purchasingDepartmentState;
    }

    public String getPurchasingDepartmentStateCode() {
        return purchasingDepartmentStateCode;
    }

    public void setPurchasingDepartmentStateCode(String purchasingDepartmentStateCode) {
        this.purchasingDepartmentStateCode = purchasingDepartmentStateCode;
    }

    public String getPurchasingDepartmentZipCode() {
        return purchasingDepartmentZipCode;
    }

    public void setPurchasingDepartmentZipCode(String purchasingDepartmentZipCode) {
        this.purchasingDepartmentZipCode = purchasingDepartmentZipCode;
    }

    public String getPurchasingInstitutionName() {
        return purchasingInstitutionName;
    }

    public void setPurchasingInstitutionName(String purchasingInstitutionName) {
        this.purchasingInstitutionName = purchasingInstitutionName;
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
