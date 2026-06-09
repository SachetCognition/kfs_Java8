package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionFundingLockId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appointmentFundingLockUserId;
    private String accountNumber;
    private String subAccountNumber;
    private String chartOfAccountsCode;
    private Integer universityFiscalYear;

    public BudgetConstructionFundingLockId() {}

    public String getAppointmentFundingLockUserId() { return appointmentFundingLockUserId; }
    public void setAppointmentFundingLockUserId(String appointmentFundingLockUserId) { this.appointmentFundingLockUserId = appointmentFundingLockUserId; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getSubAccountNumber() { return subAccountNumber; }
    public void setSubAccountNumber(String subAccountNumber) { this.subAccountNumber = subAccountNumber; }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public Integer getUniversityFiscalYear() { return universityFiscalYear; }
    public void setUniversityFiscalYear(Integer universityFiscalYear) { this.universityFiscalYear = universityFiscalYear; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionFundingLockId that = (BudgetConstructionFundingLockId) o;
        return Objects.equals(appointmentFundingLockUserId, that.appointmentFundingLockUserId) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(subAccountNumber, that.subAccountNumber) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(universityFiscalYear, that.universityFiscalYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentFundingLockUserId, accountNumber, subAccountNumber, chartOfAccountsCode, universityFiscalYear);
    }
}
