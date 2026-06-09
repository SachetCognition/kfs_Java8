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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.service.KualiModuleService;
import org.kuali.rice.krad.service.ModuleService;
import org.kuali.rice.location.api.LocationConstants;
import org.kuali.rice.location.framework.country.CountryEbo;
import org.kuali.rice.location.framework.state.StateEbo;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.hibernate.type.YesNoConverter;

/**
 * Container for information about how to get in Contact with a person at a Vendor for a particular purpose.
 */
@Entity
@Table(name = "PUR_VNDR_CNTCT_T")
public class VendorContact extends PersistableBusinessObjectBase implements MutableInactivatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VNDR_CNTCT_GNRTD_ID")
    protected Integer vendorContactGeneratedIdentifier;
    @Column(name = "VNDR_HDR_GNRTD_ID")
    protected Integer vendorHeaderGeneratedIdentifier;
    @Column(name = "VNDR_DTL_ASND_ID")
    protected Integer vendorDetailAssignedIdentifier;
    @Column(name = "VNDR_CNTCT_TYP_CD")
    protected String vendorContactTypeCode;
    @Column(name = "VNDR_CNTCT_NM")
    protected String vendorContactName;
    @Column(name = "VNDR_CNTCT_EMAIL_ADDR")
    protected String vendorContactEmailAddress;
    @Column(name = "VNDR_CNTCT_CMNT_TXT")
    protected String vendorContactCommentText;
    @Column(name = "VNDR_LN1_ADDR")
    protected String vendorLine1Address;
    @Column(name = "VNDR_LN2_ADDR")
    protected String vendorLine2Address;
    @Column(name = "VNDR_CTY_NM")
    protected String vendorCityName;
    @Column(name = "VNDR_ST_CD")
    protected String vendorStateCode;
    @Column(name = "VNDR_ZIP_CD")
    protected String vendorZipCode;
    @Column(name = "VNDR_CNTRY_CD")
    protected String vendorCountryCode;
    @Column(name = "VNDR_ATTN_NM")
    protected String vendorAttentionName;
    @Column(name = "VNDR_ADDR_INTL_PROV_NM")
    protected String vendorAddressInternationalProvinceName;
    @Column(name = "DOBJ_MAINT_CD_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    protected boolean active;

    // These aren't persisted in db, only for lookup page
    @Transient
    protected String phoneNumberForLookup;
    @Transient
    protected String tollFreeForLookup;
    @Transient
    protected String faxForLookup;

    @OneToMany(mappedBy = "vendorContact", fetch = FetchType.LAZY)
    protected List<VendorContactPhoneNumber> vendorContactPhoneNumbers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "VNDR_HDR_GNRTD_ID", referencedColumnName = "VNDR_HDR_GNRTD_ID", insertable = false, updatable = false),
        @JoinColumn(name = "VNDR_DTL_ASND_ID", referencedColumnName = "VNDR_DTL_ASND_ID", insertable = false, updatable = false)
    })
    protected VendorDetail vendorDetail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VNDR_CNTCT_TYP_CD", insertable = false, updatable = false)
    protected ContactType vendorContactType;
    @Transient
    protected StateEbo vendorState;
    @Transient
    protected CountryEbo vendorCountry;

    public VendorContact() {
        vendorContactPhoneNumbers = new ArrayList<VendorContactPhoneNumber>();
    }

    public Integer getVendorContactGeneratedIdentifier() {
        return vendorContactGeneratedIdentifier;
    }

    public void setVendorContactGeneratedIdentifier(Integer vendorContactGeneratedIdentifier) {
        this.vendorContactGeneratedIdentifier = vendorContactGeneratedIdentifier;
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

    public String getVendorContactTypeCode() {
        return vendorContactTypeCode;
    }

    public void setVendorContactTypeCode(String vendorContactTypeCode) {
        this.vendorContactTypeCode = vendorContactTypeCode;
    }

    public String getVendorContactName() {
        return vendorContactName;
    }

    public void setVendorContactName(String vendorContactName) {
        this.vendorContactName = vendorContactName;
    }

    public String getVendorContactEmailAddress() {
        return vendorContactEmailAddress;
    }

    public void setVendorContactEmailAddress(String vendorContactEmailAddress) {
        this.vendorContactEmailAddress = vendorContactEmailAddress;
    }

    public String getVendorContactCommentText() {
        return vendorContactCommentText;
    }

    public void setVendorContactCommentText(String vendorContactCommentText) {
        this.vendorContactCommentText = vendorContactCommentText;
    }

    public ContactType getVendorContactType() {
        return vendorContactType;
    }

    public void setVendorContactType(ContactType vendorContactType) {
        this.vendorContactType = vendorContactType;
    }

    public VendorDetail getVendorDetail() {
        return vendorDetail;
    }

    public void setVendorDetail(VendorDetail vendorDetail) {
        this.vendorDetail = vendorDetail;
    }

    public String getVendorAddressInternationalProvinceName() {
        return vendorAddressInternationalProvinceName;
    }

    public void setVendorAddressInternationalProvinceName(String vendorAddressInternationalProvinceName) {
        this.vendorAddressInternationalProvinceName = vendorAddressInternationalProvinceName;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getVendorAttentionName() {
        return vendorAttentionName;
    }

    public void setVendorAttentionName(String vendorAttentionName) {
        this.vendorAttentionName = vendorAttentionName;
    }

    public String getVendorCityName() {
        return vendorCityName;
    }

    public void setVendorCityName(String vendorCityName) {
        this.vendorCityName = vendorCityName;
    }

    public String getVendorCountryCode() {
        return vendorCountryCode;
    }

    public void setVendorCountryCode(String vendorCountryCode) {
        this.vendorCountryCode = vendorCountryCode;
    }

    public String getVendorLine1Address() {
        return vendorLine1Address;
    }

    public void setVendorLine1Address(String vendorLine1Address) {
        this.vendorLine1Address = vendorLine1Address;
    }

    public String getVendorLine2Address() {
        return vendorLine2Address;
    }

    public void setVendorLine2Address(String vendorLine2Address) {
        this.vendorLine2Address = vendorLine2Address;
    }

    public String getVendorStateCode() {
        return vendorStateCode;
    }

    public void setVendorStateCode(String vendorStateCode) {
        this.vendorStateCode = vendorStateCode;
    }

    public String getVendorZipCode() {
        return vendorZipCode;
    }

    public void setVendorZipCode(String vendorZipCode) {
        this.vendorZipCode = vendorZipCode;
    }

    public CountryEbo getVendorCountry() {
        if ( StringUtils.isBlank(vendorCountryCode) ) {
            vendorCountry = null;
        } else {
            if ( vendorCountry == null || !StringUtils.equals( vendorCountry.getCode(),vendorCountryCode) ) {
                ModuleService moduleService = SpringContext.getBean(KualiModuleService.class).getResponsibleModuleService(CountryEbo.class);
                if ( moduleService != null ) {
                    Map<String,Object> keys = new HashMap<String, Object>(1);
                    keys.put(LocationConstants.PrimaryKeyConstants.CODE, vendorCountryCode);
                    vendorCountry = moduleService.getExternalizableBusinessObject(CountryEbo.class, keys);
                } else {
                    throw new RuntimeException( "CONFIGURATION ERROR: No responsible module found for EBO class.  Unable to proceed." );
                }
            }
        }
        return vendorCountry;
    }

    public void setVendorCountry(CountryEbo vendorCountry) {
        this.vendorCountry = vendorCountry;
    }

    public StateEbo getVendorState() {
        if ( StringUtils.isBlank(vendorStateCode) || StringUtils.isBlank(vendorCountryCode ) ) {
            vendorState = null;
        } else {
            if ( vendorState == null || !StringUtils.equals( vendorState.getCode(),vendorStateCode) || !StringUtils.equals(vendorState.getCountryCode(), vendorCountryCode ) ) {
                ModuleService moduleService = SpringContext.getBean(KualiModuleService.class).getResponsibleModuleService(StateEbo.class);
                if ( moduleService != null ) {
                    Map<String,Object> keys = new HashMap<String, Object>(2);
                    keys.put(LocationConstants.PrimaryKeyConstants.COUNTRY_CODE, vendorCountryCode);
                    keys.put(LocationConstants.PrimaryKeyConstants.CODE, vendorStateCode);
                    vendorState = moduleService.getExternalizableBusinessObject(StateEbo.class, keys);
                } else {
                    throw new RuntimeException( "CONFIGURATION ERROR: No responsible module found for EBO class.  Unable to proceed." );
                }
            }
        }
        return vendorState;
    }

    public void setVendorState(StateEbo vendorState) {
        this.vendorState = vendorState;
    }

    public String getFaxForLookup() {
        return faxForLookup;
    }

    public void setFaxForLookup(String faxForLookup) {
        this.faxForLookup = faxForLookup;
    }

    public String getPhoneNumberForLookup() {
        return phoneNumberForLookup;
    }

    public void setPhoneNumberForLookup(String phoneNumberForLookup) {
        this.phoneNumberForLookup = phoneNumberForLookup;
    }

    public String getTollFreeForLookup() {
        return tollFreeForLookup;
    }

    public void setTollFreeForLookup(String tollFreeForLookup) {
        this.tollFreeForLookup = tollFreeForLookup;
    }

    public List<VendorContactPhoneNumber> getVendorContactPhoneNumbers() {
        return vendorContactPhoneNumbers;
    }

    public void setVendorContactPhoneNumbers(List<VendorContactPhoneNumber> vendorContactPhoneNumbers) {
        this.vendorContactPhoneNumbers = vendorContactPhoneNumbers;
    }

}
