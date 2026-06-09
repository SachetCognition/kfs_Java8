package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.dataaccess.FormatPaymentDao;
import org.kuali.kfs.pdp.dataaccess.PaymentDetailDao;
import org.kuali.kfs.pdp.dataaccess.PaymentGroupDao;
import org.kuali.kfs.pdp.dataaccess.ProcessDao;
import org.kuali.kfs.pdp.service.AchService;
import org.kuali.kfs.pdp.service.PaymentGroupService;
import org.kuali.kfs.pdp.service.PendingTransactionService;
import org.kuali.kfs.sys.batch.service.SchedulerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.MailService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class FormatServiceImplTest extends KfsUnitTestBase {

    @Mock
    private PaymentDetailDao paymentDetailDao;
    @Mock
    private PaymentGroupDao paymentGroupDao;
    @Mock
    private ProcessDao processDao;
    @Mock
    private FormatPaymentDao formatPaymentDao;
    @Mock
    private SchedulerService schedulerService;
    @Mock
    private ParameterService parameterService;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private PaymentGroupService paymentGroupService;
    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private PendingTransactionService glPendingTransactionService;
    @Mock
    private AchService achService;
    @Mock
    private MailService mailService;

    @InjectMocks
    private FormatServiceImpl formatService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(formatService).isNotNull();
    }
}
