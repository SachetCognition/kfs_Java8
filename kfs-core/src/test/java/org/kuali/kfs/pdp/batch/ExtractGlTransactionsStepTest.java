package org.kuali.kfs.pdp.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.batch.service.ExtractTransactionsService;
import org.kuali.kfs.sys.batch.service.WrappedBatchExecutorService.CustomBatchExecutor;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ExtractGlTransactionsStepTest extends KfsUnitTestBase {

    @Mock
    private ExtractTransactionsService extractTransactionsService;

    private ExtractGlTransactionsStep extractGlTransactionsStep;

    @BeforeEach
    void setUp() {
        extractGlTransactionsStep = new ExtractGlTransactionsStep();
        extractGlTransactionsStep.setExtractGlTransactionService(extractTransactionsService);
    }

    @Test
    void testGetCustomBatchExecutor_callsExtractGlTransactions() {
        CustomBatchExecutor executor = extractGlTransactionsStep.getCustomBatchExecutor();

        assertThat(executor).isNotNull();
        boolean result = executor.execute();

        assertThat(result).isTrue();
        verify(extractTransactionsService).extractGlTransactions();
    }
}
