package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class PurchaseOrderVendorQuoteId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer purchaseOrderVendorQuoteIdentifier;

    public PurchaseOrderVendorQuoteId() {
    }

    public PurchaseOrderVendorQuoteId(String documentNumber, Integer purchaseOrderVendorQuoteIdentifier) {
        this.documentNumber = documentNumber;
        this.purchaseOrderVendorQuoteIdentifier = purchaseOrderVendorQuoteIdentifier;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getPurchaseOrderVendorQuoteIdentifier() {
        return purchaseOrderVendorQuoteIdentifier;
    }

    public void setPurchaseOrderVendorQuoteIdentifier(Integer purchaseOrderVendorQuoteIdentifier) {
        this.purchaseOrderVendorQuoteIdentifier = purchaseOrderVendorQuoteIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderVendorQuoteId that = (PurchaseOrderVendorQuoteId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(purchaseOrderVendorQuoteIdentifier, that.purchaseOrderVendorQuoteIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, purchaseOrderVendorQuoteIdentifier);
    }
}
