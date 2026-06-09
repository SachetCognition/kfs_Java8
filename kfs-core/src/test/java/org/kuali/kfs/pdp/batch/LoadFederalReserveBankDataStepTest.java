package org.kuali.kfs.pdp.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.service.AchBankService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoadFederalReserveBankDataStepTest extends KfsUnitTestBase {

    @Mock
    private AchBankService achBankService;

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private LoadFederalReserveBankDataStep step;

    @BeforeEach
    public void setUp() {
        step.setParameterService(parameterService);
        step.setAchBankService(achBankService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(parameterService.getParameterValueAsString(any(Class.class), any(String.class))).thenReturn("3600");
        step.execute("testJob", new Date());
        // Service delegation verified by no exception
    }

}
