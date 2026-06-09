package org.kuali.kfs.fp.batch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.kuali.kfs.fp.batch.service.ProcurementCardLoadTransactionsService;
import org.kuali.kfs.sys.batch.BatchInputFileType;
import org.kuali.kfs.sys.batch.service.BatchInputFileService;
import org.kuali.kfs.sys.batch.service.WrappingBatchService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.ReportWriterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ProcurementCardLoadStepTest extends KfsUnitTestBase {

    @Mock
    private ProcurementCardLoadTransactionsService procurementCardLoadTransactionsService;

    @Mock
    private BatchInputFileService batchInputFileService;

    @Mock
    private BatchInputFileType procurementCardInputFileType;

    @Mock(extraInterfaces = WrappingBatchService.class)
    private ReportWriterService reportWriterService;

    @InjectMocks
    private ProcurementCardLoadStep step;

    @Test
    void executeReturnsTrueWhenNoFiles() {
        when(batchInputFileService.listInputFileNamesWithDoneFile(any())).thenReturn(new ArrayList<>());

        boolean result = step.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(procurementCardLoadTransactionsService).cleanTransactionsTable();
    }

    @Test
    void executeReturnsTrueWhenAllFilesProcessedSuccessfully() {
        List<String> files = Arrays.asList("file1.xml", "file2.xml");
        when(batchInputFileService.listInputFileNamesWithDoneFile(any())).thenReturn(files);
        when(procurementCardLoadTransactionsService.loadProcurementCardFile(any(), any())).thenReturn(true);

        boolean result = step.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(procurementCardLoadTransactionsService, times(2)).loadProcurementCardFile(any(), any());
    }

    @Test
    void executeReturnsFalseWhenFileProcessingFails() {
        List<String> files = Arrays.asList("file1.xml");
        when(batchInputFileService.listInputFileNamesWithDoneFile(any())).thenReturn(files);
        when(procurementCardLoadTransactionsService.loadProcurementCardFile(any(), any())).thenReturn(false);

        boolean result = step.execute("testJob", new Date());

        assertThat(result).isFalse();
    }

    @Test
    void executeRemovesDoneFiles(@TempDir Path tempDir) throws IOException {
        File doneFile = tempDir.resolve("file1.done").toFile();
        doneFile.createNewFile();
        String dataFile = tempDir.resolve("file1.xml").toString();

        List<String> files = Arrays.asList(dataFile);
        when(batchInputFileService.listInputFileNamesWithDoneFile(any())).thenReturn(files);
        when(procurementCardLoadTransactionsService.loadProcurementCardFile(any(), any())).thenReturn(true);

        step.execute("testJob", new Date());

        assertThat(doneFile).doesNotExist();
    }

    @Test
    void executeCleansTransactionsTableFirst() {
        when(batchInputFileService.listInputFileNamesWithDoneFile(any())).thenReturn(new ArrayList<>());

        step.execute("testJob", new Date());

        verify(procurementCardLoadTransactionsService).cleanTransactionsTable();
    }

    @Test
    void executeReturnsTrueWhenFirstFileFailsButLastSucceeds() {
        // Pre-existing bug: processSuccess is overwritten each iteration (line 72),
        // so only the last file's result determines the return value.
        List<String> files = Arrays.asList("file1.xml", "file2.xml");
        when(batchInputFileService.listInputFileNamesWithDoneFile(any())).thenReturn(files);
        when(procurementCardLoadTransactionsService.loadProcurementCardFile(eq("file1.xml"), any())).thenReturn(false);
        when(procurementCardLoadTransactionsService.loadProcurementCardFile(eq("file2.xml"), any())).thenReturn(true);

        boolean result = step.execute("testJob", new Date());

        // Returns true despite file1.xml failure — documents current (buggy) behavior
        assertThat(result).isTrue();
    }

    @Test
    void executeReturnsFalseWhenLastFileFails() {
        List<String> files = Arrays.asList("file1.xml", "file2.xml");
        when(batchInputFileService.listInputFileNamesWithDoneFile(any())).thenReturn(files);
        when(procurementCardLoadTransactionsService.loadProcurementCardFile(eq("file1.xml"), any())).thenReturn(true);
        when(procurementCardLoadTransactionsService.loadProcurementCardFile(eq("file2.xml"), any())).thenReturn(false);

        boolean result = step.execute("testJob", new Date());

        assertThat(result).isFalse();
    }
}
