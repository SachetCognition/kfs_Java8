package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class ElectronicPaymentClaimingServiceImplTest extends KfsUnitTestBase {

    @Mock
    private DocumentService documentService;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private ParameterService parameterService;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private ElectronicPaymentClaimingServiceImpl electronicPaymentClaimingService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(electronicPaymentClaimingService).isNotNull();
    }
}
