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

import java.util.LinkedHashMap;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;


/**
 * Electronic Invoice Reject Reason Type Code Business Object.
 */
@Entity
@Table(name = "AP_ELCTRNC_INV_RJT_REAS_TYP_T")
public class ElectronicInvoiceRejectReasonType extends PersistableBusinessObjectBase implements MutableInactivatable{

    @Id
    @Column(name = "INV_RJT_REAS_TYP_CD")
    private String invoiceRejectReasonTypeCode;
    @Column(name = "INV_RJT_REAS_TYP_DESC")
    private String invoiceRejectReasonTypeDescription;
    /*
     * Indicates whether this reject reason will cause a INVOICE (if true) or a FILE (if false) reject document.
     */
    @Column(name = "INV_FAIL_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean invoiceFailureIndicator;
    @Column(name = "INV_RJT_REAS_PERF_MTCH_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean performMatchingIndicator;
    @Column(name = "DOBJ_MAINT_CD_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean active;

    /**
     * Default constructor.
     */
    public ElectronicInvoiceRejectReasonType() {

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean dataObjectMaintenanceCodeActiveIndicator) {
        this.active = dataObjectMaintenanceCodeActiveIndicator;
    }

    public String getInvoiceRejectReasonTypeCode() {
        return invoiceRejectReasonTypeCode;
    }

    public void setInvoiceRejectReasonTypeCode(String invoiceRejectReasonTypeCode) {
        this.invoiceRejectReasonTypeCode = invoiceRejectReasonTypeCode;
    }

    public String getInvoiceRejectReasonTypeDescription() {
        return invoiceRejectReasonTypeDescription;
    }

    public void setInvoiceRejectReasonTypeDescription(String invoiceRejectReasonTypeDescription) {
        this.invoiceRejectReasonTypeDescription = invoiceRejectReasonTypeDescription;
    }

    public boolean isInvoiceFailureIndicator() {
        return invoiceFailureIndicator;
    }

    public void setInvoiceFailureIndicator(boolean invoiceFailureIndicator) {
        this.invoiceFailureIndicator = invoiceFailureIndicator;
    }

    public boolean isPerformMatchingIndicator() {
        return performMatchingIndicator;
    }

    public void setPerformMatchingIndicator(boolean performMatchingIndicator) {
        this.performMatchingIndicator = performMatchingIndicator;
    }
    
    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("invoiceRejectReasonTypeCode", this.invoiceRejectReasonTypeCode);
        return m;
    }
}

