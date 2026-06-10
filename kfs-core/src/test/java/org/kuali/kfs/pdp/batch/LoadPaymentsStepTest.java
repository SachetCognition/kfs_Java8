package org.kuali.kfs.pdp.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.service.PaymentFileService;
import org.kuali.kfs.sys.batch.BatchInputFileType;
import org.kuali.kfs.sys.batch.XmlBatchInputFileTypeBase;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class LoadPaymentsStepTest extends KfsUnitTestBase {

    @Mock
    private PaymentFileService paymentFileService;

    @Mock
    private BatchInputFileType paymentInputFileType;

    @InjectMocks
    private LoadPaymentsStep loadPaymentsStep;

    @BeforeEach
    void setUp() {
        loadPaymentsStep.setPaymentFileService(paymentFileService);
        loadPaymentsStep.setPaymentInputFileType(paymentInputFileType);
    }

    @Test
    void testExecute_callsProcessPaymentFiles() throws InterruptedException {
        boolean result = loadPaymentsStep.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(paymentFileService).processPaymentFiles(paymentInputFileType);
    }

    @Test
    void testGetRequiredDirectoryNames() {
        when(paymentInputFileType.getDirectoryPath()).thenReturn("/staging/pdp");
        when(paymentFileService.getRequiredDirectoryNames()).thenReturn(Arrays.asList("/staging/pdp/incoming", "/staging/pdp/done"));

        List<String> result = loadPaymentsStep.getRequiredDirectoryNames();

        assertThat(result).hasSize(3);
        assertThat(result).contains("/staging/pdp", "/staging/pdp/incoming", "/staging/pdp/done");
    }

    @Test
    void testExecute_withXmlInputFileType_schemaExists() throws Exception {
        XmlBatchInputFileTypeBase xmlType = mock(XmlBatchInputFileTypeBase.class);
        // Use a URL scheme that always resolves
        when(xmlType.getSchemaLocation()).thenReturn("file:///dev/null");

        loadPaymentsStep.setPaymentInputFileType(xmlType);

        // The file /dev/null exists on Linux so schemaResource.exists() returns true
        boolean result = loadPaymentsStep.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(paymentFileService).processPaymentFiles(xmlType);
    }

    @Test
    void testExecute_withXmlInputFileType_schemaMissing() throws Exception {
        XmlBatchInputFileTypeBase xmlType = mock(XmlBatchInputFileTypeBase.class);
        when(xmlType.getSchemaLocation()).thenReturn("file:///nonexistent/path/payment.xsd");

        loadPaymentsStep.setPaymentInputFileType(xmlType);

        assertThatThrownBy(() -> loadPaymentsStep.execute("testJob", new Date()))
                .isInstanceOf(RuntimeException.class);
    }
}
