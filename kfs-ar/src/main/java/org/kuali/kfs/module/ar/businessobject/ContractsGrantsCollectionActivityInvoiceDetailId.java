package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class ContractsGrantsCollectionActivityInvoiceDetailId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String invoiceNumber;

    public ContractsGrantsCollectionActivityInvoiceDetailId() {}

    public ContractsGrantsCollectionActivityInvoiceDetailId(String documentNumber, String invoiceNumber) {
        this.documentNumber = documentNumber;
        this.invoiceNumber = invoiceNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractsGrantsCollectionActivityInvoiceDetailId that = (ContractsGrantsCollectionActivityInvoiceDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(invoiceNumber, that.invoiceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, invoiceNumber);
    }
}
