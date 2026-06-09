package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.businessobject.TrialBalanceReport;
import org.kuali.kfs.gl.dataaccess.TrialBalanceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.report.ReportInfo;
import org.kuali.kfs.sys.service.ReportGenerationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class TrialBalanceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private TrialBalanceDao trialBalanceDao;
    @Mock
    private ReportInfo glTrialBalanceReportInfo;
    @Mock
    private ReportGenerationService reportGenerationService;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private TrialBalanceServiceImpl trialBalanceService;

    @Test
    void findTrialBalance_withValidParams_delegatesToDao() {
        TrialBalanceReport report = new TrialBalanceReport();
        when(trialBalanceDao.findBalanceByFields("2024", "BL", "01"))
                .thenReturn(Arrays.asList(report));

        List result = trialBalanceService.findTrialBalance("2024", "BL", "01");
        assertThat(result).hasSize(1);
    }

    @Test
    void findTrialBalance_blankChartCode_treatedAsEmpty() {
        when(trialBalanceDao.findBalanceByFields("2024", "", "01"))
                .thenReturn(Collections.<TrialBalanceReport>emptyList());

        List result = trialBalanceService.findTrialBalance("2024", "", "01");
        assertThat(result).isEmpty();
    }

    @Test
    void findTrialBalance_wildcardChartCode_treatedAsEmpty() {
        when(trialBalanceDao.findBalanceByFields("2024", "", "01"))
                .thenReturn(Collections.<TrialBalanceReport>emptyList());

        List result = trialBalanceService.findTrialBalance("2024", "*", "01");
        assertThat(result).isEmpty();
    }

    @Test
    void findTrialBalance_invalidPeriodCode_treatedAsEmpty() {
        when(trialBalanceDao.findBalanceByFields("2024", "BL", ""))
                .thenReturn(Collections.<TrialBalanceReport>emptyList());

        List result = trialBalanceService.findTrialBalance("2024", "BL", "ABC");
        assertThat(result).isEmpty();
    }

    @Test
    void findTrialBalance_zeroPeriodCode_treatedAsEmpty() {
        when(trialBalanceDao.findBalanceByFields("2024", "BL", ""))
                .thenReturn(Collections.<TrialBalanceReport>emptyList());

        List result = trialBalanceService.findTrialBalance("2024", "BL", "0");
        assertThat(result).isEmpty();
    }

    @Test
    void findTrialBalance_periodCode14_treatedAsEmpty() {
        when(trialBalanceDao.findBalanceByFields("2024", "BL", ""))
                .thenReturn(Collections.<TrialBalanceReport>emptyList());

        List result = trialBalanceService.findTrialBalance("2024", "BL", "14");
        assertThat(result).isEmpty();
    }

    @Test
    void findTrialBalance_periodCode13_validAndPassedThrough() {
        TrialBalanceReport report = new TrialBalanceReport();
        when(trialBalanceDao.findBalanceByFields("2024", "BL", "13"))
                .thenReturn(Arrays.asList(report));

        List result = trialBalanceService.findTrialBalance("2024", "BL", "13");
        assertThat(result).hasSize(1);
    }
}
