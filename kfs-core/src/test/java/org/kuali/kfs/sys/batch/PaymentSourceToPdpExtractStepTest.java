package org.kuali.kfs.sys.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.batch.service.PaymentSourceExtractionService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentSourceToPdpExtractStepTest extends KfsUnitTestBase {

    @Mock
    private PaymentSourceExtractionService paymentSourceExtractionService;

    @InjectMocks
    private PaymentSourceToPdpExtractStep step;

    @BeforeEach
    public void setUp() {
        step.setPaymentSourceExtractionService(paymentSourceExtractionService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(paymentSourceExtractionService.extractPayments()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(paymentSourceExtractionService).extractPayments();
    }

    @Test
    public void testExecuteReturnsFalse() throws Exception {
        when(paymentSourceExtractionService.extractPayments()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }

}
