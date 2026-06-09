package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.CustomerInvoiceDetail;
import org.kuali.kfs.module.ar.document.CustomerCreditMemoDocument;
import org.kuali.kfs.module.ar.document.CustomerInvoiceDocument;
import org.kuali.kfs.module.ar.document.service.AccountsReceivableTaxService;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDocumentService;
import org.kuali.kfs.module.ar.document.service.InvoicePaidAppliedService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerCreditMemoDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private DocumentService documentService;
    @Mock private InvoicePaidAppliedService<CustomerInvoiceDetail> paidAppliedService;
    @Mock private UniversityDateService universityDateService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private DateTimeService dateTimeService;
    @Mock private CustomerInvoiceDocumentService customerInvoiceDocumentService;
    @Mock private AccountsReceivableTaxService accountsReceivableTaxService;

    @InjectMocks
    private CustomerCreditMemoDocumentServiceImpl service;

    @Test
    void completeCustomerCreditMemo_shouldThrowWhenInvoiceIsClosed() throws WorkflowException {
        final CustomerCreditMemoDocument creditMemo = mock(CustomerCreditMemoDocument.class);
        when(creditMemo.getFinancialDocumentReferenceInvoiceNumber()).thenReturn("INV001");
        when(creditMemo.getDocumentNumber()).thenReturn("CM001");

        CustomerInvoiceDocument invoice = mock(CustomerInvoiceDocument.class);
        when(invoice.isOpenInvoiceIndicator()).thenReturn(false);
        when(documentService.getByDocumentHeaderId("INV001")).thenReturn(invoice);

        assertThrows(UnsupportedOperationException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                service.completeCustomerCreditMemo(creditMemo);
            }
        });
    }

    @Test
    void serviceInitialization_shouldCreateInstance() {
        assertThat(service).isNotNull();
    }
}
