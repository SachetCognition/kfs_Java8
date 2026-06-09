package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetRetirementGlobalDetailTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null")
    void defaultConstructor() {
        AssetRetirementGlobalDetail detail = new AssetRetirementGlobalDetail();
        assertThat(detail.getDocumentNumber()).isNull();
        assertThat(detail.getCapitalAssetNumber()).isNull();
        assertThat(detail.getAsset()).isNull();
    }

    @Test
    @DisplayName("documentNumber getter/setter")
    void documentNumber() {
        AssetRetirementGlobalDetail detail = new AssetRetirementGlobalDetail();
        detail.setDocumentNumber("DOC001");
        assertThat(detail.getDocumentNumber()).isEqualTo("DOC001");
    }

    @Test
    @DisplayName("capitalAssetNumber getter/setter")
    void capitalAssetNumber() {
        AssetRetirementGlobalDetail detail = new AssetRetirementGlobalDetail();
        detail.setCapitalAssetNumber(12345L);
        assertThat(detail.getCapitalAssetNumber()).isEqualTo(12345L);
    }

    @Test
    @DisplayName("asset getter/setter")
    void asset() {
        AssetRetirementGlobalDetail detail = new AssetRetirementGlobalDetail();
        Asset asset = new Asset();
        asset.setCapitalAssetNumber(999L);
        detail.setAsset(asset);
        assertThat(detail.getAsset()).isNotNull();
        assertThat(detail.getAsset().getCapitalAssetNumber()).isEqualTo(999L);
    }

    @Test
    @DisplayName("assetRetirementGlobal getter/setter")
    void assetRetirementGlobal() {
        AssetRetirementGlobalDetail detail = new AssetRetirementGlobalDetail();
        AssetRetirementGlobal global = new AssetRetirementGlobal();
        global.setDocumentNumber("DOC002");
        detail.setAssetRetirementGlobal(global);
        assertThat(detail.getAssetRetirementGlobal()).isNotNull();
        assertThat(detail.getAssetRetirementGlobal().getDocumentNumber()).isEqualTo("DOC002");
    }
}
