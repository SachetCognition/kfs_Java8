package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class LaborJournalVoucherDetailId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer sequenceNumber;

    public LaborJournalVoucherDetailId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaborJournalVoucherDetailId other = (LaborJournalVoucherDetailId) o;
        return Objects.equals(documentNumber, other.documentNumber) && Objects.equals(sequenceNumber, other.sequenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, sequenceNumber);
    }
}
