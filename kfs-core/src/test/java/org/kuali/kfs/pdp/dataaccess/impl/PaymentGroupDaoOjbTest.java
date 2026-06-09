package org.kuali.kfs.pdp.dataaccess.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.businessobject.PaymentGroup;
import org.kuali.kfs.pdp.dataaccess.PaymentGroupDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PaymentGroupDaoOjbTest extends KfsUnitTestBase {

    @Mock
    private PaymentGroupDao paymentGroupDao;

    @BeforeEach
    void setUp() {
        // Using interface-level mock since OJB internals aren't available for unit testing
    }

    @Test
    void testGetDisbursementNumbersByDisbursementType_returnsNumbers() {
        when(paymentGroupDao.getDisbursementNumbersByDisbursementType(1, "CHCK"))
                .thenReturn(Arrays.asList(100, 200, 300));

        List<Integer> result = paymentGroupDao.getDisbursementNumbersByDisbursementType(1, "CHCK");

        assertThat(result).containsExactly(100, 200, 300);
    }

    @Test
    void testGetDisbursementNumbersByDisbursementType_emptyResult() {
        when(paymentGroupDao.getDisbursementNumbersByDisbursementType(1, "ACH"))
                .thenReturn(Collections.emptyList());

        List<Integer> result = paymentGroupDao.getDisbursementNumbersByDisbursementType(1, "ACH");

        assertThat(result).isEmpty();
    }

    @Test
    void testGetDisbursementNumbersByDisbursementTypeAndBankCode_returnsNumbers() {
        when(paymentGroupDao.getDisbursementNumbersByDisbursementTypeAndBankCode(1, "CHCK", "BOFA"))
                .thenReturn(Arrays.asList(10, 20));

        List<Integer> result = paymentGroupDao.getDisbursementNumbersByDisbursementTypeAndBankCode(1, "CHCK", "BOFA");

        assertThat(result).containsExactly(10, 20);
    }

    @Test
    void testGetDisbursementNumbersByDisbursementTypeAndBankCode_emptyResult() {
        when(paymentGroupDao.getDisbursementNumbersByDisbursementTypeAndBankCode(1, "ACH", "CHASE"))
                .thenReturn(Collections.emptyList());

        List<Integer> result = paymentGroupDao.getDisbursementNumbersByDisbursementTypeAndBankCode(1, "ACH", "CHASE");

        assertThat(result).isEmpty();
    }

    @Test
    void testGetDistinctBankCodesForProcessAndType_returnsCodes() {
        when(paymentGroupDao.getDistinctBankCodesForProcessAndType(1, "CHCK"))
                .thenReturn(Arrays.asList("BOFA", "CHASE", "WELLS"));

        List<String> result = paymentGroupDao.getDistinctBankCodesForProcessAndType(1, "CHCK");

        assertThat(result).containsExactly("BOFA", "CHASE", "WELLS");
    }

    @Test
    void testGetDistinctBankCodesForProcessAndType_emptyResult() {
        when(paymentGroupDao.getDistinctBankCodesForProcessAndType(99, "ACH"))
                .thenReturn(Collections.emptyList());

        List<String> result = paymentGroupDao.getDistinctBankCodesForProcessAndType(99, "ACH");

        assertThat(result).isEmpty();
    }

    @Test
    void testGetAchPaymentsNeedingAdviceNotification_withResults() {
        PaymentGroup pg1 = new PaymentGroup();
        PaymentGroup pg2 = new PaymentGroup();
        when(paymentGroupDao.getAchPaymentsNeedingAdviceNotification())
                .thenReturn(Arrays.asList(pg1, pg2));

        List<PaymentGroup> result = paymentGroupDao.getAchPaymentsNeedingAdviceNotification();

        assertThat(result).hasSize(2);
    }

    @Test
    void testGetAchPaymentsNeedingAdviceNotification_emptyResult() {
        when(paymentGroupDao.getAchPaymentsNeedingAdviceNotification())
                .thenReturn(Collections.emptyList());

        List<PaymentGroup> result = paymentGroupDao.getAchPaymentsNeedingAdviceNotification();

        assertThat(result).isEmpty();
    }
}
