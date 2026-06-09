package org.kuali.kfs.module.ec.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.kuali.kfs.module.ec.businessobject.EffortCertificationDocumentBuild;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportDefinition;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportEarnPaygroup;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportPosition;
import org.kuali.kfs.module.ec.document.EffortCertificationDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.service.BusinessObjectService;

public class EffortCertificationReportDefinitionServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private EffortCertificationReportDefinitionServiceImpl reportDefinitionService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private AccountService accountService;

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private DataDictionaryService dataDictionaryService;

    @Mock
    private DataDictionary dataDictionary;

    private MockedStatic<SpringContext> springContextMock;

    private EffortCertificationReportDefinition reportDefinition;

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
                SpringContext.getBean(ConfigurationService.class);
            }
        }).thenReturn(configurationService);
        springContextMock.when(new MockedStatic.Verification() {
            @Override
            public void apply() throws Throwable {
                SpringContext.getBean(DataDictionaryService.class);
            }
        }).thenReturn(dataDictionaryService);
        lenient().when(dataDictionaryService.getDataDictionary()).thenReturn(dataDictionary);
        lenient().when(dataDictionaryService.getAttributeMaxLength(any(Class.class), any(String.class))).thenReturn(5);
        lenient().when(accountService.accountsCanCrossCharts()).thenReturn(true);

        reportDefinition = new EffortCertificationReportDefinition();
        reportDefinition.setUniversityFiscalYear(2014);
        reportDefinition.setEffortCertificationReportNumber("A01");
        reportDefinition.setEffortCertificationReportTypeCode("TYPE1");
    }

    @AfterEach
    void tearDown() {
        if (springContextMock != null) {
            springContextMock.close();
        }
    }

    @Test
    void testFindReportDefinitionByPrimaryKey_delegatesToBusinessObjectService() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("universityFiscalYear", "2014");
        fieldValues.put("effortCertificationReportNumber", "A01");

        EffortCertificationReportDefinition expected = new EffortCertificationReportDefinition();
        when(businessObjectService.findByPrimaryKey(EffortCertificationReportDefinition.class, fieldValues)).thenReturn(expected);

        EffortCertificationReportDefinition result = reportDefinitionService.findReportDefinitionByPrimaryKey(fieldValues);

        assertEquals(expected, result);
        verify(businessObjectService).findByPrimaryKey(EffortCertificationReportDefinition.class, fieldValues);
    }

    @Test
    void testFindReportDefinitionByPrimaryKey_notFound_returnsNull() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        when(businessObjectService.findByPrimaryKey(EffortCertificationReportDefinition.class, fieldValues)).thenReturn(null);

        EffortCertificationReportDefinition result = reportDefinitionService.findReportDefinitionByPrimaryKey(fieldValues);

        assertNull(result);
    }

    @Test
    void testValidateEffortCertificationReportDefinition_nullFiscalYear_returnsErrorMessage() {
        EffortCertificationReportDefinition def = new EffortCertificationReportDefinition();
        def.setUniversityFiscalYear(null);
        def.setEffortCertificationReportNumber("A01");
        lenient().when(configurationService.getPropertyValueAsString(any(String.class))).thenReturn("Error: missing fiscal year");

        String result = reportDefinitionService.validateEffortCertificationReportDefinition(def);

        assertNotNull(result);
    }

    @Test
    void testValidateEffortCertificationReportDefinition_emptyReportNumber_returnsErrorMessage() {
        EffortCertificationReportDefinition def = new EffortCertificationReportDefinition();
        def.setUniversityFiscalYear(2014);
        def.setEffortCertificationReportNumber("");
        lenient().when(configurationService.getPropertyValueAsString(any(String.class))).thenReturn("Error: missing report number");

        String result = reportDefinitionService.validateEffortCertificationReportDefinition(def);

        assertNotNull(result);
    }

    @Test
    void testValidateEffortCertificationReportDefinition_notFoundInDB_returnsErrorMessage() {
        EffortCertificationReportDefinition def = new EffortCertificationReportDefinition();
        def.setUniversityFiscalYear(2014);
        def.setEffortCertificationReportNumber("A01");
        when(businessObjectService.retrieve(def)).thenReturn(null);
        lenient().when(configurationService.getPropertyValueAsString(any(String.class))).thenReturn("Error: not found");

        String result = reportDefinitionService.validateEffortCertificationReportDefinition(def);

        assertNotNull(result);
    }

    @Test
    void testValidateEffortCertificationReportDefinition_inactive_returnsErrorMessage() {
        EffortCertificationReportDefinition def = new EffortCertificationReportDefinition();
        def.setUniversityFiscalYear(2014);
        def.setEffortCertificationReportNumber("A01");

        EffortCertificationReportDefinition retrieved = new EffortCertificationReportDefinition();
        retrieved.setActive(false);
        when(businessObjectService.retrieve(def)).thenReturn(retrieved);
        lenient().when(configurationService.getPropertyValueAsString(any(String.class))).thenReturn("Error: inactive");

        String result = reportDefinitionService.validateEffortCertificationReportDefinition(def);

        assertNotNull(result);
    }

    @Test
    void testValidateEffortCertificationReportDefinition_valid_returnsNull() {
        EffortCertificationReportDefinition def = new EffortCertificationReportDefinition();
        def.setUniversityFiscalYear(2014);
        def.setEffortCertificationReportNumber("A01");

        EffortCertificationReportDefinition retrieved = new EffortCertificationReportDefinition();
        retrieved.setActive(true);
        when(businessObjectService.retrieve(def)).thenReturn(retrieved);

        String result = reportDefinitionService.validateEffortCertificationReportDefinition(def);

        assertNull(result);
    }

    @Test
    void testFindPositionObjectGroupCodes_returnsGroupCodes() {
        EffortCertificationReportPosition pos1 = mock(EffortCertificationReportPosition.class);
        when(pos1.getEffortCertificationReportPositionObjectGroupCode()).thenReturn("GROUP1");
        EffortCertificationReportPosition pos2 = mock(EffortCertificationReportPosition.class);
        when(pos2.getEffortCertificationReportPositionObjectGroupCode()).thenReturn("GROUP2");

        Collection<EffortCertificationReportPosition> positions = Arrays.asList(pos1, pos2);
        when(businessObjectService.findMatching(eq(EffortCertificationReportPosition.class), any(Map.class))).thenReturn(positions);

        List<String> result = reportDefinitionService.findPositionObjectGroupCodes(reportDefinition);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("GROUP1"));
        assertTrue(result.contains("GROUP2"));
    }

    @Test
    void testFindPositionObjectGroupCodes_empty_returnsEmptyList() {
        Collection<EffortCertificationReportPosition> empty = new ArrayList<EffortCertificationReportPosition>();
        when(businessObjectService.findMatching(eq(EffortCertificationReportPosition.class), any(Map.class))).thenReturn(empty);

        List<String> result = reportDefinitionService.findPositionObjectGroupCodes(reportDefinition);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindReportEarnCodePayGroups_groupsByPayGroup() {
        EffortCertificationReportEarnPaygroup ep1 = mock(EffortCertificationReportEarnPaygroup.class);
        when(ep1.getPayGroup()).thenReturn("PAY1");
        when(ep1.getEarnCode()).thenReturn("EARN_A");
        EffortCertificationReportEarnPaygroup ep2 = mock(EffortCertificationReportEarnPaygroup.class);
        when(ep2.getPayGroup()).thenReturn("PAY1");
        when(ep2.getEarnCode()).thenReturn("EARN_B");
        EffortCertificationReportEarnPaygroup ep3 = mock(EffortCertificationReportEarnPaygroup.class);
        when(ep3.getPayGroup()).thenReturn("PAY2");
        when(ep3.getEarnCode()).thenReturn("EARN_C");

        Collection<EffortCertificationReportEarnPaygroup> earnPaygroups = Arrays.asList(ep1, ep2, ep3);
        when(businessObjectService.findMatching(eq(EffortCertificationReportEarnPaygroup.class), any(Map.class))).thenReturn(earnPaygroups);

        Map<String, Set<String>> result = reportDefinitionService.findReportEarnCodePayGroups(reportDefinition);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.get("PAY1").contains("EARN_A"));
        assertTrue(result.get("PAY1").contains("EARN_B"));
        assertTrue(result.get("PAY2").contains("EARN_C"));
    }

    @Test
    void testFindReportEarnCodePayGroups_empty_returnsEmptyMap() {
        Collection<EffortCertificationReportEarnPaygroup> empty = new ArrayList<EffortCertificationReportEarnPaygroup>();
        when(businessObjectService.findMatching(eq(EffortCertificationReportEarnPaygroup.class), any(Map.class))).thenReturn(empty);

        Map<String, Set<String>> result = reportDefinitionService.findReportEarnCodePayGroups(reportDefinition);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindReportEarnPay_delegatesToBusinessObjectService() {
        Collection<EffortCertificationReportEarnPaygroup> expected = new ArrayList<EffortCertificationReportEarnPaygroup>();
        when(businessObjectService.findMatching(eq(EffortCertificationReportEarnPaygroup.class), any(Map.class))).thenReturn(expected);

        Collection<EffortCertificationReportEarnPaygroup> result = reportDefinitionService.findReportEarnPay(reportDefinition);

        assertEquals(expected, result);
    }

    @Test
    void testHasBeenUsedForEffortCertificationGeneration_byDefinition_true() {
        when(businessObjectService.countMatching(eq(EffortCertificationDocument.class), any(Map.class))).thenReturn(1);

        boolean result = reportDefinitionService.hasBeenUsedForEffortCertificationGeneration(reportDefinition);

        assertTrue(result);
    }

    @Test
    void testHasBeenUsedForEffortCertificationGeneration_byDefinition_false() {
        when(businessObjectService.countMatching(eq(EffortCertificationDocument.class), any(Map.class))).thenReturn(0);

        boolean result = reportDefinitionService.hasBeenUsedForEffortCertificationGeneration(reportDefinition);

        assertFalse(result);
    }

    @Test
    void testHasBeenUsedForEffortCertificationGeneration_byEmplid_true() {
        when(businessObjectService.countMatching(eq(EffortCertificationDocument.class), any(Map.class))).thenReturn(1);

        boolean result = reportDefinitionService.hasBeenUsedForEffortCertificationGeneration("EMP001", reportDefinition);

        assertTrue(result);
    }

    @Test
    void testHasBeenUsedForEffortCertificationGeneration_byEmplid_false() {
        when(businessObjectService.countMatching(eq(EffortCertificationDocument.class), any(Map.class))).thenReturn(0);

        boolean result = reportDefinitionService.hasBeenUsedForEffortCertificationGeneration("EMP001", reportDefinition);

        assertFalse(result);
    }

    @Test
    void testHasPendingEffortCertification_pendingBuildExists_true() {
        when(businessObjectService.countMatching(eq(EffortCertificationDocumentBuild.class), any(Map.class))).thenReturn(1);

        boolean result = reportDefinitionService.hasPendingEffortCertification("EMP001", reportDefinition);

        assertTrue(result);
    }

    @Test
    void testHasPendingEffortCertification_noPendingBuildButEnrouteDoc_true() {
        when(businessObjectService.countMatching(eq(EffortCertificationDocumentBuild.class), any(Map.class))).thenReturn(0);
        when(businessObjectService.countMatching(eq(EffortCertificationDocument.class), any(Map.class))).thenReturn(1);

        boolean result = reportDefinitionService.hasPendingEffortCertification("EMP001", reportDefinition);

        assertTrue(result);
    }

    @Test
    void testHasPendingEffortCertification_noPendingAtAll_false() {
        when(businessObjectService.countMatching(eq(EffortCertificationDocumentBuild.class), any(Map.class))).thenReturn(0);
        when(businessObjectService.countMatching(eq(EffortCertificationDocument.class), any(Map.class))).thenReturn(0);

        boolean result = reportDefinitionService.hasPendingEffortCertification("EMP001", reportDefinition);

        assertFalse(result);
    }

    @Test
    void testHasApprovedEffortCertification_true() {
        when(businessObjectService.countMatching(eq(EffortCertificationDocument.class), any(Map.class))).thenReturn(1);

        boolean result = reportDefinitionService.hasApprovedEffortCertification("EMP001", reportDefinition);

        assertTrue(result);
    }

    @Test
    void testHasApprovedEffortCertification_false() {
        when(businessObjectService.countMatching(eq(EffortCertificationDocument.class), any(Map.class))).thenReturn(0);

        boolean result = reportDefinitionService.hasApprovedEffortCertification("EMP001", reportDefinition);

        assertFalse(result);
    }
}
