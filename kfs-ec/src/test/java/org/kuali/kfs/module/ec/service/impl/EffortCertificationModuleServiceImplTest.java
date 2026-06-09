package org.kuali.kfs.module.ec.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.kuali.kfs.integration.ec.EffortCertificationReport;
import org.kuali.kfs.module.ec.EffortConstants;
import org.kuali.kfs.module.ec.dataaccess.EffortCertificationReportDefinitionDao;
import org.kuali.kfs.module.ec.service.EffortCertificationReportDefinitionService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

/**
 * Tests for EffortCertificationModuleServiceImpl.
 * Uses spy to override SpringContext.getBean lookups.
 */
public class EffortCertificationModuleServiceImplTest extends KfsUnitTestBase {

    private EffortCertificationModuleServiceImpl moduleService;

    @Mock
    private EffortCertificationReportDefinitionDao reportDefinitionDao;

    @Mock
    private EffortCertificationReportDefinitionService reportDefinitionService;

    @BeforeEach
    void setUp() {
        moduleService = spy(new EffortCertificationModuleServiceImpl());
        lenient().doReturn(reportDefinitionDao).when(moduleService).getEffortCertificationReportDefinitionDao();
        lenient().doReturn(reportDefinitionService).when(moduleService).getEffortCertificationReportDefinitionService();
    }

    @Test
    void testGetCostShareSubAccountTypeCodes_returnsExpectedCodes() {
        List<String> codes = moduleService.getCostShareSubAccountTypeCodes();

        assertNotNull(codes);
        assertEquals(EffortConstants.ELIGIBLE_COST_SHARE_SUB_ACCOUNT_TYPE_CODES, codes);
    }

    @Test
    void testGetCostShareSubAccountTypeCodes_isNotEmpty() {
        List<String> codes = moduleService.getCostShareSubAccountTypeCodes();

        assertNotNull(codes);
    }

    @Test
    void testFindReportDefinitionsForPeriod_noMatchingPeriod_returnsEmpty() {
        EffortCertificationReport report = mock(EffortCertificationReport.class);
        when(report.getEffortCertificationReportBeginFiscalYear()).thenReturn(2014);
        when(report.getEffortCertificationReportBeginPeriodCode()).thenReturn("01");
        when(report.getEffortCertificationReportEndFiscalYear()).thenReturn(2014);
        when(report.getEffortCertificationReportEndPeriodCode()).thenReturn("06");

        List<EffortCertificationReport> reports = Arrays.asList(report);
        when(reportDefinitionDao.getAllByYearAndPositionCode(2014, "POS01")).thenReturn(reports);

        List<EffortCertificationReport> result = moduleService.findReportDefinitionsForPeriod(2014, "12", "POS01");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindReportDefinitionsForPeriod_matchingPeriod_returnsReport() {
        EffortCertificationReport report = mock(EffortCertificationReport.class);
        when(report.getEffortCertificationReportBeginFiscalYear()).thenReturn(2014);
        when(report.getEffortCertificationReportBeginPeriodCode()).thenReturn("01");
        when(report.getEffortCertificationReportEndFiscalYear()).thenReturn(2014);
        when(report.getEffortCertificationReportEndPeriodCode()).thenReturn("06");

        List<EffortCertificationReport> reports = Arrays.asList(report);
        when(reportDefinitionDao.getAllByYearAndPositionCode(2014, "POS01")).thenReturn(reports);

        List<EffortCertificationReport> result = moduleService.findReportDefinitionsForPeriod(2014, "03", "POS01");

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testIsEmployeeWithOpenCertification_noMatchingReports_returnsNull() {
        EffortCertificationReport report = mock(EffortCertificationReport.class);
        List<EffortCertificationReport> reports = Arrays.asList(report);

        when(reportDefinitionService.hasBeenUsedForEffortCertificationGeneration(anyString(), eq(report)))
                .thenReturn(false);

        EffortCertificationReport result = moduleService.isEmployeeWithOpenCertification(reports, "EMP001");
        assertNull(result);
    }

    @Test
    void testIsEmployeeWithOpenCertification_matchFound_returnsReport() {
        EffortCertificationReport report = mock(EffortCertificationReport.class);
        List<EffortCertificationReport> reports = Arrays.asList(report);

        when(reportDefinitionService.hasBeenUsedForEffortCertificationGeneration("EMP001", report))
                .thenReturn(true);

        EffortCertificationReport result = moduleService.isEmployeeWithOpenCertification(reports, "EMP001");
        assertNotNull(result);
        assertEquals(report, result);
    }

    @Test
    void testIsEmployeeWithOpenCertification_emptyList_returnsNull() {
        List<EffortCertificationReport> reports = new ArrayList<EffortCertificationReport>();

        EffortCertificationReport result = moduleService.isEmployeeWithOpenCertification(reports, "EMP001");
        assertNull(result);
    }

    @Test
    void testFindReportDefinitionsForPeriod_emptyReports_returnsEmpty() {
        when(reportDefinitionDao.getAllByYearAndPositionCode(2014, "POS01"))
                .thenReturn(new ArrayList<EffortCertificationReport>());

        List<EffortCertificationReport> result = moduleService.findReportDefinitionsForPeriod(2014, "03", "POS01");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testIsEmployeeWithOpenCertification_multipleReports_returnsFirstMatch() {
        EffortCertificationReport report1 = mock(EffortCertificationReport.class);
        EffortCertificationReport report2 = mock(EffortCertificationReport.class);
        List<EffortCertificationReport> reports = Arrays.asList(report1, report2);

        when(reportDefinitionService.hasBeenUsedForEffortCertificationGeneration("EMP001", report1))
                .thenReturn(false);
        when(reportDefinitionService.hasBeenUsedForEffortCertificationGeneration("EMP001", report2))
                .thenReturn(true);

        EffortCertificationReport result = moduleService.isEmployeeWithOpenCertification(reports, "EMP001");
        assertEquals(report2, result);
    }
}
