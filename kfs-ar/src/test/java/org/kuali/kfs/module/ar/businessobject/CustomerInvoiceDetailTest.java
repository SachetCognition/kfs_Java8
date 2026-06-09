package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerInvoiceDetailTest extends KfsUnitTestBase {

    private CustomerInvoiceDetail detail;

    @BeforeEach
    void setUp() {
        detail = new CustomerInvoiceDetail();
    }

    @Test
    void testInvoiceItemCode() {
        detail.setInvoiceItemCode("TUITION");
        assertThat(detail.getInvoiceItemCode()).isEqualTo("TUITION");
    }

    @Test
    void testDocumentNumber() {
        detail.setDocumentNumber("INV001");
        assertThat(detail.getDocumentNumber()).isEqualTo("INV001");
    }

    @Test
    void testInvoiceItemQuantity() {
        BigDecimal qty = new BigDecimal("5.00");
        detail.setInvoiceItemQuantity(qty);
        assertThat(detail.getInvoiceItemQuantity()).isEqualTo(qty);
    }

    @Test
    void testInvoiceItemUnitPrice() {
        BigDecimal price = new BigDecimal("100.00");
        detail.setInvoiceItemUnitPrice(price);
        assertThat(detail.getInvoiceItemUnitPrice()).isEqualByComparingTo(price);
    }

    @Test
    void testInvoiceItemDescription() {
        detail.setInvoiceItemDescription("Tuition for Fall 2024");
        assertThat(detail.getInvoiceItemDescription()).isEqualTo("Tuition for Fall 2024");
    }

    @Test
    void testInvoiceItemTaxAmount() {
        KualiDecimal taxAmount = new KualiDecimal(50);
        detail.setInvoiceItemTaxAmount(taxAmount);
        assertThat(detail.getInvoiceItemTaxAmount()).isEqualTo(taxAmount);
    }

    @Test
    void testTaxableIndicator() {
        detail.setTaxableIndicator(true);
        assertThat(detail.isTaxableIndicator()).isTrue();

        detail.setTaxableIndicator(false);
        assertThat(detail.isTaxableIndicator()).isFalse();
    }

    @Test
    void testSequenceNumber() {
        detail.setSequenceNumber(1);
        assertThat(detail.getSequenceNumber()).isEqualTo(1);
    }

    @Test
    void testAmountWithZeroQuantity() {
        detail.setInvoiceItemQuantity(BigDecimal.ZERO);
        detail.setInvoiceItemUnitPrice(BigDecimal.valueOf(100));
        assertThat(detail.getInvoiceItemPreTaxAmount()).isEqualTo(KualiDecimal.ZERO);
    }
}
