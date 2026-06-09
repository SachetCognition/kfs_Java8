package org.kuali.kfs.pdp.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.service.PendingTransactionService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClearPendingTransactionsStepTest extends KfsUnitTestBase {

    @Mock
    private PendingTransactionService pendingTransactionService;

    @InjectMocks
    private ClearPendingTransactionsStep step;

    @BeforeEach
    public void setUp() {
        step.setPendingTransactionService(pendingTransactionService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(pendingTransactionService).clearExtractedTransactions();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(pendingTransactionService, atLeastOnce()).clearExtractedTransactions();
    }

}
