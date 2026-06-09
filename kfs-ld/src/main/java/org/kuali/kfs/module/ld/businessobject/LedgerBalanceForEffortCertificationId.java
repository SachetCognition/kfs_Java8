package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class LedgerBalanceForEffortCertificationId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String financialObjectCode;
    private String financialSubObjectCode;
    private String financialBalanceTypeCode;
    private String financialObjectTypeCode;
    private String positionNumber;
    private String emplid;

    public LedgerBalanceForEffortCertificationId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LedgerBalanceForEffortCertificationId other = (LedgerBalanceForEffortCertificationId) o;
        return Objects.equals(universityFiscalYear, other.universityFiscalYear) && Objects.equals(chartOfAccountsCode, other.chartOfAccountsCode) && Objects.equals(accountNumber, other.accountNumber) && Objects.equals(subAccountNumber, other.subAccountNumber) && Objects.equals(financialObjectCode, other.financialObjectCode) && Objects.equals(financialSubObjectCode, other.financialSubObjectCode) && Objects.equals(financialBalanceTypeCode, other.financialBalanceTypeCode) && Objects.equals(financialObjectTypeCode, other.financialObjectTypeCode) && Objects.equals(positionNumber, other.positionNumber) && Objects.equals(emplid, other.emplid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber, financialObjectCode, financialSubObjectCode, financialBalanceTypeCode, financialObjectTypeCode, positionNumber, emplid);
    }
}
