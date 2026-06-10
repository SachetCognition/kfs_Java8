package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetConditionTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        AssetCondition condition = new AssetCondition();
        assertThat(condition.getAssetConditionCode()).isNull();
        assertThat(condition.getAssetConditionName()).isNull();
        assertThat(condition.isActive()).isFalse();
    }

    @Test
    @DisplayName("assetConditionCode getter/setter")
    void assetConditionCode() {
        AssetCondition condition = new AssetCondition();
        condition.setAssetConditionCode("G");
        assertThat(condition.getAssetConditionCode()).isEqualTo("G");
    }

    @Test
    @DisplayName("assetConditionName getter/setter")
    void assetConditionName() {
        AssetCondition condition = new AssetCondition();
        condition.setAssetConditionName("Good");
        assertThat(condition.getAssetConditionName()).isEqualTo("Good");
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        AssetCondition condition = new AssetCondition();
        condition.setActive(true);
        assertThat(condition.isActive()).isTrue();
    }

    @Test
    @DisplayName("toStringMapper contains assetConditionCode")
    void toStringMapper() {
        AssetCondition condition = new AssetCondition();
        condition.setAssetConditionCode("E");
        java.util.LinkedHashMap map = condition.toStringMapper_RICE20_REFACTORME();
        assertThat(map).containsEntry("assetConditionCode", "E");
    }
}
