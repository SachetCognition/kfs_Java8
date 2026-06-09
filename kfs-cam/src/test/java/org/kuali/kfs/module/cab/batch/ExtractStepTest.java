package org.kuali.kfs.module.cab.batch;

import java.sql.Timestamp;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cab.batch.service.BatchExtractService;
import org.kuali.kfs.module.cab.batch.service.BatchExtractReportService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class ExtractStepTest extends KfsUnitTestBase {

    @Mock
    private BatchExtractService batchExtractService;

    @Mock
    private BatchExtractReportService batchExtractReportService;

    @Mock
    private DateTimeService dateTimeService;

    private ExtractStep step;

    @BeforeEach
    public void setUp() {
        step = new ExtractStep();
        step.setBatchExtractService(batchExtractService);
        step.setBatchExtractReportService(batchExtractReportService);
        step.setDateTimeService(dateTimeService);
        when(dateTimeService.getCurrentTimestamp()).thenReturn(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void testStepInstantiation() {
        assertNotNull(step);
    }

    @Test
    public void testExecuteCallsService() throws Exception {
        step.execute("testJob", new Date());
        verify(batchExtractService).performExtract(any(ExtractProcessLog.class));
    }
}
