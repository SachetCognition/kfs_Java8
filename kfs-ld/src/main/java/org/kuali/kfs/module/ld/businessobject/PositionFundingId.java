package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class PositionFundingId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String financialObjectCode;
    private String financialSubObjectCode;
    private String positionNumber;
    private String emplid;
    private String csfCreateTimestamp;

    public PositionFundingId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionFundingId other = (PositionFundingId) o;
        return Objects.equals(universityFiscalYear, other.universityFiscalYear) && Objects.equals(chartOfAccountsCode, other.chartOfAccountsCode) && Objects.equals(accountNumber, other.accountNumber) && Objects.equals(subAccountNumber, other.subAccountNumber) && Objects.equals(financialObjectCode, other.financialObjectCode) && Objects.equals(financialSubObjectCode, other.financialSubObjectCode) && Objects.equals(positionNumber, other.positionNumber) && Objects.equals(emplid, other.emplid) && Objects.equals(csfCreateTimestamp, other.csfCreateTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber, financialObjectCode, financialSubObjectCode, positionNumber, emplid, csfCreateTimestamp);
    }
}
