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

class PaymentDetailTest extends KfsUnitTestBase {

    private PaymentDetail paymentDetail;

    @BeforeEach
    void setUp() {
        paymentDetail = new PaymentDetail();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(paymentDetail.getAccountDetail()).isNotNull().isEmpty();
        assertThat(paymentDetail.getNotes()).isNotNull().isEmpty();
    }

    @Test
    void testIsDetailAmountProvided_allNull() {
        assertThat(paymentDetail.isDetailAmountProvided()).isFalse();
    }

    @Test
    void testIsDetailAmountProvided_origInvoiceAmountSet() {
        paymentDetail.setOrigInvoiceAmount(new KualiDecimal(100));
        assertThat(paymentDetail.isDetailAmountProvided()).isTrue();
    }

    @Test
    void testIsDetailAmountProvided_discountAmountSet() {
        paymentDetail.setInvTotDiscountAmount(new KualiDecimal(10));
        assertThat(paymentDetail.isDetailAmountProvided()).isTrue();
    }

    @Test
    void testIsDetailAmountProvided_shipAmountSet() {
        paymentDetail.setInvTotShipAmount(new KualiDecimal(5));
        assertThat(paymentDetail.isDetailAmountProvided()).isTrue();
    }

    @Test
    void testIsDetailAmountProvided_otherDebitSet() {
        paymentDetail.setInvTotOtherDebitAmount(new KualiDecimal(3));
        assertThat(paymentDetail.isDetailAmountProvided()).isTrue();
    }

    @Test
    void testIsDetailAmountProvided_otherCreditSet() {
        paymentDetail.setInvTotOtherCreditAmount(new KualiDecimal(2));
        assertThat(paymentDetail.isDetailAmountProvided()).isTrue();
    }

