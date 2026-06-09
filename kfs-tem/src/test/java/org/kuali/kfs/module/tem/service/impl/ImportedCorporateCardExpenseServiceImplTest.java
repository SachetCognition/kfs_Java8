package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.ImportedExpense;
import org.kuali.kfs.module.tem.document.TravelDocument;
import org.kuali.kfs.module.tem.service.TravelExpenseService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.coa.service.ObjectCodeService;

class ImportedCorporateCardExpenseServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private ImportedCorporateCardExpenseServiceImpl importedCorpCardExpenseService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private TravelExpenseService travelExpenseService;

    @Mock
    private ObjectCodeService objectCodeService;

    @Test
    void testGetExpenseDetails_returnsImportedExpenses() {
        TravelDocument document = mock(TravelDocument.class);
        List<ImportedExpense> expenses = new ArrayList();
        ImportedExpense expense = new ImportedExpense();
        expense.setCardType(org.kuali.kfs.module.tem.TemConstants.TRAVEL_TYPE_CORP);
        expenses.add(expense);
        when(document.getImportedExpenses()).thenReturn(expenses);
        when(document.getHistoricalTravelExpenses()).thenReturn(new ArrayList());

        List<?> result = importedCorpCardExpenseService.getExpenseDetails(document);

        assertEquals(1, result.size());
    }

    @Test
    void testGetExpenseDetails_emptyList() {
        TravelDocument document = mock(TravelDocument.class);
        when(document.getImportedExpenses()).thenReturn(new java.util.ArrayList());
        when(document.getHistoricalTravelExpenses()).thenReturn(new ArrayList());

        List<?> result = importedCorpCardExpenseService.getExpenseDetails(document);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetNonReimbursableExpenseTotal_empty() {
        TravelDocument document = mock(TravelDocument.class);
        when(document.getImportedExpenses()).thenReturn(new java.util.ArrayList());

        KualiDecimal result = importedCorpCardExpenseService.getNonReimbursableExpenseTotal(document);

        assertEquals(KualiDecimal.ZERO, result);
    }
}
