package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class DepositId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer financialDocumentDepositLineNumber;

    public DepositId() {}

    public DepositId(String documentNumber, Integer financialDocumentDepositLineNumber) {
        this.documentNumber = documentNumber;
        this.financialDocumentDepositLineNumber = financialDocumentDepositLineNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getFinancialDocumentDepositLineNumber() { return financialDocumentDepositLineNumber; }
    public void setFinancialDocumentDepositLineNumber(Integer financialDocumentDepositLineNumber) { this.financialDocumentDepositLineNumber = financialDocumentDepositLineNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositId that = (DepositId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(financialDocumentDepositLineNumber, that.financialDocumentDepositLineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, financialDocumentDepositLineNumber);
    }
}
