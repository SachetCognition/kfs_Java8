package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionLockSummaryId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String lockType;
    private String lockUserId;
    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String positionNumber;

    public BudgetConstructionLockSummaryId() {}

    public String getLockType() { return lockType; }
    public void setLockType(String lockType) { this.lockType = lockType; }

    public String getLockUserId() { return lockUserId; }
    public void setLockUserId(String lockUserId) { this.lockUserId = lockUserId; }

    public Integer getUniversityFiscalYear() { return universityFiscalYear; }
    public void setUniversityFiscalYear(Integer universityFiscalYear) { this.universityFiscalYear = universityFiscalYear; }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getSubAccountNumber() { return subAccountNumber; }
    public void setSubAccountNumber(String subAccountNumber) { this.subAccountNumber = subAccountNumber; }

    public String getPositionNumber() { return positionNumber; }
    public void setPositionNumber(String positionNumber) { this.positionNumber = positionNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionLockSummaryId that = (BudgetConstructionLockSummaryId) o;
        return Objects.equals(lockType, that.lockType) && Objects.equals(lockUserId, that.lockUserId) && Objects.equals(universityFiscalYear, that.universityFiscalYear) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(subAccountNumber, that.subAccountNumber) && Objects.equals(positionNumber, that.positionNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lockType, lockUserId, universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber, positionNumber);
    }
}
