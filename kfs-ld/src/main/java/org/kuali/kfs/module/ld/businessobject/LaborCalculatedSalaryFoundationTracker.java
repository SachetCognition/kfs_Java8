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
package org.kuali.kfs.module.ld.businessobject;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.businessobject.SubAccount;
import org.kuali.kfs.coa.businessobject.SubObjectCode;
import org.kuali.kfs.module.ld.LaborConstants;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.sys.service.FinancialSystemUserService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.kuali.kfs.module.ld.persistence.converter.OjbKualiDecimalFieldConverter;


@Entity
@Table(name = "LD_CSF_TRACKER_T")
@IdClass(LaborCalculatedSalaryFoundationTrackerId.class)
/**
 * Labor business object for LaborCalculatedSalaryFoundationTracker.
 */
public class LaborCalculatedSalaryFoundationTracker extends PersistableBusinessObjectBase {
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
    @Id

    @Column(name = "POSITION_NBR")

    private String positionNumber;
    @Id

    @Column(name = "EMPLID")

    private String emplid;
    @Id

    @Column(name = "POS_CSF_CREATE_TM")

    private Timestamp csfCreateTimestamp;
    @Column(name = "POS_CSF_DELETE_CD")

    private String csfDeleteCode;
    @Column(name = "POS_CSF_AMT")

    @Convert(converter = OjbKualiDecimalFieldConverter.class)

    private KualiDecimal csfAmount;
    @Column(name = "POS_CSF_FTE_QTY")

    private BigDecimal csfFullTimeEmploymentQuantity;
    @Column(name = "POS_CSF_TM_PCT")

    private BigDecimal csfTimePercent;
    @Column(name = "POS_CSF_FNDSTAT_CD")

    private String csfFundingStatusCode;
    @Column(name = "EMPL_RCD")

    private Integer employeeRecord;
    @Column(name = "ERNCD")

    private String earnCode;
    @Column(name = "ADDL_SEQ")

    private Integer additionalSequence;
    @Column(name = "EFFDT")

    private Date effectiveDate;
    @Column(name = "EFFSEQ")

    private Integer effectiveSequence;

    @ManyToOne(fetch = FetchType.LAZY)


