package org.kuali.kfs.module.ec.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ec.batch.service.EffortCertificationCreateService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EffortCertificationCreateStepTest extends KfsUnitTestBase {

    @Mock
    private EffortCertificationCreateService effortCertificationCreateService;

    @InjectMocks
    private EffortCertificationCreateStep step;

    @BeforeEach
    public void setUp() {
        step.setEffortCertificationCreateService(effortCertificationCreateService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(effortCertificationCreateService).create();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(effortCertificationCreateService, atLeastOnce()).create();
    }

}
