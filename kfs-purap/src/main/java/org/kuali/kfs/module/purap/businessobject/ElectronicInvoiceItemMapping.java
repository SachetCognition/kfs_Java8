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
/*
 * Created on Feb 28, 2006
 *
 */
package org.kuali.kfs.module.purap.businessobject;

import java.util.LinkedHashMap;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AP_ELCTRNC_INV_MAP_T")
public class ElectronicInvoiceItemMapping extends PersistableBusinessObjectBase implements MutableInactivatable {

    @Id
    @Column(name = "AP_ELCTRNC_INV_MAP_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer invoiceMapIdentifier;
    @Column(name = "VNDR_HDR_GNRTD_ID")
    private Integer vendorHeaderGeneratedIdentifier;
    @Column(name = "VNDR_DTL_ASND_ID")
    private Integer vendorDetailAssignedIdentifier;
    @Column(name = "ITM_TYP_CD")
    private String itemTypeCode;
    @Column(name = "INV_ITM_TYP_CD")
    private String invoiceItemTypeCode;
    @Column(name = "DOBJ_MAINT_CD_ACTV_IND")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITM_TYP_CD", insertable = false, updatable = false)
    private ItemType itemType;
    private ItemType invoiceItemType;

    /**
   * 
   */
    public ElectronicInvoiceItemMapping() {
        super();
    }

    /**
     * @return Returns the invoiceMapIdentifier.
     */
    public Integer getInvoiceMapIdentifier() {
        return invoiceMapIdentifier;
    }

    /**
     * @param invoiceMapIdentifier The invoiceMapIdentifier to set.
     */
    public void setInvoiceMapIdentifier(Integer id) {
        this.invoiceMapIdentifier = id;
    }

    /**
     * @return Returns the invoiceItemTypeCode.
     */
    public String getInvoiceItemTypeCode() {
        return invoiceItemTypeCode;
    }

    /**
     * @param invoiceItemTypeCode The invoiceItemTypeCode to set.
     */
    public void setInvoiceItemTypeCode(String electronicInvoiceItemTypeCode) {
        this.invoiceItemTypeCode = electronicInvoiceItemTypeCode;
    }

    /**
     * @return Returns the itemTypeCode.
     */
    public String getItemTypeCode() {
        return itemTypeCode;
    }

    /**
     * @param itemTypeCode The itemTypeCode to set.
     */
    public void setItemTypeCode(String itemTypeCode) {
        this.itemTypeCode = itemTypeCode;
    }

    /**
     * @return Returns the itemType.
     */
    public ItemType getItemType() {
        return itemType;
    }

    /**
     * @param itemType The itemType to set.
     */
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
        this.itemTypeCode = itemType.getItemTypeCode();
    }

    public ItemType getInvoiceItemType() {
        return invoiceItemType;
    }

    public void setInvoiceItemType(ItemType invoiceItemType) {
        this.invoiceItemType = invoiceItemType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return Returns the vendorDetailAssignedIdentifier.
     */
    public Integer getVendorDetailAssignedIdentifier() {
        return vendorDetailAssignedIdentifier;
    }

    /**
     * @param vendorDetailAssignedIdentifier The vendorDetailAssignedIdentifier to set.
     */
    public void setVendorDetailAssignedIdentifier(Integer vendorDetailAssignedId) {
        this.vendorDetailAssignedIdentifier = vendorDetailAssignedId;
    }

    /**
     * @return Returns the vendorHeaderGeneratedIdentifier.
     */
    public Integer getVendorHeaderGeneratedIdentifier() {
        return vendorHeaderGeneratedIdentifier;
    }

    /**
     * @param vendorHeaderGeneratedIdentifier The vendorHeaderGeneratedIdentifier to set.
     */
    public void setVendorHeaderGeneratedIdentifier(Integer vendorHeaderGeneratedId) {
        this.vendorHeaderGeneratedIdentifier = vendorHeaderGeneratedId;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("invoiceMapIdentifier", this.invoiceMapIdentifier);
        return m;
    }
}
