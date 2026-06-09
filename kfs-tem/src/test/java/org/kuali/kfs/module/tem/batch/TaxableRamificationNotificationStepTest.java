package org.kuali.kfs.module.tem.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.batch.service.TaxableRamificationNotificationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaxableRamificationNotificationStepTest extends KfsUnitTestBase {

    @Mock
    private TaxableRamificationNotificationService taxableRamificationNotificationService;

    @InjectMocks
    private TaxableRamificationNotificationStep step;

    @BeforeEach
    public void setUp() {
        step.setTaxableRamificationNotificationService(taxableRamificationNotificationService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(taxableRamificationNotificationService).sendTaxableRamificationReport();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(taxableRamificationNotificationService, atLeastOnce()).sendTaxableRamificationReport();
    }

}
