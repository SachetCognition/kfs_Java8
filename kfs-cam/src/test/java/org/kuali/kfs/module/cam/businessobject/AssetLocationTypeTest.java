package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetLocationTypeTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        AssetLocationType type = new AssetLocationType();
        assertThat(type.getAssetLocationTypeCode()).isNull();
        assertThat(type.getAssetLocationTypeName()).isNull();
        assertThat(type.isActive()).isFalse();
    }

    @Test
    @DisplayName("assetLocationTypeCode getter/setter")
    void assetLocationTypeCode() {
        AssetLocationType type = new AssetLocationType();
        type.setAssetLocationTypeCode("B");
        assertThat(type.getAssetLocationTypeCode()).isEqualTo("B");
    }

    @Test
    @DisplayName("assetLocationTypeName getter/setter")
    void assetLocationTypeName() {
        AssetLocationType type = new AssetLocationType();
        type.setAssetLocationTypeName("Building");
        assertThat(type.getAssetLocationTypeName()).isEqualTo("Building");
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        AssetLocationType type = new AssetLocationType();
        type.setActive(true);
        assertThat(type.isActive()).isTrue();
    }

    @Test
    @DisplayName("toStringMapper contains assetLocationTypeCode")
    void toStringMapper() {
        AssetLocationType type = new AssetLocationType();
        type.setAssetLocationTypeCode("O");
        java.util.LinkedHashMap map = type.toStringMapper_RICE20_REFACTORME();
        assertThat(map).containsEntry("assetLocationTypeCode", "O");
    }
}
