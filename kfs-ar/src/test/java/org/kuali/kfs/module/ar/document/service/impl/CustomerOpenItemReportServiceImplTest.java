package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.document.dataaccess.AccountsReceivableDocumentHeaderDao;
import org.kuali.kfs.module.ar.document.dataaccess.CustomerInvoiceDetailDao;
import org.kuali.kfs.module.ar.document.dataaccess.NonAppliedHoldingDao;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CustomerOpenItemReportServiceImplTest extends KfsUnitTestBase {

    @Mock private AccountsReceivableDocumentHeaderDao accountsReceivableDocumentHeaderDao;
    @Mock private CustomerInvoiceDocumentService customerInvoiceDocumentService;
    @Mock private DocumentService documentService;
    @Mock private DateTimeService dateTimeService;
    @Mock private CustomerInvoiceDetailDao customerInvoiceDetailDao;
    @Mock private NonAppliedHoldingDao nonAppliedHoldingDao;
    @Mock private BusinessObjectService businessObjectService;

    @InjectMocks
    private CustomerOpenItemReportServiceImpl service;

    @Test
    void getPopulatedReportDetails_shouldReturnEmptyListForNonexistentCustomer() {
        when(accountsReceivableDocumentHeaderDao.getARDocumentNumbersIncludingHiddenApplicationByCustomerNumber("NONE"))
                .thenReturn(new ArrayList<String>());

        List result = service.getPopulatedReportDetails("NONE");
        assertThat(result).isEmpty();
    }
}
