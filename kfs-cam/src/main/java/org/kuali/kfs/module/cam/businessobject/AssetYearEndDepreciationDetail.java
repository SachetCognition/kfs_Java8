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

import java.util.LinkedHashMap;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.GlobalBusinessObjectDetailBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.Type;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */

@Entity

@Table(name = "CM_FSCL_YR_END_DEPR_DTL_T")

@IdClass(AssetYearEndDepreciationDetailId.class)

public class AssetYearEndDepreciationDetail extends GlobalBusinessObjectDetailBase implements MutableInactivatable {
    @Id
    @Column(name = "UNIV_FISCAL_YR")
    private Integer universityFiscalYear;
    @Id
    @Column(name = "CPTLAST_NBR")
    private Long capitalAssetNumber;
    @Column(name = "YEAR_END_DEPR_DTL_ACTV_IND")
    @Type(type = "yes_no")
    private boolean active;
    @Column(name = "YEAR_END_DEPR_DTL_PROC_IND")
    @Type(type = "yes_no")
    private boolean processed;
    @Transient
    private Asset asset;
    private AssetYearEndDepreciation assetYearEndDepreciation;

    /**
     * Default constructor.
     */
    public AssetYearEndDepreciationDetail() {
        setActive(true);
    }

    /**
     * univeristy fiscal year 
     * @return universityFiscalYear
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * Set the universityFiscalYear
     * @param universityFiscalYear
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    /**
     * Gets the capitalAssetNumber attribute.
     * 
     * @return Returns the capitalAssetNumber
     */
    public Long getCapitalAssetNumber() {
        return capitalAssetNumber;
    }

    /**
     * Sets the capitalAssetNumber attribute.
     * 
     * @param capitalAssetNumber The capitalAssetNumber to set.
     */
    public void setCapitalAssetNumber(Long capitalAssetNumber) {
        this.capitalAssetNumber = capitalAssetNumber;
    }

    /**
     * Is active
     * @return active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * set active status
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Is processed
     * @return processed
     */
    public boolean isProcessed() {
        return processed;
    }

    /**
     * set Processed
     * @param processed
     */
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    /**
     * Gets the asset attribute.
     * 
     * @return Returns the asset.
     */
    public Asset getAsset() {
        return asset;
    }

    /**
     * Sets the asset attribute value.
     * 
     * @param asset The asset to set.
     * @deprecated
     */
    public void setAsset(Asset asset) {
        this.asset = asset;
    }
    /**
     * get asset YearEnd Depreciation
     * @return assetYearEndDepreciation
     */
    public AssetYearEndDepreciation getAssetYearEndDepreciation() {
        return assetYearEndDepreciation;
    }

    /**
     * Sets assetYearEndDepreciation 
     * @param assetYearEndDepreciation
     */
    public void setAssetYearEndDepreciation(AssetYearEndDepreciation assetYearEndDepreciation) {
        this.assetYearEndDepreciation = assetYearEndDepreciation;
    }
    /**
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("universityFiscalYear", universityFiscalYear);
        if (capitalAssetNumber != null) {
            m.put("capitalAssetNumber", capitalAssetNumber.toString());
        }
        return m;
    }
}
