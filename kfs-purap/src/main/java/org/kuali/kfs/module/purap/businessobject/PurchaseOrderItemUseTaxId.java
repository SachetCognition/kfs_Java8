package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class PurchaseOrderItemUseTaxId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer useTaxId;

    public PurchaseOrderItemUseTaxId() {
    }

    public PurchaseOrderItemUseTaxId(String documentNumber, Integer useTaxId) {
        this.documentNumber = documentNumber;
        this.useTaxId = useTaxId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getUseTaxId() {
        return useTaxId;
    }

    public void setUseTaxId(Integer useTaxId) {
        this.useTaxId = useTaxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderItemUseTaxId that = (PurchaseOrderItemUseTaxId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(useTaxId, that.useTaxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, useTaxId);
    }
}