    @JoinColumns({


        @JoinColumn(name = "UNIV_FISCAL_YR", referencedColumnName = "UNIV_FISCAL_YR", insertable = false, updatable = false),


        @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false),


        @JoinColumn(name = "FIN_OBJECT_CD", referencedColumnName = "FIN_OBJECT_CD", insertable = false, updatable = false)


    })


    private ObjectCode financialObject;
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false)

    private Chart chartOfAccounts;
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumns({

        @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false),

        @JoinColumn(name = "ACCOUNT_NBR", referencedColumnName = "ACCOUNT_NBR", insertable = false, updatable = false)

    })

    private Account account;
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

        @JoinColumn(name = "FIN_OBJECT_CD", referencedColumnName = "FIN_OBJECT_CD", insertable = false, updatable = false),

        @JoinColumn(name = "FIN_SUB_OBJ_CD", referencedColumnName = "FIN_SUB_OBJ_CD", insertable = false, updatable = false)

    })

    private SubObjectCode financialSubObject;
    private transient SystemOptions universityFiscal;
    private final int PERCENTAGE_SCALE = 2;

    private KualiDecimal july1BudgetAmount;
    private BigDecimal july1BudgetFteQuantity;
    private BigDecimal july1BudgetTimePercent;

    /**
     * Constructs a LaborCalculatedSalaryFoundationTracker.java.
     */
    public LaborCalculatedSalaryFoundationTracker() {
        super();
        this.setJuly1BudgetAmount(KualiDecimal.ZERO);
        this.setJuly1BudgetFteQuantity(BigDecimal.ZERO);
        this.setJuly1BudgetTimePercent(BigDecimal.ZERO);
    }

    /**
     * Gets the july1BudgetAmount.
     *
     * @return Returns the july1BudgetAmount.
     */
    public KualiDecimal getJuly1BudgetAmount() {
        return july1BudgetAmount;
    }

    /**
     * Sets the july1BudgetAmount.
     *
     * @param july1BudgetAmount The july1BudgetAmount to set.
     */
    public void setJuly1BudgetAmount(KualiDecimal july1BudgetAmount) {
        this.july1BudgetAmount = july1BudgetAmount;
    }

    /**
     * Gets the july1BudgetFteQuantity.
     *
     * @return Returns the july1BudgetFteQuantity.
     */
    public BigDecimal getJuly1BudgetFteQuantity() {
        return july1BudgetFteQuantity;
    }

    /**
     * Sets the july1BudgetFteQuantity.
     *
     * @param july1BudgetFteQuantity The july1BudgetFteQuantity to set.
     */
    public void setJuly1BudgetFteQuantity(BigDecimal july1BudgetFteQuantity) {
        this.july1BudgetFteQuantity = july1BudgetFteQuantity;
    }

    /**
     * Gets the july1BudgetTimePercent.
     *
     * @return Returns the july1BudgetTimePercent.
     */
    public BigDecimal getJuly1BudgetTimePercent() {
        return july1BudgetTimePercent;
    }

    /**
     * Sets the july1BudgetTimePercent.
     *
     * @param july1BudgetTimePercent The july1BudgetTimePercent to set.
     */
    public void setJuly1BudgetTimePercent(BigDecimal july1BudgetTimePercent) {
        this.july1BudgetTimePercent = july1BudgetTimePercent;
    }

    /**
     * Gets the universityFiscalYear attribute.
     *
     * @return Returns the universityFiscalYear.
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * Sets the universityFiscalYear attribute value.
     *
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
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
     * Gets the accountNumber attribute.
     *
     * @return Returns the accountNumber.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the accountNumber attribute value.
     *
     * @param accountNumber The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the subAccountNumber attribute.
     *
     * @return Returns the subAccountNumber.
     */
    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    /**
     * Sets the subAccountNumber attribute value.
     *
     * @param subAccountNumber The subAccountNumber to set.
     */
    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }

    /**
     * Gets the financialObjectCode attribute.
     *
     * @return Returns the financialObjectCode.
     */
    public String getFinancialObjectCode() {
        return financialObjectCode;
    }

    /**
     * Sets the financialObjectCode attribute value.
     *
     * @param financialObjectCode The financialObjectCode to set.
     */
    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }

    /**
     * Gets the financialSubObjectCode attribute.
     *
     * @return Returns the financialSubObjectCode.
     */
    public String getFinancialSubObjectCode() {
        return financialSubObjectCode;
    }

    /**
     * Sets the financialSubObjectCode attribute value.
     *
     * @param financialSubObjectCode The financialSubObjectCode to set.
     */
    public void setFinancialSubObjectCode(String financialSubObjectCode) {
        this.financialSubObjectCode = financialSubObjectCode;
    }

    /**
     * Gets the positionNumber attribute.
     *
     * @return Returns the positionNumber.
     */
    public String getPositionNumber() {
        return positionNumber;
    }

    /**
     * Sets the positionNumber attribute value.
     *
     * @param positionNumber The positionNumber to set.
     */
    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }

    /**
     * Gets the emplid attribute.
     *
     * @return Returns the emplid.
     */
    public String getEmplid() {
        return emplid;
    }

    /**
     * Sets the emplid attribute value.
     *
     * @param emplid The emplid to set.
     */
    public void setEmplid(String emplid) {
        this.emplid = emplid;
    }

    /**
     * Gets the person name.
     * @return the person name
     */
    public String getName() {
        /*
         * KFSCNTRB-1337
         * Replace the old logic that got the name from the person retrieved using the PersonService with the
         * following logic that uses IdentityService, since the former is a lot slower.
         */
        String name = SpringContext.getBean(FinancialSystemUserService.class).getPersonNameByEmployeeId(getEmplid());
        if (!StringUtils.isEmpty(name)) {
            return name;
        }
        return LaborConstants.BalanceInquiries.UnknownPersonName;
    }

    /**
     * Gets the csfCreateTimestamp attribute.
     *
     * @return Returns the csfCreateTimestamp.
     */
    public Timestamp getCsfCreateTimestamp() {
        return csfCreateTimestamp;
    }

    /**
     * Sets the csfCreateTimestamp attribute value.
     *
     * @param csfCreateTimestamp The csfCreateTimestamp to set.
     */
    public void setCsfCreateTimestamp(Timestamp csfCreateTimestamp) {
        this.csfCreateTimestamp = csfCreateTimestamp;
    }

    /**
     * Gets the csfDeleteCode attribute.
     *
     * @return Returns the csfDeleteCode.
     */
    public String getCsfDeleteCode() {
        return csfDeleteCode;
    }

    /**
     * Sets the csfDeleteCode attribute value.
     *
     * @param csfDeleteCode The csfDeleteCode to set.
     */
    public void setCsfDeleteCode(String csfDeleteCode) {
        this.csfDeleteCode = csfDeleteCode;
    }

    /**
     * Gets the csfAmount attribute.
     *
     * @return Returns the csfAmount.
     */
    public KualiDecimal getCsfAmount() {
        return csfAmount;
    }

    /**
     * Sets the csfAmount attribute value.
     *
     * @param csfAmount The csfAmount to set.
     */
    public void setCsfAmount(KualiDecimal csfAmount) {
        this.csfAmount = csfAmount;
    }

    /**
     * Gets the csfFullTimeEmploymentQuantity attribute.
     *
     * @return Returns the csfFullTimeEmploymentQuantity.
     */
    public BigDecimal getCsfFullTimeEmploymentQuantity() {
        return csfFullTimeEmploymentQuantity;
    }

    /**
     * Sets the csfFullTimeEmploymentQuantity attribute value.
     *
     * @param csfFullTimeEmploymentQuantity The csfFullTimeEmploymentQuantity to set.
     */
    public void setCsfFullTimeEmploymentQuantity(BigDecimal csfFullTimeEmploymentQuantity) {
        this.csfFullTimeEmploymentQuantity = csfFullTimeEmploymentQuantity;
    }

    /**
     * Gets the csfTimePercent attribute.
     *
     * @return Returns the csfTimePercent.
     */
    public BigDecimal getCsfTimePercent() {
        return csfTimePercent;
    }

    /**
     * Sets the csfTimePercent attribute value.
     *
     * @param csfTimePercent The csfTimePercent to set.
     */
    public void setCsfTimePercent(BigDecimal csfTimePercent) {
        this.csfTimePercent = csfTimePercent;
    }

    /**
     * Gets the csfFundingStatusCode attribute.
     *
     * @return Returns the csfFundingStatusCode.
     */
    public String getCsfFundingStatusCode() {
        return csfFundingStatusCode;
    }

    /**
     * Sets the csfFundingStatusCode attribute value.
     *
     * @param csfFundingStatusCode The csfFundingStatusCode to set.
     */
    public void setCsfFundingStatusCode(String csfFundingStatusCode) {
        this.csfFundingStatusCode = csfFundingStatusCode;
    }

    /**
     * Gets the employeeRecord attribute.
     *
     * @return Returns the employeeRecord.
     */
    public Integer getEmployeeRecord() {
        return employeeRecord;
    }

    /**
     * Sets the employeeRecord attribute value.
     *
     * @param employeeRecord The employeeRecord to set.
     */
    public void setEmployeeRecord(Integer employeeRecord) {
        this.employeeRecord = employeeRecord;
    }

    /**
     * Gets the earnCode attribute.
     *
     * @return Returns the earnCode.
     */
    public String getEarnCode() {
        return earnCode;
    }

    /**
     * Sets the earnCode attribute value.
     *
     * @param earnCode The earnCode to set.
     */
    public void setEarnCode(String earnCode) {
        this.earnCode = earnCode;
    }

    /**
     * Gets the additionalSequence attribute.
     *
     * @return Returns the additionalSequence.
     */
    public Integer getAdditionalSequence() {
        return additionalSequence;
    }

    /**
     * Sets the additionalSequence attribute value.
     *
     * @param additionalSequence The additionalSequence to set.
     */
    public void setAdditionalSequence(Integer additionalSequence) {
        this.additionalSequence = additionalSequence;
    }

    /**
     * Gets the effectiveDate attribute.
     *
     * @return Returns the effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the effectiveDate attribute value.
     *
     * @param effectiveDate The effectiveDate to set.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * Gets the effectiveSequence attribute.
     *
     * @return Returns the effectiveSequence.
     */
    public Integer getEffectiveSequence() {
        return effectiveSequence;
    }

    /**
     * Sets the effectiveSequence attribute value.
     *
     * @param effectiveSequence The effectiveSequence to set.
     */
    public void setEffectiveSequence(Integer effectiveSequence) {
        this.effectiveSequence = effectiveSequence;
    }

    /**
     * Gets the financialObject attribute.
     *
     * @return Returns the financialObject.
     */
    public ObjectCode getFinancialObject() {
        return financialObject;
    }

    /**
     * Sets the financialObject attribute value.
     *
     * @param financialObject The financialObject to set.
     */
    public void setFinancialObject(ObjectCode financialObject) {
        this.financialObject = financialObject;
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
     * Gets the financialSubObject attribute.
     *
     * @return Returns the financialSubObject.
     */
    public SubObjectCode getFinancialSubObject() {
        return financialSubObject;
    }

    /**
     * Sets the financialSubObject attribute value.
     *
     * @param financialSubObject The financialSubObject to set.
     */
    public void setFinancialSubObject(SubObjectCode financialSubObject) {
        this.financialSubObject = financialSubObject;
    }

    /**
     * Gets the universityFiscal attribute.
     *
     * @return Returns the universityFiscal.
     */
    public SystemOptions getUniversityFiscal() {
        return universityFiscal;
    }

    /**
     * Sets the universityFiscal attribute value.
     *
     * @param universityFiscal The universityFiscal to set.
     */
    public void setUniversityFiscal(SystemOptions universityFiscal) {
        this.universityFiscal = universityFiscal;
    }

    /**
     * Gets the pERCENTAGE_SCALE attribute.
     *
     * @return Returns the pERCENTAGE_SCALE.
     */
    public int getPERCENTAGE_SCALE() {
        return PERCENTAGE_SCALE;
    }

    /**
     * construct the key list of the business object
     *
     * @return the key list of the business object
     */
    public List<String> getKeyFieldList() {
        List<String> keyFieldList = new ArrayList<String>();
        keyFieldList.add(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR);
        keyFieldList.add(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE);
        keyFieldList.add(KFSPropertyConstants.ACCOUNT_NUMBER);
        keyFieldList.add(KFSPropertyConstants.SUB_ACCOUNT_NUMBER);
        keyFieldList.add(KFSPropertyConstants.FINANCIAL_OBJECT_CODE);
        keyFieldList.add(KFSPropertyConstants.FINANCIAL_SUB_OBJECT_CODE);
        keyFieldList.add(KFSPropertyConstants.POSITION_NUMBER);
        keyFieldList.add(KFSPropertyConstants.EMPLID);

        return keyFieldList;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */

    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        if (this.universityFiscalYear != null) {
            m.put("universityFiscalYear", this.universityFiscalYear.toString());
        }
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        m.put("subAccountNumber", this.subAccountNumber);
        m.put("financialObjectCode", this.financialObjectCode);
        m.put("financialSubObjectCode", this.financialSubObjectCode);
        m.put("positionNumber", this.positionNumber);
        m.put("emplid", this.emplid);
        if (this.csfCreateTimestamp != null) {
            m.put("csfCreateTimestamp", this.csfCreateTimestamp.toString());
        }
        return m;
    }

}

