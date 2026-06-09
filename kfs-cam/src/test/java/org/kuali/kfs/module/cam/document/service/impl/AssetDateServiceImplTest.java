package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.businessobject.Asset;
import org.kuali.kfs.module.cam.businessobject.AssetLocation;
import org.kuali.kfs.module.cam.businessobject.AssetType;
import org.kuali.kfs.module.cam.document.service.AssetService;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.UniversityDate;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AssetDateServiceImplTest extends KfsUnitTestBase {

    @Mock
    private AssetService assetService;

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private AssetDateServiceImpl service;

    // --- checkAndUpdateFiscalYearAndPeriod ---

    @Test
    public void testCheckAndUpdateFiscalYearAndPeriod_dateChanged_findsUniversityDate() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        Date inServiceDate = Date.valueOf("2024-03-15");
        newAsset.setCapitalAssetInServiceDate(inServiceDate);

        when(assetService.isInServiceDateChanged(oldAsset, newAsset)).thenReturn(true);

        UniversityDate uDate = new UniversityDate();
        uDate.setUniversityFiscalYear(2024);
        uDate.setUniversityFiscalAccountingPeriod("09");

        when(businessObjectService.findByPrimaryKey(eq(UniversityDate.class), ArgumentMatchers.<java.util.Map<String, Object>>any())).thenReturn(uDate);

        service.checkAndUpdateFiscalYearAndPeriod(oldAsset, newAsset);

        assertEquals(Integer.valueOf(2024), newAsset.getFinancialDocumentPostingYear());
        assertEquals("09", newAsset.getFinancialDocumentPostingPeriodCode());
    }

    @Test
    public void testCheckAndUpdateFiscalYearAndPeriod_dateChanged_nullInServiceDate() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        newAsset.setCapitalAssetInServiceDate(null);

        when(assetService.isInServiceDateChanged(oldAsset, newAsset)).thenReturn(true);

        service.checkAndUpdateFiscalYearAndPeriod(oldAsset, newAsset);

        assertNull(newAsset.getFinancialDocumentPostingYear());
        assertNull(newAsset.getFinancialDocumentPostingPeriodCode());
    }

    @Test
    public void testCheckAndUpdateFiscalYearAndPeriod_dateNotChanged() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        newAsset.setFinancialDocumentPostingYear(2023);
        newAsset.setFinancialDocumentPostingPeriodCode("05");

        when(assetService.isInServiceDateChanged(oldAsset, newAsset)).thenReturn(false);

        service.checkAndUpdateFiscalYearAndPeriod(oldAsset, newAsset);

        assertEquals(Integer.valueOf(2023), newAsset.getFinancialDocumentPostingYear());
        assertEquals("05", newAsset.getFinancialDocumentPostingPeriodCode());
    }

    @Test
    public void testCheckAndUpdateFiscalYearAndPeriod_universityDateNotFound() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        Date inServiceDate = Date.valueOf("2099-01-01");
        newAsset.setCapitalAssetInServiceDate(inServiceDate);

        when(assetService.isInServiceDateChanged(oldAsset, newAsset)).thenReturn(true);
        when(businessObjectService.findByPrimaryKey(eq(UniversityDate.class), ArgumentMatchers.<java.util.Map<String, Object>>any())).thenReturn(null);

        service.checkAndUpdateFiscalYearAndPeriod(oldAsset, newAsset);

        assertNull(newAsset.getFinancialDocumentPostingYear());
        assertNull(newAsset.getFinancialDocumentPostingPeriodCode());
    }

    // --- checkAndUpdateLastInventoryDate ---

    @Test
    public void testCheckAndUpdateLastInventoryDate_locationChanged() {
        Asset oldAsset = new Asset();
        oldAsset.setCampusCode("BL");
        oldAsset.setOffCampusLocation(new AssetLocation());

        Asset newAsset = new Asset();
        newAsset.setCampusCode("IN");
        newAsset.setOffCampusLocation(new AssetLocation());

        java.util.Date currentDate = new java.util.Date();
        when(dateTimeService.getCurrentDate()).thenReturn(currentDate);

        service.checkAndUpdateLastInventoryDate(oldAsset, newAsset);

        assertNotNull(newAsset.getLastInventoryDate());
    }

    @Test
    public void testCheckAndUpdateLastInventoryDate_noChange() {
        AssetLocation offCampus = new AssetLocation();
        Asset oldAsset = new Asset();
        oldAsset.setCampusCode("BL");
        oldAsset.setBuildingCode("ADM");
        oldAsset.setBuildingRoomNumber("101");
        oldAsset.setBuildingSubRoomNumber(null);
        oldAsset.setCampusTagNumber("TAG1");
        oldAsset.setOffCampusLocation(offCampus);

        Asset newAsset = new Asset();
        newAsset.setCampusCode("BL");
        newAsset.setBuildingCode("ADM");
        newAsset.setBuildingRoomNumber("101");
        newAsset.setBuildingSubRoomNumber(null);
        newAsset.setCampusTagNumber("TAG1");
        newAsset.setOffCampusLocation(offCampus);

        service.checkAndUpdateLastInventoryDate(oldAsset, newAsset);

        assertNull(newAsset.getLastInventoryDate());
    }

    @Test
    public void testCheckAndUpdateLastInventoryDate_nullOffCampusLocation() {
        Asset oldAsset = new Asset();
        oldAsset.setOffCampusLocation(null);

        Asset newAsset = new Asset();
        newAsset.setOffCampusLocation(new AssetLocation());

        service.checkAndUpdateLastInventoryDate(oldAsset, newAsset);
        assertNull(newAsset.getLastInventoryDate());
    }

    // --- computeDepreciationDate ---

    @Test
    public void testComputeDepreciationDate_nullDepreciableLifeLimit() {
        AssetType assetType = new AssetType();
        assetType.setDepreciableLifeLimit(null);

        assertNull(service.computeDepreciationDate(assetType, null, Date.valueOf("2024-01-15")));
    }

    @Test
    public void testComputeDepreciationDate_zeroDepreciableLifeLimit() {
        AssetType assetType = new AssetType();
        assetType.setDepreciableLifeLimit(0);

        assertNull(service.computeDepreciationDate(assetType, null, Date.valueOf("2024-01-15")));
    }

    @Test
    public void testComputeDepreciationDate_createDateConvention() {
        AssetType assetType = new AssetType();
        assetType.setDepreciableLifeLimit(10);

        Date inServiceDate = Date.valueOf("2024-03-15");
        Date result = service.computeDepreciationDate(assetType, null, inServiceDate);

        assertEquals(inServiceDate, result);
    }

    @Test
    public void testComputeDepreciationDate_createDateConventionCode() {
        AssetType assetType = new AssetType();
        assetType.setDepreciableLifeLimit(10);

        org.kuali.kfs.module.cam.businessobject.AssetDepreciationConvention convention =
                new org.kuali.kfs.module.cam.businessobject.AssetDepreciationConvention();
        convention.setDepreciationConventionCode(CamsConstants.DepreciationConvention.CREATE_DATE);

        Date inServiceDate = Date.valueOf("2024-03-15");
        Date result = service.computeDepreciationDate(assetType, convention, inServiceDate);

        assertEquals(inServiceDate, result);
    }
}
