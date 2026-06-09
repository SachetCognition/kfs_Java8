package org.kuali.kfs.sys.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.batch.service.CacheService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClearCacheStepTest extends KfsUnitTestBase {

    @Mock
    private CacheService cacheService;

    @InjectMocks
    private ClearCacheStep step;

    @BeforeEach
    public void setUp() {
        step.setCacheService(cacheService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(cacheService).clearSystemCaches();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(cacheService, atLeastOnce()).clearSystemCaches();
    }

}
