package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.document.service.AccountsReceivableDocumentHeaderService;
import org.kuali.kfs.module.ar.document.service.AccountsReceivablePendingEntryService;
import org.kuali.kfs.module.ar.document.service.CustomerAddressService;
import org.kuali.kfs.module.ar.document.service.CustomerCreditMemoDetailService;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDetailService;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDocumentService;
import org.kuali.kfs.module.ar.document.service.CustomerService;
import org.kuali.kfs.module.ar.document.service.SystemInformationService;
import org.kuali.kfs.module.ar.service.CustomerDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.FinancialSystemUserService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class AccountsReceivableModuleServiceImplTest extends KfsUnitTestBase {

    @Mock private CustomerService customerService;
    @Mock private CustomerAddressService customerAddressService;
    @Mock private CustomerInvoiceDocumentService customerInvoiceDocumentService;
    @Mock private CustomerInvoiceDetailService customerInvoiceDetailService;
    @Mock private CustomerCreditMemoDetailService customerCreditMemoDetailService;
    @Mock private AccountsReceivableDocumentHeaderService accountsReceivableDocumentHeaderService;
    @Mock private AccountsReceivablePendingEntryService accountsReceivablePendingEntryService;
    @Mock private SystemInformationService systemInformationService;
    @Mock private CustomerDocumentService customerDocumentService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private DocumentService documentService;
    @Mock private KualiModuleService kualiModuleService;
    @Mock private ParameterService parameterService;
    @Mock private FinancialSystemUserService financialSystemUserService;

    @InjectMocks
    private AccountsReceivableModuleServiceImpl service;

    @Test
    void serviceInitialization_shouldCreateInstance() {
        assertThat(service).isNotNull();
    }
}
