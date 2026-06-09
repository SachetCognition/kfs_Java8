package org.kuali.kfs.module.ld.batch;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LaborFileRenameStepTest extends KfsUnitTestBase {

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private LaborFileRenameStep step;

    @BeforeEach
    public void setUp() {
        step.setDateTimeService(dateTimeService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
    }

}
