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
package org.kuali.kfs.pdp.businessobject;

import org.kuali.rice.krad.bo.KualiCodeBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;

@Entity
@Table(name = "PDP_ACH_TRANS_TYP_T")
@AttributeOverrides({
    @AttributeOverride(name = "code", column = @Column(name = "TRANS_TYP")),
    @AttributeOverride(name = "name", column = @Column(name = "TRANS_TYP_DESC"))
})
public class ACHTransactionType extends KualiCodeBase {
    
    /**
     * Constructs a AchTransactionType.java.
     */
    public ACHTransactionType() {
        super();
    }



    @Override
    @Id
    @Column(name = "TRANS_TYP")
    @jakarta.persistence.Access(jakarta.persistence.AccessType.PROPERTY)
    public String getCode() {
        return super.getCode();
    }

    @Override
    @Column(name = "TRANS_TYP_DESC")
    @jakarta.persistence.Access(jakarta.persistence.AccessType.PROPERTY)
    public String getName() {
        return super.getName();
    }

}
