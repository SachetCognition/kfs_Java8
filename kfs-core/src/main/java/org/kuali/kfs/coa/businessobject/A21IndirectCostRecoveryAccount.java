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

package org.kuali.kfs.coa.businessobject;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.LinkedHashMap;

import org.springframework.beans.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IndirectCostRecoveryAccount for A21SubAccount
 */
@Entity
@Table(name = "CA_A21_ICR_ACCT_T")
@AttributeOverride(name = "indirectCostRecoveryAccountGeneratedIdentifier", column = @Column(name = "CA_A21_ICR_ACCT_GNRTD_ID"))

public class A21IndirectCostRecoveryAccount extends IndirectCostRecoveryAccount {
    private static Logger LOG = LoggerFactory.getLogger(A21IndirectCostRecoveryAccount.class);

    //additional foreign keys to SubAccount
    @Column(name = "SUB_ACCT_NBR")
    private String subAccountNumber;
    
    /**
     * Default constructor.
     */
    public A21IndirectCostRecoveryAccount() {
    }
    
    /**
     * private constructor 
     * 
     * @param icr
     */
    private A21IndirectCostRecoveryAccount(IndirectCostRecoveryAccount icr) {
        BeanUtils.copyProperties(icr,this);
    }
    
    /**
     * static instantiate an A21ICRAccount from an ICRAccount
     *
     * @param icrAccount
     * @return
     */
    public static A21IndirectCostRecoveryAccount copyICRAccount(IndirectCostRecoveryAccount icrAccount) {
        return new A21IndirectCostRecoveryAccount(icrAccount); 
    }

    public Integer getA21IndirectCostRecoveryAccountGeneratedIdentifier() {
        return getIndirectCostRecoveryAccountGeneratedIdentifier();
    }

    public void setA21IndirectCostRecoveryAccountGeneratedIdentifier(Integer a21IndirectCostRecoveryAccountGeneratedIdentifier) {
        setIndirectCostRecoveryAccountGeneratedIdentifier(a21IndirectCostRecoveryAccountGeneratedIdentifier);
    }

    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap<String, String> m = new LinkedHashMap<String, String>();
        if (this.getA21IndirectCostRecoveryAccountGeneratedIdentifier() != null) {
            m.put("a21IndirectCostRecoveryAccountGeneratedIdentifier", this.getA21IndirectCostRecoveryAccountGeneratedIdentifier().toString());
        }
        return m;
    }

}
