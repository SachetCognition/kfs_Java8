package org.kuali.kfs.gl.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.service.OriginEntryGroupService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MarkPostableScrubberValidGroupsAsUnpostableStepTest extends KfsUnitTestBase {

    @Mock
    private OriginEntryGroupService originEntryGroupService;

    @InjectMocks
    private MarkPostableScrubberValidGroupsAsUnpostableStep step;

    @BeforeEach
    public void setUp() {
        step.setOriginEntryGroupService(originEntryGroupService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
    }

}
