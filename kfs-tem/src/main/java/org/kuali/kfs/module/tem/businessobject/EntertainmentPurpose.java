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
package org.kuali.kfs.module.tem.businessobject;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

/**
 * Entertainment Purpose
 *
 */
@Entity
@Table(name="TEM_ENT_PURPOSE_T")
public class EntertainmentPurpose extends PersistableBusinessObjectBase implements MutableInactivatable{
    @Id
    @Column(name = "PURPOSE_CODE", length = 4, nullable = false)
    private String purposeCode;
    @Column(name = "PURPOSE_NAME", length = 40, nullable = true)
    private String purposeName;
    @Column(name = "PURPOSE_DESCRIPTION", length = 100, nullable = true)
    private String purposeDescription;
    @Column(name = "REVIEW_REQUIRED_IND", length = 1, nullable = true)
    private Boolean reviewRequiredIndicator;
    @Column(name = "ROW_ACTV_IND", length = 1, nullable = false)
    private Boolean active = Boolean.TRUE;

    public String getPurposeCode() {
        return purposeCode;
    }
    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }
    public String getPurposeName() {
        return purposeName;
    }
    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }
    public String getPurposeDescription() {
        return purposeDescription;
    }
    public void setPurposeDescription(String purposeDescription) {
        this.purposeDescription = purposeDescription;
    }
    public Boolean getReviewRequiredIndicator() {
        return reviewRequiredIndicator;
    }
    public void setReviewRequiredIndicator(Boolean reviewRequiredIndicator) {
        this.reviewRequiredIndicator = reviewRequiredIndicator;
    }
    public Boolean isReviewRequiredIndicator(){
        return getReviewRequiredIndicator();
    }
    @Override
    public boolean isActive() {
        return this.active;
    }
    public boolean getActive(){
        return this.active;
    }
    @Override
    public void setActive(boolean active){
        this.active = active;
    }

    @SuppressWarnings("rawtypes")
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        return null;
    }


}
