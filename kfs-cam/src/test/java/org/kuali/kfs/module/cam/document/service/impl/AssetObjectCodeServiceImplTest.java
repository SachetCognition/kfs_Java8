package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.module.cam.businessobject.AssetObjectCode;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AssetObjectCodeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private AssetObjectCodeServiceImpl service;

    @Test
    public void testFindAssetObjectCode_returnsMatchingObject() {
        when(universityDateService.getCurrentFiscalYear()).thenReturn(2024);

        AssetObjectCode expected = new AssetObjectCode();
        Map<String, Object> expectedKeys = new HashMap<>();
        expectedKeys.put(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, 2024);
        expectedKeys.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, "BL");
        expectedKeys.put(KFSPropertyConstants.FINANCIAL_OBJECT_SUB_TYPE_CODE, "CM");

        when(businessObjectService.findByPrimaryKey(eq(AssetObjectCode.class), eq(expectedKeys)))
                .thenReturn(expected);

        AssetObjectCode result = service.findAssetObjectCode("BL", "CM");
        assertSame(expected, result);
    }

    @Test
    public void testFindAssetObjectCode_whenNotFound_returnsNull() {
        when(universityDateService.getCurrentFiscalYear()).thenReturn(2024);
        when(businessObjectService.findByPrimaryKey(eq(AssetObjectCode.class), ArgumentMatchers.<java.util.Map<String, Object>>any()))
                .thenReturn(null);

        assertNull(service.findAssetObjectCode("XX", "ZZ"));
    }

    @Test
    public void testGetUniversityDateService() {
        assertSame(universityDateService, service.getUniversityDateService());
    }

    @Test
    public void testGetBusinessObjectService() {
        assertSame(businessObjectService, service.getBusinessObjectService());
    }
}
