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
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

@Entity
@Table(name = "AR_INV_DOC_T")
public class CustomerInvoiceLookup extends PersistableBusinessObjectBase {
    
    @Id
    @Column(name = "FDOC_NBR")
    protected String invoiceNumber;
    @Column(name = "CUST_NM")
    protected String customerName;    
    @Column(name = "AR_INV_DUE_DT")
    protected Date invoiceDueDate;
    @Column(name = "AR_BILLING_DT")
    protected Date billingDate;
    @Column(name = "AR_BILL_BY_COA_CD")
    protected String billByChartOfAccountCode;
    @Column(name = "AR_BILL_BY_ORG_CD")
    protected String billedByOrganizationCode;
    
    /**
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */

    protected LinkedHashMap<String,String> toStringMapper() {
        LinkedHashMap<String,String> m = new LinkedHashMap<String,String>();      
        m.put("invoiceNumber", this.invoiceNumber);
        return m;
    }
    
    /**
     * 
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * 
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * 
     */
    public Date getInvoiceDueDate() {
        return invoiceDueDate;
    }

    /**
     * 
     */
    public void setInvoiceDueDate(Date invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    /**
     * 
     */
    public Date getBillingDate() {
        return billingDate;
    }

    /**
     * 
     */
    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    /**
     * 
     */
    public String getBillByChartOfAccountCode() {
        return billByChartOfAccountCode;
    }

    /**
     * 
     */
    public void setBillByChartOfAccountCode(String billByChartOfAccountCode) {
        this.billByChartOfAccountCode = billByChartOfAccountCode;
    }

    /**
     * 
     */
    public String getBilledByOrganizationCode() {
        return billedByOrganizationCode;
    }

    /**
     * 
     */
    public void setBilledByOrganizationCode(String billedByOrganizationCode) {
        this.billedByOrganizationCode = billedByOrganizationCode;
    }
    
}
