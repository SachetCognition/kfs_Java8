package org.kuali.kfs.pdp.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.batch.service.ExtractPaymentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ExtractAchPaymentsStepTest extends KfsUnitTestBase {

    @Mock
    private ExtractPaymentService extractPaymentService;

    @InjectMocks
    private ExtractAchPaymentsStep extractAchPaymentsStep;

    @BeforeEach
    void setUp() {
        extractAchPaymentsStep.setExtractPaymentService(extractPaymentService);
    }

    @Test
    void testExecute_callsExtractAchPayments() throws InterruptedException {
        boolean result = extractAchPaymentsStep.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(extractPaymentService).extractAchPayments();
    }

    @Test
    void testGetRequiredDirectoryNames() {
        when(extractPaymentService.getRequiredDirectoryNames()).thenReturn(Arrays.asList("/staging/pdp/ach"));

        List<String> result = extractAchPaymentsStep.getRequiredDirectoryNames();

        assertThat(result).containsExactly("/staging/pdp/ach");
    }
}
