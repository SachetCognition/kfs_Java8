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

public class ReceivingPaymentRequestStepTest extends KfsUnitTestBase {

    @Mock
    private PaymentRequestService paymentRequestService;

    @InjectMocks
    private ReceivingPaymentRequestStep step;

    @BeforeEach
    public void setUp() {
        step.setPaymentRequestService(paymentRequestService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(paymentRequestService).processPaymentRequestInReceivingStatus();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(paymentRequestService, atLeastOnce()).processPaymentRequestInReceivingStatus();
    }

}
