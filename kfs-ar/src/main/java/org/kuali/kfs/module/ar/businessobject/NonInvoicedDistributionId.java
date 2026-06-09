package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class NonInvoicedDistributionId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer financialDocumentLineNumber;
    private String referenceFinancialDocumentNumber;

    public NonInvoicedDistributionId() {}

    public NonInvoicedDistributionId(String documentNumber, Integer financialDocumentLineNumber, String referenceFinancialDocumentNumber) {
        this.documentNumber = documentNumber;
        this.financialDocumentLineNumber = financialDocumentLineNumber;
        this.referenceFinancialDocumentNumber = referenceFinancialDocumentNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getFinancialDocumentLineNumber() { return financialDocumentLineNumber; }
    public void setFinancialDocumentLineNumber(Integer financialDocumentLineNumber) { this.financialDocumentLineNumber = financialDocumentLineNumber; }

    public String getReferenceFinancialDocumentNumber() { return referenceFinancialDocumentNumber; }
    public void setReferenceFinancialDocumentNumber(String referenceFinancialDocumentNumber) { this.referenceFinancialDocumentNumber = referenceFinancialDocumentNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonInvoicedDistributionId that = (NonInvoicedDistributionId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(financialDocumentLineNumber, that.financialDocumentLineNumber) && Objects.equals(referenceFinancialDocumentNumber, that.referenceFinancialDocumentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, financialDocumentLineNumber, referenceFinancialDocumentNumber);
    }
}
