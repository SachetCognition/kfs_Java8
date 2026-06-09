package org.kuali.kfs.module.ar.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.batch.service.InvoiceRecurrenceService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InvoiceRecurrenceStepTest extends KfsUnitTestBase {

    @Mock
    private InvoiceRecurrenceService invoiceRecurrenceService;

    @InjectMocks
    private InvoiceRecurrenceStep step;

    @BeforeEach
    public void setUp() {
        step.setInvoiceRecurrenceService(invoiceRecurrenceService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(invoiceRecurrenceService.processInvoiceRecurrence()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(invoiceRecurrenceService).processInvoiceRecurrence();
    }

    @Test
    public void testExecuteHandlesException() throws Exception {
        doThrow(new RuntimeException("test error")).when(invoiceRecurrenceService).processInvoiceRecurrence();
        try {
            step.execute("testJob", new Date());
            fail("Expected exception");
        } catch (RuntimeException e) {
            assertNotNull(e);
        }
    }

}
