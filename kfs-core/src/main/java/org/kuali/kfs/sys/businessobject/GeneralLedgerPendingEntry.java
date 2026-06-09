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

package org.kuali.kfs.sys.businessobject;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.coa.businessobject.A21SubAccount;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.AccountingPeriod;
import org.kuali.kfs.coa.businessobject.BalanceType;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.businessobject.ObjectType;
import org.kuali.kfs.coa.businessobject.ProjectCode;
import org.kuali.kfs.coa.businessobject.SubAccount;
import org.kuali.kfs.coa.businessobject.SubObjectCode;
import org.kuali.kfs.gl.businessobject.Transaction;
import org.kuali.kfs.gl.businessobject.TransientBalanceInquiryAttributes;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.sys.document.validation.impl.AccountingDocumentRuleBaseConstants.GENERAL_LEDGER_PENDING_ENTRY_CODE;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.core.web.format.CurrencyFormatter;
import org.kuali.rice.kew.api.doctype.DocumentTypeService;
import org.kuali.rice.kew.doctype.bo.DocumentType;
import org.kuali.rice.kew.doctype.bo.DocumentTypeEBO;
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
 * The general ledger pending entry structure holds financial transaction info that will post to the general ledger as an entry.
 */
@Entity
@Table(name = "GL_PENDING_ENTRY_T")
public class GeneralLedgerPendingEntry extends PersistableBusinessObjectBase implements Transaction, Serializable, Cloneable {
    private static final long serialVersionUID = 4041748389323105932L;
    @Id
    @Column(name = "FS_ORIGIN_CD")
    private String financialSystemOriginationCode;
    @Id
    @Column(name = "FDOC_NBR")
    private String documentNumber;
    @Id
    @Column(name = "TRN_ENTR_SEQ_NBR")
    private Integer transactionLedgerEntrySequenceNumber;
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
    @Column(name = "FIN_BALANCE_TYP_CD")
    private String financialBalanceTypeCode;
    @Column(name = "FIN_OBJ_TYP_CD")
    private String financialObjectTypeCode;
    @Column(name = "UNIV_FISCAL_YR")
    private Integer universityFiscalYear;
    @Column(name = "UNIV_FISCAL_PRD_CD")
    private String universityFiscalPeriodCode;
    @Column(name = "TRN_LDGR_ENTR_DESC")
    private String transactionLedgerEntryDescription;
    @Column(name = "TRN_LDGR_ENTR_AMT")
    private KualiDecimal transactionLedgerEntryAmount;
    @Column(name = "TRN_DEBIT_CRDT_CD")
    private String transactionDebitCreditCode;
    @Column(name = "TRANSACTION_DT")
    private Date transactionDate;
    @Column(name = "FDOC_TYP_CD")
    private String financialDocumentTypeCode;
    @Column(name = "ORG_DOC_NBR")
    private String organizationDocumentNumber;
    @Column(name = "PROJECT_CD")
    private String projectCode;
    @Column(name = "ORG_REFERENCE_ID")
    private String organizationReferenceId;
    @Column(name = "FDOC_REF_TYP_CD")
    private String referenceFinancialDocumentTypeCode;
    @Column(name = "FS_REF_ORIGIN_CD")
    private String referenceFinancialSystemOriginationCode;
    @Column(name = "FDOC_REF_NBR")
    private String referenceFinancialDocumentNumber;
    @Column(name = "FDOC_REVERSAL_DT")
    private Date financialDocumentReversalDate;
    @Column(name = "TRN_ENCUM_UPDT_CD")
    private String transactionEncumbranceUpdateCode;
    @Column(name = "FDOC_APPROVED_CD")
    private String financialDocumentApprovedCode;
    @Column(name = "ACCT_SF_FINOBJ_CD")
    private String acctSufficientFundsFinObjCd;
    @Column(name = "TRN_ENTR_OFST_CD")
    private boolean transactionEntryOffsetIndicator;
    @Column(name = "TRNENTR_PROCESS_TM")
    private Timestamp transactionEntryProcessedTs;

