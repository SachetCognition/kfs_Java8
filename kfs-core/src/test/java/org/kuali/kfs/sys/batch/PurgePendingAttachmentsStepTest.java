package org.kuali.kfs.sys.batch;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.AttachmentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PurgePendingAttachmentsStepTest extends KfsUnitTestBase {

    @Mock
    private AttachmentService attachmentService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private PurgePendingAttachmentsStep step;

    @BeforeEach
    public void setUp() {
        step.setParameterService(parameterService);
        step.setDateTimeService(dateTimeService);
        step.setAttachmentService(attachmentService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(parameterService.getParameterValueAsString(any(Class.class), any(String.class))).thenReturn("3600");
        when(dateTimeService.getCurrentCalendar()).thenReturn(Calendar.getInstance());
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
    }

}
