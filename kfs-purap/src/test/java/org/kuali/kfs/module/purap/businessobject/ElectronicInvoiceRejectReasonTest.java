package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ElectronicInvoiceRejectReasonTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        ElectronicInvoiceRejectReason reason = new ElectronicInvoiceRejectReason();
        assertThat(reason.getInvoiceRejectReasonIdentifier()).isNull();
        assertThat(reason.getPurapDocumentIdentifier()).isNull();
        assertThat(reason.getInvoiceFileName()).isNull();
        assertThat(reason.getInvoiceRejectReasonTypeCode()).isNull();
        assertThat(reason.getInvoiceRejectReasonDescription()).isNull();
    }

    @Test
    void threeArgConstructor() {
        ElectronicInvoiceRejectReason reason =
                new ElectronicInvoiceRejectReason("TYPE01", "invoice.xml", "Missing PO number");
        assertThat(reason.getInvoiceRejectReasonTypeCode()).isEqualTo("TYPE01");
        assertThat(reason.getInvoiceFileName()).isEqualTo("invoice.xml");
        assertThat(reason.getInvoiceRejectReasonDescription()).isEqualTo("Missing PO number");
    }

    @Test
    void fourArgConstructorWithDocId() {
        ElectronicInvoiceRejectReason reason =
                new ElectronicInvoiceRejectReason(1001, "TYPE02", "inv2.xml", "Amount mismatch");
        assertThat(reason.getPurapDocumentIdentifier()).isEqualTo(1001);
        assertThat(reason.getInvoiceRejectReasonTypeCode()).isEqualTo("TYPE02");
        assertThat(reason.getInvoiceFileName()).isEqualTo("inv2.xml");
        assertThat(reason.getInvoiceRejectReasonDescription()).isEqualTo("Amount mismatch");
    }

    @Test
    void settersAndGetters() {
        ElectronicInvoiceRejectReason reason = new ElectronicInvoiceRejectReason();
        reason.setInvoiceRejectReasonIdentifier(42);
        reason.setPurapDocumentIdentifier(100);
        reason.setInvoiceFileName("test.xml");
        reason.setInvoiceRejectReasonTypeCode("INVALID");
        reason.setInvoiceRejectReasonDescription("Test description");

        assertThat(reason.getInvoiceRejectReasonIdentifier()).isEqualTo(42);
        assertThat(reason.getPurapDocumentIdentifier()).isEqualTo(100);
        assertThat(reason.getInvoiceFileName()).isEqualTo("test.xml");
        assertThat(reason.getInvoiceRejectReasonTypeCode()).isEqualTo("INVALID");
        assertThat(reason.getInvoiceRejectReasonDescription()).isEqualTo("Test description");
    }

    @Test
    void rejectReasonTypeReference() {
        ElectronicInvoiceRejectReason reason = new ElectronicInvoiceRejectReason();
        ElectronicInvoiceRejectReasonType type = new ElectronicInvoiceRejectReasonType();
        reason.setInvoiceRejectReasonType(type);
        assertThat(reason.getInvoiceRejectReasonType()).isSameAs(type);
    }
}
