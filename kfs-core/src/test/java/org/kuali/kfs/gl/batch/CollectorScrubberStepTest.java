package org.kuali.kfs.gl.batch;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.report.CollectorReportData;
import org.kuali.kfs.gl.service.ScrubberService;
import org.kuali.kfs.gl.service.impl.ScrubberStatus;
import org.kuali.kfs.sys.batch.service.WrappedBatchExecutorService.CustomBatchExecutor;
import org.kuali.kfs.sys.batch.service.WrappedBatchExecutorService;
import org.kuali.kfs.sys.batch.service.WrappingBatchService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

public class CollectorScrubberStepTest extends KfsUnitTestBase {

    @Mock
    private ScrubberStatus scrubberStatus;

    @Mock
    private CollectorBatch batch;

    @Mock
    private CollectorReportData collectorReportData;

    @Mock
    private ScrubberService scrubberService;

    @Mock
    private WrappedBatchExecutorService wrappedBatchExecutorService;

    @InjectMocks
    private CollectorScrubberStep step;

    @BeforeEach
    public void setUp() {
        step.setScrubberStatus(scrubberStatus);
        step.setBatch(batch);
        step.setCollectorReportData(collectorReportData);
        step.setScrubberService(scrubberService);
        step.setWrappedBatchExecutorService(wrappedBatchExecutorService);
        step.setWrappingBatchServices(new ArrayList<WrappingBatchService>());
    }

    @Test
    public void testExecuteDelegatesToWrappedExecutor() throws Exception {
        when(wrappedBatchExecutorService.execute((List) isNull(), any(CustomBatchExecutor.class))).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(wrappedBatchExecutorService).execute((List) isNull(), any(CustomBatchExecutor.class));
    }

    @Test
    public void testCustomBatchExecutorCallsService() throws Exception {
        Method m = step.getClass().getDeclaredMethod("getCustomBatchExecutor");
        m.setAccessible(true);
        CustomBatchExecutor executor = (CustomBatchExecutor) m.invoke(step);
        assertNotNull(executor);
        boolean result = executor.execute();
        assertTrue(result);
    }

    @Test
    public void testExecuteReturnsFalseOnFailure() throws Exception {
        when(wrappedBatchExecutorService.execute((List) isNull(), any(CustomBatchExecutor.class))).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }

}
