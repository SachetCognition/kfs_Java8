package org.kuali.kfs.module.tem.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.batch.service.TravelImportedExpenseNotificationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TravelImportedExpenseNotificationStepTest extends KfsUnitTestBase {

    @Mock
    private TravelImportedExpenseNotificationService travelImportedExpenseNotificationService;

    @InjectMocks
    private TravelImportedExpenseNotificationStep step;

    @BeforeEach
    public void setUp() {
        step.setTravelImportedExpenseNotificationService(travelImportedExpenseNotificationService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(travelImportedExpenseNotificationService).sendImportedExpenseNotification();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(travelImportedExpenseNotificationService, atLeastOnce()).sendImportedExpenseNotification();
    }

}
