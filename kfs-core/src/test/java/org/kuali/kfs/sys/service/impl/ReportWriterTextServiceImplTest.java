package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class ReportWriterTextServiceImplTest extends KfsUnitTestBase {

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private ReportWriterTextServiceImpl reportWriterTextService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(reportWriterTextService).isNotNull();
    }
}
