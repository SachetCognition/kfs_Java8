package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.ExpenseTypeObjectCode;
import org.kuali.kfs.module.tem.dataaccess.ExpenseTypeObjectCodeDao;
import org.kuali.kfs.module.tem.service.TravelService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelExpenseServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelExpenseServiceImpl travelExpenseService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private ExpenseTypeObjectCodeDao expenseTypeObjectCodeDao;

    @Mock
    private TravelService travelService;

    @Test
    void testGetExpenseType_blankExpenseThrows() {
        try {
            travelExpenseService.getExpenseType("", "TA", "IN", "EMP");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    void testGetExpenseType_nullExpenseThrows() {
        try {
            travelExpenseService.getExpenseType(null, "TA", "IN", "EMP");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    void testGetExpenseType_noMatchesReturnsNull() {
        Set<String> parentDocTypes = new HashSet();
        parentDocTypes.add("TT");
        parentDocTypes.add("TA");

        when(travelService.getParentDocumentTypeNames("TA")).thenReturn(parentDocTypes);
        when(expenseTypeObjectCodeDao.findMatchingExpenseTypeObjectCodes("AIR", parentDocTypes, "IN", "EMP"))
                .thenReturn(new java.util.ArrayList());

        ExpenseTypeObjectCode result = travelExpenseService.getExpenseType("AIR", "TA", "IN", "EMP");

        assertNull(result);
    }

    @Test
    void testGetExpenseType_singleMatchReturned() {
        Set<String> parentDocTypes = new HashSet();
        parentDocTypes.add("TT");
        parentDocTypes.add("TA");

        ExpenseTypeObjectCode etoc = new ExpenseTypeObjectCode();
        etoc.setExpenseTypeCode("AIR");
        etoc.setDocumentTypeName("TA");
        etoc.setTripTypeCode("IN");
        etoc.setTravelerTypeCode("EMP");

        List<ExpenseTypeObjectCode> matches = new ArrayList();
        matches.add(etoc);

        when(travelService.getParentDocumentTypeNames("TA")).thenReturn(parentDocTypes);
        when(expenseTypeObjectCodeDao.findMatchingExpenseTypeObjectCodes("AIR", parentDocTypes, "IN", "EMP"))
                .thenReturn(matches);

        ExpenseTypeObjectCode result = travelExpenseService.getExpenseType("AIR", "TA", "IN", "EMP");

        assertSame(etoc, result);
    }

    @Test
    void testGetExpenseType_nullListReturnsNull() {
        Set<String> parentDocTypes = new HashSet();
        parentDocTypes.add("TT");

        when(travelService.getParentDocumentTypeNames("TA")).thenReturn(parentDocTypes);
        when(expenseTypeObjectCodeDao.findMatchingExpenseTypeObjectCodes("AIR", parentDocTypes, "IN", "EMP"))
                .thenReturn(null);

        ExpenseTypeObjectCode result = travelExpenseService.getExpenseType("AIR", "TA", "IN", "EMP");

        assertNull(result);
    }
}
