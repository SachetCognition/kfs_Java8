package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.businessobject.MileageRate;
import org.kuali.kfs.module.tem.businessobject.PerDiemExpense;
import org.kuali.kfs.module.tem.dataaccess.TravelDocumentDao;
import org.kuali.kfs.module.tem.document.TravelAuthorizationDocument;
import org.kuali.kfs.module.tem.document.TravelDocument;
import org.kuali.kfs.module.tem.document.TravelReimbursementDocument;
import org.kuali.kfs.module.tem.document.service.AccountingDocumentRelationshipService;
import org.kuali.kfs.module.tem.document.service.MileageRateService;
import org.kuali.kfs.module.tem.document.service.TravelAuthorizationService;
import org.kuali.kfs.module.tem.service.PerDiemService;
import org.kuali.kfs.module.tem.service.TemRoleService;
import org.kuali.kfs.module.tem.service.TravelExpenseService;
import org.kuali.kfs.module.tem.service.TravelService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelDocumentServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelDocumentServiceImpl travelDocumentService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DocumentService documentService;

    @Mock
    private TravelDocumentDao travelDocumentDao;

    @Mock
    private TravelAuthorizationService travelAuthorizationService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private AccountingDocumentRelationshipService accountingDocumentRelationshipService;

    @Mock
    private TemRoleService temRoleService;

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private PerDiemService perDiemService;

    @Mock
    private TravelExpenseService travelExpenseService;

    @Mock
    private TravelService travelService;

    @Mock
    private MileageRateService mileageRateService;

    @Test
    void testCalculateDailyTotal() {
        PerDiemExpense expense = mock(PerDiemExpense.class);
        when(expense.getMileageTotal()).thenReturn(new KualiDecimal(50));
        when(expense.getLodgingTotal()).thenReturn(new KualiDecimal(100));
        when(expense.getMealsAndIncidentals()).thenReturn(new KualiDecimal(75));
        when(expense.getDailyTotal()).thenReturn(new KualiDecimal(225));

        Map<String, KualiDecimal> result = travelDocumentService.calculateDailyTotal(expense);

        assertEquals(new KualiDecimal(50), result.get(TemConstants.MILEAGE_TOTAL_ATTRIBUTE));
        assertEquals(new KualiDecimal(100), result.get(TemConstants.LODGING_TOTAL_ATTRIBUTE));
        assertEquals(new KualiDecimal(75), result.get(TemConstants.MEALS_AND_INC_TOTAL_ATTRIBUTE));
        assertEquals(new KualiDecimal(225), result.get(TemConstants.DAILY_TOTAL));
    }

    @Test
    void testCalculateDailyTotals_multipleExpenses() {
        PerDiemExpense expense1 = mock(PerDiemExpense.class);
        PerDiemExpense expense2 = mock(PerDiemExpense.class);

        when(expense1.getMileageTotal()).thenReturn(new KualiDecimal(50));
        when(expense1.getLodgingTotal()).thenReturn(new KualiDecimal(100));
        when(expense1.getMealsAndIncidentals()).thenReturn(new KualiDecimal(75));
        when(expense1.getDailyTotal()).thenReturn(new KualiDecimal(225));

        when(expense2.getMileageTotal()).thenReturn(new KualiDecimal(30));
        when(expense2.getLodgingTotal()).thenReturn(new KualiDecimal(80));
        when(expense2.getMealsAndIncidentals()).thenReturn(new KualiDecimal(60));
        when(expense2.getDailyTotal()).thenReturn(new KualiDecimal(170));

        List<PerDiemExpense> expenses = new ArrayList();
        expenses.add(expense1);
        expenses.add(expense2);

        List<Map<String, KualiDecimal>> result = travelDocumentService.calculateDailyTotals(expenses);

        assertEquals(2, result.size());
        assertEquals(new KualiDecimal(50), result.get(0).get(TemConstants.MILEAGE_TOTAL_ATTRIBUTE));
        assertEquals(new KualiDecimal(30), result.get(1).get(TemConstants.MILEAGE_TOTAL_ATTRIBUTE));
    }

    @Test
    void testCalculateDailyTotals_emptyList() {
        List<PerDiemExpense> expenses = new ArrayList();

        List<Map<String, KualiDecimal>> result = travelDocumentService.calculateDailyTotals(expenses);

        assertTrue(result.isEmpty());
    }

    @Test
    void testCalculateProratePercentage_notProrated() {
        PerDiemExpense expense = mock(PerDiemExpense.class);
        when(expense.isProrated()).thenReturn(false);

        Integer result = travelDocumentService.calculateProratePercentage(expense, "P", new Timestamp(System.currentTimeMillis()));

        assertEquals(100, result);
    }

    @Test
    void testCalculateProratePercentage_proratedWithPercentageMethod() {
        PerDiemExpense expense = mock(PerDiemExpense.class);
        when(expense.isProrated()).thenReturn(true);
        Timestamp tripEnd = new Timestamp(System.currentTimeMillis());

        when(parameterService.getParameterValueAsString(
                TravelAuthorizationDocument.class,
                TemConstants.TravelAuthorizationParameters.FIRST_AND_LAST_DAY_PER_DIEM_PERCENTAGE,
                "100")).thenReturn("75");

        Integer result = travelDocumentService.calculateProratePercentage(expense, TemConstants.PERCENTAGE, tripEnd);

        assertEquals(75, result);
    }

    @Test
    void testFindAuthorizationDocuments_emptyIds() {
        String travelDocId = "12345";
        List<String> emptyIds = new ArrayList<String>();

        when(travelDocumentDao.findDocumentNumbers(TravelAuthorizationDocument.class, travelDocId))
                .thenReturn(emptyIds);

        List<TravelAuthorizationDocument> result = travelDocumentService.findAuthorizationDocuments(travelDocId);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindReimbursementDocuments_emptyIds() {
        String travelDocId = "12345";
        List<String> emptyIds = new ArrayList<String>();

        when(travelDocumentDao.findDocumentNumbers(TravelReimbursementDocument.class, travelDocId))
                .thenReturn(emptyIds);

        List<TravelReimbursementDocument> result = travelDocumentService.findReimbursementDocuments(travelDocId);

        assertTrue(result.isEmpty());
    }

    @Test
    void testSetPerDiemMealsAndIncidentals_basicSetup() {
        PerDiemExpense expense = new PerDiemExpense();
        org.kuali.kfs.module.tem.businessobject.PerDiem perDiem = new org.kuali.kfs.module.tem.businessobject.PerDiem();
        perDiem.setBreakfast(new KualiDecimal(10));
        perDiem.setLunch(new KualiDecimal(15));
        perDiem.setDinner(new KualiDecimal(25));
        perDiem.setIncidentals(new KualiDecimal(5));

        org.kuali.kfs.module.tem.businessobject.TripType tripType = new org.kuali.kfs.module.tem.businessobject.TripType();

        Timestamp tripEnd = new Timestamp(System.currentTimeMillis() + 86400000L);

        travelDocumentService.setPerDiemMealsAndIncidentals(expense, perDiem, tripType, tripEnd, false);

        assertEquals(new KualiDecimal(10), expense.getBreakfastValue());
        assertEquals(new KualiDecimal(15), expense.getLunchValue());
        assertEquals(new KualiDecimal(25), expense.getDinnerValue());
        assertEquals(new KualiDecimal(5), expense.getIncidentalsValue());
    }

    @Test
    void testFindDocumentsRelatedTo_emptyResults() throws Exception {
        TravelDocument doc = mock(TravelDocument.class);
        when(doc.getDocumentNumber()).thenReturn("DOC001");
        when(accountingDocumentRelationshipService.getAllRelatedDocumentNumbers("DOC001"))
                .thenReturn(new java.util.HashSet());

        Map<String, List<org.kuali.rice.krad.document.Document>> result = travelDocumentService.getDocumentsRelatedTo(doc);

        assertNotNull(result);
    }
}
