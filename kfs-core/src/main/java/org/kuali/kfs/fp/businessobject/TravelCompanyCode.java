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

package org.kuali.kfs.fp.businessobject;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.KualiCodeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;

/**
 * This class is used to represent a travel company code business object.
 */
@Entity
@Table(name = "FP_DV_TRVL_CO_NM_T")
@AttributeOverrides({
    @AttributeOverride(name = "code", column = @Column(name = "DV_EXP_CD")),
    @AttributeOverride(name = "name", column = @Column(name = "DV_EXP_CO_NM")),
    @AttributeOverride(name = "active", column = @Column(name = "ROW_ACTV_IND"))
})
@IdClass(TravelCompanyCodeId.class)
public class TravelCompanyCode extends KualiCodeBase implements MutableInactivatable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DV_EXP_CD", insertable = false, updatable = false)
    private TravelExpenseTypeCode travelExpenseTypeCode;
    
    @Column(name = "FRGN_CMPNY")
    private boolean foreignCompany = false;

    /**
     * Default no-arg constructor.
     */
    public TravelCompanyCode() {

    }

    /**
     * Gets the travelExpenseTypeCode attribute.
     * 
     * @return Returns the travelExpenseTypeCode.
     */
    public TravelExpenseTypeCode getTravelExpenseTypeCode() {
        return travelExpenseTypeCode;
    }

    /**
     * Sets the travelExpenseTypeCode attribute value.
     * 
     * @param travelExpenseTypeCode The travelExpenseTypeCode to set.
     */
    public void setTravelExpenseTypeCode(TravelExpenseTypeCode travelExpenseTypeCode) {
        this.travelExpenseTypeCode = travelExpenseTypeCode;
    }

    public boolean isForeignCompany() {
        return foreignCompany;
    }

    public void setForeignCompany(boolean foreignCompany) {
        this.foreignCompany = foreignCompany;
    }

}
