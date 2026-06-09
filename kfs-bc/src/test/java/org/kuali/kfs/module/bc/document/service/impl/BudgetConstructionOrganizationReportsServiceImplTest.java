package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionOrganizationReports;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionOrganizationReportsDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionOrganizationReportsServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionOrganizationReportsDao budgetConstructionOrganizationReportsDao;

    @InjectMocks
    private BudgetConstructionOrganizationReportsServiceImpl service;

    @Test
    public void testGetBySearchCriteria() {
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("chartOfAccountsCode", "UA");
        List<BudgetConstructionOrganizationReports> expected = new ArrayList<BudgetConstructionOrganizationReports>();
        when(budgetConstructionOrganizationReportsDao.getBySearchCriteria(BudgetConstructionOrganizationReports.class, criteria))
                .thenReturn(expected);

        List result = service.getBySearchCriteria(BudgetConstructionOrganizationReports.class, criteria);
        assertSame(expected, result);
    }

    @Test
    public void testGetBySearchCriteriaOrderByList() {
        Map<String, Object> criteria = new HashMap<String, Object>();
        List<String> orderList = new ArrayList<String>();
        orderList.add("chartOfAccountsCode");
        List<BudgetConstructionOrganizationReports> expected = new ArrayList<BudgetConstructionOrganizationReports>();
        when(budgetConstructionOrganizationReportsDao.getBySearchCriteriaWithOrderByList(
                BudgetConstructionOrganizationReports.class, criteria, orderList)).thenReturn(expected);

        List result = service.getBySearchCriteriaOrderByList(BudgetConstructionOrganizationReports.class, criteria, orderList);
        assertSame(expected, result);
    }

    @Test
    public void testGetActiveChildOrgs_blankChart() {
        try {
            service.getActiveChildOrgs("", "UNIV");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testGetActiveChildOrgs_nullChart() {
        try {
            service.getActiveChildOrgs(null, "UNIV");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testGetActiveChildOrgs_blankOrgCode() {
        try {
            service.getActiveChildOrgs("UA", "");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testGetActiveChildOrgs_validInputs() {
        List<BudgetConstructionOrganizationReports> expected = new ArrayList<BudgetConstructionOrganizationReports>();
        when(budgetConstructionOrganizationReportsDao.getActiveChildOrgs("UA", "UNIV")).thenReturn(expected);

        List result = service.getActiveChildOrgs("UA", "UNIV");
        assertSame(expected, result);
        verify(budgetConstructionOrganizationReportsDao).getActiveChildOrgs("UA", "UNIV");
    }
}
