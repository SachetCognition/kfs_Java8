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

package org.kuali.kfs.module.cg.businessobject;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.KualiCodeBase;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.type.YesNoConverter;

/**
 * Extends KualiCodeBase with no changes.
 */
@Entity
@Table(name = "CG_AGENCY_TYP_T")
@Access(AccessType.PROPERTY)
public class AgencyType extends KualiCodeBase implements MutableInactivatable {

    @Id
    @Column(name = "CG_AGENCY_TYP_CD")
    @Override
    public String getCode() {
        return super.getCode();
    }

    @Column(name = "CG_AGENCY_TYP_DESC")
    @Override
    public String getName() {
        return super.getName();
    }

    @Column(name = "ROW_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    @Override
    public boolean isActive() {
        return super.isActive();
    }
}