    @Transient
    private DocumentTypeEBO financialSystemDocumentTypeCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FDOC_NBR", insertable = false, updatable = false)
    private FinancialSystemDocumentHeader documentHeader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIV_FISCAL_YR", insertable = false, updatable = false)
    private SystemOptions option;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIN_COA_CD", insertable = false, updatable = false)
    private Chart chart;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    private SubAccount subAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    private ObjectCode financialObject;
    @ManyToOne(fetch = FetchType.LAZY)
    private SubObjectCode financialSubObject;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIN_BALANCE_TYP_CD", insertable = false, updatable = false)
    private BalanceType balanceType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIN_OBJ_TYP_CD", insertable = false, updatable = false)
    private ObjectType objectType;
    @ManyToOne(fetch = FetchType.LAZY)
    private A21SubAccount a21SubAccount;
    @Transient
    private TransientBalanceInquiryAttributes dummyBusinessObject;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FS_ORIGIN_CD", insertable = false, updatable = false)
    private OriginationCode originationCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_CD", insertable = false, updatable = false)
    private ProjectCode project;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FS_REF_ORIGIN_CD", insertable = false, updatable = false)
    private OriginationCode referenceOriginationCode;
    @Transient
    private DocumentTypeEBO referenceFinancialSystemDocumentTypeCode;

    @Deprecated
    private transient AccountingPeriod accountingPeriod;

    /**
     * Default no-arg constructor.
     */
    public GeneralLedgerPendingEntry() {
        this.objectType = new ObjectType();
        this.balanceType = new BalanceType();
        this.dummyBusinessObject = new TransientBalanceInquiryAttributes();
        this.financialObject = new ObjectCode();
        this.financialDocumentApprovedCode = KFSConstants.PENDING_ENTRY_APPROVED_STATUS_CODE.NOT_PROCESSED;
    }

    /**
     * Copy constructor Constructs a GeneralLedgerPendingEntry.java.
     *
     * @param original entry to copy
     */
    public GeneralLedgerPendingEntry(GeneralLedgerPendingEntry original) {
        financialSystemOriginationCode = original.getFinancialSystemOriginationCode();
        documentNumber = original.getDocumentNumber();
        transactionLedgerEntrySequenceNumber = original.getTransactionLedgerEntrySequenceNumber();
        chartOfAccountsCode = original.getChartOfAccountsCode();
        accountNumber = original.getAccountNumber();
        subAccountNumber = original.getSubAccountNumber();
        financialObjectCode = original.getFinancialObjectCode();
        financialSubObjectCode = original.getFinancialSubObjectCode();
        financialBalanceTypeCode = original.getFinancialBalanceTypeCode();
        financialObjectTypeCode = original.getFinancialObjectTypeCode();
        universityFiscalYear = original.getUniversityFiscalYear();
        universityFiscalPeriodCode = original.getUniversityFiscalPeriodCode();
        transactionLedgerEntryDescription = original.getTransactionLedgerEntryDescription();
        transactionLedgerEntryAmount = original.getTransactionLedgerEntryAmount();
        transactionDebitCreditCode = original.getTransactionDebitCreditCode();
        transactionDate = original.getTransactionDate();
        financialDocumentTypeCode = original.getFinancialDocumentTypeCode();
        organizationDocumentNumber = original.getOrganizationDocumentNumber();
        projectCode = original.getProjectCode();
        organizationReferenceId = original.getOrganizationReferenceId();
        referenceFinancialDocumentTypeCode = original.getReferenceFinancialDocumentTypeCode();
        referenceFinancialSystemOriginationCode = original.getReferenceFinancialSystemOriginationCode();
        referenceFinancialDocumentNumber = original.getReferenceFinancialDocumentNumber();
        financialDocumentReversalDate = original.getFinancialDocumentReversalDate();
        transactionEncumbranceUpdateCode = original.getTransactionEncumbranceUpdateCode();
        financialDocumentApprovedCode = original.getFinancialDocumentApprovedCode();
        acctSufficientFundsFinObjCd = original.getAcctSufficientFundsFinObjCd();
        transactionEntryOffsetIndicator = original.isTransactionEntryOffsetIndicator();
        transactionEntryProcessedTs = original.getTransactionEntryProcessedTs();

        financialSystemDocumentTypeCode = original.getFinancialSystemDocumentTypeCode();
        documentHeader = original.getDocumentHeader();

        option = original.getOption();
        chart = original.getChart();
        account = original.getAccount();
        subAccount = original.getSubAccount();
        financialObject = original.getFinancialObject();
        financialSubObject = original.getFinancialSubObject();
        balanceType = original.getBalanceType();
        a21SubAccount = original.getA21SubAccount();
        dummyBusinessObject = original.getDummyBusinessObject();
        originationCode = original.getOriginationCode();
        project = original.getProject();
        referenceOriginationCode = original.getReferenceOriginationCode();
        referenceFinancialSystemDocumentTypeCode = original.getReferenceFinancialSystemDocumentTypeCode();
    }

