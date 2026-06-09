package org.kuali.kfs.module.tem.batch;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.batch.service.TemProfileExportService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@DisplayName("TemProfileExportStep")
class TemProfileExportStepTest extends KfsUnitTestBase {

    @Mock
    private TemProfileExportService temProfileExportService;

    @InjectMocks
    private TemProfileExportStep temProfileExportStep;

    @Test
    @DisplayName("should execute and delegate to temProfileExportService")
    void testExecute() throws InterruptedException {
        boolean result = temProfileExportStep.execute("testJob", new Date());

        assertThat(result).isFalse();
        verify(temProfileExportService).exportProfile();
    }

    @Test
    @DisplayName("should set temProfileExportService")
    void testSetTemProfileExportService() {
        temProfileExportStep.setTemProfileExportService(temProfileExportService);
        // verify no exception
    }
}
