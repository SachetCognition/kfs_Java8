package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class AwardAccountObjectCodeTotalBilledId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long proposalNumber;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String financialObjectCode;

    public AwardAccountObjectCodeTotalBilledId() {}

    public AwardAccountObjectCodeTotalBilledId(Long proposalNumber, String chartOfAccountsCode, String accountNumber, String financialObjectCode) {
        this.proposalNumber = proposalNumber;
        this.chartOfAccountsCode = chartOfAccountsCode;
        this.accountNumber = accountNumber;
        this.financialObjectCode = financialObjectCode;
    }

    public Long getProposalNumber() { return proposalNumber; }
    public void setProposalNumber(Long proposalNumber) { this.proposalNumber = proposalNumber; }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getFinancialObjectCode() { return financialObjectCode; }
    public void setFinancialObjectCode(String financialObjectCode) { this.financialObjectCode = financialObjectCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardAccountObjectCodeTotalBilledId that = (AwardAccountObjectCodeTotalBilledId) o;
        return Objects.equals(proposalNumber, that.proposalNumber) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(financialObjectCode, that.financialObjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proposalNumber, chartOfAccountsCode, accountNumber, financialObjectCode);
    }
}
