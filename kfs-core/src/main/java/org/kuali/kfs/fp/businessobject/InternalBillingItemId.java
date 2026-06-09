package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class InternalBillingItemId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer itemSequenceId;

    public InternalBillingItemId() {}

    public InternalBillingItemId(String documentNumber, Integer itemSequenceId) {
        this.documentNumber = documentNumber;
        this.itemSequenceId = itemSequenceId;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getItemSequenceId() { return itemSequenceId; }
    public void setItemSequenceId(Integer itemSequenceId) { this.itemSequenceId = itemSequenceId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalBillingItemId that = (InternalBillingItemId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(itemSequenceId, that.itemSequenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, itemSequenceId);
    }
}
