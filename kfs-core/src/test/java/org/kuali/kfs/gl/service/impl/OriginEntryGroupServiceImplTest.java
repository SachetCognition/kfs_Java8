package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class OriginEntryGroupServiceImplTest extends KfsUnitTestBase {

    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private KualiModuleService kualiModuleService;

    @InjectMocks
    private OriginEntryGroupServiceImpl originEntryGroupService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(originEntryGroupService).isNotNull();
    }
}
