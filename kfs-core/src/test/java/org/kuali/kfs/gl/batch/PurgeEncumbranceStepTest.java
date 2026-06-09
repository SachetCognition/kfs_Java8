package org.kuali.kfs.gl.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.ChartService;
import org.kuali.kfs.gl.service.EncumbranceService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PurgeEncumbranceStepTest extends KfsUnitTestBase {

    @Mock
    private EncumbranceService encumbranceService;

    @Mock
    private ChartService chartService;

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private PurgeEncumbranceStep step;

    @BeforeEach
    public void setUp() {
        step.setParameterService(parameterService);
        step.setEncumbranceService(encumbranceService);
        step.setChartService(chartService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(parameterService.getParameterValueAsString(any(Class.class), any(String.class))).thenReturn("3600");
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(chartService).getAllChartCodes();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        when(parameterService.getParameterValueAsString(any(Class.class), any(String.class))).thenReturn("3600");
        step.execute("testJob", new Date());
        verify(chartService, atLeastOnce()).getAllChartCodes();
    }

}
