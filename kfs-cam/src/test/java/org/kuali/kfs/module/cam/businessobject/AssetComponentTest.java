package org.kuali.kfs.module.cam.businessobject;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AssetComponentTest extends KfsUnitTestBase {

    private AssetComponent component;

    @BeforeEach
    void setUp() {
        component = new AssetComponent();
    }

    @Test
    @DisplayName("default constructor: all fields null/default")
    void defaultConstructor() {
        assertThat(component.getCapitalAssetNumber()).isNull();
        assertThat(component.getComponentNumber()).isNull();
        assertThat(component.getComponentDescription()).isNull();
        assertThat(component.isActive()).isFalse();
    }

    @Test
    @DisplayName("capitalAssetNumber getter/setter")
    void capitalAssetNumber() {
        component.setCapitalAssetNumber(12345L);
        assertThat(component.getCapitalAssetNumber()).isEqualTo(12345L);
    }

    @Test
    @DisplayName("componentNumber getter/setter")
    void componentNumber() {
        component.setComponentNumber(1);
        assertThat(component.getComponentNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("componentDescription getter/setter")
    void componentDescription() {
        component.setComponentDescription("Monitor");
        assertThat(component.getComponentDescription()).isEqualTo("Monitor");
    }

    @Test
    @DisplayName("componentContactPhoneNumber getter/setter")
    void componentContactPhoneNumber() {
        component.setComponentContactPhoneNumber("555-1234");
        assertThat(component.getComponentContactPhoneNumber()).isEqualTo("555-1234");
    }

    @Test
    @DisplayName("componentConditionCode getter/setter")
    void componentConditionCode() {
        component.setComponentConditionCode("G");
        assertThat(component.getComponentConditionCode()).isEqualTo("G");
    }

    @Test
    @DisplayName("componentEstimatedLifetimeLimit getter/setter")
    void componentEstimatedLifetimeLimit() {
        component.setComponentEstimatedLifetimeLimit(5);
        assertThat(component.getComponentEstimatedLifetimeLimit()).isEqualTo(5);
    }

    @Test
    @DisplayName("componentManufacturerName getter/setter")
    void componentManufacturerName() {
        component.setComponentManufacturerName("Dell");
        assertThat(component.getComponentManufacturerName()).isEqualTo("Dell");
    }

    @Test
    @DisplayName("componentSerialNumber getter/setter")
    void componentSerialNumber() {
        component.setComponentSerialNumber("SN123456");
        assertThat(component.getComponentSerialNumber()).isEqualTo("SN123456");
    }

    @Test
    @DisplayName("componentReplacementAmount getter/setter")
    void componentReplacementAmount() {
        KualiDecimal amount = new KualiDecimal(2500);
        component.setComponentReplacementAmount(amount);
        assertThat(component.getComponentReplacementAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("componentWarrantyNumber getter/setter")
    void componentWarrantyNumber() {
        component.setComponentWarrantyNumber("WRN001");
        assertThat(component.getComponentWarrantyNumber()).isEqualTo("WRN001");
    }

    @Test
    @DisplayName("componentWarrantyBeginningDate and endingDate")
    void componentWarrantyDates() {
        Date begin = Date.valueOf("2024-01-01");
        Date end = Date.valueOf("2027-01-01");
        component.setComponentWarrantyBeginningDate(begin);
        component.setComponentWarrantyEndingDate(end);
        assertThat(component.getComponentWarrantyBeginningDate()).isEqualTo(begin);
        assertThat(component.getComponentWarrantyEndingDate()).isEqualTo(end);
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        component.setActive(true);
        assertThat(component.isActive()).isTrue();
        component.setActive(false);
        assertThat(component.isActive()).isFalse();
    }

    @Test
    @DisplayName("governmentTagNumber and nationalStockNumber")
    void govTagAndNSN() {
        component.setGovernmentTagNumber("GOV123");
        component.setNationalStockNumber("NSN456");
        assertThat(component.getGovernmentTagNumber()).isEqualTo("GOV123");
        assertThat(component.getNationalStockNumber()).isEqualTo("NSN456");
    }

    @Test
    @DisplayName("componentManufacturerModelNumber getter/setter")
    void componentManufacturerModelNumber() {
        component.setComponentManufacturerModelNumber("XPS-15");
        assertThat(component.getComponentManufacturerModelNumber()).isEqualTo("XPS-15");
    }

    @Test
    @DisplayName("componentVendorName getter/setter")
    void componentVendorName() {
        component.setComponentVendorName("CDW");
        assertThat(component.getComponentVendorName()).isEqualTo("CDW");
    }

    @Test
    @DisplayName("componentWarrantyPhoneNumber getter/setter")
    void componentWarrantyPhoneNumber() {
        component.setComponentWarrantyPhoneNumber("800-555-1234");
        assertThat(component.getComponentWarrantyPhoneNumber()).isEqualTo("800-555-1234");
    }

    @Test
    @DisplayName("componentWarrantyContactName getter/setter")
    void componentWarrantyContactName() {
        component.setComponentWarrantyContactName("John Doe");
        assertThat(component.getComponentWarrantyContactName()).isEqualTo("John Doe");
    }

    @Test
    @DisplayName("componentWarrantyPurchaseOrderNumber getter/setter")
    void componentWarrantyPurchaseOrderNumber() {
        component.setComponentWarrantyPurchaseOrderNumber("PO-12345");
        assertThat(component.getComponentWarrantyPurchaseOrderNumber()).isEqualTo("PO-12345");
    }

    @Test
    @DisplayName("componentWarrantyText getter/setter")
    void componentWarrantyText() {
        component.setComponentWarrantyText("3-year warranty");
        assertThat(component.getComponentWarrantyText()).isEqualTo("3-year warranty");
    }

    @Test
    @DisplayName("componentOrganizationTagNumber getter/setter")
    void componentOrganizationTagNumber() {
        component.setComponentOrganizationTagNumber("ORG-TAG-001");
        assertThat(component.getComponentOrganizationTagNumber()).isEqualTo("ORG-TAG-001");
    }

    @Test
    @DisplayName("componentOrganizationText getter/setter")
    void componentOrganizationText() {
        component.setComponentOrganizationText("IT Department");
        assertThat(component.getComponentOrganizationText()).isEqualTo("IT Department");
    }
}
