package org.kuali.kfs.pdp.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.batch.service.DailyReportService;
import org.kuali.kfs.sys.batch.service.WrappedBatchExecutorService.CustomBatchExecutor;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DailyReportStepTest extends KfsUnitTestBase {

    @Mock
    private DailyReportService dailyReportService;

    @InjectMocks
    private DailyReportStep dailyReportStep;

    @BeforeEach
    void setUp() {
        dailyReportStep.setDailyReportService(dailyReportService);
    }

    @Test
    void testGetCustomBatchExecutor_callsRunReport() {
        CustomBatchExecutor executor = dailyReportStep.getCustomBatchExecutor();

        assertThat(executor).isNotNull();
        boolean result = executor.execute();

        assertThat(result).isTrue();
        verify(dailyReportService).runReport();
    }
}
