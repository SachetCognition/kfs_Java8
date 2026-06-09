package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.businessobject.PaymentChangeCode;
import org.kuali.kfs.pdp.businessobject.PaymentGroup;
import org.kuali.kfs.pdp.businessobject.PaymentStatus;
import org.kuali.kfs.pdp.dataaccess.BatchMaintenanceDao;
import org.kuali.kfs.pdp.service.PaymentGroupService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

class BatchMaintenanceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BatchMaintenanceDao batchMaintenanceDao;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private PaymentGroupService paymentGroupService;

    @Mock
    private Person user;

    @InjectMocks
    private BatchMaintenanceServiceImpl batchMaintenanceService;

    @BeforeEach
    void setUp() {
        batchMaintenanceService.setBatchMaintenanceDao(batchMaintenanceDao);
        batchMaintenanceService.setBusinessObjectService(businessObjectService);
        batchMaintenanceService.setPaymentGroupService(paymentGroupService);
        GlobalVariables.setMessageMap(new MessageMap());
    }

    @Test
    void testChangeStatus() {
        PaymentGroup paymentGroup = new PaymentGroup();
        PaymentStatus origStatus = new PaymentStatus();
        paymentGroup.setPaymentStatus(origStatus);

        PaymentChangeCode changeCode = new PaymentChangeCode();
        when(businessObjectService.findBySinglePrimaryKey(PaymentChangeCode.class, "CB")).thenReturn(changeCode);

        PaymentStatus newStatus = new PaymentStatus();
        when(businessObjectService.findBySinglePrimaryKey(PaymentStatus.class, "CPAY")).thenReturn(newStatus);

        batchMaintenanceService.changeStatus(paymentGroup, "CPAY", "CB", "Test note", user);

        verify(businessObjectService, times(2)).save(any(PersistableBusinessObject.class));
        assertThat(paymentGroup.getPaymentStatus()).isSameAs(newStatus);
    }

    @Test
    void testDoBatchPaymentsHaveOpenStatus() {
        List<PaymentGroup> batchPayments = Arrays.asList(new PaymentGroup());
        List<PaymentStatus> statusList = Arrays.asList(new PaymentStatus());

        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap())).thenReturn(batchPayments);
        when(businessObjectService.findAll(PaymentStatus.class)).thenReturn(statusList);
        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), anyList(), anyList())).thenReturn(true);

        boolean result = batchMaintenanceService.doBatchPaymentsHaveOpenStatus(1);

        assertThat(result).isTrue();
        verify(batchMaintenanceDao).doBatchPaymentsHaveOpenStatus(eq(1), anyList(), anyList());
    }

    @Test
    void testDoBatchPaymentsHaveHeldStatus() {
        List<PaymentGroup> batchPayments = Arrays.asList(new PaymentGroup());
        List<PaymentStatus> statusList = Arrays.asList(new PaymentStatus());

        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap())).thenReturn(batchPayments);
        when(businessObjectService.findAll(PaymentStatus.class)).thenReturn(statusList);
        when(batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(eq(1), anyList(), anyList())).thenReturn(true);

        boolean result = batchMaintenanceService.doBatchPaymentsHaveHeldStatus(1);

        assertThat(result).isTrue();
        verify(batchMaintenanceDao).doBatchPaymentsHaveHeldStatus(eq(1), anyList(), anyList());
    }

    @Test
    void testDoBatchPaymentsHaveOpenOrHeldStatus_openIsTrue() {
        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Arrays.asList(new PaymentGroup()));
        when(businessObjectService.findAll(PaymentStatus.class))
                .thenReturn(Arrays.asList(new PaymentStatus()));
        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), anyList(), anyList())).thenReturn(true);

        boolean result = batchMaintenanceService.doBatchPaymentsHaveOpenOrHeldStatus(1);

        assertThat(result).isTrue();
    }

    @Test
    void testDoBatchPaymentsHaveOpenOrHeldStatus_heldIsTrue() {
        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Arrays.asList(new PaymentGroup()));
        when(businessObjectService.findAll(PaymentStatus.class))
                .thenReturn(Arrays.asList(new PaymentStatus()));
        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), anyList(), anyList())).thenReturn(false);
        when(batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(eq(1), anyList(), anyList())).thenReturn(true);

        boolean result = batchMaintenanceService.doBatchPaymentsHaveOpenOrHeldStatus(1);

        assertThat(result).isTrue();
    }

    @Test
    void testDoBatchPaymentsHaveOpenOrHeldStatus_neitherOpenNorHeld() {
        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Arrays.asList(new PaymentGroup()));
        when(businessObjectService.findAll(PaymentStatus.class))
                .thenReturn(Arrays.asList(new PaymentStatus()));
        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), anyList(), anyList())).thenReturn(false);
        when(batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(eq(1), anyList(), anyList())).thenReturn(false);

        boolean result = batchMaintenanceService.doBatchPaymentsHaveOpenOrHeldStatus(1);

        assertThat(result).isFalse();
    }

    @Test
    void testCancelPendingBatch_statusNotOpenOrHeld_returnsFalse() {
        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Collections.emptyList());
        when(businessObjectService.findAll(PaymentStatus.class))
                .thenReturn(Collections.emptyList());
        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), anyList(), anyList())).thenReturn(false);
        when(batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(eq(1), anyList(), anyList())).thenReturn(false);

        boolean result = batchMaintenanceService.cancelPendingBatch(1, "Cancel note", user);

        assertThat(result).isFalse();
    }

    @Test
    void testCancelPendingBatch_openStatus_emptyGroupList_returnsFalse() {
        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Arrays.asList(new PaymentGroup()));
        when(businessObjectService.findAll(PaymentStatus.class))
                .thenReturn(Arrays.asList(new PaymentStatus()));
        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), anyList(), anyList())).thenReturn(true);
        when(paymentGroupService.getByBatchId(1)).thenReturn(Collections.emptyList());

        boolean result = batchMaintenanceService.cancelPendingBatch(1, "Cancel note", user);

        assertThat(result).isFalse();
    }

    @Test
    void testCancelPendingBatch_success() {
        PaymentGroup pg1 = new PaymentGroup();
        PaymentGroup pg2 = new PaymentGroup();

        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Arrays.asList(pg1, pg2));
        when(businessObjectService.findAll(PaymentStatus.class))
                .thenReturn(Arrays.asList(new PaymentStatus()));
        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), anyList(), anyList())).thenReturn(true);
        when(paymentGroupService.getByBatchId(1)).thenReturn(Arrays.asList(pg1, pg2));

        PaymentChangeCode changeCode = new PaymentChangeCode();
        when(businessObjectService.findBySinglePrimaryKey(eq(PaymentChangeCode.class), anyString())).thenReturn(changeCode);
        PaymentStatus cancelStatus = new PaymentStatus();
        when(businessObjectService.findBySinglePrimaryKey(eq(PaymentStatus.class), anyString())).thenReturn(cancelStatus);

        boolean result = batchMaintenanceService.cancelPendingBatch(1, "Cancel note", user);

        assertThat(result).isTrue();
        // save called twice per group (history + group) = 4 total
        verify(businessObjectService, atLeast(4)).save(any(PersistableBusinessObject.class));
    }

    @Test
    void testHoldPendingBatch_statusNotOpen_returnsFalse() {
        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Collections.emptyList());
        when(businessObjectService.findAll(PaymentStatus.class))
                .thenReturn(Collections.emptyList());
        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), anyList(), anyList())).thenReturn(false);

        boolean result = batchMaintenanceService.holdPendingBatch(1, "Hold note", user);

        assertThat(result).isFalse();
    }

    @Test
    void testHoldPendingBatch_success() {
        PaymentGroup pg = new PaymentGroup();

        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Arrays.asList(pg));
        when(businessObjectService.findAll(PaymentStatus.class))
                .thenReturn(Arrays.asList(new PaymentStatus()));
        when(batchMaintenanceDao.doBatchPaymentsHaveOpenStatus(eq(1), anyList(), anyList())).thenReturn(true);
        when(paymentGroupService.getByBatchId(1)).thenReturn(Arrays.asList(pg));

        PaymentChangeCode changeCode = new PaymentChangeCode();
        when(businessObjectService.findBySinglePrimaryKey(eq(PaymentChangeCode.class), anyString())).thenReturn(changeCode);
        PaymentStatus heldStatus = new PaymentStatus();
        when(businessObjectService.findBySinglePrimaryKey(eq(PaymentStatus.class), anyString())).thenReturn(heldStatus);

        boolean result = batchMaintenanceService.holdPendingBatch(1, "Hold note", user);

        assertThat(result).isTrue();
    }

    @Test
    void testRemoveBatchHold_statusNotHeld_returnsFalse() {
        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Collections.emptyList());
        when(businessObjectService.findAll(PaymentStatus.class))
                .thenReturn(Collections.emptyList());
        when(batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(eq(1), anyList(), anyList())).thenReturn(false);

        boolean result = batchMaintenanceService.removeBatchHold(1, "Remove hold note", user);

        assertThat(result).isFalse();
    }

    @Test
    void testRemoveBatchHold_success() {
        PaymentGroup pg = new PaymentGroup();

        when(businessObjectService.findMatching(eq(PaymentGroup.class), anyMap()))
                .thenReturn(Arrays.asList(pg));
        when(businessObjectService.findAll(PaymentStatus.class))
                .thenReturn(Arrays.asList(new PaymentStatus()));
        when(batchMaintenanceDao.doBatchPaymentsHaveHeldStatus(eq(1), anyList(), anyList())).thenReturn(true);
        when(paymentGroupService.getByBatchId(1)).thenReturn(Arrays.asList(pg));

        PaymentChangeCode changeCode = new PaymentChangeCode();
        when(businessObjectService.findBySinglePrimaryKey(eq(PaymentChangeCode.class), anyString())).thenReturn(changeCode);
        PaymentStatus openStatus = new PaymentStatus();
        when(businessObjectService.findBySinglePrimaryKey(eq(PaymentStatus.class), anyString())).thenReturn(openStatus);

        boolean result = batchMaintenanceService.removeBatchHold(1, "Remove hold note", user);

        assertThat(result).isTrue();
    }
}
