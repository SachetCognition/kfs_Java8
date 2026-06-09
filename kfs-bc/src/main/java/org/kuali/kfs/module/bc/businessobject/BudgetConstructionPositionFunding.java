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

import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.businessobject.Organization;
import org.kuali.kfs.coa.businessobject.SubAccount;
import org.kuali.kfs.coa.businessobject.SubObjectCode;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * 
 */
@Entity
@Table(name = "LD_BCN_POS_FND_T")
@IdClass(BudgetConstructionPositionFundingId.class)
public class BudgetConstructionPositionFunding extends PersistableBusinessObjectBase {

    @Id

    @Column(name = "PERSON_UNVL_ID")

    private String principalId;
    @Id
    @Column(name = "SEL_ORG_FIN_COA")
    private String selectedOrganizationChartOfAccountsCode;
    @Id
    @Column(name = "SEL_ORG_CD")
    private String selectedOrganizationCode;
    @Id
    @Column(name = "PERSON_NM")
    private String name;
    @Id
    @Column(name = "EMPLID")
    private String emplid;
    @Id
    @Column(name = "POSITION_NBR")
    private String positionNumber;
    @Id
    @Column(name = "UNIV_FISCAL_YR")
    private Integer universityFiscalYear;
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
    @Column(name = "FIN_OBJECT_CD")
    private String financialObjectCode;
    @Id
    @Column(name = "FIN_SUB_OBJ_CD")
    private String financialSubObjectCode;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "SEL_ORG_FIN_COA", insertable = false, updatable = false)

    private Chart selectedOrganizationChartOfAccounts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "SEL_ORG_FIN_COA", referencedColumnName = "SEL_ORG_FIN_COA", insertable = false, updatable = false),
        @JoinColumn(name = "SEL_ORG_CD", referencedColumnName = "SEL_ORG_CD", insertable = false, updatable = false)
    })
    private Organization selectedOrganization;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "UNIV_FISCAL_YR", referencedColumnName = "UNIV_FISCAL_YR", insertable = false, updatable = false),
        @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false),
        @JoinColumn(name = "FIN_OBJECT_CD", referencedColumnName = "FIN_OBJECT_CD", insertable = false, updatable = false)
    })
    private ObjectCode financialObject;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "UNIV_FISCAL_YR", referencedColumnName = "UNIV_FISCAL_YR", insertable = false, updatable = false),
        @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false),
        @JoinColumn(name = "ACCOUNT_NBR", referencedColumnName = "ACCOUNT_NBR", insertable = false, updatable = false),
        @JoinColumn(name = "FIN_OBJECT_CD", referencedColumnName = "FIN_OBJECT_CD", insertable = false, updatable = false),
        @JoinColumn(name = "FIN_SUB_OBJ_CD", referencedColumnName = "FIN_SUB_OBJ_CD", insertable = false, updatable = false)
    })
    private SubObjectCode financialSubObject;
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
    @JoinColumns({
        @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false),
        @JoinColumn(name = "ACCOUNT_NBR", referencedColumnName = "ACCOUNT_NBR", insertable = false, updatable = false),
        @JoinColumn(name = "SUB_ACCT_NBR", referencedColumnName = "SUB_ACCT_NBR", insertable = false, updatable = false)
    })
    private SubAccount subAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "UNIV_FISCAL_YR", referencedColumnName = "UNIV_FISCAL_YR", insertable = false, updatable = false),
        @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false),
        @JoinColumn(name = "ACCOUNT_NBR", referencedColumnName = "ACCOUNT_NBR", insertable = false, updatable = false),
        @JoinColumn(name = "SUB_ACCT_NBR", referencedColumnName = "SUB_ACCT_NBR", insertable = false, updatable = false),
        @JoinColumn(name = "FIN_OBJECT_CD", referencedColumnName = "FIN_OBJECT_CD", insertable = false, updatable = false),
        @JoinColumn(name = "FIN_SUB_OBJ_CD", referencedColumnName = "FIN_SUB_OBJ_CD", insertable = false, updatable = false),
        @JoinColumn(name = "POSITION_NBR", referencedColumnName = "POSITION_NBR", insertable = false, updatable = false),
        @JoinColumn(name = "EMPLID", referencedColumnName = "EMPLID", insertable = false, updatable = false)
    })
    private PendingBudgetConstructionAppointmentFunding pendingAppointmentFunding;

    /**
     * Default constructor.
     */
    public BudgetConstructionPositionFunding() {

    }

    /**
     * Gets the principalId attribute.
     * 
     * @return Returns the principalId
     */
    public String getPrincipalId() {
        return principalId;
    }

    /**
     * Sets the principalId attribute.
     * 
     * @param principalId The principalId to set.
     */
    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }


    /**
     * Gets the selectedOrganizationChartOfAccountsCode attribute.
     * 
     * @return Returns the selectedOrganizationChartOfAccountsCode
     */
    public String getSelectedOrganizationChartOfAccountsCode() {
        return selectedOrganizationChartOfAccountsCode;
    }

    /**
     * Sets the selectedOrganizationChartOfAccountsCode attribute.
     * 
     * @param selectedOrganizationChartOfAccountsCode The selectedOrganizationChartOfAccountsCode to set.
     */
    public void setSelectedOrganizationChartOfAccountsCode(String selectedOrganizationChartOfAccountsCode) {
        this.selectedOrganizationChartOfAccountsCode = selectedOrganizationChartOfAccountsCode;
    }


    /**
     * Gets the selectedOrganizationCode attribute.
     * 
     * @return Returns the selectedOrganizationCode
     */
    public String getSelectedOrganizationCode() {
        return selectedOrganizationCode;
    }

    /**
     * Sets the selectedOrganizationCode attribute.
     * 
     * @param selectedOrganizationCode The selectedOrganizationCode to set.
     */
    public void setSelectedOrganizationCode(String selectedOrganizationCode) {
        this.selectedOrganizationCode = selectedOrganizationCode;
    }


    /**
     * Gets the name attribute.
     * 
     * @return Returns the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name attribute.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the emplid attribute.
     * 
     * @return Returns the emplid
     */
    public String getEmplid() {
        return emplid;
    }

    /**
     * Sets the emplid attribute.
     * 
     * @param emplid The emplid to set.
     */
    public void setEmplid(String emplid) {
        this.emplid = emplid;
    }


    /**
     * Gets the positionNumber attribute.
     * 
     * @return Returns the positionNumber
     */
    public String getPositionNumber() {
        return positionNumber;
    }

    /**
     * Sets the positionNumber attribute.
     * 
     * @param positionNumber The positionNumber to set.
     */
    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }


    /**
     * Gets the universityFiscalYear attribute.
     * 
     * @return Returns the universityFiscalYear
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * Sets the universityFiscalYear attribute.
     * 
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
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
     * Gets the subAccountNumber attribute.
     * 
     * @return Returns the subAccountNumber
     */
    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    /**
     * Sets the subAccountNumber attribute.
     * 
     * @param subAccountNumber The subAccountNumber to set.
     */
    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }


    /**
     * Gets the financialObjectCode attribute.
     * 
     * @return Returns the financialObjectCode
     */
    public String getFinancialObjectCode() {
        return financialObjectCode;
    }

    /**
     * Sets the financialObjectCode attribute.
     * 
     * @param financialObjectCode The financialObjectCode to set.
     */
    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }


    /**
     * Gets the financialSubObjectCode attribute.
     * 
     * @return Returns the financialSubObjectCode
     */
    public String getFinancialSubObjectCode() {
        return financialSubObjectCode;
    }

    /**
     * Sets the financialSubObjectCode attribute.
     * 
     * @param financialSubObjectCode The financialSubObjectCode to set.
     */
    public void setFinancialSubObjectCode(String financialSubObjectCode) {
        this.financialSubObjectCode = financialSubObjectCode;
    }


    /**
     * Gets the selectedOrganizationChartOfAccounts attribute.
     * 
     * @return Returns the selectedOrganizationChartOfAccounts
     */
    public Chart getSelectedOrganizationChartOfAccounts() {
        return selectedOrganizationChartOfAccounts;
    }

    /**
     * Sets the selectedOrganizationChartOfAccounts attribute.
     * 
     * @param selectedOrganizationChartOfAccounts The selectedOrganizationChartOfAccounts to set.
     * @deprecated
     */
    public void setSelectedOrganizationChartOfAccounts(Chart selectedOrganizationChartOfAccounts) {
        this.selectedOrganizationChartOfAccounts = selectedOrganizationChartOfAccounts;
    }

    /**
     * Gets the selectedOrganization attribute.
     * 
     * @return Returns the selectedOrganization
     */
    public Organization getSelectedOrganization() {
        return selectedOrganization;
    }

    /**
     * Sets the selectedOrganization attribute.
     * 
     * @param selectedOrganization The selectedOrganization to set.
     * @deprecated
     */
    public void setSelectedOrganization(Organization selectedOrganization) {
        this.selectedOrganization = selectedOrganization;
    }

    /**
     * Gets the financialObject attribute.
     * 
     * @return Returns the financialObject
     */
    public ObjectCode getFinancialObject() {
        return financialObject;
    }

    /**
     * Sets the financialObject attribute.
     * 
     * @param financialObject The financialObject to set.
     * @deprecated
     */
    public void setFinancialObject(ObjectCode financialObject) {
        this.financialObject = financialObject;
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
     * Gets the pendingAppointmentFunding attribute.
     * 
     * @return Returns the pendingAppointmentFunding.
     */
    public PendingBudgetConstructionAppointmentFunding getPendingAppointmentFunding() {
        return pendingAppointmentFunding;
    }

    /**
     * Sets the pendingAppointmentFunding attribute value.
     * 
     * @param pendingAppointmentFunding The pendingAppointmentFunding to set.
     * @deprecated
     */
    public void setPendingAppointmentFunding(PendingBudgetConstructionAppointmentFunding pendingAppointmentFunding) {
        this.pendingAppointmentFunding = pendingAppointmentFunding;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("principalId", this.principalId);
        m.put("selectedOrganizationChartOfAccountsCode", this.selectedOrganizationChartOfAccountsCode);
        m.put("selectedOrganizationCode", this.selectedOrganizationCode);
        m.put("name", this.name);
        m.put("emplid", this.emplid);
        m.put("positionNumber", this.positionNumber);
        if (this.universityFiscalYear != null) {
            m.put("universityFiscalYear", this.universityFiscalYear.toString());
        }
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        m.put("subAccountNumber", this.subAccountNumber);
        m.put("financialObjectCode", this.financialObjectCode);
        m.put("financialSubObjectCode", this.financialSubObjectCode);
        return m;
    }

    public SubAccount getSubAccount() {
        return subAccount;
    }

    public void setSubAccount(SubAccount subAccount) {
        this.subAccount = subAccount;
    }

    public SubObjectCode getFinancialSubObject() {
        return financialSubObject;
    }

    public void setFinancialSubObject(SubObjectCode financialSubObject) {
        this.financialSubObject = financialSubObject;
    }


}

