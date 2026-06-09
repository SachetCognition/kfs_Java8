package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CustomerCreditMemoDetailId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer referenceInvoiceItemNumber;

    public CustomerCreditMemoDetailId() {}

    public CustomerCreditMemoDetailId(String documentNumber, Integer referenceInvoiceItemNumber) {
        this.documentNumber = documentNumber;
        this.referenceInvoiceItemNumber = referenceInvoiceItemNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getReferenceInvoiceItemNumber() { return referenceInvoiceItemNumber; }
    public void setReferenceInvoiceItemNumber(Integer referenceInvoiceItemNumber) { this.referenceInvoiceItemNumber = referenceInvoiceItemNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerCreditMemoDetailId that = (CustomerCreditMemoDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(referenceInvoiceItemNumber, that.referenceInvoiceItemNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, referenceInvoiceItemNumber);
    }
}
