package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class ElectronicInvoiceLoadSummaryId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer invoiceLoadSummaryIdentifier;
    private String vendorDunsNumber;

    public ElectronicInvoiceLoadSummaryId() {
    }

    public ElectronicInvoiceLoadSummaryId(Integer invoiceLoadSummaryIdentifier, String vendorDunsNumber) {
        this.invoiceLoadSummaryIdentifier = invoiceLoadSummaryIdentifier;
        this.vendorDunsNumber = vendorDunsNumber;
    }

    public Integer getInvoiceLoadSummaryIdentifier() {
        return invoiceLoadSummaryIdentifier;
    }

    public void setInvoiceLoadSummaryIdentifier(Integer invoiceLoadSummaryIdentifier) {
        this.invoiceLoadSummaryIdentifier = invoiceLoadSummaryIdentifier;
    }

    public String getVendorDunsNumber() {
        return vendorDunsNumber;
    }

    public void setVendorDunsNumber(String vendorDunsNumber) {
        this.vendorDunsNumber = vendorDunsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectronicInvoiceLoadSummaryId that = (ElectronicInvoiceLoadSummaryId) o;
        return Objects.equals(invoiceLoadSummaryIdentifier, that.invoiceLoadSummaryIdentifier) && Objects.equals(vendorDunsNumber, that.vendorDunsNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceLoadSummaryIdentifier, vendorDunsNumber);
    }
}
