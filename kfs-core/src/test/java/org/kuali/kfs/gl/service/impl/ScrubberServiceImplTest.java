package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.batch.service.ScrubberProcess;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

class ScrubberServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ScrubberProcess reportOnlyScrubberProcess;
    @Mock
    private ScrubberProcess scrubberProcess;
    @Mock
    private ScrubberProcess demergerScrubberProcess;

    @InjectMocks
    private ScrubberServiceImpl scrubberService;

    @Test
    void scrubEntries_delegatesToProcess() {
        scrubberService.scrubEntries();
        verify(scrubberProcess).scrubEntries();
    }

    @Test
    void scrubGroupReportOnly_delegatesToProcess() {
        scrubberService.scrubGroupReportOnly("testFile.data", "DOC123");
        verify(reportOnlyScrubberProcess).scrubGroupReportOnly("testFile.data", "DOC123");
    }
}
