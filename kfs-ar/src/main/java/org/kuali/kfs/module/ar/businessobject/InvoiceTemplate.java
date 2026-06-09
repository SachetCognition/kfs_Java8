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
package org.kuali.kfs.module.ar.businessobject;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * Defines Invoice Template used for generation of Contracts & Grants Invoice PDFs.
 */

@Entity
@Table(name = "AR_INV_TMPLT_T")
public class InvoiceTemplate extends TemplateBase implements MutableInactivatable {

    @Id
    @Column(name = "INV_TMPLT_CD")
    private String invoiceTemplateCode;
    @Column(name = "INV_TMPLT_DESC")
    private String invoiceTemplateDescription;

    public String getInvoiceTemplateCode() {
        return invoiceTemplateCode;
    }

    public void setInvoiceTemplateCode(String invoiceTemplateCode) {
        this.invoiceTemplateCode = invoiceTemplateCode;
    }

    public String getInvoiceTemplateDescription() {
        return invoiceTemplateDescription;
    }

    public void setInvoiceTemplateDescription(String invoiceTemplateDescription) {
        this.invoiceTemplateDescription = invoiceTemplateDescription;
    }

}
