package org.kuali.kfs.sys.batch;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class PurgeOldLookupResultsStepTest extends KfsUnitTestBase {

    @Mock
    private LookupResultsService lookupResultsService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private DateTimeService dateTimeService;

    private PurgeOldLookupResultsStep step;

    @BeforeEach
    public void setUp() {
        step = new PurgeOldLookupResultsStep();
        step.setLookupResultsService(lookupResultsService);
        step.setParameterService(parameterService);
        step.setDateTimeService(dateTimeService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(parameterService.getParameterValueAsString(any(Class.class), any(String.class))).thenReturn("3600");
        when(dateTimeService.getCurrentCalendar()).thenReturn(Calendar.getInstance());
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
    }
}
