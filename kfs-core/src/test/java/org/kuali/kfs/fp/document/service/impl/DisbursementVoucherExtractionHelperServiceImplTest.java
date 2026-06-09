package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.dataaccess.DisbursementVoucherDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.document.service.PaymentSourceHelperService;
import org.kuali.kfs.sys.service.GeneralLedgerPendingEntryService;
import org.kuali.kfs.vnd.document.service.VendorService;
import org.kuali.rice.core.api.parameter.ParameterEvaluatorService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class DisbursementVoucherExtractionHelperServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private DocumentService documentService;
    @Mock
    private GeneralLedgerPendingEntryService generalLedgerPendingEntryService;
    @Mock
    private ParameterService parameterService;
    @Mock
    private ParameterEvaluatorService parameterEvaluatorService;
    @Mock
    private VendorService vendorService;
    @Mock
    private DisbursementVoucherDao disbursementVoucherDao;
    @Mock
    private PaymentSourceHelperService paymentSourceHelperService;

    @InjectMocks
    private DisbursementVoucherExtractionHelperServiceImpl service;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(service).isNotNull();
    }
}
