package org.kuali.kfs.module.purap.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.document.service.PaymentRequestService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AutoApprovePaymentRequestsStepTest extends KfsUnitTestBase {

    @Mock
    private PaymentRequestService paymentRequestService;

    @InjectMocks
    private AutoApprovePaymentRequestsStep step;

    @BeforeEach
    public void setUp() {
        step.setPaymentRequestService(paymentRequestService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(paymentRequestService.autoApprovePaymentRequests()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(paymentRequestService).autoApprovePaymentRequests();
    }

    @Test
    public void testExecuteHandlesException() throws Exception {
        doThrow(new RuntimeException("test error")).when(paymentRequestService).autoApprovePaymentRequests();
        try {
            step.execute("testJob", new Date());
            fail("Expected exception");
        } catch (RuntimeException e) {
            assertNotNull(e);
        }
    }

}
