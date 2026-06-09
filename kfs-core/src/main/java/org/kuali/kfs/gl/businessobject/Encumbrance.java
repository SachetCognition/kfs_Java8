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
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.BalanceType;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.businessobject.SubAccount;
import org.kuali.kfs.coa.businessobject.SubObjectCode;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.OriginationCode;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kew.api.doctype.DocumentTypeService;
import org.kuali.rice.kew.doctype.bo.DocumentType;
import org.kuali.rice.kew.doctype.bo.DocumentTypeEBO;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

/**
 * Represents the encumbrance amount for a specific university fiscal year,
 * chart of accounts code, account number, sub account number, object code,
 * sub object code, balance type code, document type code, origin code, and document number.
 * This encumbrance object contains amounts for actual enumbrance amount, closed amount,
 * outstanding amount
 *
*/
@Entity
@Table(name = "GL_ENCUMBRANCE_T")
@IdClass(Encumbrance.PK.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Encumbrance extends PersistableBusinessObjectBase {
    static final long serialVersionUID = -7494473472438516396L;

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
    @Column(name = "FDOC_TYP_CD")
    private String documentTypeCode;
    @Id
    @Column(name = "FS_ORIGIN_CD")
    private String originCode;
    @Id
    @Column(name = "FDOC_NBR")
    private String documentNumber;
    @Column(name = "TRN_ENCUM_DESC")
    private String transactionEncumbranceDescription;
    @Column(name = "TRN_ENCUM_DT")
    private Date transactionEncumbranceDate;
    @Column(name = "ACLN_ENCUM_AMT")
    private KualiDecimal accountLineEncumbranceAmount;
    @Column(name = "ACLN_ENCUM_CLS_AMT")
    private KualiDecimal accountLineEncumbranceClosedAmount;
    @Transient
    private KualiDecimal accountLineEncumbranceOutstandingAmount;
    @Column(name = "ACLN_ENCUM_PRG_CD")
    private String accountLineEncumbrancePurgeCode;
    @Column(name = "TIMESTAMP")
    private Timestamp timestamp;

    @Transient
    private SubAccount subAccount;
    @Transient
    private Chart chart;
    @Transient
    private Account account;
    @Transient
    private SubObjectCode financialSubObject;
    @Transient
    private DocumentTypeEBO financialSystemDocumentTypeCode;

    @Transient
    private ObjectCode financialObject;
    @Transient
    private BalanceType balanceType;
    @Transient
    private OriginationCode originationCode;
    @Transient
    private SystemOptions option;

    @Transient

    private TransientBalanceInquiryAttributes dummyBusinessObject;

    public Encumbrance() {
    }

    public Encumbrance(Transaction t) {
        universityFiscalYear = t.getUniversityFiscalYear();
        chartOfAccountsCode = t.getChartOfAccountsCode();
        accountNumber = t.getAccountNumber();
        subAccountNumber = t.getSubAccountNumber();
        objectCode = t.getFinancialObjectCode();
        subObjectCode = t.getFinancialSubObjectCode();
        balanceTypeCode = t.getFinancialBalanceTypeCode();
        documentTypeCode = t.getFinancialDocumentTypeCode();
        originCode = t.getFinancialSystemOriginationCode();
        documentNumber = t.getDocumentNumber();
        transactionEncumbranceDescription = t.getTransactionLedgerEntryDescription();
        transactionEncumbranceDate = t.getTransactionDate();
        accountLineEncumbranceAmount = KualiDecimal.ZERO;
        accountLineEncumbranceClosedAmount = KualiDecimal.ZERO;
        accountLineEncumbrancePurgeCode = " ";
        this.dummyBusinessObject = new TransientBalanceInquiryAttributes();
    }

    /**
     * Constructs a AccountBalance.java per the primary keys only of the passed in accountBalanceHistory
     * @param accountBalanceHistory
     */
    public Encumbrance(EncumbranceHistory encumbranceHistory) {
        universityFiscalYear = encumbranceHistory.getUniversityFiscalYear();
        chartOfAccountsCode = encumbranceHistory.getChartOfAccountsCode();
        accountNumber = encumbranceHistory.getAccountNumber();
        subAccountNumber = encumbranceHistory.getSubAccountNumber();
        objectCode = encumbranceHistory.getObjectCode();
        subObjectCode = encumbranceHistory.getSubObjectCode();
        balanceTypeCode = encumbranceHistory.getBalanceTypeCode();
        documentTypeCode = encumbranceHistory.getDocumentTypeCode();
        originCode = encumbranceHistory.getOriginCode();
        documentNumber = encumbranceHistory.getDocumentNumber();
    }


    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap map = new LinkedHashMap();
        map.put(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, getUniversityFiscalYear());
        map.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, getChartOfAccountsCode());
        map.put(KFSPropertyConstants.ACCOUNT_NUMBER, getAccountNumber());
        map.put(KFSPropertyConstants.SUB_ACCOUNT_NUMBER, getSubAccountNumber());
        map.put(KFSPropertyConstants.OBJECT_CODE, getObjectCode());
        map.put(KFSPropertyConstants.SUB_OBJECT_CODE, getSubObjectCode());
        map.put(KFSPropertyConstants.BALANCE_TYPE_CODE, getBalanceTypeCode());
        map.put(KFSPropertyConstants.ENCUMBRANCE_DOCUMENT_TYPE_CODE, getDocumentTypeCode());
        map.put(KFSPropertyConstants.ORIGIN_CODE, getOriginCode());
        map.put(KFSPropertyConstants.DOCUMENT_NUMBER, getDocumentNumber());
        map.put(KFSPropertyConstants.ACCOUNT_LINE_ENCUMBRANCE_AMOUNT, getAccountLineEncumbranceAmount());
        map.put(KFSPropertyConstants.ACCOUNT_LINE_ENCUMBRANCE_CLOSED_AMOUNT, getAccountLineEncumbranceClosedAmount());
        return map;
    }

    public OriginationCode getOriginationCode() {
        return originationCode;
    }

    public void setOriginationCode(OriginationCode originationCode) {
        this.originationCode = originationCode;
    }

    /**
     * @return Returns the accountLineEncumbranceAmount.
     */
    public KualiDecimal getAccountLineEncumbranceAmount() {
        return accountLineEncumbranceAmount;
    }

    /**
     * @param accountLineEncumbranceAmount The accountLineEncumbranceAmount to set.
     */
    public void setAccountLineEncumbranceAmount(KualiDecimal accountLineEncumbranceAmount) {
        this.accountLineEncumbranceAmount = accountLineEncumbranceAmount;
    }

    /**
     * @return Returns the accountLineEncumbranceClearedAmount.
     */
    public KualiDecimal getAccountLineEncumbranceClosedAmount() {
        return accountLineEncumbranceClosedAmount;
    }

    public void setAccountLineEncumbranceOutstandingAmount() {
    }

    public KualiDecimal getAccountLineEncumbranceOutstandingAmount() {
        return accountLineEncumbranceAmount.subtract(accountLineEncumbranceClosedAmount);
    }

    /**
     * @param accountLineEncumbranceClearedAmount The accountLineEncumbranceClearedAmount to set.
     */
    public void setAccountLineEncumbranceClosedAmount(KualiDecimal accountLineEncumbranceClosedAmount) {
        this.accountLineEncumbranceClosedAmount = accountLineEncumbranceClosedAmount;
    }

    /**
     * @return Returns the accountLineEncumbrancePrg.
     */
    public String getAccountLineEncumbrancePurgeCode() {
        return accountLineEncumbrancePurgeCode;
    }

    /**
     * @param accountLineEncumbrancePrg The accountLineEncumbrancePrg to set.
     */
    public void setAccountLineEncumbrancePurgeCode(String accountLineEncumbrancePurgeCode) {
        this.accountLineEncumbrancePurgeCode = accountLineEncumbrancePurgeCode;
    }

    /**
     * @return Returns the accountNumber.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return Returns the balanceTypeCode.
     */
    public String getBalanceTypeCode() {
        return balanceTypeCode;
    }

    /**
     * @param balanceTypeCode The balanceTypeCode to set.
     */
    public void setBalanceTypeCode(String balanceTypeCode) {
        this.balanceTypeCode = balanceTypeCode;
    }

    /**
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    /**
     * @return Returns the documentNumber.
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * @param documentNumber The documentNumber to set.
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * @return Returns the documentTypeCode.
     */
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    /**
     * @param documentTypeCode The documentTypeCode to set.
     */
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    /**
     * @return Returns the objectCode.
     */
    public String getObjectCode() {
        return objectCode;
    }

    /**
     * @param objectCode The objectCode to set.
     */
    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    /**
     * @return Returns the originCode.
     */
    public String getOriginCode() {
        return originCode;
    }

    /**
     * @param originCode The originCode to set.
     */
    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    /**
     * @return Returns the subAccountNumber.
     */
    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    /**
     * @param subAccountNumber The subAccountNumber to set.
     */
    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }

    /**
     * @return Returns the subObjectCode.
     */
    public String getSubObjectCode() {
        return subObjectCode;
    }

    /**
     * @param subObjectCode The subObjectCode to set.
     */
    public void setSubObjectCode(String subObjectCode) {
        this.subObjectCode = subObjectCode;
    }

    /**
     * @return Returns the timestamp.
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The timestamp to set.
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return Returns the transactionEncumbranceDate.
     */
    public Date getTransactionEncumbranceDate() {
        return transactionEncumbranceDate;
    }

    /**
     * @param transactionEncumbranceDate The transactionEncumbranceDate to set.
     */
    public void setTransactionEncumbranceDate(Date transactionEncumbranceDate) {
        this.transactionEncumbranceDate = transactionEncumbranceDate;
    }

    /**
     * @return Returns the transactionEncumbranceDescription.
     */
    public String getTransactionEncumbranceDescription() {
        return transactionEncumbranceDescription;
    }

    /**
     * @param transactionEncumbranceDescription The transactionEncumbranceDescription to set.
     */
    public void setTransactionEncumbranceDescription(String transactionEncumbranceDescription) {
        this.transactionEncumbranceDescription = transactionEncumbranceDescription;
    }

    /**
     * @return Returns the universityFiscalYear.
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
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
     * Gets the chart attribute.
     *
     * @return Returns the chart.
     */
    public Chart getChart() {
        return chart;
    }

    /**
     * Sets the chart attribute value.
     *
     * @param chart The chart to set.
     */
    public void setChart(Chart chart) {
        this.chart = chart;
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
     * Gets the balanceType attribute.
     *
     * @return Returns the balanceType.
     */
    public BalanceType getBalanceType() {
        return balanceType;
    }

    /**
     * Sets the balanceType attribute value.
     *
     * @param balanceType The balanceType to set.
     */
    public void setBalanceType(BalanceType balanceType) {
        this.balanceType = balanceType;
    }

    /**
     * Gets the dummyBusinessObject attribute.
     *
     * @return Returns the dummyBusinessObject.
     */
    public TransientBalanceInquiryAttributes getDummyBusinessObject() {
        return dummyBusinessObject;
    }

    /**
     * Sets the dummyBusinessObject attribute value.
     *
     * @param dummyBusinessObject The dummyBusinessObject to set.
     */
    public void setDummyBusinessObject(TransientBalanceInquiryAttributes dummyBusinessObject) {
        this.dummyBusinessObject = dummyBusinessObject;
    }

    /**
     * Gets the option attribute.
     *
     * @return Returns the option.
     */
    public SystemOptions getOption() {
        return option;
    }

    /**
     * Sets the option attribute value.
     *
     * @param option The option to set.
     */
    public void setOption(SystemOptions option) {
        this.option = option;
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
     * Gets the financialSystemDocumentTypeCode attribute.
     * @return Returns the financialSystemDocumentTypeCode.
     */
    public DocumentTypeEBO getFinancialSystemDocumentTypeCode() {
        if ( StringUtils.isBlank( documentTypeCode ) ) {
            financialSystemDocumentTypeCode = null;
        } else {
            if ( financialSystemDocumentTypeCode == null || !StringUtils.equals(documentTypeCode, financialSystemDocumentTypeCode.getName() ) ) {
                org.kuali.rice.kew.api.doctype.DocumentType temp = SpringContext.getBean(DocumentTypeService.class).getDocumentTypeByName(documentTypeCode);
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

    public static class PK implements java.io.Serializable {
        private static final long serialVersionUID = 1L;
        private Integer universityFiscalYear;
        private String chartOfAccountsCode;
        private String accountNumber;
        private String subAccountNumber;
        private String objectCode;
        private String subObjectCode;
        private String balanceTypeCode;
        private String documentTypeCode;
        private String originCode;
        private String documentNumber;

        public PK() {}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PK)) return false;
            PK that = (PK) o;
            return java.util.Objects.equals(universityFiscalYear, that.universityFiscalYear) && java.util.Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && java.util.Objects.equals(accountNumber, that.accountNumber) && java.util.Objects.equals(subAccountNumber, that.subAccountNumber) && java.util.Objects.equals(objectCode, that.objectCode) && java.util.Objects.equals(subObjectCode, that.subObjectCode) && java.util.Objects.equals(balanceTypeCode, that.balanceTypeCode) && java.util.Objects.equals(documentTypeCode, that.documentTypeCode) && java.util.Objects.equals(originCode, that.originCode) && java.util.Objects.equals(documentNumber, that.documentNumber);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber, objectCode, subObjectCode, balanceTypeCode, documentTypeCode, originCode, documentNumber);
        }
    }
}
