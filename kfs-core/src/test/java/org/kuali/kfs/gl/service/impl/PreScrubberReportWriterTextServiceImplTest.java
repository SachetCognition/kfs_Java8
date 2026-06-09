package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.service.PreScrubberService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class PreScrubberReportWriterTextServiceImplTest extends KfsUnitTestBase {

    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private PreScrubberService preScrubberService;

    @InjectMocks
    private PreScrubberReportWriterTextServiceImpl service;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(service).isNotNull();
    }
}
