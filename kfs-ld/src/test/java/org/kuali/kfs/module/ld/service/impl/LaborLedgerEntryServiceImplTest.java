package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.batch.service.LaborAccountingCycleCachingService;
import org.kuali.kfs.module.ld.businessobject.LedgerEntry;
import org.kuali.kfs.module.ld.dataaccess.LaborLedgerEntryDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaborLedgerEntryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborLedgerEntryDao laborLedgerEntryDao;

    @Mock
    private LaborAccountingCycleCachingService laborAccountingCycleCachingService;

    @InjectMocks
    private LaborLedgerEntryServiceImpl service;

    @Test
    void testSave() {
        LedgerEntry entry = new LedgerEntry();
        service.save(entry);
        verify(laborAccountingCycleCachingService).insertLedgerEntry(entry);
    }

    @Test
    void testGetMaxSequenceNumber() {
        LedgerEntry entry = new LedgerEntry();
        when(laborAccountingCycleCachingService.getMaxLaborSequenceNumber(entry)).thenReturn(10);

        Integer result = service.getMaxSequenceNumber(entry);
        assertEquals(10, result);
    }

    @Test
    void testGetMaxSequenceNumber_returnsZero() {
        LedgerEntry entry = new LedgerEntry();
        when(laborAccountingCycleCachingService.getMaxLaborSequenceNumber(entry)).thenReturn(0);

        Integer result = service.getMaxSequenceNumber(entry);
        assertEquals(0, result.intValue());
    }

    @Test
    void testFind() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("chartOfAccountsCode", "BL");
        Iterator<LedgerEntry> expected = Collections.emptyIterator();

        when(laborLedgerEntryDao.find(fieldValues)).thenReturn(expected);

        Iterator<LedgerEntry> result = service.find(fieldValues);
        assertSame(expected, result);
    }

    @Test
    void testFindEmployeesWithPayType() {
        Map<Integer, Set<String>> payPeriods = new HashMap<>();
        List<String> balanceTypes = Arrays.asList("AC", "CB");
        Map<String, Set<String>> earnCodePayGroupMap = new HashMap<>();
        List<String> expected = Arrays.asList("EMP001", "EMP002");

        when(laborLedgerEntryDao.findEmployeesWithPayType(payPeriods, balanceTypes, earnCodePayGroupMap)).thenReturn(expected);

        List<String> result = service.findEmployeesWithPayType(payPeriods, balanceTypes, earnCodePayGroupMap);
        assertEquals(expected, result);
    }

    @Test
    void testIsEmployeeWithPayType() {
        Map<Integer, Set<String>> payPeriods = new HashMap<>();
        List<String> balanceTypes = Arrays.asList("AC");
        Map<String, Set<String>> earnCodePayGroupMap = new HashMap<>();

        when(laborLedgerEntryDao.isEmployeeWithPayType("EMP001", payPeriods, balanceTypes, earnCodePayGroupMap)).thenReturn(true);

        assertTrue(service.isEmployeeWithPayType("EMP001", payPeriods, balanceTypes, earnCodePayGroupMap));
    }

    @Test
    void testIsEmployeeWithPayType_notFound() {
        Map<Integer, Set<String>> payPeriods = new HashMap<>();
        List<String> balanceTypes = Arrays.asList("AC");
        Map<String, Set<String>> earnCodePayGroupMap = new HashMap<>();

        when(laborLedgerEntryDao.isEmployeeWithPayType("EMP999", payPeriods, balanceTypes, earnCodePayGroupMap)).thenReturn(false);

        assertFalse(service.isEmployeeWithPayType("EMP999", payPeriods, balanceTypes, earnCodePayGroupMap));
    }

    @Test
    void testDeleteLedgerEntriesPriorToYear() {
        service.deleteLedgerEntriesPriorToYear(2014, "BL");
        verify(laborLedgerEntryDao).deleteLedgerEntriesPriorToYear(2014, "BL");
    }
}
