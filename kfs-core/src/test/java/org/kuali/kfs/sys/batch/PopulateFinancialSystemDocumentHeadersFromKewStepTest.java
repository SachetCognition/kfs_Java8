package org.kuali.kfs.sys.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.batch.service.FinancialSystemDocumentHeaderPopulationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class PopulateFinancialSystemDocumentHeadersFromKewStepTest extends KfsUnitTestBase {

    @Mock
    private FinancialSystemDocumentHeaderPopulationService populationService;

    @Mock
    private ParameterService parameterService;

    private PopulateFinancialSystemDocumentHeadersFromKewStep step;

    @BeforeEach
    public void setUp() {
        step = new PopulateFinancialSystemDocumentHeadersFromKewStep();
        step.setPopulationService(populationService);
        step.setParameterService(parameterService);
    }

    @Test
    public void testStepInstantiation() {
        assertNotNull(step);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(parameterService.getParameterValueAsString(any(Class.class), any(String.class), any(String.class))).thenReturn("1000");
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
    }
}
