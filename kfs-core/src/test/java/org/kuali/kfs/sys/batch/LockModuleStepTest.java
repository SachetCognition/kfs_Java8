package org.kuali.kfs.sys.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.batch.service.LockModuleService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LockModuleStepTest extends KfsUnitTestBase {

    @Mock
    private LockModuleService lockModuleService;

    @InjectMocks
    private LockModuleStep step;

    @BeforeEach
    public void setUp() {
        step.setLockModuleService(lockModuleService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
    }

}
