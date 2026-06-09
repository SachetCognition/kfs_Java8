package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CustomerInvoiceItemCodeId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String chartOfAccountsCode;
    private String organizationCode;
    private String invoiceItemCode;

    public CustomerInvoiceItemCodeId() {}

    public CustomerInvoiceItemCodeId(String chartOfAccountsCode, String organizationCode, String invoiceItemCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
        this.organizationCode = organizationCode;
        this.invoiceItemCode = invoiceItemCode;
    }

    public String getChartOfAccountsCode() { return chartOfAccountsCode; }
    public void setChartOfAccountsCode(String chartOfAccountsCode) { this.chartOfAccountsCode = chartOfAccountsCode; }

    public String getOrganizationCode() { return organizationCode; }
    public void setOrganizationCode(String organizationCode) { this.organizationCode = organizationCode; }

    public String getInvoiceItemCode() { return invoiceItemCode; }
    public void setInvoiceItemCode(String invoiceItemCode) { this.invoiceItemCode = invoiceItemCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInvoiceItemCodeId that = (CustomerInvoiceItemCodeId) o;
        return Objects.equals(chartOfAccountsCode, that.chartOfAccountsCode) && Objects.equals(organizationCode, that.organizationCode) && Objects.equals(invoiceItemCode, that.invoiceItemCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chartOfAccountsCode, organizationCode, invoiceItemCode);
    }
}
