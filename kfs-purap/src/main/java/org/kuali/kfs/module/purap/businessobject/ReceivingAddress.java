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

import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.Organization;
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
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;


/**
 * Receiving Address Business Object. 
 * Used when an institution has products shipped by vendors to their "central receiving" organziation, 
 * which will then deliever the products to the final delivery addresses.
 * ReceivingAddress defines all the required address fields as well as an indicator to decide whether the 
 * receiving address or the final delivery address will be used as the shipping address provided to a vendor.
 */
@Entity
@Table(name = "PUR_RCVNG_ADDR_T")
public class ReceivingAddress extends PersistableBusinessObjectBase implements MutableInactivatable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUR_RCVNG_ADDR_ID")
    private Integer receivingAddressIdentifier;
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;    
    @Column(name = "ORG_CD")
    private String organizationCode;
    @Column(name = "PUR_RCVNG_NM")
    private String receivingName;
    @Column(name = "PUR_RCVNG_LN1_ADDR")
    private String receivingLine1Address;
    @Column(name = "PUR_RCVNG_LN2_ADDR")
    private String receivingLine2Address;
    @Column(name = "PUR_RCVNG_CTY_NM")
    private String receivingCityName;
    @Column(name = "PUR_RCVNG_ST_CD")
    private String receivingStateCode;
    @Column(name = "PUR_RCVNG_PSTL_CD")
    private String receivingPostalCode;
    @Column(name = "PUR_RCVNG_CNTRY_CD")
    private String receivingCountryCode;
    @Column(name = "VNDR_ADDR_USE_RCVNG_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean useReceivingIndicator;
    @Column(name = "PUR_RCVNG_DFLT_ADDR_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean defaultIndicator;
    @Column(name = "DOBJ_MAINT_CD_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false)
    private Chart chartOfAccounts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false),
            @JoinColumn(name = "ORG_CD", insertable = false, updatable = false)
    })
    private Organization organization;

    /**
     * Default constructor.
     */
    public ReceivingAddress() {
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    public boolean isDefaultIndicator() {
        return defaultIndicator;
    }

    public void setDefaultIndicator(boolean defaultIndicator) {
        this.defaultIndicator = defaultIndicator;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public Integer getReceivingAddressIdentifier() {
        return receivingAddressIdentifier;
    }

    public void setReceivingAddressIdentifier(Integer receivingAddressIdentifier) {
        this.receivingAddressIdentifier = receivingAddressIdentifier;
    }

    public String getReceivingCityName() {
        return receivingCityName;
    }

    public void setReceivingCityName(String receivingCityName) {
        this.receivingCityName = receivingCityName;
    }

    public String getReceivingCountryCode() {
        return receivingCountryCode;
    }

    public void setReceivingCountryCode(String receivingCountryCode) {
        this.receivingCountryCode = receivingCountryCode;
    }

    public String getReceivingLine1Address() {
        return receivingLine1Address;
    }

    public void setReceivingLine1Address(String receivingLine1Address) {
        this.receivingLine1Address = receivingLine1Address;
    }

    public String getReceivingLine2Address() {
        return receivingLine2Address;
    }

    public void setReceivingLine2Address(String receivingLine2Address) {
        this.receivingLine2Address = receivingLine2Address;
    }

    public String getReceivingName() {
        return receivingName;
    }

    public void setReceivingName(String receivingName) {
        this.receivingName = receivingName;
    }

    public String getReceivingPostalCode() {
        return receivingPostalCode;
    }

    public void setReceivingPostalCode(String receivingPostalCode) {
        this.receivingPostalCode = receivingPostalCode;
    }

    public String getReceivingStateCode() {
        return receivingStateCode;
    }

    public void setReceivingStateCode(String receivingStateCode) {
        this.receivingStateCode = receivingStateCode;
    }

    public boolean isUseReceivingIndicator() {
        return useReceivingIndicator;
    }

    public void setUseReceivingIndicator(boolean useReceivingIndicator) {
        this.useReceivingIndicator = useReceivingIndicator;
    }
    
    public Chart getChartOfAccounts() {
        return chartOfAccounts;
    }

    /**
     * @deprecated
     */
    public void setChartOfAccounts(Chart chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
    }

    public Organization getOrganization() {
        return organization;
    }

    /**
     * @deprecated
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        return m;
    }
}
