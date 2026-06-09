package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.HistoricalTravelExpense;
import org.kuali.kfs.module.tem.dataaccess.HistoricalTravelExpenseDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class HistoricalTravelExpenseServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private HistoricalTravelExpenseServiceImpl historicalTravelExpenseService;

    @Mock
    private HistoricalTravelExpenseDao historicalTravelExpenseDao;

    @Test
    void testGetImportedExpensesToBeNotified_allProfiles() {
        List<HistoricalTravelExpense> expenses = new ArrayList();
        expenses.add(new HistoricalTravelExpense());

        when(historicalTravelExpenseDao.getImportedExpesnesToBeNotified()).thenReturn(expenses);

        List<HistoricalTravelExpense> result = historicalTravelExpenseService.getImportedExpesnesToBeNotified();

        assertEquals(1, result.size());
    }

    @Test
    void testGetImportedExpensesToBeNotified_emptyList() {
        when(historicalTravelExpenseDao.getImportedExpesnesToBeNotified()).thenReturn(new java.util.ArrayList());

        List<HistoricalTravelExpense> result = historicalTravelExpenseService.getImportedExpesnesToBeNotified();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetImportedExpensesToBeNotified_byProfileId() {
        List<HistoricalTravelExpense> expenses = new ArrayList();
        expenses.add(new HistoricalTravelExpense());
        expenses.add(new HistoricalTravelExpense());

        when(historicalTravelExpenseDao.getImportedExpesnesToBeNotified(100)).thenReturn(expenses);

        List<HistoricalTravelExpense> result = historicalTravelExpenseService.getImportedExpesnesToBeNotified(100);

        assertEquals(2, result.size());
    }

    @Test
    void testGetImportedExpensesToBeNotified_byProfileId_empty() {
        when(historicalTravelExpenseDao.getImportedExpesnesToBeNotified(999)).thenReturn(new java.util.ArrayList());

        List<HistoricalTravelExpense> result = historicalTravelExpenseService.getImportedExpesnesToBeNotified(999);

        assertTrue(result.isEmpty());
    }
}
