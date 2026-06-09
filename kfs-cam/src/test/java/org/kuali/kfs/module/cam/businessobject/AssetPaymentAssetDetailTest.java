package org.kuali.kfs.module.cam.businessobject;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AssetPaymentAssetDetailTest extends KfsUnitTestBase {

    private AssetPaymentAssetDetail detail;

    @BeforeEach
    void setUp() {
        detail = new AssetPaymentAssetDetail();
    }

    @Test
    @DisplayName("default constructor: initializes lists and defaults")
    void defaultConstructor() {
        assertThat(detail.getDocumentNumber()).isNull();
        assertThat(detail.getCapitalAssetNumber()).isNull();
        assertThat(detail.getPreviousTotalCostAmount()).isNull();
        assertThat(detail.getAllocatedAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(detail.getAllocatedUserValue()).isEqualTo(KualiDecimal.ZERO);
        assertThat(detail.getAllocatedUserValuePct()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("documentNumber getter/setter")
    void documentNumber() {
        detail.setDocumentNumber("MPAY-001");
        assertThat(detail.getDocumentNumber()).isEqualTo("MPAY-001");
    }

    @Test
    @DisplayName("capitalAssetNumber getter/setter")
    void capitalAssetNumber() {
        detail.setCapitalAssetNumber(99999L);
        assertThat(detail.getCapitalAssetNumber()).isEqualTo(99999L);
    }

    @Test
    @DisplayName("previousTotalCostAmount getter/setter")
    void previousTotalCostAmount() {
        KualiDecimal amount = new KualiDecimal(15000);
        detail.setPreviousTotalCostAmount(amount);
        assertThat(detail.getPreviousTotalCostAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("allocatedAmount getter/setter")
    void allocatedAmount() {
        KualiDecimal amount = new KualiDecimal(5000);
        detail.setAllocatedAmount(amount);
        assertThat(detail.getAllocatedAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("allocatedUserValue getter/setter")
    void allocatedUserValue() {
        KualiDecimal value = new KualiDecimal(3000);
        detail.setAllocatedUserValue(value);
        assertThat(detail.getAllocatedUserValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("allocatedUserValuePct getter/setter")
    void allocatedUserValuePct() {
        BigDecimal pct = new BigDecimal("33.33");
        detail.setAllocatedUserValuePct(pct);
        assertThat(detail.getAllocatedUserValuePct()).isEqualTo(pct);
    }

    @Test
    @DisplayName("asset getter/setter")
    void asset() {
        Asset asset = new Asset();
        asset.setCapitalAssetNumber(1L);
        detail.setAsset(asset);
        assertThat(detail.getAsset()).isNotNull();
        assertThat(detail.getAsset().getCapitalAssetNumber()).isEqualTo(1L);
    }
}
