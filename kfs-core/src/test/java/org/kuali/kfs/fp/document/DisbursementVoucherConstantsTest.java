package org.kuali.kfs.fp.document;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class DisbursementVoucherConstantsTest extends KfsUnitTestBase {

    @Test
    void documentTypeCodeIsDV() {
        assertThat(DisbursementVoucherConstants.DOCUMENT_TYPE_CODE).isEqualTo("DV");
    }

    @Test
    void paymentMethodCheckConstant() {
        assertThat(DisbursementVoucherConstants.PAYMENT_METHOD_CHECK).isEqualTo("P");
    }

    @Test
    void paymentMethodWireConstant() {
        assertThat(DisbursementVoucherConstants.PAYMENT_METHOD_WIRE).isEqualTo("W");
    }

    @Test
    void paymentMethodDraftConstant() {
        assertThat(DisbursementVoucherConstants.PAYMENT_METHOD_DRAFT).isEqualTo("F");
    }

    @Test
    void payeeTypeEmployeeConstant() {
        assertThat(DisbursementVoucherConstants.DV_PAYEE_TYPE_EMPLOYEE).isEqualTo("E");
    }

    @Test
    void payeeTypeVendorConstant() {
        assertThat(DisbursementVoucherConstants.DV_PAYEE_TYPE_VENDOR).isEqualTo("V");
    }

    @Test
    void payeeTypeCustomerConstant() {
        assertThat(DisbursementVoucherConstants.DV_PAYEE_TYPE_CUSTOMER).isEqualTo("C");
    }

    @Test
    void payeeTypeSubjectPaymentVendorConstant() {
        assertThat(DisbursementVoucherConstants.DV_PAYEE_TYPE_SUBJECT_PAYMENT_VENDOR).isEqualTo("VSP");
    }

    @Test
    void payeeTypeRevolvingFundVendorConstant() {
        assertThat(DisbursementVoucherConstants.DV_PAYEE_TYPE_REVOLVING_FUND_VENDOR).isEqualTo("VRF");
    }

    @Test
    void vendorPayeeTypeCodesContainsExpectedCodes() {
        assertThat(DisbursementVoucherConstants.VENDOR_PAYEE_TYPE_CODES).isNotEmpty();
    }

    @Test
    void maxNotLineSizeIsNinety() {
        assertThat(DisbursementVoucherConstants.MAX_NOTE_LINE_SIZE).isEqualTo(90);
    }
}
