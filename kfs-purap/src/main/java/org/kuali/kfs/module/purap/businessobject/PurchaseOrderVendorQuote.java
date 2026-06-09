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

package org.kuali.kfs.module.purap.businessobject;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.module.purap.document.PurchaseOrderDocument;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.service.KualiModuleService;
import org.kuali.rice.krad.service.ModuleService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.location.api.LocationConstants;
import org.kuali.rice.location.framework.country.CountryEbo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;


/**
 * Purchase Order Vendor Quote Business Object.
 */
@Entity
@Table(name = "PUR_PO_VNDR_QT_T")
@IdClass(PurchaseOrderVendorQuoteId.class)
public class PurchaseOrderVendorQuote extends PersistableBusinessObjectBase {

    @Id
    @Column(name = "FDOC_NBR")
    private String documentNumber;
    @Id
    @Column(name = "PO_VNDR_QT_ID")
    private Integer purchaseOrderVendorQuoteIdentifier;
    @Column(name = "VNDR_HDR_GNRTD_ID")
    private Integer vendorHeaderGeneratedIdentifier;
    @Column(name = "VNDR_DTL_ASND_ID")
    private Integer vendorDetailAssignedIdentifier;
    @Column(name = "VNDR_NM")
    private String vendorName;
    @Column(name = "VNDR_LN1_ADDR")
    private String vendorLine1Address;
    @Column(name = "VNDR_LN2_ADDR")
    private String vendorLine2Address;
    @Column(name = "VNDR_CTY_NM")
    private String vendorCityName;
    @Column(name = "VNDR_ST_CD")
    private String vendorStateCode;
    @Column(name = "VNDR_PSTL_CD")
    private String vendorPostalCode;
    @Column(name = "VNDR_PHN_NBR")
    private String vendorPhoneNumber;
    @Column(name = "VNDR_FAX_NBR")
    private String vendorFaxNumber;
    @Column(name = "VNDR_EMAIL_ADDR")
    private String vendorEmailAddress;
    @Column(name = "VNDR_ATTN_NM")
    private String vendorAttentionName;
    @Column(name = "PO_QT_TRANS_TYP_CD")
    private String purchaseOrderQuoteTransmitTypeCode;
    @Column(name = "PO_QT_TRANS_DT")
    private Timestamp purchaseOrderQuoteTransmitTimestamp;
    @Column(name = "PO_QT_PRCE_EXPR_DT")
    private Date purchaseOrderQuotePriceExpirationDate;
    @Column(name = "PO_QT_STAT_CD")
    private String purchaseOrderQuoteStatusCode;
    @Column(name = "PO_QT_AWD_DT")
    private Timestamp purchaseOrderQuoteAwardTimestamp;
    @Column(name = "PO_QT_RANK_NBR")
    private String purchaseOrderQuoteRankNumber;
    @Column(name = "VNDR_CNTRY_CD")
    private String vendorCountryCode;
    @Column(name = "VNDR_ADDR_INTL_PROV_NM")
    private String vendorAddressInternationalProvinceName;
    private boolean isTransmitPrintDisplayed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FDOC_NBR", insertable = false, updatable = false)
    private PurchaseOrderDocument purchaseOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PO_QT_STAT_CD", insertable = false, updatable = false)
    private PurchaseOrderQuoteStatus purchaseOrderQuoteStatus;
    private CountryEbo vendorCountry;

    //non-persisted variables
    protected boolean isPdfDisplayedToUserOnce;
    
