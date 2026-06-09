package org.kuali.kfs.fp.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.batch.service.ProcurementCardCreateDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProcurementCardCreateDocumentsStepTest extends KfsUnitTestBase {

    @Mock
    private ProcurementCardCreateDocumentService procurementCardDocumentService;

    private ProcurementCardCreateDocumentsStep step;

    @BeforeEach
    public void setUp() {
        step = new ProcurementCardCreateDocumentsStep();
        step.setProcurementCardCreateDocumentService(procurementCardDocumentService);
    }

    @Test
    public void testExecuteCallsService() throws Exception {
        when(procurementCardDocumentService.createProcurementCardDocuments()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(procurementCardDocumentService).createProcurementCardDocuments();
    }

    @Test
    public void testExecuteReturnsFalseWhenServiceFails() throws Exception {
        when(procurementCardDocumentService.createProcurementCardDocuments()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }
}
