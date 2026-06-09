package org.kuali.kfs.module.ec.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportDefinition;
import org.kuali.kfs.module.ec.util.ExtractProcessReportDataHolder;
import org.kuali.kfs.sys.report.ReportInfo;
import org.kuali.kfs.sys.service.ReportGenerationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

public class EffortCertificationReportServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private EffortCertificationReportServiceImpl reportService;

    @Mock
    private ReportGenerationService reportGenerationService;

    @Mock
    private ReportInfo effortExtractProcessReportInfo;

    @Mock
    private ResourceBundle resourceBundle;

    private EffortCertificationReportDefinition reportDefinition;

    @BeforeEach
    void setUp() {
        reportService.setReportGenerationService(reportGenerationService);
        reportService.setEffortExtractProcessReportInfo(effortExtractProcessReportInfo);

        reportDefinition = new EffortCertificationReportDefinition();
        reportDefinition.setUniversityFiscalYear(2014);
        reportDefinition.setEffortCertificationReportNumber("A01");
        reportDefinition.setEffortCertificationReportBeginFiscalYear(2014);
        reportDefinition.setEffortCertificationReportBeginPeriodCode("01");
        reportDefinition.setEffortCertificationReportEndFiscalYear(2014);
        reportDefinition.setEffortCertificationReportEndPeriodCode("06");

        when(effortExtractProcessReportInfo.getReportFileName()).thenReturn("testReport");
        when(effortExtractProcessReportInfo.getReportsDirectory()).thenReturn("/tmp/reports");
        when(effortExtractProcessReportInfo.getReportTemplateClassPath()).thenReturn("classpath:");
        when(effortExtractProcessReportInfo.getReportTemplateName()).thenReturn("template");
        when(effortExtractProcessReportInfo.getResourceBundle()).thenReturn(resourceBundle);
        when(effortExtractProcessReportInfo.getSubReportTemplateClassPath()).thenReturn("classpath:sub/");
        when(effortExtractProcessReportInfo.getSubReports()).thenReturn(new HashMap<String, String>());

        when(reportGenerationService.buildFullFileName(any(Date.class), anyString(), anyString(), anyString()))
                .thenReturn("/tmp/reports/testReport");
    }

    @Test
    void testGenerateReportForExtractProcess_callsGenerateReportToPdfFile() {
        ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder(reportDefinition);
        Date runDate = new Date();

        reportService.generateReportForExtractProcess(holder, runDate);

        verify(reportGenerationService).generateReportToPdfFile(any(Map.class), eq("classpath:template"), eq("/tmp/reports/testReport"));
    }

    @Test
    void testGenerateReportForExtractProcess_callsBuildFullFileName() {
        ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder(reportDefinition);
        Date runDate = new Date();

        reportService.generateReportForExtractProcess(holder, runDate);

        verify(reportGenerationService).buildFullFileName(eq(runDate), eq("/tmp/reports"), eq("testReport"), eq(""));
    }

    @Test
    void testGenerateReportForExtractProcess_reportDataContainsResourceBundle() {
        ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder(reportDefinition);
        Date runDate = new Date();

        reportService.generateReportForExtractProcess(holder, runDate);

        verify(reportGenerationService).generateReportToPdfFile(any(Map.class), anyString(), anyString());
    }

    @Test
    void testGenerateReportForExtractProcess_usesCorrectTemplate() {
        when(effortExtractProcessReportInfo.getReportTemplateClassPath()).thenReturn("org/kuali/kfs/module/ec/report/");
        when(effortExtractProcessReportInfo.getReportTemplateName()).thenReturn("EffortExtractProcessReport");

        ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder(reportDefinition);
        Date runDate = new Date();

        reportService.generateReportForExtractProcess(holder, runDate);

        verify(reportGenerationService).generateReportToPdfFile(any(Map.class),
                eq("org/kuali/kfs/module/ec/report/EffortExtractProcessReport"), anyString());
    }

    @Test
    void testGenerateReportForExtractProcess_subReportInfoPopulated() {
        Map<String, String> subReports = new HashMap<String, String>();
        subReports.put("sub1", "SubReport1");
        when(effortExtractProcessReportInfo.getSubReports()).thenReturn(subReports);

        ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder(reportDefinition);
        Date runDate = new Date();

        reportService.generateReportForExtractProcess(holder, runDate);

        verify(reportGenerationService).generateReportToPdfFile(any(Map.class), anyString(), anyString());
    }
}
