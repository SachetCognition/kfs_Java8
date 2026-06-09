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

import java.util.LinkedHashMap;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Phone Types for Vendors. These types may be based on technical distinctions, the Vendor's organization, or the phone's intended
 * purpose.
 */
@Entity
@Table(name = "PUR_PHN_TYP_T")
public class PhoneType extends PersistableBusinessObjectBase implements MutableInactivatable {

    @Id
    @Column(name = "VNDR_PHN_TYP_CD")
    private String vendorPhoneTypeCode;
    @Column(name = "VNDR_PHN_TYP_DESC")
    private String vendorPhoneTypeDescription;
    @Column(name = "DOBJ_MAINT_CD_ACTV_IND")
    private boolean active;

    /**
     * Default constructor.
     */
    public PhoneType() {

    }

    public String getVendorPhoneTypeCode() {

        return vendorPhoneTypeCode;
    }

    public void setVendorPhoneTypeCode(String vendorPhoneTypeCode) {
        this.vendorPhoneTypeCode = vendorPhoneTypeCode;
    }

    public String getVendorPhoneTypeDescription() {

        return vendorPhoneTypeDescription;
    }

    public void setVendorPhoneTypeDescription(String vendorPhoneTypeDescription) {
        this.vendorPhoneTypeDescription = vendorPhoneTypeDescription;
    }

    public boolean isActive() {

        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("vendorPhoneTypeCode", this.vendorPhoneTypeCode);

        return m;
    }
}
