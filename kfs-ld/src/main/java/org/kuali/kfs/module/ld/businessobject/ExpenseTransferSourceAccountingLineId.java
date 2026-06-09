package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class ExpenseTransferSourceAccountingLineId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String sequenceNumber;

    public ExpenseTransferSourceAccountingLineId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseTransferSourceAccountingLineId other = (ExpenseTransferSourceAccountingLineId) o;
        return Objects.equals(documentNumber, other.documentNumber) && Objects.equals(sequenceNumber, other.sequenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, sequenceNumber);
    }
}
