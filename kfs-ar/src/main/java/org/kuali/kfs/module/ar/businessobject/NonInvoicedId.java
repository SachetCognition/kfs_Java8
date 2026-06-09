package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class NonInvoicedId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer financialDocumentLineNumber;

    public NonInvoicedId() {}

    public NonInvoicedId(String documentNumber, Integer financialDocumentLineNumber) {
        this.documentNumber = documentNumber;
        this.financialDocumentLineNumber = financialDocumentLineNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getFinancialDocumentLineNumber() { return financialDocumentLineNumber; }
    public void setFinancialDocumentLineNumber(Integer financialDocumentLineNumber) { this.financialDocumentLineNumber = financialDocumentLineNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonInvoicedId that = (NonInvoicedId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(financialDocumentLineNumber, that.financialDocumentLineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, financialDocumentLineNumber);
    }
}
