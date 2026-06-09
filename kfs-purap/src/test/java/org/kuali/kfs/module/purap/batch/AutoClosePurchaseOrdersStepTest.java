package org.kuali.kfs.module.purap.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.document.service.PurchaseOrderService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AutoClosePurchaseOrdersStepTest extends KfsUnitTestBase {

    @Mock
    private PurchaseOrderService purchaseOrderService;

    @InjectMocks
    private AutoClosePurchaseOrdersStep step;

    @Test
    void executeCallsAutoCloseAndReturnsTrue() throws Exception {
        when(purchaseOrderService.autoCloseFullyDisencumberedOrders()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertThat(result).isTrue();
        verify(purchaseOrderService).autoCloseFullyDisencumberedOrders();
    }

    @Test
    void executeReturnsFalseWhenServiceReturnsFalse() throws Exception {
        when(purchaseOrderService.autoCloseFullyDisencumberedOrders()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertThat(result).isFalse();
    }
}
