package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class GECTargetAccountingLineId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer sequenceNumber;
    private String financialDocumentLineTypeCode;

    public GECTargetAccountingLineId() {}

    public GECTargetAccountingLineId(String documentNumber, Integer sequenceNumber, String financialDocumentLineTypeCode) {
        this.documentNumber = documentNumber;
        this.sequenceNumber = sequenceNumber;
        this.financialDocumentLineTypeCode = financialDocumentLineTypeCode;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getSequenceNumber() { return sequenceNumber; }
    public void setSequenceNumber(Integer sequenceNumber) { this.sequenceNumber = sequenceNumber; }

    public String getFinancialDocumentLineTypeCode() { return financialDocumentLineTypeCode; }
    public void setFinancialDocumentLineTypeCode(String financialDocumentLineTypeCode) { this.financialDocumentLineTypeCode = financialDocumentLineTypeCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GECTargetAccountingLineId that = (GECTargetAccountingLineId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(sequenceNumber, that.sequenceNumber) && Objects.equals(financialDocumentLineTypeCode, that.financialDocumentLineTypeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, sequenceNumber, financialDocumentLineTypeCode);
    }
}
