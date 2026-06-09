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

package org.kuali.kfs.fp.businessobject;

import java.util.LinkedHashMap;

import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectCodeCurrent;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
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
 * This class represents an offset account business object.
 */
@Entity
@Table(name = "FP_OFST_ACCT_T")
public class OffsetAccount extends PersistableBusinessObjectBase implements MutableInactivatable {

    @Id
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;
    @Id
    @Column(name = "ACCOUNT_NBR")
    private String accountNumber;
    @Id
    @Column(name = "FIN_OFST_OBJ_CD")
    private String financialOffsetObjectCode;
    @Column(name = "FIN_OFST_COA_CD")
    private String financialOffsetChartOfAccountCode;
    @Column(name = "FIN_OFST_ACCT_NBR")
    private String financialOffsetAccountNumber;
    @Column(name = "ROW_ACTV_IND")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false)
    private Chart chart;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIN_OFST_COA_CD", insertable = false, updatable = false)
    private Chart financialOffsetChartOfAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account financialOffsetAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    private ObjectCodeCurrent objectCodeCurrent;

    /**
     * Default constructor.
     */
    public OffsetAccount() {

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
     * Gets the financialOffsetObjectCode attribute.
     * 
     * @return Returns the financialOffsetObjectCode
     */
    public String getFinancialOffsetObjectCode() {
        return financialOffsetObjectCode;
    }

    /**
     * Sets the financialOffsetObjectCode attribute.
     * 
     * @param financialOffsetObjectCode The financialOffsetObjectCode to set.
     */
    public void setFinancialOffsetObjectCode(String financialOffsetObjectCode) {
        this.financialOffsetObjectCode = financialOffsetObjectCode;
    }


    /**
     * Gets the financialOffsetChartOfAccountCode attribute.
     * 
     * @return Returns the financialOffsetChartOfAccountCode
     */
    public String getFinancialOffsetChartOfAccountCode() {
        return financialOffsetChartOfAccountCode;
    }

    /**
     * Sets the financialOffsetChartOfAccountCode attribute.
     * 
     * @param financialOffsetChartOfAccountCode The financialOffsetChartOfAccountCode to set.
     */
    public void setFinancialOffsetChartOfAccountCode(String financialOffsetChartOfAccountCode) {
        this.financialOffsetChartOfAccountCode = financialOffsetChartOfAccountCode;
    }


    /**
     * Gets the financialOffsetAccountNumber attribute.
     * 
     * @return Returns the financialOffsetAccountNumber
     */
    public String getFinancialOffsetAccountNumber() {
        return financialOffsetAccountNumber;
    }

    /**
     * Sets the financialOffsetAccountNumber attribute.
     * 
     * @param financialOffsetAccountNumber The financialOffsetAccountNumber to set.
     */
    public void setFinancialOffsetAccountNumber(String financialOffsetAccountNumber) {
        this.financialOffsetAccountNumber = financialOffsetAccountNumber;
    }


    /**
     * Gets the chart attribute.
     * 
     * @return Returns the chart
     */
    public Chart getChart() {
        return chart;
    }

    /**
     * Sets the chart attribute.
     * 
     * @param chart The chart to set.
     * @deprecated
     */
    public void setChart(Chart chart) {
        this.chart = chart;
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
     * Gets the financialOffsetChartOfAccount attribute.
     * 
     * @return Returns the financialOffsetChartOfAccount
     */
    public Chart getFinancialOffsetChartOfAccount() {
        return financialOffsetChartOfAccount;
    }

    /**
     * Sets the financialOffsetChartOfAccount attribute.
     * 
     * @param financialOffsetChartOfAccount The financialOffsetChartOfAccount to set.
     * @deprecated
     */
    public void setFinancialOffsetChartOfAccount(Chart financialOffsetChartOfAccount) {
        this.financialOffsetChartOfAccount = financialOffsetChartOfAccount;
    }

    /**
     * @return Returns the financialOffsetAccount.
     */
    public Account getFinancialOffsetAccount() {
        return financialOffsetAccount;
    }

    /**
     * @param financialOffsetAccount The financialOffsetAccount to set.
     * @deprecated
     */
    public void setFinancialOffsetAccount(Account financialOffsetAccount) {
        this.financialOffsetAccount = financialOffsetAccount;
    }

    /**
     * @return Returns the objectCodeCurrent.
     */
    public ObjectCodeCurrent getObjectCodeCurrent() {
        return objectCodeCurrent;
    }

    /**
     * @param objectCodeCurrent The objectCodeCurrent to set.
     * @deprecated
     */
    public void setObjectCodeCurrent(ObjectCodeCurrent objectCodeCurrent) {
        this.objectCodeCurrent = objectCodeCurrent;
    }

    /**
     * This method (a hack by any other name...) returns a string so that an offset Account can have a link to view its own
     * inquiry page after a look up
     * 
     * @return the String "View Offset Account"
     */
    public String getOffsetAccountViewer() {
        return "View Offset Account";
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        m.put("financialOffsetObjectCode", this.financialOffsetObjectCode);
        return m;
    }

    /**
     * Gets the active attribute. 
     * @return Returns the active.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active attribute value.
     * @param active The active to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}
