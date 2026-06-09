package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class LineItemReceivingViewId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer accountsPayablePurchasingDocumentLinkIdentifier;
    private Integer purchaseOrderIdentifier;
    private String documentNumber;

    public LineItemReceivingViewId() {
    }

    public LineItemReceivingViewId(Integer accountsPayablePurchasingDocumentLinkIdentifier, Integer purchaseOrderIdentifier, String documentNumber) {
        this.accountsPayablePurchasingDocumentLinkIdentifier = accountsPayablePurchasingDocumentLinkIdentifier;
        this.purchaseOrderIdentifier = purchaseOrderIdentifier;
        this.documentNumber = documentNumber;
    }

    public Integer getAccountsPayablePurchasingDocumentLinkIdentifier() {
        return accountsPayablePurchasingDocumentLinkIdentifier;
    }

    public void setAccountsPayablePurchasingDocumentLinkIdentifier(Integer accountsPayablePurchasingDocumentLinkIdentifier) {
        this.accountsPayablePurchasingDocumentLinkIdentifier = accountsPayablePurchasingDocumentLinkIdentifier;
    }

    public Integer getPurchaseOrderIdentifier() {
        return purchaseOrderIdentifier;
    }

    public void setPurchaseOrderIdentifier(Integer purchaseOrderIdentifier) {
        this.purchaseOrderIdentifier = purchaseOrderIdentifier;
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
        LineItemReceivingViewId that = (LineItemReceivingViewId) o;
        return Objects.equals(accountsPayablePurchasingDocumentLinkIdentifier, that.accountsPayablePurchasingDocumentLinkIdentifier) && Objects.equals(purchaseOrderIdentifier, that.purchaseOrderIdentifier) && Objects.equals(documentNumber, that.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountsPayablePurchasingDocumentLinkIdentifier, purchaseOrderIdentifier, documentNumber);
    }
}
