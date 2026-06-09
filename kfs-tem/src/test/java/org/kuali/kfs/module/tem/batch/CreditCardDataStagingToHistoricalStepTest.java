package org.kuali.kfs.module.tem.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.batch.service.CreditCardDataImportService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreditCardDataStagingToHistoricalStepTest extends KfsUnitTestBase {

    @Mock
    private CreditCardDataImportService creditCardDataImportService;

    @InjectMocks
    private CreditCardDataStagingToHistoricalStep step;

    @BeforeEach
    public void setUp() {
        step.setCreditCardDataImportService(creditCardDataImportService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(creditCardDataImportService.moveCreditCardDataToHistoricalExpenseTable()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(creditCardDataImportService).moveCreditCardDataToHistoricalExpenseTable();
    }

    @Test
    public void testExecuteReturnsFalse() throws Exception {
        when(creditCardDataImportService.moveCreditCardDataToHistoricalExpenseTable()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }

}
