package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.businessobject.PaymentGroup;
import org.kuali.kfs.pdp.businessobject.PaymentProcess;
import org.kuali.kfs.pdp.dataaccess.PaymentGroupDao;
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

class PaymentGroupServiceImplTest extends KfsUnitTestBase {

    @Mock
    private PaymentGroupDao paymentGroupDao;

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private PaymentGroupServiceImpl paymentGroupService;

    @BeforeEach
    void setUp() {
        paymentGroupService.setPaymentGroupDao(paymentGroupDao);
        paymentGroupService.setBusinessObjectService(businessObjectService);
    }

    @Test
    void testGetDisbursementNumbersByDisbursementType() {
        List<Integer> expected = Arrays.asList(1, 2, 3);
        when(paymentGroupDao.getDisbursementNumbersByDisbursementType(1, "CHCK")).thenReturn(expected);

        List<Integer> result = paymentGroupService.getDisbursementNumbersByDisbursementType(1, "CHCK");

        assertThat(result).containsExactly(1, 2, 3);
        verify(paymentGroupDao).getDisbursementNumbersByDisbursementType(1, "CHCK");
    }

    @Test
    void testGetDisbursementNumbersByDisbursementTypeAndBankCode() {
        List<Integer> expected = Arrays.asList(10, 20);
        when(paymentGroupDao.getDisbursementNumbersByDisbursementTypeAndBankCode(1, "ACH", "BOFA")).thenReturn(expected);

        List<Integer> result = paymentGroupService.getDisbursementNumbersByDisbursementTypeAndBankCode(1, "ACH", "BOFA");

        assertThat(result).containsExactly(10, 20);
        verify(paymentGroupDao).getDisbursementNumbersByDisbursementTypeAndBankCode(1, "ACH", "BOFA");
    }

    @Test
    void testGetDistinctBankCodesForProcessAndType() {
        List<String> expected = Arrays.asList("BOFA", "CHASE");
        when(paymentGroupDao.getDistinctBankCodesForProcessAndType(1, "CHCK")).thenReturn(expected);

        List<String> result = paymentGroupService.getDistinctBankCodesForProcessAndType(1, "CHCK");

        assertThat(result).containsExactly("BOFA", "CHASE");
        verify(paymentGroupDao).getDistinctBankCodesForProcessAndType(1, "CHCK");
    }

    @Test
    void testGetByDisbursementTypeStatusCode() {
        PaymentGroup pg = new PaymentGroup();
        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap())).thenReturn(Collections.singletonList(pg));

        Iterator result = paymentGroupService.getByDisbursementTypeStatusCode("CHCK", "OPEN");

        assertThat(result).isNotNull();
        assertThat(result.hasNext()).isTrue();
        verify(businessObjectService).findMatching(eq(PaymentGroup.class), anyMap());
    }

    @Test
    void testGetByProcess() {
        PaymentProcess process = new PaymentProcess();
        PaymentGroup pg = new PaymentGroup();
        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap())).thenReturn(Collections.singletonList(pg));

        Iterator result = paymentGroupService.getByProcess(process);

        assertThat(result).isNotNull();
        verify(businessObjectService).findMatching(eq(PaymentGroup.class), anyMap());
    }

    @Test
    void testGetByBatchId() {
        PaymentGroup pg1 = new PaymentGroup();
        PaymentGroup pg2 = new PaymentGroup();
        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Arrays.asList(pg1, pg2));

        List<PaymentGroup> result = paymentGroupService.getByBatchId(42);

        assertThat(result).hasSize(2);
        verify(businessObjectService).findMatching(eq(PaymentGroup.class), anyMap());
    }

    @Test
    void testGet_delegatesToBusinessObjectService() {
        PaymentGroup expected = new PaymentGroup();
        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Collections.singletonList(expected));

        // getByBatchId uses findMatching internally
        List<PaymentGroup> result = paymentGroupService.getByBatchId(1);
        assertThat(result).isNotEmpty();
    }
}
