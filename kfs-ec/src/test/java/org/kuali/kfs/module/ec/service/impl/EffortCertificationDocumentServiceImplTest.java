package org.kuali.kfs.module.ec.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.integration.cg.ContractsAndGrantsModuleService;
import org.kuali.kfs.integration.ld.LaborModuleService;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationDetail;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationDetailBuild;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationDocumentBuild;
import org.kuali.kfs.module.ec.document.EffortCertificationDocument;
import org.kuali.kfs.sys.businessobject.FinancialSystemDocumentHeader;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kew.api.WorkflowDocument;

import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentHeaderService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.KualiModuleService;

public class EffortCertificationDocumentServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private EffortCertificationDocumentServiceImpl documentService;

    @Mock
    private LaborModuleService laborModuleService;

    @Mock
    private KualiModuleService kualiModuleService;

    @Mock
    private ContractsAndGrantsModuleService contractsAndGrantsModuleService;

    @Mock
    private DocumentService docService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private EffortCertificationDocument ecDocument;

    @Mock
    private FinancialSystemDocumentHeader documentHeader;

    @Mock
    private WorkflowDocument workflowDocument;

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

        documentService.setDocumentService(docService);
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
    void testProcessApprovedEffortCertificationDocument_notProcessed_doesNotGenerate() {
        when(ecDocument.getDocumentHeader()).thenReturn(documentHeader);
        when(documentHeader.getWorkflowDocument()).thenReturn(workflowDocument);
        when(workflowDocument.isProcessed()).thenReturn(false);

        documentService.processApprovedEffortCertificationDocument(ecDocument);

        verify(ecDocument, never()).getEmplid();
    }

    @Test
    void testPopulateEffortCertificationDocument_setsFieldsFromBuild() {
        EffortCertificationDocument realDoc = new EffortCertificationDocument();
        realDoc.setDocumentHeader(new FinancialSystemDocumentHeader());

        EffortCertificationDocumentBuild build = mock(EffortCertificationDocumentBuild.class);
        when(build.getUniversityFiscalYear()).thenReturn(2014);
        when(build.getEmplid()).thenReturn("EMP001");
        when(build.getEffortCertificationReportNumber()).thenReturn("A01");
        when(build.getEffortCertificationDocumentCode()).thenReturn(false);
        when(build.getEffortCertificationDetailLinesBuild()).thenReturn(new ArrayList<EffortCertificationDetailBuild>());

        boolean result = documentService.populateEffortCertificationDocument(realDoc, build);

        assertTrue(result);
        assertEquals(Integer.valueOf(2014), realDoc.getUniversityFiscalYear());
        assertEquals("EMP001", realDoc.getEmplid());
        assertEquals("A01", realDoc.getEffortCertificationReportNumber());
        assertEquals(false, realDoc.getEffortCertificationDocumentCode());
    }

    @Test
    void testPopulateEffortCertificationDocument_emptyBuildLines_clearsDetailLines() {
        EffortCertificationDocument realDoc = new EffortCertificationDocument();
        realDoc.setDocumentHeader(new FinancialSystemDocumentHeader());

        EffortCertificationDocumentBuild build = mock(EffortCertificationDocumentBuild.class);
        when(build.getUniversityFiscalYear()).thenReturn(2014);
        when(build.getEmplid()).thenReturn("EMP001");
        when(build.getEffortCertificationReportNumber()).thenReturn("A01");
        when(build.getEffortCertificationDocumentCode()).thenReturn(false);
        when(build.getEffortCertificationDetailLinesBuild()).thenReturn(new ArrayList<EffortCertificationDetailBuild>());

        boolean result = documentService.populateEffortCertificationDocument(realDoc, build);

        assertTrue(result);
        assertEquals(0, realDoc.getEffortCertificationDetailLines().size());
    }

    @Test
    void testPopulateEffortCertificationDocument_setsDocumentHeaderDescription() {
        EffortCertificationDocument realDoc = new EffortCertificationDocument();
        FinancialSystemDocumentHeader header = new FinancialSystemDocumentHeader();
        realDoc.setDocumentHeader(header);

        EffortCertificationDocumentBuild build = mock(EffortCertificationDocumentBuild.class);
        when(build.getUniversityFiscalYear()).thenReturn(2014);
        when(build.getEmplid()).thenReturn("EMP001");
        when(build.getEffortCertificationReportNumber()).thenReturn("A01");
        when(build.getEffortCertificationDocumentCode()).thenReturn(false);
        when(build.getEffortCertificationDetailLinesBuild()).thenReturn(new ArrayList<EffortCertificationDetailBuild>());

        documentService.populateEffortCertificationDocument(realDoc, build);

        assertEquals("EMP001", header.getDocumentDescription());
    }

    @Test
    void testRemoveEffortCertificationDetailLines_deletesMatchingDetailLines() {
        when(ecDocument.getDocumentNumber()).thenReturn("DOC123");

        documentService.removeEffortCertificationDetailLines(ecDocument);

        verify(businessObjectService).deleteMatching(eq(EffortCertificationDetail.class), any(Map.class));
    }

    @Test
    void testGenerateSalaryExpenseTransferDocument_emptySourceLines_returnsTrue() {
        when(ecDocument.getEffortCertificationDetailLines()).thenReturn(new ArrayList<EffortCertificationDetail>());

        boolean result = documentService.generateSalaryExpenseTransferDocument(ecDocument);

        assertTrue(result);
    }

    @Test
    void testGenerateSalaryExpenseTransferDocument_noChangedAmounts_returnsTrue() {
        EffortCertificationDetail detail = mock(EffortCertificationDetail.class);
        when(detail.getEffortCertificationOriginalPayrollAmount()).thenReturn(new KualiDecimal(1000));
        when(detail.getEffortCertificationPayrollAmount()).thenReturn(new KualiDecimal(1000));
        List<EffortCertificationDetail> details = Arrays.asList(detail);
        when(ecDocument.getEffortCertificationDetailLines()).thenReturn(details);

        boolean result = documentService.generateSalaryExpenseTransferDocument(ecDocument);

        assertTrue(result);
    }

    @Test
    void testCreateAndRouteEffortCertificationDocument_throwsRuntimeOnWorkflowException() throws Exception {
        EffortCertificationDocumentBuild build = mock(EffortCertificationDocumentBuild.class);
        when(docService.getNewDocument(anyString())).thenThrow(new WorkflowException("test error"));

        try {
            documentService.createAndRouteEffortCertificationDocument(build);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Unable to route ECD document"));
        }
    }

    @Test
    void testPopulateEffortCertificationDocument_returnsTrue() {
        EffortCertificationDocument realDoc = new EffortCertificationDocument();
        realDoc.setDocumentHeader(new FinancialSystemDocumentHeader());

        EffortCertificationDocumentBuild build = mock(EffortCertificationDocumentBuild.class);
        when(build.getUniversityFiscalYear()).thenReturn(2014);
        when(build.getEmplid()).thenReturn("EMP002");
        when(build.getEffortCertificationReportNumber()).thenReturn("B02");
        when(build.getEffortCertificationDocumentCode()).thenReturn(true);
        when(build.getEffortCertificationDetailLinesBuild()).thenReturn(new ArrayList<EffortCertificationDetailBuild>());

        boolean result = documentService.populateEffortCertificationDocument(realDoc, build);

        assertTrue(result);
        assertEquals("EMP002", realDoc.getEmplid());
        assertEquals("B02", realDoc.getEffortCertificationReportNumber());
    }
}
