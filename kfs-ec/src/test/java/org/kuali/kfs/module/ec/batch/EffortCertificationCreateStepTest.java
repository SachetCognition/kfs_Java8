package org.kuali.kfs.module.ec.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ec.batch.service.EffortCertificationCreateService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class EffortCertificationCreateStepTest extends KfsUnitTestBase {

    @Mock
    private EffortCertificationCreateService effortCertificationCreateService;

    @InjectMocks
    private EffortCertificationCreateStep createStep;

    @Test
    @DisplayName("execute should invoke create service and return true")
    void executeShouldInvokeCreateService() throws Exception {
        doNothing().when(effortCertificationCreateService).create();

        boolean result = createStep.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(effortCertificationCreateService).create();
    }

    @Test
    @DisplayName("execute should pass jobName parameter")
    void executeShouldAcceptJobName() throws Exception {
        doNothing().when(effortCertificationCreateService).create();

        boolean result = createStep.execute("effortCertificationCreateJob", new Date());

        assertThat(result).isTrue();
    }
}
