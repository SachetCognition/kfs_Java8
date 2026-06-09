package org.kuali.kfs.module.tem.batch;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.batch.service.TaxableRamificationNotificationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@DisplayName("TaxableRamificationNotificationStep")
class TaxableRamificationNotificationStepTest extends KfsUnitTestBase {

    @Mock
    private TaxableRamificationNotificationService taxableRamificationNotificationService;

    @InjectMocks
    private TaxableRamificationNotificationStep step;

    @Test
    @DisplayName("should execute and always return true")
    void testExecute() throws InterruptedException {
        boolean result = step.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(taxableRamificationNotificationService).sendTaxableRamificationReport();
    }

    @Test
    @DisplayName("should set and get taxableRamificationNotificationService")
    void testSetService() {
        step.setTaxableRamificationNotificationService(taxableRamificationNotificationService);
        assertThat(step.getTaxableRamificationNotificationService()).isSameAs(taxableRamificationNotificationService);
    }
}
