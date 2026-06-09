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

class VendorMatchStepTest extends KfsUnitTestBase {

    @Mock
    private VendorExcludeService vendorExcludeService;

    private VendorMatchStep step;

    @BeforeEach
    void setUp() {
        step = new VendorMatchStep();
        step.setVendorExcludeService(vendorExcludeService);
    }

    @Test
    void executeDelegatesToVendorExcludeService() throws InterruptedException {
        when(vendorExcludeService.matchVendors()).thenReturn(true);

        boolean result = step.execute("vendorMatchJob", new Date());

        assertThat(result).isTrue();
        verify(vendorExcludeService).matchVendors();
    }

    @Test
    void executeReturnsFalseWhenServiceReturnsFalse() throws InterruptedException {
        when(vendorExcludeService.matchVendors()).thenReturn(false);

        boolean result = step.execute("vendorMatchJob", new Date());

        assertThat(result).isFalse();
    }
}
