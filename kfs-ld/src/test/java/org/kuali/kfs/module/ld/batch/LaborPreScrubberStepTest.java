package org.kuali.kfs.module.ld.batch;

import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.service.PreScrubberService;
import org.kuali.kfs.sys.batch.service.WrappedBatchExecutorService;
import org.kuali.kfs.sys.batch.service.WrappedBatchExecutorService.CustomBatchExecutor;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;

public class LaborPreScrubberStepTest extends KfsUnitTestBase {

    @Mock
    private PreScrubberService preScrubberService;

    @Mock
    private WrappedBatchExecutorService wrappedBatchExecutorService;

    private LaborPreScrubberStep step;

    @BeforeEach
    public void setUp() {
        step = new LaborPreScrubberStep();
        step.setLaborPreScrubberService(preScrubberService);
        step.setWrappedBatchExecutorService(wrappedBatchExecutorService);
    }

    @Test
    public void testExecuteDelegatesToWrappedExecutor() throws Exception {
        when(wrappedBatchExecutorService.execute((List) isNull(), any(CustomBatchExecutor.class))).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(wrappedBatchExecutorService).execute((List) isNull(), any(CustomBatchExecutor.class));
    }
}
