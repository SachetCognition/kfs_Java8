package org.kuali.kfs.sys.batch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.batch.service.FilePurgeService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FilePurgeStepTest extends KfsUnitTestBase {

    @Mock
    private FilePurgeService filePurgeService;

    private FilePurgeStep step;

    @BeforeEach
    public void setUp() {
        step = new FilePurgeStep();
        step.setFilePurgeService(filePurgeService);
        step.setDirectories(new ArrayList<String>());
        step.setCustomAges(new ArrayList<FilePurgeCustomAge>());
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
    }

    @Test
    public void testExecuteWithDirectory() throws Exception {
        List<String> dirs = new ArrayList<String>();
        dirs.add("/tmp/test-purge");
        step.setDirectories(dirs);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(filePurgeService).purgeFiles(eq("/tmp/test-purge"), any(List.class));
    }
}
