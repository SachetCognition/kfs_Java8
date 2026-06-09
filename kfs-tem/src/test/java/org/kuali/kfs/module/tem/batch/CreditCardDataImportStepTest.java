package org.kuali.kfs.module.tem.batch;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.batch.service.CreditCardDataImportService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("CreditCardDataImportStep")
class CreditCardDataImportStepTest extends KfsUnitTestBase {

    @Mock
    private CreditCardDataImportService creditCardDataImportService;

    @InjectMocks
    private CreditCardDataImportStep creditCardDataImportStep;

    @Test
    @DisplayName("should execute and delegate to creditCardDataImportService")
    void testExecute() throws InterruptedException {
        when(creditCardDataImportService.importCreditCardData()).thenReturn(true);

        boolean result = creditCardDataImportStep.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(creditCardDataImportService).importCreditCardData();
    }

    @Test
    @DisplayName("should return false when import fails")
    void testExecuteReturnsFalse() throws InterruptedException {
        when(creditCardDataImportService.importCreditCardData()).thenReturn(false);

        boolean result = creditCardDataImportStep.execute("testJob", new Date());

        assertThat(result).isFalse();
    }
}
