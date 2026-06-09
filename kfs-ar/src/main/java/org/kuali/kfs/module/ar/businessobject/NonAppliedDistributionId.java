package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class NonAppliedDistributionId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer paidAppliedItemNumber;
    private String referenceFinancialDocumentNumber;

    public NonAppliedDistributionId() {}

    public NonAppliedDistributionId(String documentNumber, Integer paidAppliedItemNumber, String referenceFinancialDocumentNumber) {
        this.documentNumber = documentNumber;
        this.paidAppliedItemNumber = paidAppliedItemNumber;
        this.referenceFinancialDocumentNumber = referenceFinancialDocumentNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getPaidAppliedItemNumber() { return paidAppliedItemNumber; }
    public void setPaidAppliedItemNumber(Integer paidAppliedItemNumber) { this.paidAppliedItemNumber = paidAppliedItemNumber; }

    public String getReferenceFinancialDocumentNumber() { return referenceFinancialDocumentNumber; }
    public void setReferenceFinancialDocumentNumber(String referenceFinancialDocumentNumber) { this.referenceFinancialDocumentNumber = referenceFinancialDocumentNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonAppliedDistributionId that = (NonAppliedDistributionId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(paidAppliedItemNumber, that.paidAppliedItemNumber) && Objects.equals(referenceFinancialDocumentNumber, that.referenceFinancialDocumentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, paidAppliedItemNumber, referenceFinancialDocumentNumber);
    }
}
