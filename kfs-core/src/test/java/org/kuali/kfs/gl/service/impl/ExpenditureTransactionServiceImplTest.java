package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.ExpenditureTransactionDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

class ExpenditureTransactionServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private ExpenditureTransactionServiceImpl expenditureTransactionService;

    @Mock
    private ExpenditureTransactionDao expenditureTransactionDao;

    @Test
    void deleteAllExpenditureTransactions_delegatesToDao() {
        expenditureTransactionService.deleteAllExpenditureTransactions();

        verify(expenditureTransactionDao).deleteAllExpenditureTransactions();
    }
}
