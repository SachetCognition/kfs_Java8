package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.ActualExpense;
import org.kuali.kfs.module.tem.document.TravelDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.coa.service.ObjectCodeService;

class ActualExpenseServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private ActualExpenseServiceImpl actualExpenseService;

    @Mock
    private ObjectCodeService objectCodeService;

    @Test
    void testGetExpenseDetails_returnsActualExpenses() {
        TravelDocument document = mock(TravelDocument.class);
        List<ActualExpense> expenses = new ArrayList();
        expenses.add(new ActualExpense());
        when(document.getActualExpenses()).thenReturn(expenses);

        List<?> result = actualExpenseService.getExpenseDetails(document);

        assertEquals(1, result.size());
    }

    @Test
    void testGetExpenseDetails_emptyList() {
        TravelDocument document = mock(TravelDocument.class);
        when(document.getActualExpenses()).thenReturn(new java.util.ArrayList());

        List<?> result = actualExpenseService.getExpenseDetails(document);

        assertTrue(result.isEmpty());
    }

    @Test
    void testProcessExpense_doesNothing() {
        TravelDocument document = mock(TravelDocument.class);
        actualExpenseService.processExpense(document, null);
        verifyNoInteractions(document);
    }

    @Test
    void testUpdateExpense_doesNothing() {
        TravelDocument document = mock(TravelDocument.class);
        actualExpenseService.updateExpense(document);
        verifyNoInteractions(document);
    }

    @Test
    void testGetAllExpenseTotal_noExpenses() {
        TravelDocument document = mock(TravelDocument.class);
        when(document.getActualExpenses()).thenReturn(new java.util.ArrayList());

        KualiDecimal result = actualExpenseService.getAllExpenseTotal(document, true);

        assertEquals(KualiDecimal.ZERO, result);
    }
}
