package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.service.CustomerProfileService;
import org.kuali.kfs.pdp.service.PaymentFileValidationService;
import org.kuali.kfs.pdp.service.PdpEmailService;
import org.kuali.kfs.sys.batch.service.BatchInputFileService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentFileServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;
    @Mock
    private CustomerProfileService customerProfileService;
    @Mock
    private BatchInputFileService batchInputFileService;
    @Mock
    private PaymentFileValidationService paymentFileValidationService;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private PdpEmailService paymentFileEmailService;
    @Mock
    private ConfigurationService kualiConfigurationService;

    @InjectMocks
    private PaymentFileServiceImpl paymentFileService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(paymentFileService).isNotNull();
    }
}
