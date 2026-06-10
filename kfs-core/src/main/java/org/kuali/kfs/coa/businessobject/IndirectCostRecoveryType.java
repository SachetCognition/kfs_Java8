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
package org.kuali.kfs.coa.businessobject;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.KualiCodeBase;

/**
 * This class...
 */
@Entity
@Table(name = "CA_ICR_TYPE_T")

public class IndirectCostRecoveryType extends KualiCodeBase implements MutableInactivatable {
    
    @Id
    @Column(name = "ACCT_ICR_TYP_CD")
    private String code;
    @Column(name = "ACCT_ICR_TYP_DESC")
    private String name;
    @Column(name = "ACCT_ICR_TYP_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean active;
    @Transient
    private List indirectCostRecoveryExclusionTypeDetails;

    public IndirectCostRecoveryType () {
        indirectCostRecoveryExclusionTypeDetails = new ArrayList<IndirectCostRecoveryExclusionType>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List getIndirectCostRecoveryExclusionTypeDetails() {
        return indirectCostRecoveryExclusionTypeDetails;
    }

    public void setIndirectCostRecoveryExclusionTypeDetails(List indirectCostRecoveryExclusionTypeDetails) {
        this.indirectCostRecoveryExclusionTypeDetails = indirectCostRecoveryExclusionTypeDetails;
    }
    
}
