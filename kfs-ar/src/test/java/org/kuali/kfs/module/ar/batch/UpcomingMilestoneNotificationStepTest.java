package org.kuali.kfs.module.ar.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.ar.AccountsReceivableModuleBillingService;
import org.kuali.kfs.module.ar.batch.service.UpcomingMilestoneNotificationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

class UpcomingMilestoneNotificationStepTest extends KfsUnitTestBase {

    @InjectMocks
    private UpcomingMilestoneNotificationStep step;

    @Mock
    private AccountsReceivableModuleBillingService accountsReceivableModuleBillingService;

    @Mock
    private UpcomingMilestoneNotificationService upcomingMilestoneNotificationService;

    @Test
    void testExecuteWhenBillingEnhancementActive() throws InterruptedException {
        when(accountsReceivableModuleBillingService.isContractsGrantsBillingEnhancementActive())
                .thenReturn(true);

        boolean result = step.execute("testJob", new Date());
        assertThat(result).isTrue();
        verify(upcomingMilestoneNotificationService).sendNotificationsForMilestones();
    }

    @Test
    void testExecuteWhenBillingEnhancementNotActive() throws InterruptedException {
        when(accountsReceivableModuleBillingService.isContractsGrantsBillingEnhancementActive())
                .thenReturn(false);

        boolean result = step.execute("testJob", new Date());
        assertThat(result).isTrue();
        verify(upcomingMilestoneNotificationService, never()).sendNotificationsForMilestones();
    }
}
