package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class LedgerEntryId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String financialObjectCode;
    private String financialSubObjectCode;
    private String financialBalanceTypeCode;
    private String financialObjectTypeCode;
    private String universityFiscalPeriodCode;
    private String financialDocumentTypeCode;
    private String financialSystemOriginationCode;
    private String documentNumber;
    private String transactionLedgerEntrySequenceNumber;

    public LedgerEntryId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LedgerEntryId other = (LedgerEntryId) o;
        return Objects.equals(universityFiscalYear, other.universityFiscalYear) && Objects.equals(chartOfAccountsCode, other.chartOfAccountsCode) && Objects.equals(accountNumber, other.accountNumber) && Objects.equals(subAccountNumber, other.subAccountNumber) && Objects.equals(financialObjectCode, other.financialObjectCode) && Objects.equals(financialSubObjectCode, other.financialSubObjectCode) && Objects.equals(financialBalanceTypeCode, other.financialBalanceTypeCode) && Objects.equals(financialObjectTypeCode, other.financialObjectTypeCode) && Objects.equals(universityFiscalPeriodCode, other.universityFiscalPeriodCode) && Objects.equals(financialDocumentTypeCode, other.financialDocumentTypeCode) && Objects.equals(financialSystemOriginationCode, other.financialSystemOriginationCode) && Objects.equals(documentNumber, other.documentNumber) && Objects.equals(transactionLedgerEntrySequenceNumber, other.transactionLedgerEntrySequenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber, financialObjectCode, financialSubObjectCode, financialBalanceTypeCode, financialObjectTypeCode, universityFiscalPeriodCode, financialDocumentTypeCode, financialSystemOriginationCode, documentNumber, transactionLedgerEntrySequenceNumber);
    }
}
