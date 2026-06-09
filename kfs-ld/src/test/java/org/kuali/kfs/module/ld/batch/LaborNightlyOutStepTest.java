package org.kuali.kfs.module.ld.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.batch.service.LaborNightlyOutService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LaborNightlyOutStepTest extends KfsUnitTestBase {

    @Mock
    private LaborNightlyOutService laborNightlyOutService;

    @InjectMocks
    private LaborNightlyOutStep step;

    @BeforeEach
    public void setUp() {
        step.setLaborNightlyOutService(laborNightlyOutService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(laborNightlyOutService).copyApprovedPendingLedgerEntries();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(laborNightlyOutService, atLeastOnce()).copyApprovedPendingLedgerEntries();
    }

}
