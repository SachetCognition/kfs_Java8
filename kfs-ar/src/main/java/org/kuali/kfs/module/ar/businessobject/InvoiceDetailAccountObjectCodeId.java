package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class InvoiceDetailAccountObjectCodeId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Long proposalNumber;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String financialObjectCode;

    public InvoiceDetailAccountObjectCodeId() {}

    public InvoiceDetailAccountObjectCodeId(String documentNumber, Long proposalNumber, String chartOfAccountsCode, String accountNumber, String financialObjectCode) {
        this.documentNumber = documentNumber;
        this.proposalNumber = proposalNumber;
        this.chartOfAccountsCode = chartOfAccountsCode;
        this.accountNumber = accountNumber;
        this.financialObjectCode = financialObjectCode;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

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
        InvoiceDetailAccountObjectCodeId that = (InvoiceDetailAccountObjectCodeId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(proposalNumber, that.proposalNumber) && Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(financialObjectCode, that.financialObjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, proposalNumber, chartOfAccountsCode, accountNumber, financialObjectCode);
    }
}
