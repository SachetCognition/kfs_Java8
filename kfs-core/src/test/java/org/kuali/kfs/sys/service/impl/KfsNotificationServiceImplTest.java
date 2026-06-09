package org.kuali.kfs.sys.service.impl;

import org.apache.velocity.app.VelocityEngine;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.MailService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class KfsNotificationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private VelocityEngine velocityEngine;
    @Mock
    private ConfigurationService configurationService;
    @Mock
    private MailService mailService;

    @InjectMocks
    private KfsNotificationServiceImpl kfsNotificationService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(kfsNotificationService).isNotNull();
    }
}
