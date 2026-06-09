package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetPaymentAllocationTypeTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        AssetPaymentAllocationType type = new AssetPaymentAllocationType();
        assertThat(type.getAllocationCode()).isNull();
        assertThat(type.getAllocationName()).isNull();
        assertThat(type.getAllocationColumnName()).isNull();
        assertThat(type.isActive()).isFalse();
        assertThat(type.isAllocationEditable()).isFalse();
    }

    @Test
    @DisplayName("allocationCode getter/setter")
    void allocationCode() {
        AssetPaymentAllocationType type = new AssetPaymentAllocationType();
        type.setAllocationCode("EQ");
        assertThat(type.getAllocationCode()).isEqualTo("EQ");
    }

    @Test
    @DisplayName("allocationName getter/setter")
    void allocationName() {
        AssetPaymentAllocationType type = new AssetPaymentAllocationType();
        type.setAllocationName("Equal Distribution");
        assertThat(type.getAllocationName()).isEqualTo("Equal Distribution");
    }

    @Test
    @DisplayName("allocationColumnName getter/setter")
    void allocationColumnName() {
        AssetPaymentAllocationType type = new AssetPaymentAllocationType();
        type.setAllocationColumnName("Amount");
        assertThat(type.getAllocationColumnName()).isEqualTo("Amount");
    }

    @Test
    @DisplayName("allocationEditable getter/setter")
    void allocationEditable() {
        AssetPaymentAllocationType type = new AssetPaymentAllocationType();
        type.setAllocationEditable(true);
        assertThat(type.isAllocationEditable()).isTrue();
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        AssetPaymentAllocationType type = new AssetPaymentAllocationType();
        type.setActive(true);
        assertThat(type.isActive()).isTrue();
    }

    @Test
    @DisplayName("toStringMapper contains AllocationCode")
    void toStringMapper() {
        AssetPaymentAllocationType type = new AssetPaymentAllocationType();
        type.setAllocationCode("PR");
        java.util.LinkedHashMap map = type.toStringMapper_RICE20_REFACTORME();
        assertThat(map).containsEntry("AllocationCode", "PR");
    }
}
