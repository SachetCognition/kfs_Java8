package org.kuali.kfs.module.cg.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.businessobject.CfdaUpdateResults;
import org.kuali.kfs.module.cg.service.CfdaService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.mail.MailMessage;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.MailService;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

class CfdaBatchStepTest extends KfsUnitTestBase {

    @Mock
    private CfdaService cfdaService;

    @Mock
    private MailService mailService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private ConfigurationService configurationService;

    private CfdaBatchStep cfdaBatchStep;

    @BeforeEach
    void setUp() {
        cfdaBatchStep = new CfdaBatchStep();
        cfdaBatchStep.setCfdaService(cfdaService);
        cfdaBatchStep.setMailService(mailService);
        cfdaBatchStep.setParameterService(parameterService);
        cfdaBatchStep.setConfigurationService(configurationService);
    }

    @Test
    void testExecuteSuccessful_WithNotificationAddresses() throws Exception {
        CfdaUpdateResults results = new CfdaUpdateResults();
        results.setNumberOfRecordsRetrievedFromWebSite(100);
        results.setNumberOfRecordsInKfsDatabase(80);
        results.setNumberOfRecordsNewlyAddedFromWebSite(5);
        results.setNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite(2);
        results.setNumberOfRecordsReActivated(1);
        results.setNumberOfRecordsNotUpdatedBecauseManual(10);
        results.setNumberOfRecordsUpdatedBecauseAutomatic(15);
        results.setNumberOfRecrodsNotUpdatedForHistoricalPurposes(3);

        when(cfdaService.update()).thenReturn(results);
        when(parameterService.getParameterValuesAsString(eq(CfdaBatchStep.class),
                eq(KFSConstants.RESULT_SUMMARY_TO_EMAIL_ADDRESSES)))
                .thenReturn(Arrays.asList("admin@test.edu"));
        when(configurationService.getPropertyValueAsString(any(String.class)))
                .thenReturn("CFDA Update");

        boolean result = cfdaBatchStep.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(mailService).sendMessage(any(MailMessage.class));
    }

    @Test
    void testExecuteSuccessful_WithEmptyNotificationAddresses() throws Exception {
        CfdaUpdateResults results = new CfdaUpdateResults();

        when(cfdaService.update()).thenReturn(results);
        when(parameterService.getParameterValuesAsString(eq(CfdaBatchStep.class),
                eq(KFSConstants.RESULT_SUMMARY_TO_EMAIL_ADDRESSES)))
                .thenReturn(Collections.emptyList());

        boolean result = cfdaBatchStep.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(mailService, never()).sendMessage(any(MailMessage.class));
    }

    @Test
    void testExecuteReturnsFalse_WhenIOException() throws Exception {
        when(cfdaService.update()).thenThrow(new IOException("Connection failed"));

        boolean result = cfdaBatchStep.execute("testJob", new Date());

        assertThat(result).isFalse();
    }

    @Test
    void testGetConfigurationService() {
        assertThat(cfdaBatchStep.getConfigurationService()).isSameAs(configurationService);
    }
}
