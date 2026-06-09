package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.batch.service.LaborAccountingCycleCachingService;
import org.kuali.kfs.module.ld.businessobject.LedgerEntry;
import org.kuali.kfs.module.ld.dataaccess.LaborLedgerEntryDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class LaborLedgerEntryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborLedgerEntryDao laborLedgerEntryDao;

    @Mock
    private LaborAccountingCycleCachingService laborAccountingCycleCachingService;

    @InjectMocks
    private LaborLedgerEntryServiceImpl laborLedgerEntryService;

    @Test
    void testSaveDelegatesToCachingService() {
        LedgerEntry entry = new LedgerEntry();
        laborLedgerEntryService.save(entry);
        verify(laborAccountingCycleCachingService).insertLedgerEntry(entry);
    }

    @Test
    void testGetMaxSequenceNumberDelegatesToCachingService() {
        LedgerEntry entry = new LedgerEntry();
        when(laborAccountingCycleCachingService.getMaxLaborSequenceNumber(entry)).thenReturn(5);

        Integer result = laborLedgerEntryService.getMaxSequenceNumber(entry);

        assertThat(result).isEqualTo(5);
        verify(laborAccountingCycleCachingService).getMaxLaborSequenceNumber(entry);
    }

    @Test
    void testFindDelegatesToDao() {
        Map<String, String> fieldValues = Map.of("universityFiscalYear", "2024");
        Iterator<LedgerEntry> expected = Collections.emptyIterator();
        when(laborLedgerEntryDao.find(fieldValues)).thenReturn(expected);

        Iterator<LedgerEntry> result = laborLedgerEntryService.find(fieldValues);

        assertThat(result).isSameAs(expected);
        verify(laborLedgerEntryDao).find(fieldValues);
    }

    @Test
    void testFindEmployeesWithPayTypeDelegatesToDao() {
        Map<Integer, Set<String>> payPeriods = new HashMap<>();
        List<String> balanceTypes = List.of("AC");
        Map<String, Set<String>> earnCodePayGroupMap = new HashMap<>();
        List<String> expected = List.of("0000001234");

        when(laborLedgerEntryDao.findEmployeesWithPayType(payPeriods, balanceTypes, earnCodePayGroupMap))
                .thenReturn(expected);

        List<String> result = laborLedgerEntryService.findEmployeesWithPayType(payPeriods, balanceTypes, earnCodePayGroupMap);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testIsEmployeeWithPayTypeDelegatesToDao() {
        Map<Integer, Set<String>> payPeriods = new HashMap<>();
        List<String> balanceTypes = List.of("AC");
        Map<String, Set<String>> earnCodePayGroupMap = new HashMap<>();

        when(laborLedgerEntryDao.isEmployeeWithPayType("0000001234", payPeriods, balanceTypes, earnCodePayGroupMap))
                .thenReturn(true);

        boolean result = laborLedgerEntryService.isEmployeeWithPayType("0000001234", payPeriods, balanceTypes, earnCodePayGroupMap);

        assertThat(result).isTrue();
    }

    @Test
    void testDeleteLedgerEntriesPriorToYearDelegatesToDao() {
        laborLedgerEntryService.deleteLedgerEntriesPriorToYear(2024, "BL");
        verify(laborLedgerEntryDao).deleteLedgerEntriesPriorToYear(2024, "BL");
    }
}
