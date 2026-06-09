package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.businessobject.OriginEntryStatistics;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.gl.service.OriginEntryGroupService;
import org.kuali.rice.core.api.datetime.DateTimeService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LaborOriginEntryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private OriginEntryGroupService originEntryGroupService;

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private LaborOriginEntryServiceImpl service;

    private File tempDir;

    @BeforeEach
    void setUpTempDir() {
        tempDir = new File(System.getProperty("java.io.tmpdir"), "labor-origin-entry-test-" + System.nanoTime());
        tempDir.mkdirs();
    }

    @Test
    void testGetStatistics_emptyFile() throws IOException {
        File testFile = new File(tempDir, "empty.txt");
        testFile.createNewFile();

        OriginEntryStatistics stats = service.getStatistics(testFile.getAbsolutePath());
        assertNotNull(stats);
        assertEquals(0, stats.getRowCount());
    }

    @Test
    void testGetStatistics_fileNotFound() {
        try {
            service.getStatistics("/nonexistent/file.txt");
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // expected
        }
    }

    @Test
    void testGetStatistics_validDebitEntry() throws IOException {
        File testFile = new File(tempDir, "debit.txt");
        FileWriter writer = new FileWriter(testFile);
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 109; i++) {
            line.append(' ');
        }
        line.append("          1000.00"); // 17 chars for amount
        line.append("D"); // debit code at position 126
        writer.write(line.toString() + "\n");
        writer.close();

        OriginEntryStatistics stats = service.getStatistics(testFile.getAbsolutePath());
        assertNotNull(stats);
        assertEquals(1, stats.getRowCount());
    }

    @Test
    void testGetStatistics_validCreditEntry() throws IOException {
        File testFile = new File(tempDir, "credit.txt");
        FileWriter writer = new FileWriter(testFile);
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 109; i++) {
            line.append(' ');
        }
        line.append("           500.00"); // 17 chars for amount
        line.append("C"); // credit code at position 126
        writer.write(line.toString() + "\n");
        writer.close();

        OriginEntryStatistics stats = service.getStatistics(testFile.getAbsolutePath());
        assertNotNull(stats);
        assertEquals(1, stats.getRowCount());
    }

    @Test
    void testGetStatistics_multipleEntries() throws IOException {
        File testFile = new File(tempDir, "multi.txt");
        FileWriter writer = new FileWriter(testFile);
        for (int entry = 0; entry < 3; entry++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < 109; i++) {
                line.append(' ');
            }
            line.append("           100.00"); // 17 chars
            line.append("D");
            writer.write(line.toString() + "\n");
        }
        writer.close();

        OriginEntryStatistics stats = service.getStatistics(testFile.getAbsolutePath());
        assertNotNull(stats);
        assertEquals(3, stats.getRowCount());
    }
}
