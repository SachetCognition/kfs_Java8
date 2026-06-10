package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LockboxTest extends KfsUnitTestBase {

    private Lockbox lockbox;

    @BeforeEach
    void setUp() {
        lockbox = new Lockbox();
    }

    @Test
    void testLockboxNumber() {
        lockbox.setLockboxNumber("LB001");
        assertThat(lockbox.getLockboxNumber()).isEqualTo("LB001");
    }

    @Test
    void testInvoiceSequenceNumber() {
        lockbox.setInvoiceSequenceNumber(1L);
        assertThat(lockbox.getInvoiceSequenceNumber()).isEqualTo(1L);
    }

    @Test
    void testFinancialDocumentReferenceInvoiceNumber() {
        lockbox.setFinancialDocumentReferenceInvoiceNumber("INV001");
        assertThat(lockbox.getFinancialDocumentReferenceInvoiceNumber()).isEqualTo("INV001");
    }

    @Test
    void testCustomerNumber() {
        lockbox.setCustomerNumber("CUST001");
        assertThat(lockbox.getCustomerNumber()).isEqualTo("CUST001");
    }

    @Test
    void testInvoiceTotalAmount() {
        KualiDecimal amount = new KualiDecimal(1000);
        lockbox.setInvoiceTotalAmount(amount);
        assertThat(lockbox.getInvoiceTotalAmount()).isEqualTo(amount);
    }

    @Test
    void testInvoicePaidOrAppliedAmount() {
        KualiDecimal amount = new KualiDecimal(750);
        lockbox.setInvoicePaidOrAppliedAmount(amount);
        assertThat(lockbox.getInvoicePaidOrAppliedAmount()).isEqualTo(amount);
    }

    @Test
    void testProcessedInvoiceDate() {
        Date date = Date.valueOf("2024-03-15");
        lockbox.setProcessedInvoiceDate(date);
        assertThat(lockbox.getProcessedInvoiceDate()).isEqualTo(date);
    }

    @Test
    void testBatchSequenceNumber() {
        lockbox.setBatchSequenceNumber(5);
        assertThat(lockbox.getBatchSequenceNumber()).isEqualTo(5);
    }
}
