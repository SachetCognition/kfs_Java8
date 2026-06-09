package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.document.CustomerInvoiceDocument;
import org.kuali.kfs.module.ar.document.dataaccess.CustomerInvoiceDocumentDao;
import org.kuali.kfs.module.ar.document.service.AccountsReceivableDocumentHeaderService;
import org.kuali.kfs.module.ar.document.service.CustomerAddressService;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDetailService;
import org.kuali.kfs.module.ar.document.service.InvoicePaidAppliedService;
import org.kuali.kfs.module.ar.document.service.NonInvoicedDistributionService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.FinancialSystemUserService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.dao.DocumentDao;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.NoteService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerInvoiceDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private AccountsReceivableDocumentHeaderService accountsReceivableDocumentHeaderService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private CustomerAddressService customerAddressService;
    @Mock private CustomerInvoiceDetailService customerInvoiceDetailService;
    @Mock private CustomerInvoiceDocumentDao customerInvoiceDocumentDao;
    @Mock private ConfigurationService configurationService;
    @Mock private DateTimeService dateTimeService;
    @Mock private DocumentService documentService;
    @Mock private DocumentDao documentDao;
    @Mock private FinancialSystemUserService financialSystemUserService;
    @Mock private InvoicePaidAppliedService invoicePaidAppliedService;
    @Mock private NonInvoicedDistributionService nonInvoicedDistributionService;
    @Mock private PersonService personService;
    @Mock private UniversityDateService universityDateService;
    @Mock private NoteService noteService;
    @Mock private IdentityService identityService;

    @InjectMocks
    private CustomerInvoiceDocumentServiceImpl service;

    @Test
    void getAllOpenCustomerInvoiceDocumentsWithoutWorkflow_shouldReturnEmptyWhenNone() {
        when(customerInvoiceDocumentDao.getAllOpen()).thenReturn(new ArrayList<CustomerInvoiceDocument>());

        Collection<CustomerInvoiceDocument> result = service.getAllOpenCustomerInvoiceDocumentsWithoutWorkflow();
        assertThat(result).isEmpty();
    }

    @Test
    void getOpenInvoiceDocumentsByCustomerNumber_shouldReturnEmptyWhenNoResults() {
        when(customerInvoiceDocumentDao.getOpenByCustomerNumber("CUST001")).thenReturn(new ArrayList<CustomerInvoiceDocument>());

        Collection<CustomerInvoiceDocument> result = service.getOpenInvoiceDocumentsByCustomerNumber("CUST001");
        assertThat(result).isEmpty();
    }

    @Test
    void getInvoiceByOrganizationInvoiceNumber_shouldDelegateToDao() {
        CustomerInvoiceDocument invoice = mock(CustomerInvoiceDocument.class);
        when(customerInvoiceDocumentDao.getInvoiceByOrganizationInvoiceNumber("ORG-001"))
                .thenReturn(invoice);

        CustomerInvoiceDocument result = service.getInvoiceByOrganizationInvoiceNumber("ORG-001");
        assertThat(result).isSameAs(invoice);
    }

    @Test
    void getInvoiceByInvoiceDocumentNumber_shouldDelegateToDao() {
        CustomerInvoiceDocument invoice = mock(CustomerInvoiceDocument.class);
        when(customerInvoiceDocumentDao.getInvoiceByInvoiceDocumentNumber("DOC001"))
                .thenReturn(invoice);

        CustomerInvoiceDocument result = service.getInvoiceByInvoiceDocumentNumber("DOC001");
        assertThat(result).isSameAs(invoice);
    }
}
