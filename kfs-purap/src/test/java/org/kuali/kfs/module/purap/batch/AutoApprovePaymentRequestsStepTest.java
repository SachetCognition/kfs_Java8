package org.kuali.kfs.module.purap.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.document.service.PaymentRequestService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AutoApprovePaymentRequestsStepTest extends KfsUnitTestBase {

    @Mock
    private PaymentRequestService paymentRequestService;

    @InjectMocks
    private AutoApprovePaymentRequestsStep step;

    @Test
    void executeCallsAutoApproveAndReturnsTrue() throws Exception {
        when(paymentRequestService.autoApprovePaymentRequests()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertThat(result).isTrue();
        verify(paymentRequestService).autoApprovePaymentRequests();
    }

    @Test
    void executeReturnsFalseWhenServiceReturnsFalse() throws Exception {
        when(paymentRequestService.autoApprovePaymentRequests()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertThat(result).isFalse();
    }
}
