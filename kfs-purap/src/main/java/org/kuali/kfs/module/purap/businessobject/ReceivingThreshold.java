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

import java.util.LinkedHashMap;

import org.kuali.kfs.coa.businessobject.AccountType;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.businessobject.Organization;
import org.kuali.kfs.coa.businessobject.SubFundGroup;
import org.kuali.kfs.vnd.businessobject.CommodityCode;
import org.kuali.kfs.vnd.businessobject.VendorDetail;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.core.api.util.type.KualiDecimal;
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
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;


@Entity
@Table(name = "PUR_THRSHLD_T")
public class ReceivingThreshold extends PersistableBusinessObjectBase implements MutableInactivatable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUR_THRSHLD_ID")
    private Integer thresholdIdentifier;
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;
    @Column(name = "ACCT_TYP_CD")
    private String accountTypeCode;
    @Column(name = "SUB_FUND_GRP_CD")
    private String subFundGroupCode;
    @Column(name = "FIN_OBJECT_CD")
    private String financialObjectCode;
    @Column(name = "ORG_CD")
    private String organizationCode;
    @Column(name = "PUR_THRSHLD_AMT")
    private KualiDecimal thresholdAmount;
    @Column(name = "PUR_COMM_CD")
    private String purchasingCommodityCode;
    
    @Column(name = "VNDR_HDR_GNRTD_ID")
    private Integer vendorHeaderGeneratedIdentifier;
    @Column(name = "VNDR_DTL_ASND_ID")
    private Integer vendorDetailAssignedIdentifier;

    @Column(name = "DOBJ_MAINT_CD_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean active;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false)
    private Chart chart;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCT_TYP_CD", insertable = false, updatable = false)
    private AccountType accountType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_FUND_GRP_CD", insertable = false, updatable = false)
    private SubFundGroup subFundGroup;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false),
            @JoinColumn(name = "FIN_OBJECT_CD", insertable = false, updatable = false)
    })
    private ObjectCode financialObject;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false),
            @JoinColumn(name = "ORG_CD", insertable = false, updatable = false)
    })
    private Organization organization;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "VNDR_HDR_GNRTD_ID", insertable = false, updatable = false),
            @JoinColumn(name = "VNDR_DTL_ASND_ID", insertable = false, updatable = false)
    })
    private VendorDetail vendorDetail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUR_COMM_CD", insertable = false, updatable = false)
    private CommodityCode commodityCode;
    
    public ReceivingThreshold(){
    }
    
    
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        
        LinkedHashMap returnMap = new LinkedHashMap();
        
        returnMap.put("thresholdIdentifier",thresholdIdentifier);
        returnMap.put("chartOfAccountsCode",chartOfAccountsCode);
        returnMap.put("accountTypeCode",accountTypeCode);
        returnMap.put("subFundGroupCode",subFundGroupCode);
        returnMap.put("financialObjectCode",financialObjectCode);
        returnMap.put("organizationCode",organizationCode);
        returnMap.put("vendorHeaderGeneratedIdentifier",vendorHeaderGeneratedIdentifier);
        returnMap.put("thresholdAmount",thresholdAmount);
        returnMap.put("active",active);
        
        return returnMap;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    public ObjectCode getFinancialObject() {
        return financialObject;
    }

    public void setFinancialObject(ObjectCode financialObject) {
        this.financialObject = financialObject;
    }

    public String getFinancialObjectCode() {
        return financialObjectCode;
    }

    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public SubFundGroup getSubFundGroup() {
        return subFundGroup;
    }

    public void setSubFundGroup(SubFundGroup subFundGroup) {
        this.subFundGroup = subFundGroup;
    }

    public String getSubFundGroupCode() {
        return subFundGroupCode;
    }

    public void setSubFundGroupCode(String subFundGroupCode) {
        this.subFundGroupCode = subFundGroupCode;
    }

    public Integer getThresholdIdentifier() {
        return thresholdIdentifier;
    }

    public void setThresholdIdentifier(Integer thresholdIdentifier) {
        this.thresholdIdentifier = thresholdIdentifier;
    }

    public Integer getVendorHeaderGeneratedIdentifier() {
        return vendorHeaderGeneratedIdentifier;
    }

    public void setVendorHeaderGeneratedIdentifier(Integer vendorHeaderGeneratedIdentifier) {
        this.vendorHeaderGeneratedIdentifier = vendorHeaderGeneratedIdentifier;
    }

    public KualiDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(KualiDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public VendorDetail getVendorDetail() {
        return vendorDetail;
    }

    public void setVendorDetail(VendorDetail vendorDetail) {
        this.vendorDetail = vendorDetail;
    }

    public String getVendorNumber() {
        VendorDetail tempVendorDetail = new VendorDetail();
        tempVendorDetail.setVendorHeaderGeneratedIdentifier(vendorHeaderGeneratedIdentifier);
        tempVendorDetail.setVendorDetailAssignedIdentifier(vendorDetailAssignedIdentifier);
        return tempVendorDetail.getVendorNumber();
    }

    public void setVendorNumber(String vendorNumber) {
        VendorDetail tempVendorDetail = new VendorDetail();
        tempVendorDetail.setVendorNumber(vendorNumber);
        setVendorHeaderGeneratedIdentifier(tempVendorDetail.getVendorHeaderGeneratedIdentifier());
        setVendorDetailAssignedIdentifier(tempVendorDetail.getVendorDetailAssignedIdentifier());
    }
    
    public Integer getVendorDetailAssignedIdentifier() {
        return vendorDetailAssignedIdentifier;
    }

    public void setVendorDetailAssignedIdentifier(Integer vendorDetailAssignedIdentifier) {
        this.vendorDetailAssignedIdentifier = vendorDetailAssignedIdentifier;
    }
    
    public String getPurchasingCommodityCode() {
        return purchasingCommodityCode;
    }

    public void setPurchasingCommodityCode(String purchasingCommodityCode) {
        this.purchasingCommodityCode = purchasingCommodityCode;
    }

    public CommodityCode getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(CommodityCode commodityCode) {
        this.commodityCode = commodityCode;
    }
    
}
