package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.businessobject.TrialBalanceReport;
import org.kuali.kfs.gl.dataaccess.TrialBalanceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.ReportGenerationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class TrialBalanceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private TrialBalanceDao trialBalanceDao;
    @Mock
    private ReportGenerationService reportGenerationService;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private TrialBalanceServiceImpl trialBalanceService;

    @Test
    void findTrialBalance_blankChartCode_treatedAsEmpty() {
        when(trialBalanceDao.findBalanceByFields("2024", "", "")).thenReturn(new ArrayList<TrialBalanceReport>());

        List result = trialBalanceService.findTrialBalance("2024", "", "");
        assertThat(result).isEmpty();
    }

    @Test
    void findTrialBalance_wildcardChartCode_treatedAsEmpty() {
        when(trialBalanceDao.findBalanceByFields("2024", "", "")).thenReturn(new ArrayList<TrialBalanceReport>());

        List result = trialBalanceService.findTrialBalance("2024", "*", "*");
        assertThat(result).isEmpty();
    }

    @Test
    void findTrialBalance_invalidPeriod_treatedAsEmpty() {
        when(trialBalanceDao.findBalanceByFields("2024", "BL", "")).thenReturn(new ArrayList<TrialBalanceReport>());

        List result = trialBalanceService.findTrialBalance("2024", "BL", "99");
        assertThat(result).isEmpty();
    }

    @Test
    void findTrialBalance_nonNumericPeriod_treatedAsEmpty() {
        when(trialBalanceDao.findBalanceByFields("2024", "BL", "")).thenReturn(new ArrayList<TrialBalanceReport>());

        List result = trialBalanceService.findTrialBalance("2024", "BL", "abc");
        assertThat(result).isEmpty();
    }

    @Test
    void findTrialBalance_validInputs_delegatesToDao() {
        List<TrialBalanceReport> reports = new ArrayList<TrialBalanceReport>();
        reports.add(new TrialBalanceReport());
        when(trialBalanceDao.findBalanceByFields("2024", "BL", "01")).thenReturn(reports);

        List result = trialBalanceService.findTrialBalance("2024", "BL", "01");
        assertThat(result).hasSize(1);
    }
}
