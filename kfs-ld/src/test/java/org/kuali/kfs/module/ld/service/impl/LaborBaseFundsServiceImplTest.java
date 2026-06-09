package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.AccountStatusBaseFunds;
import org.kuali.kfs.module.ld.dataaccess.LaborBaseFundsDao;
import org.kuali.kfs.module.ld.service.LaborCalculatedSalaryFoundationTrackerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaborBaseFundsServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborBaseFundsDao laborBaseFundsDao;

    @Mock
    private LaborCalculatedSalaryFoundationTrackerService laborCalculatedSalaryFoundationTrackerService;

    @InjectMocks
    private LaborBaseFundsServiceImpl laborBaseFundsService;

    @Test
    void testFindLaborBaseFunds() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("chartOfAccountsCode", "BL");
        List<AccountStatusBaseFunds> expected = new ArrayList<AccountStatusBaseFunds>();
        expected.add(new AccountStatusBaseFunds());

        when(laborBaseFundsDao.findLaborBaseFunds(fieldValues, false)).thenReturn(expected);

        List<AccountStatusBaseFunds> result = laborBaseFundsService.findLaborBaseFunds(fieldValues, false);
        assertEquals(expected, result);
        verify(laborBaseFundsDao).findLaborBaseFunds(fieldValues, false);
    }

    @Test
    void testFindLaborBaseFunds_consolidated() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        List<AccountStatusBaseFunds> expected = new ArrayList<AccountStatusBaseFunds>();

        when(laborBaseFundsDao.findLaborBaseFunds(fieldValues, true)).thenReturn(expected);

        List<AccountStatusBaseFunds> result = laborBaseFundsService.findLaborBaseFunds(fieldValues, true);
        assertEquals(expected, result);
        verify(laborBaseFundsDao).findLaborBaseFunds(fieldValues, true);
    }

    @Test
    void testFindAccountStatusBaseFundsWithCSFTracker_noMatchingTrackers() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        AccountStatusBaseFunds baseFund = new AccountStatusBaseFunds();
        baseFund.setAccountNumber("1234567");
        List<AccountStatusBaseFunds> baseFunds = new ArrayList<AccountStatusBaseFunds>();
        baseFunds.add(baseFund);

        List<AccountStatusBaseFunds> csfTrackers = new ArrayList<AccountStatusBaseFunds>();

        when(laborBaseFundsDao.findLaborBaseFunds(fieldValues, false)).thenReturn(baseFunds);
        when(laborCalculatedSalaryFoundationTrackerService.findCSFTrackersAsAccountStatusBaseFunds(fieldValues, false)).thenReturn(csfTrackers);

        List<AccountStatusBaseFunds> result = laborBaseFundsService.findAccountStatusBaseFundsWithCSFTracker(fieldValues, false);
        assertEquals(1, result.size());
    }

    @Test
    void testFindAccountStatusBaseFundsWithCSFTracker_matchingTracker() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();

        AccountStatusBaseFunds baseFund = new AccountStatusBaseFunds();
        baseFund.setAccountNumber("1234567");
        baseFund.setChartOfAccountsCode("BL");
        List<AccountStatusBaseFunds> baseFunds = new ArrayList<AccountStatusBaseFunds>();
        baseFunds.add(baseFund);

        AccountStatusBaseFunds tracker = new AccountStatusBaseFunds();
        tracker.setAccountNumber("1234567");
        tracker.setChartOfAccountsCode("BL");
        tracker.setCsfAmount(new KualiDecimal(500));
        List<AccountStatusBaseFunds> csfTrackers = new ArrayList<AccountStatusBaseFunds>();
        csfTrackers.add(tracker);

        when(laborBaseFundsDao.findLaborBaseFunds(fieldValues, false)).thenReturn(baseFunds);
        when(laborCalculatedSalaryFoundationTrackerService.findCSFTrackersAsAccountStatusBaseFunds(fieldValues, false)).thenReturn(csfTrackers);

        List<AccountStatusBaseFunds> result = laborBaseFundsService.findAccountStatusBaseFundsWithCSFTracker(fieldValues, false);
        assertEquals(1, result.size());
    }

    @Test
    void testFindAccountStatusBaseFundsWithCSFTracker_nonMatchingTracker() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        List<AccountStatusBaseFunds> baseFunds = new ArrayList<AccountStatusBaseFunds>();

        AccountStatusBaseFunds tracker = new AccountStatusBaseFunds();
        tracker.setAccountNumber("9999999");
        tracker.setCsfAmount(new KualiDecimal(100));
        List<AccountStatusBaseFunds> csfTrackers = new ArrayList<AccountStatusBaseFunds>();
        csfTrackers.add(tracker);

        when(laborBaseFundsDao.findLaborBaseFunds(fieldValues, false)).thenReturn(baseFunds);
        when(laborCalculatedSalaryFoundationTrackerService.findCSFTrackersAsAccountStatusBaseFunds(fieldValues, false)).thenReturn(csfTrackers);

        List<AccountStatusBaseFunds> result = laborBaseFundsService.findAccountStatusBaseFundsWithCSFTracker(fieldValues, false);
        assertEquals(1, result.size());
        assertEquals(tracker, result.get(0));
    }

    @Test
    void testFindAccountStatusBaseFundsWithCSFTracker_emptyResults() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();

        when(laborBaseFundsDao.findLaborBaseFunds(fieldValues, true)).thenReturn(new ArrayList<AccountStatusBaseFunds>());
        when(laborCalculatedSalaryFoundationTrackerService.findCSFTrackersAsAccountStatusBaseFunds(fieldValues, true)).thenReturn(new ArrayList<AccountStatusBaseFunds>());

        List<AccountStatusBaseFunds> result = laborBaseFundsService.findAccountStatusBaseFundsWithCSFTracker(fieldValues, true);
        assertTrue(result.isEmpty());
    }
}
