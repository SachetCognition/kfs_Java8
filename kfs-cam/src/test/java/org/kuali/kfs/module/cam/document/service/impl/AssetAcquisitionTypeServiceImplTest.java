package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.module.cam.CamsPropertyConstants;
import org.kuali.kfs.module.cam.businessobject.AssetAcquisitionType;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AssetAcquisitionTypeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private AssetAcquisitionTypeServiceImpl service;

    @Test
    public void testHasIncomeAssetObjectCode_whenObjectCodeIsNotBlank_returnsTrue() {
        AssetAcquisitionType acquisitionType = new AssetAcquisitionType();
        acquisitionType.setIncomeAssetObjectCode("7000");

        when(businessObjectService.findByPrimaryKey(eq(AssetAcquisitionType.class), ArgumentMatchers.<java.util.Map<String, Object>>any()))
                .thenReturn(acquisitionType);

        assertTrue(service.hasIncomeAssetObjectCode("A"));
    }

    @Test
    public void testHasIncomeAssetObjectCode_whenObjectCodeIsBlank_returnsFalse() {
        AssetAcquisitionType acquisitionType = new AssetAcquisitionType();
        acquisitionType.setIncomeAssetObjectCode("");

        when(businessObjectService.findByPrimaryKey(eq(AssetAcquisitionType.class), ArgumentMatchers.<java.util.Map<String, Object>>any()))
                .thenReturn(acquisitionType);

        assertFalse(service.hasIncomeAssetObjectCode("B"));
    }

    @Test
    public void testHasIncomeAssetObjectCode_whenObjectCodeIsNull_returnsFalse() {
        AssetAcquisitionType acquisitionType = new AssetAcquisitionType();
        acquisitionType.setIncomeAssetObjectCode(null);

        when(businessObjectService.findByPrimaryKey(eq(AssetAcquisitionType.class), ArgumentMatchers.<java.util.Map<String, Object>>any()))
                .thenReturn(acquisitionType);

        assertFalse(service.hasIncomeAssetObjectCode("C"));
    }

    @Test
    public void testGetBusinessObjectService() {
        assertSame(businessObjectService, service.getBusinessObjectService());
    }
}
