package org.kuali.kfs.module.ar.batch.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerDigesterVOTest extends KfsUnitTestBase {

    private CustomerDigesterVO vo;

    @BeforeEach
    void setUp() {
        vo = new CustomerDigesterVO();
    }

    @Test
    void testDefaultConstructorInitializesAddressList() {
        assertThat(vo.getCustomerAddresses()).isNotNull().isEmpty();
    }

    @Test
    void testCustomerNumber() {
        vo.setCustomerNumber("CUST001");
        assertThat(vo.getCustomerNumber()).isEqualTo("CUST001");
    }

    @Test
    void testCustomerName() {
        vo.setCustomerName("Test Corp");
        assertThat(vo.getCustomerName()).isEqualTo("Test Corp");
    }

    @Test
    void testCustomerParentCompanyNumber() {
        vo.setCustomerParentCompanyNumber("PARENT01");
        assertThat(vo.getCustomerParentCompanyNumber()).isEqualTo("PARENT01");
    }

    @Test
    void testCustomerTypeCode() {
        vo.setCustomerTypeCode("GV");
        assertThat(vo.getCustomerTypeCode()).isEqualTo("GV");
    }

    @Test
    void testCustomerLastActivityDate() {
        vo.setCustomerLastActivityDate("2024-03-15");
        assertThat(vo.getCustomerLastActivityDate()).isEqualTo("2024-03-15");
    }

    @Test
    void testCustomerTaxTypeCode() {
        vo.setCustomerTaxTypeCode("FEIN");
        assertThat(vo.getCustomerTaxTypeCode()).isEqualTo("FEIN");
    }

    @Test
    void testCustomerTaxNbr() {
        vo.setCustomerTaxNbr("123456789");
        assertThat(vo.getCustomerTaxNbr()).isEqualTo("123456789");
    }

    @Test
    void testCustomerActiveIndicator() {
        vo.setCustomerActiveIndicator("Y");
        assertThat(vo.getCustomerActiveIndicator()).isEqualTo("Y");
    }

    @Test
    void testCustomerPhoneNumber() {
        vo.setCustomerPhoneNumber("555-0100");
        assertThat(vo.getCustomerPhoneNumber()).isEqualTo("555-0100");
    }

    @Test
    void testCustomer800PhoneNumber() {
        vo.setCustomer800PhoneNumber("800-555-0100");
        assertThat(vo.getCustomer800PhoneNumber()).isEqualTo("800-555-0100");
    }

    @Test
    void testCustomerContactName() {
        vo.setCustomerContactName("John Doe");
        assertThat(vo.getCustomerContactName()).isEqualTo("John Doe");
    }

    @Test
    void testCustomerContactPhoneNumber() {
        vo.setCustomerContactPhoneNumber("555-0200");
        assertThat(vo.getCustomerContactPhoneNumber()).isEqualTo("555-0200");
    }

    @Test
    void testCustomerFaxNumber() {
        vo.setCustomerFaxNumber("555-0300");
        assertThat(vo.getCustomerFaxNumber()).isEqualTo("555-0300");
    }

    @Test
    void testCustomerBirthDate() {
        vo.setCustomerBirthDate("1990-01-01");
        assertThat(vo.getCustomerBirthDate()).isEqualTo("1990-01-01");
    }

    @Test
    void testCustomerTaxExemptIndicator() {
        vo.setCustomerTaxExemptIndicator("Y");
        assertThat(vo.getCustomerTaxExemptIndicator()).isEqualTo("Y");
    }

    @Test
    void testCustomerCreditLimitAmount() {
        vo.setCustomerCreditLimitAmount("50000");
        assertThat(vo.getCustomerCreditLimitAmount()).isEqualTo("50000");
    }

    @Test
    void testCustomerCreditApprovedByName() {
        vo.setCustomerCreditApprovedByName("Jane Smith");
        assertThat(vo.getCustomerCreditApprovedByName()).isEqualTo("Jane Smith");
    }

    @Test
    void testCustomerEmailAddress() {
        vo.setCustomerEmailAddress("test@example.com");
        assertThat(vo.getCustomerEmailAddress()).isEqualTo("test@example.com");
    }

    @Test
    void testCustomerAddresses() {
        List<CustomerAddressDigesterVO> addresses = new ArrayList<>();
        CustomerAddressDigesterVO addr = new CustomerAddressDigesterVO();
        addr.setCustomerAddressName("Main Office");
        addresses.add(addr);

        vo.setCustomerAddresses(addresses);
        assertThat(vo.getCustomerAddresses()).hasSize(1);
        assertThat(vo.getCustomerAddresses().get(0).getCustomerAddressName()).isEqualTo("Main Office");
    }
}
