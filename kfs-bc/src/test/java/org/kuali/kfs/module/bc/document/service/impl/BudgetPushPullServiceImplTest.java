package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetOrganizationPushPullDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetPushPullServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetOrganizationPushPullDao budgetOrganizationPushPullDao;

    @InjectMocks
    private BudgetPushPullServiceImpl service;

    @Test
    public void testPullupSelectedOrganizationDocuments() {
        service.pullupSelectedOrganizationDocuments("user1", 2024, "UA", "UNIV");
        verify(budgetOrganizationPushPullDao).pullupSelectedOrganizationDocuments("user1", 2024, "UA", "UNIV");
    }

    @Test
    public void testPushdownSelectedOrganizationDocuments() {
        service.pushdownSelectedOrganizationDocuments("user1", 2024, "UA", "UNIV");
        verify(budgetOrganizationPushPullDao).pushdownSelectedOrganizationDocuments("user1", 2024, "UA", "UNIV");
    }

    @Test
    public void testBuildPullUpBudgetedDocuments() {
        when(budgetOrganizationPushPullDao.buildPullUpBudgetedDocuments("user1", 2024, "UA", "UNIV")).thenReturn(5);
        int result = service.buildPullUpBudgetedDocuments("user1", 2024, "UA", "UNIV");
        assertEquals(5, result);
    }

    @Test
    public void testBuildPushDownBudgetedDocuments() {
        when(budgetOrganizationPushPullDao.buildPushDownBudgetedDocuments("user1", 2024, "UA", "UNIV")).thenReturn(3);
        int result = service.buildPushDownBudgetedDocuments("user1", 2024, "UA", "UNIV");
        assertEquals(3, result);
    }

    @Test
    public void testBuildPullUpBudgetedDocuments_zero() {
        when(budgetOrganizationPushPullDao.buildPullUpBudgetedDocuments("user1", 2024, "UA", "UNIV")).thenReturn(0);
        assertEquals(0, service.buildPullUpBudgetedDocuments("user1", 2024, "UA", "UNIV"));
    }
}
