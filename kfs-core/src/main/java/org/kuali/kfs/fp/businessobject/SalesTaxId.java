package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class SalesTaxId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String financialDocumentLineTypeCode;
    private Integer financialDocumentLineNumber;

    public SalesTaxId() {}

    public SalesTaxId(String documentNumber, String financialDocumentLineTypeCode, Integer financialDocumentLineNumber) {
        this.documentNumber = documentNumber;
        this.financialDocumentLineTypeCode = financialDocumentLineTypeCode;
        this.financialDocumentLineNumber = financialDocumentLineNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getFinancialDocumentLineTypeCode() { return financialDocumentLineTypeCode; }
    public void setFinancialDocumentLineTypeCode(String financialDocumentLineTypeCode) { this.financialDocumentLineTypeCode = financialDocumentLineTypeCode; }

    public Integer getFinancialDocumentLineNumber() { return financialDocumentLineNumber; }
    public void setFinancialDocumentLineNumber(Integer financialDocumentLineNumber) { this.financialDocumentLineNumber = financialDocumentLineNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTaxId that = (SalesTaxId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(financialDocumentLineTypeCode, that.financialDocumentLineTypeCode) && Objects.equals(financialDocumentLineNumber, that.financialDocumentLineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, financialDocumentLineTypeCode, financialDocumentLineNumber);
    }
}
