package org.kuali.kfs.module.ar.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.batch.service.LockboxLoadService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LockboxLoadStepTest extends KfsUnitTestBase {

    @Mock
    private LockboxLoadService lockboxLoadService;

    @InjectMocks
    private LockboxLoadStep step;

    @BeforeEach
    public void setUp() {
        step.setLockboxLoadService(lockboxLoadService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(lockboxLoadService.loadFile()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(lockboxLoadService).loadFile();
    }

    @Test
    public void testExecuteReturnsFalse() throws Exception {
        when(lockboxLoadService.loadFile()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }

}
