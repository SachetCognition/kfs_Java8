package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CorrectionReceivingViewId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer accountsPayablePurchasingDocumentLinkIdentifier;
    private String lineItemReceivingDocumentNumber;
    private String documentNumber;

    public CorrectionReceivingViewId() {
    }

    public CorrectionReceivingViewId(Integer accountsPayablePurchasingDocumentLinkIdentifier, String lineItemReceivingDocumentNumber, String documentNumber) {
        this.accountsPayablePurchasingDocumentLinkIdentifier = accountsPayablePurchasingDocumentLinkIdentifier;
        this.lineItemReceivingDocumentNumber = lineItemReceivingDocumentNumber;
        this.documentNumber = documentNumber;
    }

    public Integer getAccountsPayablePurchasingDocumentLinkIdentifier() {
        return accountsPayablePurchasingDocumentLinkIdentifier;
    }

    public void setAccountsPayablePurchasingDocumentLinkIdentifier(Integer accountsPayablePurchasingDocumentLinkIdentifier) {
        this.accountsPayablePurchasingDocumentLinkIdentifier = accountsPayablePurchasingDocumentLinkIdentifier;
    }

    public String getLineItemReceivingDocumentNumber() {
        return lineItemReceivingDocumentNumber;
    }

    public void setLineItemReceivingDocumentNumber(String lineItemReceivingDocumentNumber) {
        this.lineItemReceivingDocumentNumber = lineItemReceivingDocumentNumber;
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
        CorrectionReceivingViewId that = (CorrectionReceivingViewId) o;
        return Objects.equals(accountsPayablePurchasingDocumentLinkIdentifier, that.accountsPayablePurchasingDocumentLinkIdentifier) && Objects.equals(lineItemReceivingDocumentNumber, that.lineItemReceivingDocumentNumber) && Objects.equals(documentNumber, that.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountsPayablePurchasingDocumentLinkIdentifier, lineItemReceivingDocumentNumber, documentNumber);
    }
}
