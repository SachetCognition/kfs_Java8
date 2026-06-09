package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionAppointmentFundingLoad02MoveId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String chartOfAccountsCode;
    private String accountNumber;

    public BudgetConstructionAppointmentFundingLoad02MoveId() {}

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionAppointmentFundingLoad02MoveId that = (BudgetConstructionAppointmentFundingLoad02MoveId) o;
        return Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chartOfAccountsCode, accountNumber);
    }
}
