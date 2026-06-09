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

import org.kuali.kfs.fp.businessobject.options.TaxIncomeClassValuesFinder;
import org.kuali.kfs.sys.KFSPropertyConstants;
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

/**
 * This class is used to represent a disbursement voucher non-resident alien tax.
 */
@Entity
@Table(name = "FP_DV_NRA_TAX_T")
public class DisbursementVoucherNonResidentAlienTax extends PersistableBusinessObjectBase {

    @Id
    @Column(name = "FDOC_NBR")
    private String documentNumber;
    @Column(name = "FED_INC_TAX_PCT")
    private KualiDecimal federalIncomeTaxPercent;
    @Column(name = "ST_INC_TAX_PCT")
    private KualiDecimal stateIncomeTaxPercent;
    @Column(name = "INC_CLS_CD")
    private String incomeClassCode;
    @Column(name = "POSTAL_CNTRY_CD")
    private String postalCountryCode;
    @Column(name = "INC_TAX_TRTY_EXMPT_IND")
    private boolean incomeTaxTreatyExemptCode;
    @Column(name = "FRGN_SRC_INC_IND")
    private boolean foreignSourceIncomeCode;
    @Column(name = "INC_TAX_GRS_UP_IND")
    private boolean incomeTaxGrossUpCode;
    @Column(name = "FS_REF_ORIGIN_CD")
    private String referenceFinancialSystemOriginationCode;
    @Column(name = "FDOC_REF_NBR")
    private String referenceFinancialDocumentNumber;
    @Column(name = "FDOC_ACCTG_LN_TXT")
    private String financialDocumentAccountingLineText;
    @Column(name = "NQI_CUST_TAX_ID")
    private String taxNQIId;
    @Column(name = "INC_TAX_EXMPT_CD_OTHR_IND")
    private boolean taxOtherExemptIndicator;
    @Column(name = "USAID_DIEM_IND")
    private boolean taxUSAIDPerDiemIndicator;
    @Column(name = "SPCL_W4_INC_AMT")
    protected KualiDecimal taxSpecialW4Amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INC_CLS_CD", insertable = false, updatable = false)
    private TaxIncomeClassCode incomeClass;

