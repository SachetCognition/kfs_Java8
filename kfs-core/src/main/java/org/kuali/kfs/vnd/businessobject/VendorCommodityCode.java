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
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;

@Entity
@Table(name = "PUR_VNDR_COMM_T")
@IdClass(VendorCommodityCodeId.class)
public class VendorCommodityCode extends PersistableBusinessObjectBase implements MutableInactivatable {

	@Id
	@Column(name = "VNDR_HDR_GNRTD_ID")
	private Integer vendorHeaderGeneratedIdentifier;
	@Id
	@Column(name = "VNDR_DTL_ASND_ID")
	private Integer vendorDetailAssignedIdentifier;
	@Id
	@Column(name = "PUR_COMM_CD")
	private String purchasingCommodityCode;
	@Column(name = "PUR_COMM_DFLT_IND")
	@Convert(converter = YesNoConverter.class)
	private boolean commodityDefaultIndicator;
	@Column(name = "DOBJ_MAINT_CD_ACTV_IND")
	@Convert(converter = YesNoConverter.class)
	private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "VNDR_HDR_GNRTD_ID", referencedColumnName = "VNDR_HDR_GNRTD_ID", insertable = false, updatable = false),
        @JoinColumn(name = "VNDR_DTL_ASND_ID", referencedColumnName = "VNDR_DTL_ASND_ID", insertable = false, updatable = false)
    })
    private VendorDetail vendorDetail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUR_COMM_CD", insertable = false, updatable = false)
    private CommodityCode commodityCode;
    
	/**
	 * Default constructor.
	 */
	public VendorCommodityCode() {

	}

	public VendorCommodityCode(Integer vendorHeaderGeneratedIdentifier, Integer vendorDetailAssignedIdentifier, CommodityCode commodityCode, boolean active) {
	    this.vendorHeaderGeneratedIdentifier = vendorHeaderGeneratedIdentifier;
	    this.vendorDetailAssignedIdentifier = vendorDetailAssignedIdentifier;
	    this.commodityCode = commodityCode;
	    if (commodityCode != null) {
	        this.purchasingCommodityCode = commodityCode.getPurchasingCommodityCode();
	    }
	    this.active = active;
	}
	
	/**
	 * Gets the vendorHeaderGeneratedIdentifier attribute.
	 * 
	 * @return Returns the vendorHeaderGeneratedIdentifier
	 * 
	 */
	public Integer getVendorHeaderGeneratedIdentifier() { 
		return vendorHeaderGeneratedIdentifier;
	}

	/**
	 * Sets the vendorHeaderGeneratedIdentifier attribute.
	 * 
	 * @param vendorHeaderGeneratedIdentifier The vendorHeaderGeneratedIdentifier to set.
	 * 
	 */
	public void setVendorHeaderGeneratedIdentifier(Integer vendorHeaderGeneratedIdentifier) {
		this.vendorHeaderGeneratedIdentifier = vendorHeaderGeneratedIdentifier;
	}


	/**
	 * Gets the vendorDetailAssignedIdentifier attribute.
	 * 
	 * @return Returns the vendorDetailAssignedIdentifier
	 * 
	 */
	public Integer getVendorDetailAssignedIdentifier() { 
		return vendorDetailAssignedIdentifier;
	}

	/**
	 * Sets the vendorDetailAssignedIdentifier attribute.
	 * 
	 * @param vendorDetailAssignedIdentifier The vendorDetailAssignedIdentifier to set.
	 * 
	 */
	public void setVendorDetailAssignedIdentifier(Integer vendorDetailAssignedIdentifier) {
		this.vendorDetailAssignedIdentifier = vendorDetailAssignedIdentifier;
	}


	/**
	 * Gets the commodityCode attribute.
	 * 
	 * @return Returns the commodityCode
	 * 
	 */
	public String getPurchasingCommodityCode() { 
		return purchasingCommodityCode;
	}

	/**
	 * Sets the commodityCode attribute.
	 * 
	 * @param commodityCode The commodityCode to set.
	 * 
	 */
	public void setPurchasingCommodityCode(String purchasingCommodityCode) {
		this.purchasingCommodityCode = purchasingCommodityCode;
	}


	/**
	 * Gets the commodityDefaultIndicator attribute.
	 * 
	 * @return Returns the commodityDefaultIndicator
	 * 
	 */
	public boolean isCommodityDefaultIndicator() { 
		return commodityDefaultIndicator;
	}

	/**
	 * Sets the commodityDefaultIndicator attribute.
	 * 
	 * @param commodityDefaultIndicator The commodityDefaultIndicator to set.
	 * 
	 */
	public void setCommodityDefaultIndicator(boolean commodityDefaultIndicator) {
		this.commodityDefaultIndicator = commodityDefaultIndicator;
	}


	/**
	 * Gets the active attribute.
	 * 
	 * @return Returns the active
	 * 
	 */
	public boolean isActive() { 
		return active;
	}

	/**
	 * Sets the active attribute.
	 * 
	 * @param active The active to set.
	 * 
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
     * Gets the vendorDetail attribute. 
     * @return Returns the vendorDetail.
     */
    public VendorDetail getVendorDetail() {
        return vendorDetail;
    }

    /**
     * Sets the vendorDetail attribute value.
     * @param vendorDetail The vendorDetail to set.
     * @deprecated
     */
    public void setVendorDetail(VendorDetail vendorDetail) {
        this.vendorDetail = vendorDetail;
    }

    public CommodityCode getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(CommodityCode commodityCode) {
        this.commodityCode = commodityCode;
    }

}
