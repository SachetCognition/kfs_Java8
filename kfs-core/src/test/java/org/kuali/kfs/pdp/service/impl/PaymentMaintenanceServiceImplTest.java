package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.dataaccess.PaymentDetailDao;
import org.kuali.kfs.pdp.dataaccess.PaymentGroupDao;
import org.kuali.kfs.pdp.service.PaymentGroupService;
import org.kuali.kfs.pdp.service.PdpAuthorizationService;
import org.kuali.kfs.pdp.service.PdpEmailService;
import org.kuali.kfs.pdp.service.PendingTransactionService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.BankService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.MailService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentMaintenanceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private PaymentGroupDao paymentGroupDao;
    @Mock
    private PaymentDetailDao paymentDetailDao;
    @Mock
    private PendingTransactionService glPendingTransactionService;
    @Mock
    private MailService mailService;
    @Mock
    private ParameterService parameterService;
    @Mock
    private BankService bankService;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private PaymentGroupService paymentGroupService;
    @Mock
    private PdpEmailService emailService;
    @Mock
    private PdpAuthorizationService pdpAuthorizationService;

    @InjectMocks
    private PaymentMaintenanceServiceImpl paymentMaintenanceService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(paymentMaintenanceService).isNotNull();
    }
}
