package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.AccountingPeriodService;
import org.kuali.kfs.pdp.dataaccess.PendingTransactionDao;
import org.kuali.kfs.pdp.service.PdpUtilService;
import org.kuali.kfs.pdp.service.ResearchParticipantPaymentValidationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.BankService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class PendingTransactionServiceImplTest extends KfsUnitTestBase {

    @Mock
    private PendingTransactionDao glPendingTransactionDao;
    @Mock
    private AccountingPeriodService accountingPeriodService;
    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private ConfigurationService kualiConfigurationService;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private BankService bankService;
    @Mock
    private DataDictionaryService dataDictionaryService;
    @Mock
    private ParameterService parameterService;
    @Mock
    private ResearchParticipantPaymentValidationService researchParticipantPaymentValidationService;
    @Mock
    private PdpUtilService pdpUtilService;

    @InjectMocks
    private PendingTransactionServiceImpl pendingTransactionService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(pendingTransactionService).isNotNull();
    }
}
