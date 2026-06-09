package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.MaintenanceDocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class DisbursementVoucherTaxServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private MaintenanceDocumentService maintenanceDocumentService;

    @InjectMocks
    private DisbursementVoucherTaxServiceImpl disbursementVoucherTaxService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(disbursementVoucherTaxService).isNotNull();
    }
}
