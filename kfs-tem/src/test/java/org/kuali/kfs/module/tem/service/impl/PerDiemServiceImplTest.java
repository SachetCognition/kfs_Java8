package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.TemParameterConstants;
import org.kuali.kfs.module.tem.batch.businessobject.MealBreakDownStrategy;
import org.kuali.kfs.module.tem.businessobject.PerDiem;
import org.kuali.kfs.module.tem.businessobject.PerDiemExpense;
import org.kuali.kfs.module.tem.dataaccess.PerDiemDao;
import org.kuali.kfs.module.tem.dataaccess.TravelDocumentDao;
import org.kuali.kfs.module.tem.document.TravelDocument;
import org.kuali.kfs.module.tem.service.TravelExpenseService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class PerDiemServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private PerDiemServiceImpl perDiemService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private PerDiemDao perDiemDao;

    @Mock
    private TravelDocumentDao travelDocumentDao;

    @Mock
    private TravelExpenseService travelExpenseService;

    private Map<String, MealBreakDownStrategy> mealBreakDownStrategies;

    @BeforeEach
    void setUp() {
        mealBreakDownStrategies = new HashMap();
        perDiemService.setMealBreakDownStrategies(mealBreakDownStrategies);
    }

    @Test
    void testBreakDownMealsIncidental_validConusIndicator() {
        MealBreakDownStrategy strategy = mock(MealBreakDownStrategy.class);
        mealBreakDownStrategies.put("Y", strategy);

        PerDiem perDiem = new PerDiem();
        perDiem.setConusIndicator("Y");

        perDiemService.breakDownMealsIncidental(perDiem);

        verify(strategy).breakDown(perDiem);
    }

    @Test
    void testBreakDownMealsIncidental_invalidConusIndicatorThrows() {
        PerDiem perDiem = new PerDiem();
        perDiem.setConusIndicator("INVALID");

        try {
            perDiemService.breakDownMealsIncidental(perDiem);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // expected
        }
    }

    @Test
    void testBreakDownMealsIncidental_list() {
        MealBreakDownStrategy strategy = mock(MealBreakDownStrategy.class);
        mealBreakDownStrategies.put("Y", strategy);

        PerDiem perDiem1 = new PerDiem();
        perDiem1.setConusIndicator("Y");
        PerDiem perDiem2 = new PerDiem();
        perDiem2.setConusIndicator("Y");

        List<PerDiem> list = Arrays.asList(perDiem1, perDiem2);
        perDiemService.breakDownMealsIncidental(list);

        verify(strategy, times(2)).breakDown(any(PerDiem.class));
    }

    @Test
    void testRetrieveInactivePerDiem() {
        List<PerDiem> inactive = new ArrayList();
        inactive.add(new PerDiem());
        Map<String, Object> fieldValues = new HashMap();
        fieldValues.put(KFSPropertyConstants.ACTIVE, Boolean.FALSE);

        when(businessObjectService.findMatching(PerDiem.class, fieldValues)).thenReturn(inactive);

        List<PerDiem> result = perDiemService.retrieveInactivePerDiem();

        assertEquals(1, result.size());
    }

    @Test
    void testRetrievePreviousPerDiem() {
        PerDiem perDiem = new PerDiem();
        List<PerDiem> expected = Arrays.asList(new PerDiem());
        when(perDiemDao.findSimilarPerDiems(perDiem)).thenReturn(expected);

        List<PerDiem> result = perDiemService.retrievePreviousPerDiem(perDiem);

        assertEquals(1, result.size());
    }

    @Test
    void testHasExistingPerDiem_true() {
        PerDiem perDiem = new PerDiem();
        org.kuali.kfs.module.tem.businessobject.PrimaryDestination dest = new org.kuali.kfs.module.tem.businessobject.PrimaryDestination();
        dest.setPrimaryDestinationName("Test City");
        perDiem.setPrimaryDestination(dest);
        perDiem.setSeasonBeginMonthAndDay("0101");
        perDiem.setEffectiveFromDate(java.sql.Date.valueOf("2024-01-01"));
        List<PerDiem> allPerDiems = new ArrayList();
        allPerDiems.add(perDiem);

        doReturn(allPerDiems).when(businessObjectService).findAll(PerDiem.class);

        boolean result = perDiemService.hasExistingPerDiem(perDiem);

        assertTrue(result);
    }

    @Test
    void testHasExistingPerDiem_false() {
        PerDiem perDiem = new PerDiem();
        perDiem.setId(1);
        PerDiem otherPerDiem = new PerDiem();
        otherPerDiem.setId(2);
        List<PerDiem> allPerDiems = new ArrayList();
        allPerDiems.add(otherPerDiem);

        when(businessObjectService.findAll(PerDiem.class)).thenReturn(allPerDiems);

        boolean result = perDiemService.hasExistingPerDiem(perDiem);

        assertFalse(result);
    }

    @Test
    void testGetNonReimbursableExpenseTotal_alwaysZero() {
        TravelDocument document = mock(TravelDocument.class);

        KualiDecimal result = perDiemService.getNonReimbursableExpenseTotal(document);

        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    void testGetMealsAndIncidentalsGrandTotal() {
        TravelDocument document = mock(TravelDocument.class);
        PerDiemExpense expense1 = mock(PerDiemExpense.class);
        PerDiemExpense expense2 = mock(PerDiemExpense.class);

        when(expense1.getMealsAndIncidentals()).thenReturn(new KualiDecimal(50));
        when(expense2.getMealsAndIncidentals()).thenReturn(new KualiDecimal(75));
        when(document.getPerDiemExpenses()).thenReturn(Arrays.asList(expense1, expense2));

        KualiDecimal result = perDiemService.getMealsAndIncidentalsGrandTotal(document);

        assertEquals(new KualiDecimal(125), result);
    }

    @Test
    void testGetMealsAndIncidentalsGrandTotal_emptyList() {
        TravelDocument document = mock(TravelDocument.class);
        when(document.getPerDiemExpenses()).thenReturn(new java.util.ArrayList());

        KualiDecimal result = perDiemService.getMealsAndIncidentalsGrandTotal(document);

        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    void testGetLodgingGrandTotal_excludesPersonal() {
        TravelDocument document = mock(TravelDocument.class);
        PerDiemExpense personal = mock(PerDiemExpense.class);
        PerDiemExpense business = mock(PerDiemExpense.class);

        when(personal.getPersonal()).thenReturn(true);
        when(business.getPersonal()).thenReturn(false);
        when(business.getLodgingTotal()).thenReturn(new KualiDecimal(100));
        when(document.getPerDiemExpenses()).thenReturn(Arrays.asList(personal, business));

        KualiDecimal result = perDiemService.getLodgingGrandTotal(document);

        assertEquals(new KualiDecimal(100), result);
    }

    @Test
    void testGetMileageTotalGrandTotal() {
        TravelDocument document = mock(TravelDocument.class);
        PerDiemExpense expense1 = mock(PerDiemExpense.class);
        PerDiemExpense expense2 = mock(PerDiemExpense.class);

        when(expense1.getMileageTotal()).thenReturn(new KualiDecimal(30));
        when(expense2.getMileageTotal()).thenReturn(new KualiDecimal(45));
        when(document.getPerDiemExpenses()).thenReturn(Arrays.asList(expense1, expense2));

        KualiDecimal result = perDiemService.getMileageTotalGrandTotal(document);

        assertEquals(new KualiDecimal(75), result);
    }

    @Test
    void testGetDailyTotalGrandTotal() {
        TravelDocument document = mock(TravelDocument.class);
        PerDiemExpense expense1 = mock(PerDiemExpense.class);
        PerDiemExpense expense2 = mock(PerDiemExpense.class);

        when(expense1.getDailyTotal()).thenReturn(new KualiDecimal(200));
        when(expense2.getDailyTotal()).thenReturn(new KualiDecimal(150));
        when(document.getPerDiemExpenses()).thenReturn(Arrays.asList(expense1, expense2));

        KualiDecimal result = perDiemService.getDailyTotalGrandTotal(document);

        assertEquals(new KualiDecimal(350), result);
    }

    @Test
    void testGetMilesGrandTotal() {
        TravelDocument document = mock(TravelDocument.class);
        PerDiemExpense expense1 = mock(PerDiemExpense.class);
        PerDiemExpense expense2 = mock(PerDiemExpense.class);

        when(expense1.getMiles()).thenReturn(100);
        when(expense2.getMiles()).thenReturn(250);
        when(document.getPerDiemExpenses()).thenReturn(Arrays.asList(expense1, expense2));

        Integer result = perDiemService.getMilesGrandTotal(document);

        assertEquals(350, result);
    }

    @Test
    void testGetAllExpenseTotal_includeNonReimbursable() {
        TravelDocument document = mock(TravelDocument.class);
        PerDiemExpense personal = mock(PerDiemExpense.class);
        PerDiemExpense business = mock(PerDiemExpense.class);

        when(personal.getPersonal()).thenReturn(true);
        when(personal.getDailyTotalForDocument(document)).thenReturn(new KualiDecimal(50));
        when(business.getPersonal()).thenReturn(false);
        when(business.getDailyTotalForDocument(document)).thenReturn(new KualiDecimal(100));
        when(document.getPerDiemExpenses()).thenReturn(Arrays.asList(personal, business));

        KualiDecimal result = perDiemService.getAllExpenseTotal(document, true);

        assertEquals(new KualiDecimal(150), result);
    }

    @Test
    void testGetAllExpenseTotal_excludeNonReimbursable() {
        TravelDocument document = mock(TravelDocument.class);
        PerDiemExpense personal = mock(PerDiemExpense.class);
        PerDiemExpense business = mock(PerDiemExpense.class);

        when(personal.getPersonal()).thenReturn(true);
        when(business.getPersonal()).thenReturn(false);
        when(business.getDailyTotalForDocument(document)).thenReturn(new KualiDecimal(100));
        when(document.getPerDiemExpenses()).thenReturn(Arrays.asList(personal, business));

        KualiDecimal result = perDiemService.getAllExpenseTotal(document, false);

        assertEquals(new KualiDecimal(100), result);
    }

    @Test
    void testIsPerDiemHandlingLodging_true() {
        Collection<String> categories = Arrays.asList("lodging=Y", "meals=Y");
        when(parameterService.getParameterValuesAsString(TemParameterConstants.TEM_DOCUMENT.class, TemConstants.TravelParameters.PER_DIEM_CATEGORIES))
                .thenReturn(categories);

        assertTrue(perDiemService.isPerDiemHandlingLodging());
    }

    @Test
    void testIsPerDiemHandlingLodging_false() {
        Collection<String> categories = Arrays.asList("lodging=N", "meals=Y");
        when(parameterService.getParameterValuesAsString(TemParameterConstants.TEM_DOCUMENT.class, TemConstants.TravelParameters.PER_DIEM_CATEGORIES))
                .thenReturn(categories);

        assertFalse(perDiemService.isPerDiemHandlingLodging());
    }

    @Test
    void testIsPerDiemHandlingLodging_noCategoryFound() {
        Collection<String> categories = Arrays.asList("meals=Y");
        when(parameterService.getParameterValuesAsString(TemParameterConstants.TEM_DOCUMENT.class, TemConstants.TravelParameters.PER_DIEM_CATEGORIES))
                .thenReturn(categories);

        assertFalse(perDiemService.isPerDiemHandlingLodging());
    }

    @Test
    void testGetPerDiem_emptyList() {
        when(travelDocumentDao.findEffectivePerDiems(1, new java.sql.Date(0L))).thenReturn(new java.util.ArrayList());

        PerDiem result = perDiemService.getPerDiem(1, new Timestamp(0L), new java.sql.Date(0L));

        assertNull(result);
    }

    @Test
    void testGetPerDiem_singleResult() {
        PerDiem perDiem = new PerDiem();
        List<PerDiem> list = new ArrayList();
        list.add(perDiem);
        java.sql.Date effectiveDate = new java.sql.Date(System.currentTimeMillis());

        when(travelDocumentDao.findEffectivePerDiems(1, effectiveDate)).thenReturn(list);

        PerDiem result = perDiemService.getPerDiem(1, new Timestamp(System.currentTimeMillis()), effectiveDate);

        assertSame(perDiem, result);
    }

    @Test
    void testGetExpenseDetails_returnsNull() {
        TravelDocument document = mock(TravelDocument.class);
        assertNull(perDiemService.getExpenseDetails(document));
    }

    @Test
    void testProcessExpense_doesNothing() {
        TravelDocument document = mock(TravelDocument.class);
        perDiemService.processExpense(document, null);
        verifyNoInteractions(document);
    }

    @Test
    void testUpdateExpense_doesNothing() {
        TravelDocument document = mock(TravelDocument.class);
        perDiemService.updateExpense(document);
        verifyNoInteractions(document);
    }
}
