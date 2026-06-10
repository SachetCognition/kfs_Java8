package org.kuali.kfs.module.cam.batch;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BarcodeInventoryTest extends KfsUnitTestBase {

    private BarcodeInventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new BarcodeInventory();
    }

    @Test
    @DisplayName("default constructor creates empty object")
    void defaultConstructor() {
        assertThat(inventory.getCampusTagNumber()).isNull();
        assertThat(inventory.getInventoryScannedCode()).isNull();
        assertThat(inventory.getCreateDate()).isNull();
        assertThat(inventory.getCampusCode()).isNull();
        assertThat(inventory.getBuildingCode()).isNull();
        assertThat(inventory.getBuildingRoomNumber()).isNull();
        assertThat(inventory.getBuildingSubRoomNumber()).isNull();
        assertThat(inventory.getConditionCode()).isNull();
        assertThat(inventory.getInventoryStatusCode()).isNull();
    }

    @Test
    @DisplayName("campusTagNumber getter/setter")
    void campusTagNumber() {
        inventory.setCampusTagNumber("TAG001");
        assertThat(inventory.getCampusTagNumber()).isEqualTo("TAG001");
    }

    @Test
    @DisplayName("inventoryScannedCode getter/setter")
    void inventoryScannedCode() {
        inventory.setInventoryScannedCode("Y");
        assertThat(inventory.getInventoryScannedCode()).isEqualTo("Y");
    }

    @Test
    @DisplayName("createDate getter/setter")
    void createDate() {
        Date date = Date.valueOf("2024-06-15");
        inventory.setCreateDate(date);
        assertThat(inventory.getCreateDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("campusCode getter/setter")
    void campusCode() {
        inventory.setCampusCode("BL");
        assertThat(inventory.getCampusCode()).isEqualTo("BL");
    }

    @Test
    @DisplayName("buildingCode getter/setter")
    void buildingCode() {
        inventory.setBuildingCode("BLDG1");
        assertThat(inventory.getBuildingCode()).isEqualTo("BLDG1");
    }

    @Test
    @DisplayName("buildingRoomNumber getter/setter")
    void buildingRoomNumber() {
        inventory.setBuildingRoomNumber("101A");
        assertThat(inventory.getBuildingRoomNumber()).isEqualTo("101A");
    }

    @Test
    @DisplayName("buildingSubRoomNumber getter/setter")
    void buildingSubRoomNumber() {
        inventory.setBuildingSubRoomNumber("A1");
        assertThat(inventory.getBuildingSubRoomNumber()).isEqualTo("A1");
    }

    @Test
    @DisplayName("conditionCode getter/setter")
    void conditionCode() {
        inventory.setConditionCode("G");
        assertThat(inventory.getConditionCode()).isEqualTo("G");
    }

    @Test
    @DisplayName("inventoryStatusCode getter/setter")
    void inventoryStatusCode() {
        inventory.setInventoryStatusCode("A");
        assertThat(inventory.getInventoryStatusCode()).isEqualTo("A");
    }

    @Test
    @DisplayName("all properties set and retrieved correctly")
    void allPropertiesSetAndRetrieved() {
        Date date = Date.valueOf("2024-01-01");
        inventory.setCampusTagNumber("TAG");
        inventory.setInventoryScannedCode("N");
        inventory.setCreateDate(date);
        inventory.setCampusCode("IN");
        inventory.setBuildingCode("BLD");
        inventory.setBuildingRoomNumber("200");
        inventory.setBuildingSubRoomNumber("B");
        inventory.setConditionCode("E");
        inventory.setInventoryStatusCode("I");

        assertThat(inventory.getCampusTagNumber()).isEqualTo("TAG");
        assertThat(inventory.getInventoryScannedCode()).isEqualTo("N");
        assertThat(inventory.getCreateDate()).isEqualTo(date);
        assertThat(inventory.getCampusCode()).isEqualTo("IN");
        assertThat(inventory.getBuildingCode()).isEqualTo("BLD");
        assertThat(inventory.getBuildingRoomNumber()).isEqualTo("200");
        assertThat(inventory.getBuildingSubRoomNumber()).isEqualTo("B");
        assertThat(inventory.getConditionCode()).isEqualTo("E");
        assertThat(inventory.getInventoryStatusCode()).isEqualTo("I");
    }
}
