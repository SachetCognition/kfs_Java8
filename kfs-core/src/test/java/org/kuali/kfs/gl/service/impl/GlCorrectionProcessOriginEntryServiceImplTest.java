package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class GlCorrectionProcessOriginEntryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private GlCorrectionProcessOriginEntryServiceImpl glCorrectionProcessOriginEntryService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(glCorrectionProcessOriginEntryService).isNotNull();
    }
}