    @Test
    void testGetCalculatedPaymentAmount_allNull() {
        assertThat(paymentDetail.getCalculatedPaymentAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testGetCalculatedPaymentAmount_withAllAmounts() {
        paymentDetail.setOrigInvoiceAmount(new KualiDecimal(1000));
        paymentDetail.setInvTotDiscountAmount(new KualiDecimal(100));
        paymentDetail.setInvTotShipAmount(new KualiDecimal(50));
        paymentDetail.setInvTotOtherDebitAmount(new KualiDecimal(25));
        paymentDetail.setInvTotOtherCreditAmount(new KualiDecimal(10));

        // 1000 - 100 + 50 + 25 - 10 = 965
        assertThat(paymentDetail.getCalculatedPaymentAmount()).isEqualTo(new KualiDecimal(965));
    }

    @Test
    void testGetCalculatedPaymentAmount_onlyOrigInvoice() {
        paymentDetail.setOrigInvoiceAmount(new KualiDecimal(500));
        assertThat(paymentDetail.getCalculatedPaymentAmount()).isEqualTo(new KualiDecimal(500));
    }

    @Test
    void testGetCalculatedPaymentAmount_nullOrigInvoice() {
        paymentDetail.setInvTotDiscountAmount(new KualiDecimal(50));
        paymentDetail.setInvTotShipAmount(new KualiDecimal(10));

        // 0 - 50 + 10 + 0 - 0 = -40
        assertThat(paymentDetail.getCalculatedPaymentAmount()).isEqualTo(new KualiDecimal(-40));
    }

    @Test
    void testGetAccountTotal_emptyAccountDetails() {
        assertThat(paymentDetail.getAccountTotal()).isEqualTo(new KualiDecimal(0));
    }

    @Test
    void testGetAccountTotal_withAccountDetails() {
        PaymentAccountDetail acct1 = new PaymentAccountDetail();
        acct1.setAccountNetAmount(new KualiDecimal(200));
        PaymentAccountDetail acct2 = new PaymentAccountDetail();
        acct2.setAccountNetAmount(new KualiDecimal(300));
        PaymentAccountDetail acct3 = new PaymentAccountDetail();
        // null amount

        paymentDetail.getAccountDetail().add(acct1);
        paymentDetail.getAccountDetail().add(acct2);
        paymentDetail.getAccountDetail().add(acct3);

        assertThat(paymentDetail.getAccountTotal()).isEqualTo(new KualiDecimal(500));
    }

    @Test
    void testAddAccountDetail_setsPaymentDetail() {
        PaymentAccountDetail acctDetail = new PaymentAccountDetail();
        paymentDetail.addAccountDetail(acctDetail);

        assertThat(paymentDetail.getAccountDetail()).hasSize(1);
        assertThat(acctDetail.getPaymentDetail()).isSameAs(paymentDetail);
    }

    @Test
    void testDeleteAccountDetail() {
        PaymentAccountDetail acctDetail = new PaymentAccountDetail();
        paymentDetail.getAccountDetail().add(acctDetail);
        paymentDetail.deleteAccountDetail(acctDetail);
        assertThat(paymentDetail.getAccountDetail()).isEmpty();
    }

    @Test
    void testAddNote_nonBlank() {
        PaymentNoteText note = new PaymentNoteText();
        note.setCustomerNoteText("Test note");
        paymentDetail.addNote(note);

        assertThat(paymentDetail.getNotes()).hasSize(1);
        assertThat(note.getPaymentDetail()).isSameAs(paymentDetail);
    }

    @Test
    void testAddNote_blank_notAdded() {
        PaymentNoteText note = new PaymentNoteText();
        note.setCustomerNoteText("");
        paymentDetail.addNote(note);

        assertThat(paymentDetail.getNotes()).isEmpty();
    }

    @Test
    void testAddNote_null_notAdded() {
        PaymentNoteText note = new PaymentNoteText();
        // customerNoteText is null by default
        paymentDetail.addNote(note);

        assertThat(paymentDetail.getNotes()).isEmpty();
    }

    @Test
    void testAddPaymentText() {
        paymentDetail.addPaymentText("Payment text line 1");

        assertThat(paymentDetail.getNotes()).hasSize(1);
        PaymentNoteText addedNote = paymentDetail.getNotes().get(0);
        assertThat(addedNote.getCustomerNoteText()).isEqualTo("Payment text line 1");
        assertThat(addedNote.getCustomerNoteLineNbr()).isEqualTo(new KualiInteger(1));
    }

    @Test
    void testAddPaymentText_multipleLines() {
        paymentDetail.addPaymentText("Line 1");
        paymentDetail.addPaymentText("Line 2");

        assertThat(paymentDetail.getNotes()).hasSize(2);
        assertThat(paymentDetail.getNotes().get(0).getCustomerNoteLineNbr()).isEqualTo(new KualiInteger(1));
        assertThat(paymentDetail.getNotes().get(1).getCustomerNoteLineNbr()).isEqualTo(new KualiInteger(2));
    }

    @Test
    void testDeleteNote() {
        PaymentNoteText note = new PaymentNoteText();
        note.setCustomerNoteText("To delete");
        paymentDetail.getNotes().add(note);
        paymentDetail.deleteNote(note);
        assertThat(paymentDetail.getNotes()).isEmpty();
    }

    @Test
    void testSetAndGetNotes() {
        List<PaymentNoteText> noteList = new ArrayList<>();
        PaymentNoteText n = new PaymentNoteText();
        n.setCustomerNoteText("test");
        noteList.add(n);
        paymentDetail.setNotes(noteList);
        assertThat(paymentDetail.getNotes()).hasSize(1);
    }

    @Test
    void testSetAndGetAccountDetail() {
        List<PaymentAccountDetail> acctList = new ArrayList<>();
        acctList.add(new PaymentAccountDetail());
        paymentDetail.setAccountDetail(acctList);
        assertThat(paymentDetail.getAccountDetail()).hasSize(1);
    }

    @Test
    void testGettersAndSetters_basicFields() {
        KualiInteger id = new KualiInteger(42);
        paymentDetail.setId(id);
        paymentDetail.setInvoiceNbr("INV-001");
        paymentDetail.setPurchaseOrderNbr("PO-001");
        paymentDetail.setCustPaymentDocNbr("DOC-001");
        paymentDetail.setFinancialSystemOriginCode("01");
        paymentDetail.setFinancialDocumentTypeCode("DV");
        paymentDetail.setRequisitionNbr("REQ-001");
        paymentDetail.setOrganizationDocNbr("ORG-001");
        paymentDetail.setCustomerInstitutionNumber("INST-001");
        paymentDetail.setPrimaryCancelledPayment(true);

        assertThat(paymentDetail.getId()).isEqualTo(id);
        assertThat(paymentDetail.getInvoiceNbr()).isEqualTo("INV-001");
        assertThat(paymentDetail.getPurchaseOrderNbr()).isEqualTo("PO-001");
        assertThat(paymentDetail.getCustPaymentDocNbr()).isEqualTo("DOC-001");
        assertThat(paymentDetail.getFinancialSystemOriginCode()).isEqualTo("01");
        assertThat(paymentDetail.getFinancialDocumentTypeCode()).isEqualTo("DV");
        assertThat(paymentDetail.getRequisitionNbr()).isEqualTo("REQ-001");
        assertThat(paymentDetail.getOrganizationDocNbr()).isEqualTo("ORG-001");
        assertThat(paymentDetail.getCustomerInstitutionNumber()).isEqualTo("INST-001");
        assertThat(paymentDetail.getPrimaryCancelledPayment()).isTrue();
    }

    @Test
    void testGettersAndSetters_amountFields() {
        paymentDetail.setOrigInvoiceAmount(new KualiDecimal(1000));
        paymentDetail.setNetPaymentAmount(new KualiDecimal(900));
        paymentDetail.setInvTotDiscountAmount(new KualiDecimal(50));
        paymentDetail.setInvTotShipAmount(new KualiDecimal(25));
        paymentDetail.setInvTotOtherDebitAmount(new KualiDecimal(10));
        paymentDetail.setInvTotOtherCreditAmount(new KualiDecimal(5));

        assertThat(paymentDetail.getOrigInvoiceAmount()).isEqualTo(new KualiDecimal(1000));
        assertThat(paymentDetail.getNetPaymentAmount()).isEqualTo(new KualiDecimal(900));
        assertThat(paymentDetail.getInvTotDiscountAmount()).isEqualTo(new KualiDecimal(50));
        assertThat(paymentDetail.getInvTotShipAmount()).isEqualTo(new KualiDecimal(25));
        assertThat(paymentDetail.getInvTotOtherDebitAmount()).isEqualTo(new KualiDecimal(10));
        assertThat(paymentDetail.getInvTotOtherCreditAmount()).isEqualTo(new KualiDecimal(5));
    }

    @Test
    void testGettersAndSetters_paymentGroup() {
        PaymentGroup pg = new PaymentGroup();
        KualiInteger pgId = new KualiInteger(100);
        paymentDetail.setPaymentGroup(pg);
        paymentDetail.setPaymentGroupId(pgId);

        assertThat(paymentDetail.getPaymentGroup()).isSameAs(pg);
        assertThat(paymentDetail.getPaymentGroupId()).isEqualTo(pgId);
    }

    @Test
    void testInvoiceDateGetterSetter() {
        Date invDate = Date.valueOf("2024-01-15");
        paymentDetail.setInvoiceDate(invDate);
        assertThat(paymentDetail.getInvoiceDate()).isEqualTo(invDate);
    }

    @Test
    void testSetAmountsFromString() {
        paymentDetail.setInvTotDiscountAmount("100.50");
        assertThat(paymentDetail.getInvTotDiscountAmount()).isEqualTo(new KualiDecimal("100.50"));

        paymentDetail.setInvTotOtherCreditAmount("200.25");
        assertThat(paymentDetail.getInvTotOtherCreditAmount()).isEqualTo(new KualiDecimal("200.25"));

        paymentDetail.setInvTotOtherDebitAmount("50.75");
        assertThat(paymentDetail.getInvTotOtherDebitAmount()).isEqualTo(new KualiDecimal("50.75"));

        paymentDetail.setInvTotShipAmount("30.00");
        assertThat(paymentDetail.getInvTotShipAmount()).isEqualTo(new KualiDecimal("30.00"));
    }
}
