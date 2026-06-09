/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 *
 * Copyright 2005-2014 The Kuali Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.module.tem.businessobject;

import java.io.Serializable;

public class HeldEncumbranceEntryId implements Serializable {
    private String documentNumber;
    private Integer transactionLedgerEntrySequenceNumber;

    public HeldEncumbranceEntryId() {}

    public HeldEncumbranceEntryId(String documentNumber, Integer transactionLedgerEntrySequenceNumber) {
        this.documentNumber = documentNumber;
        this.transactionLedgerEntrySequenceNumber = transactionLedgerEntrySequenceNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    public Integer getTransactionLedgerEntrySequenceNumber() { return transactionLedgerEntrySequenceNumber; }
    public void setTransactionLedgerEntrySequenceNumber(Integer transactionLedgerEntrySequenceNumber) { this.transactionLedgerEntrySequenceNumber = transactionLedgerEntrySequenceNumber; }

    @Override
    public int hashCode() {
        int result = documentNumber != null ? documentNumber.hashCode() : 0;
        result = 31 * result + (transactionLedgerEntrySequenceNumber != null ? transactionLedgerEntrySequenceNumber.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeldEncumbranceEntryId that = (HeldEncumbranceEntryId) o;
        if (documentNumber != null ? !documentNumber.equals(that.documentNumber) : that.documentNumber != null) return false;
        return transactionLedgerEntrySequenceNumber != null ? transactionLedgerEntrySequenceNumber.equals(that.transactionLedgerEntrySequenceNumber) : that.transactionLedgerEntrySequenceNumber == null;
    }
}
