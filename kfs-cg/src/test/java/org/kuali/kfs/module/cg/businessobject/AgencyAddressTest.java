package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class AgencyAddressTest extends KfsUnitTestBase {

    private AgencyAddress address;

    @BeforeEach
    void setUp() {
        address = new AgencyAddress();
    }

    @Test
    void testAgencyNumber() {
        address.setAgencyNumber("AG001");
        assertThat(address.getAgencyNumber()).isEqualTo("AG001");
    }

    @Test
    void testAgencyAddressIdentifier() {
        address.setAgencyAddressIdentifier(1L);
        assertThat(address.getAgencyAddressIdentifier()).isEqualTo(1L);
    }

    @Test
    void testAgencyAddressName() {
        address.setAgencyAddressName("Main Office");
        assertThat(address.getAgencyAddressName()).isEqualTo("Main Office");
    }

    @Test
    void testAgencyContactName() {
        address.setAgencyContactName("Jane Doe");
        assertThat(address.getAgencyContactName()).isEqualTo("Jane Doe");
    }

    @Test
    void testAgencyLine1StreetAddress() {
        address.setAgencyLine1StreetAddress("123 Main St");
        assertThat(address.getAgencyLine1StreetAddress()).isEqualTo("123 Main St");
    }

    @Test
    void testAgencyLine2StreetAddress() {
        address.setAgencyLine2StreetAddress("Suite 100");
        assertThat(address.getAgencyLine2StreetAddress()).isEqualTo("Suite 100");
    }

    @Test
    void testAgencyCityName() {
        address.setAgencyCityName("Bloomington");
        assertThat(address.getAgencyCityName()).isEqualTo("Bloomington");
    }

    @Test
    void testAgencyStateCode() {
        address.setAgencyStateCode("IN");
        assertThat(address.getAgencyStateCode()).isEqualTo("IN");
    }

    @Test
    void testAgencyZipCode() {
        address.setAgencyZipCode("47405");
        assertThat(address.getAgencyZipCode()).isEqualTo("47405");
    }

    @Test
    void testAgencyCountryCode() {
        address.setAgencyCountryCode("US");
        assertThat(address.getAgencyCountryCode()).isEqualTo("US");
    }

    @Test
    void testAgencyPhoneNumber() {
        address.setAgencyPhoneNumber("555-0100");
        assertThat(address.getAgencyPhoneNumber()).isEqualTo("555-0100");
    }

    @Test
    void testAgencyFaxNumber() {
        address.setAgencyFaxNumber("555-0101");
        assertThat(address.getAgencyFaxNumber()).isEqualTo("555-0101");
    }

    @Test
    void testAgencyContactEmailAddress() {
        address.setAgencyContactEmailAddress("contact@agency.gov");
        assertThat(address.getAgencyContactEmailAddress()).isEqualTo("contact@agency.gov");
    }

    @Test
    void testCustomerAddressTypeCode() {
        address.setCustomerAddressTypeCode("P");
        assertThat(address.getCustomerAddressTypeCode()).isEqualTo("P");
    }

    @Test
    void testAgencyAddressEndDate() {
        Date date = Date.valueOf("2025-12-31");
        address.setAgencyAddressEndDate(date);
        assertThat(address.getAgencyAddressEndDate()).isEqualTo(date);
    }

    @Test
    void testAgencyAddressInternationalProvinceName() {
        address.setAgencyAddressInternationalProvinceName("Ontario");
        assertThat(address.getAgencyAddressInternationalProvinceName()).isEqualTo("Ontario");
    }

    @Test
    void testAgencyInternationalMailCode() {
        address.setAgencyInternationalMailCode("K1A 0A9");
        assertThat(address.getAgencyInternationalMailCode()).isEqualTo("K1A 0A9");
    }
}
