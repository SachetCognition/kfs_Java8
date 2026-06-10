package org.kuali.kfs.fp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class InternalBillingItemTest extends KfsUnitTestBase {

    private InternalBillingItem item;

    @BeforeEach
    void setUp() {
        item = new InternalBillingItem();
    }

    @Test
    void defaultConstructorSetsUnitAmountToZero() {
        assertThat(item.getItemUnitAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void setAndGetDocumentNumber() {
        item.setDocumentNumber("DOC-001");
        assertThat(item.getDocumentNumber()).isEqualTo("DOC-001");
    }

    @Test
    void setAndGetItemSequenceId() {
        item.setItemSequenceId(5);
        assertThat(item.getItemSequenceId()).isEqualTo(5);
    }

    @Test
    void setAndGetItemStockNumber() {
        item.setItemStockNumber("STK-100");
        assertThat(item.getItemStockNumber()).isEqualTo("STK-100");
    }

    @Test
    void setAndGetItemStockDescription() {
        item.setItemStockDescription("Office Supplies");
        assertThat(item.getItemStockDescription()).isEqualTo("Office Supplies");
    }

    @Test
    void setAndGetItemServiceDate() {
        Timestamp ts = Timestamp.valueOf("2024-06-15 10:30:00");
        item.setItemServiceDate(ts);
        assertThat(item.getItemServiceDate()).isEqualTo(ts);
    }

    @Test
    void setAndGetItemQuantity() {
        item.setItemQuantity(10);
        assertThat(item.getItemQuantity()).isEqualTo(10);
    }

    @Test
    void setAndGetItemUnitAmount() {
        item.setItemUnitAmount(new KualiDecimal(25.50));
        assertThat(item.getItemUnitAmount()).isEqualTo(new KualiDecimal(25.50));
    }

    @Test
    void setAndGetUnitOfMeasureCode() {
        item.setUnitOfMeasureCode("EA");
        assertThat(item.getUnitOfMeasureCode()).isEqualTo("EA");
    }

    @Test
    void getTotal() {
        item.setItemQuantity(5);
        item.setItemUnitAmount(new KualiDecimal(10.00));
        assertThat(item.getTotal()).isEqualTo(new KualiDecimal(50.00));
    }

    @Test
    void getTotalWithLargeQuantity() {
        item.setItemQuantity(100);
        item.setItemUnitAmount(new KualiDecimal(25.00));
        assertThat(item.getTotal()).isEqualTo(new KualiDecimal(2500.00));
    }

    @Test
    void getTotalWithZeroQuantity() {
        item.setItemQuantity(0);
        item.setItemUnitAmount(new KualiDecimal(10.00));
        assertThat(item.getTotal()).isEqualTo(KualiDecimal.ZERO);
    }
}
