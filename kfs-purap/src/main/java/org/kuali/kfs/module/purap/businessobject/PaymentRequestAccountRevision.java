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

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;

import org.kuali.kfs.coa.businessobject.AccountingPeriod;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.sys.util.ObjectPopulationUtils;
import org.kuali.rice.core.api.datetime.DateTimeService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Payment Request Account Revision Business Object.
 */
@Entity
@Table(name = "AP_PMT_RQST_ACCT_CHG_T")
public class PaymentRequestAccountRevision extends PaymentRequestAccount {

    @Id
    @Column(name = "PMT_RQST_ACCT_CHG_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer accountRevisionIdentifier;
    @Column(name = "PMT_RQST_ACCT_CHG_TS")
    private Timestamp accountRevisionTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "FDOC_POST_YR", insertable = false, updatable = false), @JoinColumn(name = "FDOC_POST_PRD_CD", insertable = false, updatable = false)})
    private AccountingPeriod accountingPeriod;

    public AccountingPeriod getAccountingPeriod() {
        return accountingPeriod;
    }

    public void setAccountingPeriod(AccountingPeriod accountingPeriod) {
        this.accountingPeriod = accountingPeriod;
    }

    /**
     * Default constructor.
     */
    public PaymentRequestAccountRevision() {

    }

    /**
     * Constructor.
     *
     * @param account - payment request account
     */
    public PaymentRequestAccountRevision(PaymentRequestAccount pra, Integer postingYear, String postingPeriodCode) {
        // copy base attributes
        ObjectPopulationUtils.populateFromBaseWithSuper(pra, this, new HashMap<String, Class<?>>(), new HashSet<Class>());
        this.setAccountRevisionTimestamp(SpringContext.getBean(DateTimeService.class).getCurrentTimestamp());
        this.setPostingYear(postingYear);
        this.setPostingPeriodCode(postingPeriodCode);
    }

    public Integer getAccountRevisionIdentifier() {
        return accountRevisionIdentifier;
    }

    public void setAccountRevisionIdentifier(Integer accountRevisionIdentifier) {
        this.accountRevisionIdentifier = accountRevisionIdentifier;
    }

    public Timestamp getAccountRevisionTimestamp() {
        return accountRevisionTimestamp;
    }

    public void setAccountRevisionTimestamp(Timestamp accountRevisionTimestamp) {
        this.accountRevisionTimestamp = accountRevisionTimestamp;
    }

}