    public DocumentTypeEBO getReferenceFinancialSystemDocumentTypeCode() {
        if ( StringUtils.isBlank( referenceFinancialDocumentTypeCode ) ) {
            referenceFinancialSystemDocumentTypeCode = null;
        } else {
            if ( referenceFinancialSystemDocumentTypeCode == null || !StringUtils.equals(referenceFinancialDocumentTypeCode, referenceFinancialSystemDocumentTypeCode.getName() ) ) {
                org.kuali.rice.kew.api.doctype.DocumentType temp = SpringContext.getBean(DocumentTypeService.class).getDocumentTypeByName(referenceFinancialDocumentTypeCode);
                if ( temp != null ) {
                    referenceFinancialSystemDocumentTypeCode = DocumentType.from( temp );
                } else {
                    referenceFinancialSystemDocumentTypeCode = null;
                }
            }
        }
        return referenceFinancialSystemDocumentTypeCode;
    }

    public OriginationCode getReferenceOriginationCode() {
        return referenceOriginationCode;
    }

    public void setReferenceOriginationCode(OriginationCode referenceOriginationCode) {
        this.referenceOriginationCode = referenceOriginationCode;
    }

    public ProjectCode getProject() {
        return project;
    }

    public void setProject(ProjectCode project) {
        this.project = project;
    }

    public OriginationCode getOriginationCode() {
        return originationCode;
    }

    public void setOriginationCode(OriginationCode originationCode) {
        this.originationCode = originationCode;
    }

    @Override
    public void setOption(SystemOptions option) {
        this.option = option;
    }

    @Override
    public SystemOptions getOption() {
        return option;
    }

    /**
     * Gets the documentNumber attribute.
     *
     * @return Returns the documentNumber
     */
    @Override
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Sets the documentNumber attribute.
     *
     * @param documentNumber The documentNumber to set.
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * Gets the transactionLedgerEntrySequenceNumber attribute.
     *
     * @return Returns the transactionLedgerEntrySequenceNumber
     */
    @Override
    public Integer getTransactionLedgerEntrySequenceNumber() {
        return transactionLedgerEntrySequenceNumber;
    }

    /**
     * Sets the transactionLedgerEntrySequenceNumber attribute.
     *
     * @param transactionLedgerEntrySequenceNumber The transactionLedgerEntrySequenceNumber to set.
     */
    public void setTransactionLedgerEntrySequenceNumber(Integer transactionLedgerEntrySequenceNumber) {
        this.transactionLedgerEntrySequenceNumber = transactionLedgerEntrySequenceNumber;
    }

    /**
     * Gets the chartOfAccountsCode attribute.
     *
     * @return Returns the chartOfAccountsCode
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
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
     * Gets the financialBalanceTypeCode attribute.
     *
     * @return Returns the financialBalanceTypeCode
     */
    @Override
    public String getFinancialBalanceTypeCode() {
        return financialBalanceTypeCode;
    }

    /**
     * Sets the financialBalanceTypeCode attribute.
     *
     * @param financialBalanceTypeCode The financialBalanceTypeCode to set.
     */
    public void setFinancialBalanceTypeCode(String financialBalanceTypeCode) {
        this.financialBalanceTypeCode = financialBalanceTypeCode;
    }

    /**
     * Gets the financialObjectTypeCode attribute.
     *
     * @return Returns the financialObjectTypeCode
     */
    @Override
    public String getFinancialObjectTypeCode() {
        return financialObjectTypeCode;
    }


    /**
     * Sets the financialObjectTypeCode attribute.
     *
     * @param financialObjectTypeCode The financialObjectTypeCode to set.
     */
    public void setFinancialObjectTypeCode(String financialObjectTypeCode) {
        this.financialObjectTypeCode = financialObjectTypeCode;
    }

