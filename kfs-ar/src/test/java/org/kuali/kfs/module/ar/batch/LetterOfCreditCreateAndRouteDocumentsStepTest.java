package org.kuali.kfs.module.ar.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.ar.AccountsReceivableModuleBillingService;
import org.kuali.kfs.module.ar.batch.service.LetterOfCreditCreateService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LetterOfCreditCreateAndRouteDocumentsStepTest extends KfsUnitTestBase {

    @Mock
    private AccountsReceivableModuleBillingService accountsReceivableModuleBillingService;

    @Mock
    private LetterOfCreditCreateService letterOfCreditCreateService;

    @InjectMocks
    private LetterOfCreditCreateAndRouteDocumentsStep step;

    @BeforeEach
    public void setUp() {
        step.setAccountsReceivableModuleBillingService(accountsReceivableModuleBillingService);
        step.setLetterOfCreditCreateService(letterOfCreditCreateService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(accountsReceivableModuleBillingService).isContractsGrantsBillingEnhancementActive();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(accountsReceivableModuleBillingService, atLeastOnce()).isContractsGrantsBillingEnhancementActive();
    }

}
