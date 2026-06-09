package org.kuali.kfs.module.ar.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.ar.AccountsReceivableModuleBillingService;
import org.kuali.kfs.module.ar.batch.service.UpcomingMilestoneNotificationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpcomingMilestoneNotificationStepTest extends KfsUnitTestBase {

    @Mock
    private AccountsReceivableModuleBillingService accountsReceivableModuleBillingService;

    @Mock
    private UpcomingMilestoneNotificationService upcomingMilestoneNotificationService;

    @InjectMocks
    private UpcomingMilestoneNotificationStep step;

    @BeforeEach
    public void setUp() {
        step.setAccountsReceivableModuleBillingService(accountsReceivableModuleBillingService);
        step.setUpcomingMilestoneNotificationService(upcomingMilestoneNotificationService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
    }

}
