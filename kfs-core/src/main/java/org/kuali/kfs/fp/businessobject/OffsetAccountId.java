package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class OffsetAccountId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String chartOfAccountsCode;
    private String accountNumber;
    private String financialOffsetObjectCode;

    public OffsetAccountId() {}

    public OffsetAccountId(String chartOfAccountsCode, String accountNumber, String financialOffsetObjectCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
        this.accountNumber = accountNumber;
        this.financialOffsetObjectCode = financialOffsetObjectCode;
    }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getFinancialOffsetObjectCode() { return financialOffsetObjectCode; }
    public void setFinancialOffsetObjectCode(String financialOffsetObjectCode) { this.financialOffsetObjectCode = financialOffsetObjectCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffsetAccountId that = (OffsetAccountId) o;
        return Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(financialOffsetObjectCode, that.financialOffsetObjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chartOfAccountsCode, accountNumber, financialOffsetObjectCode);
    }
}
