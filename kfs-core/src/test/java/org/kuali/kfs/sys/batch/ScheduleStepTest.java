package org.kuali.kfs.sys.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.batch.service.SchedulerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class ScheduleStepTest extends KfsUnitTestBase {

    @Mock
    private SchedulerService schedulerService;

    @Mock
    private ParameterService parameterService;

    private ScheduleStep step;

    @BeforeEach
    public void setUp() {
        step = new ScheduleStep();
        step.setSchedulerService(schedulerService);
        step.setParameterService(parameterService);
    }

    @Test
    public void testExecuteNoIncompleteJobs() throws Exception {
        when(schedulerService.hasIncompleteJob()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(schedulerService).reinitializeScheduledJobs();
        verify(schedulerService).logScheduleResults();
    }

    @Test
    public void testExecuteWithIncompleteJobsThenComplete() throws Exception {
        when(schedulerService.hasIncompleteJob()).thenReturn(true, false);
        when(schedulerService.isPastScheduleCutoffTime()).thenReturn(false);
        when(parameterService.getParameterValueAsString(any(Class.class), any(String.class))).thenReturn("10");
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(schedulerService).processWaitingJobs();
    }
}
