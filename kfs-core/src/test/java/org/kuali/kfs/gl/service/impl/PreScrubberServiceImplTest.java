package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class PreScrubberServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private PreScrubberServiceImpl preScrubberService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(preScrubberService).isNotNull();
    }
}
