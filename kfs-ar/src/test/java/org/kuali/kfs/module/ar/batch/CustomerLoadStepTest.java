package org.kuali.kfs.module.ar.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.batch.service.CustomerLoadService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerLoadStepTest extends KfsUnitTestBase {

    @Mock
    private CustomerLoadService batchService;

    @InjectMocks
    private CustomerLoadStep step;

    @BeforeEach
    public void setUp() {
        step.setBatchService(batchService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(batchService.loadFiles()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(batchService).loadFiles();
    }

    @Test
    public void testExecuteReturnsFalse() throws Exception {
        when(batchService.loadFiles()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }

}
