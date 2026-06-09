package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.coa.service.SubAccountService;
import org.kuali.kfs.coa.service.SubObjectCodeService;
import org.kuali.kfs.pdp.dataaccess.PaymentFileLoadDao;
import org.kuali.kfs.pdp.service.CustomerProfileService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.BankService;
import org.kuali.kfs.sys.service.OriginationCodeService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentFileValidationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private CustomerProfileService customerProfileService;
    @Mock
    private PaymentFileLoadDao paymentFileLoadDao;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private ParameterService parameterService;
    @Mock
    private ConfigurationService kualiConfigurationService;
    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private AccountService accountService;
    @Mock
    private SubAccountService subAccountService;
    @Mock
    private ObjectCodeService objectCodeService;
    @Mock
    private SubObjectCodeService subObjectCodeService;
    @Mock
    private BankService bankService;
    @Mock
    private OriginationCodeService originationCodeService;
    @Mock
    private DataDictionaryService dataDictionaryService;

    @InjectMocks
    private PaymentFileValidationServiceImpl paymentFileValidationService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(paymentFileValidationService).isNotNull();
    }
}
