package org.kuali.kfs.fp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.businessobject.CashDetailTypeCode;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;

class CashDetailTypeCodeServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private CashDetailTypeCodeServiceImpl cashDetailTypeCodeService;

    @Test
    void getCashReceiptCheckTypeCode_returnsCheckCode() {
        CashDetailTypeCode result = cashDetailTypeCodeService.getCashReceiptCheckTypeCode();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo("CRCHK");
    }

    @Test
    void getCashReceiptCoinTypeCode_returnsCode() {
        CashDetailTypeCode result = cashDetailTypeCodeService.getCashReceiptCoinTypeCode();
        assertThat(result).isNotNull();
    }
}
