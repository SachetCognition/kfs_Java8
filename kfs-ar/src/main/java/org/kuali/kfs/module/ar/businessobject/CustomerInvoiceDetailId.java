package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CustomerInvoiceDetailId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String sequenceNumber;

    public CustomerInvoiceDetailId() {}

    public CustomerInvoiceDetailId(String documentNumber, String sequenceNumber) {
        this.documentNumber = documentNumber;
        this.sequenceNumber = sequenceNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getSequenceNumber() { return sequenceNumber; }
    public void setSequenceNumber(String sequenceNumber) { this.sequenceNumber = sequenceNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInvoiceDetailId that = (CustomerInvoiceDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(sequenceNumber, that.sequenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, sequenceNumber);
    }
}
