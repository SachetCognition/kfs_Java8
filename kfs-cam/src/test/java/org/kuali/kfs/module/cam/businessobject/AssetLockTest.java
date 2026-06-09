package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetLockTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null")
    void defaultConstructor() {
        AssetLock lock = new AssetLock();
        assertThat(lock.getDocumentNumber()).isNull();
        assertThat(lock.getCapitalAssetNumber()).isNull();
        assertThat(lock.getLockingInformation()).isNull();
        assertThat(lock.getDocumentTypeName()).isNull();
    }

    @Test
    @DisplayName("parameterized constructor sets all fields")
    void parameterizedConstructor() {
        AssetLock lock = new AssetLock("DOC001", 12345L, "LOCK_INFO", "AT");
        assertThat(lock.getDocumentNumber()).isEqualTo("DOC001");
        assertThat(lock.getCapitalAssetNumber()).isEqualTo(12345L);
        assertThat(lock.getLockingInformation()).isEqualTo("LOCK_INFO");
        assertThat(lock.getDocumentTypeName()).isEqualTo("AT");
    }

    @Test
    @DisplayName("setters correctly update fields")
    void setters() {
        AssetLock lock = new AssetLock();
        lock.setDocumentNumber("DOC002");
        lock.setCapitalAssetNumber(99999L);
        lock.setLockingInformation("NEW_LOCK");
        lock.setDocumentTypeName("MPAY");

        assertThat(lock.getDocumentNumber()).isEqualTo("DOC002");
        assertThat(lock.getCapitalAssetNumber()).isEqualTo(99999L);
        assertThat(lock.getLockingInformation()).isEqualTo("NEW_LOCK");
        assertThat(lock.getDocumentTypeName()).isEqualTo("MPAY");
    }

    @Test
    @DisplayName("toStringMapper contains expected keys")
    void toStringMapper() {
        AssetLock lock = new AssetLock("DOC", 100L, "INFO", "AT");
        java.util.LinkedHashMap map = lock.toStringMapper_RICE20_REFACTORME();
        assertThat(map).containsKeys("documentNumber", "capitalAssetNumber", "lockingInformation");
        assertThat(map.get("documentNumber")).isEqualTo("DOC");
        assertThat(map.get("capitalAssetNumber")).isEqualTo(100L);
    }
}
