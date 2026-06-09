package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.AccountsReceivableDocumentHeader;
import org.kuali.kfs.module.ar.businessobject.Customer;
import org.kuali.kfs.module.ar.businessobject.CustomerAddress;
import org.kuali.kfs.module.ar.businessobject.CustomerInvoiceDetail;
import org.kuali.kfs.module.ar.businessobject.OrganizationOptions;
import org.kuali.kfs.module.ar.document.CustomerInvoiceDocument;
import org.kuali.kfs.module.ar.document.service.CustomerAddressService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.impl.KfsParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountsReceivableTaxServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private CustomerAddressService customerAddressService;

    @InjectMocks
    private AccountsReceivableTaxServiceImpl service;

    @Test
    void isCustomerInvoiceDetailTaxable_shouldReturnFalseWhenSalesTaxDisabled() {
        when(parameterService.getParameterValueAsBoolean(any(Class.class), any(String.class)))
                .thenReturn(false);

        CustomerInvoiceDocument doc = mock(CustomerInvoiceDocument.class);
        CustomerInvoiceDetail detail = mock(CustomerInvoiceDetail.class);

        assertThat(service.isCustomerInvoiceDetailTaxable(doc, detail)).isFalse();
    }

    @Test
    void isCustomerInvoiceDetailTaxable_shouldReturnFalseWhenCustomerTaxExempt() {
        when(parameterService.getParameterValueAsBoolean(any(Class.class), any(String.class)))
                .thenReturn(true);

        CustomerInvoiceDocument doc = mock(CustomerInvoiceDocument.class);
        Customer customer = new Customer();
        customer.setCustomerTaxExemptIndicator(true);
        when(doc.getCustomer()).thenReturn(customer);

        CustomerInvoiceDetail detail = mock(CustomerInvoiceDetail.class);
        assertThat(service.isCustomerInvoiceDetailTaxable(doc, detail)).isFalse();
    }

    @Test
    void isCustomerInvoiceDetailTaxable_shouldReturnFalseWhenDetailNotTaxable() {
        when(parameterService.getParameterValueAsBoolean(any(Class.class), any(String.class)))
                .thenReturn(true);

        CustomerInvoiceDocument doc = mock(CustomerInvoiceDocument.class);
        when(doc.getCustomer()).thenReturn(null);

        CustomerInvoiceDetail detail = mock(CustomerInvoiceDetail.class);
        when(detail.isTaxableIndicator()).thenReturn(false);
        assertThat(service.isCustomerInvoiceDetailTaxable(doc, detail)).isFalse();
    }

    @Test
    void isCustomerInvoiceDetailTaxable_shouldReturnTrueWhenAllConditionsMet() {
        when(parameterService.getParameterValueAsBoolean(any(Class.class), any(String.class)))
                .thenReturn(true);

        CustomerInvoiceDocument doc = mock(CustomerInvoiceDocument.class);
        Customer customer = new Customer();
        customer.setCustomerTaxExemptIndicator(false);
        when(doc.getCustomer()).thenReturn(customer);
        when(doc.getShippingZipCode()).thenReturn(null);

        CustomerInvoiceDetail detail = mock(CustomerInvoiceDetail.class);
        when(detail.isTaxableIndicator()).thenReturn(true);
        assertThat(service.isCustomerInvoiceDetailTaxable(doc, detail)).isTrue();
    }

    @Test
    void getPostalCodeForTaxation_shouldReturnCustomerAddressPostalCode() {
        CustomerInvoiceDocument doc = mock(CustomerInvoiceDocument.class);
        AccountsReceivableDocumentHeader arHeader = new AccountsReceivableDocumentHeader();
        arHeader.setCustomerNumber("CUST001");
        when(doc.getAccountsReceivableDocumentHeader()).thenReturn(arHeader);
        when(doc.getCustomerShipToAddressIdentifier()).thenReturn(1);

        CustomerAddress addr = new CustomerAddress();
        addr.setCustomerZipCode("12345");
        when(customerAddressService.getByPrimaryKey("CUST001", 1)).thenReturn(addr);

        String result = service.getPostalCodeForTaxation(doc);
        assertThat(result).isEqualTo("12345");
    }

    @Test
    void getPostalCodeForTaxation_shouldFallBackToOrgOptionsWhenNoShipToAddress() {
        CustomerInvoiceDocument doc = mock(CustomerInvoiceDocument.class);
        AccountsReceivableDocumentHeader arHeader = new AccountsReceivableDocumentHeader();
        arHeader.setCustomerNumber("CUST001");
        when(doc.getAccountsReceivableDocumentHeader()).thenReturn(arHeader);
        when(doc.getCustomerShipToAddressIdentifier()).thenReturn(null);
        when(doc.getBillByChartOfAccountCode()).thenReturn("UA");
        when(doc.getBilledByOrganizationCode()).thenReturn("VPIT");

        OrganizationOptions orgOpts = new OrganizationOptions();
        orgOpts.setOrganizationPostalZipCode("54321");
        when(businessObjectService.findByPrimaryKey(eq(OrganizationOptions.class), any(Map.class)))
                .thenReturn(orgOpts);

        String result = service.getPostalCodeForTaxation(doc);
        assertThat(result).isEqualTo("54321");
    }
}
