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
/*
 * Created on Jul 8, 2004
 *
 */
package org.kuali.kfs.pdp.businessobject;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.TimestampedBusinessObjectBase;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.core.api.util.type.KualiInteger;
import org.kuali.rice.kim.api.identity.Person;

@Entity
@Table(name = "PDP_FIL_T")
public class Batch extends TimestampedBusinessObjectBase {

    @Id
    @Column(name = "PMT_FIL_ID")
    private KualiInteger id;

    @Column(name = "CUST_ID")
    private KualiInteger customerId;

    @Column(name = "PMT_FL_NM")
    private String paymentFileName;

    @Column(name = "CUST_FL_CRTN_TS")
    private Timestamp customerFileCreateTimestamp;

    @Column(name = "PMT_CNT")
    private KualiInteger paymentCount;

    @Column(name = "PMT_TOT_AMT")
    private KualiDecimal paymentTotalAmount;

    @Column(name = "SBMTR_USR_ID")
    private String submiterUserId;

    @Column(name = "FL_PROC_TS")
    private Timestamp fileProcessTimestamp;

    @Transient
    private CustomerProfile customerProfile;
    @Transient
    private Person submiterUser;
    
    public Batch() {
        super();
    }

    public Timestamp getFileProcessTimestamp() {
        return fileProcessTimestamp;
    }

    /**
     * @return
     */
    public KualiInteger getId() {
        return id;
    }

    /**
     * @return
     */
    public Timestamp getCustomerFileCreateTimestamp() {
        return customerFileCreateTimestamp;
    }

    /**
     * @return
     */
    public KualiInteger getPaymentCount() {
        return paymentCount;
    }

    /**
     * @return
     */
    public String getPaymentFileName() {
        return paymentFileName;
    }

    /**
     * @return
     */
    public KualiDecimal getPaymentTotalAmount() {
        return paymentTotalAmount;
    }

    /**
     * @return
     */
    public CustomerProfile getCustomerProfile() {
        return customerProfile;
    }

    /**
     * @param string
     */
    public void setCustomerFileCreateTimestamp(Timestamp t) {
        customerFileCreateTimestamp = t;
    }

    /**
     * @param timestamp
     */
    public void setFileProcessTimestamp(Timestamp timestamp) {
        fileProcessTimestamp = timestamp;
    }

    /**
     * @param integer
     */
    public void setId(KualiInteger integer) {
        id = integer;
    }

    /**
     * @param integer
     */
    public void setPaymentCount(KualiInteger integer) {
        paymentCount = integer;
    }

    /**
     * @param string
     */
    public void setPaymentFileName(String string) {
        paymentFileName = string;
    }

    /**
     * @param decimal
     */
    public void setPaymentTotalAmount(KualiDecimal decimal) {
        paymentTotalAmount = decimal;
    }

    /**
     * @param integer
     */
    public void setCustomerProfile(CustomerProfile cp) {
        customerProfile = cp;
    }

    public Person getSubmiterUser() {
        submiterUser = SpringContext.getBean(org.kuali.rice.kim.api.identity.PersonService.class).updatePersonIfNecessary(submiterUserId, submiterUser);
        return submiterUser;
    }

    public void setSubmiterUser(Person s) {
        this.submiterUser = s;
    }

    /**
     * @return Returns the submiterUserId.
     */
    public String getSubmiterUserId() {
        return submiterUserId;
    }

    /**
     * @param submiterUserId The submiterUserId to set.
     */
    public void setSubmiterUserId(String submiterUserId) {
        this.submiterUserId = submiterUserId;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        
        m.put(KFSPropertyConstants.ID, this.id);

        return m;
    }
   
}

