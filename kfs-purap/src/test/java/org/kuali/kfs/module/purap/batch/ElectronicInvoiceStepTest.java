package org.kuali.kfs.module.purap.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.businessobject.ElectronicInvoiceLoad;
import org.kuali.kfs.module.purap.service.ElectronicInvoiceHelperService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ElectronicInvoiceStepTest extends KfsUnitTestBase {

    @Mock
    private ElectronicInvoiceHelperService electronicInvoiceHelperService;

    @InjectMocks
    private ElectronicInvoiceStep step;

    @Test
    void executeAlwaysReturnsTrue() {
        ElectronicInvoiceLoad load = new ElectronicInvoiceLoad();
        when(electronicInvoiceHelperService.loadElectronicInvoices()).thenReturn(load);
        boolean result = step.execute("testJob", new Date());
        assertThat(result).isTrue();
        verify(electronicInvoiceHelperService).loadElectronicInvoices();
    }

    @Test
    void getRequiredDirectoryNamesDelegatesToService() {
        List<String> dirs = Arrays.asList("/tmp/einvoice/accept", "/tmp/einvoice/reject");
        when(electronicInvoiceHelperService.getRequiredDirectoryNames()).thenReturn(dirs);
        assertThat(step.getRequiredDirectoryNames()).isEqualTo(dirs);
    }
}
