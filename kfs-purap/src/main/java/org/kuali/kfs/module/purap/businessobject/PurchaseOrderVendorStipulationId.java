package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class PurchaseOrderVendorStipulationId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer purchaseOrderVendorStipulationIdentifier;

    public PurchaseOrderVendorStipulationId() {
    }

    public PurchaseOrderVendorStipulationId(String documentNumber, Integer purchaseOrderVendorStipulationIdentifier) {
        this.documentNumber = documentNumber;
        this.purchaseOrderVendorStipulationIdentifier = purchaseOrderVendorStipulationIdentifier;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getPurchaseOrderVendorStipulationIdentifier() {
        return purchaseOrderVendorStipulationIdentifier;
    }

    public void setPurchaseOrderVendorStipulationIdentifier(Integer purchaseOrderVendorStipulationIdentifier) {
        this.purchaseOrderVendorStipulationIdentifier = purchaseOrderVendorStipulationIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderVendorStipulationId that = (PurchaseOrderVendorStipulationId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(purchaseOrderVendorStipulationIdentifier, that.purchaseOrderVendorStipulationIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, purchaseOrderVendorStipulationIdentifier);
    }
}
