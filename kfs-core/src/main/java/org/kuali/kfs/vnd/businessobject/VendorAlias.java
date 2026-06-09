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

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.type.YesNoConverter;

/**
 * Alternate name for a Vendor.
 */
@Entity
@Table(name = "PUR_VNDR_ALIAS_T")
@IdClass(VendorAliasId.class)
public class VendorAlias extends PersistableBusinessObjectBase implements MutableInactivatable {

    @Id
    @Column(name = "VNDR_ALIAS_NM")
    private String vendorAliasName;
    @Id
    @Column(name = "VNDR_DTL_ASND_ID")
    private Integer vendorDetailAssignedIdentifier;
    @Id
    @Column(name = "VNDR_HDR_GNRTD_ID")
    private Integer vendorHeaderGeneratedIdentifier;
    @Column(name = "DOBJ_MAINT_CD_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "VNDR_HDR_GNRTD_ID", referencedColumnName = "VNDR_HDR_GNRTD_ID", insertable = false, updatable = false),
        @JoinColumn(name = "VNDR_DTL_ASND_ID", referencedColumnName = "VNDR_DTL_ASND_ID", insertable = false, updatable = false)
    })
    VendorDetail vendorDetail;

    /**
     * Default constructor.
     */
    public VendorAlias() {

    }

    public String getVendorAliasName() {

        return vendorAliasName;
    }

    public void setVendorAliasName(String vendorAliasName) {
        this.vendorAliasName = vendorAliasName;
    }

    public Integer getVendorDetailAssignedIdentifier() {

        return vendorDetailAssignedIdentifier;
    }

    public void setVendorDetailAssignedIdentifier(Integer vendorDetailAssignedIdentifier) {
        this.vendorDetailAssignedIdentifier = vendorDetailAssignedIdentifier;
    }

    public Integer getVendorHeaderGeneratedIdentifier() {

        return vendorHeaderGeneratedIdentifier;
    }

    public void setVendorHeaderGeneratedIdentifier(Integer vendorHeaderGeneratedIdentifier) {
        this.vendorHeaderGeneratedIdentifier = vendorHeaderGeneratedIdentifier;
    }

    public boolean isActive() {

        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public VendorDetail getVendorDetail() {

        return vendorDetail;
    }

    /**
     * Sets the vendorDetail attribute value.
     * 
     * @param vendorDetail The vendorDetail to set.
     * @deprecated
     */
    public void setVendorDetail(VendorDetail vendorDetail) {
        this.vendorDetail = vendorDetail;
    }
}
