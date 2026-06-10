package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BillingAddressTest extends KfsUnitTestBase {

    @Test
    void defaultConstructorInitializesFieldsToNull() {
        BillingAddress addr = new BillingAddress();
        assertThat(addr.getBillingCampusCode()).isNull();
        assertThat(addr.getBillingName()).isNull();
        assertThat(addr.getBillingLine1Address()).isNull();
        assertThat(addr.getBillingLine2Address()).isNull();
        assertThat(addr.getBillingCityName()).isNull();
        assertThat(addr.getBillingStateCode()).isNull();
        assertThat(addr.getBillingPostalCode()).isNull();
        assertThat(addr.getBillingCountryCode()).isNull();
        assertThat(addr.getBillingPhoneNumber()).isNull();
        assertThat(addr.getBillingEmailAddress()).isNull();
        assertThat(addr.isActive()).isFalse();
    }

    @Test
    void settersAndGetters() {
        BillingAddress addr = new BillingAddress();
        addr.setBillingCampusCode("BL");
        addr.setBillingName("Test University");
        addr.setBillingLine1Address("123 Main St");
        addr.setBillingLine2Address("Suite 100");
        addr.setBillingCityName("Bloomington");
        addr.setBillingStateCode("IN");
        addr.setBillingPostalCode("47405");
        addr.setBillingCountryCode("US");
        addr.setBillingPhoneNumber("812-555-1234");
        addr.setBillingEmailAddress("billing@test.edu");
        addr.setActive(true);

        assertThat(addr.getBillingCampusCode()).isEqualTo("BL");
        assertThat(addr.getBillingName()).isEqualTo("Test University");
        assertThat(addr.getBillingLine1Address()).isEqualTo("123 Main St");
        assertThat(addr.getBillingLine2Address()).isEqualTo("Suite 100");
        assertThat(addr.getBillingCityName()).isEqualTo("Bloomington");
        assertThat(addr.getBillingStateCode()).isEqualTo("IN");
        assertThat(addr.getBillingPostalCode()).isEqualTo("47405");
        assertThat(addr.getBillingCountryCode()).isEqualTo("US");
        assertThat(addr.getBillingPhoneNumber()).isEqualTo("812-555-1234");
        assertThat(addr.getBillingEmailAddress()).isEqualTo("billing@test.edu");
        assertThat(addr.isActive()).isTrue();
    }
}
