package org.kuali.kfs.module.purap.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.document.service.FaxBatchDocumentsService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FaxPendingDocumentStepTest extends KfsUnitTestBase {

    @Mock
    private FaxBatchDocumentsService faxBatchDocumentsService;

    @InjectMocks
    private FaxPendingDocumentStep step;

    @BeforeEach
    public void setUp() {
        step.setFaxBatchDocumentsService(faxBatchDocumentsService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(faxBatchDocumentsService.faxPendingPurchaseOrders()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(faxBatchDocumentsService).faxPendingPurchaseOrders();
    }

    @Test
    public void testExecuteReturnsFalse() throws Exception {
        when(faxBatchDocumentsService.faxPendingPurchaseOrders()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }

}
