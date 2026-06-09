package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.LaborGeneralLedgerEntry;
import org.kuali.kfs.module.ld.dataaccess.LaborDao;
import org.kuali.kfs.module.ld.dataaccess.LaborGeneralLedgerEntryDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaborGeneralLedgerEntryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborGeneralLedgerEntryDao laborGeneralLedgerEntryDao;

    @Mock
    private LaborDao laborDao;

    @InjectMocks
    private LaborGeneralLedgerEntryServiceImpl service;

    @Test
    void testGetMaxSequenceNumber() {
        LaborGeneralLedgerEntry entry = new LaborGeneralLedgerEntry();
        when(laborGeneralLedgerEntryDao.getMaxSequenceNumber(entry)).thenReturn(5);

        Integer result = service.getMaxSequenceNumber(entry);
        assertEquals(5, result);
        verify(laborGeneralLedgerEntryDao).getMaxSequenceNumber(entry);
    }

    @Test
    void testGetMaxSequenceNumber_returnsNull() {
        LaborGeneralLedgerEntry entry = new LaborGeneralLedgerEntry();
        when(laborGeneralLedgerEntryDao.getMaxSequenceNumber(entry)).thenReturn(null);

        Integer result = service.getMaxSequenceNumber(entry);
        assertNull(result);
    }

    @Test
    void testSave() {
        LaborGeneralLedgerEntry entry = new LaborGeneralLedgerEntry();
        service.save(entry);
        verify(laborDao).insert(entry);
    }
}
