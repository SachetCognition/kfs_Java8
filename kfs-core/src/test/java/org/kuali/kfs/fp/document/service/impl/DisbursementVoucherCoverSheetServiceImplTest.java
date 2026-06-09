package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.PersistenceStructureService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class DisbursementVoucherCoverSheetServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private PersistenceStructureService persistenceStructureService;

    @InjectMocks
    private DisbursementVoucherCoverSheetServiceImpl service;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(service).isNotNull();
    }
}
