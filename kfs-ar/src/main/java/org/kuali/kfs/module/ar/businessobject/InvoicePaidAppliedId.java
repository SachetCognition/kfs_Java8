package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class InvoicePaidAppliedId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer paidAppliedItemNumber;

    public InvoicePaidAppliedId() {}

    public InvoicePaidAppliedId(String documentNumber, Integer paidAppliedItemNumber) {
        this.documentNumber = documentNumber;
        this.paidAppliedItemNumber = paidAppliedItemNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getPaidAppliedItemNumber() { return paidAppliedItemNumber; }
    public void setPaidAppliedItemNumber(Integer paidAppliedItemNumber) { this.paidAppliedItemNumber = paidAppliedItemNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoicePaidAppliedId that = (InvoicePaidAppliedId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(paidAppliedItemNumber, that.paidAppliedItemNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, paidAppliedItemNumber);
    }
}
