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

import java.sql.Date;
import java.util.Arrays;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.module.ar.ArPropertyConstants;
import org.kuali.kfs.module.ar.document.ContractsGrantsInvoiceDocument;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.ObjectUtil;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * Defines a Collection Activity Invoice Detail object.
 */
@Entity
@Table(name = "AR_CGB_CLCTN_ACTVY_INV_DTL_T")
public class ContractsGrantsCollectionActivityInvoiceDetail extends PersistableBusinessObjectBase {

    @Id
    @Column(name = "FDOC_NBR")
    private String documentNumber;
    @Id
    @Column(name = "INV_FDOC_NBR")
    private String invoiceNumber;
    @Column(name = "BILL_DT")
    private Date billingDate;
    @Column(name = "BILLNG_PRD")
    private String billingPeriod;

    @Transient
    private ContractsGrantsInvoiceDocument invoiceDocument;

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public String getBillingPeriod() {
        return billingPeriod;
    }

    public void setBillingPeriod(String billingPeriod) {
        this.billingPeriod = billingPeriod;
    }

    public ContractsGrantsInvoiceDocument getInvoiceDocument() {
        return invoiceDocument;
    }

    public void setInvoiceDocument(ContractsGrantsInvoiceDocument invoiceDocument) {
        this.invoiceDocument = invoiceDocument;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (ObjectUtils.isNotNull(obj)) {
            if (this.getClass().equals(obj.getClass())) {
                ContractsGrantsCollectionActivityInvoiceDetail other = (ContractsGrantsCollectionActivityInvoiceDetail) obj;
                return (StringUtils.equalsIgnoreCase(this.documentNumber, other.documentNumber) &&
                        StringUtils.equalsIgnoreCase(this.invoiceNumber, other.invoiceNumber));
            }
        }
        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ObjectUtil.generateHashCode(this, Arrays.asList(KFSPropertyConstants.DOCUMENT_NUMBER, ArPropertyConstants.INVOICE_NUMBER));
    }

}
