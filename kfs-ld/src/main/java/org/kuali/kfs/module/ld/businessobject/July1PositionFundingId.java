package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class July1PositionFundingId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String financialObjectCode;
    private String financialSubObjectCode;
    private String positionNumber;
    private String emplid;

    public July1PositionFundingId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        July1PositionFundingId other = (July1PositionFundingId) o;
        return Objects.equals(universityFiscalYear, other.universityFiscalYear) && Objects.equals(chartOfAccountsCode, other.chartOfAccountsCode) && Objects.equals(accountNumber, other.accountNumber) && Objects.equals(subAccountNumber, other.subAccountNumber) && Objects.equals(financialObjectCode, other.financialObjectCode) && Objects.equals(financialSubObjectCode, other.financialSubObjectCode) && Objects.equals(positionNumber, other.positionNumber) && Objects.equals(emplid, other.emplid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber, financialObjectCode, financialSubObjectCode, positionNumber, emplid);
    }
}
