package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionOrganizationReportsService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.PersistenceService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionReportsServiceHelperImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private PersistenceService persistenceServiceOjb;

    @InjectMocks
    private BudgetConstructionReportsServiceHelperImpl service;

    @Test
    public void testGetObjectCode_found() {
        ObjectCode expected = new ObjectCode();
        when(businessObjectService.findByPrimaryKey(eq(ObjectCode.class), any(Map.class))).thenReturn(expected);
        ObjectCode result = service.getObjectCode(2024, "UA", "5000");
        assertSame(expected, result);
    }

    @Test
    public void testGetObjectCode_notFound() {
        when(businessObjectService.findByPrimaryKey(eq(ObjectCode.class), any(Map.class))).thenReturn(null);
        ObjectCode result = service.getObjectCode(2024, "UA", "5000");
        assertNull(result);
    }

    @Test
    public void testGetDataForBuildingReports_withSearchCriteria() {
        Map<String, Object> criteria = new HashMap<String, Object>();
        List<String> orderList = new ArrayList<String>();
        List expected = new ArrayList();
        when(budgetConstructionOrganizationReportsService.getBySearchCriteriaOrderByList(any(Class.class), eq(criteria), eq(orderList)))
                .thenReturn(expected);
        Collection result = service.getDataForBuildingReports(Object.class, criteria, orderList);
        assertSame(expected, result);
    }

    @Test
    public void testGetPersistenceServiceOjb() {
        assertSame(persistenceServiceOjb, service.getPersistenceServiceOjb());
    }
}
