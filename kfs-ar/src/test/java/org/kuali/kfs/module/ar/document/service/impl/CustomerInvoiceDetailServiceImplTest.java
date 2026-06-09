package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.CustomerInvoiceDetail;
import org.kuali.kfs.module.ar.businessobject.OrganizationAccountingDefault;
import org.kuali.kfs.module.ar.document.service.AccountsReceivablePendingEntryService;
import org.kuali.kfs.module.ar.document.service.AccountsReceivableTaxService;
import org.kuali.kfs.module.ar.document.service.InvoicePaidAppliedService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.FinancialSystemUserService;
import org.kuali.kfs.sys.service.TaxService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class CustomerInvoiceDetailServiceImplTest extends KfsUnitTestBase {

    @Mock private AccountsReceivablePendingEntryService accountsReceivablePendingEntryService;
    @Mock private DateTimeService dateTimeService;
    @Mock private UniversityDateService universityDateService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private FinancialSystemUserService financialSystemUserService;
    @Mock private ParameterService parameterService;
    @Mock private InvoicePaidAppliedService invoicePaidAppliedService;
    @Mock private AccountsReceivableTaxService accountsReceivableTaxService;
    @Mock private TaxService taxService;

    @InjectMocks
    private CustomerInvoiceDetailServiceImpl service;

    @Test
    void serviceInitialization_shouldCreateInstance() {
        assertThat(service).isNotNull();
    }

    @Test
    void getCustomerInvoiceDetailsForInvoice_shouldReturnResults() {
        CustomerInvoiceDetail detail = new CustomerInvoiceDetail();
        when(businessObjectService.findMatching(eq(CustomerInvoiceDetail.class), any(Map.class)))
                .thenReturn(Arrays.asList(detail));

        Collection<CustomerInvoiceDetail> result = service.getCustomerInvoiceDetailsForInvoice("INV001");
        assertThat(result).hasSize(1);
    }

    @Test
    void getCustomerInvoiceDetailsForInvoice_shouldReturnEmptyWhenNone() {
        when(businessObjectService.findMatching(eq(CustomerInvoiceDetail.class), any(Map.class)))
                .thenReturn(Collections.emptyList());

        Collection<CustomerInvoiceDetail> result = service.getCustomerInvoiceDetailsForInvoice("INV001");
        assertThat(result).isEmpty();
    }

    @Test
    void getCustomerInvoiceDetail_shouldReturnDetailByPrimaryKey() {
        CustomerInvoiceDetail detail = new CustomerInvoiceDetail();
        when(businessObjectService.findByPrimaryKey(eq(CustomerInvoiceDetail.class), any(Map.class)))
                .thenReturn(detail);

        CustomerInvoiceDetail result = service.getCustomerInvoiceDetail("INV001", 1);
        assertThat(result).isSameAs(detail);
    }
}
