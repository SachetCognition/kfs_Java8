package org.kuali.kfs.module.ar.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.batch.service.LockboxService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LockboxStepTest extends KfsUnitTestBase {

    @Mock
    private LockboxService lockboxService;

    @InjectMocks
    private LockboxStep step;

    @BeforeEach
    public void setUp() {
        step.setLockboxService(lockboxService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(lockboxService.processLockboxes()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(lockboxService).processLockboxes();
    }

    @Test
    public void testExecuteHandlesException() throws Exception {
        doThrow(new RuntimeException("test error")).when(lockboxService).processLockboxes();
        try {
            step.execute("testJob", new Date());
            fail("Expected exception");
        } catch (RuntimeException e) {
            assertNotNull(e);
        }
    }

}
