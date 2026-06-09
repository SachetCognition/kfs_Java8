package org.kuali.kfs.fp.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.service.PopulateProcurementCardDefaultIdsService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PopulateProcurementCardDefaultIdStepTest extends KfsUnitTestBase {

    @Mock
    private PopulateProcurementCardDefaultIdsService populateProcurementCardDefaultIdsService;

    @InjectMocks
    private PopulateProcurementCardDefaultIdStep step;

    @BeforeEach
    public void setUp() {
        step.setPopulateProcurementCardDefaultIdsService(populateProcurementCardDefaultIdsService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(populateProcurementCardDefaultIdsService).populateIdsOnProcurementCardDefaults();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(populateProcurementCardDefaultIdsService, atLeastOnce()).populateIdsOnProcurementCardDefaults();
    }

}
