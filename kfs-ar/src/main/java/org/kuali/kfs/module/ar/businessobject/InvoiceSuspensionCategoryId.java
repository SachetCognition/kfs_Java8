package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class InvoiceSuspensionCategoryId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String suspensionCategoryCode;

    public InvoiceSuspensionCategoryId() {}

    public InvoiceSuspensionCategoryId(String documentNumber, String suspensionCategoryCode) {
        this.documentNumber = documentNumber;
        this.suspensionCategoryCode = suspensionCategoryCode;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getSuspensionCategoryCode() { return suspensionCategoryCode; }
    public void setSuspensionCategoryCode(String suspensionCategoryCode) { this.suspensionCategoryCode = suspensionCategoryCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSuspensionCategoryId that = (InvoiceSuspensionCategoryId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(suspensionCategoryCode, that.suspensionCategoryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, suspensionCategoryCode);
    }
}
