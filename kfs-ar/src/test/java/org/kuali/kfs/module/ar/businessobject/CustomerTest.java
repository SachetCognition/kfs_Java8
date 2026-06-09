package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest extends KfsUnitTestBase {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @Test
    void testDefaultConstructorInitializesAddressList() {
        assertThat(customer.getCustomerAddresses()).isNotNull().isEmpty();
    }

    @Test
    void testCustomerNumber() {
        customer.setCustomerNumber("CUST001");
        assertThat(customer.getCustomerNumber()).isEqualTo("CUST001");
    }

    @Test
    void testCustomerName() {
        customer.setCustomerName("Test University");
        assertThat(customer.getCustomerName()).isEqualTo("Test University");
    }

    @Test
    void testCustomerParentCompanyNumber() {
        customer.setCustomerParentCompanyNumber("PARENT01");
        assertThat(customer.getCustomerParentCompanyNumber()).isEqualTo("PARENT01");
    }

    @Test
    void testCustomerTypeCode() {
        customer.setCustomerTypeCode("GV");
        assertThat(customer.getCustomerTypeCode()).isEqualTo("GV");
    }

    @Test
    void testCustomerTypeDescription() {
        customer.setCustomerTypeDescription("Government");
        assertThat(customer.getCustomerTypeDescription()).isEqualTo("Government");
    }

    @Test
    void testCustomerAddressChangeDate() {
        Date date = Date.valueOf("2024-01-15");
        customer.setCustomerAddressChangeDate(date);
        assertThat(customer.getCustomerAddressChangeDate()).isEqualTo(date);
    }

    @Test
    void testCustomerRecordAddDate() {
        Date date = Date.valueOf("2023-06-01");
        customer.setCustomerRecordAddDate(date);
        assertThat(customer.getCustomerRecordAddDate()).isEqualTo(date);
    }

    @Test
    void testCustomerLastActivityDate() {
        Date date = Date.valueOf("2024-03-20");
        customer.setCustomerLastActivityDate(date);
        assertThat(customer.getCustomerLastActivityDate()).isEqualTo(date);
    }

    @Test
    void testCustomerTaxTypeCode() {
        customer.setCustomerTaxTypeCode("FEIN");
        assertThat(customer.getCustomerTaxTypeCode()).isEqualTo("FEIN");
    }

    @Test
    void testCustomerTaxNbr() {
        customer.setCustomerTaxNbr("123456789");
        assertThat(customer.getCustomerTaxNbr()).isEqualTo("123456789");
    }

    @Test
    void testActiveIndicator() {
        customer.setActive(true);
        assertThat(customer.isActive()).isTrue();

        customer.setActive(false);
        assertThat(customer.isActive()).isFalse();
    }

    @Test
    void testCustomerPhoneNumber() {
        customer.setCustomerPhoneNumber("555-123-4567");
        assertThat(customer.getCustomerPhoneNumber()).isEqualTo("555-123-4567");
    }

    @Test
    void testCustomer800PhoneNumber() {
        customer.setCustomer800PhoneNumber("800-555-0100");
        assertThat(customer.getCustomer800PhoneNumber()).isEqualTo("800-555-0100");
    }

    @Test
    void testCustomerContactName() {
        customer.setCustomerContactName("John Doe");
        assertThat(customer.getCustomerContactName()).isEqualTo("John Doe");
    }

    @Test
    void testCustomerContactPhoneNumber() {
        customer.setCustomerContactPhoneNumber("555-987-6543");
        assertThat(customer.getCustomerContactPhoneNumber()).isEqualTo("555-987-6543");
    }

    @Test
    void testCustomerFaxNumber() {
        customer.setCustomerFaxNumber("555-111-2222");
        assertThat(customer.getCustomerFaxNumber()).isEqualTo("555-111-2222");
    }

    @Test
    void testCustomerBirthDate() {
        Date date = Date.valueOf("1990-05-15");
        customer.setCustomerBirthDate(date);
        assertThat(customer.getCustomerBirthDate()).isEqualTo(date);
    }

    @Test
    void testCustomerTaxExemptIndicator() {
        customer.setCustomerTaxExemptIndicator(true);
        assertThat(customer.isCustomerTaxExemptIndicator()).isTrue();

        customer.setCustomerTaxExemptIndicator(false);
        assertThat(customer.isCustomerTaxExemptIndicator()).isFalse();
    }

    @Test
    void testCustomerCreditLimitAmount() {
        KualiDecimal limit = new KualiDecimal(50000);
        customer.setCustomerCreditLimitAmount(limit);
        assertThat(customer.getCustomerCreditLimitAmount()).isEqualTo(limit);
    }

    @Test
    void testCustomerCreditApprovedByName() {
        customer.setCustomerCreditApprovedByName("Jane Smith");
        assertThat(customer.getCustomerCreditApprovedByName()).isEqualTo("Jane Smith");
    }

    @Test
    void testCustomerEmailAddress() {
        customer.setCustomerEmailAddress("test@university.edu");
        assertThat(customer.getCustomerEmailAddress()).isEqualTo("test@university.edu");
    }

    @Test
    void testCustomerAddresses() {
        List<CustomerAddress> addresses = new ArrayList<>();
        CustomerAddress address = new CustomerAddress();
        address.setCustomerNumber("CUST001");
        addresses.add(address);

        customer.setCustomerAddresses(addresses);
        assertThat(customer.getCustomerAddresses()).hasSize(1);
        assertThat(customer.getCustomerAddresses().get(0).getCustomerNumber()).isEqualTo("CUST001");
    }

    @Test
    void testBankruptcyType() {
        customer.setBankruptcyType("CH7");
        assertThat(customer.getBankruptcyType()).isEqualTo("CH7");
    }

    @Test
    void testBankruptcyDate() {
        Date date = Date.valueOf("2024-02-01");
        customer.setBankruptcyDate(date);
        assertThat(customer.getBankruptcyDate()).isEqualTo(date);
    }

    @Test
    void testBankruptcyReviewDate() {
        Date date = Date.valueOf("2024-06-01");
        customer.setBankruptcyReviewDate(date);
        assertThat(customer.getBankruptcyReviewDate()).isEqualTo(date);
    }

    @Test
    void testStopWorkIndicator() {
        customer.setStopWorkIndicator(true);
        assertThat(customer.isStopWorkIndicator()).isTrue();
    }

    @Test
    void testStopWorkReason() {
        customer.setStopWorkReason("Payment overdue");
        assertThat(customer.getStopWorkReason()).isEqualTo("Payment overdue");
    }

    @Test
    void testCustomerInvoiceTemplateCode() {
        customer.setCustomerInvoiceTemplateCode("TMPL01");
        assertThat(customer.getCustomerInvoiceTemplateCode()).isEqualTo("TMPL01");
    }

    @Test
    void testCustomerType() {
        CustomerType type = new CustomerType();
        type.setCustomerTypeCode("GV");
        customer.setCustomerType(type);
        assertThat(customer.getCustomerType()).isNotNull();
        assertThat(customer.getCustomerType().getCustomerTypeCode()).isEqualTo("GV");
    }
}
