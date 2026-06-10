package org.kuali.kfs.vnd.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.vnd.batch.service.VendorExcludeService;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoadEplsFileStepTest extends KfsUnitTestBase {

    @Mock
    private VendorExcludeService vendorExcludeService;

    private LoadEplsFileStep step;

    @BeforeEach
    void setUp() {
        step = new LoadEplsFileStep();
        step.setVendorExcludeService(vendorExcludeService);
    }

    @Test
    void executeDelegatesToVendorExcludeService() throws InterruptedException {
        when(vendorExcludeService.loadEplsFile()).thenReturn(true);

        boolean result = step.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(vendorExcludeService).loadEplsFile();
    }

    @Test
    void executeReturnsFalseWhenServiceReturnsFalse() throws InterruptedException {
        when(vendorExcludeService.loadEplsFile()).thenReturn(false);

        boolean result = step.execute("testJob", new Date());

        assertThat(result).isFalse();
    }
}
