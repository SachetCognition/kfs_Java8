package org.kuali.kfs.fp.document;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AuxiliaryVoucherDocumentUnitTest extends KfsUnitTestBase {

    @Test
    void adjustmentDocTypeConstant() {
        assertThat(KFSConstants.AuxiliaryVoucher.ADJUSTMENT_DOC_TYPE).isNotBlank();
    }

    @Test
    void recodeDocTypeConstant() {
        assertThat(KFSConstants.AuxiliaryVoucher.RECODE_DOC_TYPE).isNotBlank();
    }

    @Test
    void accrualDocTypeConstant() {
        assertThat(KFSConstants.AuxiliaryVoucher.ACCRUAL_DOC_TYPE).isNotBlank();
    }

    @Test
    void allTypeCodesAreDifferent() {
        assertThat(KFSConstants.AuxiliaryVoucher.ADJUSTMENT_DOC_TYPE)
                .isNotEqualTo(KFSConstants.AuxiliaryVoucher.RECODE_DOC_TYPE)
                .isNotEqualTo(KFSConstants.AuxiliaryVoucher.ACCRUAL_DOC_TYPE);
    }

    @Test
    void accrualDocTypeNameConstant() {
        assertThat(KFSConstants.AuxiliaryVoucher.ACCRUAL_DOC_TYPE_NAME).isNotBlank();
    }

    @Test
    void accrualDocDayOfMonthConstant() {
        assertThat(KFSConstants.AuxiliaryVoucher.ACCRUAL_DOC_DAY_OF_MONTH).isEqualTo(15);
    }

    @Test
    void changeVoucherTypeConstant() {
        assertThat(KFSConstants.AuxiliaryVoucher.CHANGE_VOUCHER_TYPE).isNotBlank();
    }
}
