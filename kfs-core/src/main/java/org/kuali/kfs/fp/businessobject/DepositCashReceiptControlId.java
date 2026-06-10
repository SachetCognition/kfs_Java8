package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class DepositCashReceiptControlId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String financialDocumentDepositNumber;
    private Integer financialDocumentDepositLineNumber;
    private String financialDocumentCashReceiptNumber;

    public DepositCashReceiptControlId() {}

    public DepositCashReceiptControlId(String financialDocumentDepositNumber, Integer financialDocumentDepositLineNumber, String financialDocumentCashReceiptNumber) {
        this.financialDocumentDepositNumber = financialDocumentDepositNumber;
        this.financialDocumentDepositLineNumber = financialDocumentDepositLineNumber;
        this.financialDocumentCashReceiptNumber = financialDocumentCashReceiptNumber;
    }

    public String getFinancialDocumentDepositNumber() { return financialDocumentDepositNumber; }
    public void setFinancialDocumentDepositNumber(String financialDocumentDepositNumber) { this.financialDocumentDepositNumber = financialDocumentDepositNumber; }

    public Integer getFinancialDocumentDepositLineNumber() { return financialDocumentDepositLineNumber; }
    public void setFinancialDocumentDepositLineNumber(Integer financialDocumentDepositLineNumber) { this.financialDocumentDepositLineNumber = financialDocumentDepositLineNumber; }

    public String getFinancialDocumentCashReceiptNumber() { return financialDocumentCashReceiptNumber; }
    public void setFinancialDocumentCashReceiptNumber(String financialDocumentCashReceiptNumber) { this.financialDocumentCashReceiptNumber = financialDocumentCashReceiptNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositCashReceiptControlId that = (DepositCashReceiptControlId) o;
        return Objects.equals(financialDocumentDepositNumber, that.financialDocumentDepositNumber) && Objects.equals(financialDocumentDepositLineNumber, that.financialDocumentDepositLineNumber) && Objects.equals(financialDocumentCashReceiptNumber, that.financialDocumentCashReceiptNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(financialDocumentDepositNumber, financialDocumentDepositLineNumber, financialDocumentCashReceiptNumber);
    }
}
