package org.kuali.kfs.module.tem.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.service.TravelEncumbranceService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TemReleaseHeldEncumbranceStepTest extends KfsUnitTestBase {

    @Mock
    private TravelEncumbranceService travelEncumbranceService;

    @InjectMocks
    private TemReleaseHeldEncumbranceStep step;

    @BeforeEach
    public void setUp() {
        step.setTravelEncumbranceService(travelEncumbranceService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(travelEncumbranceService).releaseHeldEncumbrances();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(travelEncumbranceService, atLeastOnce()).releaseHeldEncumbrances();
    }

}
