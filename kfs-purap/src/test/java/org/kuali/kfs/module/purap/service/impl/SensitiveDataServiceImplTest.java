package org.kuali.kfs.module.purap.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.kuali.kfs.module.purap.businessobject.SensitiveData;
import org.kuali.kfs.module.purap.dataaccess.SensitiveDataDao;
import org.kuali.kfs.module.purap.document.dataaccess.PurchaseOrderDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

@MockitoSettings(strictness = Strictness.LENIENT)
public class SensitiveDataServiceImplTest extends KfsUnitTestBase {

    @Mock private SensitiveDataDao sensitiveDataDao;
    @Mock private PurchaseOrderDao purchaseOrderDao;

    @InjectMocks
    private SensitiveDataServiceImpl sensitiveDataService;

    @Test
    public void testGetSensitiveDatasAssignedByPoId_returnsData() {
        List<SensitiveData> expected = new ArrayList<SensitiveData>();
        expected.add(new SensitiveData());
        when(sensitiveDataDao.getSensitiveDatasAssignedByPoId(100)).thenReturn(expected);

        List<SensitiveData> result = sensitiveDataService.getSensitiveDatasAssignedByPoId(100);

        assertThat(result).hasSize(1);
    }

    @Test
    public void testGetSensitiveDatasAssignedByPoId_emptyResult() {
        when(sensitiveDataDao.getSensitiveDatasAssignedByPoId(999)).thenReturn(new ArrayList<SensitiveData>());

        List<SensitiveData> result = sensitiveDataService.getSensitiveDatasAssignedByPoId(999);

        assertThat(result).isEmpty();
    }

    @Test
    public void testGetSensitiveDatasAssignedByReqId_returnsData() {
        List<SensitiveData> expected = new ArrayList<SensitiveData>();
        expected.add(new SensitiveData());
        when(sensitiveDataDao.getSensitiveDatasAssignedByReqId(50)).thenReturn(expected);

        List<SensitiveData> result = sensitiveDataService.getSensitiveDatasAssignedByReqId(50);

        assertThat(result).hasSize(1);
    }

    @Test
    public void testDeletePurchaseOrderSensitiveDatas_callsDao() {
        sensitiveDataService.deletePurchaseOrderSensitiveDatas(100);

        verify(sensitiveDataDao).deletePurchaseOrderSensitiveDatas(100);
    }

    @Test
    public void testGetSensitiveDatasAssignedByRelatedDocId_delegatesToDao() {
        when(purchaseOrderDao.getPurchaseOrderIdForCurrentPurchaseOrderByRelatedDocId(200)).thenReturn(100);
        List<SensitiveData> expected = new ArrayList<SensitiveData>();
        expected.add(new SensitiveData());
        when(sensitiveDataDao.getSensitiveDatasAssignedByPoId(100)).thenReturn(expected);

        List<SensitiveData> result = sensitiveDataService.getSensitiveDatasAssignedByRelatedDocId(200);

        assertThat(result).hasSize(1);
    }

    @Test
    public void testGetSensitiveDatasAssignedByRelatedDocId_nullPoId() {
        when(purchaseOrderDao.getPurchaseOrderIdForCurrentPurchaseOrderByRelatedDocId(300)).thenReturn(null);
        when(sensitiveDataDao.getSensitiveDatasAssignedByPoId(null)).thenReturn(new ArrayList<SensitiveData>());

        List<SensitiveData> result = sensitiveDataService.getSensitiveDatasAssignedByRelatedDocId(300);

        assertThat(result).isEmpty();
    }
}
