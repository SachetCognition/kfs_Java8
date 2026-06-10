package org.kuali.kfs.module.purap.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.service.PdpExtractService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ExtractPdpStepTest extends KfsUnitTestBase {

    @Mock
    private PdpExtractService pdpExtractService;

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private ExtractPdpStep step;

    @Test
    void executeCallsExtractPaymentsAndReturnsTrue() throws Exception {
        Date jobRunDate = new Date();
        boolean result = step.execute("testJob", jobRunDate);
        assertThat(result).isTrue();
        verify(pdpExtractService).extractPayments(any(java.sql.Date.class));
    }
}
