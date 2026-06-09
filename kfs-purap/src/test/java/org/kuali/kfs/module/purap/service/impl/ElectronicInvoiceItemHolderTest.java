package org.kuali.kfs.module.purap.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.businessobject.ElectronicInvoiceItem;
import org.kuali.kfs.module.purap.businessobject.ElectronicInvoiceRejectItem;
import org.kuali.kfs.module.purap.businessobject.PurchaseOrderItem;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ElectronicInvoiceItemHolderTest extends KfsUnitTestBase {

    @Test
    void constructorWithNullRejectItemThrows() {
        assertThatThrownBy(() ->
                new ElectronicInvoiceItemHolder(
                        (ElectronicInvoiceRejectItem) null, new HashMap<>(), null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("ElectronicInvoiceRejectItem should not be null");
    }

    @Test
    void constructorWithNullInvoiceItemThrows() {
        assertThatThrownBy(() ->
                new ElectronicInvoiceItemHolder(
                        (ElectronicInvoiceItem) null, new HashMap<>(), null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("ElectronicInvoiceItem should not be null");
    }

    @Test
    void rejectItemHolderIsRejectItemHolder() {
        ElectronicInvoiceRejectItem rejectItem = mock(ElectronicInvoiceRejectItem.class);
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                rejectItem, new HashMap<>(), null, null);
        assertThat(holder.isRejectItemHolder()).isTrue();
    }

    @Test
    void invoiceItemHolderIsNotRejectItemHolder() {
        ElectronicInvoiceItem invoiceItem = mock(ElectronicInvoiceItem.class);
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                invoiceItem, new HashMap<>(), null, null);
        assertThat(holder.isRejectItemHolder()).isFalse();
    }

    @Test
    void getInvoiceItemLineNumberFromRejectItem() {
        ElectronicInvoiceRejectItem rejectItem = mock(ElectronicInvoiceRejectItem.class);
        when(rejectItem.getInvoiceReferenceItemLineNumber()).thenReturn(5);
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                rejectItem, new HashMap<>(), null, null);
        assertThat(holder.getInvoiceItemLineNumber()).isEqualTo(5);
    }

    @Test
    void getInvoiceItemLineNumberFromInvoiceItem() {
        ElectronicInvoiceItem invoiceItem = mock(ElectronicInvoiceItem.class);
        when(invoiceItem.getReferenceLineNumberInteger()).thenReturn(3);
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                invoiceItem, new HashMap<>(), null, null);
        assertThat(holder.getInvoiceItemLineNumber()).isEqualTo(3);
    }

    @Test
    void getInvoiceItemDescriptionFromRejectItem() {
        ElectronicInvoiceRejectItem rejectItem = mock(ElectronicInvoiceRejectItem.class);
        when(rejectItem.getInvoiceReferenceItemDescription()).thenReturn("Test Desc");
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                rejectItem, new HashMap<>(), null, null);
        assertThat(holder.getInvoiceItemDescription()).isEqualTo("Test Desc");
    }

    @Test
    void getInvoiceItemDescriptionFromInvoiceItem() {
        ElectronicInvoiceItem invoiceItem = mock(ElectronicInvoiceItem.class);
        when(invoiceItem.getReferenceDescription()).thenReturn("Invoice Desc");
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                invoiceItem, new HashMap<>(), null, null);
        assertThat(holder.getInvoiceItemDescription()).isEqualTo("Invoice Desc");
    }

    @Test
    void getUnitPriceFromRejectItem() {
        ElectronicInvoiceRejectItem rejectItem = mock(ElectronicInvoiceRejectItem.class);
        when(rejectItem.getInvoiceItemUnitPrice()).thenReturn(new BigDecimal("25.50"));
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                rejectItem, new HashMap<>(), null, null);
        assertThat(holder.getUnitPrice()).isEqualByComparingTo(new BigDecimal("25.50"));
    }

    @Test
    void getUnitPriceFromInvoiceItem() {
        ElectronicInvoiceItem invoiceItem = mock(ElectronicInvoiceItem.class);
        when(invoiceItem.getInvoiceLineUnitCostBigDecimal()).thenReturn(new BigDecimal("10.00"));
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                invoiceItem, new HashMap<>(), null, null);
        assertThat(holder.getUnitPrice()).isEqualByComparingTo(new BigDecimal("10.00"));
    }

    @Test
    void getQuantityFromRejectItem() {
        ElectronicInvoiceRejectItem rejectItem = mock(ElectronicInvoiceRejectItem.class);
        when(rejectItem.getInvoiceItemQuantity()).thenReturn(new BigDecimal("100"));
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                rejectItem, new HashMap<>(), null, null);
        assertThat(holder.getQuantity()).isEqualByComparingTo(new BigDecimal("100"));
    }

    @Test
    void getQuantityFromInvoiceItem() {
        ElectronicInvoiceItem invoiceItem = mock(ElectronicInvoiceItem.class);
        when(invoiceItem.getInvoiceLineQuantityBigDecimal()).thenReturn(new BigDecimal("50"));
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                invoiceItem, new HashMap<>(), null, null);
        assertThat(holder.getQuantity()).isEqualByComparingTo(new BigDecimal("50"));
    }

    @Test
    void getPurchaseOrderItem() {
        ElectronicInvoiceItem invoiceItem = mock(ElectronicInvoiceItem.class);
        PurchaseOrderItem poItem = new PurchaseOrderItem();
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                invoiceItem, new HashMap<>(), poItem, null);
        assertThat(holder.getPurchaseOrderItem()).isSameAs(poItem);
    }

    @Test
    void getCatalogNumberStrippedFromRejectItem() {
        ElectronicInvoiceRejectItem rejectItem = mock(ElectronicInvoiceRejectItem.class);
        when(rejectItem.getInvoiceItemCatalogNumber()).thenReturn("CAT-123!@#");
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                rejectItem, new HashMap<>(), null, null);
        assertThat(holder.getCatalogNumberStripped()).isEqualTo("CAT123");
    }

    @Test
    void getCatalogNumberStrippedFromInvoiceItem() {
        ElectronicInvoiceItem invoiceItem = mock(ElectronicInvoiceItem.class);
        when(invoiceItem.getCatalogNumber()).thenReturn("PART-456$%");
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                invoiceItem, new HashMap<>(), null, null);
        assertThat(holder.getCatalogNumberStripped()).isEqualTo("PART456");
    }

    @Test
    void getUnitPriceCurrencyFromRejectItem() {
        ElectronicInvoiceRejectItem rejectItem = mock(ElectronicInvoiceRejectItem.class);
        when(rejectItem.getInvoiceItemUnitPriceCurrencyCode()).thenReturn("USD");
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                rejectItem, new HashMap<>(), null, null);
        assertThat(holder.getUnitPriceCurrency()).isEqualTo("USD");
    }

    @Test
    void getUnitPriceCurrencyFromInvoiceItem() {
        ElectronicInvoiceItem invoiceItem = mock(ElectronicInvoiceItem.class);
        when(invoiceItem.getUnitPriceCurrency()).thenReturn("EUR");
        ElectronicInvoiceItemHolder holder = new ElectronicInvoiceItemHolder(
                invoiceItem, new HashMap<>(), null, null);
        assertThat(holder.getUnitPriceCurrency()).isEqualTo("EUR");
    }
}
