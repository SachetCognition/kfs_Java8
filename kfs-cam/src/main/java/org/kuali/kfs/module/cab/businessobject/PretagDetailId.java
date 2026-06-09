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
package org.kuali.kfs.module.cab.businessobject;

import java.io.Serializable;

public class PretagDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String purchaseOrderNumber;
    private Integer itemLineNumber;
    private String campusTagNumber;

    public PretagDetailId() {
    }

    public PretagDetailId(String purchaseOrderNumber, Integer itemLineNumber, String campusTagNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.itemLineNumber = itemLineNumber;
        this.campusTagNumber = campusTagNumber;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public Integer getItemLineNumber() {
        return itemLineNumber;
    }

    public void setItemLineNumber(Integer itemLineNumber) {
        this.itemLineNumber = itemLineNumber;
    }

    public String getCampusTagNumber() {
        return campusTagNumber;
    }

    public void setCampusTagNumber(String campusTagNumber) {
        this.campusTagNumber = campusTagNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PretagDetailId that = (PretagDetailId) o;
        return java.util.Objects.equals(purchaseOrderNumber, that.purchaseOrderNumber) && java.util.Objects.equals(itemLineNumber, that.itemLineNumber) && java.util.Objects.equals(campusTagNumber, that.campusTagNumber);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(purchaseOrderNumber, itemLineNumber, campusTagNumber);
    }
}
