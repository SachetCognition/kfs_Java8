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

public class ExtractPdpImmediatesStepTest extends KfsUnitTestBase {

    @Mock
    private PdpExtractService pdpExtractService;

    @Mock
    private DateTimeService dateTimeService;

    private ExtractPdpImmediatesStep step;

    @BeforeEach
    public void setUp() {
        step = new ExtractPdpImmediatesStep();
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
        verify(pdpExtractService).extractImmediatePaymentsOnly();
    }
}