    /**
     * Default constructor.
     */
    public PurchaseOrderVendorQuote() {
        
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getPurchaseOrderVendorQuoteIdentifier() {
        return purchaseOrderVendorQuoteIdentifier;
    }

    /**
     * Gets the vendorCountryCode attribute.
     *
     * @return Returns the vendorCountryCode.
     */
    public CountryEbo getVendorCountry() {
        if ( StringUtils.isBlank(vendorCountryCode) ) {
            vendorCountry = null;
        } else {
            if ( vendorCountry == null || !StringUtils.equals( vendorCountry.getCode(), vendorCountryCode) ) {
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

    public void setPurchaseOrderVendorQuoteIdentifier(Integer purchaseOrderVendorQuoteIdentifier) {
        this.purchaseOrderVendorQuoteIdentifier = purchaseOrderVendorQuoteIdentifier;
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

    public String getVendorNumber() {
        String vendorNumber = "";
        if (ObjectUtils.isNotNull(this.vendorHeaderGeneratedIdentifier)) {
            vendorNumber = this.vendorHeaderGeneratedIdentifier.toString();
        }
        if (ObjectUtils.isNotNull(this.vendorDetailAssignedIdentifier)) {
            vendorNumber += "-" + this.vendorDetailAssignedIdentifier.toString();
        }
        return vendorNumber;
    }

    public void setVendorNumber(String vendorNumber) {
        // do nothing
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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

    public String getVendorPostalCode() {
        return vendorPostalCode;
    }

    public void setVendorPostalCode(String vendorPostalCode) {
        this.vendorPostalCode = vendorPostalCode;
    }

    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public String getVendorFaxNumber() {
        return vendorFaxNumber;
    }

    public void setVendorFaxNumber(String vendorFaxNumber) {
        this.vendorFaxNumber = vendorFaxNumber;
    }

    public String getVendorEmailAddress() {
        return vendorEmailAddress;
    }

    public void setVendorEmailAddress(String vendorEmailAddress) {
        this.vendorEmailAddress = vendorEmailAddress;
    }

    public String getVendorAttentionName() {
        return vendorAttentionName;
    }

    public void setVendorAttentionName(String vendorAttentionName) {
        this.vendorAttentionName = vendorAttentionName;
    }

    public String getPurchaseOrderQuoteTransmitTypeCode() {
        return purchaseOrderQuoteTransmitTypeCode;
    }

    public void setPurchaseOrderQuoteTransmitTypeCode(String purchaseOrderQuoteTransmitTypeCode) {
        this.purchaseOrderQuoteTransmitTypeCode = purchaseOrderQuoteTransmitTypeCode;
    }

    public Timestamp getPurchaseOrderQuoteTransmitTimestamp() {
        return purchaseOrderQuoteTransmitTimestamp;
    }

    public void setPurchaseOrderQuoteTransmitTimestamp(Timestamp purchaseOrderQuoteTransmitTimestamp) {
        this.purchaseOrderQuoteTransmitTimestamp = purchaseOrderQuoteTransmitTimestamp;
    }

    public Date getPurchaseOrderQuotePriceExpirationDate() {
        return purchaseOrderQuotePriceExpirationDate;
    }

    public void setPurchaseOrderQuotePriceExpirationDate(Date purchaseOrderQuotePriceExpirationDate) {
        this.purchaseOrderQuotePriceExpirationDate = purchaseOrderQuotePriceExpirationDate;
    }

    public String getPurchaseOrderQuoteStatusCode() {
        return purchaseOrderQuoteStatusCode;
    }

    public void setPurchaseOrderQuoteStatusCode(String purchaseOrderQuoteStatusCode) {
        this.purchaseOrderQuoteStatusCode = purchaseOrderQuoteStatusCode;
    }

    public Timestamp getPurchaseOrderQuoteAwardTimestamp() {
        return purchaseOrderQuoteAwardTimestamp;
    }

    public void setPurchaseOrderQuoteAwardTimestamp(Timestamp purchaseOrderQuoteAwardTimestamp) {
        this.purchaseOrderQuoteAwardTimestamp = purchaseOrderQuoteAwardTimestamp;
    }

    public String getPurchaseOrderQuoteRankNumber() {
        return purchaseOrderQuoteRankNumber;
    }

    public void setPurchaseOrderQuoteRankNumber(String purchaseOrderQuoteRankNumber) {
        this.purchaseOrderQuoteRankNumber = purchaseOrderQuoteRankNumber;
    }

    public PurchaseOrderDocument getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * Sets the purchaseOrder attribute.
     * 
     * @param purchaseOrder The purchaseOrder to set.
     * @deprecated
     */
    public void setPurchaseOrder(PurchaseOrderDocument purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public PurchaseOrderQuoteStatus getPurchaseOrderQuoteStatus() {
        return purchaseOrderQuoteStatus;
    }

    /**
     * Sets the purchaseOrderQuoteStatus attribute.
     * 
     * @param purchaseOrderQuoteStatus The purchaseOrderQuoteStatus to set.
     * @deprecated
     */
    public void setPurchaseOrderQuoteStatus(PurchaseOrderQuoteStatus purchaseOrderQuoteStatus) {
        this.purchaseOrderQuoteStatus = purchaseOrderQuoteStatus;
    }

    public String getVendorCountryCode() {
        return vendorCountryCode;
    }

    public void setVendorCountryCode(String vendorCountryCode) {
        this.vendorCountryCode = vendorCountryCode;
    }

    public boolean isTransmitPrintDisplayed() {
        return isTransmitPrintDisplayed;
    }

    public void setTransmitPrintDisplayed(boolean isTransmitPrintDisplayed) {
        this.isTransmitPrintDisplayed = isTransmitPrintDisplayed;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("documentNumber", this.documentNumber);
        if (this.purchaseOrderVendorQuoteIdentifier != null) {
            m.put("purchaseOrderVendorQuoteIdentifier", this.purchaseOrderVendorQuoteIdentifier.toString());
        }
        return m;
    }

    /**
     * Method to determine if the the pdf has already been displayed to the user
     * one time. If false, its set to true and locks this out.
     * 
     * @return
     */
    public boolean isPdfDisplayedToUserOnce() {
        boolean valueToReturn = isPdfDisplayedToUserOnce;
        
        //if not displayed, we will return false, but subsequent calls will return true.
        if (valueToReturn == false){
            isPdfDisplayedToUserOnce = true;
        }
        
        return valueToReturn;
    }

    public void setPdfDisplayedToUserOnce(boolean isPdfDisplayedToUserOnce) {
        this.isPdfDisplayedToUserOnce = isPdfDisplayedToUserOnce;
    }

}
