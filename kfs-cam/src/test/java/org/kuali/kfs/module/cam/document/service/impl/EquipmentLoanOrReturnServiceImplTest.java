package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.businessobject.Asset;
import org.kuali.kfs.module.cam.businessobject.AssetLocation;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

public class EquipmentLoanOrReturnServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private EquipmentLoanOrReturnServiceImpl service;

    // --- setEquipmentLoanInfo ---

    @Test
    public void testSetEquipmentLoanInfo_activeLoan_setsBorrowerAndStorage() {
        Asset asset = mock(Asset.class);
        when(asset.getExpectedReturnDate()).thenReturn(new Date(System.currentTimeMillis()));
        when(asset.getLoanReturnDate()).thenReturn(null);

        AssetLocation borrowerLoc = new AssetLocation();
        borrowerLoc.setAssetLocationTypeCode(CamsConstants.AssetLocationTypeCode.BORROWER);
        borrowerLoc.setAssetLocationContactName("Borrower");

        AssetLocation storageLoc = new AssetLocation();
        storageLoc.setAssetLocationTypeCode(CamsConstants.AssetLocationTypeCode.BORROWER_STORAGE);
        storageLoc.setAssetLocationCityName("StorageCity");

        List<AssetLocation> locations = new ArrayList<AssetLocation>();
        locations.add(borrowerLoc);
        locations.add(storageLoc);
        when(asset.getAssetLocations()).thenReturn(locations);

        service.setEquipmentLoanInfo(asset);

        verify(asset).setBorrowerLocation(borrowerLoc);
        verify(asset).setBorrowerStorageLocation(storageLoc);
    }

    @Test
    public void testSetEquipmentLoanInfo_loanReturned_doesNotSet() {
        Asset asset = new Asset();
        asset.setExpectedReturnDate(new Date(System.currentTimeMillis()));
        asset.setLoanReturnDate(new Date(System.currentTimeMillis()));
        asset.setAssetLocations(new ArrayList<AssetLocation>());

        service.setEquipmentLoanInfo(asset);

        assertNull(asset.getBorrowerLocation());
        assertNull(asset.getBorrowerStorageLocation());
    }

    @Test
    public void testSetEquipmentLoanInfo_noExpectedReturnDate_doesNotSet() {
        Asset asset = new Asset();
        asset.setExpectedReturnDate(null);
        asset.setLoanReturnDate(null);
        asset.setAssetLocations(new ArrayList<AssetLocation>());

        service.setEquipmentLoanInfo(asset);

        assertNull(asset.getBorrowerLocation());
        assertNull(asset.getBorrowerStorageLocation());
    }

    @Test
    public void testSetEquipmentLoanInfo_activeLoan_noBorrowerLocation() {
        Asset asset = mock(Asset.class);
        when(asset.getExpectedReturnDate()).thenReturn(new Date(System.currentTimeMillis()));
        when(asset.getLoanReturnDate()).thenReturn(null);

        AssetLocation otherLoc = new AssetLocation();
        otherLoc.setAssetLocationTypeCode(CamsConstants.AssetLocationTypeCode.OFF_CAMPUS);

        List<AssetLocation> locations = new ArrayList<AssetLocation>();
        locations.add(otherLoc);
        when(asset.getAssetLocations()).thenReturn(locations);

        service.setEquipmentLoanInfo(asset);

        verify(asset, never()).setBorrowerLocation(any(AssetLocation.class));
        verify(asset, never()).setBorrowerStorageLocation(any(AssetLocation.class));
    }

    @Test
    public void testGetBusinessObjectService() {
        assertSame(businessObjectService, service.getBusinessObjectService());
    }
}
