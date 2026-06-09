package org.kuali.kfs.module.cab.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cab.batch.service.BatchExtractService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PreAssetTaggingStepTest extends KfsUnitTestBase {

    @Mock
    private BatchExtractService batchExtractService;

    @Mock
    private DateTimeService dateTimeService;

    private PreAssetTaggingStep step;

    @BeforeEach
    public void setUp() {
        step = new PreAssetTaggingStep();
        step.setBatchExtractService(batchExtractService);
        step.setDateTimeService(dateTimeService);
    }

    @Test
    public void testStepInstantiation() {
        assertNotNull(step);
    }

    @Test
    public void testExecuteCallsService() throws Exception {
        when(dateTimeService.getCurrentSqlDate()).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(batchExtractService).findPreTaggablePOAccounts();
    }
}
