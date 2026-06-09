package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class ProcurementCardTransactionDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer financialDocumentTransactionLineNumber;

    public ProcurementCardTransactionDetailId() {}

    public ProcurementCardTransactionDetailId(String documentNumber, Integer financialDocumentTransactionLineNumber) {
        this.documentNumber = documentNumber;
        this.financialDocumentTransactionLineNumber = financialDocumentTransactionLineNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getFinancialDocumentTransactionLineNumber() { return financialDocumentTransactionLineNumber; }
    public void setFinancialDocumentTransactionLineNumber(Integer financialDocumentTransactionLineNumber) { this.financialDocumentTransactionLineNumber = financialDocumentTransactionLineNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcurementCardTransactionDetailId that = (ProcurementCardTransactionDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(financialDocumentTransactionLineNumber, that.financialDocumentTransactionLineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, financialDocumentTransactionLineNumber);
    }
}