    /**
     * Gets the universityFiscalYear attribute.
     *
     * @return Returns the universityFiscalYear
     */
    @Override
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
     * Gets the universityFiscalPeriodCode attribute.
     *
     * @return Returns the universityFiscalPeriodCode
     */
    @Override
    public String getUniversityFiscalPeriodCode() {
        return universityFiscalPeriodCode;
    }

    /**
     * Sets the universityFiscalPeriodCode attribute.
     *
     * @param universityFiscalPeriodCode The universityFiscalPeriodCode to set.
     */
    public void setUniversityFiscalPeriodCode(String universityFiscalPeriodCode) {
        this.universityFiscalPeriodCode = universityFiscalPeriodCode;
    }

    /**
     * Gets the transactionLedgerEntryDescription attribute.
     *
     * @return Returns the transactionLedgerEntryDescription
     */
    @Override
    public String getTransactionLedgerEntryDescription() {
        return transactionLedgerEntryDescription;
    }

    /**
     * Sets the transactionLedgerEntryDescription attribute.
     *
     * @param transactionLedgerEntryDescription The transactionLedgerEntryDescription to set.
     */
    public void setTransactionLedgerEntryDescription(String transactionLedgerEntryDescription) {
        this.transactionLedgerEntryDescription = transactionLedgerEntryDescription;
    }

    /**
     * Gets the transactionLedgerEntryAmount attribute.
     *
     * @return Returns the transactionLedgerEntryAmount
     */
    @Override
    public KualiDecimal getTransactionLedgerEntryAmount() {
        return transactionLedgerEntryAmount;
    }

    /**
     * Sets the transactionLedgerEntryAmount attribute.
     *
     * @param transactionLedgerEntryAmount The transactionLedgerEntryAmount to set.
     */
    public void setTransactionLedgerEntryAmount(KualiDecimal transactionLedgerEntryAmount) {
        this.transactionLedgerEntryAmount = transactionLedgerEntryAmount;
    }

    /**
     * Gets the transactionDebitCreditCode attribute.
     *
     * @return Returns the transactionDebitCreditCode
     */
    @Override
    public String getTransactionDebitCreditCode() {
        return transactionDebitCreditCode;
    }

    /**
     * Sets the transactionDebitCreditCode attribute.
     *
     * @param transactionDebitCreditCode The transactionDebitCreditCode to set.
     */
    public void setTransactionDebitCreditCode(String transactionDebitCreditCode) {
        this.transactionDebitCreditCode = transactionDebitCreditCode;
    }

    /**
     * Gets the transactionDate attribute.
     *
     * @return Returns the transactionDate
     */
    @Override
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the transactionDate attribute.
     *
     * @param transactionDate The transactionDate to set.
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the financialDocumentTypeCode attribute.
     *
     * @return Returns the financialDocumentTypeCode
     */
    @Override
    public String getFinancialDocumentTypeCode() {
        return financialDocumentTypeCode;
    }

    /**
     * Sets the financialDocumentTypeCode attribute.
     *
     * @param financialDocumentTypeCode The financialDocumentTypeCode to set.
     */
    public void setFinancialDocumentTypeCode(String financialDocumentTypeCode) {
        this.financialDocumentTypeCode = financialDocumentTypeCode;
    }

    /**
     * Gets the organizationDocumentNumber attribute.
     *
     * @return Returns the organizationDocumentNumber
     */
    @Override
    public String getOrganizationDocumentNumber() {
        return organizationDocumentNumber;
    }

    /**
     * Sets the organizationDocumentNumber attribute.
     *
     * @param organizationDocumentNumber The organizationDocumentNumber to set.
     */
    public void setOrganizationDocumentNumber(String organizationDocumentNumber) {
        this.organizationDocumentNumber = organizationDocumentNumber;
    }

    /**
     * Gets the projectCode attribute.
     *
     * @return Returns the projectCode
     */
    @Override
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * Sets the projectCode attribute.
     *
     * @param projectCode The projectCode to set.
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    /**
     * Gets the organizationReferenceId attribute.
     *
     * @return Returns the organizationReferenceId
     */
    @Override
    public String getOrganizationReferenceId() {
        return organizationReferenceId;
    }

