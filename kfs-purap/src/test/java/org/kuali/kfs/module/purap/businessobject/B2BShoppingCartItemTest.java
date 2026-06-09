package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class B2BShoppingCartItemTest extends KfsUnitTestBase {

    @Test
    void defaultConstructorFieldsNull() {
        B2BShoppingCartItem item = new B2BShoppingCartItem();
        assertThat(item.getQuantity()).isNull();
        assertThat(item.getSupplierPartId()).isNull();
        assertThat(item.getSupplierPartAuxiliaryId()).isNull();
        assertThat(item.getUnitPrice()).isNull();
        assertThat(item.getUnitPriceCurrency()).isNull();
        assertThat(item.getDescription()).isNull();
        assertThat(item.getUnitOfMeasure()).isNull();
        assertThat(item.getManufacturerPartID()).isNull();
        assertThat(item.getManufacturerName()).isNull();
    }

    @Test
    void settersAndGetters() {
        B2BShoppingCartItem item = new B2BShoppingCartItem();
        item.setQuantity("10");
        item.setSupplierPartId("SP-001");
        item.setSupplierPartAuxiliaryId("AUX-001");
        item.setUnitPrice("25.50");
        item.setUnitPriceCurrency("USD");
        item.setDescription("Test Widget");
        item.setUnitOfMeasure("EA");
        item.setManufacturerPartID("MFG-001");
        item.setManufacturerName("ACME Corp");

        assertThat(item.getQuantity()).isEqualTo("10");
        assertThat(item.getSupplierPartId()).isEqualTo("SP-001");
        assertThat(item.getSupplierPartAuxiliaryId()).isEqualTo("AUX-001");
        assertThat(item.getUnitPrice()).isEqualTo("25.50");
        assertThat(item.getUnitPriceCurrency()).isEqualTo("USD");
        assertThat(item.getDescription()).isEqualTo("Test Widget");
        assertThat(item.getUnitOfMeasure()).isEqualTo("EA");
        assertThat(item.getManufacturerPartID()).isEqualTo("MFG-001");
        assertThat(item.getManufacturerName()).isEqualTo("ACME Corp");
    }

    @Test
    void classificationMethods() {
        B2BShoppingCartItem item = new B2BShoppingCartItem();
        item.addClassification("UNSPSC", "43211500");
        assertThat(item.getClassification("UNSPSC")).isEqualTo("43211500");
        assertThat(item.getClassification("OTHER")).isNull();
        assertThat(item.getClassification()).containsKey("UNSPSC");
    }

    @Test
    void extrinsicMethods() {
        B2BShoppingCartItem item = new B2BShoppingCartItem();
        item.addExtrinsic("CostCenter", "CC-100");
        assertThat(item.getExtrinsic("CostCenter")).isEqualTo("CC-100");
        assertThat(item.getExtrinsic("Missing")).isNull();
        assertThat(item.getExtrinsic()).containsKey("CostCenter");
    }

    @Test
    void supplierMethods() {
        B2BShoppingCartItem item = new B2BShoppingCartItem();
        item.setSupplier("DUNS", "123456789");
        assertThat(item.getSupplier("DUNS")).isEqualTo("123456789");
        assertThat(item.getSupplier("OTHER")).isNull();
        assertThat(item.getSupplier()).containsKey("DUNS");
    }

    @Test
    void toStringContainsFields() {
        B2BShoppingCartItem item = new B2BShoppingCartItem();
        item.setQuantity("5");
        item.setDescription("Widget");
        String result = item.toString();
        assertThat(result).contains("5").contains("Widget");
    }
}
