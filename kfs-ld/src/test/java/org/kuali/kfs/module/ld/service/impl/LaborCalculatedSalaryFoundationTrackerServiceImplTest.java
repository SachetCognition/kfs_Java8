package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.AccountStatusBaseFunds;
import org.kuali.kfs.module.ld.businessobject.EmployeeFunding;
import org.kuali.kfs.module.ld.businessobject.LaborCalculatedSalaryFoundationTracker;
import org.kuali.kfs.module.ld.dataaccess.LaborCalculatedSalaryFoundationTrackerDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.LookupService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaborCalculatedSalaryFoundationTrackerServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborCalculatedSalaryFoundationTrackerDao laborCalculatedSalaryFoundationTrackerDao;

    @Mock
    private LookupService lookupService;

    @InjectMocks
    private LaborCalculatedSalaryFoundationTrackerServiceImpl service;

    @Test
    void testFindCSFTracker() {
        Map<String, Object> fieldValues = new HashMap<>();
        List<LaborCalculatedSalaryFoundationTracker> expected = new ArrayList<>();
        expected.add(new LaborCalculatedSalaryFoundationTracker());

        when(laborCalculatedSalaryFoundationTrackerDao.findCSFTrackers(fieldValues, false)).thenReturn(expected);

        List<LaborCalculatedSalaryFoundationTracker> result = service.findCSFTracker(fieldValues, false);
        assertEquals(expected, result);
        verify(laborCalculatedSalaryFoundationTrackerDao).findCSFTrackers(fieldValues, false);
    }

    @Test
    void testFindCSFTracker_consolidated() {
        Map<String, Object> fieldValues = new HashMap<>();
        List<LaborCalculatedSalaryFoundationTracker> expected = new ArrayList<>();

        when(laborCalculatedSalaryFoundationTrackerDao.findCSFTrackers(fieldValues, true)).thenReturn(expected);

        List<LaborCalculatedSalaryFoundationTracker> result = service.findCSFTracker(fieldValues, true);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindCSFTrackersAsAccountStatusBaseFunds() {
        Map<String, Object> fieldValues = new HashMap<>();
        List<AccountStatusBaseFunds> expected = new ArrayList<>();
        expected.add(new AccountStatusBaseFunds());

        when(laborCalculatedSalaryFoundationTrackerDao.findCSFTrackersAsAccountStatusBaseFunds(fieldValues, false)).thenReturn(expected);

        List<AccountStatusBaseFunds> result = service.findCSFTrackersAsAccountStatusBaseFunds(fieldValues, false);
        assertEquals(expected, result);
    }

    @Test
    void testFindCSFTrackersAsEmployeeFunding() {
        Map<String, Object> fieldValues = new HashMap<>();
        List<EmployeeFunding> expected = new ArrayList<>();
        expected.add(new EmployeeFunding());

        when(laborCalculatedSalaryFoundationTrackerDao.findCSFTrackersAsEmployeeFunding(fieldValues, true)).thenReturn(expected);

        List<EmployeeFunding> result = service.findCSFTrackersAsEmployeeFunding(fieldValues, true);
        assertEquals(expected, result);
    }
}
