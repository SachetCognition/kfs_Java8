package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class InvoiceAddressDetailId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String customerNumber;
    private Integer customerAddressIdentifier;

    public InvoiceAddressDetailId() {}

    public InvoiceAddressDetailId(String documentNumber, String customerNumber, Integer customerAddressIdentifier) {
        this.documentNumber = documentNumber;
        this.customerNumber = customerNumber;
        this.customerAddressIdentifier = customerAddressIdentifier;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getCustomerNumber() { return customerNumber; }
    public void setCustomerNumber(String customerNumber) { this.customerNumber = customerNumber; }

    public Integer getCustomerAddressIdentifier() { return customerAddressIdentifier; }
    public void setCustomerAddressIdentifier(Integer customerAddressIdentifier) { this.customerAddressIdentifier = customerAddressIdentifier; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceAddressDetailId that = (InvoiceAddressDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(customerNumber, that.customerNumber) && Objects.equals(customerAddressIdentifier, that.customerAddressIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, customerNumber, customerAddressIdentifier);
    }
}
