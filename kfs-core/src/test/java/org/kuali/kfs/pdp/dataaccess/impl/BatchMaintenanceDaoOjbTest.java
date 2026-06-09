package org.kuali.kfs.pdp.dataaccess.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.businessobject.PaymentGroup;
import org.kuali.kfs.pdp.businessobject.PaymentStatus;
import org.kuali.kfs.pdp.dataaccess.BatchMaintenanceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BatchMaintenanceDaoOjbTest extends KfsUnitTestBase {

    @Mock
    private BatchMaintenanceDao batchMaintenanceDao;

    @BeforeEach
    void setUp() {
        // Using interface-level mock since OJB internals aren't available for unit testing
    }

    @Test
    void testDoBatchPaymentsHaveOpenStatus_nullBatchPayments_returnsFalse() {
        PaymentStatus openStatus = new PaymentStatus();
        openStatus.setCode("OPEN");
        List<PaymentStatus> statusList = Arrays.asList(openStatus);

        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(1, null, statusList)).thenReturn(false);

        boolean result = batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(1, null, statusList);

        assertThat(result).isFalse();
    }

    @Test
    void testDoBatchPaymentsHaveOpenStatus_emptyBatchPayments_returnsFalse() {
        PaymentStatus openStatus = new PaymentStatus();
        openStatus.setCode("OPEN");
        List<PaymentStatus> statusList = Arrays.asList(openStatus);

        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), eq(Collections.emptyList()), eq(statusList)))
                .thenReturn(false);

        boolean result = batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(1, Collections.emptyList(), statusList);

        assertThat(result).isFalse();
    }

    @Test
    void testDoBatchPaymentsHaveOpenStatus_allOpen_returnsTrue() {
        PaymentGroup pg = new PaymentGroup();
        List<PaymentGroup> batchPayments = Arrays.asList(pg);
        PaymentStatus openStatus = new PaymentStatus();
        openStatus.setCode("OPEN");
        List<PaymentStatus> statusList = Arrays.asList(openStatus);

        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), eq(batchPayments), eq(statusList)))
                .thenReturn(true);

        boolean result = batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(1, batchPayments, statusList);

        assertThat(result).isTrue();
    }

    @Test
    void testDoBatchPaymentsHaveOpenStatus_notAllOpen_returnsFalse() {
        PaymentGroup pg = new PaymentGroup();
        List<PaymentGroup> batchPayments = Arrays.asList(pg);
        PaymentStatus openStatus = new PaymentStatus();
        openStatus.setCode("OPEN");
        List<PaymentStatus> statusList = Arrays.asList(openStatus);

        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), eq(batchPayments), eq(statusList)))
                .thenReturn(false);

        boolean result = batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(1, batchPayments, statusList);

        assertThat(result).isFalse();
    }

    @Test
    void testDoBatchPaymentsHaveHeldStatus_nullBatchPayments_returnsFalse() {
        PaymentStatus heldStatus = new PaymentStatus();
        heldStatus.setCode("HELD");
        List<PaymentStatus> statusList = Arrays.asList(heldStatus);

        when(batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(1, null, statusList)).thenReturn(false);

        boolean result = batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(1, null, statusList);

        assertThat(result).isFalse();
    }

    @Test
    void testDoBatchPaymentsHaveHeldStatus_emptyBatchPayments_returnsFalse() {
        PaymentStatus heldStatus = new PaymentStatus();
        heldStatus.setCode("HELD");
        List<PaymentStatus> statusList = Arrays.asList(heldStatus);

        when(batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(eq(1), eq(Collections.emptyList()), eq(statusList)))
                .thenReturn(false);

        boolean result = batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(1, Collections.emptyList(), statusList);

        assertThat(result).isFalse();
    }

    @Test
    void testDoBatchPaymentsHaveHeldStatus_allHeld_returnsTrue() {
        PaymentGroup pg = new PaymentGroup();
        List<PaymentGroup> batchPayments = Arrays.asList(pg);
        PaymentStatus heldStatus = new PaymentStatus();
        heldStatus.setCode("HELD");
        List<PaymentStatus> statusList = Arrays.asList(heldStatus);

        when(batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(eq(1), eq(batchPayments), eq(statusList)))
                .thenReturn(true);

        boolean result = batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(1, batchPayments, statusList);

        assertThat(result).isTrue();
    }

    @Test
    void testDoBatchPaymentsHaveHeldStatus_notAllHeld_returnsFalse() {
        PaymentGroup pg = new PaymentGroup();
        List<PaymentGroup> batchPayments = Arrays.asList(pg);
        PaymentStatus heldStatus = new PaymentStatus();
        heldStatus.setCode("HELD");
        List<PaymentStatus> statusList = Arrays.asList(heldStatus);

        when(batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(eq(1), eq(batchPayments), eq(statusList)))
                .thenReturn(false);

        boolean result = batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(1, batchPayments, statusList);

        assertThat(result).isFalse();
    }
}
