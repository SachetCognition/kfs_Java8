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

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.LinkedHashMap;

import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

/**
 * Represents a expenditure transaction for a specific fiscal year, COA code, account number,
 * sub account number, object code, sub-object code, balance type code, object type code,
 * fiscal accounting period, project code, organization reference ID
 */
@Entity
@Table(name = "GL_EXPEND_TRN_MT")
@IdClass(ExpenditureTransaction.PK.class)
public class ExpenditureTransaction extends PersistableBusinessObjectBase {
    static final long serialVersionUID = 5296540728313789670L;

    private final static String UNIVERISITY_FISCAL_YEAR = "universityFiscalYear";
    private final static String CHART_OF_ACCOUNTS_CODE = "chartOfAccountsCode";
    private final static String ACCOUNT_NUMBER = "accountNumber";
    private final static String SUB_ACCOUNT_NUMBER = "subAccountNumber";
    private final static String OBJECT_CODE = "objectCode";
    private final static String BALANCE_TYPE_CODE = "balanceTypeCode";
    private final static String OBJECT_TYPE_CODE = "objectTypeCode";
    private final static String UNIVERSITY_FISCAL_ACCOUNTING_PERIOD = "universityFiscalAccountingPeriod";
    private final static String SUB_OBJECT_CODE = "subObjectCode";
    private final static String PROJECT_CODE = "projectCode";
    private final static String ORGANIZATION_REFERENCE_ID = "organizationReferenceId";

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
    private String objectCode;
    @Id
    @Column(name = "FIN_SUB_OBJ_CD")
    private String subObjectCode;
    @Id
    @Column(name = "FIN_BALANCE_TYP_CD")
    private String balanceTypeCode;
    @Id
    @Column(name = "FIN_OBJ_TYP_CD")
    private String objectTypeCode;
    @Id
    @Column(name = "UNIV_FISCAL_PRD_CD")
    private String universityFiscalAccountingPeriod;
    @Id
    @Column(name = "PROJECT_CD")
    private String projectCode;
    @Id
    @Column(name = "ORG_REFERENCE_ID")
    private String organizationReferenceId;
    @Column(name = "ACCT_OBJ_DCST_AMT")
    private KualiDecimal accountObjectDirectCostAmount;

    @Transient
    private Account account;
    @Transient
    private SystemOptions option;

    /**
     * 
     */
    public ExpenditureTransaction() {
        super();
    }

    public ExpenditureTransaction(Transaction t) {
        universityFiscalYear = t.getUniversityFiscalYear();
        chartOfAccountsCode = t.getChartOfAccountsCode();
        accountNumber = t.getAccountNumber();
        subAccountNumber = t.getSubAccountNumber();
        objectCode = t.getFinancialObjectCode();
        subObjectCode = t.getFinancialSubObjectCode();
        balanceTypeCode = t.getFinancialBalanceTypeCode();
        objectTypeCode = t.getFinancialObjectTypeCode();
        universityFiscalAccountingPeriod = t.getUniversityFiscalPeriodCode();
        projectCode = t.getProjectCode();
        organizationReferenceId = t.getOrganizationReferenceId();
        accountObjectDirectCostAmount = new KualiDecimal(KualiDecimal.ZERO.toString());
    }

    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap map = new LinkedHashMap();
        map.put(UNIVERISITY_FISCAL_YEAR, getUniversityFiscalYear());
        map.put(CHART_OF_ACCOUNTS_CODE, getChartOfAccountsCode());
        map.put(ACCOUNT_NUMBER, getAccountNumber());
        map.put(SUB_ACCOUNT_NUMBER, getSubAccountNumber());
        map.put(OBJECT_CODE, getObjectCode());
        map.put(SUB_OBJECT_CODE, getSubObjectCode());
        map.put(BALANCE_TYPE_CODE, getBalanceTypeCode());
        map.put(OBJECT_TYPE_CODE, getObjectTypeCode());
        map.put(UNIVERSITY_FISCAL_ACCOUNTING_PERIOD, getUniversityFiscalAccountingPeriod());
        map.put(PROJECT_CODE, getProjectCode());
        map.put(ORGANIZATION_REFERENCE_ID, getOrganizationReferenceId());
        return map;
    }

    public SystemOptions getOption() {
        return option;
    }

    public void setOption(SystemOptions option) {
        this.option = option;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account a) {
        account = a;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public KualiDecimal getAccountObjectDirectCostAmount() {
        return accountObjectDirectCostAmount;
    }

    public void setAccountObjectDirectCostAmount(KualiDecimal accountObjectDirectCostAmount) {
        this.accountObjectDirectCostAmount = accountObjectDirectCostAmount;
    }

    public String getBalanceTypeCode() {
        return balanceTypeCode;
    }

    public void setBalanceTypeCode(String balanceTypeCode) {
        this.balanceTypeCode = balanceTypeCode;
    }

    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getObjectTypeCode() {
        return objectTypeCode;
    }

    public void setObjectTypeCode(String objectTypeCode) {
        this.objectTypeCode = objectTypeCode;
    }

    public String getOrganizationReferenceId() {
        return organizationReferenceId;
    }

    public void setOrganizationReferenceId(String organizationReferenceId) {
        this.organizationReferenceId = organizationReferenceId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }

    public String getSubObjectCode() {
        return subObjectCode;
    }

    public void setSubObjectCode(String subObjectCode) {
        this.subObjectCode = subObjectCode;
    }

    public String getUniversityFiscalAccountingPeriod() {
        return universityFiscalAccountingPeriod;
    }

    public void setUniversityFiscalAccountingPeriod(String universityFiscalAccountingPeriod) {
        this.universityFiscalAccountingPeriod = universityFiscalAccountingPeriod;
    }

    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    public static class PK implements java.io.Serializable {
        private static final long serialVersionUID = 1L;
        private Integer universityFiscalYear;
        private String chartOfAccountsCode;
        private String accountNumber;
        private String subAccountNumber;
        private String objectCode;
        private String subObjectCode;
        private String balanceTypeCode;
        private String objectTypeCode;
        private String universityFiscalAccountingPeriod;
        private String projectCode;
        private String organizationReferenceId;

        public PK() {}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PK)) return false;
            PK that = (PK) o;
            return java.util.Objects.equals(universityFiscalYear, that.universityFiscalYear) && java.util.Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && java.util.Objects.equals(accountNumber, that.accountNumber) && java.util.Objects.equals(subAccountNumber, that.subAccountNumber) && java.util.Objects.equals(objectCode, that.objectCode) && java.util.Objects.equals(subObjectCode, that.subObjectCode) && java.util.Objects.equals(balanceTypeCode, that.balanceTypeCode) && java.util.Objects.equals(objectTypeCode, that.objectTypeCode) && java.util.Objects.equals(universityFiscalAccountingPeriod, that.universityFiscalAccountingPeriod) && java.util.Objects.equals(projectCode, that.projectCode) && java.util.Objects.equals(organizationReferenceId, that.organizationReferenceId);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber, objectCode, subObjectCode, balanceTypeCode, objectTypeCode, universityFiscalAccountingPeriod, projectCode, organizationReferenceId);
        }
    }
}