    /**
     * Sets the organizationReferenceId attribute.
     *
     * @param organizationReferenceId The organizationReferenceId to set.
     */
    public void setOrganizationReferenceId(String organizationReferenceId) {
        this.organizationReferenceId = organizationReferenceId;
    }

    /**
     * Gets the referenceFinancialDocumentTypeCode attribute.
     *
     * @return Returns the referenceFinancialDocumentTypeCode
     */
    @Override
    public String getReferenceFinancialDocumentTypeCode() {
        return referenceFinancialDocumentTypeCode;
    }

    /**
     * Sets the referenceFinancialDocumentTypeCode attribute.
     *
     * @param referenceFinancialDocumentTypeCode The referenceFinancialDocumentTypeCode to set.
     */
    public void setReferenceFinancialDocumentTypeCode(String referenceFinancialDocumentTypeCode) {
        this.referenceFinancialDocumentTypeCode = referenceFinancialDocumentTypeCode;
    }

    /**
     * Gets the referenceFinancialSystemOriginationCode attribute.
     *
     * @return Returns the referenceFinancialSystemOriginationCode
     */
    @Override
    public String getReferenceFinancialSystemOriginationCode() {
        return referenceFinancialSystemOriginationCode;
    }

    /**
     * Sets the referenceFinancialSystemOriginationCode attribute.
     *
     * @param referenceFinancialSystemOriginationCode The referenceFinancialSystemOriginationCode to set.
     */
    public void setReferenceFinancialSystemOriginationCode(String referenceFinancialSystemOriginationCode) {
        this.referenceFinancialSystemOriginationCode = referenceFinancialSystemOriginationCode;
    }

    /**
     * Gets the referenceFinancialDocumentNumber attribute.
     *
     * @return Returns the referenceFinancialDocumentNumber
     */
    @Override
    public String getReferenceFinancialDocumentNumber() {
        return referenceFinancialDocumentNumber;
    }

    /**
     * Sets the referenceFinancialDocumentNumber attribute.
     *
     * @param referenceFinancialDocumentNumber The referenceFinancialDocumentNumber to set.
     */
    public void setReferenceFinancialDocumentNumber(String referenceFinancialDocumentNumber) {
        this.referenceFinancialDocumentNumber = referenceFinancialDocumentNumber;
    }

    /**
     * Gets the financialDocumentReversalDate attribute.
     *
     * @return Returns the financialDocumentReversalDate
     */
    @Override
    public Date getFinancialDocumentReversalDate() {
        return financialDocumentReversalDate;
    }

    /**
     * Sets the financialDocumentReversalDate attribute.
     *
     * @param financialDocumentReversalDate The financialDocumentReversalDate to set.
     */
    public void setFinancialDocumentReversalDate(Date financialDocumentReversalDate) {
        this.financialDocumentReversalDate = financialDocumentReversalDate;
    }

    /**
     * Gets the transactionEncumbranceUpdateCode attribute.
     *
     * @return Returns the transactionEncumbranceUpdateCode
     */
    @Override
    public String getTransactionEncumbranceUpdateCode() {
        return transactionEncumbranceUpdateCode;
    }

    /**
     * Sets the transactionEncumbranceUpdateCode attribute.
     *
     * @param transactionEncumbranceUpdateCode The transactionEncumbranceUpdateCode to set.
     */
    public void setTransactionEncumbranceUpdateCode(String transactionEncumbranceUpdateCode) {
        this.transactionEncumbranceUpdateCode = transactionEncumbranceUpdateCode;
    }

    /**
     * Gets the financialDocumentApprovedCode attribute.
     *
     * @return Returns the financialDocumentApprovedCode
     */
    public String getFinancialDocumentApprovedCode() {
        return financialDocumentApprovedCode;
    }


    /**
     * Sets the financialDocumentApprovedCode attribute.
     *
     * @param financialDocumentApprovedCode The financialDocumentApprovedCode to set.
     */
    public void setFinancialDocumentApprovedCode(String financialDocumentApprovedCode) {
        this.financialDocumentApprovedCode = financialDocumentApprovedCode;
    }

    /**
     * Gets the acctSufficientFundsFinObjCd attribute.
     *
     * @return Returns the acctSufficientFundsFinObjCd
     */
    public String getAcctSufficientFundsFinObjCd() {
        return acctSufficientFundsFinObjCd;
    }

