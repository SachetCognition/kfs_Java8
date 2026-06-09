package org.kuali.kfs.sys.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ReportAggregatorServiceTextImplTest extends KfsUnitTestBase {

    @TempDir
    File tempDir;

    private ReportAggregatorServiceTextImpl service;

    @BeforeEach
    void setUp() {
        service = new ReportAggregatorServiceTextImpl();
        service.setNewLineCharacter("\n");
    }

    @Test
    void aggregateReports_singleFile_copiesContent() throws IOException {
        File input = createFile("input1.txt", "Line1\nLine2");
        File output = new File(tempDir, "output.txt");

        service.aggregateReports(output, Arrays.asList(input));

        String content = new String(Files.readAllBytes(output.toPath()), StandardCharsets.UTF_8);
        assertThat(content).contains("Line1");
        assertThat(content).contains("Line2");
    }

    @Test
    void aggregateReports_multipleFiles_allIncluded() throws IOException {
        File input1 = createFile("input1.txt", "File1-Content");
        File input2 = createFile("input2.txt", "File2-Content");
        File output = new File(tempDir, "output.txt");

        service.aggregateReports(output, Arrays.asList(input1, input2));

        String content = new String(Files.readAllBytes(output.toPath()), StandardCharsets.UTF_8);
        assertThat(content).contains("File1-Content");
        assertThat(content).contains("File2-Content");
    }

    @Test
    void aggregateReports_deletesInputFiles() throws IOException {
        File input = createFile("input.txt", "content");
        File output = new File(tempDir, "output.txt");

        service.aggregateReports(output, Arrays.asList(input));

        assertThat(input).doesNotExist();
    }

    @Test
    void aggregateReports_replacesPageNumbers() throws IOException {
        File input = createFile("input.txt", "Page ${pageNumber} of report");
        File output = new File(tempDir, "output.txt");

        service.aggregateReports(output, Arrays.asList(input));

        String content = new String(Files.readAllBytes(output.toPath()), StandardCharsets.UTF_8);
        assertThat(content).contains("Page 1 of report");
        assertThat(content).doesNotContain("${pageNumber}");
    }

    @Test
    void aggregateReports_multiplePageNumbersIncrementSequentially() throws IOException {
        File input = createFile("input.txt", "Page ${pageNumber}\nPage ${pageNumber}");
        File output = new File(tempDir, "output.txt");

        service.aggregateReports(output, Arrays.asList(input));

        String content = new String(Files.readAllBytes(output.toPath()), StandardCharsets.UTF_8);
        assertThat(content).contains("Page 1");
        assertThat(content).contains("Page 2");
    }

    @Test
    void aggregateReports_pageNumbersContinueAcrossFiles() throws IOException {
        File input1 = createFile("input1.txt", "Page ${pageNumber}");
        File input2 = createFile("input2.txt", "Page ${pageNumber}");
        File output = new File(tempDir, "output.txt");

        service.aggregateReports(output, Arrays.asList(input1, input2));

        String content = new String(Files.readAllBytes(output.toPath()), StandardCharsets.UTF_8);
        assertThat(content).contains("Page 1");
        assertThat(content).contains("Page 2");
    }

    @Test
    void aggregateReports_emptyList_createsEmptyOutput() throws IOException {
        File output = new File(tempDir, "output.txt");
        service.aggregateReports(output, List.of());
        assertThat(output).exists();
        assertThat(output.length()).isZero();
    }

    private File createFile(String name, String content) throws IOException {
        File f = new File(tempDir, name);
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(content);
        }
        return f;
    }
}
