package org.kuali.kfs.module.cab.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetTransactionTypeTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        AssetTransactionType type = new AssetTransactionType();
        assertThat(type.getCapitalAssetTransactionTypeCode()).isNull();
        assertThat(type.getCapitalAssetTransactionTypeDescription()).isNull();
        assertThat(type.getCapitalAssetNonquantityDrivenAllowIndicator()).isFalse();
        assertThat(type.getCapitalAssetQuantitySubtypeRequiredText()).isNull();
        assertThat(type.getCapitalAssetNonquantitySubtypeRequiredText()).isNull();
        assertThat(type.isActive()).isFalse();
    }

    @Test
    @DisplayName("parameterized constructor: sets code")
    void parameterizedConstructor() {
        AssetTransactionType type = new AssetTransactionType("NEW");
        assertThat(type.getCapitalAssetTransactionTypeCode()).isEqualTo("NEW");
    }

    @Test
    @DisplayName("capitalAssetTransactionTypeCode getter/setter")
    void code() {
        AssetTransactionType type = new AssetTransactionType();
        type.setCapitalAssetTransactionTypeCode("RENEW");
        assertThat(type.getCapitalAssetTransactionTypeCode()).isEqualTo("RENEW");
    }

    @Test
    @DisplayName("capitalAssetTransactionTypeDescription getter/setter")
    void description() {
        AssetTransactionType type = new AssetTransactionType();
        type.setCapitalAssetTransactionTypeDescription("Renewal Transaction");
        assertThat(type.getCapitalAssetTransactionTypeDescription()).isEqualTo("Renewal Transaction");
    }

    @Test
    @DisplayName("capitalAssetNonquantityDrivenAllowIndicator getter/setter")
    void nonquantityDrivenAllowIndicator() {
        AssetTransactionType type = new AssetTransactionType();
        type.setCapitalAssetNonquantityDrivenAllowIndicator(true);
        assertThat(type.getCapitalAssetNonquantityDrivenAllowIndicator()).isTrue();
    }

    @Test
    @DisplayName("capitalAssetQuantitySubtypeRequiredText getter/setter")
    void quantitySubtypeRequiredText() {
        AssetTransactionType type = new AssetTransactionType();
        type.setCapitalAssetQuantitySubtypeRequiredText("Required for quantity");
        assertThat(type.getCapitalAssetQuantitySubtypeRequiredText()).isEqualTo("Required for quantity");
    }

    @Test
    @DisplayName("capitalAssetNonquantitySubtypeRequiredText getter/setter")
    void nonquantitySubtypeRequiredText() {
        AssetTransactionType type = new AssetTransactionType();
        type.setCapitalAssetNonquantitySubtypeRequiredText("Required for non-quantity");
        assertThat(type.getCapitalAssetNonquantitySubtypeRequiredText()).isEqualTo("Required for non-quantity");
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        AssetTransactionType type = new AssetTransactionType();
        type.setActive(true);
        assertThat(type.isActive()).isTrue();
    }

    @Test
    @DisplayName("toStringMapper contains capitalAssetTransactionTypeCode")
    void toStringMapper() {
        AssetTransactionType type = new AssetTransactionType("TEST");
        java.util.LinkedHashMap map = type.toStringMapper_RICE20_REFACTORME();
        assertThat(map).containsEntry("capitalAssetTransactionTypeCode", "TEST");
    }
}
