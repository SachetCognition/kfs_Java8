package org.kuali.kfs.module.bc.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.batch.service.GenesisService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.PersistenceStructureService;

import static org.assertj.core.api.Assertions.assertThat;

class GenesisBatchStepTest extends KfsUnitTestBase {

    @Mock
    private GenesisService genesisService;

    @Mock
    private PersistenceStructureService psService;

    @Mock
    private BusinessObjectService boService;

    @Test
    void setGenesisService_setsField() {
        GenesisBatchStep step = new GenesisBatchStep();
        step.setGenesisService(genesisService);
        assertThat(step).isNotNull();
    }

    @Test
    void setPersistenceStructureService_setsField() {
        GenesisBatchStep step = new GenesisBatchStep();
        step.setPersistenceStructureService(psService);
        assertThat(step).isNotNull();
    }

    @Test
    void setBusinessObjectService_setsField() {
        GenesisBatchStep step = new GenesisBatchStep();
        step.setBusinessObjectService(boService);
        assertThat(step).isNotNull();
    }
}
