package org.kuali.kfs.module.purap.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.document.service.ReceivingService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class ApproveLineItemReceivingStepTest extends KfsUnitTestBase {

    @Mock
    private ReceivingService receivingService;

    @InjectMocks
    private ApproveLineItemReceivingStep step;

    @Test
    void executeCallsApproveReceivingDocs() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertThat(result).isTrue();
        verify(receivingService).approveReceivingDocsForPOAmendment();
    }

    @Test
    void getReceivingServiceReturnsInjectedService() {
        assertThat(step.getReceivingService()).isSameAs(receivingService);
    }
}
