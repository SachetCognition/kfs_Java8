package org.kuali.kfs.module.cg.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.service.CloseService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CloseBatchStepTest extends KfsUnitTestBase {

    @Mock
    private CloseService closeService;

    private CloseBatchStep closeBatchStep;

    @BeforeEach
    void setUp() {
        closeBatchStep = new CloseBatchStep();
        closeBatchStep.setCloseService(closeService);
    }

    @Test
    void testExecuteReturnsTrue_WhenCloseServiceReturnsTrue() {
        when(closeService.close()).thenReturn(true);

        boolean result = closeBatchStep.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(closeService).close();
    }

    @Test
    void testExecuteReturnsFalse_WhenCloseServiceReturnsFalse() {
        when(closeService.close()).thenReturn(false);

        boolean result = closeBatchStep.execute("testJob", new Date());

        assertThat(result).isFalse();
        verify(closeService).close();
    }
}
