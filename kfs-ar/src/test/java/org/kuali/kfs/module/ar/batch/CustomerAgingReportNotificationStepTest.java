package org.kuali.kfs.module.ar.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.batch.service.CustomerNotificationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class CustomerAgingReportNotificationStepTest extends KfsUnitTestBase {

    @InjectMocks
    private CustomerAgingReportNotificationStep step;

    @Mock
    private CustomerNotificationService customerNotificationService;

    @Test
    void testExecuteCallsSendReport() throws InterruptedException {
        boolean result = step.execute("testJob", new Date());
        assertThat(result).isTrue();
        verify(customerNotificationService).sendCustomerAgingReport();
    }

    @Test
    void testGetCustomerNotificationService() {
        assertThat(step.getCustomerNotificationService()).isEqualTo(customerNotificationService);
    }
}
