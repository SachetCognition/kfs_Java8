package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class InvoicePaidAppliedTest extends KfsUnitTestBase {

    private InvoicePaidApplied invoicePaidApplied;

    @BeforeEach
    void setUp() {
        invoicePaidApplied = new InvoicePaidApplied();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(invoicePaidApplied.getInvoiceItemAppliedAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testParameterizedConstructor() {
        InvoicePaidApplied ipa = new InvoicePaidApplied(
                "DOC001", "INV001", 1, new KualiDecimal(100), 1, 2024, "01");

        assertThat(ipa.getDocumentNumber()).isEqualTo("DOC001");
        assertThat(ipa.getFinancialDocumentReferenceInvoiceNumber()).isEqualTo("INV001");
        assertThat(ipa.getInvoiceItemNumber()).isEqualTo(1);
        assertThat(ipa.getInvoiceItemAppliedAmount()).isEqualTo(new KualiDecimal(100));
        assertThat(ipa.getPaidAppliedItemNumber()).isEqualTo(1);
        assertThat(ipa.getUniversityFiscalYear()).isEqualTo(2024);
        assertThat(ipa.getUniversityFiscalPeriodCode()).isEqualTo("01");
    }

    @Test
    void testDocumentNumber() {
        invoicePaidApplied.setDocumentNumber("DOC123");
        assertThat(invoicePaidApplied.getDocumentNumber()).isEqualTo("DOC123");
    }

    @Test
    void testPaidAppliedItemNumber() {
        invoicePaidApplied.setPaidAppliedItemNumber(5);
        assertThat(invoicePaidApplied.getPaidAppliedItemNumber()).isEqualTo(5);
    }

    @Test
    void testFinancialDocumentReferenceInvoiceNumber() {
        invoicePaidApplied.setFinancialDocumentReferenceInvoiceNumber("INV999");
        assertThat(invoicePaidApplied.getFinancialDocumentReferenceInvoiceNumber()).isEqualTo("INV999");
    }

    @Test
    void testInvoiceItemNumber() {
        invoicePaidApplied.setInvoiceItemNumber(3);
        assertThat(invoicePaidApplied.getInvoiceItemNumber()).isEqualTo(3);
    }

    @Test
    void testUniversityFiscalYear() {
        invoicePaidApplied.setUniversityFiscalYear(2024);
        assertThat(invoicePaidApplied.getUniversityFiscalYear()).isEqualTo(2024);
    }

    @Test
    void testUniversityFiscalPeriodCode() {
        invoicePaidApplied.setUniversityFiscalPeriodCode("06");
        assertThat(invoicePaidApplied.getUniversityFiscalPeriodCode()).isEqualTo("06");
    }

    @Test
    void testInvoiceItemAppliedAmount() {
        KualiDecimal amount = new KualiDecimal(250.50);
        invoicePaidApplied.setInvoiceItemAppliedAmount(amount);
        assertThat(invoicePaidApplied.getInvoiceItemAppliedAmount()).isEqualTo(amount);
    }

    @Test
    void testPaidAppliedDistributionAmount() {
        KualiDecimal amount = new KualiDecimal(75.25);
        invoicePaidApplied.setPaidAppiedDistributionAmount(amount);
        assertThat(invoicePaidApplied.getPaidAppiedDistributionAmount()).isEqualTo(amount);
    }
}
