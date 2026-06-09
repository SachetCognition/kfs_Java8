package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class ContractManagerAssignmentDetailId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer requisitionIdentifier;

    public ContractManagerAssignmentDetailId() {
    }

    public ContractManagerAssignmentDetailId(String documentNumber, Integer requisitionIdentifier) {
        this.documentNumber = documentNumber;
        this.requisitionIdentifier = requisitionIdentifier;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getRequisitionIdentifier() {
        return requisitionIdentifier;
    }

    public void setRequisitionIdentifier(Integer requisitionIdentifier) {
        this.requisitionIdentifier = requisitionIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractManagerAssignmentDetailId that = (ContractManagerAssignmentDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(requisitionIdentifier, that.requisitionIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, requisitionIdentifier);
    }
}
