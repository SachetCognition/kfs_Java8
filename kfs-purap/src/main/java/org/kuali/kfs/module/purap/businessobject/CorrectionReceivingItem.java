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

import org.kuali.kfs.module.purap.document.CorrectionReceivingDocument;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
@Entity
@Table(name = "PUR_RCVNG_COR_ITM_T")
@AttributeOverrides({
    @AttributeOverride(name = "receivingItemIdentifier", column = @Column(name = "RCVNG_COR_ITM_ID")),
    @AttributeOverride(name = "itemReceivedTotalQuantity", column = @Column(name = "ITM_COR_RCVD_TOT_QTY")),
    @AttributeOverride(name = "itemReturnedTotalQuantity", column = @Column(name = "ITM_COR_RTRN_TOT_QTY")),
    @AttributeOverride(name = "itemDamagedTotalQuantity", column = @Column(name = "ITM_COR_DMGED_TOT_QTY"))
})
public class CorrectionReceivingItem extends ReceivingItemBase {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FDOC_NBR", insertable = false, updatable = false)
	private CorrectionReceivingDocument correctionReceivingDocument;
    
	/**
	 * Default constructor.
	 */
	public CorrectionReceivingItem() {

	}

    public CorrectionReceivingItem(LineItemReceivingItem rli, CorrectionReceivingDocument rcd){
        
        this.setDocumentNumber( rcd.getDocumentNumber() );        
        this.setItemTypeCode( rli.getItemTypeCode() );
        this.setPurchaseOrderIdentifier(rli.getPurchaseOrderIdentifier());
        
        this.setItemLineNumber( rli.getItemLineNumber() );
        this.setItemCatalogNumber( rli.getItemCatalogNumber() );
        this.setItemDescription( rli.getItemDescription() );        
        this.setItemUnitOfMeasureCode( rli.getItemUnitOfMeasureCode() );
                
        this.setItemOriginalReceivedTotalQuantity( rli.getItemReceivedTotalQuantity() );
        this.setItemOriginalReturnedTotalQuantity( rli.getItemReturnedTotalQuantity() );
        this.setItemOriginalDamagedTotalQuantity( rli.getItemDamagedTotalQuantity() );

        this.setItemReceivedTotalQuantity(rli.getItemReceivedTotalQuantity());
        this.setItemReturnedTotalQuantity(rli.getItemReturnedTotalQuantity());
        this.setItemDamagedTotalQuantity(rli.getItemDamagedTotalQuantity());

        //not added
        this.setItemReasonAddedCode(null);
    }


    public CorrectionReceivingDocument getCorrectionReceivingDocument() {
        return correctionReceivingDocument;
    }

    /**
     * Sets the receivingCorrectionDocument attribute value.
     * @param receivingCorrectionDocument The receivingCorrectionDocument to set.
     * @deprecated
     */
    public void setCorrectionReceivingDocument(CorrectionReceivingDocument correctionReceivingDocument) {
        this.correctionReceivingDocument = correctionReceivingDocument;
    }

    
}
