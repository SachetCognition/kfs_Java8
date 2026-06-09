package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.module.cam.document.service.AssetLocationService;
import org.kuali.kfs.module.cam.document.service.AssetObjectCodeService;
import org.kuali.kfs.module.cam.document.service.AssetPaymentService;
import org.kuali.kfs.module.cam.document.service.AssetService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AssetTransferServiceImplTest extends KfsUnitTestBase {

    @Mock
    private AssetService assetService;

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private AssetPaymentService assetPaymentService;

    @Mock
    private AssetObjectCodeService assetObjectCodeService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private AssetLocationService assetLocationService;

    @InjectMocks
    private AssetTransferServiceImpl service;

    // --- accessor tests ---

    @Test
    public void testGetAssetService() {
        assertSame(assetService, service.getAssetService());
    }

    @Test
    public void testGetUniversityDateService() {
        assertSame(universityDateService, service.getUniversityDateService());
    }

    @Test
    public void testGetBusinessObjectService() {
        assertSame(businessObjectService, service.getBusinessObjectService());
    }

    @Test
    public void testGetAssetPaymentService() {
        assertSame(assetPaymentService, service.getAssetPaymentService());
    }

    @Test
    public void testGetAssetObjectCodeService() {
        assertSame(assetObjectCodeService, service.getAssetObjectCodeService());
    }

    @Test
    public void testGetDateTimeService() {
        assertSame(dateTimeService, service.getDateTimeService());
    }

    @Test
    public void testGetAssetLocationService() {
        assertSame(assetLocationService, service.getAssetLocationService());
    }
}
