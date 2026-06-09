package org.kuali.kfs.gl.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.batch.service.SufficientFundsFullRebuildService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SufficientFundsFullRebuildStepTest extends KfsUnitTestBase {

    @Mock
    private SufficientFundsFullRebuildService sufficientFundsFullRebuildService;

    @InjectMocks
    private SufficientFundsFullRebuildStep step;

    @BeforeEach
    public void setUp() {
        step.setSufficientFundsFullRebuildService(sufficientFundsFullRebuildService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(sufficientFundsFullRebuildService).syncSufficientFunds();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(sufficientFundsFullRebuildService, atLeastOnce()).syncSufficientFunds();
    }

}
