package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.PersistenceService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class IndirectCostRecoveryRateDetailInactivationBlockingDetectionServiceImplTest extends KfsUnitTestBase {

    @Mock
    private PersistenceService persistenceService;
    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private IndirectCostRecoveryRateDetailInactivationBlockingDetectionServiceImpl service;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(service).isNotNull();
    }
}
