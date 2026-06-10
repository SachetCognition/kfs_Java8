package org.kuali.kfs.vnd.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.exception.ParseException;
import org.kuali.kfs.vnd.VendorConstants;
import org.kuali.kfs.vnd.VendorKeyConstants;
import org.kuali.kfs.vnd.businessobject.DebarredVendorDetail;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.Mock;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

class VendorExcludeInputFileTypeTest extends KfsUnitTestBase {

    @Mock
    private DateTimeService dateTimeService;

    private VendorExcludeInputFileType fileType;

    @BeforeEach
    void setUp() {
        fileType = new VendorExcludeInputFileType();
        fileType.setDateTimeService(dateTimeService);
    }

    @Test
    void getFileTypeIdentiferReturnsConstant() {
        assertThat(fileType.getFileTypeIdentifer()).isEqualTo(VendorConstants.VENDOR_EXCLUDE_FILE_TYPE_INDENTIFIER);
    }

    @Test
    void validateAlwaysReturnsTrue() {
        assertThat(fileType.validate(null)).isTrue();
        assertThat(fileType.validate("anything")).isTrue();
    }

    @Test
    void getTitleKeyReturnsMessageKey() {
        assertThat(fileType.getTitleKey()).isEqualTo(VendorKeyConstants.MESSAGE_BATCH_UPLOAD_VENDOR_EXCLUDE);
    }

    @Test
    void getAuthorPrincipalNameReturnsNull() {
        assertThat(fileType.getAuthorPrincipalName(new File("test.csv"))).isNull();
    }

    @Test
    void getFileNameIncludesPrefix() {
        when(dateTimeService.getCurrentDate()).thenReturn(new Date());
        when(dateTimeService.toDateTimeStringForFilename(dateTimeService.getCurrentDate())).thenReturn("20240101_120000");

        String fileName = fileType.getFileName("admin", null, "test");
        assertThat(fileName).startsWith("epls_debarred_vendors_");
    }

    @Test
    void parseHandlesValidCsvWithNameField() {
        // CSV columns: 0:Name, 1:First, 2:Mid, 3:Last, 4:Prefix, 5:Suffix,
        // 6:Address1, 7:Address2, 8:City, 9:Province, 10:State, 11:Zip,
        // 12:Country, 13:Aliases, 14-17:unused, 18:Description
        String header = "\"H0\",\"H1\",\"H2\",\"H3\",\"H4\",\"H5\",\"H6\",\"H7\",\"H8\",\"H9\",\"H10\",\"H11\",\"H12\",\"H13\",\"H14\",\"H15\",\"H16\",\"H17\",\"H18\"\n";
        String data = "\"Acme Corp\",\"\",\"\",\"\",\"\",\"\",\"123 Main St\",\"Suite 100\",\"Springfield\",\"\",\"IL\",\"62701\",\"US\",\"ACME\",\"\",\"\",\"\",\"\",\"Debarred for fraud\"\n";
        byte[] content = (header + data).getBytes(StandardCharsets.UTF_8);

        @SuppressWarnings("unchecked")
        List<DebarredVendorDetail> result = (List<DebarredVendorDetail>) fileType.parse(content);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Acme Corp");
        assertThat(result.get(0).getAddress1()).isEqualTo("123 Main St");
        assertThat(result.get(0).getCity()).isEqualTo("Springfield");
        assertThat(result.get(0).getState()).isEqualTo("IL");
        assertThat(result.get(0).getZip()).isEqualTo("62701");
        assertThat(result.get(0).getAliases()).isEqualTo("ACME");
        assertThat(result.get(0).getDescription()).isEqualTo("Debarred for fraud");
        assertThat(result.get(0).getLoadDate()).isNotNull();
    }

    @Test
    void parseUsesNamePartsWhenNameFieldEmpty() {
        // When name (index 0) is empty, the code falls into the else branch
        // that concatenates first/middle/last/prefix (indices 1-4) and suffix (index 5).
        // NOTE: Production code has inverted conditions at indices 1-4:
        //   `!StringUtils.isNotEmpty(nextLine[i])` == `isEmpty(nextLine[i])`
        // so non-empty first/middle/last/prefix values are NOT appended (pre-existing bug).
        // Only suffix (index 5) uses the correct condition `StringUtils.isNotEmpty`.
        String header = "\"H0\",\"H1\",\"H2\",\"H3\",\"H4\",\"H5\",\"H6\",\"H7\",\"H8\",\"H9\",\"H10\",\"H11\",\"H12\",\"H13\",\"H14\",\"H15\",\"H16\",\"H17\",\"H18\"\n";
        String data = "\"\",\"John\",\"M\",\"Doe\",\"Mr\",\"Jr\",\"123 Main\",\"\",\"City\",\"\",\"IL\",\"60601\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"\n";
        byte[] content = (header + data).getBytes(StandardCharsets.UTF_8);

        @SuppressWarnings("unchecked")
        List<DebarredVendorDetail> result = (List<DebarredVendorDetail>) fileType.parse(content);

        assertThat(result).hasSize(1);
        // Due to the inverted conditions bug, only suffix (index 5) is appended
        assertThat(result.get(0).getName()).isEqualTo(" Jr");
    }

    @Test
    void parseHandlesMultipleLines() {
        String csv = "\"Header1\",\"H2\",\"H3\",\"H4\",\"H5\",\"H6\",\"H7\",\"H8\",\"H9\",\"H10\",\"H11\",\"H12\",\"H13\",\"H14\",\"H15\",\"H16\",\"H17\",\"H18\",\"H19\"\n"
                + "\"Vendor A\",\"\",\"\",\"\",\"\",\"\",\"Addr A\",\"\",\"City A\",\"\",\"CA\",\"90001\",\"\",\"\",\"\",\"\",\"\",\"\",\"Desc A\"\n"
                + "\"Vendor B\",\"\",\"\",\"\",\"\",\"\",\"Addr B\",\"\",\"City B\",\"\",\"NY\",\"10001\",\"\",\"\",\"\",\"\",\"\",\"\",\"Desc B\"\n";
        byte[] content = csv.getBytes(StandardCharsets.UTF_8);

        @SuppressWarnings("unchecked")
        List<DebarredVendorDetail> result = (List<DebarredVendorDetail>) fileType.parse(content);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Vendor A");
        assertThat(result.get(1).getName()).isEqualTo("Vendor B");
    }

    @Test
    void fieldSizesArray() {
        assertThat(VendorExcludeInputFileType.FIELD_SIZES).hasSize(9);
        assertThat(VendorExcludeInputFileType.FIELD_SIZES[0]).isEqualTo(200);
    }

    @Test
    void processDoesNothing() {
        fileType.process("test.csv", new Object());
    }
}
