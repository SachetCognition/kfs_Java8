package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class LaborLedgerPendingEntryId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String financialSystemOriginationCode;
    private String documentNumber;
    private String transactionLedgerEntrySequenceNumber;

    public LaborLedgerPendingEntryId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaborLedgerPendingEntryId other = (LaborLedgerPendingEntryId) o;
        return Objects.equals(financialSystemOriginationCode, other.financialSystemOriginationCode) && Objects.equals(documentNumber, other.documentNumber) && Objects.equals(transactionLedgerEntrySequenceNumber, other.transactionLedgerEntrySequenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(financialSystemOriginationCode, documentNumber, transactionLedgerEntrySequenceNumber);
    }
}
