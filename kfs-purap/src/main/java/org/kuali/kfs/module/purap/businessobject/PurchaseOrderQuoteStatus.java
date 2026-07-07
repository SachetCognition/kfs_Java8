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

package org.kuali.kfs.module.purap.businessobject;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;



/**
 * Purchase Order Quote Status Business Object.
 * 
 */
@Entity
@Table(name = "PUR_PO_QT_STAT_T")
@AttributeOverrides({
    @AttributeOverride(name = "statusCode", column = @Column(name = "PO_QT_STAT_CD"))
})
public class PurchaseOrderQuoteStatus extends Status implements MutableInactivatable{

    @Column(name = "DOBJ_MAINT_CD_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean active;
    
    /**
     * Default constructor.
     */
    public PurchaseOrderQuoteStatus() {
        super();
        super.ojbConcreteClass = this.getClass().getName();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
