package org.kuali.kfs.module.ar.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.batch.service.CustomerLoadService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerLoadStepTest extends KfsUnitTestBase {

    @InjectMocks
    private CustomerLoadStep customerLoadStep;

    @Mock
    private CustomerLoadService batchService;

    @Test
    void testExecuteReturnsTrueOnSuccess() throws InterruptedException {
        when(batchService.loadFiles()).thenReturn(true);

        boolean result = customerLoadStep.execute("testJob", new Date());
        assertThat(result).isTrue();
        verify(batchService).loadFiles();
    }

    @Test
    void testExecuteReturnsFalseOnFailure() throws InterruptedException {
        when(batchService.loadFiles()).thenReturn(false);

        boolean result = customerLoadStep.execute("testJob", new Date());
        assertThat(result).isFalse();
    }

    @Test
    void testGetRequiredDirectoryNames() {
        List<String> expected = Arrays.asList("/tmp/ar/customer");
        when(batchService.getRequiredDirectoryNames()).thenReturn(expected);

        List<String> result = customerLoadStep.getRequiredDirectoryNames();
        assertThat(result).isEqualTo(expected);
    }
}