    /**
     * Sets the acctSufficientFundsFinObjCd attribute.
     *
     * @param acctSufficientFundsFinObjCd The acctSufficientFundsFinObjCd to set.
     */
    public void setAcctSufficientFundsFinObjCd(String acctSufficientFundsFinObjCd) {
        this.acctSufficientFundsFinObjCd = acctSufficientFundsFinObjCd;
    }

    /**
     * Gets the transactionEntryOffsetIndicator attribute.
     *
     * @return Returns the transactionEntryOffsetIndicator
     */
    public boolean isTransactionEntryOffsetIndicator() {
        return transactionEntryOffsetIndicator;
    }

    /**
     * Sets the transactionEntryOffsetIndicator attribute.
     *
     * @param transactionEntryOffsetIndicator The transactionEntryOffsetIndicator to set.
     */
    public void setTransactionEntryOffsetIndicator(boolean transactionEntryOffsetIndicator) {
        this.transactionEntryOffsetIndicator = transactionEntryOffsetIndicator;
    }

    /**
     * Gets the transactionEntryProcessedTs attribute.
     *
     * @return Returns the transactionEntryProcessedTs
     */
    public Timestamp getTransactionEntryProcessedTs() {
        return transactionEntryProcessedTs;
    }

    /**
     * Sets the transactionEntryProcessedTs attribute.
     *
     * @param transactionEntryProcessedTs The transactionEntryProcessedTs to set.
     */
    public void setTransactionEntryProcessedTs(Timestamp transactionEntryProcessedTs) {
        this.transactionEntryProcessedTs = transactionEntryProcessedTs;
    }

    /**
     * @return Returns the financialSystemOriginationCode.
     */
    @Override
    public String getFinancialSystemOriginationCode() {
        return financialSystemOriginationCode;
    }

    /**
     * @param financialSystemOriginationCode The financialSystemOriginationCode to set.
     */
    public void setFinancialSystemOriginationCode(String financialSystemOriginationCode) {
        this.financialSystemOriginationCode = financialSystemOriginationCode;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("financialSystemOriginationCode", this.financialSystemOriginationCode);
        m.put(KFSPropertyConstants.DOCUMENT_NUMBER, this.documentNumber);
        if (transactionLedgerEntrySequenceNumber == null) {
            m.put("transactionLedgerEntrySequenceNumber", null);
        }
        else {
            m.put("transactionLedgerEntrySequenceNumber", this.transactionLedgerEntrySequenceNumber.toString());
        }
        return m;
    }

    /**
     * Gets the financialSystemDocumentTypeCode attribute.
     * @return Returns the financialSystemDocumentTypeCode.
     */
    @Override
    public DocumentTypeEBO getFinancialSystemDocumentTypeCode() {
        if ( StringUtils.isBlank( financialDocumentTypeCode ) ) {
            financialSystemDocumentTypeCode = null;
        } else {
            if ( financialSystemDocumentTypeCode == null || !StringUtils.equals(financialDocumentTypeCode, financialSystemDocumentTypeCode.getName() ) ) {
                org.kuali.rice.kew.api.doctype.DocumentType temp = SpringContext.getBean(DocumentTypeService.class).getDocumentTypeByName(financialDocumentTypeCode);
                if ( temp != null ) {
                    financialSystemDocumentTypeCode = DocumentType.from( temp );
                } else {
                    financialSystemDocumentTypeCode = null;
                }
            }
        }
        return financialSystemDocumentTypeCode;
    }

    /**
     * Gets the documentHeader attribute.
     *
     * @return Returns the documentHeader.
     */
    public FinancialSystemDocumentHeader getDocumentHeader() {
        return documentHeader;
    }

    /**
     * Sets the documentHeader attribute value.
     *
     * @param documentHeader The documentHeader to set.
     */
    public void setDocumentHeader(FinancialSystemDocumentHeader documentHeader) {
        this.documentHeader = documentHeader;
    }

    /**
     * Gets the account attribute.
     *
     * @return Returns the account.
     */
    @Override
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account attribute value.
     *
     * @param account The account to set.
     */
    @Override
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets the balanceType attribute.
     *
     * @return Returns the balanceType.
     */
    @Override
    public BalanceType getBalanceType() {
        return balanceType;
    }

