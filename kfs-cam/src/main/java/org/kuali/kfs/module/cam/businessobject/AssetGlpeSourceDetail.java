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
package org.kuali.kfs.module.cam.businessobject;

import java.util.LinkedHashMap;

import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "CM_AST_PMT_DTL_T")
public class AssetGlpeSourceDetail extends PersistableBusinessObjectBase implements GeneralLedgerPendingEntrySourceDetail {
    @Column(name = "ACCOUNT_NBR")
    private String accountNumber;
    @Column(name = "ACCT_CHARGE_AMT")
    private KualiDecimal amount;
    @Transient
    private String balanceTypeCode;
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;
    @Id
    @Column(name = "FDOC_NBR")
    private String documentNumber;
    @Transient
    private String financialDocumentLineDescription;
    @Column(name = "FIN_OBJECT_CD")
    private String financialObjectCode;
    @Column(name = "FIN_SUB_OBJ_CD")
    private String financialSubObjectCode;
    @Transient
    private String organizationReferenceId;
    @Column(name = "FDOC_POST_YR")
    private Integer postingYear;
    @Column(name = "PROJECT_CD")
    private String projectCode;
    @Transient
    private String referenceNumber;
    @Transient
    private String referenceOriginCode;
    @Transient
    private String referenceTypeCode;
    @Column(name = "SUB_ACCT_NBR")
    private String subAccountNumber;
    @Transient
    private boolean source;
    @Transient
    private boolean expense;
    @Transient
    private boolean capitalization;
    @Transient
    private boolean accumulatedDepreciation;
    @Transient
    private boolean capitalizationOffset;
    @Transient
    private boolean payment;
    @Transient
    private boolean paymentOffset;
    @Transient
    private Account account;
    @Transient
    private ObjectCode objectCode;
    @Id
    @Column(name = "FDOC_LINE_NBR")
    private int sequenceNumber;
    @Column(name = "FDOC_POST_PRD_CD")
    private String postingPeriodCode;


    public Account getAccount() {
        return account;
    }


    public void setAccount(Account account) {
        this.account = account;
    }


    public String getAccountNumber() {
        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public KualiDecimal getAmount() {
        return amount;
    }


    public void setAmount(KualiDecimal amount) {
        this.amount = amount;
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


    public String getDocumentNumber() {
        return documentNumber;
    }


    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }


    public String getFinancialDocumentLineDescription() {
        return financialDocumentLineDescription;
    }


    public void setFinancialDocumentLineDescription(String financialDocumentLineDescription) {
        this.financialDocumentLineDescription = financialDocumentLineDescription;
    }


    public String getFinancialObjectCode() {
        return financialObjectCode;
    }


    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }


    public String getFinancialSubObjectCode() {
        return financialSubObjectCode;
    }


    public void setFinancialSubObjectCode(String financialSubObjectCode) {
        this.financialSubObjectCode = financialSubObjectCode;
    }


    public ObjectCode getObjectCode() {
        return objectCode;
    }


    public void setObjectCode(ObjectCode objectCode) {
        this.objectCode = objectCode;
    }


    public String getOrganizationReferenceId() {
        return organizationReferenceId;
    }


    public void setOrganizationReferenceId(String organizationReferenceId) {
        this.organizationReferenceId = organizationReferenceId;
    }


    public Integer getPostingYear() {
        return postingYear;
    }


    public void setPostingYear(Integer postingYear) {
        this.postingYear = postingYear;
    }


    public String getProjectCode() {
        return projectCode;
    }


    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }


    public String getReferenceNumber() {
        return referenceNumber;
    }


    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }


    public String getReferenceOriginCode() {
        return referenceOriginCode;
    }


    public void setReferenceOriginCode(String referenceOriginCode) {
        this.referenceOriginCode = referenceOriginCode;
    }


    public String getReferenceTypeCode() {
        return referenceTypeCode;
    }


    public void setReferenceTypeCode(String referenceTypeCode) {
        this.referenceTypeCode = referenceTypeCode;
    }


    public String getSubAccountNumber() {
        return subAccountNumber;
    }


    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }


    public boolean isSource() {
        return source;
    }


    public void setSource(boolean source) {
        this.source = source;
    }


    public boolean isExpense() {
        return expense;
    }


    public void setExpense(boolean expense) {
        this.expense = expense;
    }


    public boolean isCapitalization() {
        return capitalization;
    }


    public void setCapitalization(boolean capitalization) {
        this.capitalization = capitalization;
    }


    public boolean isAccumulatedDepreciation() {
        return accumulatedDepreciation;
    }


    public void setAccumulatedDepreciation(boolean accumulatedDepreciation) {
        this.accumulatedDepreciation = accumulatedDepreciation;
    }


    
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("accountNumber", this.accountNumber);
        map.put("amount", this.chartOfAccountsCode);
        return map;
    }


    public void setCapitalizationOffset(boolean b) {
        this.capitalizationOffset = b;

    }


    public boolean isCapitalizationOffset() {
        return capitalizationOffset;
    }


    /**
     * Gets the payment attribute.
     * 
     * @return Returns the payment.
     */
    public boolean isPayment() {
        return payment;
    }


    /**
     * Sets the payment attribute value.
     * 
     * @param payment The payment to set.
     */
    public void setPayment(boolean payment) {
        this.payment = payment;
    }


    /**
     * Gets the paymentOffset attribute.
     * 
     * @return Returns the paymentOffset.
     */
    public boolean isPaymentOffset() {
        return paymentOffset;
    }


    /**
     * Sets the paymentOffset attribute value.
     * 
     * @param paymentOffset The paymentOffset to set.
     */
    public void setPaymentOffset(boolean paymentOffset) {
        this.paymentOffset = paymentOffset;
    }


    /**
     * We have to return from this method directly since this is not a real persistent class and if we call super, it will run into
     * "Class not found in OJB repository" exception.
     * 
     * @see org.kuali.rice.krad.bo.PersistableBusinessObjectBase#refresh()
     */
    @Override
    public void refresh() {
        return;
    }


    /**
     * We have to return from this method directly since this is not a real persistent class and if we call super, it will run into
     * "Class not found in OJB repository" exception.
     * 
     * @see org.kuali.rice.krad.bo.PersistableBusinessObjectBase#refreshNonUpdateableReferences()
     */
    @Override
    public void refreshNonUpdateableReferences() {
        return;
    }


    /**
     * We have to return from this method directly since this is not a real persistent class and if we call super, it will run into
     * "Class not found in OJB repository" exception.
     * 
     * @see org.kuali.rice.krad.bo.PersistableBusinessObjectBase#refreshReferenceObject(java.lang.String)
     */
    @Override
    public void refreshReferenceObject(String referenceObjectName) {
        return;
    }


    /**
     * Gets the sequenceNumber attribute.
     * 
     * @return Returns the sequenceNumber.
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }


    /**
     * Sets the sequenceNumber attribute value.
     * 
     * @param sequenceNumber The sequenceNumber to set.
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    /**
     * Gets the postingPeriodCode attribute.
     * 
     * @return Returns the postingPeriodCode.
     */
    public String getPostingPeriodCode() {
        return postingPeriodCode;
    }


    /**
     * Sets the postingPeriodCode attribute value.
     * 
     * @param postingPeriodCode The postingPeriodCode to set.
     */
    public void setPostingPeriodCode(String postingPeriodCode) {
        this.postingPeriodCode = postingPeriodCode;
    }


}
