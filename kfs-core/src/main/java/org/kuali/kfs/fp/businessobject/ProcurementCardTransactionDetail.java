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

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.AccountingLineBase;
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
import javax.persistence.OneToMany;

/**
 * This class is used to represent a procurement card transaction detail business object.
 */
@Entity
@Table(name = "FP_PRCRMNT_TRN_DTL_T")
public class ProcurementCardTransactionDetail extends PersistableBusinessObjectBase {

    @Id
    @Column(name = "FDOC_NBR")
    private String documentNumber;
    @Id
    @Column(name = "FDOC_TRN_LN_NBR")
    private Integer financialDocumentTransactionLineNumber;
    @Column(name = "TRANSACTION_DT")
    private Date transactionDate;
    @Column(name = "TRN_REF_NBR")
    private String transactionReferenceNumber;
    @Column(name = "TRN_POST_DT")
    private Date transactionPostingDate;
    @Column(name = "TRN_ORIG_CRNCY_CD")
    private String transactionOriginalCurrencyCode;
    @Column(name = "TRN_BILL_CRNCY_CD")
    private String transactionBillingCurrencyCode;
    @Column(name = "TRN_ORIG_CRNCY_AMT")
    private KualiDecimal transactionOriginalCurrencyAmount;
    @Column(name = "TRN_CRNCY_EXCH_RT")
    private BigDecimal transactionCurrencyExchangeRate;
    @Column(name = "TRN_STLMNT_AMT")
    private KualiDecimal transactionSettlementAmount;
    @Column(name = "TRN_SALES_TAX_AMT")
    private KualiDecimal transactionSalesTaxAmount;
    @Column(name = "TRN_TAX_EXMPT_IND")
    private boolean transactionTaxExemptIndicator;
    @Column(name = "TRN_PURCH_ID_IND")
    private boolean transactionPurchaseIdentifierIndicator;
    @Column(name = "TRN_PURCH_ID_DESC")
    private String transactionPurchaseIdentifierDescription;
    @Column(name = "TRN_UNIT_CNTCT_NM")
    private String transactionUnitContactName;
    @Column(name = "TRN_TRVL_AUTH_CD")
    private String transactionTravelAuthorizationCode;
    @Column(name = "TRN_PT_OF_SALE_CD")
    private String transactionPointOfSaleCode;
    @Column(name = "TRN_CYCLE_STRT_DT")
    private Date transactionCycleStartDate;
    @Column(name = "TRN_CYCLE_END_DT")
    private Date transactionCycleEndDate;
    @Column(name = "TRN_TOT_AMT")
    private KualiDecimal transactionTotalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProcurementCardVendor procurementCardVendor;

    @OneToMany(fetch = FetchType.LAZY)
    private List sourceAccountingLines;
    @OneToMany(fetch = FetchType.LAZY)
    private List targetAccountingLines;

    @Transient
    protected FormFile targetFile;
    /**
     * Default constructor.
     */
    public ProcurementCardTransactionDetail() {
        sourceAccountingLines = new ArrayList<ProcurementCardSourceAccountingLine>();
        targetAccountingLines = new ArrayList<ProcurementCardTargetAccountingLine>();
    }

    /**
     * @see org.kuali.rice.krad.document.TransactionalDocument#getTargetTotal()
     */
    public KualiDecimal getTargetTotal() {
        KualiDecimal total = KualiDecimal.ZERO;
        AccountingLineBase al = null;
        Iterator iter = getTargetAccountingLines().iterator();
        while (iter.hasNext()) {
            al = (AccountingLineBase) iter.next();

            KualiDecimal amount = al.getAmount();
            if (amount != null) {
                total = total.add(amount);
            }
        }
        return total;
    }

    /**
     * Gets the documentNumber attribute.
     *
     * @return Returns the documentNumber
     */
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
     * Gets the financialDocumentTransactionLineNumber attribute.
     *
     * @return Returns the financialDocumentTransactionLineNumber
     */
    public Integer getFinancialDocumentTransactionLineNumber() {
        return financialDocumentTransactionLineNumber;
    }

