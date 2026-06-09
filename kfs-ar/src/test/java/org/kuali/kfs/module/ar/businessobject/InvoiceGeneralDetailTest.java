package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class InvoiceGeneralDetailTest extends KfsUnitTestBase {

    private InvoiceGeneralDetail detail;

    @BeforeEach
    void setUp() {
        detail = new InvoiceGeneralDetail();
    }

    @Test
    void testDocumentNumber() {
        detail.setDocumentNumber("DOC001");
        assertThat(detail.getDocumentNumber()).isEqualTo("DOC001");
    }

    @Test
    void testAwardDateRange() {
        detail.setAwardDateRange("01/01/2024 - 12/31/2024");
        assertThat(detail.getAwardDateRange()).isEqualTo("01/01/2024 - 12/31/2024");
    }

    @Test
    void testBillingFrequencyCode() {
        detail.setBillingFrequencyCode("MNTH");
        assertThat(detail.getBillingFrequencyCode()).isEqualTo("MNTH");
    }

    @Test
    void testFinalBillIndicator() {
        detail.setFinalBillIndicator(false);
        assertThat(detail.isFinalBillIndicator()).isFalse();

        detail.setFinalBillIndicator(true);
        assertThat(detail.isFinalBillIndicator()).isTrue();
    }

    @Test
    void testBillingPeriod() {
        detail.setBillingPeriod("January 2024");
        assertThat(detail.getBillingPeriod()).isEqualTo("January 2024");
    }

    @Test
    void testInstrumentTypeCode() {
        detail.setInstrumentTypeCode("GRT");
        assertThat(detail.getInstrumentTypeCode()).isEqualTo("GRT");
    }

    @Test
    void testAwardTotal() {
        KualiDecimal total = new KualiDecimal(100000);
        detail.setAwardTotal(total);
        assertThat(detail.getAwardTotal()).isEqualTo(total);
    }

    @Test
    void testTotalAmountBilledToDate() {
        KualiDecimal amount = new KualiDecimal(25000);
        detail.setTotalAmountBilledToDate(amount);
        assertThat(detail.getTotalAmountBilledToDate()).isEqualTo(amount);
    }

    @Test
    void testTotalPreviouslyBilled() {
        KualiDecimal amount = new KualiDecimal(15000);
        detail.setTotalPreviouslyBilled(amount);
        assertThat(detail.getTotalPreviouslyBilled()).isEqualTo(amount);
    }

    @Test
    void testCostShareAmount() {
        KualiDecimal amount = new KualiDecimal(5000);
        detail.setCostShareAmount(amount);
        assertThat(detail.getCostShareAmount()).isEqualTo(amount);
    }

    @Test
    void testLastBilledDate() {
        Date date = Date.valueOf("2024-03-31");
        detail.setLastBilledDate(date);
        assertThat(detail.getLastBilledDate()).isEqualTo(date);
    }

    @Test
    void testProposalNumber() {
        detail.setProposalNumber(12345L);
        assertThat(detail.getProposalNumber()).isEqualTo(12345L);
    }
}
