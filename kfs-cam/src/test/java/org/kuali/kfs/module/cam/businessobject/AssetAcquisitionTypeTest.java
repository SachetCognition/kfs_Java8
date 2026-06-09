package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetAcquisitionTypeTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        AssetAcquisitionType type = new AssetAcquisitionType();
        assertThat(type.getAcquisitionTypeCode()).isNull();
        assertThat(type.getAcquisitionTypeName()).isNull();
        assertThat(type.getIncomeAssetObjectCode()).isNull();
        assertThat(type.isActive()).isFalse();
    }

    @Test
    @DisplayName("acquisitionTypeCode getter/setter")
    void acquisitionTypeCode() {
        AssetAcquisitionType type = new AssetAcquisitionType();
        type.setAcquisitionTypeCode("P");
        assertThat(type.getAcquisitionTypeCode()).isEqualTo("P");
    }

    @Test
    @DisplayName("acquisitionTypeName getter/setter")
    void acquisitionTypeName() {
        AssetAcquisitionType type = new AssetAcquisitionType();
        type.setAcquisitionTypeName("Purchase");
        assertThat(type.getAcquisitionTypeName()).isEqualTo("Purchase");
    }

    @Test
    @DisplayName("incomeAssetObjectCode getter/setter")
    void incomeAssetObjectCode() {
        AssetAcquisitionType type = new AssetAcquisitionType();
        type.setIncomeAssetObjectCode("7400");
        assertThat(type.getIncomeAssetObjectCode()).isEqualTo("7400");
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        AssetAcquisitionType type = new AssetAcquisitionType();
        type.setActive(true);
        assertThat(type.isActive()).isTrue();
    }

    @Test
    @DisplayName("toStringMapper contains acquisitionTypeCode")
    void toStringMapper() {
        AssetAcquisitionType type = new AssetAcquisitionType();
        type.setAcquisitionTypeCode("D");
        java.util.LinkedHashMap map = type.toStringMapper_RICE20_REFACTORME();
        assertThat(map).containsEntry("acquisitionTypeCode", "D");
    }
}
