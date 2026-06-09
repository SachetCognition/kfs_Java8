package org.kuali.kfs.module.ar.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.ArConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.core.api.datetime.DateTimeService;

import java.io.File;
import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CustomerInvoiceWriteoffBatchInputFileTypeTest extends KfsUnitTestBase {

    @InjectMocks
    private CustomerInvoiceWriteoffBatchInputFileType fileType;

    @Mock
    private DateTimeService dateTimeService;

    @Test
    void testGetFileNameWithIdentifier() {
        Timestamp timestamp = Timestamp.valueOf("2024-03-15 10:30:00");
        when(dateTimeService.getCurrentTimestamp()).thenReturn(timestamp);
        when(dateTimeService.toString(timestamp, "yyyyMMdd_HHmmss")).thenReturn("20240315_103000");

        String fileName = fileType.getFileName("admin", null, "quarterly");
        assertThat(fileName).startsWith("customer_invoice_writeoff");
        assertThat(fileName).contains("admin");
        assertThat(fileName).contains("quarterly");
        assertThat(fileName).contains("20240315_103000");
    }

    @Test
    void testGetFileNameWithoutIdentifier() {
        Timestamp timestamp = Timestamp.valueOf("2024-03-15 10:30:00");
        when(dateTimeService.getCurrentTimestamp()).thenReturn(timestamp);
        when(dateTimeService.toString(timestamp, "yyyyMMdd_HHmmss")).thenReturn("20240315_103000");

        String fileName = fileType.getFileName("admin", null, "");
        assertThat(fileName).startsWith("customer_invoice_writeoff");
        assertThat(fileName).contains("admin");
        assertThat(fileName).doesNotContain("  ");
    }

    @Test
    void testGetFileNameStripsSpaces() {
        Timestamp timestamp = Timestamp.valueOf("2024-03-15 10:30:00");
        when(dateTimeService.getCurrentTimestamp()).thenReturn(timestamp);
        when(dateTimeService.toString(timestamp, "yyyyMMdd_HHmmss")).thenReturn("20240315_103000");

        String fileName = fileType.getFileName("user name", null, "file id");
        assertThat(fileName).doesNotContain(" ");
    }

    @Test
    void testGetFileTypeIdentifier() {
        String identifier = fileType.getFileTypeIdentifer();
        assertThat(identifier).isEqualTo(ArConstants.CustomerInvoiceWriteoff.CUSTOMER_INVOICE_WRITEOFF_FILE_TYPE_IDENTIFIER);
    }

    @Test
    void testValidateAlwaysReturnsTrue() {
        assertThat(fileType.validate(new Object())).isTrue();
        assertThat(fileType.validate(null)).isTrue();
    }

    @Test
    void testGetTitleKeyReturnsEmpty() {
        assertThat(fileType.getTitleKey()).isEmpty();
    }

    @Test
    void testGetAuthorPrincipalNameReturnsNull() {
        File file = new File("/tmp/test.xml");
        assertThat(fileType.getAuthorPrincipalName(file)).isNull();
    }
}
