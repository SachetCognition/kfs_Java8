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
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.log4j.Logger;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.vnd.document.service.VendorService;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.service.KualiModuleService;
import org.kuali.rice.krad.service.ModuleService;
import org.kuali.rice.krad.util.ObjectUtils;
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
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;

/**
 * Address to be associated with a particular Vendor.
 */
@Entity
@Table(name = "PUR_VNDR_ADDR_T")
public class VendorAddress extends PersistableBusinessObjectBase implements VendorRoutingComparable, MutableInactivatable {
    private static final Logger LOG = Logger.getLogger(VendorAddress.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VNDR_ADDR_GNRTD_ID")
    protected Integer vendorAddressGeneratedIdentifier;
    @Column(name = "VNDR_HDR_GNRTD_ID")
    protected Integer vendorHeaderGeneratedIdentifier;
    @Column(name = "VNDR_DTL_ASND_ID")
    protected Integer vendorDetailAssignedIdentifier;
    @Column(name = "VNDR_ADDR_TYP_CD")
    protected String vendorAddressTypeCode;
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
    @Column(name = "VNDR_ADDR_EMAIL_ADDR")
    protected String vendorAddressEmailAddress;
    @Column(name = "VNDR_B2B_URL_ADDR")
    protected String vendorBusinessToBusinessUrlAddress;
    @Column(name = "VNDR_FAX_NBR")
    protected String vendorFaxNumber;
    @Column(name = "VNDR_DFLT_ADDR_IND")
    @Convert(converter = YesNoConverter.class)
    protected boolean vendorDefaultAddressIndicator;
    @Column(name = "DOBJ_MAINT_CD_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    protected boolean active;

    @OneToMany(mappedBy = "vendorAddress", fetch = FetchType.LAZY)
    protected List<VendorDefaultAddress> vendorDefaultAddresses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "VNDR_HDR_GNRTD_ID", referencedColumnName = "VNDR_HDR_GNRTD_ID", insertable = false, updatable = false),
        @JoinColumn(name = "VNDR_DTL_ASND_ID", referencedColumnName = "VNDR_DTL_ASND_ID", insertable = false, updatable = false)
    })
    protected VendorDetail vendorDetail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VNDR_ADDR_TYP_CD", insertable = false, updatable = false)
    protected AddressType vendorAddressType;
    @Transient
    protected StateEbo vendorState;
    @Transient
    protected CountryEbo vendorCountry;

    /**
     * Default constructor.
     */
    public VendorAddress() {
        vendorDefaultAddresses = new ArrayList<VendorDefaultAddress>();
    }

    public Integer getVendorAddressGeneratedIdentifier() {
        return vendorAddressGeneratedIdentifier;
    }

    public void setVendorAddressGeneratedIdentifier(Integer vendorAddressGeneratedIdentifier) {
        this.vendorAddressGeneratedIdentifier = vendorAddressGeneratedIdentifier;
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

    public String getVendorAddressInternationalProvinceName() {
        return vendorAddressInternationalProvinceName;
    }

    public void setVendorAddressInternationalProvinceName(String vendorAddressInternationalProvinceName) {
        this.vendorAddressInternationalProvinceName = vendorAddressInternationalProvinceName;
    }

    public String getVendorAddressEmailAddress() {
        return vendorAddressEmailAddress;
    }

    public void setVendorAddressEmailAddress(String vendorAddressEmailAddress) {
        this.vendorAddressEmailAddress = vendorAddressEmailAddress;
    }

    public String getVendorAddressTypeCode() {
        return vendorAddressTypeCode;
    }

    public void setVendorAddressTypeCode(String vendorAddressTypeCode) {
        this.vendorAddressTypeCode = vendorAddressTypeCode;
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

    public String getVendorCityName() {
        return vendorCityName;
    }

    public void setVendorCityName(String vendorCityName) {
        this.vendorCityName = vendorCityName;
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

    public String getVendorCountryCode() {
        return vendorCountryCode;
    }

    public void setVendorCountryCode(String vendorCountryCode) {
        this.vendorCountryCode = vendorCountryCode;
    }

    public String getVendorAttentionName() {
        return vendorAttentionName;
    }

    public void setVendorAttentionName(String vendorAttentionName) {
        this.vendorAttentionName = vendorAttentionName;
    }

    public String getVendorBusinessToBusinessUrlAddress() {
        return vendorBusinessToBusinessUrlAddress;
    }

    public void setVendorBusinessToBusinessUrlAddress(String vendorBusinessToBusinessUrlAddress) {
        this.vendorBusinessToBusinessUrlAddress = vendorBusinessToBusinessUrlAddress;
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

    public AddressType getVendorAddressType() {
        return vendorAddressType;
    }

    /**
     * Sets the vendorAddressType attribute.
     *
     * @param vendorAddressType The vendorAddressType to set.
     * @deprecated
     */
    public void setVendorAddressType(AddressType vendorAddressType) {
        this.vendorAddressType = vendorAddressType;
    }

    public StateEbo getVendorState() {
        if ( StringUtils.isBlank(vendorStateCode) || StringUtils.isBlank(vendorCountryCode ) ) {
            vendorState = null;
        } else {
            if ( ObjectUtils.isNull(vendorState) || !StringUtils.equals( vendorState.getCode(),vendorStateCode) || !StringUtils.equals(vendorState.getCountryCode(), vendorCountryCode ) ) {
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

    /**
     * Sets the vendorState attribute.
     *
     * @param vendorState The vendorState to set.
     * @deprecated
     */
    public void setVendorState(StateEbo vendorState) {
        this.vendorState = vendorState;
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

    /**
     * Sets the vendorCountry attribute.
     *
     * @param vendorCountry The vendorCountry to set.
     * @deprecated
     */
    public void setVendorCountry(CountryEbo vendorCountry) {
        this.vendorCountry = vendorCountry;
    }

    /**
     * Gets the vendorFaxNumber attribute.
     *
     * @return Returns the vendorFaxNumber.
     */
    public String getVendorFaxNumber() {
        return vendorFaxNumber;
    }

    /**
     * Sets the vendorFaxNumber attribute value.
     *
     * @param vendorFaxNumber The vendorFaxNumber to set.
     */
    public void setVendorFaxNumber(String vendorFaxNumber) {
        this.vendorFaxNumber = vendorFaxNumber;
    }

    /**
     * Gets the vendorDefaultAddressIndicator attribute.
     *
     * @return Returns the vendorDefaultAddressIndicator.
     */
    public boolean isVendorDefaultAddressIndicator() {
        return vendorDefaultAddressIndicator;
    }

    /**
     * Sets the vendorDefaultAddressIndicator attribute value.
     *
     * @param vendorDefaultAddressIndicator The vendorDefaultAddressIndicator to set.
     */
    public void setVendorDefaultAddressIndicator(boolean vendorDefaultAddressIndicator) {
        this.vendorDefaultAddressIndicator = vendorDefaultAddressIndicator;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public List<VendorDefaultAddress> getVendorDefaultAddresses() {
        return vendorDefaultAddresses;
    }

    public void setVendorDefaultAddresses(List<VendorDefaultAddress> vendorDefaultAddresses) {
        this.vendorDefaultAddresses = vendorDefaultAddresses;
    }

    /**
     * @see org.kuali.kfs.vnd.document.routing.VendorRoutingComparable#isEqualForRouting(java.lang.Object)
     */
    @Override
    public boolean isEqualForRouting(Object toCompare) {
        LOG.debug("Entering isEqualForRouting.");
        if ((ObjectUtils.isNull(toCompare)) || !(toCompare instanceof VendorAddress)) {
            LOG.debug("Exiting isEqualForRouting");
            return false;
        }
        else {
            VendorAddress va = (VendorAddress) toCompare;
            boolean eq = new EqualsBuilder().append(this.getVendorAddressGeneratedIdentifier(), va.getVendorAddressGeneratedIdentifier()).append(this.getVendorHeaderGeneratedIdentifier(), va.getVendorHeaderGeneratedIdentifier()).append(this.getVendorDetailAssignedIdentifier(), va.getVendorDetailAssignedIdentifier()).append(this.getVendorAddressTypeCode(), va.getVendorAddressTypeCode()).append(this.getVendorLine1Address(), va.getVendorLine1Address()).append(this.getVendorLine2Address(), va.getVendorLine2Address()).append(this.getVendorCityName(), va.getVendorCityName()).append(this.getVendorStateCode(), va.getVendorStateCode()).append(this.getVendorZipCode(), va.getVendorZipCode()).append(this.getVendorCountryCode(), va.getVendorCountryCode()).append(this.getVendorAttentionName(), va.getVendorAttentionName()).append(this.getVendorAddressInternationalProvinceName(), va.getVendorAddressInternationalProvinceName()).append(this.getVendorAddressEmailAddress(),
                    va.getVendorAddressEmailAddress()).append(this.getVendorBusinessToBusinessUrlAddress(), va.getVendorBusinessToBusinessUrlAddress()).append(this.getVendorFaxNumber(), va.getVendorFaxNumber()).append(this.isVendorDefaultAddressIndicator(), va.isVendorDefaultAddressIndicator()).isEquals();
            eq &= SpringContext.getBean(VendorService.class).equalMemberLists(this.getVendorDefaultAddresses(), va.getVendorDefaultAddresses());
            LOG.debug("Exiting isEqualForRouting.");
            return eq;
        }
    }

}
