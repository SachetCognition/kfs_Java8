package org.kuali.kfs.module.purap.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.document.service.PurchaseOrderService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AutoCloseRecurringOrdersStepTest extends KfsUnitTestBase {

    @Mock
    private PurchaseOrderService purchaseOrderService;

    @InjectMocks
    private AutoCloseRecurringOrdersStep step;

    @BeforeEach
    public void setUp() {
        step.setPurchaseOrderService(purchaseOrderService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(purchaseOrderService.autoCloseRecurringOrders()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(purchaseOrderService).autoCloseRecurringOrders();
    }

    @Test
    public void testExecuteHandlesException() throws Exception {
        doThrow(new RuntimeException("test error")).when(purchaseOrderService).autoCloseRecurringOrders();
        try {
            step.execute("testJob", new Date());
            fail("Expected exception");
        } catch (RuntimeException e) {
            assertNotNull(e);
        }
    }

}
