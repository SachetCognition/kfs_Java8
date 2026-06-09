package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.businessobject.PaymentDetail;
import org.kuali.kfs.pdp.dataaccess.PaymentDetailDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PaymentDetailServiceImplTest extends KfsUnitTestBase {

    @Mock
    private PaymentDetailDao paymentDetailDao;

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private PaymentDetailServiceImpl paymentDetailService;

    @BeforeEach
    void setUp() {
        paymentDetailService.setPaymentDetailDao(paymentDetailDao);
        paymentDetailService.setBusinessObjectService(businessObjectService);
    }

    @Test
    void testGetByDisbursementNumber_singleArg() {
        PaymentDetail pd1 = new PaymentDetail();
        pd1.setFinancialDocumentTypeCode("DV");
        pd1.setCustPaymentDocNbr("DOC001");
        PaymentDetail pd2 = new PaymentDetail();
        pd2.setFinancialDocumentTypeCode("DV");
        pd2.setCustPaymentDocNbr("DOC002");

        when(businessObjectService.findMatching(eq(PaymentDetail.class), anyMap()))
                .thenReturn(Arrays.asList(pd1, pd2));

        Iterator result = paymentDetailService.getByDisbursementNumber(12345);

        assertThat(result).isNotNull();
        assertThat(result.hasNext()).isTrue();
        verify(businessObjectService).findMatching(eq(PaymentDetail.class), anyMap());
    }

    @Test
    void testGetByDisbursementNumber_fourArgs() {
        PaymentDetail pd = new PaymentDetail();
        when(businessObjectService.findMatching(eq(PaymentDetail.class), anyMap()))
                .thenReturn(Collections.singletonList(pd));

        Iterator<PaymentDetail> result = paymentDetailService.getByDisbursementNumber(12345, 1, "CHCK", "BOFA");

        assertThat(result).isNotNull();
        assertThat(result.hasNext()).isTrue();
        verify(businessObjectService).findMatching(eq(PaymentDetail.class), anyMap());
    }

    @Test
    void testGetByDisbursementNumber_emptyResult() {
        when(businessObjectService.findMatching(eq(PaymentDetail.class), anyMap()))
                .thenReturn(Collections.emptyList());

        Iterator result = paymentDetailService.getByDisbursementNumber(99999);

        assertThat(result).isNotNull();
        assertThat(result.hasNext()).isFalse();
    }
}
