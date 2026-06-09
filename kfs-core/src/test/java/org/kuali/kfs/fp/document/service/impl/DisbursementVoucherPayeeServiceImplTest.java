package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.vnd.document.service.VendorService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class DisbursementVoucherPayeeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private DataDictionaryService dataDictionaryService;
    @Mock
    private DocumentService documentService;
    @Mock
    private ParameterService parameterService;
    @Mock
    private VendorService vendorService;

    @InjectMocks
    private DisbursementVoucherPayeeServiceImpl disbursementVoucherPayeeService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(disbursementVoucherPayeeService).isNotNull();
    }
}