    /**
     * Default no-arg constructor.
     */
    public DisbursementVoucherNonResidentAlienTax() {

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
     * Gets the federalIncomeTaxPercent attribute.
     *
     * @return Returns the federalIncomeTaxPercent
     */
    public KualiDecimal getFederalIncomeTaxPercent() {
        return federalIncomeTaxPercent;
    }


    /**
     * Sets the federalIncomeTaxPercent attribute.
     *
     * @param federalIncomeTaxPercent The federalIncomeTaxPercent to set.
     */
    public void setFederalIncomeTaxPercent(KualiDecimal federalIncomeTaxPercent) {
        this.federalIncomeTaxPercent = federalIncomeTaxPercent;
    }

    /**
     * Gets the stateIncomeTaxPercent attribute.
     *
     * @return Returns the stateIncomeTaxPercent
     */
    public KualiDecimal getStateIncomeTaxPercent() {
        return stateIncomeTaxPercent;
    }


    /**
     * Sets the stateIncomeTaxPercent attribute.
     *
     * @param stateIncomeTaxPercent The stateIncomeTaxPercent to set.
     */
    public void setStateIncomeTaxPercent(KualiDecimal stateIncomeTaxPercent) {
        this.stateIncomeTaxPercent = stateIncomeTaxPercent;
    }

    /**
     * Gets the incomeClassCode attribute.
     *
     * @return Returns the incomeClassCode
     */
    public String getIncomeClassCode() {
        return incomeClassCode;
    }


    /**
     * Sets the incomeClassCode attribute.
     *
     * @param incomeClassCode The incomeClassCode to set.
     */
    public void setIncomeClassCode(String incomeClassCode) {
        this.incomeClassCode = incomeClassCode;
    }

    /**
     * Gets the postalCountryCode attribute.
     *
     * @return Returns the postalCountryCode
     */
    public String getPostalCountryCode() {
        return postalCountryCode;
    }


    /**
     * Sets the postalCountryCode attribute.
     *
     * @param postalCountryCode The postalCountryCode to set.
     */
    public void setPostalCountryCode(String postalCountryCode) {
        this.postalCountryCode = postalCountryCode;
    }

    /**
     * Gets the incomeTaxTreatyExemptCode attribute.
     *
     * @return Returns the incomeTaxTreatyExemptCode
     */
    public boolean isIncomeTaxTreatyExemptCode() {
        return incomeTaxTreatyExemptCode;
    }


    /**
     * Sets the incomeTaxTreatyExemptCode attribute.
     *
     * @param incomeTaxTreatyExemptCode The incomeTaxTreatyExemptCode to set.
     */
    public void setIncomeTaxTreatyExemptCode(boolean incomeTaxTreatyExemptCode) {
        this.incomeTaxTreatyExemptCode = incomeTaxTreatyExemptCode;
    }

    /**
     * Gets the foreignSourceIncomeCode attribute.
     *
     * @return Returns the foreignSourceIncomeCode
     */
    public boolean isForeignSourceIncomeCode() {
        return foreignSourceIncomeCode;
    }


    /**
     * Sets the foreignSourceIncomeCode attribute.
     *
     * @param foreignSourceIncomeCode The foreignSourceIncomeCode to set.
     */
    public void setForeignSourceIncomeCode(boolean foreignSourceIncomeCode) {
        this.foreignSourceIncomeCode = foreignSourceIncomeCode;
    }

    /**
     * Gets the incomeTaxGrossUpCode attribute.
     *
     * @return Returns the incomeTaxGrossUpCode
     */
    public boolean isIncomeTaxGrossUpCode() {
        return incomeTaxGrossUpCode;
    }


    /**
     * Sets the incomeTaxGrossUpCode attribute.
     *
     * @param incomeTaxGrossUpCode The incomeTaxGrossUpCode to set.
     */
    public void setIncomeTaxGrossUpCode(boolean incomeTaxGrossUpCode) {
        this.incomeTaxGrossUpCode = incomeTaxGrossUpCode;
    }

    /**
     * Gets the referenceFinancialSystemOriginationCode attribute.
     *
     * @return Returns the referenceFinancialSystemOriginationCode
     */
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
     * @return Returns the financialDocumentAccountingLineText.
     */
    public String getFinancialDocumentAccountingLineText() {
        return financialDocumentAccountingLineText;
    }

    /**
     * @param financialDocumentAccountingLineText The financialDocumentAccountingLineText to set.
     */
    public void setFinancialDocumentAccountingLineText(String financialDocumentAccountingLineText) {
        this.financialDocumentAccountingLineText = financialDocumentAccountingLineText;
    }

    /**
     * Gets the taxNQIId attribute.
     *
     * @return Returns the taxNQIId.
     */
    public String getTaxNQIId() {
        return taxNQIId;
    }

    /**
     * Gets the taxOtherExemptIndicator attribute.
     *
     * @return Returns the taxOtherExemptIndicator.
     */
    public boolean isTaxOtherExemptIndicator() {
        return taxOtherExemptIndicator;
    }

    /**
     * Gets the taxUSAIDPerDiemIndicator attribute.
     *
     * @return Returns the taxUSAIDPerDiemIndicator.
     */
    public boolean isTaxUSAIDPerDiemIndicator() {
        return taxUSAIDPerDiemIndicator;
    }

    /**
     * Gets the taxSpecialW4Amount attribute.
     *
     * @return Returns the taxSpecialW4Amount.
     */
    public KualiDecimal getTaxSpecialW4Amount() {
        return taxSpecialW4Amount;
    }

    /**
     * Sets the taxNQIId attribute value.
     *
     * @param taxNQIId The taxNQIId to set.
     */
    public void setTaxNQIId(String taxNQIId) {
        this.taxNQIId = taxNQIId;
    }

    /**
     * Sets the taxOtherExemptIndicator attribute value.
     *
     * @param taxOtherExemptIndicator The taxOtherExemptIndicator to set.
     */
    public void setTaxOtherExemptIndicator(boolean taxOtherExemptIndicator) {
        this.taxOtherExemptIndicator = taxOtherExemptIndicator;
    }

    /**
     * Sets the taxUSAIDPerDiemIndicator attribute value.
     *
     * @param taxUSAIDPerDiemIndicator The taxUSAIDPerDiemIndicator to set.
     */
    public void setTaxUSAIDPerDiemIndicator(boolean taxUSAIDPerDiemIndicator) {
        this.taxUSAIDPerDiemIndicator = taxUSAIDPerDiemIndicator;
    }

    /**
     * Sets the taxSpecialW4Amount attribute value.
     *
     * @param taxSpecialW4Amount The taxSpecialW4Amount to set.
     */
    public void setTaxSpecialW4Amount(KualiDecimal taxSpecialW4Amount) {
        this.taxSpecialW4Amount = taxSpecialW4Amount;
    }

    /**
     * Gets the incomeClass attribute.
     *
     * @return Returns the incomeClass
     */
    public TaxIncomeClassCode getIncomeClass() {
        return incomeClass;
    }


    /**
     * Sets the incomeClass attribute.
     *
     * @param incomeClass The incomeClass to set.
     * @deprecated
     */
    @Deprecated
    public void setIncomeClass(TaxIncomeClassCode incomeClass) {
        this.incomeClass = incomeClass;
    }

    /**
     * Return select read-only label for income class
     *
     * @return
     */
    public String getIncomeClassName() {
        return new TaxIncomeClassValuesFinder().getKeyLabel(incomeClassCode);
    }

    /**
     * Sets the incomeClassName attribute.
     *
     * @param name The incomeClass name to set.
     */
    public void setincomeClassName(String name) {
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    @SuppressWarnings("rawtypes")
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put(KFSPropertyConstants.DOCUMENT_NUMBER, this.documentNumber);
        return m;
    }

}
