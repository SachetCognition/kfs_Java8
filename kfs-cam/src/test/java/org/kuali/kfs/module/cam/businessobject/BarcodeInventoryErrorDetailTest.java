package org.kuali.kfs.module.cam.businessobject;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BarcodeInventoryErrorDetailTest extends KfsUnitTestBase {

    private BarcodeInventoryErrorDetail detail;

    @BeforeEach
    void setUp() {
        detail = new BarcodeInventoryErrorDetail();
    }

    @Test
    @DisplayName("default constructor: all fields null/default")
    void defaultConstructor() {
        assertThat(detail.getDocumentNumber()).isNull();
        assertThat(detail.getUploadRowNumber()).isNull();
        assertThat(detail.getErrorCorrectionStatusCode()).isNull();
        assertThat(detail.getAssetTagNumber()).isNull();
        assertThat(detail.isUploadScanIndicator()).isFalse();
        assertThat(detail.getCampusCode()).isNull();
        assertThat(detail.getBuildingCode()).isNull();
        assertThat(detail.getBuildingRoomNumber()).isNull();
        assertThat(detail.getBuildingSubRoomNumber()).isNull();
        assertThat(detail.getAssetConditionCode()).isNull();
    }

    @Test
    @DisplayName("documentNumber getter/setter")
    void documentNumber() {
        detail.setDocumentNumber("DOC001");
        assertThat(detail.getDocumentNumber()).isEqualTo("DOC001");
    }

    @Test
    @DisplayName("uploadRowNumber getter/setter")
    void uploadRowNumber() {
        detail.setUploadRowNumber(42L);
        assertThat(detail.getUploadRowNumber()).isEqualTo(42L);
    }

    @Test
    @DisplayName("errorCorrectionStatusCode getter/setter")
    void errorCorrectionStatusCode() {
        detail.setErrorCorrectionStatusCode("E");
        assertThat(detail.getErrorCorrectionStatusCode()).isEqualTo("E");
    }

    @Test
    @DisplayName("correctorUniversalIdentifier getter/setter")
    void correctorUniversalIdentifier() {
        detail.setCorrectorUniversalIdentifier("admin");
        assertThat(detail.getCorrectorUniversalIdentifier()).isEqualTo("admin");
    }

    @Test
    @DisplayName("inventoryCorrectionTimestamp getter/setter")
    void inventoryCorrectionTimestamp() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        detail.setInventoryCorrectionTimestamp(ts);
        assertThat(detail.getInventoryCorrectionTimestamp()).isEqualTo(ts);
    }

    @Test
    @DisplayName("assetTagNumber getter/setter")
    void assetTagNumber() {
        detail.setAssetTagNumber("TAG999");
        assertThat(detail.getAssetTagNumber()).isEqualTo("TAG999");
    }

    @Test
    @DisplayName("uploadScanIndicator getter/setter")
    void uploadScanIndicator() {
        detail.setUploadScanIndicator(true);
        assertThat(detail.isUploadScanIndicator()).isTrue();
    }

    @Test
    @DisplayName("uploadScanTimestamp getter/setter")
    void uploadScanTimestamp() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        detail.setUploadScanTimestamp(ts);
        assertThat(detail.getUploadScanTimestamp()).isEqualTo(ts);
    }

    @Test
    @DisplayName("campusCode getter/setter")
    void campusCode() {
        detail.setCampusCode("BL");
        assertThat(detail.getCampusCode()).isEqualTo("BL");
    }

    @Test
    @DisplayName("buildingCode getter/setter")
    void buildingCode() {
        detail.setBuildingCode("BLDG1");
        assertThat(detail.getBuildingCode()).isEqualTo("BLDG1");
    }

    @Test
    @DisplayName("buildingRoomNumber getter/setter")
    void buildingRoomNumber() {
        detail.setBuildingRoomNumber("101");
        assertThat(detail.getBuildingRoomNumber()).isEqualTo("101");
    }

    @Test
    @DisplayName("buildingSubRoomNumber getter/setter")
    void buildingSubRoomNumber() {
        detail.setBuildingSubRoomNumber("A");
        assertThat(detail.getBuildingSubRoomNumber()).isEqualTo("A");
    }

    @Test
    @DisplayName("assetConditionCode getter/setter")
    void assetConditionCode() {
        detail.setAssetConditionCode("G");
        assertThat(detail.getAssetConditionCode()).isEqualTo("G");
    }

    @Test
    @DisplayName("errorDescription getter/setter")
    void errorDescription() {
        detail.setErrorDescription("Invalid campus code");
        assertThat(detail.getErrorDescription()).isEqualTo("Invalid campus code");
    }

    @Test
    @DisplayName("all basic fields can be set and retrieved together")
    void allFieldsTogether() {
        detail.setDocumentNumber("DOC999");
        detail.setUploadRowNumber(100L);
        detail.setErrorCorrectionStatusCode("C");
        detail.setAssetTagNumber("TAG123");
        detail.setCampusCode("BL");
        detail.setBuildingCode("BLDG");
        detail.setBuildingRoomNumber("101");
        detail.setBuildingSubRoomNumber("A");
        detail.setAssetConditionCode("G");

        assertThat(detail.getDocumentNumber()).isEqualTo("DOC999");
        assertThat(detail.getUploadRowNumber()).isEqualTo(100L);
        assertThat(detail.getErrorCorrectionStatusCode()).isEqualTo("C");
        assertThat(detail.getAssetTagNumber()).isEqualTo("TAG123");
        assertThat(detail.getCampusCode()).isEqualTo("BL");
        assertThat(detail.getBuildingCode()).isEqualTo("BLDG");
        assertThat(detail.getBuildingRoomNumber()).isEqualTo("101");
        assertThat(detail.getBuildingSubRoomNumber()).isEqualTo("A");
        assertThat(detail.getAssetConditionCode()).isEqualTo("G");
    }
}
