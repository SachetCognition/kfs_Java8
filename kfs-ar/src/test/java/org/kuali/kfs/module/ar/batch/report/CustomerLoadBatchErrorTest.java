package org.kuali.kfs.module.ar.batch.report;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerLoadBatchErrorTest extends KfsUnitTestBase {

    @Test
    void testDefaultConstructor() {
        CustomerLoadBatchError error = new CustomerLoadBatchError();
        assertThat(error.getCustomerName()).isEmpty();
        assertThat(error.getPropertyName()).isEmpty();
        assertThat(error.getPropertyClass()).isNull();
        assertThat(error.getValue()).isEmpty();
        assertThat(error.getDescription()).isEmpty();
    }

    @Test
    void testConstructorWithCustomerName() {
        CustomerLoadBatchError error = new CustomerLoadBatchError("Test Corp");
        assertThat(error.getCustomerName()).isEqualTo("Test Corp");
    }

    @Test
    void testFullConstructor() {
        CustomerLoadBatchError error = new CustomerLoadBatchError(
                "Test Corp", "customerName", String.class, "Invalid Name", "Name too long");

        assertThat(error.getCustomerName()).isEqualTo("Test Corp");
        assertThat(error.getPropertyName()).isEqualTo("customerName");
        assertThat(error.getPropertyClass()).isEqualTo(String.class);
        assertThat(error.getValue()).isEqualTo("Invalid Name");
        assertThat(error.getDescription()).isEqualTo("Name too long");
    }

    @Test
    void testToStringContainsCustomerName() {
        CustomerLoadBatchError error = new CustomerLoadBatchError(
                "Test Corp", "customerName", String.class, "BadValue", "Invalid format");

        String result = error.toString();
        assertThat(result).contains("[Test Corp]");
        assertThat(result).contains("Invalid format");
    }

    @Test
    void testGetPropertyNameLastElement_simple() {
        CustomerLoadBatchError error = new CustomerLoadBatchError();
        error.setPropertyName("customerName");
        assertThat(error.getPropertyNameLastElement()).isEqualTo("customerName");
    }

    @Test
    void testGetPropertyNameLastElement_dotSeparated() {
        CustomerLoadBatchError error = new CustomerLoadBatchError();
        error.setPropertyName("customer.address.cityName");
        assertThat(error.getPropertyNameLastElement()).isEqualTo("cityName");
    }

    @Test
    void testGetPropertyNameLastElement_blank() {
        CustomerLoadBatchError error = new CustomerLoadBatchError();
        error.setPropertyName("");
        assertThat(error.getPropertyNameLastElement()).isEmpty();
    }

    @Test
    void testSetters() {
        CustomerLoadBatchError error = new CustomerLoadBatchError();
        error.setCustomerName("Updated Corp");
        error.setPropertyName("email");
        error.setPropertyClass(String.class);
        error.setValue("bad@");
        error.setDescription("Invalid email");

        assertThat(error.getCustomerName()).isEqualTo("Updated Corp");
        assertThat(error.getPropertyName()).isEqualTo("email");
        assertThat(error.getPropertyClass()).isEqualTo(String.class);
        assertThat(error.getValue()).isEqualTo("bad@");
        assertThat(error.getDescription()).isEqualTo("Invalid email");
    }
}
