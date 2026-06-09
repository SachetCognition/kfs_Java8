package org.kuali.kfs.gl.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.service.ExpenditureTransactionService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteAllExpenditureTransactionsStepTest extends KfsUnitTestBase {

    @Mock
    private ExpenditureTransactionService expenditureTransactionService;

    @InjectMocks
    private DeleteAllExpenditureTransactionsStep step;

    @BeforeEach
    public void setUp() {
        step.setExpenditureTransactionService(expenditureTransactionService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(expenditureTransactionService).deleteAllExpenditureTransactions();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(expenditureTransactionService, atLeastOnce()).deleteAllExpenditureTransactions();
    }

}
