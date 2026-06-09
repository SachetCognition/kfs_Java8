package org.kuali.kfs.module.purap.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.document.service.PaymentRequestService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class ReceivingPaymentRequestStepTest extends KfsUnitTestBase {

    @Mock
    private PaymentRequestService paymentRequestService;

    @InjectMocks
    private ReceivingPaymentRequestStep step;

    @Test
    void executeCallsProcessPaymentRequestAndReturnsTrue() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertThat(result).isTrue();
        verify(paymentRequestService).processPaymentRequestInReceivingStatus();
    }
}
