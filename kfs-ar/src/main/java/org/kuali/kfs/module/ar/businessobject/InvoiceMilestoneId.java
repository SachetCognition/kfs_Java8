package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class InvoiceMilestoneId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Long milestoneIdentifier;

    public InvoiceMilestoneId() {}

    public InvoiceMilestoneId(String documentNumber, Long milestoneIdentifier) {
        this.documentNumber = documentNumber;
        this.milestoneIdentifier = milestoneIdentifier;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Long getMilestoneIdentifier() { return milestoneIdentifier; }
    public void setMilestoneIdentifier(Long milestoneIdentifier) { this.milestoneIdentifier = milestoneIdentifier; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceMilestoneId that = (InvoiceMilestoneId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(milestoneIdentifier, that.milestoneIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, milestoneIdentifier);
    }
}
