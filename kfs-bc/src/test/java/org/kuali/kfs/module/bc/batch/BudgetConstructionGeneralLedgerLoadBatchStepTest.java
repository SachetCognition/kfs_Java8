package org.kuali.kfs.module.bc.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.batch.service.GLBudgetLoadService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class BudgetConstructionGeneralLedgerLoadBatchStepTest extends KfsUnitTestBase {

    @Mock
    private GLBudgetLoadService glBudgetLoadService;

    @InjectMocks
    private BudgetConstructionGeneralLedgerLoadBatchStep step;

    @Test
    void setGLBudgetLoadService_acceptsService() {
        BudgetConstructionGeneralLedgerLoadBatchStep newStep = new BudgetConstructionGeneralLedgerLoadBatchStep();
        newStep.setGLBudgetLoadService(glBudgetLoadService);
        // no exception means setter works
        assertThat(newStep).isNotNull();
    }
}
