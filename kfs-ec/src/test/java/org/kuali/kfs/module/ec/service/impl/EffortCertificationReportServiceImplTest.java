package org.kuali.kfs.module.ec.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportDefinition;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.ReportGenerationService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

class EffortCertificationReportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ReportGenerationService reportGenerationService;

    @InjectMocks
    private EffortCertificationReportServiceImpl service;

    @Test
    @DisplayName("Service should be instantiated with injected dependencies")
    void serviceShouldBeInstantiated() {
        assertThat(service).isNotNull();
    }
}
