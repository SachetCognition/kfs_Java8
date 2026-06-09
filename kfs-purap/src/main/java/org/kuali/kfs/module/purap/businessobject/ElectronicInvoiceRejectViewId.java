package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class ElectronicInvoiceRejectViewId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer accountsPayablePurchasingDocumentLinkIdentifier;
    private Integer purapDocumentIdentifier;
    private Integer paymentRequestIdentifier;
    private Integer purchaseOrderIdentifier;
    private String documentNumber;

    public ElectronicInvoiceRejectViewId() {
    }

    public ElectronicInvoiceRejectViewId(Integer accountsPayablePurchasingDocumentLinkIdentifier, Integer purapDocumentIdentifier, Integer paymentRequestIdentifier, Integer purchaseOrderIdentifier, String documentNumber) {
        this.accountsPayablePurchasingDocumentLinkIdentifier = accountsPayablePurchasingDocumentLinkIdentifier;
        this.purapDocumentIdentifier = purapDocumentIdentifier;
        this.paymentRequestIdentifier = paymentRequestIdentifier;
        this.purchaseOrderIdentifier = purchaseOrderIdentifier;
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

    public Integer getPaymentRequestIdentifier() {
        return paymentRequestIdentifier;
    }

    public void setPaymentRequestIdentifier(Integer paymentRequestIdentifier) {
        this.paymentRequestIdentifier = paymentRequestIdentifier;
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
        ElectronicInvoiceRejectViewId that = (ElectronicInvoiceRejectViewId) o;
        return Objects.equals(accountsPayablePurchasingDocumentLinkIdentifier, that.accountsPayablePurchasingDocumentLinkIdentifier) && Objects.equals(purapDocumentIdentifier, that.purapDocumentIdentifier) && Objects.equals(paymentRequestIdentifier, that.paymentRequestIdentifier) && Objects.equals(purchaseOrderIdentifier, that.purchaseOrderIdentifier) && Objects.equals(documentNumber, that.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountsPayablePurchasingDocumentLinkIdentifier, purapDocumentIdentifier, paymentRequestIdentifier, purchaseOrderIdentifier, documentNumber);
    }
}
