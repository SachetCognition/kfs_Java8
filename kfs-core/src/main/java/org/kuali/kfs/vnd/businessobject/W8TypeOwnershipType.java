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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;

@Entity
@Table(name = "PUR_VNDR_W8_OWNRSHP_T")
public class W8TypeOwnershipType extends PersistableBusinessObjectBase implements MutableInactivatable {

    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "VNDR_OWNRSHIP_CD")
    private String vendorOwnershipCode;
    @Column(name = "VNDR_W8_TYP_CD")
    private String w8TypeCode;
    @Column(name = "ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VNDR_W8_TYP_CD", insertable = false, updatable = false)
    private W8Type w8Type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VNDR_OWNRSHIP_CD", insertable = false, updatable = false)
    private OwnershipType ownershipType;

    public W8TypeOwnershipType() {

    }


    /**
     * Gets the vendorOwnershipCode attribute.
     *
     * @return Returns the vendorOwnershipCode
     */

    public String getVendorOwnershipCode() {
        return vendorOwnershipCode;
    }
    /**
     * Sets the vendorOwnershipCode attribute.
     *
     * @param vendorOwnershipCode The vendorOwnershipCode to set.
     */
    public void setVendorOwnershipCode(String vendorOwnershipCode) {
        this.vendorOwnershipCode = vendorOwnershipCode;
    }
    /**
     * Gets the ownershipType attribute.
     *
     * @return Returns the ownershipType
     */

    public OwnershipType getOwnershipType() {
        return ownershipType;
    }
    /**
     * Sets the ownershipType attribute.
     *
     * @param ownershipType The ownershipType to set.
     */
    @Deprecated
    public void setOwnershipType(OwnershipType ownershipType) {
        this.ownershipType = ownershipType;
    }
    /**
     * Gets the w8TypeCode attribute.
     *
     * @return Returns the w8TypeCode
     */

    public String getW8TypeCode() {
        return w8TypeCode;
    }
    /**
     * Sets the w8TypeCode attribute.
     *
     * @param w8TypeCode The w8TypeCode to set.
     */
    public void setW8TypeCode(String w8TypeCode) {
        this.w8TypeCode = w8TypeCode;
    }
    /**
     * Gets the active attribute.
     *
     * @return Returns the active
     */

    @Override
    public boolean isActive() {
        return active;
    }
    /**
     * Sets the active attribute.
     *
     * @param active The active to set.
     */
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
    /**
     * Gets the w8Type attribute.
     *
     * @return Returns the w8Type
     */

    public W8Type getW8Type() {
        return w8Type;
    }
    /**
     * Sets the w8Type attribute.
     *
     * @param w8Type The w8Type to set.
     */
    @Deprecated
    public void setW8Type(W8Type w8Type) {
        this.w8Type = w8Type;
    }
    /**
     * Gets the id attribute.
     *
     * @return Returns the id
     */

    public Integer getId() {
        return id;
    }
    /**
     * Sets the id attribute.
     *
     * @param id The id to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

}
