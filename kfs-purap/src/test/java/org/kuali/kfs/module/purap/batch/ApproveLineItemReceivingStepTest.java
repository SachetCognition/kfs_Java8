package org.kuali.kfs.module.purap.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.document.service.ReceivingService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ApproveLineItemReceivingStepTest extends KfsUnitTestBase {

    @Mock
    private ReceivingService receivingService;

    @InjectMocks
    private ApproveLineItemReceivingStep step;

    @BeforeEach
    public void setUp() {
        step.setReceivingService(receivingService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(receivingService).approveReceivingDocsForPOAmendment();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(receivingService, atLeastOnce()).approveReceivingDocsForPOAmendment();
    }

}
