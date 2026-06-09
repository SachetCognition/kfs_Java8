package org.kuali.kfs.gl.batch;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.batch.service.BalancingService;
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

public class PosterBalancingHistorySyncStepTest extends KfsUnitTestBase {

    @Mock
    private BalancingService balancingService;

    @Mock
    private WrappedBatchExecutorService wrappedBatchExecutorService;

    @InjectMocks
    private PosterBalancingHistorySyncStep step;

    @BeforeEach
    public void setUp() {
        step.setBalancingService(balancingService);
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
        executor.execute();
        verify(balancingService).clearHistories();
    }

    @Test
    public void testExecuteReturnsFalseOnFailure() throws Exception {
        when(wrappedBatchExecutorService.execute((List) isNull(), any(CustomBatchExecutor.class))).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }

}
