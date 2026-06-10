package org.kuali.kfs.module.bc.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.batch.service.GenesisService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetConstructionUpdateBatchStepTest extends KfsUnitTestBase {

    @Mock
    private GenesisService genesisService;

    @Test
    void setGenesisService_setsField() {
        BudgetConstructionUpdateBatchStep step = new BudgetConstructionUpdateBatchStep();
        step.setGenesisService(genesisService);
        assertThat(step.genesisService).isSameAs(genesisService);
    }
}
