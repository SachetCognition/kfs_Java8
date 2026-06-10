package org.kuali.kfs.module.tem.batch;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.batch.service.AgencyDataImportService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("AgencyDataImportStep")
class AgencyDataImportStepTest extends KfsUnitTestBase {

    @Mock
    private AgencyDataImportService agencyDataImportService;

    @InjectMocks
    private AgencyDataImportStep agencyDataImportStep;

    @Test
    @DisplayName("should execute and delegate to agencyDataImportService")
    void testExecute() throws InterruptedException {
        when(agencyDataImportService.importAgencyData()).thenReturn(true);

        boolean result = agencyDataImportStep.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(agencyDataImportService).importAgencyData();
    }

    @Test
    @DisplayName("should return false when import fails")
    void testExecuteReturnsFalse() throws InterruptedException {
        when(agencyDataImportService.importAgencyData()).thenReturn(false);

        boolean result = agencyDataImportStep.execute("testJob", new Date());

        assertThat(result).isFalse();
    }
}
