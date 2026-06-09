package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class LaborEntryHistoryId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String financialObjectCode;
    private String financialBalanceTypeCode;
    private String universityFiscalPeriodCode;
    private String transactionDebitCreditCode;

    public LaborEntryHistoryId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaborEntryHistoryId other = (LaborEntryHistoryId) o;
        return Objects.equals(universityFiscalYear, other.universityFiscalYear) && Objects.equals(chartOfAccountsCode, other.chartOfAccountsCode) && Objects.equals(financialObjectCode, other.financialObjectCode) && Objects.equals(financialBalanceTypeCode, other.financialBalanceTypeCode) && Objects.equals(universityFiscalPeriodCode, other.universityFiscalPeriodCode) && Objects.equals(transactionDebitCreditCode, other.transactionDebitCreditCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, chartOfAccountsCode, financialObjectCode, financialBalanceTypeCode, universityFiscalPeriodCode, transactionDebitCreditCode);
    }
}
