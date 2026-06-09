package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.module.cam.businessobject.AssetComponent;
import org.kuali.kfs.module.cam.document.dataaccess.AssetComponentDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

public class AssetComponentServiceImplTest extends KfsUnitTestBase {

    @Mock
    private AssetComponentDao assetComponentDao;

    @InjectMocks
    private AssetComponentServiceImpl service;

    @Test
    public void testGetMaxSequenceNumber_returnsValueFromDao() {
        AssetComponent component = new AssetComponent();
        when(assetComponentDao.getMaxSquenceNumber(component)).thenReturn(5);

        assertEquals(Integer.valueOf(5), service.getMaxSequenceNumber(component));
        verify(assetComponentDao).getMaxSquenceNumber(component);
    }

    @Test
    public void testGetMaxSequenceNumber_whenDaoReturnsNull() {
        AssetComponent component = new AssetComponent();
        when(assetComponentDao.getMaxSquenceNumber(component)).thenReturn(null);

        assertNull(service.getMaxSequenceNumber(component));
    }

    @Test
    public void testGetMaxSequenceNumber_whenDaoReturnsZero() {
        AssetComponent component = new AssetComponent();
        when(assetComponentDao.getMaxSquenceNumber(component)).thenReturn(0);

        assertEquals(Integer.valueOf(0), service.getMaxSequenceNumber(component));
    }

    @Test
    public void testGetAssetComponentDao() {
        assertSame(assetComponentDao, service.getAssetComponentDao());
    }
}
