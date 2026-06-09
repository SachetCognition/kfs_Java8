package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.businessobject.PaymentDetail;
import org.kuali.kfs.pdp.dataaccess.PaymentDetailDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class PaymentDetailServiceImplTest extends KfsUnitTestBase {

    @Mock
    private PaymentDetailDao paymentDetailDao;
    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private PaymentDetailServiceImpl paymentDetailService;

    @Test
    void getByDisbursementNumber_returnsMatchingDetails() {
        PaymentDetail detail = new PaymentDetail();
        when(businessObjectService.findMatching(eq(PaymentDetail.class), any(Map.class)))
                .thenReturn(Arrays.asList(detail));

        Iterator result = paymentDetailService.getByDisbursementNumber(12345);
        assertThat(result.hasNext()).isTrue();
    }

    @Test
    void getByDisbursementNumber_noResults_returnsEmptyIterator() {
        when(businessObjectService.findMatching(eq(PaymentDetail.class), any(Map.class)))
                .thenReturn(Collections.<PaymentDetail>emptyList());

        Iterator result = paymentDetailService.getByDisbursementNumber(99999);
        assertThat(result.hasNext()).isFalse();
    }

    @Test
    void getByDisbursementNumber_withProcessParams_returnsMatchingDetails() {
        PaymentDetail detail = new PaymentDetail();
        when(businessObjectService.findMatching(eq(PaymentDetail.class), any(Map.class)))
                .thenReturn(Arrays.asList(detail));

        Iterator<PaymentDetail> result = paymentDetailService.getByDisbursementNumber(12345, 1, "CHCK", "BANK1");
        assertThat(result.hasNext()).isTrue();
    }
}
