package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetTypeTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        AssetType type = new AssetType();
        assertThat(type.getCapitalAssetTypeCode()).isNull();
        assertThat(type.getCapitalAssetTypeDescription()).isNull();
        assertThat(type.getDepreciableLifeLimit()).isNull();
        assertThat(type.isMovingIndicator()).isFalse();
        assertThat(type.isRequiredBuildingIndicator()).isFalse();
        assertThat(type.isActive()).isFalse();
    }

    @Test
    @DisplayName("capitalAssetTypeCode getter/setter")
    void capitalAssetTypeCode() {
        AssetType type = new AssetType();
        type.setCapitalAssetTypeCode("EQ");
        assertThat(type.getCapitalAssetTypeCode()).isEqualTo("EQ");
    }

    @Test
    @DisplayName("capitalAssetTypeDescription getter/setter")
    void capitalAssetTypeDescription() {
        AssetType type = new AssetType();
        type.setCapitalAssetTypeDescription("Equipment");
        assertThat(type.getCapitalAssetTypeDescription()).isEqualTo("Equipment");
    }

    @Test
    @DisplayName("depreciableLifeLimit getter/setter")
    void depreciableLifeLimit() {
        AssetType type = new AssetType();
        type.setDepreciableLifeLimit(10);
        assertThat(type.getDepreciableLifeLimit()).isEqualTo(10);
    }

    @Test
    @DisplayName("movingIndicator getter/setter")
    void movingIndicator() {
        AssetType type = new AssetType();
        type.setMovingIndicator(true);
        assertThat(type.isMovingIndicator()).isTrue();
    }

    @Test
    @DisplayName("requiredBuildingIndicator getter/setter")
    void requiredBuildingIndicator() {
        AssetType type = new AssetType();
        type.setRequiredBuildingIndicator(true);
        assertThat(type.isRequiredBuildingIndicator()).isTrue();
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        AssetType type = new AssetType();
        type.setActive(true);
        assertThat(type.isActive()).isTrue();
    }
}
