package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ElectronicInvoiceLoadSummaryTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        ElectronicInvoiceLoadSummary summary = new ElectronicInvoiceLoadSummary();
        assertThat(summary.getVendorDunsNumber()).isNull();
        assertThat(summary.getInvoiceLoadSuccessCount()).isEqualTo(0);
        assertThat(summary.getInvoiceLoadSuccessAmount()).isEqualTo(new KualiDecimal(0));
        assertThat(summary.getInvoiceLoadFailCount()).isEqualTo(0);
        assertThat(summary.getInvoiceLoadFailAmount()).isEqualTo(new KualiDecimal(0));
        assertThat(summary.isEmpty()).isTrue();
    }

    @Test
    void dunsConstructor() {
        ElectronicInvoiceLoadSummary summary = new ElectronicInvoiceLoadSummary("123456789");
        assertThat(summary.getVendorDunsNumber()).isEqualTo("123456789");
    }

    @Test
    void settersAndGetters() {
        ElectronicInvoiceLoadSummary summary = new ElectronicInvoiceLoadSummary();
        summary.setVendorDunsNumber("987654321");
        summary.setVendorHeaderGeneratedIdentifier(1000);
        summary.setVendorDetailAssignedIdentifier(0);
        summary.setVendorName("Test Vendor");

        assertThat(summary.getVendorDunsNumber()).isEqualTo("987654321");
        assertThat(summary.getVendorHeaderGeneratedIdentifier()).isEqualTo(1000);
        assertThat(summary.getVendorDetailAssignedIdentifier()).isEqualTo(0);
        assertThat(summary.getVendorName()).isEqualTo("Test Vendor");
    }

    @Test
    void vendorDescriptorWithAllFields() {
        ElectronicInvoiceLoadSummary summary = new ElectronicInvoiceLoadSummary("111222333");
        summary.setVendorHeaderGeneratedIdentifier(1000);
        summary.setVendorDetailAssignedIdentifier(0);
        summary.setVendorName("ACME Corp");
        String descriptor = summary.getVendorDescriptor();
        assertThat(descriptor).contains("111222333");
        assertThat(descriptor).contains("ACME Corp");
        assertThat(descriptor).contains("1000-0");
    }

    @Test
    void vendorDescriptorWithNameAndIds() {
        ElectronicInvoiceLoadSummary summary = new ElectronicInvoiceLoadSummary("111222333");
        summary.setVendorHeaderGeneratedIdentifier(1000);
        summary.setVendorDetailAssignedIdentifier(0);
        summary.setVendorName("Vendor X");
        String descriptor = summary.getVendorDescriptor();
        assertThat(descriptor).contains("Vendor X");
    }

    @Test
    void vendorDescriptorWithIdsOnly() {
        ElectronicInvoiceLoadSummary summary = new ElectronicInvoiceLoadSummary("111222333");
        summary.setVendorHeaderGeneratedIdentifier(2000);
        summary.setVendorDetailAssignedIdentifier(1);
        String descriptor = summary.getVendorDescriptor();
        assertThat(descriptor).contains("2000-1");
    }

    @Test
    void vendorDescriptorWithNameOnly() {
        ElectronicInvoiceLoadSummary summary = new ElectronicInvoiceLoadSummary("111222333");
        summary.setVendorName("Solo Vendor");
        String descriptor = summary.getVendorDescriptor();
        assertThat(descriptor).contains("Solo Vendor");
    }

    @Test
    void vendorDescriptorDunsOnly() {
        ElectronicInvoiceLoadSummary summary = new ElectronicInvoiceLoadSummary("111222333");
        String descriptor = summary.getVendorDescriptor();
        assertThat(descriptor).isEqualTo("111222333");
    }

    @Test
    void invoiceLoadSummaryIdentifier() {
        ElectronicInvoiceLoadSummary summary = new ElectronicInvoiceLoadSummary();
        summary.setInvoiceLoadSummaryIdentifier(42);
        assertThat(summary.getInvoiceLoadSummaryIdentifier()).isEqualTo(42);
    }

    @Test
    void setIsEmpty() {
        ElectronicInvoiceLoadSummary summary = new ElectronicInvoiceLoadSummary();
        assertThat(summary.isEmpty()).isTrue();
        summary.setIsEmpty(false);
        assertThat(summary.isEmpty()).isFalse();
    }
}
