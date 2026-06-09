package org.kuali.kfs.pdp.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.batch.service.ExtractPaymentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExtractCanceledChecksStepTest extends KfsUnitTestBase {

    @Mock
    private ExtractPaymentService extractPaymentService;

    @InjectMocks
    private ExtractCanceledChecksStep step;

    @BeforeEach
    public void setUp() {
        step.setExtractPaymentService(extractPaymentService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(extractPaymentService).extractCanceledChecks();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(extractPaymentService, atLeastOnce()).extractCanceledChecks();
    }

}
