package org.kuali.kfs.module.cam.businessobject;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetWarrantyTest extends KfsUnitTestBase {

    private AssetWarranty warranty;

    @BeforeEach
    void setUp() {
        warranty = new AssetWarranty();
    }

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        assertThat(warranty.getCapitalAssetNumber()).isNull();
        assertThat(warranty.getWarrantyContactName()).isNull();
        assertThat(warranty.getWarrantyPhoneNumber()).isNull();
        assertThat(warranty.getWarrantyBeginningDate()).isNull();
        assertThat(warranty.getWarrantyEndingDate()).isNull();
        assertThat(warranty.getWarrantyNumber()).isNull();
        assertThat(warranty.getWarrantyPurchaseOrderNumber()).isNull();
        assertThat(warranty.getWarrantyText()).isNull();
        assertThat(warranty.isActive()).isFalse();
    }

    @Test
    @DisplayName("capitalAssetNumber getter/setter")
    void capitalAssetNumber() {
        warranty.setCapitalAssetNumber(12345L);
        assertThat(warranty.getCapitalAssetNumber()).isEqualTo(12345L);
    }

    @Test
    @DisplayName("warrantyContactName getter/setter")
    void warrantyContactName() {
        warranty.setWarrantyContactName("John Doe");
        assertThat(warranty.getWarrantyContactName()).isEqualTo("John Doe");
    }

    @Test
    @DisplayName("warrantyPhoneNumber getter/setter")
    void warrantyPhoneNumber() {
        warranty.setWarrantyPhoneNumber("555-1234");
        assertThat(warranty.getWarrantyPhoneNumber()).isEqualTo("555-1234");
    }

    @Test
    @DisplayName("warranty date range")
    void warrantyDates() {
        Date begin = Date.valueOf("2024-01-01");
        Date end = Date.valueOf("2025-12-31");
        warranty.setWarrantyBeginningDate(begin);
        warranty.setWarrantyEndingDate(end);
        assertThat(warranty.getWarrantyBeginningDate()).isEqualTo(begin);
        assertThat(warranty.getWarrantyEndingDate()).isEqualTo(end);
    }

    @Test
    @DisplayName("warrantyNumber getter/setter")
    void warrantyNumber() {
        warranty.setWarrantyNumber("W-001");
        assertThat(warranty.getWarrantyNumber()).isEqualTo("W-001");
    }

    @Test
    @DisplayName("warrantyPurchaseOrderNumber getter/setter")
    void warrantyPurchaseOrderNumber() {
        warranty.setWarrantyPurchaseOrderNumber("PO-999");
        assertThat(warranty.getWarrantyPurchaseOrderNumber()).isEqualTo("PO-999");
    }

    @Test
    @DisplayName("warrantyText getter/setter")
    void warrantyText() {
        warranty.setWarrantyText("Standard 2-year warranty");
        assertThat(warranty.getWarrantyText()).isEqualTo("Standard 2-year warranty");
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        warranty.setActive(true);
        assertThat(warranty.isActive()).isTrue();
    }
}
