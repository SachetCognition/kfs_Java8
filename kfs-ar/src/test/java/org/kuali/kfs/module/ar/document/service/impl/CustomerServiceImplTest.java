package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.Customer;
import org.kuali.kfs.module.ar.document.CustomerInvoiceDocument;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.NoteService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest extends KfsUnitTestBase {

    @Mock
    private SequenceAccessorService sequenceAccessorService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private CustomerInvoiceDocumentService customerInvoiceDocumentService;

    @Mock
    private NoteService noteService;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void getByPrimaryKey_shouldReturnCustomer() {
        Customer customer = new Customer();
        when(businessObjectService.findBySinglePrimaryKey(Customer.class, "CUST001"))
                .thenReturn(customer);

        Customer result = customerService.getByPrimaryKey("CUST001");
        assertThat(result).isSameAs(customer);
    }

    @Test
    void getByPrimaryKey_shouldReturnNullWhenNotFound() {
        when(businessObjectService.findBySinglePrimaryKey(Customer.class, "NONE"))
                .thenReturn(null);

        Customer result = customerService.getByPrimaryKey("NONE");
        assertThat(result).isNull();
    }

    @Test
    void getByTaxNumber_shouldReturnCustomer() {
        Customer customer = new Customer();
        when(businessObjectService.findMatching(eq(Customer.class), any(Map.class)))
                .thenReturn(Arrays.asList(customer));

        Customer result = customerService.getByTaxNumber("123456789");
        assertThat(result).isSameAs(customer);
    }

    @Test
    void getByTaxNumber_shouldReturnNullWhenNotFound() {
        when(businessObjectService.findMatching(eq(Customer.class), any(Map.class)))
                .thenReturn(Collections.emptyList());

        Customer result = customerService.getByTaxNumber("000000000");
        assertThat(result).isNull();
    }

    @Test
    void getNextCustomerNumber_shouldReturnPrefixPlusSequence() {
        Customer customer = new Customer();
        customer.setCustomerName("ABCDEF");
        when(sequenceAccessorService.getNextAvailableSequenceNumber(eq("CUST_NBR_SEQ"), eq(Customer.class)))
                .thenReturn(100L);

        String result = customerService.getNextCustomerNumber(customer);
        assertThat(result).isEqualTo("ABC100");
    }

    @Test
    void getNextCustomerNumber_shouldThrowWhenNameTooShort() {
        final Customer customer = new Customer();
        customer.setCustomerName("AB");
        when(sequenceAccessorService.getNextAvailableSequenceNumber(eq("CUST_NBR_SEQ"), eq(Customer.class)))
                .thenReturn(100L);

        assertThrows(StringIndexOutOfBoundsException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                customerService.getNextCustomerNumber(customer);
            }
        });
    }

    @Test
    void getCustomerByName_shouldReturnMatchingCustomer() {
        Customer customer = new Customer();
        when(businessObjectService.findMatching(eq(Customer.class), any(Map.class)))
                .thenReturn(Arrays.asList(customer));

        Customer result = customerService.getCustomerByName("Test Customer");
        assertThat(result).isSameAs(customer);
    }

    @Test
    void getCustomerByName_shouldReturnNullWhenNoMatch() {
        when(businessObjectService.findMatching(eq(Customer.class), any(Map.class)))
                .thenReturn(Collections.emptyList());

        Customer result = customerService.getCustomerByName("Nonexistent");
        assertThat(result).isNull();
    }

    @Test
    void getInvoicesForCustomer_byObject_shouldDelegateToStringVersion() {
        Customer customer = new Customer();
        customer.setCustomerNumber("CUST001");
        CustomerInvoiceDocument mockInvoice = mock(CustomerInvoiceDocument.class);
        Collection<CustomerInvoiceDocument> invoices = Arrays.asList(mockInvoice);
        when(customerInvoiceDocumentService.getCustomerInvoiceDocumentsByCustomerNumber("CUST001"))
                .thenReturn(invoices);

        Collection<CustomerInvoiceDocument> result = customerService.getInvoicesForCustomer(customer);
        assertThat(result).hasSize(1);
    }

    @Test
    void getInvoicesForCustomer_byNumber_shouldDelegate() {
        CustomerInvoiceDocument mockInvoice = mock(CustomerInvoiceDocument.class);
        Collection<CustomerInvoiceDocument> invoices = Arrays.asList(mockInvoice);
        when(customerInvoiceDocumentService.getCustomerInvoiceDocumentsByCustomerNumber("CUST001"))
                .thenReturn(invoices);

        Collection<CustomerInvoiceDocument> result = customerService.getInvoicesForCustomer("CUST001");
        assertThat(result).hasSize(1);
    }
}
