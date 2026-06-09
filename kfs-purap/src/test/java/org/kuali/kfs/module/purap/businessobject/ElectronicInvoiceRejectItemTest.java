package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ElectronicInvoiceRejectItemTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        ElectronicInvoiceRejectItem item = new ElectronicInvoiceRejectItem();
        assertThat(item.getInvoiceRejectItemIdentifier()).isNull();
        assertThat(item.getPurapDocumentIdentifier()).isNull();
        assertThat(item.getInvoiceItemLineNumber()).isNull();
    }

    @Test
    void settersAndGetters() {
        ElectronicInvoiceRejectItem item = new ElectronicInvoiceRejectItem();
        item.setInvoiceRejectItemIdentifier(1);
        item.setPurapDocumentIdentifier(100);
        item.setInvoiceItemLineNumber(1);
        item.setInvoiceItemQuantity(new BigDecimal("10"));
        item.setInvoiceItemUnitOfMeasureCode("EA");
        item.setInvoiceItemCatalogNumber("CAT-001");
        item.setInvoiceItemUnitPrice(new BigDecimal("25.50"));
        item.setInvoiceItemSubTotalAmount(new BigDecimal("255.00"));
        item.setInvoiceItemUnitPriceCurrencyCode("USD");
        item.setInvoiceItemSubTotalCurrencyCode("USD");

        assertThat(item.getInvoiceRejectItemIdentifier()).isEqualTo(1);
        assertThat(item.getPurapDocumentIdentifier()).isEqualTo(100);
        assertThat(item.getInvoiceItemLineNumber()).isEqualTo(1);
        assertThat(item.getInvoiceItemQuantity()).isEqualByComparingTo(new BigDecimal("10"));
        assertThat(item.getInvoiceItemUnitOfMeasureCode()).isEqualTo("EA");
        assertThat(item.getInvoiceItemCatalogNumber()).isEqualTo("CAT-001");
        assertThat(item.getInvoiceItemUnitPrice()).isEqualByComparingTo(new BigDecimal("25.50"));
        assertThat(item.getInvoiceItemSubTotalAmount()).isEqualByComparingTo(new BigDecimal("255.00"));
        assertThat(item.getInvoiceItemUnitPriceCurrencyCode()).isEqualTo("USD");
        assertThat(item.getInvoiceItemSubTotalCurrencyCode()).isEqualTo("USD");
    }

    @Test
    void referenceFields() {
        ElectronicInvoiceRejectItem item = new ElectronicInvoiceRejectItem();
        item.setInvoiceReferenceItemLineNumber(5);
        item.setInvoiceReferenceItemSerialNumber("SN001");
        item.setInvoiceReferenceItemSupplierPartIdentifier("SP001");
        item.setInvoiceReferenceItemSupplierPartAuxiliaryIdentifier("AUX01");
        item.setInvoiceReferenceItemDescription("Widget");
        item.setInvoiceReferenceItemManufacturerPartIdentifier("MFG01");
        item.setInvoiceReferenceItemManufacturerName("ACME");

        assertThat(item.getInvoiceReferenceItemLineNumber()).isEqualTo(5);
        assertThat(item.getInvoiceReferenceItemSerialNumber()).isEqualTo("SN001");
        assertThat(item.getInvoiceReferenceItemSupplierPartIdentifier()).isEqualTo("SP001");
        assertThat(item.getInvoiceReferenceItemSupplierPartAuxiliaryIdentifier()).isEqualTo("AUX01");
        assertThat(item.getInvoiceReferenceItemDescription()).isEqualTo("Widget");
        assertThat(item.getInvoiceReferenceItemManufacturerPartIdentifier()).isEqualTo("MFG01");
        assertThat(item.getInvoiceReferenceItemManufacturerName()).isEqualTo("ACME");
    }

    @Test
    void acceptIndicators() {
        ElectronicInvoiceRejectItem item = new ElectronicInvoiceRejectItem();
        assertThat(item.isUnitOfMeasureAcceptIndicator()).isFalse();
        assertThat(item.isCatalogNumberAcceptIndicator()).isFalse();

        item.setUnitOfMeasureAcceptIndicator(true);
        item.setCatalogNumberAcceptIndicator(true);
        assertThat(item.isUnitOfMeasureAcceptIndicator()).isTrue();
        assertThat(item.isCatalogNumberAcceptIndicator()).isTrue();
    }

    @Test
    void shippingAndTaxFields() {
        ElectronicInvoiceRejectItem item = new ElectronicInvoiceRejectItem();
        item.setInvoiceItemQuantity(new BigDecimal("10"));
        item.setInvoiceItemUnitPrice(new BigDecimal("25.50"));
        item.setInvoiceItemShippingAmount(new BigDecimal("15.00"));
        item.setInvoiceItemShippingDescription("Ground shipping");
        item.setInvoiceItemShippingCurrencyCode("USD");
        item.setInvoiceItemTaxAmount(new BigDecimal("5.50"));
        item.setInvoiceItemTaxDescription("State tax");
        item.setInvoiceItemTaxCurrencyCode("USD");
        item.setInvoiceItemSpecialHandlingAmount(new BigDecimal("3.00"));
        item.setInvoiceItemSpecialHandlingCurrencyCode("USD");
        item.setInvoiceItemDiscountAmount(new BigDecimal("10.00"));
        item.setInvoiceItemDiscountCurrencyCode("USD");
        item.setInvoiceItemNetCurrencyCode("USD");

        assertThat(item.getInvoiceItemShippingAmount()).isEqualByComparingTo(new BigDecimal("15.00"));
        assertThat(item.getInvoiceItemShippingDescription()).isEqualTo("Ground shipping");
        assertThat(item.getInvoiceItemTaxAmount()).isEqualByComparingTo(new BigDecimal("5.50"));
        assertThat(item.getInvoiceItemTaxDescription()).isEqualTo("State tax");
        assertThat(item.getInvoiceItemSpecialHandlingAmount()).isEqualByComparingTo(new BigDecimal("3.00"));
        assertThat(item.getInvoiceItemNetAmount()).isNotNull();
        assertThat(item.getInvoiceItemGrossAmount()).isNotNull();
    }
}
