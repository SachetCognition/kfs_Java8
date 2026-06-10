package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class ProcurementCardTargetAccountingLineId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer financialDocumentTransactionLineNumber;
    private Integer sequenceNumber;
    private String financialDocumentLineTypeCode;

    public ProcurementCardTargetAccountingLineId() {}

    public ProcurementCardTargetAccountingLineId(String documentNumber, Integer financialDocumentTransactionLineNumber, Integer sequenceNumber, String financialDocumentLineTypeCode) {
        this.documentNumber = documentNumber;
        this.financialDocumentTransactionLineNumber = financialDocumentTransactionLineNumber;
        this.sequenceNumber = sequenceNumber;
        this.financialDocumentLineTypeCode = financialDocumentLineTypeCode;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getFinancialDocumentTransactionLineNumber() { return financialDocumentTransactionLineNumber; }
    public void setFinancialDocumentTransactionLineNumber(Integer financialDocumentTransactionLineNumber) { this.financialDocumentTransactionLineNumber = financialDocumentTransactionLineNumber; }

    public Integer getSequenceNumber() { return sequenceNumber; }
    public void setSequenceNumber(Integer sequenceNumber) { this.sequenceNumber = sequenceNumber; }

    public String getFinancialDocumentLineTypeCode() { return financialDocumentLineTypeCode; }
    public void setFinancialDocumentLineTypeCode(String financialDocumentLineTypeCode) { this.financialDocumentLineTypeCode = financialDocumentLineTypeCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcurementCardTargetAccountingLineId that = (ProcurementCardTargetAccountingLineId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(financialDocumentTransactionLineNumber, that.financialDocumentTransactionLineNumber) && Objects.equals(sequenceNumber, that.sequenceNumber) && Objects.equals(financialDocumentLineTypeCode, that.financialDocumentLineTypeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, financialDocumentTransactionLineNumber, sequenceNumber, financialDocumentLineTypeCode);
    }
}