    /**
     * Sets the balanceType attribute value.
     *
     * @param balanceType The balanceType to set.
     */
    @Override
    public void setBalanceType(BalanceType balanceType) {
        this.balanceType = balanceType;
    }

    /**
     * Gets the chart attribute.
     *
     * @return Returns the chart.
     */
    @Override
    public Chart getChart() {
        return chart;
    }

    /**
     * Sets the chart attribute value.
     *
     * @param chart The chart to set.
     */
    @Override
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    /**
     * Gets the financialObject attribute.
     *
     * @return Returns the financialObject.
     */
    @Override
    public ObjectCode getFinancialObject() {
        return financialObject;
    }

    /**
     * Sets the financialObject attribute value.
     *
     * @param financialObject The financialObject to set.
     */
    @Override
    public void setFinancialObject(ObjectCode financialObject) {
        this.financialObject = financialObject;
    }

    /**
     * Gets the objectType attribute.
     *
     * @return Returns the objectType.
     */
    @Override
    public ObjectType getObjectType() {
        return objectType;
    }

    /**
     * Sets the objectType attribute value.
     *
     * @param objectType The objectType to set.
     */
    @Override
    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    /**
     * Gets the a21SubAccount attribute.
     *
     * @return Returns the a21SubAccount.
     */
    public A21SubAccount getA21SubAccount() {
        return this.a21SubAccount;
    }

    /**
     * Sets the a21SubAccount attribute value.
     *
     * @param a21SubAccount The a21SubAccount to set.
     */
    public void setA21SubAccount(A21SubAccount a21SubAccount) {
        this.a21SubAccount = a21SubAccount;
    }

    /**
     * Gets the dummyBusinessObject attribute.
     *
     * @return Returns the dummyBusinessObject.
     */
    public TransientBalanceInquiryAttributes getDummyBusinessObject() {
        return this.dummyBusinessObject;
    }

    /**
     * Sets the dummyBusinessObject attribute value.
     *
     * @param dummyBusinessObject The dummyBusinessObject to set.
     */
    public void setDummyBusinessObject(TransientBalanceInquiryAttributes dummyBusinessObject) {
        this.dummyBusinessObject = dummyBusinessObject;
    }

    @Override
    public SubAccount getSubAccount() {
        return subAccount;
    }

    public void setSubAccount(SubAccount subAccount) {
        this.subAccount = subAccount;
    }

    @Override
    public SubObjectCode getFinancialSubObject() {
        return financialSubObject;
    }

    public void setFinancialSubObject(SubObjectCode financialSubObject) {
        this.financialSubObject = financialSubObject;
    }

    public boolean isSubAccountNumberBlank() {
        return subAccountNumber == null || GENERAL_LEDGER_PENDING_ENTRY_CODE.getBlankSubAccountNumber().equals(subAccountNumber);
    }

    public boolean isFinancialObjectCodeBlank() {
        return financialObjectCode == null || GENERAL_LEDGER_PENDING_ENTRY_CODE.getBlankFinancialObjectCode().equals(financialObjectCode);
    }

    public boolean isFinancialSubObjectCodeBlank() {
        return financialSubObjectCode == null || GENERAL_LEDGER_PENDING_ENTRY_CODE.getBlankFinancialSubObjectCode().equals(financialSubObjectCode);
    }

    public boolean isProjectCodeBlank() {
        return projectCode == null || GENERAL_LEDGER_PENDING_ENTRY_CODE.getBlankProjectCode().equals(projectCode);
    }

    public boolean isFinancialObjectTypeCodeBlank() {
        return financialObjectTypeCode == null || GENERAL_LEDGER_PENDING_ENTRY_CODE.getBlankFinancialObjectType().equals(financialObjectTypeCode);
    }

    @Deprecated
    public AccountingPeriod getAccountingPeriod() {
        return accountingPeriod;
    }

    @Deprecated
    public void setAccountingPeriod(AccountingPeriod accountingPeriod) {
        this.accountingPeriod = accountingPeriod;
    }

    /**
     * @return the amount formatted as a currency number
     */
    public String getCurrencyFormattedTransactionLedgerEntryAmount() {
        return (String) new CurrencyFormatter().format(getTransactionLedgerEntryAmount());
    }
}
