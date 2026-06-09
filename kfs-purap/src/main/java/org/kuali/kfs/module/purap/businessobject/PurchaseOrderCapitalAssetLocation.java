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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */

@Entity
@Table(name = "PUR_PO_CPTL_AST_LOC_T")
public class PurchaseOrderCapitalAssetLocation extends PurchasingCapitalAssetLocationBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPTL_AST_SYS_ID", insertable = false, updatable = false)
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
