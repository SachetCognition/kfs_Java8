package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

public class AssetLocationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private AssetLocationServiceImpl service;

    // --- setOffCampusLocation ---

    @Test
    public void testSetOffCampusLocation_findsExistingOffCampus() {
        Asset asset = new Asset();
        asset.setCapitalAssetNumber(1000L);

        AssetLocation offCampusLoc = new AssetLocation();
        offCampusLoc.setAssetLocationTypeCode(CamsConstants.AssetLocationTypeCode.OFF_CAMPUS);
        offCampusLoc.setAssetLocationCityName("Boston");

        AssetLocation onCampusLoc = new AssetLocation();
        onCampusLoc.setAssetLocationTypeCode("B"); // some other type

        List<AssetLocation> locations = new ArrayList<>();
        locations.add(onCampusLoc);
        locations.add(offCampusLoc);
        asset.setAssetLocations(locations);

        service.setOffCampusLocation(asset);

        assertNotNull(asset.getOffCampusLocation());
        assertEquals("Boston", asset.getOffCampusLocation().getAssetLocationCityName());
    }

    @Test
    public void testSetOffCampusLocation_noOffCampusFound_createsNew() {
        Asset asset = new Asset();
        asset.setCapitalAssetNumber(1000L);
        asset.setAssetLocations(new ArrayList<AssetLocation>());

        service.setOffCampusLocation(asset);

        assertNotNull(asset.getOffCampusLocation());
        assertEquals(CamsConstants.AssetLocationTypeCode.OFF_CAMPUS,
                asset.getOffCampusLocation().getAssetLocationTypeCode());
    }

    // --- isOffCampusLocationExists ---

    @Test
    public void testIsOffCampusLocationExists_offCampusType() {
        AssetLocation location = new AssetLocation();
        location.setAssetLocationTypeCode(CamsConstants.AssetLocationTypeCode.OFF_CAMPUS);
        assertTrue(service.isOffCampusLocationExists(location));
    }

    @Test
    public void testIsOffCampusLocationExists_otherType() {
        AssetLocation location = new AssetLocation();
        location.setAssetLocationTypeCode("B");
        assertFalse(service.isOffCampusLocationExists(location));
    }

    @Test
    public void testIsOffCampusLocationExists_null() {
        assertFalse(service.isOffCampusLocationExists(null));
    }

    // --- isOffCampusLocationEmpty ---

    @Test
    public void testIsOffCampusLocationEmpty_allBlank() {
        AssetLocation location = new AssetLocation();
        assertTrue(service.isOffCampusLocationEmpty(location));
    }

    @Test
    public void testIsOffCampusLocationEmpty_hasCityName() {
        AssetLocation location = new AssetLocation();
        location.setAssetLocationCityName("Boston");
        assertFalse(service.isOffCampusLocationEmpty(location));
    }

    @Test
    public void testIsOffCampusLocationEmpty_hasContactName() {
        AssetLocation location = new AssetLocation();
        location.setAssetLocationContactName("John");
        assertFalse(service.isOffCampusLocationEmpty(location));
    }

    @Test
    public void testIsOffCampusLocationEmpty_hasStreetAddress() {
        AssetLocation location = new AssetLocation();
        location.setAssetLocationStreetAddress("123 Main St");
        assertFalse(service.isOffCampusLocationEmpty(location));
    }

    @Test
    public void testIsOffCampusLocationEmpty_hasStateCode() {
        AssetLocation location = new AssetLocation();
        location.setAssetLocationStateCode("MA");
        assertFalse(service.isOffCampusLocationEmpty(location));
    }

    @Test
    public void testIsOffCampusLocationEmpty_hasZipCode() {
        AssetLocation location = new AssetLocation();
        location.setAssetLocationZipCode("02101");
        assertFalse(service.isOffCampusLocationEmpty(location));
    }

    @Test
    public void testIsOffCampusLocationEmpty_hasCountryCode() {
        AssetLocation location = new AssetLocation();
        location.setAssetLocationCountryCode("US");
        assertFalse(service.isOffCampusLocationEmpty(location));
    }

    @Test
    public void testIsOffCampusLocationEmpty_hasPhoneNumber() {
        AssetLocation location = new AssetLocation();
        location.setAssetLocationPhoneNumber("555-1234");
        assertFalse(service.isOffCampusLocationEmpty(location));
    }

    @Test
    public void testIsOffCampusLocationEmpty_null() {
        assertTrue(service.isOffCampusLocationEmpty(null));
    }

    // --- updateOffCampusLocation ---

    @Test
    public void testUpdateOffCampusLocation_updatesExistingLocation() {
        Asset asset = new Asset();
        AssetLocation offCampus = new AssetLocation();
        offCampus.setAssetLocationCityName("New York");
        offCampus.setAssetLocationStateCode("NY");
        asset.setOffCampusLocation(offCampus);

        AssetLocation existingOff = new AssetLocation();
        existingOff.setAssetLocationTypeCode(CamsConstants.AssetLocationTypeCode.OFF_CAMPUS);

        List<AssetLocation> locations = new ArrayList<>();
        locations.add(existingOff);
        asset.setAssetLocations(locations);

        service.updateOffCampusLocation(asset);

        assertEquals("New York", existingOff.getAssetLocationCityName());
        assertEquals("NY", existingOff.getAssetLocationStateCode());
    }

    @Test
    public void testUpdateOffCampusLocation_removesWhenEmpty() {
        Asset asset = new Asset();
        AssetLocation emptyOffCampus = new AssetLocation();
        asset.setOffCampusLocation(emptyOffCampus);

        AssetLocation existingOff = new AssetLocation();
        existingOff.setAssetLocationTypeCode(CamsConstants.AssetLocationTypeCode.OFF_CAMPUS);

        List<AssetLocation> locations = new ArrayList<>();
        locations.add(existingOff);
        asset.setAssetLocations(locations);

        service.updateOffCampusLocation(asset);

        assertFalse(asset.getAssetLocations().contains(existingOff));
    }

    @Test
    public void testUpdateOffCampusLocation_addsNewWhenNotEmpty() {
        Asset asset = new Asset();
        AssetLocation offCampus = new AssetLocation();
        offCampus.setAssetLocationCityName("Chicago");
        asset.setOffCampusLocation(offCampus);

        asset.setAssetLocations(new ArrayList<AssetLocation>());

        service.updateOffCampusLocation(asset);

        assertEquals(1, asset.getAssetLocations().size());
        assertEquals("Chicago", asset.getAssetLocations().get(0).getAssetLocationCityName());
    }
}
