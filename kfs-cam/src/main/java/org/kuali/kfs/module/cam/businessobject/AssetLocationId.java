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

public class AssetLocationId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long capitalAssetNumber;
    private String assetLocationTypeCode;

    public AssetLocationId() {
    }

    public AssetLocationId(Long capitalAssetNumber, String assetLocationTypeCode) {
        this.capitalAssetNumber = capitalAssetNumber;
        this.assetLocationTypeCode = assetLocationTypeCode;
    }

    public Long getCapitalAssetNumber() {
        return capitalAssetNumber;
    }

    public void setCapitalAssetNumber(Long capitalAssetNumber) {
        this.capitalAssetNumber = capitalAssetNumber;
    }

    public String getAssetLocationTypeCode() {
        return assetLocationTypeCode;
    }

    public void setAssetLocationTypeCode(String assetLocationTypeCode) {
        this.assetLocationTypeCode = assetLocationTypeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetLocationId that = (AssetLocationId) o;
        return java.util.Objects.equals(capitalAssetNumber, that.capitalAssetNumber) && java.util.Objects.equals(assetLocationTypeCode, that.assetLocationTypeCode);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(capitalAssetNumber, assetLocationTypeCode);
    }
}
