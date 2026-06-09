package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class AutoApproveExcludeId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String chartOfAccountsCode;
    private String accountNumber;

    public AutoApproveExcludeId() {
    }

    public AutoApproveExcludeId(String chartOfAccountsCode, String accountNumber) {
        this.chartOfAccountsCode = chartOfAccountsCode;
        this.accountNumber = accountNumber;
    }

    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoApproveExcludeId that = (AutoApproveExcludeId) o;
        return Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chartOfAccountsCode, accountNumber);
    }
}
