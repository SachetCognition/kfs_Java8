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

package org.kuali.kfs.module.ld.businessobject;

import java.util.LinkedHashMap;

import org.kuali.kfs.integration.ld.LaborLedgerPositionObjectGroup;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import org.kuali.kfs.module.ld.persistence.converter.OjbCharBooleanConverter;

@Entity
@Table(name = "LD_POS_OBJ_GRP_T")
/**
 * Labor business object for PositionObjectGroup
 */
public class PositionObjectGroup extends PersistableBusinessObjectBase implements LaborLedgerPositionObjectGroup, MutableInactivatable {
    @Id

    @Column(name = "POS_OBJ_GRP_CD")

    private String positionObjectGroupCode;
    @Column(name = "POS_OBJ_GRP_NM")

    private String positionObjectGroupName;
    @Column(name = "ROW_ACTV_IND")

    @Convert(converter = OjbCharBooleanConverter.class)

    private boolean active;

    /**
     * Default constructor.
     */
    public PositionObjectGroup() {

    }

    /**
     * Gets the positionObjectGroupCode
     * 
     * @return Returns the positionObjectGroupCode
     */
    public String getPositionObjectGroupCode() {
        return positionObjectGroupCode;
    }

    /**
     * Sets the positionObjectGroupCode
     * 
     * @param positionObjectGroupCode The positionObjectGroupCode to set.
     */
    public void setPositionObjectGroupCode(String positionObjectGroupCode) {
        this.positionObjectGroupCode = positionObjectGroupCode;
    }

    /**
     * Gets the positionObjectGroupName
     * 
     * @return Returns the positionObjectGroupName
     */
    public String getPositionObjectGroupName() {
        return positionObjectGroupName;
    }

    /**
     * Sets the positionObjectGroupName
     * 
     * @param positionObjectGroupName The positionObjectGroupName to set.
     */
    public void setPositionObjectGroupName(String positionObjectGroupName) {
        this.positionObjectGroupName = positionObjectGroupName;
    }

    /**
     * Gets the active attribute.
     * 
     * @return Returns the active.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active attribute value.
     * 
     * @param active The active to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * @see org.kuali.kfs.integration.ld.LaborLedgerPositionObjectGroup#getActive()
     */
    public boolean getActive() {
        return active;
    }

    /**
     * construct the key list of the business object.
     * 
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("positionObjectGroupCode", this.positionObjectGroupCode);
        return m;
    }
}
