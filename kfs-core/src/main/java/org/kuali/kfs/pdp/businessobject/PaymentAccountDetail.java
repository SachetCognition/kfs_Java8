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
 * Created on Jul 12, 2004
 *
 */
package org.kuali.kfs.pdp.businessobject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectCodeCurrent;
import org.kuali.kfs.coa.businessobject.ProjectCode;
import org.kuali.kfs.coa.businessobject.SubAccount;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.TimestampedBusinessObjectBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.core.api.util.type.KualiInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "PDP_PMT_ACCT_DTL_T")
public class PaymentAccountDetail extends TimestampedBusinessObjectBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PMT_ACCT_DTL_ID")
    private KualiInteger id; // PMT_ACCT_DTL_ID
    @Column(name = "FIN_COA_CD")
    private String finChartCode; // FIN_COA_CD
    @Column(name = "ACCOUNT_NBR")
    private String accountNbr; // ACCOUNT_NBR
    @Column(name = "SUB_ACCT_NBR")
    private String subAccountNbr; // SUB_ACCT_NBR
    @Column(name = "FIN_OBJECT_CD")
    private String finObjectCode; // FIN_OBJECT_CD
    @Column(name = "FIN_SUB_OBJ_CD")
    private String finSubObjectCode; // FIN_SUB_OBJ_CD
    @Column(name = "ORG_REFERENCE_ID")
    private String orgReferenceId; // ORG_REFERENCE_ID
    @Column(name = "PROJECT_CD")
    private String projectCode; // PROJECT_CD
    @Column(name = "ACCT_NET_AMT")
    private KualiDecimal accountNetAmount; // ACCT_NET_AMT

    @Column(name = "PMT_DTL_ID")
    private KualiInteger paymentDetailId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PMT_DTL_ID", insertable = false, updatable = false)
    private PaymentDetail paymentDetail; // PMT_DTL_ID

    @OneToMany(fetch = FetchType.LAZY)
    private List<PaymentAccountHistory> accountHistory = new ArrayList<PaymentAccountHistory>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false)
    private Chart chartOfAccounts;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    private SubAccount subAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_CD", insertable = false, updatable = false)
    private ProjectCode project;
    @ManyToOne(fetch = FetchType.LAZY)
    private ObjectCodeCurrent objectCode;

    /**
     * Constructs a PaymentAccountDetail.java.
     */
    public PaymentAccountDetail() {
        super();
    }

    /**
     * This method gets the accountHistory list.
     * 
     * @return the accountHistory list
     */
    public List<PaymentAccountHistory> getAccountHistory() {
        return accountHistory;
    }

    /**
     * This method sets the accountHistory list
     * 
     * @param ah
     */
    public void setAccountHistory(List<PaymentAccountHistory> ah) {
        accountHistory = ah;
    }

    /**
     * This method add a new PaymentAccountHistory.
     * 
     * @param pah
     */
    public void addAccountHistory(PaymentAccountHistory pah) {
        pah.setPaymentAccountDetail(this);
        accountHistory.add(pah);
    }

    /**
     * This method deletes a PaymentAccountHistory.
     * 
     * @param pah
     */
    public void deleteAccountDetail(PaymentAccountHistory pah) {
        accountHistory.remove(pah);
    }

    /**
     * @hibernate.id column="PMT_ACCT_DTL_ID" generator-class="sequence"
     * @hibernate.generator-param name="sequence" value="PDP.PDP_PMT_ACCT_DTL_ID_SEQ"
     * @return
     */
    public KualiInteger getId() {
        return id;
    }

    /**
     * @return
     * @hibernate.many-to-one column="PMT_DTL_ID" class="edu.iu.uis.pdp.bo.PaymentDetail"
     */
    public PaymentDetail getPaymentDetail() {
        return this.paymentDetail;
    }

    /**
     * @return
     * @hibernate.property column="ACCOUNT_NBR" length="7"
     */
    public String getAccountNbr() {
        return accountNbr;
    }

    /**
     * @return
     * @hibernate.property column="ACCT_NET_AMT" length="14"
     */
    public KualiDecimal getAccountNetAmount() {
        return accountNetAmount;
    }

    /**
     * @return
     * @hibernate.property column="FIN_COA_CD" length="2"
     */
    public String getFinChartCode() {
        return finChartCode;
    }

    /**
     * @return
     * @hibernate.property column="FIN_OBJECT_CD" length="4"
     */
    public String getFinObjectCode() {
        return finObjectCode;
    }

    /**
     * @return
     * @hibernate.property column="FIN_SUB_OBJ_CD" length="3"
     */
    public String getFinSubObjectCode() {
        return finSubObjectCode;
    }

    /**
     * @return
     * @hibernate.property column="ORG_REFERENCE_ID" length="8"
     */
    public String getOrgReferenceId() {
        return orgReferenceId;
    }

    /**
     * @return
     * @hibernate.property column="PROJECT_CD" length="10"
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * @return
     * @hibernate.property column="SUB_ACCT_NBR" length="5"
     */
    public String getSubAccountNbr() {
        return subAccountNbr;
    }

    /**
     * @param string
     */
    public void setAccountNbr(String string) {
        accountNbr = string;
    }

    /**
     * @param string
     */
    public void setAccountNetAmount(KualiDecimal bigdecimal) {
        accountNetAmount = bigdecimal;
    }

    public void setAccountNetAmount(String bigdecimal) {
        accountNetAmount = new KualiDecimal(bigdecimal);
    }

    /**
     * @param integer
     */
    public void setPaymentDetail(PaymentDetail pd) {
        paymentDetail = pd;
    }

    /**
     * @param string
     */
    public void setFinChartCode(String string) {
        finChartCode = string;
    }

    /**
     * @param string
     */
    public void setFinObjectCode(String string) {
        finObjectCode = string;
    }

    /**
     * @param string
     */
    public void setFinSubObjectCode(String string) {
        finSubObjectCode = string;
    }

    /**
     * @param integer
     */
    public void setId(KualiInteger integer) {
        id = integer;
    }

    /**
     * @param string
     */
    public void setOrgReferenceId(String string) {
        orgReferenceId = string;
    }

    /**
     * @param string
     */
    public void setProjectCode(String string) {
        projectCode = string;
    }

    /**
     * @param string
     */
    public void setSubAccountNbr(String string) {
        subAccountNbr = string;
    }

    /**
     * Gets the paymentDetailId attribute.
     * 
     * @return Returns the paymentDetailId.
     */
    public KualiInteger getPaymentDetailId() {
        return paymentDetailId;
    }

    /**
     * Sets the paymentDetailId attribute value.
     * 
     * @param paymentDetailId The paymentDetailId to set.
     */
    public void setPaymentDetailId(KualiInteger paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();

        m.put(KFSPropertyConstants.ID, this.id);

        return m;
    }

    /**
     * This method gets the account.
     * 
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * This method sets the account.
     * 
     * @param account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * This method gets the subAccount.
     * 
     * @return the subAccount
     */
    public SubAccount getSubAccount() {
        return subAccount;
    }

    /**
     * This method sets the subAccount.
     * 
     * @param subAccount
     */
    public void setSubAccount(SubAccount subAccount) {
        this.subAccount = subAccount;
    }

    /**
     * This method gets the chart of accounts.
     * 
     * @return the chart of accounts
     */
    public Chart getChartOfAccounts() {
        return chartOfAccounts;
    }

    /**
     * This method sets the chart of accounts.
     * 
     * @param chartOfAccounts
     */
    public void setChartOfAccounts(Chart chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
    }

    /**
     * This method gets the project.
     * 
     * @return the project
     */
    public ProjectCode getProject() {
        return project;
    }

    /**
     * This method sets the project.
     * 
     * @param project
     */
    public void setProject(ProjectCode project) {
        this.project = project;
    }

    /**
     * This method gets the cuttent object code.
     * 
     * @return the current object code
     */
    public ObjectCodeCurrent getObjectCode() {
        return objectCode;
    }

    /**
     * This method sets the cuttent object code.
     * 
     * @param objectCode
     */
    public void setObjectCode(ObjectCodeCurrent objectCode) {
        this.objectCode = objectCode;
    }
}
