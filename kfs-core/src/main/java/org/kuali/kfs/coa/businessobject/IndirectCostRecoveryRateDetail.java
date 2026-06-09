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

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.kuali.kfs.sys.businessobject.FiscalYearBasedBusinessObject;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

/**
 * 
 */
@Entity
@Table(name = "CA_ICR_AUTO_ENTR_T")
@IdClass(IndirectCostRecoveryRateDetailId.class)

public class IndirectCostRecoveryRateDetail extends PersistableBusinessObjectBase implements MutableInactivatable, FiscalYearBasedBusinessObject {

    /**
     * Default no-arg constructor.
     */
    public IndirectCostRecoveryRateDetail() {
    }

    @Id
    @Column(name = "UNIV_FISCAL_YR")
    private Integer universityFiscalYear;
    @Id
    @Column(name = "FIN_SERIES_ID")
    private String financialIcrSeriesIdentifier;
    @Id
    @Column(name = "AWRD_ICR_ENTRY_NBR")
    private Integer awardIndrCostRcvyEntryNbr;
    @Column(name = "TRN_DEBIT_CRDT_CD")
    private String transactionDebitIndicator;
    @Column(name = "AWRD_ICR_RATE_PCT")
    private BigDecimal awardIndrCostRcvyRatePct;
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;
    @Column(name = "ACCOUNT_NBR")
    private String accountNumber;
    @Column(name = "SUB_ACCT_NBR")
    private String subAccountNumber;
    @Column(name = "FIN_OBJECT_CD")
    private String financialObjectCode;
    @Column(name = "FIN_SUB_OBJ_CD")
    private String financialSubObjectCode;
    @Column(name = "ACCT_ICR_RATE_ACTV_IND")
    @Convert(converter = YesNoConverter.class)
    private boolean active;

    @Transient
    private SystemOptions universityFiscal;
    private IndirectCostRecoveryRate indirectCostRecoveryRate;
    
    /*
     * Don't use reference objects because Chart, Account, Sub-Account, etc. contain special characters. RO 2/8/06 private Chart
     * chartOfAccounts; private Account account; private SubAccount subAccount; private ObjectCode financialObject; private SubObjCd
     * financialSubObject; private ObjectCode offsetBalanceSheetObjectCode;
     */

    /*
     * public Account getAccount() { return account; } public void setAccount(Account account) { this.account = account; }
     */
    public String getAccountNumber() {
        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public Integer getAwardIndrCostRcvyEntryNbr() {
        return awardIndrCostRcvyEntryNbr;
    }


    public void setAwardIndrCostRcvyEntryNbr(Integer awardIndrCostRcvyEntryNbr) {
        this.awardIndrCostRcvyEntryNbr = awardIndrCostRcvyEntryNbr;
    }


    public BigDecimal getAwardIndrCostRcvyRatePct() {
        return awardIndrCostRcvyRatePct;
    }


    public void setAwardIndrCostRcvyRatePct(BigDecimal awardIndrCostRcvyRatePct) {
        this.awardIndrCostRcvyRatePct = awardIndrCostRcvyRatePct;
    }

    /*
     * public Chart getChartOfAccounts() { return chartOfAccounts; } public void setChartOfAccounts(Chart chartOfAccounts) {
     * this.chartOfAccounts = chartOfAccounts; }
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }


    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }


    public String getFinancialIcrSeriesIdentifier() {
        return financialIcrSeriesIdentifier;
    }


    public void setFinancialIcrSeriesIdentifier(String financialIcrSeriesIdentifier) {
        this.financialIcrSeriesIdentifier = financialIcrSeriesIdentifier;
    }


    /*
     * public ObjectCode getFinancialObject() { return financialObject; } public void setFinancialObject(ObjectCode financialObject) {
     * this.financialObject = financialObject; }
     */
    public String getFinancialObjectCode() {
        return financialObjectCode;
    }


    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }

    /*
     * public SubObjCd getFinancialSubObject() { return financialSubObject; } public void setFinancialSubObject(SubObjCd
     * financialSubObject) { this.financialSubObject = financialSubObject; }
     */

    public String getFinancialSubObjectCode() {
        return financialSubObjectCode;
    }


    public void setFinancialSubObjectCode(String financialSubObjectCode) {
        this.financialSubObjectCode = financialSubObjectCode;
    }

    /*
     * public SubAccount getSubAccount() { return subAccount; } public void setSubAccount(SubAccount subAccount) { this.subAccount =
     * subAccount; }
     */

    public String getSubAccountNumber() {
        return subAccountNumber;
    }


    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }


    public String getTransactionDebitIndicator() {
        return transactionDebitIndicator;
    }


    public void setTransactionDebitIndicator(String transactionDebitIndicator) {
        this.transactionDebitIndicator = transactionDebitIndicator;
    }


    public SystemOptions getUniversityFiscal() {
        return universityFiscal;
    }


    public void setUniversityFiscal(SystemOptions universityFiscal) {
        this.universityFiscal = universityFiscal;
    }


    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }


    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }


    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {

        LinkedHashMap m = new LinkedHashMap();
        m.put("universityFiscal", this.universityFiscalYear);
        m.put("financialIcrSeriesIdentifier", this.financialIcrSeriesIdentifier);
        m.put("awardIndrCostRcvyEntryNbr", this.awardIndrCostRcvyEntryNbr);

        return m;
    }


    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    public IndirectCostRecoveryRate getIndirectCostRecoveryRate() {
        return indirectCostRecoveryRate;
    }

    public void setIndirectCostRecoveryRate(IndirectCostRecoveryRate indirectCostRecoveryRate) {
        this.indirectCostRecoveryRate = indirectCostRecoveryRate;
    }
}
