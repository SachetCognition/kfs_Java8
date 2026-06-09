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
package org.kuali.kfs.module.cam.businessobject;

import java.io.Serializable;

public class AssetLockId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Long capitalAssetNumber;
    private String lockingInformation;

    public AssetLockId() {
    }

    public AssetLockId(String documentNumber, Long capitalAssetNumber, String lockingInformation) {
        this.documentNumber = documentNumber;
        this.capitalAssetNumber = capitalAssetNumber;
        this.lockingInformation = lockingInformation;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Long getCapitalAssetNumber() {
        return capitalAssetNumber;
    }

    public void setCapitalAssetNumber(Long capitalAssetNumber) {
        this.capitalAssetNumber = capitalAssetNumber;
    }

    public String getLockingInformation() {
        return lockingInformation;
    }

    public void setLockingInformation(String lockingInformation) {
        this.lockingInformation = lockingInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetLockId that = (AssetLockId) o;
        return java.util.Objects.equals(documentNumber, that.documentNumber) && java.util.Objects.equals(capitalAssetNumber, that.capitalAssetNumber) && java.util.Objects.equals(lockingInformation, that.lockingInformation);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(documentNumber, capitalAssetNumber, lockingInformation);
    }
}
