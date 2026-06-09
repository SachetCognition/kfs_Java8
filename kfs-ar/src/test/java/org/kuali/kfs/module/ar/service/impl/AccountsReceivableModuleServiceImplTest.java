package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.Customer;
import org.kuali.kfs.module.ar.businessobject.CustomerAddress;
import org.kuali.kfs.module.ar.businessobject.CustomerType;
import org.kuali.kfs.module.ar.document.service.AccountsReceivableDocumentHeaderService;
import org.kuali.kfs.module.ar.document.service.AccountsReceivablePendingEntryService;
import org.kuali.kfs.module.ar.document.service.CustomerAddressService;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDetailService;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDocumentService;
import org.kuali.kfs.module.ar.document.service.CustomerService;
import org.kuali.kfs.module.ar.service.CustomerDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiModuleService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountsReceivableModuleServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private AccountsReceivableModuleServiceImpl service;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerAddressService customerAddressService;

    @Mock
    private CustomerInvoiceDocumentService customerInvoiceDocumentService;

    @Mock
    private CustomerInvoiceDetailService customerInvoiceDetailService;

    @Mock
    private AccountsReceivableDocumentHeaderService accountsReceivableDocumentHeaderService;

    @Mock
    private AccountsReceivablePendingEntryService accountsReceivablePendingEntryService;

    @Mock
    private CustomerDocumentService customerDocumentService;

    @Mock
    private DocumentService documentService;

    @Mock
    private KualiModuleService kualiModuleService;

    @Test
    void testCreateCustomer() {
        Customer customer = (Customer) service.createCustomer();
        assertThat(customer).isNotNull();
    }

    @Test
    void testCreateCustomerAddress() {
        CustomerAddress address = (CustomerAddress) service.createCustomerAddress();
        assertThat(address).isNotNull();
    }

    @Test
    void testCreateCustomerReturnsNewInstance() {
        Customer customer1 = (Customer) service.createCustomer();
        Customer customer2 = (Customer) service.createCustomer();
        assertThat(customer1).isNotSameAs(customer2);
    }

    @Test
    void testCreateCustomerAddressReturnsNewInstance() {
        CustomerAddress address1 = (CustomerAddress) service.createCustomerAddress();
        CustomerAddress address2 = (CustomerAddress) service.createCustomerAddress();
        assertThat(address1).isNotSameAs(address2);
    }

    @Test
    void testFindCustomerAddressByPrimaryKey() {
        CustomerAddress expectedAddress = new CustomerAddress();
        expectedAddress.setCustomerNumber("CUST001");
        when(businessObjectService.findByPrimaryKey(any(), any(java.util.Map.class))).thenReturn(expectedAddress);

        CustomerAddress result = (CustomerAddress) service.findCustomerAddress("CUST001", "1");
        assertThat(result).isNotNull();
        assertThat(result.getCustomerNumber()).isEqualTo("CUST001");
    }
}
