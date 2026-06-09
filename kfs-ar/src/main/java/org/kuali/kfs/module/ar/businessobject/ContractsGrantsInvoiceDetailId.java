package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class ContractsGrantsInvoiceDetailId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Long invoiceDetailIdentifier;

    public ContractsGrantsInvoiceDetailId() {}

    public ContractsGrantsInvoiceDetailId(String documentNumber, Long invoiceDetailIdentifier) {
        this.documentNumber = documentNumber;
        this.invoiceDetailIdentifier = invoiceDetailIdentifier;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Long getInvoiceDetailIdentifier() { return invoiceDetailIdentifier; }
    public void setInvoiceDetailIdentifier(Long invoiceDetailIdentifier) { this.invoiceDetailIdentifier = invoiceDetailIdentifier; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractsGrantsInvoiceDetailId that = (ContractsGrantsInvoiceDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(invoiceDetailIdentifier, that.invoiceDetailIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, invoiceDetailIdentifier);
    }
}
