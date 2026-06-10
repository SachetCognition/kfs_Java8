package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class PurchaseOrderViewId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer accountsPayablePurchasingDocumentLinkIdentifier;
    private Integer purapDocumentIdentifier;
    private String documentNumber;

    public PurchaseOrderViewId() {
    }

    public PurchaseOrderViewId(Integer accountsPayablePurchasingDocumentLinkIdentifier, Integer purapDocumentIdentifier, String documentNumber) {
        this.accountsPayablePurchasingDocumentLinkIdentifier = accountsPayablePurchasingDocumentLinkIdentifier;
        this.purapDocumentIdentifier = purapDocumentIdentifier;
        this.documentNumber = documentNumber;
    }

    public Integer getAccountsPayablePurchasingDocumentLinkIdentifier() {
        return accountsPayablePurchasingDocumentLinkIdentifier;
    }

    public void setAccountsPayablePurchasingDocumentLinkIdentifier(Integer accountsPayablePurchasingDocumentLinkIdentifier) {
        this.accountsPayablePurchasingDocumentLinkIdentifier = accountsPayablePurchasingDocumentLinkIdentifier;
    }

    public Integer getPurapDocumentIdentifier() {
        return purapDocumentIdentifier;
    }

    public void setPurapDocumentIdentifier(Integer purapDocumentIdentifier) {
        this.purapDocumentIdentifier = purapDocumentIdentifier;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderViewId that = (PurchaseOrderViewId) o;
        return Objects.equals(accountsPayablePurchasingDocumentLinkIdentifier, that.accountsPayablePurchasingDocumentLinkIdentifier) && Objects.equals(purapDocumentIdentifier, that.purapDocumentIdentifier) && Objects.equals(documentNumber, that.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountsPayablePurchasingDocumentLinkIdentifier, purapDocumentIdentifier, documentNumber);
    }
}
