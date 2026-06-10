package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerAddressTest extends KfsUnitTestBase {

    private CustomerAddress address;

    @BeforeEach
    void setUp() {
        address = new CustomerAddress();
    }

    @Test
    void testCustomerNumber() {
        address.setCustomerNumber("CUST001");
        assertThat(address.getCustomerNumber()).isEqualTo("CUST001");
    }

    @Test
    void testCustomerAddressIdentifier() {
        address.setCustomerAddressIdentifier(1);
        assertThat(address.getCustomerAddressIdentifier()).isEqualTo(1);
    }

    @Test
    void testCustomerAddressName() {
        address.setCustomerAddressName("Main Office");
        assertThat(address.getCustomerAddressName()).isEqualTo("Main Office");
    }

    @Test
    void testCustomerLine1StreetAddress() {
        address.setCustomerLine1StreetAddress("123 Main St");
        assertThat(address.getCustomerLine1StreetAddress()).isEqualTo("123 Main St");
    }

    @Test
    void testCustomerLine2StreetAddress() {
        address.setCustomerLine2StreetAddress("Suite 100");
        assertThat(address.getCustomerLine2StreetAddress()).isEqualTo("Suite 100");
    }

    @Test
    void testCustomerCityName() {
        address.setCustomerCityName("Bloomington");
        assertThat(address.getCustomerCityName()).isEqualTo("Bloomington");
    }

    @Test
    void testCustomerStateCode() {
        address.setCustomerStateCode("IN");
        assertThat(address.getCustomerStateCode()).isEqualTo("IN");
    }

    @Test
    void testCustomerZipCode() {
        address.setCustomerZipCode("47401");
        assertThat(address.getCustomerZipCode()).isEqualTo("47401");
    }

    @Test
    void testCustomerCountryCode() {
        address.setCustomerCountryCode("US");
        assertThat(address.getCustomerCountryCode()).isEqualTo("US");
    }

    @Test
    void testCustomerAddressInternationalProvinceName() {
        address.setCustomerAddressInternationalProvinceName("Ontario");
        assertThat(address.getCustomerAddressInternationalProvinceName()).isEqualTo("Ontario");
    }

    @Test
    void testCustomerInternationalMailCode() {
        address.setCustomerInternationalMailCode("M5V 3L9");
        assertThat(address.getCustomerInternationalMailCode()).isEqualTo("M5V 3L9");
    }

    @Test
    void testCustomerEmailAddress() {
        address.setCustomerEmailAddress("contact@example.com");
        assertThat(address.getCustomerEmailAddress()).isEqualTo("contact@example.com");
    }

    @Test
    void testCustomerAddressTypeCode() {
        address.setCustomerAddressTypeCode("P");
        assertThat(address.getCustomerAddressTypeCode()).isEqualTo("P");
    }

    @Test
    void testCustomerAddressEndDate() {
        Date endDate = Date.valueOf("2025-12-31");
        address.setCustomerAddressEndDate(endDate);
        assertThat(address.getCustomerAddressEndDate()).isEqualTo(endDate);
    }

    @Test
    void testCustomerInvoiceTemplateCode() {
        address.setCustomerInvoiceTemplateCode("TMPL01");
        assertThat(address.getCustomerInvoiceTemplateCode()).isEqualTo("TMPL01");
    }

    @Test
    void testInvoiceTransmissionMethodCode() {
        address.setInvoiceTransmissionMethodCode("MAIL");
        assertThat(address.getInvoiceTransmissionMethodCode()).isEqualTo("MAIL");
    }

    @Test
    void testCustomerCopiesToPrint() {
        address.setCustomerCopiesToPrint(3);
        assertThat(address.getCustomerCopiesToPrint()).isEqualTo(3);
    }

    @Test
    void testCustomerEnvelopesToPrintQuantity() {
        address.setCustomerEnvelopesToPrintQuantity(2);
        assertThat(address.getCustomerEnvelopesToPrintQuantity()).isEqualTo(2);
    }

    @Test
    void testCompareToSameAddressReturnsZero() {
        CustomerAddress address1 = new CustomerAddress();
        address1.setCustomerNumber("CUST001");
        address1.setCustomerAddressIdentifier(1);

        CustomerAddress address2 = new CustomerAddress();
        address2.setCustomerNumber("CUST001");
        address2.setCustomerAddressIdentifier(1);

        assertThat(address1.compareTo(address2)).isZero();
    }

    @Test
    void testCompareToDifferentCustomerNumber() {
        CustomerAddress address1 = new CustomerAddress();
        address1.setCustomerNumber("CUST001");

        CustomerAddress address2 = new CustomerAddress();
        address2.setCustomerNumber("CUST002");

        assertThat(address1.compareTo(address2)).isEqualTo(-1);
    }

    @Test
    void testCompareToDifferentIdentifier() {
        CustomerAddress address1 = new CustomerAddress();
        address1.setCustomerAddressIdentifier(1);

        CustomerAddress address2 = new CustomerAddress();
        address2.setCustomerAddressIdentifier(2);

        assertThat(address1.compareTo(address2)).isEqualTo(-1);
    }
}
