package org.kuali.kfs.gl.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.service.NightlyOutService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClearPendingStepTest extends KfsUnitTestBase {

    @Mock
    private NightlyOutService nightlyOutService;

    @InjectMocks
    private ClearPendingStep step;

    @BeforeEach
    public void setUp() {
        step.setNightlyOutService(nightlyOutService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(nightlyOutService).deleteCopiedPendingLedgerEntries();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(nightlyOutService, atLeastOnce()).deleteCopiedPendingLedgerEntries();
    }

}
