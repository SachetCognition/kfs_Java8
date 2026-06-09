package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetDepreciationConventionTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        AssetDepreciationConvention conv = new AssetDepreciationConvention();
        assertThat(conv.getFinancialObjectSubTypeCode()).isNull();
        assertThat(conv.getDepreciationConventionCode()).isNull();
        assertThat(conv.isActive()).isFalse();
        assertThat(conv.getFinancialObjectSubType()).isNull();
    }

    @Test
    @DisplayName("financialObjectSubTypeCode getter/setter")
    void financialObjectSubTypeCode() {
        AssetDepreciationConvention conv = new AssetDepreciationConvention();
        conv.setFinancialObjectSubTypeCode("CM");
        assertThat(conv.getFinancialObjectSubTypeCode()).isEqualTo("CM");
    }

    @Test
    @DisplayName("depreciationConventionCode getter/setter")
    void depreciationConventionCode() {
        AssetDepreciationConvention conv = new AssetDepreciationConvention();
        conv.setDepreciationConventionCode("HY");
        assertThat(conv.getDepreciationConventionCode()).isEqualTo("HY");
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        AssetDepreciationConvention conv = new AssetDepreciationConvention();
        conv.setActive(true);
        assertThat(conv.isActive()).isTrue();
    }

    @Test
    @DisplayName("toStringMapper contains financialObjectSubTypeCode")
    void toStringMapper() {
        AssetDepreciationConvention conv = new AssetDepreciationConvention();
        conv.setFinancialObjectSubTypeCode("CM");
        java.util.LinkedHashMap map = conv.toStringMapper_RICE20_REFACTORME();
        assertThat(map).containsEntry("financialObjectSubTypeCode", "CM");
    }
}
