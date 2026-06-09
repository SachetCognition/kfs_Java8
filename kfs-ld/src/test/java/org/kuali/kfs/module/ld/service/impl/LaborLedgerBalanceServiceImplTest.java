package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.LedgerBalance;
import org.kuali.kfs.module.ld.dataaccess.LaborLedgerBalanceDao;
import org.kuali.kfs.module.ld.service.LaborCalculatedSalaryFoundationTrackerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaborLedgerBalanceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborLedgerBalanceDao laborLedgerBalanceDao;

    @Mock
    private LaborCalculatedSalaryFoundationTrackerService laborCalculatedSalaryFoundationTrackerService;

    @InjectMocks
    private LaborLedgerBalanceServiceImpl service;

    @Test
    void testFindBalancesForFiscalYear() {
        Iterator<LedgerBalance> expected = Collections.emptyIterator();
        when(laborLedgerBalanceDao.findBalancesForFiscalYear(2014)).thenReturn(expected);

        Iterator<LedgerBalance> result = service.findBalancesForFiscalYear(2014);
        assertSame(expected, result);
    }

    @Test
    void testFindBalancesForFiscalYear_withFieldValuesAndEncumbranceTypes() {
        Map<String, String> fieldValues = new HashMap<>();
        List<String> encumbranceTypes = Arrays.asList("IE", "EX");
        Iterator<LedgerBalance> expected = Collections.emptyIterator();

        when(laborLedgerBalanceDao.findBalancesForFiscalYear(2014, fieldValues, encumbranceTypes)).thenReturn(expected);

        Iterator<LedgerBalance> result = service.findBalancesForFiscalYear(2014, fieldValues, encumbranceTypes);
        assertSame(expected, result);
    }

    @Test
    void testFindBalance() {
        Map<String, String> fieldValues = new HashMap<>();
        List<String> encumbranceTypes = Arrays.asList("IE");
        Iterator expected = Collections.emptyIterator();

        when(laborLedgerBalanceDao.findBalance(fieldValues, false, encumbranceTypes, true)).thenReturn(expected);

        Iterator result = service.findBalance(fieldValues, false, encumbranceTypes, true);
        assertSame(expected, result);
    }

    @Test
    void testFindBalance_consolidated() {
        Map<String, String> fieldValues = new HashMap<>();
        List<String> encumbranceTypes = Arrays.asList("IE");
        Iterator expected = Collections.emptyIterator();

        when(laborLedgerBalanceDao.findBalance(fieldValues, true, encumbranceTypes, false)).thenReturn(expected);

        Iterator result = service.findBalance(fieldValues, true, encumbranceTypes, false);
        assertSame(expected, result);
    }

    @Test
    void testFindBalance_notConsolidated() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        List<String> encumbranceTypes = Arrays.asList("IE");
        Iterator expected = Collections.emptyIterator();

        when(laborLedgerBalanceDao.findBalance(fieldValues, false, encumbranceTypes, true)).thenReturn(expected);

        Iterator result = service.findBalance(fieldValues, false, encumbranceTypes, true);
        assertSame(expected, result);
    }

    @Test
    void testGetBalanceRecordCount_consolidated() {
        Map<String, String> fieldValues = new HashMap<>();
        List<String> encumbranceTypes = Arrays.asList("IE");
        Iterator countIterator = Collections.emptyIterator();

        when(laborLedgerBalanceDao.getConsolidatedBalanceRecordCount(fieldValues, encumbranceTypes, true)).thenReturn(countIterator);

        Integer result = service.getBalanceRecordCount(fieldValues, true, encumbranceTypes, true);
        assertEquals(0, result);
    }
}
