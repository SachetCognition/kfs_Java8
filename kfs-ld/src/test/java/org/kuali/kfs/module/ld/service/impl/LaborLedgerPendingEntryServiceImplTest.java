package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.LaborLedgerPendingEntry;
import org.kuali.kfs.module.ld.dataaccess.LaborLedgerPendingEntryDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.GeneralLedgerPendingEntryService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LaborLedgerPendingEntryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborLedgerPendingEntryDao laborLedgerPendingEntryDao;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private GeneralLedgerPendingEntryService generalLedgerPendingEntryService;

    @InjectMocks
    private LaborLedgerPendingEntryServiceImpl service;

    @Test
    void testHasPendingLaborLedgerEntry_byChartAndAccount_true() {
        when(businessObjectService.countMatching(eq(LaborLedgerPendingEntry.class), any(Map.class))).thenReturn(3);

        boolean result = service.hasPendingLaborLedgerEntry("BL", "1234567");
        assertTrue(result);
    }

    @Test
    void testHasPendingLaborLedgerEntry_byChartAndAccount_false() {
        when(businessObjectService.countMatching(eq(LaborLedgerPendingEntry.class), any(Map.class))).thenReturn(0);

        boolean result = service.hasPendingLaborLedgerEntry("BL", "1234567");
        assertFalse(result);
    }

    @Test
    void testDelete() {
        service.delete("DOC123");
        verify(laborLedgerPendingEntryDao).delete("DOC123");
    }

    @Test
    void testFindApprovedPendingLedgerEntries() {
        Iterator<LaborLedgerPendingEntry> expected = Collections.emptyIterator();
        when(laborLedgerPendingEntryDao.findApprovedPendingLedgerEntries()).thenReturn(expected);

        Iterator<LaborLedgerPendingEntry> result = service.findApprovedPendingLedgerEntries();
        assertSame(expected, result);
    }

    @Test
    void testDeleteByFinancialDocumentApprovedCode() {
        service.deleteByFinancialDocumentApprovedCode("A");
        verify(laborLedgerPendingEntryDao).deleteByFinancialDocumentApprovedCode("A");
    }
}
