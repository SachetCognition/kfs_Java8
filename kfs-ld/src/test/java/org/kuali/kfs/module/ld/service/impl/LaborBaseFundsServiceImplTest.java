package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.AccountStatusBaseFunds;
import org.kuali.kfs.module.ld.dataaccess.LaborBaseFundsDao;
import org.kuali.kfs.module.ld.service.LaborCalculatedSalaryFoundationTrackerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LaborBaseFundsServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborBaseFundsDao laborBaseFundsDao;

    @Mock
    private LaborCalculatedSalaryFoundationTrackerService laborCalculatedSalaryFoundationTrackerService;

    @InjectMocks
    private LaborBaseFundsServiceImpl laborBaseFundsService;

    private Map<String, String> fieldValues;

    @BeforeEach
    void setUp() {
        fieldValues = new HashMap<>();
        fieldValues.put("universityFiscalYear", "2024");
        fieldValues.put("chartOfAccountsCode", "BL");
    }

    @Test
    void testFindLaborBaseFundsDelegatesToDao() {
        List<AccountStatusBaseFunds> expected = new ArrayList<>();
        when(laborBaseFundsDao.findLaborBaseFunds(fieldValues, false)).thenReturn(expected);

        List<AccountStatusBaseFunds> result = laborBaseFundsService.findLaborBaseFunds(fieldValues, false);

        assertThat(result).isSameAs(expected);
        verify(laborBaseFundsDao).findLaborBaseFunds(fieldValues, false);
    }

    @Test
    void testFindLaborBaseFundsConsolidated() {
        List<AccountStatusBaseFunds> expected = new ArrayList<>();
        when(laborBaseFundsDao.findLaborBaseFunds(fieldValues, true)).thenReturn(expected);

        List<AccountStatusBaseFunds> result = laborBaseFundsService.findLaborBaseFunds(fieldValues, true);

        assertThat(result).isSameAs(expected);
        verify(laborBaseFundsDao).findLaborBaseFunds(fieldValues, true);
    }

    @Test
    void testFindAccountStatusBaseFundsWithCSFTrackerMergesResults() {
        AccountStatusBaseFunds baseFund = new AccountStatusBaseFunds();
        baseFund.setAccountNumber("1234567");
        baseFund.setChartOfAccountsCode("BL");
        baseFund.setFinancialObjectCode("5000");

        AccountStatusBaseFunds csfTracker = new AccountStatusBaseFunds();
        csfTracker.setAccountNumber("1234567");
        csfTracker.setChartOfAccountsCode("BL");
        csfTracker.setFinancialObjectCode("5000");
        csfTracker.setCsfAmount(new KualiDecimal(1000));

        List<AccountStatusBaseFunds> baseFunds = new ArrayList<>(List.of(baseFund));
        List<AccountStatusBaseFunds> csfTrackers = new ArrayList<>(List.of(csfTracker));

        when(laborBaseFundsDao.findLaborBaseFunds(fieldValues, false)).thenReturn(baseFunds);
        when(laborCalculatedSalaryFoundationTrackerService.findCSFTrackersAsAccountStatusBaseFunds(fieldValues, false))
                .thenReturn(csfTrackers);

        List<AccountStatusBaseFunds> result = laborBaseFundsService.findAccountStatusBaseFundsWithCSFTracker(fieldValues, false);

        assertThat(result).isNotEmpty();
        verify(laborBaseFundsDao).findLaborBaseFunds(fieldValues, false);
        verify(laborCalculatedSalaryFoundationTrackerService).findCSFTrackersAsAccountStatusBaseFunds(fieldValues, false);
    }

    @Test
    void testFindAccountStatusBaseFundsWithCSFTrackerAddsNewTrackers() {
        AccountStatusBaseFunds csfTracker = new AccountStatusBaseFunds();
        csfTracker.setAccountNumber("9999999");
        csfTracker.setChartOfAccountsCode("BL");
        csfTracker.setFinancialObjectCode("6000");
        csfTracker.setCsfAmount(new KualiDecimal(500));

        List<AccountStatusBaseFunds> baseFunds = new ArrayList<>();
        List<AccountStatusBaseFunds> csfTrackers = new ArrayList<>(List.of(csfTracker));

        when(laborBaseFundsDao.findLaborBaseFunds(fieldValues, false)).thenReturn(baseFunds);
        when(laborCalculatedSalaryFoundationTrackerService.findCSFTrackersAsAccountStatusBaseFunds(fieldValues, false))
                .thenReturn(csfTrackers);

        List<AccountStatusBaseFunds> result = laborBaseFundsService.findAccountStatusBaseFundsWithCSFTracker(fieldValues, false);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAccountNumber()).isEqualTo("9999999");
    }

    @Test
    void testFindAccountStatusBaseFundsWithEmptyResults() {
        when(laborBaseFundsDao.findLaborBaseFunds(fieldValues, false)).thenReturn(new ArrayList<>());
        when(laborCalculatedSalaryFoundationTrackerService.findCSFTrackersAsAccountStatusBaseFunds(fieldValues, false))
                .thenReturn(new ArrayList<>());

        List<AccountStatusBaseFunds> result = laborBaseFundsService.findAccountStatusBaseFundsWithCSFTracker(fieldValues, false);

        assertThat(result).isEmpty();
    }
}
