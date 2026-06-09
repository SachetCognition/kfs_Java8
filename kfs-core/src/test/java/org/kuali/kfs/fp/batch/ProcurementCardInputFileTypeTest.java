package org.kuali.kfs.fp.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.File;
import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ProcurementCardInputFileTypeTest extends KfsUnitTestBase {

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private ProcurementCardInputFileType fileType;

    @Test
    void getFileTypeIdentifierReturnsPcdoIdentifier() {
        assertThat(fileType.getFileTypeIdentifer()).isEqualTo(KFSConstants.PCDO_FILE_TYPE_INDENTIFIER);
    }

    @Test
    void getFileNameIncludesPrincipalName() {
        java.util.Date now = new java.util.Date();
        when(dateTimeService.getCurrentDate()).thenReturn(now);
        when(dateTimeService.toDateTimeStringForFilename(now)).thenReturn("20240615_120000");

        String fileName = fileType.getFileName("testuser", null, "");
        assertThat(fileName).startsWith("pcdo_testuser");
    }

    @Test
    void getFileNameIncludesUserIdentifier() {
        java.util.Date now = new java.util.Date();
        when(dateTimeService.getCurrentDate()).thenReturn(now);
        when(dateTimeService.toDateTimeStringForFilename(now)).thenReturn("20240615_120000");

        String fileName = fileType.getFileName("testuser", null, "batch1");
        assertThat(fileName).contains("batch1");
    }

    @Test
    void getFileNameRemovesSpaces() {
        java.util.Date now = new java.util.Date();
        when(dateTimeService.getCurrentDate()).thenReturn(now);
        when(dateTimeService.toDateTimeStringForFilename(now)).thenReturn("20240615_120000");

        String fileName = fileType.getFileName("test user", null, "");
        assertThat(fileName).doesNotContain(" ");
    }

    @Test
    void getAuthorPrincipalNameExtractsFromFilename() {
        File file = new File("pcdo_testuser_20240615.xml");
        String author = fileType.getAuthorPrincipalName(file);
        assertThat(author).isEqualTo("testuser");
    }

    @Test
    void getAuthorPrincipalNameReturnsNullForShortFilename() {
        File file = new File("pcdo.xml");
        String author = fileType.getAuthorPrincipalName(file);
        assertThat(author).isNull();
    }
}
