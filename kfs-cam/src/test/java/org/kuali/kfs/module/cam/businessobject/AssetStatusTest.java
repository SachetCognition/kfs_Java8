package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetStatusTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        AssetStatus status = new AssetStatus();
        assertThat(status.getInventoryStatusCode()).isNull();
        assertThat(status.getInventoryStatusName()).isNull();
        assertThat(status.isActive()).isFalse();
    }

    @Test
    @DisplayName("inventoryStatusCode getter/setter")
    void inventoryStatusCode() {
        AssetStatus status = new AssetStatus();
        status.setInventoryStatusCode("A");
        assertThat(status.getInventoryStatusCode()).isEqualTo("A");
    }

    @Test
    @DisplayName("inventoryStatusName getter/setter")
    void inventoryStatusName() {
        AssetStatus status = new AssetStatus();
        status.setInventoryStatusName("Active");
        assertThat(status.getInventoryStatusName()).isEqualTo("Active");
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        AssetStatus status = new AssetStatus();
        status.setActive(true);
        assertThat(status.isActive()).isTrue();
    }

    @Test
    @DisplayName("toStringMapper contains inventoryStatusCode")
    void toStringMapper() {
        AssetStatus status = new AssetStatus();
        status.setInventoryStatusCode("R");
        java.util.LinkedHashMap map = status.toStringMapper_RICE20_REFACTORME();
        assertThat(map).containsEntry("inventoryStatusCode", "R");
    }
}
