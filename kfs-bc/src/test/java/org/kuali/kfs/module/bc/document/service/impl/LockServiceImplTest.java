package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionHeader;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionLockStatus;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPosition;
import org.kuali.kfs.module.bc.businessobject.PendingBudgetConstructionAppointmentFunding;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionDao;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionLockDao;
import org.kuali.kfs.module.bc.document.service.BudgetDocumentService;
import org.kuali.kfs.module.bc.BCConstants.LockStatus;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class LockServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionDao budgetConstructionDao;

    @Mock
    private BudgetConstructionLockDao budgetConstructionLockDao;

    @Mock
    private BudgetDocumentService budgetDocumentService;

    @InjectMocks
    private LockServiceImpl service;

    @Test
    public void testLockAccount_nullHeader() {
        BudgetConstructionLockStatus result = service.lockAccount(null, "user1");
        assertEquals(LockStatus.NO_DOOR, result.getLockStatus());
    }

    @Test
    public void testLockAccount_alreadyLockedBySameUser() {
        BudgetConstructionHeader header = new BudgetConstructionHeader();
        header.setBudgetLockUserIdentifier("user1");
        BudgetConstructionLockStatus result = service.lockAccount(header, "user1");
        assertEquals(LockStatus.SUCCESS, result.getLockStatus());
    }

    @Test
    public void testLockAccount_alreadyLockedByOtherUser() {
        BudgetConstructionHeader header = new BudgetConstructionHeader();
        header.setBudgetLockUserIdentifier("otherUser");
        BudgetConstructionLockStatus result = service.lockAccount(header, "user1");
        assertEquals(LockStatus.BY_OTHER, result.getLockStatus());
        assertEquals("otherUser", result.getAccountLockOwner());
    }

    @Test
    public void testIsAccountLocked_headerWithLock() {
        BudgetConstructionHeader header = new BudgetConstructionHeader();
        header.setChartOfAccountsCode("UA");
        header.setAccountNumber("1234567");
        header.setSubAccountNumber("-----");
        header.setUniversityFiscalYear(2024);

        BudgetConstructionHeader freshHeader = new BudgetConstructionHeader();
        freshHeader.setBudgetLockUserIdentifier("user1");

        when(budgetConstructionDao.getByCandidateKey("UA", "1234567", "-----", 2024)).thenReturn(freshHeader);
        assertTrue(service.isAccountLocked(header));
    }

    @Test
    public void testIsAccountLocked_headerWithoutLock() {
        BudgetConstructionHeader header = new BudgetConstructionHeader();
        header.setChartOfAccountsCode("UA");
        header.setAccountNumber("1234567");
        header.setSubAccountNumber("-----");
        header.setUniversityFiscalYear(2024);

        BudgetConstructionHeader freshHeader = new BudgetConstructionHeader();
        freshHeader.setBudgetLockUserIdentifier(null);

        when(budgetConstructionDao.getByCandidateKey("UA", "1234567", "-----", 2024)).thenReturn(freshHeader);
        assertFalse(service.isAccountLocked(header));
    }

    @Test
    public void testIsAccountLocked_headerNotFound() {
        BudgetConstructionHeader header = new BudgetConstructionHeader();
        header.setChartOfAccountsCode("UA");
        header.setAccountNumber("NOPE");
        header.setSubAccountNumber("-----");
        header.setUniversityFiscalYear(2024);

        when(budgetConstructionDao.getByCandidateKey("UA", "NOPE", "-----", 2024)).thenReturn(null);
        assertFalse(service.isAccountLocked(header));
    }

    @Test
    public void testIsAccountLockedByUser_lockedByUser() {
        BudgetConstructionHeader freshHeader = new BudgetConstructionHeader();
        freshHeader.setBudgetLockUserIdentifier("user1");
        when(budgetConstructionDao.getByCandidateKey("UA", "1234567", "-----", 2024)).thenReturn(freshHeader);
        assertTrue(service.isAccountLockedByUser("UA", "1234567", "-----", 2024, "user1"));
    }

    @Test
    public void testIsAccountLockedByUser_lockedByOther() {
        BudgetConstructionHeader freshHeader = new BudgetConstructionHeader();
        freshHeader.setBudgetLockUserIdentifier("otherUser");
        when(budgetConstructionDao.getByCandidateKey("UA", "1234567", "-----", 2024)).thenReturn(freshHeader);
        assertFalse(service.isAccountLockedByUser("UA", "1234567", "-----", 2024, "user1"));
    }

    @Test
    public void testIsAccountLockedByUser_notLocked() {
        BudgetConstructionHeader freshHeader = new BudgetConstructionHeader();
        freshHeader.setBudgetLockUserIdentifier(null);
        when(budgetConstructionDao.getByCandidateKey("UA", "1234567", "-----", 2024)).thenReturn(freshHeader);
        assertFalse(service.isAccountLockedByUser("UA", "1234567", "-----", 2024, "user1"));
    }

    @Test
    public void testIsAccountLockedByUser_headerNotFound() {
        when(budgetConstructionDao.getByCandidateKey("UA", "1234567", "-----", 2024)).thenReturn(null);
        assertFalse(service.isAccountLockedByUser("UA", "1234567", "-----", 2024, "user1"));
    }

    @Test
    public void testUnlockAccount_nullHeader() {
        assertEquals(LockStatus.NO_DOOR, service.unlockAccount(null));
    }

    @Test
    public void testUnlockAccount_alreadyUnlocked() {
        BudgetConstructionHeader header = new BudgetConstructionHeader();
        header.setBudgetLockUserIdentifier(null);
        assertEquals(LockStatus.SUCCESS, service.unlockAccount(header));
    }

    @Test
    public void testIsAccountLocked_appointmentFundingOverload() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        BudgetConstructionHeader header = new BudgetConstructionHeader();
        header.setChartOfAccountsCode("UA");
        header.setAccountNumber("1234567");
        header.setSubAccountNumber("-----");
        header.setUniversityFiscalYear(2024);
        when(budgetDocumentService.getBudgetConstructionHeader(funding)).thenReturn(header);

        BudgetConstructionHeader freshHeader = new BudgetConstructionHeader();
        freshHeader.setBudgetLockUserIdentifier(null);
        when(budgetConstructionDao.getByCandidateKey("UA", "1234567", "-----", 2024)).thenReturn(freshHeader);

        assertFalse(service.isAccountLocked(funding));
    }

}
