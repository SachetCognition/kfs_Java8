package org.kuali.kfs.vnd.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class VendorAddressTest extends KfsUnitTestBase {

    private VendorAddress vendorAddress;

    @BeforeEach
    void setUp() {
        vendorAddress = new VendorAddress();
    }

    @Test
    void defaultConstructorInitializesDefaultAddresses() {
        assertThat(vendorAddress.getVendorDefaultAddresses()).isNotNull().isEmpty();
    }

    @Test
    void vendorAddressGeneratedIdentifierGetterSetter() {
        vendorAddress.setVendorAddressGeneratedIdentifier(100);
        assertThat(vendorAddress.getVendorAddressGeneratedIdentifier()).isEqualTo(100);
    }

    @Test
    void vendorHeaderGeneratedIdentifierGetterSetter() {
        vendorAddress.setVendorHeaderGeneratedIdentifier(200);
        assertThat(vendorAddress.getVendorHeaderGeneratedIdentifier()).isEqualTo(200);
    }

    @Test
    void vendorDetailAssignedIdentifierGetterSetter() {
        vendorAddress.setVendorDetailAssignedIdentifier(5);
        assertThat(vendorAddress.getVendorDetailAssignedIdentifier()).isEqualTo(5);
    }

    @Test
    void vendorAddressTypeCodeGetterSetter() {
        vendorAddress.setVendorAddressTypeCode("PO");
        assertThat(vendorAddress.getVendorAddressTypeCode()).isEqualTo("PO");
    }

    @Test
    void vendorLine1AddressGetterSetter() {
        vendorAddress.setVendorLine1Address("123 Main St");
        assertThat(vendorAddress.getVendorLine1Address()).isEqualTo("123 Main St");
    }

    @Test
    void vendorLine2AddressGetterSetter() {
        vendorAddress.setVendorLine2Address("Suite 100");
        assertThat(vendorAddress.getVendorLine2Address()).isEqualTo("Suite 100");
    }

    @Test
    void vendorCityNameGetterSetter() {
        vendorAddress.setVendorCityName("Springfield");
        assertThat(vendorAddress.getVendorCityName()).isEqualTo("Springfield");
    }

    @Test
    void vendorStateCodeGetterSetter() {
        vendorAddress.setVendorStateCode("IL");
        assertThat(vendorAddress.getVendorStateCode()).isEqualTo("IL");
    }

    @Test
    void vendorZipCodeGetterSetter() {
        vendorAddress.setVendorZipCode("62701");
        assertThat(vendorAddress.getVendorZipCode()).isEqualTo("62701");
    }

    @Test
    void vendorCountryCodeGetterSetter() {
        vendorAddress.setVendorCountryCode("US");
        assertThat(vendorAddress.getVendorCountryCode()).isEqualTo("US");
    }

    @Test
    void vendorAttentionNameGetterSetter() {
        vendorAddress.setVendorAttentionName("John Doe");
        assertThat(vendorAddress.getVendorAttentionName()).isEqualTo("John Doe");
    }

    @Test
    void vendorAddressEmailAddressGetterSetter() {
        vendorAddress.setVendorAddressEmailAddress("vendor@example.com");
        assertThat(vendorAddress.getVendorAddressEmailAddress()).isEqualTo("vendor@example.com");
    }

    @Test
    void vendorFaxNumberGetterSetter() {
        vendorAddress.setVendorFaxNumber("555-123-4567");
        assertThat(vendorAddress.getVendorFaxNumber()).isEqualTo("555-123-4567");
    }

    @Test
    void vendorDefaultAddressIndicatorGetterSetter() {
        vendorAddress.setVendorDefaultAddressIndicator(true);
        assertThat(vendorAddress.isVendorDefaultAddressIndicator()).isTrue();

        vendorAddress.setVendorDefaultAddressIndicator(false);
        assertThat(vendorAddress.isVendorDefaultAddressIndicator()).isFalse();
    }

    @Test
    void activeIndicatorGetterSetter() {
        vendorAddress.setActive(true);
        assertThat(vendorAddress.isActive()).isTrue();

        vendorAddress.setActive(false);
        assertThat(vendorAddress.isActive()).isFalse();
    }

    @Test
    void vendorAddressInternationalProvinceNameGetterSetter() {
        vendorAddress.setVendorAddressInternationalProvinceName("Ontario");
        assertThat(vendorAddress.getVendorAddressInternationalProvinceName()).isEqualTo("Ontario");
    }

    @Test
    void vendorBusinessToBusinessUrlAddressGetterSetter() {
        vendorAddress.setVendorBusinessToBusinessUrlAddress("https://b2b.example.com");
        assertThat(vendorAddress.getVendorBusinessToBusinessUrlAddress()).isEqualTo("https://b2b.example.com");
    }
}
