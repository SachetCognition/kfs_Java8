package org.kuali.kfs.pdp.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.batch.service.ProcessPdpCancelPaidService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProcessPdPCancelsAndPaidStepTest extends KfsUnitTestBase {

    @Mock
    private ProcessPdpCancelPaidService processPdpCancelPaidService;

    @InjectMocks
    private ProcessPdPCancelsAndPaidStep step;

    @BeforeEach
    public void setUp() {
        step.setProcessPdpCancelPaidService(processPdpCancelPaidService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(processPdpCancelPaidService).processPdpCancelsAndPaids();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(processPdpCancelPaidService, atLeastOnce()).processPdpCancelsAndPaids();
    }

}
