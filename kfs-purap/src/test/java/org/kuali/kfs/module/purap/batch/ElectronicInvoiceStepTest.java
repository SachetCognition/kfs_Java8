package org.kuali.kfs.module.purap.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.service.ElectronicInvoiceHelperService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ElectronicInvoiceStepTest extends KfsUnitTestBase {

    @Mock
    private ElectronicInvoiceHelperService electronicInvoiceHelperService;

    @InjectMocks
    private ElectronicInvoiceStep step;

    @BeforeEach
    public void setUp() {
        step.setElectronicInvoiceHelperService(electronicInvoiceHelperService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(electronicInvoiceHelperService).loadElectronicInvoices();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(electronicInvoiceHelperService, atLeastOnce()).loadElectronicInvoices();
    }

}
