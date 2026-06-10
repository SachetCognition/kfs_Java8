package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class PurchaseOrderItemId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer itemIdentifier;

    public PurchaseOrderItemId() {
    }

    public PurchaseOrderItemId(String documentNumber, Integer itemIdentifier) {
        this.documentNumber = documentNumber;
        this.itemIdentifier = itemIdentifier;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getItemIdentifier() {
        return itemIdentifier;
    }

    public void setItemIdentifier(Integer itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderItemId that = (PurchaseOrderItemId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(itemIdentifier, that.itemIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, itemIdentifier);
    }
}
