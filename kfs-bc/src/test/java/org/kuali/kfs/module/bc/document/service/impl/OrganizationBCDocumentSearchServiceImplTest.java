package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.OrganizationBCDocumentSearchDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class OrganizationBCDocumentSearchServiceImplTest extends KfsUnitTestBase {

    @Mock
    private OrganizationBCDocumentSearchDao organizationBCDocumentSearchDao;

    @InjectMocks
    private OrganizationBCDocumentSearchServiceImpl service;

    @Test
    public void testBuildAccountSelectPullList() {
        when(organizationBCDocumentSearchDao.buildAccountSelectPullList("user1", 2024)).thenReturn(10);
        assertEquals(10, service.buildAccountSelectPullList("user1", 2024));
    }

    @Test
    public void testBuildBudgetedAccountsAbovePointsOfView() {
        when(organizationBCDocumentSearchDao.buildBudgetedAccountsAbovePointsOfView("user1", 2024, "UA", "UNIV"))
                .thenReturn(5);
        assertEquals(5, service.buildBudgetedAccountsAbovePointsOfView("user1", 2024, "UA", "UNIV"));
    }

    @Test
    public void testBuildAccountManagerDelegateList() {
        when(organizationBCDocumentSearchDao.buildAccountManagerDelegateList("user1", 2024)).thenReturn(3);
        assertEquals(3, service.buildAccountManagerDelegateList("user1", 2024));
    }

    @Test
    public void testCleanAccountSelectPullList() {
        service.cleanAccountSelectPullList("user1", 2024);
        verify(organizationBCDocumentSearchDao).cleanAccountSelectPullList("user1");
    }

    @Test
    public void testBuildAccountSelectPullList_zero() {
        when(organizationBCDocumentSearchDao.buildAccountSelectPullList("user1", 2024)).thenReturn(0);
        assertEquals(0, service.buildAccountSelectPullList("user1", 2024));
    }
}
