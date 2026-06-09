package org.kuali.kfs.module.cam.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.module.cam.businessobject.AssetLock;
import org.kuali.kfs.module.cam.dataaccess.CapitalAssetLockDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

public class AssetLockServiceImplTest extends KfsUnitTestBase {

    @Mock
    private CapitalAssetLockDao capitalAssetLockDao;

    @InjectMocks
    private AssetLockServiceImpl service;

    // --- buildAssetLockHelper ---

    @Test
    public void testBuildAssetLockHelper_createsLocksForEachAsset() {
        List<Long> assetNumbers = Arrays.asList(100L, 200L, 300L);
        List<AssetLock> locks = service.buildAssetLockHelper(assetNumbers, "DOC001", "ASSET_EDIT", "lockInfo");

        assertEquals(3, locks.size());
        assertEquals("DOC001", locks.get(0).getDocumentNumber());
        assertEquals(Long.valueOf(100L), locks.get(0).getCapitalAssetNumber());
        assertEquals("ASSET_EDIT", locks.get(0).getDocumentTypeName());
        assertEquals("lockInfo", locks.get(0).getLockingInformation());
    }

    @Test
    public void testBuildAssetLockHelper_skipsNullAssetNumbers() {
        List<Long> assetNumbers = Arrays.asList(100L, null, 300L);
        List<AssetLock> locks = service.buildAssetLockHelper(assetNumbers, "DOC001", "ASSET_EDIT", "lockInfo");

        assertEquals(2, locks.size());
    }

    @Test
    public void testBuildAssetLockHelper_emptyList() {
        List<AssetLock> locks = service.buildAssetLockHelper(new ArrayList<Long>(), "DOC001", "ASSET_EDIT", "lockInfo");

        assertTrue(locks.isEmpty());
    }

    // --- isAssetLocked ---

    @Test
    public void testIsAssetLocked_emptyAssetNumbers() {
        assertFalse(service.isAssetLocked(new ArrayList<Long>(), "ASSET_EDIT", "DOC001"));
    }

    @Test
    public void testIsAssetLocked_nullAssetNumbers() {
        assertFalse(service.isAssetLocked(null, "ASSET_EDIT", "DOC001"));
    }

    // --- isPurApDocument ---

    @Test
    public void testIsPurApDocument_preq() {
        assertTrue(service.isPurApDocument("PREQ"));
    }

    @Test
    public void testIsPurApDocument_cm() {
        assertTrue(service.isPurApDocument("CM"));
    }

    @Test
    public void testIsPurApDocument_otherType() {
        assertFalse(service.isPurApDocument("ASSET_EDIT"));
    }

    // --- getCapitalAssetLockDao ---

    @Test
    public void testGetCapitalAssetLockDao() {
        assertSame(capitalAssetLockDao, service.getCapitalAssetLockDao());
    }

    // --- checkAndSetAssetLocks ---

    @Test
    public void testCheckAndSetAssetLocks_nullInput() {
        assertTrue(service.checkAndSetAssetLocks(null, false));
    }

    @Test
    public void testCheckAndSetAssetLocks_emptyInput() {
        assertTrue(service.checkAndSetAssetLocks(new ArrayList<AssetLock>(), false));
    }

    // --- getAssetLockingDocuments ---

    @Test
    public void testGetAssetLockingDocuments_financialDocType() {
        List<Long> assetNumbers = Arrays.asList(100L);
        when(capitalAssetLockDao.getLockingDocumentNumbers(eq(assetNumbers), anyCollection(), eq("DOC001")))
                .thenReturn(new ArrayList<String>());

        List<String> result = service.getAssetLockingDocuments(assetNumbers, "CR", "DOC001");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
