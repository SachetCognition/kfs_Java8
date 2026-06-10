package org.kuali.kfs.vnd.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class DebarredVendorDetailTest extends KfsUnitTestBase {

    private DebarredVendorDetail vendor;

    @BeforeEach
    void setUp() {
        vendor = new DebarredVendorDetail();
    }

    @Test
    void debarredVendorIdGetterSetter() {
        vendor.setDebarredVendorId(42);
        assertThat(vendor.getDebarredVendorId()).isEqualTo(42);
    }

    @Test
    void loadDateGetterSetter() {
        Date now = new Date();
        vendor.setLoadDate(now);
        assertThat(vendor.getLoadDate()).isEqualTo(now);
    }

    @Test
    void nameGetterSetter() {
        vendor.setName("Debarred Corp");
        assertThat(vendor.getName()).isEqualTo("Debarred Corp");
    }

    @Test
    void address1GetterSetter() {
        vendor.setAddress1("100 Bad Road");
        assertThat(vendor.getAddress1()).isEqualTo("100 Bad Road");
    }

    @Test
    void address2GetterSetter() {
        vendor.setAddress2("Apt B");
        assertThat(vendor.getAddress2()).isEqualTo("Apt B");
    }

    @Test
    void cityGetterSetter() {
        vendor.setCity("Fraud City");
        assertThat(vendor.getCity()).isEqualTo("Fraud City");
    }

    @Test
    void stateGetterSetter() {
        vendor.setState("CA");
        assertThat(vendor.getState()).isEqualTo("CA");
    }

    @Test
    void provinceGetterSetter() {
        vendor.setProvince("British Columbia");
        assertThat(vendor.getProvince()).isEqualTo("British Columbia");
    }

    @Test
    void zipGetterSetter() {
        vendor.setZip("90210");
        assertThat(vendor.getZip()).isEqualTo("90210");
    }

    @Test
    void aliasesGetterSetter() {
        vendor.setAliases("Alias1;Alias2");
        assertThat(vendor.getAliases()).isEqualTo("Alias1;Alias2");
    }

    @Test
    void descriptionGetterSetter() {
        vendor.setDescription("Excluded for fraud");
        assertThat(vendor.getDescription()).isEqualTo("Excluded for fraud");
    }

    @Test
    void allFieldsPopulated() {
        Date loadDate = new Date();
        vendor.setDebarredVendorId(1);
        vendor.setLoadDate(loadDate);
        vendor.setName("Test Vendor");
        vendor.setAddress1("123 Main");
        vendor.setAddress2("Suite A");
        vendor.setCity("TestCity");
        vendor.setState("TX");
        vendor.setProvince("N/A");
        vendor.setZip("75001");
        vendor.setAliases("TV");
        vendor.setDescription("Test description");

        assertThat(vendor.getDebarredVendorId()).isEqualTo(1);
        assertThat(vendor.getLoadDate()).isEqualTo(loadDate);
        assertThat(vendor.getName()).isEqualTo("Test Vendor");
        assertThat(vendor.getAddress1()).isEqualTo("123 Main");
        assertThat(vendor.getAddress2()).isEqualTo("Suite A");
        assertThat(vendor.getCity()).isEqualTo("TestCity");
        assertThat(vendor.getState()).isEqualTo("TX");
        assertThat(vendor.getProvince()).isEqualTo("N/A");
        assertThat(vendor.getZip()).isEqualTo("75001");
        assertThat(vendor.getAliases()).isEqualTo("TV");
        assertThat(vendor.getDescription()).isEqualTo("Test description");
    }
}
