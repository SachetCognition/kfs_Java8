package org.kuali.kfs.module.tem.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.batch.service.AgencyDataImportService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AgencyDataStagingToHistoricalStepTest extends KfsUnitTestBase {

    @Mock
    private AgencyDataImportService agencyDataImportService;

    @InjectMocks
    private AgencyDataStagingToHistoricalStep step;

    @BeforeEach
    public void setUp() {
        step.setAgencyDataImportService(agencyDataImportService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(agencyDataImportService.moveAgencyDataToHistoricalExpenseTable()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(agencyDataImportService).moveAgencyDataToHistoricalExpenseTable();
    }

    @Test
    public void testExecuteReturnsFalse() throws Exception {
        when(agencyDataImportService.moveAgencyDataToHistoricalExpenseTable()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }

}
