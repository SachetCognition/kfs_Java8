package org.kuali.kfs.gl.batch;

import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.batch.service.WrappedBatchExecutorService;
import org.kuali.kfs.sys.batch.service.WrappedBatchExecutorService.CustomBatchExecutor;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;

public class PosterSummaryReportStepTest extends KfsUnitTestBase {

    @Mock
    private WrappedBatchExecutorService wrappedBatchExecutorService;

    private PosterSummaryReportStep step;

    @BeforeEach
    public void setUp() {
        step = new PosterSummaryReportStep();
        step.setWrappedBatchExecutorService(wrappedBatchExecutorService);
    }

    @Test
    public void testExecuteDelegatesToWrappedExecutor() throws Exception {
        when(wrappedBatchExecutorService.execute((List) isNull(), any(CustomBatchExecutor.class))).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(wrappedBatchExecutorService).execute((List) isNull(), any(CustomBatchExecutor.class));
    }

    @Test
    public void testExecuteReturnsFalseOnFailure() throws Exception {
        when(wrappedBatchExecutorService.execute((List) isNull(), any(CustomBatchExecutor.class))).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }
}
