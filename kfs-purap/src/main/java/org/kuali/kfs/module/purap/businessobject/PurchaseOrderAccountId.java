package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class PurchaseOrderAccountId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer accountIdentifier;

    public PurchaseOrderAccountId() {
    }

    public PurchaseOrderAccountId(String documentNumber, Integer accountIdentifier) {
        this.documentNumber = documentNumber;
        this.accountIdentifier = accountIdentifier;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(Integer accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderAccountId that = (PurchaseOrderAccountId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(accountIdentifier, that.accountIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, accountIdentifier);
    }
}
