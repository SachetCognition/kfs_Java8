package org.kuali.kfs.sys.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.batch.service.FiscalYearMakerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FiscalYearMakerStepTest extends KfsUnitTestBase {

    @Mock
    private FiscalYearMakerService fiscalYearMakerService;

    @InjectMocks
    private FiscalYearMakerStep step;

    @BeforeEach
    public void setUp() {
        step.setFiscalYearMakerService(fiscalYearMakerService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(fiscalYearMakerService).runProcess();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(fiscalYearMakerService, atLeastOnce()).runProcess();
    }

}
