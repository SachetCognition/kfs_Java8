package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.service.AchBankService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.MailService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class PdpEmailServiceImplTest extends KfsUnitTestBase {

    @Mock
    private MailService mailService;
    @Mock
    private ConfigurationService kualiConfigurationService;
    @Mock
    private ParameterService parameterService;
    @Mock
    private AchBankService achBankService;

    @InjectMocks
    private PdpEmailServiceImpl pdpEmailService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(pdpEmailService).isNotNull();
    }
}
