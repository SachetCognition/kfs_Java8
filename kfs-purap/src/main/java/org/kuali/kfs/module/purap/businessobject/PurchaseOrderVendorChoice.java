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

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;


/**
 * Purchase Order Vendor Choice Business Object.
 */
@Entity
@Table(name = "PUR_PO_VNDR_CHC_T")
public class PurchaseOrderVendorChoice extends PersistableBusinessObjectBase implements MutableInactivatable{

    @Id
    @Column(name = "PO_VNDR_CHC_CD")
    private String purchaseOrderVendorChoiceCode;
    @Column(name = "PO_VNDR_CHC_DESC")
    private String purchaseOrderVendorChoiceDescription;
    @Column(name = "DOBJ_MAINT_CD_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean active;

    /**
     * Default constructor.
     */
    public PurchaseOrderVendorChoice() {

    }

    public String getPurchaseOrderVendorChoiceCode() {
        return purchaseOrderVendorChoiceCode;
    }

    public void setPurchaseOrderVendorChoiceCode(String purchaseOrderVendorChoiceCode) {
        this.purchaseOrderVendorChoiceCode = purchaseOrderVendorChoiceCode;
    }

    public String getPurchaseOrderVendorChoiceDescription() {
        return purchaseOrderVendorChoiceDescription;
    }

    public void setPurchaseOrderVendorChoiceDescription(String purchaseOrderVendorChoiceDescription) {
        this.purchaseOrderVendorChoiceDescription = purchaseOrderVendorChoiceDescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("purchaseOrderVendorChoiceCode", this.purchaseOrderVendorChoiceCode);
        return m;
    }

}
