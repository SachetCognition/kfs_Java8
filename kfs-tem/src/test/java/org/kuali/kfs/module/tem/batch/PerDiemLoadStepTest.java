package org.kuali.kfs.module.tem.batch;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.batch.service.PerDiemLoadService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("PerDiemLoadStep")
class PerDiemLoadStepTest extends KfsUnitTestBase {

    @Mock
    private PerDiemLoadService perDiemLoadService;

    @InjectMocks
    private PerDiemLoadStep perDiemLoadStep;

    @Test
    @DisplayName("should execute and delegate to perDiemLoadService")
    void testExecute() throws InterruptedException {
        when(perDiemLoadService.loadPerDiem()).thenReturn(true);

        boolean result = perDiemLoadStep.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(perDiemLoadService).loadPerDiem();
    }

    @Test
    @DisplayName("should return false when perDiemLoadService returns false")
    void testExecuteReturnsFalse() throws InterruptedException {
        when(perDiemLoadService.loadPerDiem()).thenReturn(false);

        boolean result = perDiemLoadStep.execute("testJob", new Date());

        assertThat(result).isFalse();
        verify(perDiemLoadService).loadPerDiem();
    }

    @Test
    @DisplayName("should set and get perDiemLoadService")
    void testSetPerDiemLoadService() {
        perDiemLoadStep.setPerDiemLoadService(perDiemLoadService);
        assertThat(perDiemLoadStep.getPerDiemLoadService()).isSameAs(perDiemLoadService);
    }
}
