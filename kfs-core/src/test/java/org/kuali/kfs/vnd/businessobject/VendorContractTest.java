package org.kuali.kfs.vnd.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class VendorContractTest extends KfsUnitTestBase {

    private VendorContract vendorContract;

    @BeforeEach
    void setUp() {
        vendorContract = new VendorContract();
    }

    @Test
    void defaultConstructorInitializesOrganizations() {
        assertThat(vendorContract.getVendorContractOrganizations()).isNotNull().isEmpty();
    }

    @Test
    void vendorContractGeneratedIdentifierGetterSetter() {
        vendorContract.setVendorContractGeneratedIdentifier(100);
        assertThat(vendorContract.getVendorContractGeneratedIdentifier()).isEqualTo(100);
    }

    @Test
    void vendorHeaderGeneratedIdentifierGetterSetter() {
        vendorContract.setVendorHeaderGeneratedIdentifier(200);
        assertThat(vendorContract.getVendorHeaderGeneratedIdentifier()).isEqualTo(200);
    }

    @Test
    void vendorDetailAssignedIdentifierGetterSetter() {
        vendorContract.setVendorDetailAssignedIdentifier(1);
        assertThat(vendorContract.getVendorDetailAssignedIdentifier()).isEqualTo(1);
    }

    @Test
    void vendorContractNameGetterSetter() {
        vendorContract.setVendorContractName("Office Supplies Contract");
        assertThat(vendorContract.getVendorContractName()).isEqualTo("Office Supplies Contract");
    }

    @Test
    void vendorContractDescriptionGetterSetter() {
        vendorContract.setVendorContractDescription("Annual office supplies agreement");
        assertThat(vendorContract.getVendorContractDescription()).isEqualTo("Annual office supplies agreement");
    }

    @Test
    void vendorCampusCodeGetterSetter() {
        vendorContract.setVendorCampusCode("BL");
        assertThat(vendorContract.getVendorCampusCode()).isEqualTo("BL");
    }

    @Test
    void vendorContractDatesGetterSetter() {
        Date begin = Date.valueOf("2024-01-01");
        Date end = Date.valueOf("2025-12-31");
        vendorContract.setVendorContractBeginningDate(begin);
        vendorContract.setVendorContractEndDate(end);

        assertThat(vendorContract.getVendorContractBeginningDate()).isEqualTo(begin);
        assertThat(vendorContract.getVendorContractEndDate()).isEqualTo(end);
    }

    @Test
    void contractManagerCodeGetterSetter() {
        vendorContract.setContractManagerCode(99);
        assertThat(vendorContract.getContractManagerCode()).isEqualTo(99);
    }

    @Test
    void purchaseOrderCostSourceCodeGetterSetter() {
        vendorContract.setPurchaseOrderCostSourceCode("EST");
        assertThat(vendorContract.getPurchaseOrderCostSourceCode()).isEqualTo("EST");
    }

    @Test
    void vendorPaymentTermsCodeGetterSetter() {
        vendorContract.setVendorPaymentTermsCode("NET30");
        assertThat(vendorContract.getVendorPaymentTermsCode()).isEqualTo("NET30");
    }

    @Test
    void vendorB2bIndicatorGetterSetter() {
        vendorContract.setVendorB2bIndicator(true);
        assertThat(vendorContract.getVendorB2bIndicator()).isTrue();
    }

    @Test
    void organizationAutomaticPurchaseOrderLimitGetterSetter() {
        KualiDecimal limit = new KualiDecimal("5000.00");
        vendorContract.setOrganizationAutomaticPurchaseOrderLimit(limit);
        assertThat(vendorContract.getOrganizationAutomaticPurchaseOrderLimit()).isEqualTo(limit);
    }

    @Test
    void activeIndicatorGetterSetter() {
        vendorContract.setActive(true);
        assertThat(vendorContract.isActive()).isTrue();

        vendorContract.setActive(false);
        assertThat(vendorContract.isActive()).isFalse();
    }

    @Test
    void vendorContractExtensionDateGetterSetter() {
        Date extension = Date.valueOf("2026-06-30");
        vendorContract.setVendorContractExtensionDate(extension);
        assertThat(vendorContract.getVendorContractExtensionDate()).isEqualTo(extension);
    }

    @Test
    void vendorShippingPaymentTermsCodeGetterSetter() {
        vendorContract.setVendorShippingPaymentTermsCode("PP");
        assertThat(vendorContract.getVendorShippingPaymentTermsCode()).isEqualTo("PP");
    }

    @Test
    void vendorShippingTitleCodeGetterSetter() {
        vendorContract.setVendorShippingTitleCode("FOB");
        assertThat(vendorContract.getVendorShippingTitleCode()).isEqualTo("FOB");
    }
}
