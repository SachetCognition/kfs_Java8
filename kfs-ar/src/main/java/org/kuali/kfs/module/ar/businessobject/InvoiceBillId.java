package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class InvoiceBillId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String billIdentifier;

    public InvoiceBillId() {}

    public InvoiceBillId(String documentNumber, String billIdentifier) {
        this.documentNumber = documentNumber;
        this.billIdentifier = billIdentifier;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getBillIdentifier() { return billIdentifier; }
    public void setBillIdentifier(String billIdentifier) { this.billIdentifier = billIdentifier; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceBillId that = (InvoiceBillId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(billIdentifier, that.billIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, billIdentifier);
    }
}
