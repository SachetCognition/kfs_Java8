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

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.hibernate.type.YesNoConverter;

/**
 * Generic Phone Numbers for Vendors, as opposed to <code>VendorContactPhoneNumber</code> instances, which are specific to the
 * Contact.
 * 
 * @see org.kuali.kfs.vnd.businessobject.VendorContactPhoneNumber
 */
@Entity
@Table(name = "PUR_VNDR_PHN_NBR_T")
public class VendorPhoneNumber extends PersistableBusinessObjectBase implements MutableInactivatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VNDR_PHN_GNRTD_ID")
    private Integer vendorPhoneGeneratedIdentifier;
    @Column(name = "VNDR_HDR_GNRTD_ID")
    private Integer vendorHeaderGeneratedIdentifier;
    @Column(name = "VNDR_DTL_ASND_ID")
    private Integer vendorDetailAssignedIdentifier;
    @Column(name = "VNDR_PHN_TYP_CD")
    private String vendorPhoneTypeCode;
    @Column(name = "VNDR_PHN_NBR")
    private String vendorPhoneNumber;
    @Column(name = "VNDR_PHN_EXTNS_NBR")
    private String vendorPhoneExtensionNumber;
    @Column(name = "DOBJ_MAINT_CD_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "VNDR_HDR_GNRTD_ID", referencedColumnName = "VNDR_HDR_GNRTD_ID", insertable = false, updatable = false),
        @JoinColumn(name = "VNDR_DTL_ASND_ID", referencedColumnName = "VNDR_DTL_ASND_ID", insertable = false, updatable = false)
    })
    private VendorDetail vendorDetail;
    @Transient
    private VendorContact vendorContact;
    @Transient
    private VendorAddress vendorAddress;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VNDR_PHN_TYP_CD", insertable = false, updatable = false)
    private PhoneType vendorPhoneType;

    /**
     * Default constructor.
     */
    public VendorPhoneNumber() {

    }

    public Integer getVendorPhoneGeneratedIdentifier() {

        return vendorPhoneGeneratedIdentifier;
    }

    public void setVendorPhoneGeneratedIdentifier(Integer vendorPhoneGeneratedIdentifier) {
        this.vendorPhoneGeneratedIdentifier = vendorPhoneGeneratedIdentifier;
    }

    public Integer getVendorHeaderGeneratedIdentifier() {

        return vendorHeaderGeneratedIdentifier;
    }

    public void setVendorHeaderGeneratedIdentifier(Integer vendorHeaderGeneratedIdentifier) {
        this.vendorHeaderGeneratedIdentifier = vendorHeaderGeneratedIdentifier;
    }

    public Integer getVendorDetailAssignedIdentifier() {

        return vendorDetailAssignedIdentifier;
    }

    public void setVendorDetailAssignedIdentifier(Integer vendorDetailAssignedIdentifier) {
        this.vendorDetailAssignedIdentifier = vendorDetailAssignedIdentifier;
    }

    public String getVendorPhoneTypeCode() {

        return vendorPhoneTypeCode;
    }

    public void setVendorPhoneTypeCode(String vendorPhoneTypeCode) {
        this.vendorPhoneTypeCode = vendorPhoneTypeCode;
    }

    public String getVendorPhoneNumber() {

        return vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public String getVendorPhoneExtensionNumber() {

        return vendorPhoneExtensionNumber;
    }

    public void setVendorPhoneExtensionNumber(String vendorPhoneExtensionNumber) {
        this.vendorPhoneExtensionNumber = vendorPhoneExtensionNumber;
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
     * Sets the vendorDetail attribute.
     * 
     * @param vendorDetail The vendorDetail to set.
     * @deprecated
     */
    public void setVendorDetail(VendorDetail vendorDetail) {
        this.vendorDetail = vendorDetail;
    }

    public VendorContact getVendorContact() {

        return vendorContact;
    }

    /**
     * Sets the vendorContact attribute.
     * 
     * @param vendorContact The vendorContact to set.
     * @deprecated
     */
    public void setVendorContact(VendorContact vendorContact) {
        this.vendorContact = vendorContact;
    }

    public VendorAddress getVendorAddress() {

        return vendorAddress;
    }

    /**
     * Sets the vendorAddress attribute.
     * 
     * @param vendorAddress The vendorAddress to set.
     * @deprecated
     */
    public void setVendorAddress(VendorAddress vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public PhoneType getVendorPhoneType() {

        return vendorPhoneType;
    }

    /**
     * Sets the vendorPhoneType attribute.
     * 
     * @param vendorPhoneType The vendorPhoneType to set.
     * @deprecated
     */
    public void setVendorPhoneType(PhoneType vendorPhoneType) {
        this.vendorPhoneType = vendorPhoneType;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        if (this.vendorPhoneGeneratedIdentifier != null) {
            m.put("vendorPhoneGeneratedIdentifier", this.vendorPhoneGeneratedIdentifier.toString());
        }

        return m;
    }
}
