package org.kuali.kfs.module.ar.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.batch.service.CustomerNotificationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerAgingReportNotificationStepTest extends KfsUnitTestBase {

    @Mock
    private CustomerNotificationService customerNotificationService;

    @InjectMocks
    private CustomerAgingReportNotificationStep step;

    @BeforeEach
    public void setUp() {
        step.setCustomerNotificationService(customerNotificationService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(customerNotificationService).sendCustomerAgingReport();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(customerNotificationService, atLeastOnce()).sendCustomerAgingReport();
    }

}
