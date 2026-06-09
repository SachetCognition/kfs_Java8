package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.dataaccess.BatchMaintenanceDao;
import org.kuali.kfs.pdp.service.PaymentGroupService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class BatchMaintenanceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BatchMaintenanceDao batchMaintenanceDao;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private PaymentGroupService paymentGroupService;

    @InjectMocks
    private BatchMaintenanceServiceImpl batchMaintenanceService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(batchMaintenanceService).isNotNull();
    }
}
