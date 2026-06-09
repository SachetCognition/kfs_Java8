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

package org.kuali.kfs.module.bc.businessobject;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * 
 */
@Entity
@Table(name = "LD_BCN_ACCT_RPTS_T")
@IdClass(BudgetConstructionAccountReportsId.class)
public class BudgetConstructionAccountReports extends PersistableBusinessObjectBase {

    @Id

    @Column(name = "FIN_COA_CD")

    private String chartOfAccountsCode;
    @Id
    @Column(name = "ACCOUNT_NBR")
    private String accountNumber;
    @Column(name = "RPTS_TO_FIN_COA_CD")
    private String reportsToChartOfAccountsCode;
    @Column(name = "RPTS_TO_ORG_CD")
    private String reportsToOrganizationCode;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumns({

        @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false),

        @JoinColumn(name = "ACCOUNT_NBR", referencedColumnName = "ACCOUNT_NBR", insertable = false, updatable = false)

    })

    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false)
    private Chart chartOfAccounts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RPTS_TO_FIN_COA_CD", insertable = false, updatable = false)
    private Chart reportsToChartOfAccounts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "RPTS_TO_FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false),
        @JoinColumn(name = "RPTS_TO_ORG_CD", referencedColumnName = "ORG_CD", insertable = false, updatable = false)
    })
    private BudgetConstructionOrganizationReports budgetConstructionOrganizationReports;

    @javax.persistence.Transient
    private List budgetConstructionAccountOrganizationHierarchy;

    /**
     * Default constructor.
     */
    public BudgetConstructionAccountReports() {

    }

    /**
     * Gets the chartOfAccountsCode attribute.
     * 
     * @return Returns the chartOfAccountsCode
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * Sets the chartOfAccountsCode attribute.
     * 
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }


    /**
     * Gets the accountNumber attribute.
     * 
     * @return Returns the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the accountNumber attribute.
     * 
     * @param accountNumber The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the reportsToChartOfAccountsCode attribute.
     * 
     * @return Returns the reportsToChartOfAccountsCode
     */
    public String getReportsToChartOfAccountsCode() {
        return reportsToChartOfAccountsCode;
    }

    /**
     * Sets the reportsToChartOfAccountsCode attribute.
     * 
     * @param reportsToChartOfAccountsCode The reportsToChartOfAccountsCode to set.
     */
    public void setReportsToChartOfAccountsCode(String reportsToChartOfAccountsCode) {
        this.reportsToChartOfAccountsCode = reportsToChartOfAccountsCode;
    }


    /**
     * Gets the reportsToOrganizationCode attribute.
     * 
     * @return Returns the reportsToOrganizationCode
     */
    public String getReportsToOrganizationCode() {
        return reportsToOrganizationCode;
    }

    /**
     * Sets the reportsToOrganizationCode attribute.
     * 
     * @param reportsToOrganizationCode The reportsToOrganizationCode to set.
     */
    public void setReportsToOrganizationCode(String reportsToOrganizationCode) {
        this.reportsToOrganizationCode = reportsToOrganizationCode;
    }


    /**
     * Gets the account attribute.
     * 
     * @return Returns the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account attribute.
     * 
     * @param account The account to set.
     * @deprecated
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets the chartOfAccounts attribute.
     * 
     * @return Returns the chartOfAccounts
     */
    public Chart getChartOfAccounts() {
        return chartOfAccounts;
    }

    /**
     * Sets the chartOfAccounts attribute.
     * 
     * @param chartOfAccounts The chartOfAccounts to set.
     * @deprecated
     */
    public void setChartOfAccounts(Chart chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
    }

    /**
     * Gets the reportsToChartOfAccounts attribute.
     * 
     * @return Returns the reportsToChartOfAccounts
     */
    public Chart getReportsToChartOfAccounts() {
        return reportsToChartOfAccounts;
    }

    /**
     * Sets the reportsToChartOfAccounts attribute.
     * 
     * @param reportsToChartOfAccounts The reportsToChartOfAccounts to set.
     * @deprecated
     */
    public void setReportsToChartOfAccounts(Chart reportsToChartOfAccounts) {
        this.reportsToChartOfAccounts = reportsToChartOfAccounts;
    }

    /**
     * Gets the budgetConstructionOrganizationReports attribute.
     * 
     * @return Returns the budgetConstructionOrganizationReports.
     */
    public BudgetConstructionOrganizationReports getBudgetConstructionOrganizationReports() {
        return budgetConstructionOrganizationReports;
    }

    /**
     * Sets the budgetConstructionOrganizationReports attribute value.
     * 
     * @param budgetConstructionOrganizationReports The budgetConstructionOrganizationReports to set.
     * @deprecated
     */
    public void setBudgetConstructionOrganizationReports(BudgetConstructionOrganizationReports budgetConstructionOrganizationReports) {
        this.budgetConstructionOrganizationReports = budgetConstructionOrganizationReports;
    }

    /**
     * Gets the budgetConstructionAccountOrganizationHierarchy attribute.
     * 
     * @return Returns the budgetConstructionAccountOrganizationHierarchy.
     */
    public List<BudgetConstructionAccountOrganizationHierarchy> getBudgetConstructionAccountOrganizationHierarchy() {
        return budgetConstructionAccountOrganizationHierarchy;
    }

    /**
     * Sets the budgetConstructionAccountOrganizationHierarchy attribute value.
     * 
     * @param budgetConstructionAccountOrganizationHierarchy The budgetConstructionAccountOrganizationHierarchy to set.
     */
    public void setBudgetConstructionAccountOrganizationHierarchy(List<BudgetConstructionAccountOrganizationHierarchy> budgetConstructionAccountOrganizationHierarchy) {
        this.budgetConstructionAccountOrganizationHierarchy = budgetConstructionAccountOrganizationHierarchy;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        return m;
    }
}
