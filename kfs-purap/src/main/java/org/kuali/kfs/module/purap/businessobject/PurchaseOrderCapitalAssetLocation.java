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

import org.kuali.kfs.integration.purap.CapitalAssetSystem;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;



/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */

@Entity
@Table(name = "PUR_PO_CPTL_AST_LOC_T")
public class PurchaseOrderCapitalAssetLocation extends PurchasingCapitalAssetLocationBase {

    @Transient
    protected CapitalAssetSystem purchaseOrderCapitalAssetSystem;

	/**
	 * Default constructor.
	 */
	public PurchaseOrderCapitalAssetLocation() {
	    super();
	}

	public CapitalAssetSystem getPurchaseOrderCapitalAssetSystem() {
        return purchaseOrderCapitalAssetSystem;
    }

    public void setPurchaseOrderCapitalAssetSystem(CapitalAssetSystem purchaseOrderCapitalAssetSystem) {
        this.purchaseOrderCapitalAssetSystem = purchaseOrderCapitalAssetSystem;
    }

}
