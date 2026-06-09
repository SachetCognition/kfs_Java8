package org.kuali.kfs.module.ar.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.service.ContractsGrantsInvoiceCreateDocumentService;
import org.kuali.kfs.integration.ar.AccountsReceivableModuleBillingService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContractsGrantsInvoiceRouteDocumentsStepTest extends KfsUnitTestBase {

    @Mock
    private ContractsGrantsInvoiceCreateDocumentService cgInvoiceDocumentCreateService;

    @Mock
    private AccountsReceivableModuleBillingService accountsReceivableModuleBillingService;

    private ContractsGrantsInvoiceRouteDocumentsStep step;

    @BeforeEach
    public void setUp() {
        step = new ContractsGrantsInvoiceRouteDocumentsStep();
        step.setCgInvoiceDocumentCreateService(cgInvoiceDocumentCreateService);
        step.setAccountsReceivableModuleBillingService(accountsReceivableModuleBillingService);
    }

    @Test
    public void testStepInstantiation() {
        assertNotNull(step);
    }

    @Test
    public void testExecuteWhenBillingNotActive() throws Exception {
        when(accountsReceivableModuleBillingService.isContractsGrantsBillingEnhancementActive()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verifyNoInteractions(cgInvoiceDocumentCreateService);
    }
}
