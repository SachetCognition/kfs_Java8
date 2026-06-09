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

import org.kuali.kfs.vnd.VendorPropertyConstants;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.hibernate.type.YesNoConverter;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
@Entity
@Table(name = "PUR_COMM_CONTR_MGR_T")
@IdClass(CommodityContractManagerId.class)
public class CommodityContractManager extends PersistableBusinessObjectBase implements MutableInactivatable {

	@Id
	@Column(name = "PUR_COMM_CD")
	private String purchasingCommodityCode;
	@Id
	@Column(name = "CAMPUS_CD")
	private String campusCode;
	@Id
	@Column(name = "CONTR_MGR_CD")
	private Integer contractManagerCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAMPUS_CD", insertable = false, updatable = false)
    private CampusParameter campus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUR_COMM_CD", insertable = false, updatable = false)
    private CommodityCode commodityCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTR_MGR_CD", insertable = false, updatable = false)
    private ContractManager contractManager;
    @Column(name = "CONTR_MGR_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean active;
    
	/**
	 * Default constructor.
	 */
	public CommodityContractManager() {

	}

	public String getPurchasingCommodityCode() { 
		return purchasingCommodityCode;
	}

	public void setPurchasingCommodityCode(String purchasingCommodityCode) {
		this.purchasingCommodityCode = purchasingCommodityCode;
	}

	public String getCampusCode() { 
		return campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}

	public Integer getContractManagerCode() { 
		return contractManagerCode;
	}

	public void setContractManagerCode(Integer contractManagerCode) {
		this.contractManagerCode = contractManagerCode;
	}

	public CampusParameter getCampus() { 
        return campus;
	}

	/**
	 * Sets the campus attribute.
	 * 
	 * @param campus The campus to set.
	 * @deprecated
	 */
	public void setCampus(CampusParameter campus) {
		this.campus = campus;
	}


    public CommodityCode getCommodityCode() {
        return commodityCode;
    }

    /**
     * Sets the commodity attribute value.
     * @param commodityCode The commodityCode to set.
     * @deprecated
     */
    public void setCommodityCode(CommodityCode commodityCode) {
        this.commodityCode = commodityCode;
    }


    public ContractManager getContractManager() {
        return contractManager;
    }

    /**
     * Sets the contractManager attribute value.
     * @param contractManager The contractManager to set.
     * @deprecated
     */
    public void setContractManager(ContractManager contractManager) {
        this.contractManager = contractManager;
    }

    /**
	 * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put(VendorPropertyConstants.PURCHASING_COMMODITY_CODE, this.purchasingCommodityCode);
        m.put(VendorPropertyConstants.CAMPUS_CODE, this.campusCode);
        if (this.contractManagerCode != null) {
            m.put(VendorPropertyConstants.CONTRACT_MANAGER_CODE, this.contractManagerCode.toString());
        }
	    return m;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
