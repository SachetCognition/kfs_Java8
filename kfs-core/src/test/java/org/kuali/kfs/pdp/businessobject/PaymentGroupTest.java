package org.kuali.kfs.pdp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.core.api.util.type.KualiInteger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentGroupTest extends KfsUnitTestBase {

    private PaymentGroup paymentGroup;

    @BeforeEach
    void setUp() {
        paymentGroup = new PaymentGroup();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(paymentGroup.getPaymentDetails()).isNotNull().isEmpty();
        assertThat(paymentGroup.getPaymentGroupHistory()).isNotNull().isEmpty();
    }

    @Test
    void testIsDailyReportSpecialHandling_trueWhenSpecialHandlingAndNotImmediate() {
        paymentGroup.setPymtSpecialHandling(true);
        paymentGroup.setProcessImmediate(false);
        assertThat(paymentGroup.isDailyReportSpecialHandling()).isTrue();
    }

    @Test
    void testIsDailyReportSpecialHandling_falseWhenProcessImmediate() {
        paymentGroup.setPymtSpecialHandling(true);
        paymentGroup.setProcessImmediate(true);
        assertThat(paymentGroup.isDailyReportSpecialHandling()).isFalse();
    }

    @Test
    void testIsDailyReportSpecialHandling_falseWhenNotSpecialHandling() {
        paymentGroup.setPymtSpecialHandling(false);
        paymentGroup.setProcessImmediate(false);
        assertThat(paymentGroup.isDailyReportSpecialHandling()).isFalse();
    }

    @Test
    void testIsDailyReportAttachment_trueWhenAttachmentNotSpecialNotImmediate() {
        paymentGroup.setPymtAttachment(true);
        paymentGroup.setPymtSpecialHandling(false);
        paymentGroup.setProcessImmediate(false);
        assertThat(paymentGroup.isDailyReportAttachment()).isTrue();
    }

    @Test
    void testIsDailyReportAttachment_falseWhenSpecialHandling() {
        paymentGroup.setPymtAttachment(true);
        paymentGroup.setPymtSpecialHandling(true);
        paymentGroup.setProcessImmediate(false);
        assertThat(paymentGroup.isDailyReportAttachment()).isFalse();
    }

    @Test
    void testIsDailyReportAttachment_falseWhenProcessImmediate() {
        paymentGroup.setPymtAttachment(true);
        paymentGroup.setPymtSpecialHandling(false);
        paymentGroup.setProcessImmediate(true);
        assertThat(paymentGroup.isDailyReportAttachment()).isFalse();
    }

    @Test
    void testIsDailyReportAttachment_falseWhenNoAttachment() {
        paymentGroup.setPymtAttachment(false);
        paymentGroup.setPymtSpecialHandling(false);
        paymentGroup.setProcessImmediate(false);
        assertThat(paymentGroup.isDailyReportAttachment()).isFalse();
    }

    @Test
    void testGetNetPaymentAmount_emptyDetails() {
        assertThat(paymentGroup.getNetPaymentAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testGetNetPaymentAmount_sumsAllDetails() {
        PaymentDetail detail1 = new PaymentDetail();
        detail1.setNetPaymentAmount(new KualiDecimal(100.00));
        paymentGroup.addPaymentDetails(detail1);

        PaymentDetail detail2 = new PaymentDetail();
        detail2.setNetPaymentAmount(new KualiDecimal(250.50));
        paymentGroup.addPaymentDetails(detail2);

        assertThat(paymentGroup.getNetPaymentAmount()).isEqualTo(new KualiDecimal(350.50));
    }

    @Test
    void testGetNoteLines_emptyDetails() {
        assertThat(paymentGroup.getNoteLines()).isZero();
    }

    @Test
    void testGetNoteLines_countsInvoicesAndNotes() {
        PaymentDetail detail1 = new PaymentDetail();
        PaymentNoteText note1 = new PaymentNoteText();
        note1.setCustomerNoteText("Note 1");
        detail1.getNotes().add(note1);
        PaymentNoteText note2 = new PaymentNoteText();
        note2.setCustomerNoteText("Note 2");
        detail1.getNotes().add(note2);
        paymentGroup.addPaymentDetails(detail1);

        // 1 (for invoice) + 2 notes = 3
        assertThat(paymentGroup.getNoteLines()).isEqualTo(3);
    }

    @Test
    void testGetNoteLines_multipleDetails() {
        PaymentDetail detail1 = new PaymentDetail();
        PaymentNoteText note1 = new PaymentNoteText();
        note1.setCustomerNoteText("Note A");
        detail1.getNotes().add(note1);

        PaymentDetail detail2 = new PaymentDetail();
        // no notes for detail2

        paymentGroup.addPaymentDetails(detail1);
        paymentGroup.addPaymentDetails(detail2);

        // detail1: 1 + 1 = 2, detail2: 1 + 0 = 1 -> total 3
        assertThat(paymentGroup.getNoteLines()).isEqualTo(3);
    }

    @Test
    void testAddAndDeletePaymentDetails() {
        PaymentDetail detail = new PaymentDetail();
        paymentGroup.addPaymentDetails(detail);
        assertThat(paymentGroup.getPaymentDetails()).hasSize(1);

        paymentGroup.deletePaymentDetails(detail);
        assertThat(paymentGroup.getPaymentDetails()).isEmpty();
    }

    @Test
    void testAddAndDeletePaymentGroupHistory() {
        PaymentGroupHistory history = new PaymentGroupHistory();
        paymentGroup.addPaymentGroupHistory(history);
        assertThat(paymentGroup.getPaymentGroupHistory()).hasSize(1);

        paymentGroup.deletePaymentGroupHistory(history);
        assertThat(paymentGroup.getPaymentGroupHistory()).isEmpty();
    }

    @Test
    void testGetPaymentStatusCode() {
        paymentGroup.setPaymentStatusCode("OPEN");
        assertThat(paymentGroup.getPaymentStatusCode()).isEqualTo("OPEN");
    }

    @Test
    void testGetSortValue_defaultNull() {
        assertThat(paymentGroup.getSortValue()).isNull();
    }

    @Test
    void testGettersAndSetters_addressFields() {
        paymentGroup.setPayeeName("Test Payee");
        paymentGroup.setPayeeId("P001");
        paymentGroup.setPayeeIdTypeCd("V");
        paymentGroup.setLine1Address("123 Main St");
        paymentGroup.setLine2Address("Suite 100");
        paymentGroup.setLine3Address("Building A");
        paymentGroup.setLine4Address("Floor 3");
        paymentGroup.setCity("Anytown");
        paymentGroup.setState("IN");
        paymentGroup.setCountry("US");
        paymentGroup.setZipCd("46202");

        assertThat(paymentGroup.getPayeeName()).isEqualTo("Test Payee");
        assertThat(paymentGroup.getPayeeId()).isEqualTo("P001");
        assertThat(paymentGroup.getPayeeIdTypeCd()).isEqualTo("V");
        assertThat(paymentGroup.getLine1Address()).isEqualTo("123 Main St");
        assertThat(paymentGroup.getLine2Address()).isEqualTo("Suite 100");
        assertThat(paymentGroup.getLine3Address()).isEqualTo("Building A");
        assertThat(paymentGroup.getLine4Address()).isEqualTo("Floor 3");
        assertThat(paymentGroup.getCity()).isEqualTo("Anytown");
        assertThat(paymentGroup.getState()).isEqualTo("IN");
        assertThat(paymentGroup.getCountry()).isEqualTo("US");
        assertThat(paymentGroup.getZipCd()).isEqualTo("46202");
    }

    @Test
    void testGettersAndSetters_paymentProperties() {
        paymentGroup.setCombineGroups(true);
        paymentGroup.setCampusAddress(true);
        paymentGroup.setPymtAttachment(true);
        paymentGroup.setPymtSpecialHandling(false);
        paymentGroup.setTaxablePayment(true);
        paymentGroup.setNraPayment(false);
        paymentGroup.setProcessImmediate(true);
        paymentGroup.setEmployeeIndicator(true);

        assertThat(paymentGroup.getCombineGroups()).isTrue();
        assertThat(paymentGroup.getCampusAddress()).isTrue();
        assertThat(paymentGroup.getPymtAttachment()).isTrue();
        assertThat(paymentGroup.getPymtSpecialHandling()).isFalse();
        assertThat(paymentGroup.getTaxablePayment()).isTrue();
        assertThat(paymentGroup.getNraPayment()).isFalse();
        assertThat(paymentGroup.getProcessImmediate()).isTrue();
        assertThat(paymentGroup.getEmployeeIndicator()).isTrue();
    }

    @Test
    void testGettersAndSetters_disbursementFields() {
        KualiInteger disbNbr = new KualiInteger(12345);
        Date disbDate = new Date(System.currentTimeMillis());
        paymentGroup.setDisbursementNbr(disbNbr);
        paymentGroup.setDisbursementDate(disbDate);
        paymentGroup.setDisbursementTypeCode("CHCK");
        paymentGroup.setPhysCampusProcessCd("BL");

        assertThat(paymentGroup.getDisbursementNbr()).isEqualTo(disbNbr);
        assertThat(paymentGroup.getDisbursementDate()).isEqualTo(disbDate);
        assertThat(paymentGroup.getDisbursementTypeCode()).isEqualTo("CHCK");
        assertThat(paymentGroup.getPhysCampusProcessCd()).isEqualTo("BL");
    }

    @Test
    void testGettersAndSetters_achFields() {
        paymentGroup.setAchBankRoutingNbr("123456789");
        paymentGroup.setAdviceEmailAddress("test@example.com");
        paymentGroup.setAchAccountType("22");

        assertThat(paymentGroup.getAchBankRoutingNbr()).isEqualTo("123456789");
        assertThat(paymentGroup.getAdviceEmailAddress()).isEqualTo("test@example.com");
        assertThat(paymentGroup.getAchAccountType()).isEqualTo("22");
    }

    @Test
    void testGettersAndSetters_creditMemoFields() {
        paymentGroup.setCreditMemoNbr("CM001");
        paymentGroup.setCreditMemoAmount(new KualiDecimal(500.00));

        assertThat(paymentGroup.getCreditMemoNbr()).isEqualTo("CM001");
        assertThat(paymentGroup.getCreditMemoAmount()).isEqualTo(new KualiDecimal(500.00));
    }

    @Test
    void testSetAndGetBatch() {
        Batch batch = new Batch();
        KualiInteger batchId = new KualiInteger(99);
        paymentGroup.setBatch(batch);
        paymentGroup.setBatchId(batchId);

        assertThat(paymentGroup.getBatch()).isSameAs(batch);
        assertThat(paymentGroup.getBatchId()).isEqualTo(batchId);
    }

    @Test
    void testSetAndGetProcess() {
        PaymentProcess process = new PaymentProcess();
        KualiInteger processId = new KualiInteger(42);
        paymentGroup.setProcess(process);
        paymentGroup.setProcessId(processId);

        assertThat(paymentGroup.getProcess()).isSameAs(process);
        assertThat(paymentGroup.getProcessId()).isEqualTo(processId);
    }

    @Test
    void testSetAndGetPaymentStatus() {
        PaymentStatus status = new PaymentStatus();
        paymentGroup.setPaymentStatus(status);
        assertThat(paymentGroup.getPaymentStatus()).isSameAs(status);
    }

    @Test
    void testSetAndGetDisbursementType() {
        DisbursementType disbType = new DisbursementType();
        paymentGroup.setDisbursementType(disbType);
        assertThat(paymentGroup.getDisbursementType()).isSameAs(disbType);
    }

    @Test
    void testSetAndGetBank() {
        paymentGroup.setBankCode("BOFA");
        assertThat(paymentGroup.getBankCode()).isEqualTo("BOFA");
    }

    @Test
    void testSetAndGetId() {
        KualiInteger id = new KualiInteger(1001);
        paymentGroup.setId(id);
        assertThat(paymentGroup.getId()).isEqualTo(id);
    }

    @Test
    void testSetAndGetAchAccountNumber() {
        AchAccountNumber achAcct = new AchAccountNumber();
        paymentGroup.setAchAccountNumber(achAcct);
        assertThat(paymentGroup.getAchAccountNumber()).isSameAs(achAcct);
    }

    @Test
    void testAlternatePayeeFields() {
        paymentGroup.setAlternatePayeeId("ALT001");
        paymentGroup.setAlternatePayeeIdTypeCd("V");

        assertThat(paymentGroup.getAlternatePayeeId()).isEqualTo("ALT001");
        assertThat(paymentGroup.getAlternatePayeeIdTypeCd()).isEqualTo("V");
    }

    @Test
    void testPayeeOwnerCd() {
        paymentGroup.setPayeeOwnerCd("OWN");
        assertThat(paymentGroup.getPayeeOwnerCd()).isEqualTo("OWN");
    }

    @Test
    void testPaymentDate() {
        Date paymentDate = new Date(System.currentTimeMillis());
        paymentGroup.setPaymentDate(paymentDate);
        assertThat(paymentGroup.getPaymentDate()).isEqualTo(paymentDate);
    }

    @Test
    void testSetPaymentDetails_replaces() {
        PaymentDetail d1 = new PaymentDetail();
        PaymentDetail d2 = new PaymentDetail();
        List<PaymentDetail> details = new ArrayList<>();
        details.add(d1);
        details.add(d2);
        paymentGroup.setPaymentDetails(details);

        assertThat(paymentGroup.getPaymentDetails()).hasSize(2).containsExactly(d1, d2);
    }

    @Test
    void testSetPaymentGroupHistory_replaces() {
        PaymentGroupHistory h1 = new PaymentGroupHistory();
        List<PaymentGroupHistory> histList = new ArrayList<>();
        histList.add(h1);
        paymentGroup.setPaymentGroupHistory(histList);

        assertThat(paymentGroup.getPaymentGroupHistory()).hasSize(1).containsExactly(h1);
    }
}
