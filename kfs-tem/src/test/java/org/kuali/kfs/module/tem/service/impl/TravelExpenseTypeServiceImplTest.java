package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.businessobject.TravelCompanyCode;
import org.kuali.kfs.module.tem.TemPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.mockito.Mock;

class TravelExpenseTypeServiceImplTest extends KfsUnitTestBase {

    private TravelExpenseTypeServiceImpl travelExpenseTypeService;

    @Mock
    private KeyValuesService keyValuesService;

    @BeforeEach
    void setUp() {
        travelExpenseTypeService = spy(new TravelExpenseTypeServiceImpl());
        doReturn(keyValuesService).when(travelExpenseTypeService).getKeyValuesService();
    }

    @Test
    void testGetCompanyNameMapFrom_withResults() {
        Map<String, Object> criteria = new HashMap();
        criteria.put(TemPropertyConstants.EXPENSE_TYPE_OBJECT_CODE, "AIR");

        TravelCompanyCode company = new TravelCompanyCode();
        company.setName("Delta Airlines");

        List<TravelCompanyCode> companies = new ArrayList<TravelCompanyCode>();
        companies.add(company);

        doReturn(companies).when(keyValuesService).findMatching(TravelCompanyCode.class, criteria);

        Map<String, String> result = travelExpenseTypeService.getCompanyNameMapFrom("AIR");

        assertEquals(1, result.size());
        assertTrue(result.containsKey("Delta Airlines"));
    }

    @Test
    void testGetCompanyNameMapFrom_empty() {
        Map<String, Object> criteria = new HashMap();
        criteria.put(TemPropertyConstants.EXPENSE_TYPE_OBJECT_CODE, "AIR");

        doReturn(new ArrayList()).when(keyValuesService).findMatching(TravelCompanyCode.class, criteria);

        Map<String, String> result = travelExpenseTypeService.getCompanyNameMapFrom("AIR");

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCompanyNamePairsFrom_withActiveResults() {
        Map<String, Object> criteria = new HashMap();
        criteria.put(TemPropertyConstants.EXPENSE_TYPE_OBJECT_CODE, "AIR");

        TravelCompanyCode company = new TravelCompanyCode();
        company.setName("Delta Airlines");
        company.setActive(true);

        List<TravelCompanyCode> companies = new ArrayList<TravelCompanyCode>();
        companies.add(company);

        doReturn(companies).when(keyValuesService).findMatching(TravelCompanyCode.class, criteria);

        List<ConcreteKeyValue> result = travelExpenseTypeService.getCompanyNamePairsFrom("AIR");

        assertEquals(2, result.size()); // blank entry + Delta
        assertEquals("", result.get(0).getKey());
        assertEquals("Delta Airlines", result.get(1).getKey());
    }

    @Test
    void testGetCompanyNamePairsFrom_inactiveFiltered() {
        Map<String, Object> criteria = new HashMap();
        criteria.put(TemPropertyConstants.EXPENSE_TYPE_OBJECT_CODE, "AIR");

        TravelCompanyCode company = new TravelCompanyCode();
        company.setName("Defunct Airlines");
        company.setActive(false);

        List<TravelCompanyCode> companies = new ArrayList<TravelCompanyCode>();
        companies.add(company);

        doReturn(companies).when(keyValuesService).findMatching(TravelCompanyCode.class, criteria);

        List<ConcreteKeyValue> result = travelExpenseTypeService.getCompanyNamePairsFrom("AIR");

        assertEquals(1, result.size()); // only blank entry
        assertEquals("", result.get(0).getKey());
    }
}
