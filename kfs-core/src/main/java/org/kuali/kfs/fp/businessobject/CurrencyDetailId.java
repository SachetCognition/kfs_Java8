package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CurrencyDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String financialDocumentTypeCode;
    private String cashieringStatus;

    public CurrencyDetailId() {}

    public CurrencyDetailId(String documentNumber, String financialDocumentTypeCode, String cashieringStatus) {
        this.documentNumber = documentNumber;
        this.financialDocumentTypeCode = financialDocumentTypeCode;
        this.cashieringStatus = cashieringStatus;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getFinancialDocumentTypeCode() { return financialDocumentTypeCode; }
    public void setFinancialDocumentTypeCode(String financialDocumentTypeCode) { this.financialDocumentTypeCode = financialDocumentTypeCode; }

    public String getCashieringStatus() { return cashieringStatus; }
    public void setCashieringStatus(String cashieringStatus) { this.cashieringStatus = cashieringStatus; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyDetailId that = (CurrencyDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(financialDocumentTypeCode, that.financialDocumentTypeCode) && Objects.equals(cashieringStatus, that.cashieringStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, financialDocumentTypeCode, cashieringStatus);
    }
}
