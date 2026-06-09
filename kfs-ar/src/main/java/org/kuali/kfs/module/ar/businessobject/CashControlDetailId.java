package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CashControlDetailId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String referenceFinancialDocumentNumber;

    public CashControlDetailId() {}

    public CashControlDetailId(String documentNumber, String referenceFinancialDocumentNumber) {
        this.documentNumber = documentNumber;
        this.referenceFinancialDocumentNumber = referenceFinancialDocumentNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getReferenceFinancialDocumentNumber() { return referenceFinancialDocumentNumber; }
    public void setReferenceFinancialDocumentNumber(String referenceFinancialDocumentNumber) { this.referenceFinancialDocumentNumber = referenceFinancialDocumentNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashControlDetailId that = (CashControlDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(referenceFinancialDocumentNumber, that.referenceFinancialDocumentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, referenceFinancialDocumentNumber);
    }
}