    /**
     * Sets the financialDocumentTransactionLineNumber attribute.
     *
     * @param financialDocumentTransactionLineNumber The financialDocumentTransactionLineNumber to set.
     */
    public void setFinancialDocumentTransactionLineNumber(Integer financialDocumentTransactionLineNumber) {
        this.financialDocumentTransactionLineNumber = financialDocumentTransactionLineNumber;
    }


    /**
     * Gets the transactionDate attribute.
     *
     * @return Returns the transactionDate
     */
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
     * Gets the transactionReferenceNumber attribute.
     *
     * @return Returns the transactionReferenceNumber
     */
    public String getTransactionReferenceNumber() {
        return transactionReferenceNumber;
    }

    /**
     * Sets the transactionReferenceNumber attribute.
     *
     * @param transactionReferenceNumber The transactionReferenceNumber to set.
     */
    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }


    /**
     * Gets the transactionPostingDate attribute.
     *
     * @return Returns the transactionPostingDate
     */
    public Date getTransactionPostingDate() {
        return transactionPostingDate;
    }

    /**
     * Sets the transactionPostingDate attribute.
     *
     * @param transactionPostingDate The transactionPostingDate to set.
     */
    public void setTransactionPostingDate(Date transactionPostingDate) {
        this.transactionPostingDate = transactionPostingDate;
    }


    /**
     * Gets the transactionOriginalCurrencyCode attribute.
     *
     * @return Returns the transactionOriginalCurrencyCode
     */
    public String getTransactionOriginalCurrencyCode() {
        return transactionOriginalCurrencyCode;
    }

    /**
     * Sets the transactionOriginalCurrencyCode attribute.
     *
     * @param transactionOriginalCurrencyCode The transactionOriginalCurrencyCode to set.
     */
    public void setTransactionOriginalCurrencyCode(String transactionOriginalCurrencyCode) {
        this.transactionOriginalCurrencyCode = transactionOriginalCurrencyCode;
    }


    /**
     * Gets the transactionBillingCurrencyCode attribute.
     *
     * @return Returns the transactionBillingCurrencyCode
     */
    public String getTransactionBillingCurrencyCode() {
        return transactionBillingCurrencyCode;
    }

    /**
     * Sets the transactionBillingCurrencyCode attribute.
     *
     * @param transactionBillingCurrencyCode The transactionBillingCurrencyCode to set.
     */
    public void setTransactionBillingCurrencyCode(String transactionBillingCurrencyCode) {
        this.transactionBillingCurrencyCode = transactionBillingCurrencyCode;
    }


    /**
     * Gets the transactionOriginalCurrencyAmount attribute.
     *
     * @return Returns the transactionOriginalCurrencyAmount
     */
    public KualiDecimal getTransactionOriginalCurrencyAmount() {
        return transactionOriginalCurrencyAmount;
    }

    /**
     * Sets the transactionOriginalCurrencyAmount attribute.
     *
     * @param transactionOriginalCurrencyAmount The transactionOriginalCurrencyAmount to set.
     */
    public void setTransactionOriginalCurrencyAmount(KualiDecimal transactionOriginalCurrencyAmount) {
        this.transactionOriginalCurrencyAmount = transactionOriginalCurrencyAmount;
    }


    /**
     * Gets the transactionCurrencyExchangeRate attribute.
     *
     * @return Returns the transactionCurrencyExchangeRate
     */
    public BigDecimal getTransactionCurrencyExchangeRate() {
        return transactionCurrencyExchangeRate;
    }

    /**
     * Sets the transactionCurrencyExchangeRate attribute.
     *
     * @param transactionCurrencyExchangeRate The transactionCurrencyExchangeRate to set.
     */
    public void setTransactionCurrencyExchangeRate(BigDecimal transactionCurrencyExchangeRate) {
        this.transactionCurrencyExchangeRate = transactionCurrencyExchangeRate;
    }


    /**
     * Gets the transactionSettlementAmount attribute.
     *
     * @return Returns the transactionSettlementAmount
     */
    public KualiDecimal getTransactionSettlementAmount() {
        return transactionSettlementAmount;
    }

    /**
     * Sets the transactionSettlementAmount attribute.
     *
     * @param transactionSettlementAmount The transactionSettlementAmount to set.
     */
    public void setTransactionSettlementAmount(KualiDecimal transactionSettlementAmount) {
        this.transactionSettlementAmount = transactionSettlementAmount;
    }


    /**
     * Gets the transactionSalesTaxAmount attribute.
     *
     * @return Returns the transactionSalesTaxAmount
     */
    public KualiDecimal getTransactionSalesTaxAmount() {
        return transactionSalesTaxAmount;
    }

    /**
     * Sets the transactionSalesTaxAmount attribute.
     *
     * @param transactionSalesTaxAmount The transactionSalesTaxAmount to set.
     */
    public void setTransactionSalesTaxAmount(KualiDecimal transactionSalesTaxAmount) {
        this.transactionSalesTaxAmount = transactionSalesTaxAmount;
    }


    /**
     * Gets the transactionTaxExemptIndicator attribute.
     *
     * @return Returns the transactionTaxExemptIndicator
     */
    public boolean getTransactionTaxExemptIndicator() {
        return transactionTaxExemptIndicator;
    }

    /**
     * Sets the transactionTaxExemptIndicator attribute.
     *
     * @param transactionTaxExemptIndicator The transactionTaxExemptIndicator to set.
     */
    public void setTransactionTaxExemptIndicator(boolean transactionTaxExemptIndicator) {
        this.transactionTaxExemptIndicator = transactionTaxExemptIndicator;
    }


    /**
     * Gets the transactionPurchaseIdentifierIndicator attribute.
     *
     * @return Returns the transactionPurchaseIdentifierIndicator
     */
    public boolean getTransactionPurchaseIdentifierIndicator() {
        return transactionPurchaseIdentifierIndicator;
    }

    /**
     * Sets the transactionPurchaseIdentifierIndicator attribute.
     *
     * @param transactionPurchaseIdentifierIndicator The transactionPurchaseIdentifierIndicator to set.
     */
    public void setTransactionPurchaseIdentifierIndicator(boolean transactionPurchaseIdentifierIndicator) {
        this.transactionPurchaseIdentifierIndicator = transactionPurchaseIdentifierIndicator;
    }


    /**
     * Gets the transactionPurchaseIdentifierDescription attribute.
     *
     * @return Returns the transactionPurchaseIdentifierDescription
     */
    public String getTransactionPurchaseIdentifierDescription() {
        return transactionPurchaseIdentifierDescription;
    }

    /**
     * Sets the transactionPurchaseIdentifierDescription attribute.
     *
     * @param transactionPurchaseIdentifierDescription The transactionPurchaseIdentifierDescription to set.
     */
    public void setTransactionPurchaseIdentifierDescription(String transactionPurchaseIdentifierDescription) {
        this.transactionPurchaseIdentifierDescription = transactionPurchaseIdentifierDescription;
    }


    /**
     * Gets the transactionUnitContactName attribute.
     *
     * @return Returns the transactionUnitContactName
     */
    public String getTransactionUnitContactName() {
        return transactionUnitContactName;
    }

    /**
     * Sets the transactionUnitContactName attribute.
     *
     * @param transactionUnitContactName The transactionUnitContactName to set.
     */
    public void setTransactionUnitContactName(String transactionUnitContactName) {
        this.transactionUnitContactName = transactionUnitContactName;
    }


    /**
     * Gets the transactionTravelAuthorizationCode attribute.
     *
     * @return Returns the transactionTravelAuthorizationCode
     */
    public String getTransactionTravelAuthorizationCode() {
        return transactionTravelAuthorizationCode;
    }

    /**
     * Sets the transactionTravelAuthorizationCode attribute.
     *
     * @param transactionTravelAuthorizationCode The transactionTravelAuthorizationCode to set.
     */
    public void setTransactionTravelAuthorizationCode(String transactionTravelAuthorizationCode) {
        this.transactionTravelAuthorizationCode = transactionTravelAuthorizationCode;
    }


    /**
     * Gets the transactionPointOfSaleCode attribute.
     *
     * @return Returns the transactionPointOfSaleCode
     */
    public String getTransactionPointOfSaleCode() {
        return transactionPointOfSaleCode;
    }

    /**
     * Sets the transactionPointOfSaleCode attribute.
     *
     * @param transactionPointOfSaleCode The transactionPointOfSaleCode to set.
     */
    public void setTransactionPointOfSaleCode(String transactionPointOfSaleCode) {
        this.transactionPointOfSaleCode = transactionPointOfSaleCode;
    }


    /**
     * Gets the sourceAccountingLines attribute.
     *
     * @return Returns the sourceAccountingLines.
     */
    public List getSourceAccountingLines() {
        return sourceAccountingLines;
    }


    /**
     * Sets the sourceAccountingLines attribute value.
     *
     * @param sourceAccountingLines The sourceAccountingLines to set.
     */
    public void setSourceAccountingLines(List sourceAccountingLines) {
        this.sourceAccountingLines = sourceAccountingLines;
    }


    /**
     * Gets the targetAccountingLines attribute.
     *
     * @return Returns the targetAccountingLines.
     */
    public List getTargetAccountingLines() {
        return targetAccountingLines;
    }


    /**
     * Sets the targetAccountingLines attribute value.
     *
     * @param targetAccountingLines The targetAccountingLines to set.
     */
    public void setTargetAccountingLines(List targetAccountingLines) {
        this.targetAccountingLines = targetAccountingLines;
    }


    /**
     * Gets the transactionCycleEndDate attribute.
     *
     * @return Returns the transactionCycleEndDate.
     */
    public Date getTransactionCycleEndDate() {
        return transactionCycleEndDate;
    }

    /**
     * Sets the transactionCycleEndDate attribute value.
     *
     * @param transactionCycleEndDate The transactionCycleEndDate to set.
     */
    public void setTransactionCycleEndDate(Date transactionCycleEndDate) {
        this.transactionCycleEndDate = transactionCycleEndDate;
    }

    /**
     * Gets the transactionCycleStartDate attribute.
     *
     * @return Returns the transactionCycleStartDate.
     */
    public Date getTransactionCycleStartDate() {
        return transactionCycleStartDate;
    }

    /**
     * Sets the transactionCycleStartDate attribute value.
     *
     * @param transactionCycleStartDate The transactionCycleStartDate to set.
     */
    public void setTransactionCycleStartDate(Date transactionCycleStartDate) {
        this.transactionCycleStartDate = transactionCycleStartDate;
    }

    /**
     * Gets the procurementCardVendor attribute.
     *
     * @return Returns the procurementCardVendor.
     */
    public ProcurementCardVendor getProcurementCardVendor() {
        return procurementCardVendor;
    }

    /**
     * Sets the procurementCardVendor attribute value.
     *
     * @param procurementCardVendor The procurementCardVendor to set.
     */
    public void setProcurementCardVendor(ProcurementCardVendor procurementCardVendor) {
        this.procurementCardVendor = procurementCardVendor;
    }

    /**
     * Gets the transactionTotalAmount attribute.
     *
     * @return Returns the transactionTotalAmount.
     */
    public KualiDecimal getTransactionTotalAmount() {
        return transactionTotalAmount;
    }

    /**
     * Sets the transactionTotalAmount attribute value.
     *
     * @param transactionTotalAmount The transactionTotalAmount to set.
     */
    public void setTransactionTotalAmount(KualiDecimal transactionTotalAmount) {
        this.transactionTotalAmount = transactionTotalAmount;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put(KFSPropertyConstants.DOCUMENT_NUMBER, this.documentNumber);
        if (this.financialDocumentTransactionLineNumber != null) {
            m.put("financialDocumentTransactionLineNumber", this.financialDocumentTransactionLineNumber.toString());
        }
        return m;
    }


    /**
     *
     * @return
     */
    public FormFile getTargetFile() {
        return targetFile;
    }

    /**
     *
     * @param targetFile
     */
    public void setTargetFile(FormFile targetFile) {
        this.targetFile = targetFile;
    }

}
