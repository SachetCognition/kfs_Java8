package org.kuali.kfs.module.tem.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CreditCardAgency Business Object")
class CreditCardAgencyTest extends KfsUnitTestBase {

    private CreditCardAgency agency;

    @BeforeEach
    void setUp() {
        agency = new CreditCardAgency();
    }

    @Test
    @DisplayName("should initialize with default values")
    void testDefaultConstruction() {
        assertThat(agency.getPaymentIndicator()).isFalse();
        assertThat(agency.getPreReconciled()).isFalse();
        assertThat(agency.getEnableNonReimbursable()).isFalse();
        assertThat(agency.getForeignCompany()).isFalse();
        assertThat(agency.isActive()).isTrue();
    }

    @Test
    @DisplayName("should set and get creditCardOrAgencyCode")
    void testCreditCardOrAgencyCode() {
        agency.setCreditCardOrAgencyCode("VISA");
        assertThat(agency.getCreditCardOrAgencyCode()).isEqualTo("VISA");
    }

    @Test
    @DisplayName("should set and get travelCardTypeCode")
    void testTravelCardTypeCode() {
        agency.setTravelCardTypeCode("CTS");
        assertThat(agency.getTravelCardTypeCode()).isEqualTo("CTS");
    }

    @Test
    @DisplayName("should set and get creditCardOrAgencyName")
    void testCreditCardOrAgencyName() {
        agency.setCreditCardOrAgencyName("American Express");
        assertThat(agency.getCreditCardOrAgencyName()).isEqualTo("American Express");
    }

    @Test
    @DisplayName("should set and get address fields")
    void testAddressFields() {
        agency.setAddress1("123 Main St");
        agency.setAddress2("Suite 200");
        agency.setCity("New York");
        agency.setState("NY");
        agency.setZipCode("10001");

        assertThat(agency.getAddress1()).isEqualTo("123 Main St");
        assertThat(agency.getAddress2()).isEqualTo("Suite 200");
        assertThat(agency.getCity()).isEqualTo("New York");
        assertThat(agency.getState()).isEqualTo("NY");
        assertThat(agency.getZipCode()).isEqualTo("10001");
    }

    @Test
    @DisplayName("should set and get contact fields")
    void testContactFields() {
        agency.setEmail("test@example.com");
        agency.setPhone("555-555-5555");
        agency.setContactName("John Doe");

        assertThat(agency.getEmail()).isEqualTo("test@example.com");
        assertThat(agency.getPhone()).isEqualTo("555-555-5555");
        assertThat(agency.getContactName()).isEqualTo("John Doe");
    }

    @Test
    @DisplayName("should set and get paymentIndicator")
    void testPaymentIndicator() {
        agency.setPaymentIndicator(Boolean.TRUE);
        assertThat(agency.getPaymentIndicator()).isTrue();
    }

    @Test
    @DisplayName("should set and get preReconciled")
    void testPreReconciled() {
        agency.setPreReconciled(Boolean.TRUE);
        assertThat(agency.getPreReconciled()).isTrue();
    }

    @Test
    @DisplayName("should set and get enableNonReimbursable")
    void testEnableNonReimbursable() {
        agency.setEnableNonReimbursable(Boolean.TRUE);
        assertThat(agency.getEnableNonReimbursable()).isTrue();
    }

    @Test
    @DisplayName("should set and get foreignCompany")
    void testForeignCompany() {
        agency.setForeignCompany(Boolean.TRUE);
        assertThat(agency.getForeignCompany()).isTrue();
    }

    @Test
    @DisplayName("should set and get active flag")
    void testActive() {
        agency.setActive(false);
        assertThat(agency.isActive()).isFalse();
    }

    @Test
    @DisplayName("should set and get bankCode")
    void testBankCode() {
        agency.setBankCode("BANK01");
        assertThat(agency.getBankCode()).isEqualTo("BANK01");
    }

    @Test
    @DisplayName("should set and get vendorHeaderGeneratedIdentifier")
    void testVendorHeaderGeneratedIdentifier() {
        agency.setVendorHeaderGeneratedIdentifier(12345);
        assertThat(agency.getVendorHeaderGeneratedIdentifier()).isEqualTo(12345);
    }

    @Test
    @DisplayName("should set and get vendorDetailAssignedIdentifier")
    void testVendorDetailAssignedIdentifier() {
        agency.setVendorDetailAssignedIdentifier(67890);
        assertThat(agency.getVendorDetailAssignedIdentifier()).isEqualTo(67890);
    }
}
