package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class ContractsGrantsLetterOfCreditReviewDetailId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Long letterOfCreditReviewDetailIdentifier;

    public ContractsGrantsLetterOfCreditReviewDetailId() {}

    public ContractsGrantsLetterOfCreditReviewDetailId(String documentNumber, Long letterOfCreditReviewDetailIdentifier) {
        this.documentNumber = documentNumber;
        this.letterOfCreditReviewDetailIdentifier = letterOfCreditReviewDetailIdentifier;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Long getLetterOfCreditReviewDetailIdentifier() { return letterOfCreditReviewDetailIdentifier; }
    public void setLetterOfCreditReviewDetailIdentifier(Long letterOfCreditReviewDetailIdentifier) { this.letterOfCreditReviewDetailIdentifier = letterOfCreditReviewDetailIdentifier; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractsGrantsLetterOfCreditReviewDetailId that = (ContractsGrantsLetterOfCreditReviewDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(letterOfCreditReviewDetailIdentifier, that.letterOfCreditReviewDetailIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, letterOfCreditReviewDetailIdentifier);
    }
}
