package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class PurchaseOrderQuoteListVendorId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer purchaseOrderQuoteListIdentifier;
    private Integer vendorHeaderGeneratedIdentifier;
    private Integer vendorDetailAssignedIdentifier;

    public PurchaseOrderQuoteListVendorId() {
    }

    public PurchaseOrderQuoteListVendorId(Integer purchaseOrderQuoteListIdentifier, Integer vendorHeaderGeneratedIdentifier, Integer vendorDetailAssignedIdentifier) {
        this.purchaseOrderQuoteListIdentifier = purchaseOrderQuoteListIdentifier;
        this.vendorHeaderGeneratedIdentifier = vendorHeaderGeneratedIdentifier;
        this.vendorDetailAssignedIdentifier = vendorDetailAssignedIdentifier;
    }

    public Integer getPurchaseOrderQuoteListIdentifier() {
        return purchaseOrderQuoteListIdentifier;
    }

    public void setPurchaseOrderQuoteListIdentifier(Integer purchaseOrderQuoteListIdentifier) {
        this.purchaseOrderQuoteListIdentifier = purchaseOrderQuoteListIdentifier;
    }

    public Integer getVendorHeaderGeneratedIdentifier() {
        return vendorHeaderGeneratedIdentifier;
    }

    public void setVendorHeaderGeneratedIdentifier(Integer vendorHeaderGeneratedIdentifier) {
        this.vendorHeaderGeneratedIdentifier = vendorHeaderGeneratedIdentifier;
    }

    public Integer getVendorDetailAssignedIdentifier() {
        return vendorDetailAssignedIdentifier;
    }

    public void setVendorDetailAssignedIdentifier(Integer vendorDetailAssignedIdentifier) {
        this.vendorDetailAssignedIdentifier = vendorDetailAssignedIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderQuoteListVendorId that = (PurchaseOrderQuoteListVendorId) o;
        return Objects.equals(purchaseOrderQuoteListIdentifier, that.purchaseOrderQuoteListIdentifier) && Objects.equals(vendorHeaderGeneratedIdentifier, that.vendorHeaderGeneratedIdentifier) && Objects.equals(vendorDetailAssignedIdentifier, that.vendorDetailAssignedIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseOrderQuoteListIdentifier, vendorHeaderGeneratedIdentifier, vendorDetailAssignedIdentifier);
    }
}
