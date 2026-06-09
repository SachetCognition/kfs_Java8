package org.kuali.kfs.module.tem.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.batch.service.PerDiemLoadService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PerDiemLoadStepTest extends KfsUnitTestBase {

    @Mock
    private PerDiemLoadService perDiemLoadService;

    @InjectMocks
    private PerDiemLoadStep step;

    @BeforeEach
    public void setUp() {
        step.setPerDiemLoadService(perDiemLoadService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(perDiemLoadService.loadPerDiem()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(perDiemLoadService).loadPerDiem();
    }

    @Test
    public void testExecuteReturnsFalse() throws Exception {
        when(perDiemLoadService.loadPerDiem()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }

}
