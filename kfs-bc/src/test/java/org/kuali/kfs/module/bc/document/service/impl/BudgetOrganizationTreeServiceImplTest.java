package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.any;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPullup;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionDao;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetPullupDao;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionOrganizationReportsService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.PersistenceService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetOrganizationTreeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private BudgetConstructionDao budgetConstructionDao;

    @Mock
    private BudgetPullupDao budgetPullupDao;

    @Mock
    private PersistenceService persistenceServiceOjb;

    @InjectMocks
    private BudgetOrganizationTreeServiceImpl service;

    @Test
    public void testCleanPullup() {
        service.cleanPullup("user1");
        verify(budgetPullupDao).cleanGeneralLedgerObjectSummaryTable("user1");
        verify(persistenceServiceOjb).clearCache();
    }

    @Test
    public void testResetPullFlag_emptyResults() {
        List<BudgetConstructionPullup> empty = Collections.emptyList();
        when(budgetConstructionDao.getBudgetConstructionPullupFlagSetByUserId("user1"))
                .thenReturn(empty);
        service.resetPullFlag("user1");
        verify(businessObjectService, never()).save(any(List.class));
    }

    @Test
    public void testResetPullFlag_withResults() {
        BudgetConstructionPullup pullup = mock(BudgetConstructionPullup.class);
        List<BudgetConstructionPullup> results = new ArrayList<BudgetConstructionPullup>();
        results.add(pullup);
        when(budgetConstructionDao.getBudgetConstructionPullupFlagSetByUserId("user1"))
                .thenReturn(results);
        service.resetPullFlag("user1");
        verify(pullup).setPullFlag(anyInt());
        verify(businessObjectService).save(results);
    }

    @Test
    public void testResetPullFlag_blankPrincipalId() {
        try {
            service.resetPullFlag("");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testGetPullupChildOrgs_blankPrincipalId() {
        try {
            service.getPullupChildOrgs("", "UA", "UNIV");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testGetPullupChildOrgs_blankChart() {
        try {
            service.getPullupChildOrgs("user1", "", "UNIV");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testGetPullupChildOrgs_blankOrgCode() {
        try {
            service.getPullupChildOrgs("user1", "UA", "");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testGetPullupChildOrgs_validInputs() {
        List<BudgetConstructionPullup> expected = new ArrayList<BudgetConstructionPullup>();
        when(budgetConstructionDao.getBudgetConstructionPullupChildOrgs("user1", "UA", "UNIV"))
                .thenReturn(expected);
        List<BudgetConstructionPullup> result = service.getPullupChildOrgs("user1", "UA", "UNIV");
        assertSame(expected, result);
    }

    @Test
    public void testGetSelectedOrgs_blankPrincipalId() {
        try {
            service.getSelectedOrgs("");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testGetSelectedOrgs_valid() {
        List<BudgetConstructionPullup> expected = new ArrayList<BudgetConstructionPullup>();
        when(budgetConstructionDao.getBudgetConstructionPullupFlagSetByUserId("user1"))
                .thenReturn(expected);
        assertSame(expected, service.getSelectedOrgs("user1"));
    }
}
