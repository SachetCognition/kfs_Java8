package org.kuali.kfs.module.ec.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.integration.ld.LaborLedgerBalance;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationDetailBuild;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationDocumentBuild;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportDefinition;
import org.kuali.kfs.module.ec.service.EffortCertificationDetailBuildService;
import org.kuali.kfs.sys.businessobject.FinancialSystemDocumentHeader;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentHeaderService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

public class EffortCertificationDocumentBuildServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private EffortCertificationDocumentBuildServiceImpl documentBuildService;

    @Mock
    private EffortCertificationDetailBuildService effortCertificationDetailBuildService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private EffortCertificationReportDefinition reportDefinition;

    @Mock
    private AccountService accountService;

    @Mock
    private DocumentHeaderService documentHeaderService;

    @Mock
    private DataDictionaryService dataDictionaryService;

    @Mock
    private DataDictionary dataDictionary;

    @Mock
    private ConfigurationService configurationService;

    private MockedStatic<SpringContext> springContextMock;
    private MockedStatic<KRADServiceLocatorWeb> kradMock;

    private LaborLedgerBalance balance1;
    private LaborLedgerBalance balance2;

    @BeforeEach
    void setUp() {
        springContextMock = Mockito.mockStatic(SpringContext.class);
        springContextMock.when(new MockedStatic.Verification() {
            @Override
            public void apply() throws Throwable {
                SpringContext.getBean(AccountService.class);
            }
        }).thenReturn(accountService);
        springContextMock.when(new MockedStatic.Verification() {
            @Override
            public void apply() throws Throwable {
                SpringContext.getBean(DataDictionaryService.class);
            }
        }).thenReturn(dataDictionaryService);
        springContextMock.when(new MockedStatic.Verification() {
            @Override
            public void apply() throws Throwable {
                SpringContext.getBean(ConfigurationService.class);
            }
        }).thenReturn(configurationService);
        lenient().when(dataDictionaryService.getDataDictionary()).thenReturn(dataDictionary);
        lenient().when(dataDictionaryService.getAttributeMaxLength(any(Class.class), any(String.class))).thenReturn(5);
        lenient().when(accountService.accountsCanCrossCharts()).thenReturn(true);

        kradMock = Mockito.mockStatic(KRADServiceLocatorWeb.class);
        kradMock.when(new MockedStatic.Verification() {
            @Override
            public void apply() throws Throwable {
                KRADServiceLocatorWeb.getDocumentHeaderService();
            }
        }).thenReturn(documentHeaderService);
        lenient().when(documentHeaderService.getDocumentHeaderBaseClass()).thenReturn((Class) FinancialSystemDocumentHeader.class);

        balance1 = mock(LaborLedgerBalance.class);
        balance2 = mock(LaborLedgerBalance.class);

        lenient().when(balance1.getUniversityFiscalYear()).thenReturn(2014);
        lenient().when(balance1.getAmountByPeriod("01")).thenReturn(new KualiDecimal(5000));
        lenient().when(balance1.getEmplid()).thenReturn("EMP001");
        lenient().when(balance2.getUniversityFiscalYear()).thenReturn(2014);
        lenient().when(balance2.getAmountByPeriod("01")).thenReturn(new KualiDecimal(3000));
        lenient().when(balance2.getEmplid()).thenReturn("EMP002");

        lenient().when(reportDefinition.getUniversityFiscalYear()).thenReturn(2014);
        lenient().when(reportDefinition.getEffortCertificationReportNumber()).thenReturn("A01");

        Map<Integer, Set<String>> reportPeriods = new HashMap<Integer, Set<String>>();
        reportPeriods.put(2014, Collections.singleton("01"));
        lenient().when(reportDefinition.getReportPeriods()).thenReturn(reportPeriods);
    }

    @AfterEach
    void tearDown() {
        if (kradMock != null) {
            kradMock.close();
        }
        if (springContextMock != null) {
            springContextMock.close();
        }
    }

    @Test
    void testRemoveExistingDocumentBuild_delegatesToBusinessObjectService() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("universityFiscalYear", "2014");

        Collection<EffortCertificationDocumentBuild> docs = new ArrayList<EffortCertificationDocumentBuild>();
        when(businessObjectService.findMatching(EffortCertificationDocumentBuild.class, fieldValues)).thenReturn(docs);

        documentBuildService.removeExistingDocumentBuild(fieldValues);

        verify(businessObjectService).findMatching(EffortCertificationDocumentBuild.class, fieldValues);
        verify(businessObjectService).delete(any(ArrayList.class));
    }

    @Test
    void testGenerateDocumentBuildList_singleEmployee_returnsOneDocument() {
        EffortCertificationDetailBuild detailLine = new EffortCertificationDetailBuild();
        detailLine.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
        detailLine.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(5000));
        when(effortCertificationDetailBuildService.generateDetailBuild(eq(2014), eq(balance1), eq(reportDefinition)))
                .thenReturn(detailLine);

        List<LaborLedgerBalance> balances = Arrays.asList(balance1);
        List<EffortCertificationDocumentBuild> result = documentBuildService.generateDocumentBuildList(2014, reportDefinition, balances);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("EMP001", result.get(0).getEmplid());
    }

    @Test
    void testGenerateDocumentBuildList_twoEmployees_returnsTwoDocuments() {
        EffortCertificationDetailBuild detail1 = new EffortCertificationDetailBuild();
        detail1.setEffortCertificationPayrollAmount(new KualiDecimal(3000));
        detail1.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(3000));
        when(effortCertificationDetailBuildService.generateDetailBuild(eq(2014), eq(balance1), eq(reportDefinition)))
                .thenReturn(detail1);

        EffortCertificationDetailBuild detail2 = new EffortCertificationDetailBuild();
        detail2.setEffortCertificationPayrollAmount(new KualiDecimal(2000));
        detail2.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(2000));
        when(effortCertificationDetailBuildService.generateDetailBuild(eq(2014), eq(balance2), eq(reportDefinition)))
                .thenReturn(detail2);

        List<LaborLedgerBalance> balances = Arrays.asList(balance1, balance2);
        List<EffortCertificationDocumentBuild> result = documentBuildService.generateDocumentBuildList(2014, reportDefinition, balances);

        assertEquals(2, result.size());
    }

    @Test
    void testGenerateDocumentBuild_setsDocumentFields() {
        EffortCertificationDetailBuild detailLine = new EffortCertificationDetailBuild();
        detailLine.setEffortCertificationPayrollAmount(new KualiDecimal(1000));
        detailLine.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(1000));
        when(effortCertificationDetailBuildService.generateDetailBuild(eq(2014), eq(balance1), eq(reportDefinition)))
                .thenReturn(detailLine);

        List<LaborLedgerBalance> balances = Arrays.asList(balance1);
        EffortCertificationDocumentBuild doc = documentBuildService.generateDocumentBuild(2014, reportDefinition, balances);

        assertNotNull(doc);
        assertEquals("EMP001", doc.getEmplid());
        assertEquals("A01", doc.getEffortCertificationReportNumber());
        assertEquals(Integer.valueOf(2014), doc.getUniversityFiscalYear());
        assertEquals(false, doc.getEffortCertificationDocumentCode());
    }

    @Test
    void testGenerateDocumentBuild_detailLineAddedToDocument() {
        EffortCertificationDetailBuild detailLine = new EffortCertificationDetailBuild();
        detailLine.setEffortCertificationPayrollAmount(new KualiDecimal(500));
        detailLine.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(500));
        when(effortCertificationDetailBuildService.generateDetailBuild(anyInt(), any(LaborLedgerBalance.class), any(EffortCertificationReportDefinition.class)))
                .thenReturn(detailLine);

        List<LaborLedgerBalance> balances = Arrays.asList(balance1);
        EffortCertificationDocumentBuild doc = documentBuildService.generateDocumentBuild(2014, reportDefinition, balances);

        assertNotNull(doc.getEffortCertificationDetailLinesBuild());
        assertTrue(doc.getEffortCertificationDetailLinesBuild().size() >= 1);
    }

    @Test
    void testGenerateDocumentBuildList_sameEmployee_groupedIntoOneDocument() {
        lenient().when(balance2.getEmplid()).thenReturn("EMP001");

        EffortCertificationDetailBuild detail1 = new EffortCertificationDetailBuild();
        detail1.setEffortCertificationPayrollAmount(new KualiDecimal(2000));
        detail1.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(2000));
        EffortCertificationDetailBuild detail2 = new EffortCertificationDetailBuild();
        detail2.setEffortCertificationPayrollAmount(new KualiDecimal(3000));
        detail2.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(3000));

        when(effortCertificationDetailBuildService.generateDetailBuild(eq(2014), eq(balance1), eq(reportDefinition)))
                .thenReturn(detail1);
        when(effortCertificationDetailBuildService.generateDetailBuild(eq(2014), eq(balance2), eq(reportDefinition)))
                .thenReturn(detail2);

        List<LaborLedgerBalance> balances = Arrays.asList(balance1, balance2);
        List<EffortCertificationDocumentBuild> result = documentBuildService.generateDocumentBuildList(2014, reportDefinition, balances);

        assertEquals(1, result.size());
    }

    @Test
    void testGenerateDocumentBuildList_emptyBalances_returnsEmptyList() {
        List<LaborLedgerBalance> balances = new ArrayList<LaborLedgerBalance>();
        List<EffortCertificationDocumentBuild> result = documentBuildService.generateDocumentBuildList(2014, reportDefinition, balances);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
