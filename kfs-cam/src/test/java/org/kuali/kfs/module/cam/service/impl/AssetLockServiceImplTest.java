package org.kuali.kfs.module.cam.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.module.cab.CabConstants;
import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.businessobject.AssetLock;
import org.kuali.kfs.module.cam.dataaccess.CapitalAssetLockDao;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AssetLockServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private AssetLockServiceImpl service;

    @Mock
    private CapitalAssetLockDao capitalAssetLockDao;

    @Test
    @DisplayName("buildAssetLockHelper: creates locks for all non-null asset numbers")
    void buildAssetLockHelper_createsLocksForAllAssets() {
        List<Long> assetNumbers = Arrays.asList(1001L, 1002L, 1003L);
        List<AssetLock> result = service.buildAssetLockHelper(assetNumbers, "DOC001", "AT", "LOCK_INFO");

        assertThat(result).hasSize(3);
        assertThat(result.get(0).getDocumentNumber()).isEqualTo("DOC001");
        assertThat(result.get(0).getCapitalAssetNumber()).isEqualTo(1001L);
        assertThat(result.get(0).getDocumentTypeName()).isEqualTo("AT");
        assertThat(result.get(0).getLockingInformation()).isEqualTo("LOCK_INFO");
    }

    @Test
    @DisplayName("buildAssetLockHelper: skips null asset numbers")
    void buildAssetLockHelper_skipsNullAssets() {
        List<Long> assetNumbers = Arrays.asList(1001L, null, 1003L);
        List<AssetLock> result = service.buildAssetLockHelper(assetNumbers, "DOC001", "AT", "");
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("buildAssetLockHelper: empty list returns empty locks")
    void buildAssetLockHelper_emptyList() {
        List<AssetLock> result = service.buildAssetLockHelper(Collections.emptyList(), "DOC001", "AT", "");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("isAssetLocked: null asset numbers returns false")
    void isAssetLocked_nullAssetNumbers() {
        assertThat(service.isAssetLocked(null, "AT", "DOC001")).isFalse();
    }

    @Test
    @DisplayName("isAssetLocked: empty asset numbers returns false")
    void isAssetLocked_emptyAssetNumbers() {
        assertThat(service.isAssetLocked(Collections.emptyList(), "AT", "DOC001")).isFalse();
    }

    @Test
    @DisplayName("isAssetLocked: PurAp document type (PREQ) always returns false")
    void isAssetLocked_purApDocument_returnsFalse() {
        List<Long> assetNumbers = Arrays.asList(100L);
        assertThat(service.isAssetLocked(assetNumbers, CabConstants.PREQ, "DOC001")).isFalse();
    }

    @Test
    @DisplayName("isAssetLocked: PurAp document type (CM) always returns false")
    void isAssetLocked_cmDocument_returnsFalse() {
        List<Long> assetNumbers = Arrays.asList(100L);
        assertThat(service.isAssetLocked(assetNumbers, CabConstants.CM, "DOC001")).isFalse();
    }

    @Test
    @DisplayName("isAssetLocked: no locking documents returns false")
    void isAssetLocked_noLockingDocs_returnsFalse() {
        List<Long> assetNumbers = Arrays.asList(100L);
        when(capitalAssetLockDao.getLockingDocumentNumbers(anyList(), any(), anyString()))
                .thenReturn(Collections.emptyList());

        assertThat(service.isAssetLocked(assetNumbers, "AT", "DOC001")).isFalse();
    }

    @Test
    @DisplayName("getCapitalAssetLockDao: getter/setter works")
    void capitalAssetLockDaoGetterSetter() {
        AssetLockServiceImpl newService = new AssetLockServiceImpl();
        assertThat(newService.getCapitalAssetLockDao()).isNull();
        newService.setCapitalAssetLockDao(capitalAssetLockDao);
        assertThat(newService.getCapitalAssetLockDao()).isEqualTo(capitalAssetLockDao);
    }

    @Test
    @DisplayName("getAssetLockingDocuments: delegates to DAO with correct blocking types")
    void getAssetLockingDocuments_delegatesToDao() {
        List<Long> assetNumbers = Arrays.asList(100L, 200L);
        List<String> expected = Arrays.asList("LOCK-DOC-1");
        when(capitalAssetLockDao.getLockingDocumentNumbers(anyList(), any(), anyString()))
                .thenReturn(expected);

        List<String> result = service.getAssetLockingDocuments(assetNumbers, "AT", "DOC001");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("buildAssetLockHelper: all null asset numbers returns empty list")
    void buildAssetLockHelper_allNullAssets() {
        List<Long> assetNumbers = Arrays.asList((Long) null, null, null);
        List<AssetLock> result = service.buildAssetLockHelper(assetNumbers, "DOC001", "AT", "INFO");
        assertThat(result).isEmpty();
    }
}
