package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CheckBaseId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String financialDocumentTypeCode;
    private String cashieringStatus;
    private Integer sequenceId;

    public CheckBaseId() {}

    public CheckBaseId(String documentNumber, String financialDocumentTypeCode, String cashieringStatus, Integer sequenceId) {
        this.documentNumber = documentNumber;
        this.financialDocumentTypeCode = financialDocumentTypeCode;
        this.cashieringStatus = cashieringStatus;
        this.sequenceId = sequenceId;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getFinancialDocumentTypeCode() { return financialDocumentTypeCode; }
    public void setFinancialDocumentTypeCode(String financialDocumentTypeCode) { this.financialDocumentTypeCode = financialDocumentTypeCode; }

    public String getCashieringStatus() { return cashieringStatus; }
    public void setCashieringStatus(String cashieringStatus) { this.cashieringStatus = cashieringStatus; }

    public Integer getSequenceId() { return sequenceId; }
    public void setSequenceId(Integer sequenceId) { this.sequenceId = sequenceId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckBaseId that = (CheckBaseId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(financialDocumentTypeCode, that.financialDocumentTypeCode) && Objects.equals(cashieringStatus, that.cashieringStatus) && Objects.equals(sequenceId, that.sequenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, financialDocumentTypeCode, cashieringStatus, sequenceId);
    }
}
