package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetDepreciationMethodTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        AssetDepreciationMethod method = new AssetDepreciationMethod();
        assertThat(method.getDepreciationMethodCode()).isNull();
        assertThat(method.getDepreciationMethodName()).isNull();
        assertThat(method.isActive()).isFalse();
    }

    @Test
    @DisplayName("depreciationMethodCode getter/setter")
    void depreciationMethodCode() {
        AssetDepreciationMethod method = new AssetDepreciationMethod();
        method.setDepreciationMethodCode("SL");
        assertThat(method.getDepreciationMethodCode()).isEqualTo("SL");
    }

    @Test
    @DisplayName("depreciationMethodName getter/setter")
    void depreciationMethodName() {
        AssetDepreciationMethod method = new AssetDepreciationMethod();
        method.setDepreciationMethodName("Straight Line");
        assertThat(method.getDepreciationMethodName()).isEqualTo("Straight Line");
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        AssetDepreciationMethod method = new AssetDepreciationMethod();
        method.setActive(true);
        assertThat(method.isActive()).isTrue();
    }

    @Test
    @DisplayName("toStringMapper contains depreciationMethodCode")
    void toStringMapper() {
        AssetDepreciationMethod method = new AssetDepreciationMethod();
        method.setDepreciationMethodCode("SL");
        java.util.LinkedHashMap map = method.toStringMapper_RICE20_REFACTORME();
        assertThat(map).containsEntry("depreciationMethodCode", "SL");
    }
}
