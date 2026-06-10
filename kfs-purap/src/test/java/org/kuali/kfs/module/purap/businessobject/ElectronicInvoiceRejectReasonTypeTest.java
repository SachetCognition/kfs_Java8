package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ElectronicInvoiceRejectReasonTypeTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        ElectronicInvoiceRejectReasonType type = new ElectronicInvoiceRejectReasonType();
        assertThat(type.getInvoiceRejectReasonTypeCode()).isNull();
        assertThat(type.getInvoiceRejectReasonTypeDescription()).isNull();
        assertThat(type.isInvoiceFailureIndicator()).isFalse();
        assertThat(type.isPerformMatchingIndicator()).isFalse();
        assertThat(type.isActive()).isFalse();
    }

    @Test
    void settersAndGetters() {
        ElectronicInvoiceRejectReasonType type = new ElectronicInvoiceRejectReasonType();
        type.setInvoiceRejectReasonTypeCode("MISS_PO");
        type.setInvoiceRejectReasonTypeDescription("Missing PO Number");
        type.setInvoiceFailureIndicator(true);
        type.setPerformMatchingIndicator(true);
        type.setActive(true);

        assertThat(type.getInvoiceRejectReasonTypeCode()).isEqualTo("MISS_PO");
        assertThat(type.getInvoiceRejectReasonTypeDescription()).isEqualTo("Missing PO Number");
        assertThat(type.isInvoiceFailureIndicator()).isTrue();
        assertThat(type.isPerformMatchingIndicator()).isTrue();
        assertThat(type.isActive()).isTrue();
    }
}
