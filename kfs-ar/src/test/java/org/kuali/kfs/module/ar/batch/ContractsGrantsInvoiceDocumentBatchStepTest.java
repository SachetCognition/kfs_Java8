package org.kuali.kfs.module.ar.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.ar.AccountsReceivableModuleBillingService;
import org.kuali.kfs.module.ar.batch.service.ContractsGrantsInvoiceBatchCreateDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContractsGrantsInvoiceDocumentBatchStepTest extends KfsUnitTestBase {

    @Mock
    private AccountsReceivableModuleBillingService accountsReceivableModuleBillingService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private ContractsGrantsInvoiceBatchCreateDocumentService cgInvoiceBatchDocumentCreateService;

    @InjectMocks
    private ContractsGrantsInvoiceDocumentBatchStep step;

    @BeforeEach
    public void setUp() {
        step.setAccountsReceivableModuleBillingService(accountsReceivableModuleBillingService);
        step.setDateTimeService(dateTimeService);
        step.setCgInvoiceBatchDocumentCreateService(cgInvoiceBatchDocumentCreateService);
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
