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
package org.kuali.kfs.gl.businessobject;

import java.util.LinkedHashMap;

import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.OrganizationReversionCategory;
import org.kuali.kfs.coa.businessobject.SubAccount;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This class represents a organization reversion unit of work category amount
 */
@Entity
@Table(name = "GL_ORG_RVRSN_CTGRY_AMT_T")
public class OrgReversionUnitOfWorkCategoryAmount extends PersistableBusinessObjectBase {
    @Id
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;
    @Id
    @Column(name = "ACCOUNT_NBR")
    private String accountNumber;
    @Id
    @Column(name = "SUB_ACCT_NBR")
    private String subAccountNumber;
    @Id
    @Column(name = "ORG_RVRSN_CTGRY_CD")
    private String categoryCode;
    @Column(name = "ORG_TOT_ACTL_AMT")
    private KualiDecimal actual = KualiDecimal.ZERO;
    @Column(name = "ORG_TOT_BDGT_AMT")
    private KualiDecimal budget = KualiDecimal.ZERO;
    @Column(name = "ORG_TOT_ENCUM_AMT")
    private KualiDecimal encumbrance = KualiDecimal.ZERO;
    @Column(name = "ORG_TOT_CF_AMT")
    private KualiDecimal carryForward = KualiDecimal.ZERO;
    @Column(name = "ORG_TOT_AVAIL_AMT")
    private KualiDecimal available = KualiDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false)
    private Chart chartOfAccounts;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    private SubAccount subAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORG_RVRSN_CTGRY_CD", insertable = false, updatable = false)
    private OrganizationReversionCategory organizationReversionCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    private OrgReversionUnitOfWork organizationReversionUnitOfWork;

    public OrgReversionUnitOfWorkCategoryAmount(String cat) {
        this.categoryCode = cat;
    }

    public OrgReversionUnitOfWorkCategoryAmount(String chartOfAccountsCode, String accountNbr, String subAccountNbr, String cat) {
        this.chartOfAccountsCode = chartOfAccountsCode;
        this.accountNumber = accountNbr;
        this.subAccountNumber = subAccountNbr;
        categoryCode = cat;
    }

    public void addActual(KualiDecimal amount) {
        actual = actual.add(amount);
    }

    public void addBudget(KualiDecimal amount) {
        budget = budget.add(amount);
    }

    public void addEncumbrance(KualiDecimal amount) {
        encumbrance = encumbrance.add(amount);
    }

    public void addCarryForward(KualiDecimal amount) {
        carryForward = carryForward.add(amount);
    }

    public void addAvailable(KualiDecimal amount) {
        available = available.add(amount);
    }

    public KualiDecimal getAvailable() {
        return available;
    }

    public void setAvailable(KualiDecimal available) {
        this.available = available;
    }

    public KualiDecimal getActual() {
        return actual;
    }

    public void setActual(KualiDecimal actual) {
        this.actual = actual;
    }

    public KualiDecimal getBudget() {
        return budget;
    }

    public void setBudget(KualiDecimal budget) {
        this.budget = budget;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public KualiDecimal getEncumbrance() {
        return encumbrance;
    }

    public void setEncumbrance(KualiDecimal encumbrance) {
        this.encumbrance = encumbrance;
    }

    public KualiDecimal getCarryForward() {
        return carryForward;
    }

    public void setCarryForward(KualiDecimal carryForward) {
        this.carryForward = carryForward;
    }

    /**
     * Gets the accountNbr attribute.
     * 
     * @return Returns the accountNbr.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the accountNbr attribute value.
     * 
     * @param accountNbr The accountNbr to set.
     */
    public void setAccountNumber(String accountNbr) {
        this.accountNumber = accountNbr;
    }

    /**
     * Gets the chartOfAccountsCode attribute.
     * 
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * Sets the chartOfAccountsCode attribute value.
     * 
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    /**
     * Gets the subAccountNbr attribute.
     * 
     * @return Returns the subAccountNbr.
     */
    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    /**
     * Sets the subAccountNbr attribute value.
     * 
     * @param subAccountNbr The subAccountNbr to set.
     */
    public void setSubAccountNumber(String subAccountNbr) {
        this.subAccountNumber = subAccountNbr;
    }

    /**
     * Gets the account attribute.
     * 
     * @return Returns the account.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account attribute value.
     * 
     * @param account The account to set.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets the chartOfAccounts attribute.
     * 
     * @return Returns the chartOfAccounts.
     */
    public Chart getChartOfAccounts() {
        return chartOfAccounts;
    }

    /**
     * Sets the chartOfAccounts attribute value.
     * 
     * @param chartOfAccounts The chartOfAccounts to set.
     */
    public void setChartOfAccounts(Chart chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
    }

    /**
     * Gets the organizationReversionCategory attribute.
     * 
     * @return Returns the organizationReversionCategory.
     */
    public OrganizationReversionCategory getOrganizationReversionCategory() {
        return organizationReversionCategory;
    }

    /**
     * Sets the organizationReversionCategory attribute value.
     * 
     * @param organizationReversionCategory The organizationReversionCategory to set.
     */
    public void setOrganizationReversionCategory(OrganizationReversionCategory organizationReversionCategory) {
        this.organizationReversionCategory = organizationReversionCategory;
    }

    /**
     * Gets the organizationReversionUnitOfWork attribute.
     * 
     * @return Returns the organizationReversionUnitOfWork.
     */
    public OrgReversionUnitOfWork getOrganizationReversionUnitOfWork() {
        return organizationReversionUnitOfWork;
    }

    /**
     * Sets the organizationReversionUnitOfWork attribute value.
     * 
     * @param organizationReversionUnitOfWork The organizationReversionUnitOfWork to set.
     */
    public void setOrganizationReversionUnitOfWork(OrgReversionUnitOfWork organizationReversionUnitOfWork) {
        this.organizationReversionUnitOfWork = organizationReversionUnitOfWork;
    }

    /**
     * Gets the subAccount attribute.
     * 
     * @return Returns the subAccount.
     */
    public SubAccount getSubAccount() {
        return subAccount;
    }

    /**
     * Sets the subAccount attribute value.
     * 
     * @param subAccount The subAccount to set.
     */
    public void setSubAccount(SubAccount subAccount) {
        this.subAccount = subAccount;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap pkMap = new LinkedHashMap();
        pkMap.put("chartOfAccountsCode", this.chartOfAccountsCode);
        pkMap.put("accountNbr", this.accountNumber);
        pkMap.put("subAccountNbr", this.subAccountNumber);
        pkMap.put("categoryCode", this.categoryCode);
        return pkMap;
    }

}
