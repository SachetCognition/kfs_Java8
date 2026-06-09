package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetRetirementReasonTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        AssetRetirementReason reason = new AssetRetirementReason();
        assertThat(reason.getRetirementReasonCode()).isNull();
        assertThat(reason.getRetirementReasonName()).isNull();
        assertThat(reason.isActive()).isFalse();
        assertThat(reason.isRetirementReasonRestrictionIndicator()).isFalse();
    }

    @Test
    @DisplayName("retirementReasonCode getter/setter")
    void retirementReasonCode() {
        AssetRetirementReason reason = new AssetRetirementReason();
        reason.setRetirementReasonCode("SOLD");
        assertThat(reason.getRetirementReasonCode()).isEqualTo("SOLD");
    }

    @Test
    @DisplayName("retirementReasonName getter/setter")
    void retirementReasonName() {
        AssetRetirementReason reason = new AssetRetirementReason();
        reason.setRetirementReasonName("Sold to external party");
        assertThat(reason.getRetirementReasonName()).isEqualTo("Sold to external party");
    }

    @Test
    @DisplayName("retirementReasonRestrictionIndicator getter/setter")
    void retirementReasonRestrictionIndicator() {
        AssetRetirementReason reason = new AssetRetirementReason();
        reason.setRetirementReasonRestrictionIndicator(true);
        assertThat(reason.isRetirementReasonRestrictionIndicator()).isTrue();
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        AssetRetirementReason reason = new AssetRetirementReason();
        reason.setActive(true);
        assertThat(reason.isActive()).isTrue();
    }

    @Test
    @DisplayName("toStringMapper contains retirementReasonCode")
    void toStringMapper() {
        AssetRetirementReason reason = new AssetRetirementReason();
        reason.setRetirementReasonCode("DEST");
        java.util.LinkedHashMap map = reason.toStringMapper_RICE20_REFACTORME();
        assertThat(map).containsEntry("retirementReasonCode", "DEST");
    }
}
