package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.kuali.kfs.pdp.businessobject.ACHBank;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AchBankServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private AchBankServiceImpl achBankService;

    @BeforeEach
    void setUp() {
        achBankService.setBusinessObjectService(businessObjectService);
    }

    @Test
    void testGetByPrimaryId() {
        ACHBank expectedBank = new ACHBank();
        expectedBank.setBankRoutingNumber("111000025");
        expectedBank.setBankName("Test Bank");

        when(businessObjectService.findByPrimaryKey(eq(ACHBank.class), anyMap())).thenReturn(expectedBank);

        ACHBank result = achBankService.getByPrimaryId("111000025");

        assertThat(result).isNotNull();
        assertThat(result.getBankRoutingNumber()).isEqualTo("111000025");
        assertThat(result.getBankName()).isEqualTo("Test Bank");
        verify(businessObjectService).findByPrimaryKey(eq(ACHBank.class), anyMap());
    }

    @Test
    void testGetByPrimaryId_notFound() {
        when(businessObjectService.findByPrimaryKey(eq(ACHBank.class), anyMap())).thenReturn(null);

        ACHBank result = achBankService.getByPrimaryId("999999999");

        assertThat(result).isNull();
    }

    @Test
    void testReloadTable_success(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("achbank.txt").toFile();
        // Valid 155-char ACH bank records per Federal Reserve format
        String record1 = "074914274O0710003011020207000000000UNITED COMMERCE BANK                211 SOUTH COLLEGE AVENUE            BLOOMINGTON         IN474040000812336226511     ";
        String record2 = "091000019O0910000191101220000000000FEDERAL RESERVE BANK                1000 PEACHTREE ST NE                ATLANTA             GA303094470404555678911     ";
        try (FileWriter fw = new FileWriter(testFile)) {
            fw.write(record1 + "\n");
            fw.write(record2 + "\n");
        }

        boolean result = achBankService.reloadTable(testFile.getAbsolutePath());

        assertThat(result).isTrue();
        verify(businessObjectService).deleteMatching(eq(ACHBank.class), anyMap());
        verify(businessObjectService, times(2)).save(any(ACHBank.class));
    }

    @Test
    void testReloadTable_fileNotFound() {
        boolean result = achBankService.reloadTable("/nonexistent/path/achbank.txt");

        assertThat(result).isFalse();
        verify(businessObjectService).deleteMatching(eq(ACHBank.class), anyMap());
        verify(businessObjectService, never()).save(any(ACHBank.class));
    }

    @Test
    void testReloadTable_emptyFile(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("empty.txt").toFile();
        testFile.createNewFile();

        boolean result = achBankService.reloadTable(testFile.getAbsolutePath());

        assertThat(result).isTrue();
        verify(businessObjectService).deleteMatching(eq(ACHBank.class), anyMap());
        verify(businessObjectService, never()).save(any(ACHBank.class));
    }

    @Test
    void testReloadTable_blankLinesSkipped(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("blanks.txt").toFile();
        String record = "074914274O0710003011020207000000000UNITED COMMERCE BANK                211 SOUTH COLLEGE AVENUE            BLOOMINGTON         IN474040000812336226511     ";
        try (FileWriter fw = new FileWriter(testFile)) {
            fw.write("\n");
            fw.write("   \n");
            fw.write(record + "\n");
            fw.write("\n");
        }

        boolean result = achBankService.reloadTable(testFile.getAbsolutePath());

        assertThat(result).isTrue();
        verify(businessObjectService, times(1)).save(any(ACHBank.class));
    }
}
