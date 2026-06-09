package org.kuali.kfs.module.purap.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.service.PdpExtractService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class ExtractPdpStepTest extends KfsUnitTestBase {

    @Mock
    private PdpExtractService pdpExtractService;

    @Mock
    private DateTimeService dateTimeService;

    private ExtractPdpStep step;

    @BeforeEach
    public void setUp() {
        step = new ExtractPdpStep();
        step.setPdpExtractService(pdpExtractService);
        step.setDateTimeService(dateTimeService);
    }

    @Test
    public void testStepInstantiation() {
        assertNotNull(step);
    }

    @Test
    public void testExecuteCallsService() throws Exception {
        step.execute("testJob", new Date());
        verify(pdpExtractService).extractPayments(any(java.sql.Date.class));
    }
}
