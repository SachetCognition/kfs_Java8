package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetOrganizationTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null")
    void defaultConstructor() {
        AssetOrganization org = new AssetOrganization();
        assertThat(org.getCapitalAssetNumber()).isNull();
        assertThat(org.getOrganizationAssetTypeIdentifier()).isNull();
        assertThat(org.getOrganizationTagNumber()).isNull();
        assertThat(org.getOrganizationText()).isNull();
    }

    @Test
    @DisplayName("capitalAssetNumber getter/setter")
    void capitalAssetNumber() {
        AssetOrganization org = new AssetOrganization();
        org.setCapitalAssetNumber(12345L);
        assertThat(org.getCapitalAssetNumber()).isEqualTo(12345L);
    }

    @Test
    @DisplayName("organizationAssetTypeIdentifier getter/setter")
    void organizationAssetTypeIdentifier() {
        AssetOrganization org = new AssetOrganization();
        org.setOrganizationAssetTypeIdentifier("LAPTOP");
        assertThat(org.getOrganizationAssetTypeIdentifier()).isEqualTo("LAPTOP");
    }

    @Test
    @DisplayName("organizationTagNumber getter/setter")
    void organizationTagNumber() {
        AssetOrganization org = new AssetOrganization();
        org.setOrganizationTagNumber("ORG-TAG-001");
        assertThat(org.getOrganizationTagNumber()).isEqualTo("ORG-TAG-001");
    }

    @Test
    @DisplayName("organizationText getter/setter")
    void organizationText() {
        AssetOrganization org = new AssetOrganization();
        org.setOrganizationText("IT Department Equipment");
        assertThat(org.getOrganizationText()).isEqualTo("IT Department Equipment");
    }
}
