package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class AdvanceDepositDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String financialDocumentTypeCode;
    private Integer financialDocumentLineNumber;

    public AdvanceDepositDetailId() {}

    public AdvanceDepositDetailId(String documentNumber, String financialDocumentTypeCode, Integer financialDocumentLineNumber) {
        this.documentNumber = documentNumber;
        this.financialDocumentTypeCode = financialDocumentTypeCode;
        this.financialDocumentLineNumber = financialDocumentLineNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getFinancialDocumentTypeCode() { return financialDocumentTypeCode; }
    public void setFinancialDocumentTypeCode(String financialDocumentTypeCode) { this.financialDocumentTypeCode = financialDocumentTypeCode; }

    public Integer getFinancialDocumentLineNumber() { return financialDocumentLineNumber; }
    public void setFinancialDocumentLineNumber(Integer financialDocumentLineNumber) { this.financialDocumentLineNumber = financialDocumentLineNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvanceDepositDetailId that = (AdvanceDepositDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(financialDocumentTypeCode, that.financialDocumentTypeCode) && Objects.equals(financialDocumentLineNumber, that.financialDocumentLineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, financialDocumentTypeCode, financialDocumentLineNumber);
    }
}
