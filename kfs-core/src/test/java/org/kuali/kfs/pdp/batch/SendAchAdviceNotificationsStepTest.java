package org.kuali.kfs.pdp.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.batch.service.AchAdviceNotificationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SendAchAdviceNotificationsStepTest extends KfsUnitTestBase {

    @Mock
    private AchAdviceNotificationService achAdviceNotificationService;

    @InjectMocks
    private SendAchAdviceNotificationsStep step;

    @BeforeEach
    public void setUp() {
        step.setAchAdviceNotificationService(achAdviceNotificationService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(achAdviceNotificationService).sendAdviceNotifications();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(achAdviceNotificationService, atLeastOnce()).sendAdviceNotifications();
    }

}
