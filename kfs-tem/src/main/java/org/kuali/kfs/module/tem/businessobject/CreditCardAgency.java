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
package org.kuali.kfs.module.tem.businessobject;

import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.kuali.kfs.sys.businessobject.Bank;
import org.kuali.kfs.vnd.VendorPropertyConstants;
import org.kuali.kfs.vnd.businessobject.VendorDetail;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.util.ObjectUtils;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "TEM_CREDIT_CARD_AGENCY_T")
public class CreditCardAgency extends PersistableBusinessObjectBase implements MutableInactivatable {

    @Id

    @Column(name = "CREDIT_CARD_AGENCY_CODE")

    private String creditCardOrAgencyCode;
    @Column(name = "TRAVEL_CARD_TYPE_CD")
    private String travelCardTypeCode;
    @Column(name = "PAYMENT_IND")
    private Boolean paymentIndicator = Boolean.FALSE;
    @Column(name = "CREDIT_CARD_AGENCY_NAME")
    private String creditCardOrAgencyName;
    @Column(name = "ADDRESS1")
    private String address1;
    @Column(name = "ADDRESS2")
    private String address2;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "ZIPCODE")
    private String zipCode;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "CONTACT_NAME")
    private String contactName;
    @Column(name = "PRE_RECONCILED")
    private Boolean preReconciled = Boolean.FALSE;
    @Column(name = "ENABLE_NON_REIMBURSABLE")
    private Boolean enableNonReimbursable = Boolean.FALSE;
    @Column(name = "VNDR_HDR_GNRTD_ID")
    private Integer vendorHeaderGeneratedIdentifier;
    @Column(name = "VNDR_DTL_ASND_ID")
    private Integer vendorDetailAssignedIdentifier;
    @Column(name = "FOREIGN_COMPANY")
    private Boolean foreignCompany = Boolean.FALSE;
    @Column(name = "BNK_CD")
    private String bankCode;
    @Column(name = "ACTV_IND")
    private Boolean active = Boolean.TRUE;

    private TravelCardType travelCardType;
    private Bank bank;
    private VendorDetail vendorDetail;

    public String getCreditCardOrAgencyCode() {
        return creditCardOrAgencyCode;
    }


    public void setCreditCardOrAgencyCode(String creditCardOrAgencyCode) {
        this.creditCardOrAgencyCode = creditCardOrAgencyCode;
    }

    public TravelCardType getTravelCardType() {
        return travelCardType;
    }


    public void setTravelCardType(TravelCardType travelCardType) {
        this.travelCardType = travelCardType;
    }

    public String getTravelCardTypeCode() {
        return travelCardTypeCode;
    }


    public void setTravelCardTypeCode(String travelCardTypeCode) {
        this.travelCardTypeCode = travelCardTypeCode;
    }

    public Boolean getPaymentIndicator() {
        return paymentIndicator;
    }
    public Boolean isPaymentIndicator() {
        return paymentIndicator;
    }

    public void setPaymentIndicator(Boolean paymentIndicator) {
        this.paymentIndicator = paymentIndicator;
    }

    public String getCreditCardOrAgencyName() {
        return creditCardOrAgencyName;
    }

    public void setCreditCardOrAgencyName(String creditCardOrAgencyName) {
        this.creditCardOrAgencyName = creditCardOrAgencyName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }


    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getForeignCompany() {
        return foreignCompany;
    }


    public void setForeignCompany(Boolean foreignCompany) {
        this.foreignCompany = foreignCompany;
    }

    public String getContactName() {
        return contactName;
    }


    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Boolean getPreReconciled() {
        return preReconciled;
    }
    public Boolean isPreReconciled() {
        return preReconciled;
    }

    public void setPreReconciled(Boolean preReconciled) {
        this.preReconciled = preReconciled;
    }

    public Boolean getEnableNonReimbursable() {
        return enableNonReimbursable;
    }
    public Boolean isEnableNonReimbursable() {
        return enableNonReimbursable;
    }

    public void setEnableNonReimbursable(Boolean enableNonReimbursable) {
        this.enableNonReimbursable = enableNonReimbursable;
    }

    public void setVendorNumber(String vendorNumber) {
        VendorDetail vd = new VendorDetail();
        vd.setVendorNumber(vendorNumber);
        vendorHeaderGeneratedIdentifier = vd.getVendorHeaderGeneratedIdentifier();
        vendorDetailAssignedIdentifier = vd.getVendorDetailAssignedIdentifier();
        refreshReferenceObject(VendorPropertyConstants.VENDOR_DETAIL);
    }

    public String getVendorNumber() {
        if (!ObjectUtils.isNull(vendorDetail)) {
            return vendorDetail.getVendorNumber();
        } else if (vendorHeaderGeneratedIdentifier != null && vendorDetailAssignedIdentifier != null) {
            VendorDetail vd = new VendorDetail();
            vd.setVendorHeaderGeneratedIdentifier(vendorHeaderGeneratedIdentifier);
            vd.setVendorDetailAssignedIdentifier(vendorDetailAssignedIdentifier);
            return vd.getVendorNumber();
        }
        else {
            return "";
        }
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

    public Integer getVendorHeaderGeneratedIdentifier() {
        return vendorHeaderGeneratedIdentifier;
    }

    public Boolean getActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
       this.active = active;
    }

    @SuppressWarnings("rawtypes")
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("creditCardOrAgencyCode", this.creditCardOrAgencyCode);
        map.put("travelCardType", this.travelCardType);
        map.put("creditCardOrAgencyName", this.creditCardOrAgencyName);
        return map;
    }


    @Override
    public boolean isActive() {
        return getActive();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public VendorDetail getVendorDetail() {
        return vendorDetail;
    }

    public void setVendorDetail(VendorDetail vendorDetail) {
        this.vendorDetail = vendorDetail;
    }
}
