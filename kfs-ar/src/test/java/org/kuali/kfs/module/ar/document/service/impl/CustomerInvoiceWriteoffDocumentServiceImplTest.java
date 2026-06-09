package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.module.ar.batch.service.CustomerInvoiceWriteoffBatchService;
import org.kuali.kfs.module.ar.businessobject.CustomerInvoiceDetail;
import org.kuali.kfs.module.ar.document.CustomerInvoiceWriteoffDocument;
import org.kuali.kfs.module.ar.document.service.AccountsReceivableDocumentHeaderService;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDocumentService;
import org.kuali.kfs.module.ar.document.service.CustomerService;
import org.kuali.kfs.module.ar.document.service.InvoicePaidAppliedService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.FinancialSystemUserService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerInvoiceWriteoffDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private ParameterService parameterService;
    @Mock private UniversityDateService universityDateService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private AccountsReceivableDocumentHeaderService accountsReceivableDocumentHeaderService;
    @Mock private CustomerInvoiceDocumentService customerInvoiceDocumentService;
    @Mock private CustomerService customerService;
    @Mock private DocumentService documentService;
    @Mock private CustomerInvoiceWriteoffBatchService invoiceWriteoffBatchService;
    @Mock private DateTimeService dateTimeService;
    @Mock private InvoicePaidAppliedService<CustomerInvoiceDetail> paidAppliedService;
    @Mock private FinancialSystemUserService financialSystemUserService;
    @Mock private ObjectCodeService objectCodeService;

    @InjectMocks
    private CustomerInvoiceWriteoffDocumentServiceImpl service;

    @Test
    void getCustomerCreditMemoDocumentByInvoiceDocument_shouldReturnEmptyWhenNone() {
        when(businessObjectService.findMatching(eq(CustomerInvoiceWriteoffDocument.class), any(Map.class)))
                .thenReturn(Collections.EMPTY_LIST);

        Collection<CustomerInvoiceWriteoffDocument> result = service.getCustomerCreditMemoDocumentByInvoiceDocument("INV001");
        assertThat(result).isEmpty();
    }

    @Test
    void serviceInitialization_shouldCreateInstance() {
        assertThat(service).isNotNull();
    }
}
