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

public class AssetYearEndDepreciationDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer universityFiscalYear;
    private Long capitalAssetNumber;

    public AssetYearEndDepreciationDetailId() {
    }

    public AssetYearEndDepreciationDetailId(Integer universityFiscalYear, Long capitalAssetNumber) {
        this.universityFiscalYear = universityFiscalYear;
        this.capitalAssetNumber = capitalAssetNumber;
    }

    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    public Long getCapitalAssetNumber() {
        return capitalAssetNumber;
    }

    public void setCapitalAssetNumber(Long capitalAssetNumber) {
        this.capitalAssetNumber = capitalAssetNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetYearEndDepreciationDetailId that = (AssetYearEndDepreciationDetailId) o;
        return java.util.Objects.equals(universityFiscalYear, that.universityFiscalYear) && java.util.Objects.equals(capitalAssetNumber, that.capitalAssetNumber);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(universityFiscalYear, capitalAssetNumber);
    }
}
