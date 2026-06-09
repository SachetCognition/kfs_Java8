package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.businessobject.Asset;
import org.kuali.kfs.module.cam.businessobject.AssetLocation;
import org.kuali.kfs.module.cam.businessobject.AssetPayment;
import org.kuali.kfs.module.cam.businessobject.AssetType;
import org.kuali.kfs.module.cam.document.service.PaymentSummaryService;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.UniversityDate;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AssetServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;

    @Mock
    private PaymentSummaryService paymentSummaryService;

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private AssetServiceImpl service;

    // --- isAssetDepreciationStarted ---

    @Test
    public void testIsAssetDepreciationStarted_withPositiveAccumulatedDepreciation() {
        Asset asset = new Asset();
        asset.setAccumulatedDepreciation(new KualiDecimal(100));
        asset.setAssetPayments(new ArrayList<AssetPayment>());

        assertTrue(service.isAssetDepreciationStarted(asset));
    }

    @Test
    public void testIsAssetDepreciationStarted_withNullAccumulatedDepreciation_checksPayments() {
        Asset asset = mock(Asset.class);
        when(asset.getAccumulatedDepreciation()).thenReturn(null);

        AssetPayment payment = new AssetPayment();
        payment.setAccumulatedPrimaryDepreciationAmount(new KualiDecimal(50));
        when(asset.getAssetPayments()).thenReturn(Arrays.asList(payment));

        assertTrue(service.isAssetDepreciationStarted(asset));
    }

    @Test
    public void testIsAssetDepreciationStarted_withZeroAccumulatedDepreciation_checksPayments() {
        Asset asset = new Asset();
        asset.setAccumulatedDepreciation(KualiDecimal.ZERO);

        AssetPayment payment = new AssetPayment();
        payment.setAccumulatedPrimaryDepreciationAmount(null);
        asset.setAssetPayments(Arrays.asList(payment));

        assertFalse(service.isAssetDepreciationStarted(asset));
    }

    @Test
    public void testIsAssetDepreciationStarted_noPayments() {
        Asset asset = mock(Asset.class);
        when(asset.getAccumulatedDepreciation()).thenReturn(null);
        when(asset.getAssetPayments()).thenReturn(new ArrayList<AssetPayment>());

        assertFalse(service.isAssetDepreciationStarted(asset));
    }

    // --- isCapitalAsset ---

    @Test
    public void testIsCapitalAsset_whenStatusCodeMatches() {
        Asset asset = new Asset();
        asset.setInventoryStatusCode("A");
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.CAPITAL_ASSET_STATUS_CODES)))
                .thenReturn(Arrays.asList("A", "C", "S"));

        assertTrue(service.isCapitalAsset(asset));
    }

    @Test
    public void testIsCapitalAsset_whenStatusCodeDoesNotMatch() {
        Asset asset = new Asset();
        asset.setInventoryStatusCode("R");
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.CAPITAL_ASSET_STATUS_CODES)))
                .thenReturn(Arrays.asList("A", "C", "S"));

        assertFalse(service.isCapitalAsset(asset));
    }

    // --- isAssetRetired ---

    @Test
    public void testIsAssetRetired_whenRetiredStatusCode() {
        Asset asset = new Asset();
        asset.setInventoryStatusCode("R");
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.RETIRED_STATUS_CODES)))
                .thenReturn(Arrays.asList("R", "T"));

        assertTrue(service.isAssetRetired(asset));
    }

    @Test
    public void testIsAssetRetired_whenActiveStatusCode() {
        Asset asset = new Asset();
        asset.setInventoryStatusCode("A");
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.RETIRED_STATUS_CODES)))
                .thenReturn(Arrays.asList("R", "T"));

        assertFalse(service.isAssetRetired(asset));
    }

    // --- isInServiceDateChanged ---

    @Test
    public void testIsInServiceDateChanged_bothNull() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        oldAsset.setCapitalAssetInServiceDate(null);
        newAsset.setCapitalAssetInServiceDate(null);

        assertFalse(service.isInServiceDateChanged(oldAsset, newAsset));
    }

    @Test
    public void testIsInServiceDateChanged_oldNullNewNotNull() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        oldAsset.setCapitalAssetInServiceDate(null);
        newAsset.setCapitalAssetInServiceDate(new Date(System.currentTimeMillis()));

        assertTrue(service.isInServiceDateChanged(oldAsset, newAsset));
    }

    @Test
    public void testIsInServiceDateChanged_sameDates() {
        Date date = Date.valueOf("2024-01-15");
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        oldAsset.setCapitalAssetInServiceDate(date);
        newAsset.setCapitalAssetInServiceDate(date);

        assertFalse(service.isInServiceDateChanged(oldAsset, newAsset));
    }

    @Test
    public void testIsInServiceDateChanged_differentDates() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        oldAsset.setCapitalAssetInServiceDate(Date.valueOf("2024-01-01"));
        newAsset.setCapitalAssetInServiceDate(Date.valueOf("2024-06-15"));

        assertTrue(service.isInServiceDateChanged(oldAsset, newAsset));
    }

    // --- isAssetLoaned ---

    @Test
    public void testIsAssetLoaned_whenLoanedOut() {
        Asset asset = new Asset();
        asset.setExpectedReturnDate(new Date(System.currentTimeMillis()));
        asset.setLoanReturnDate(null);

        assertTrue(service.isAssetLoaned(asset));
    }

    @Test
    public void testIsAssetLoaned_whenReturned() {
        Asset asset = new Asset();
        asset.setExpectedReturnDate(new Date(System.currentTimeMillis()));
        asset.setLoanReturnDate(new Date(System.currentTimeMillis()));

        assertFalse(service.isAssetLoaned(asset));
    }

    @Test
    public void testIsAssetLoaned_whenNeverLoaned() {
        Asset asset = new Asset();
        asset.setExpectedReturnDate(null);
        asset.setLoanReturnDate(null);

        assertFalse(service.isAssetLoaned(asset));
    }

    // --- isTagNumberCheckExclude ---

    @Test
    public void testIsTagNumberCheckExclude_capitalAssetRetired() {
        Asset asset = new Asset();
        asset.setInventoryStatusCode(CamsConstants.InventoryStatusCode.CAPITAL_ASSET_RETIRED);

        assertTrue(service.isTagNumberCheckExclude(asset));
    }

    @Test
    public void testIsTagNumberCheckExclude_nonCapitalAssetRetired() {
        Asset asset = new Asset();
        asset.setInventoryStatusCode(CamsConstants.InventoryStatusCode.NON_CAPITAL_ASSET_RETIRED);

        assertTrue(service.isTagNumberCheckExclude(asset));
    }

    @Test
    public void testIsTagNumberCheckExclude_nonTaggableAsset() {
        Asset asset = new Asset();
        asset.setInventoryStatusCode("A");
        asset.setCampusTagNumber(CamsConstants.Asset.NON_TAGGABLE_ASSET);

        assertTrue(service.isTagNumberCheckExclude(asset));
    }

    @Test
    public void testIsTagNumberCheckExclude_activeWithTag() {
        Asset asset = new Asset();
        asset.setInventoryStatusCode("A");
        asset.setCampusTagNumber("12345");

        assertFalse(service.isTagNumberCheckExclude(asset));
    }

    // --- isOffCampusLocationEntered ---

    @Test
    public void testIsOffCampusLocationEntered_withContactName() {
        Asset asset = new Asset();
        AssetLocation offCampus = new AssetLocation();
        offCampus.setAssetLocationContactName("John");
        asset.setOffCampusLocation(offCampus);

        assertTrue(service.isOffCampusLocationEntered(asset));
    }

    @Test
    public void testIsOffCampusLocationEntered_empty() {
        Asset asset = new Asset();
        AssetLocation offCampus = new AssetLocation();
        asset.setOffCampusLocation(offCampus);

        assertFalse(service.isOffCampusLocationEntered(asset));
    }

    @Test
    public void testIsOffCampusLocationEntered_withCityName() {
        Asset asset = new Asset();
        AssetLocation offCampus = new AssetLocation();
        offCampus.setAssetLocationCityName("Boston");
        asset.setOffCampusLocation(offCampus);

        assertTrue(service.isOffCampusLocationEntered(asset));
    }

    // --- isFinancialObjectSubTypeCodeChanged ---

    @Test
    public void testIsFinancialObjectSubTypeCodeChanged_same() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        oldAsset.setFinancialObjectSubTypeCode("CM");
        newAsset.setFinancialObjectSubTypeCode("CM");

        assertFalse(service.isFinancialObjectSubTypeCodeChanged(oldAsset, newAsset));
    }

    @Test
    public void testIsFinancialObjectSubTypeCodeChanged_different() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        oldAsset.setFinancialObjectSubTypeCode("CM");
        newAsset.setFinancialObjectSubTypeCode("CF");

        assertTrue(service.isFinancialObjectSubTypeCodeChanged(oldAsset, newAsset));
    }

    @Test
    public void testIsFinancialObjectSubTypeCodeChanged_caseInsensitive() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        oldAsset.setFinancialObjectSubTypeCode("cm");
        newAsset.setFinancialObjectSubTypeCode("CM");

        assertFalse(service.isFinancialObjectSubTypeCodeChanged(oldAsset, newAsset));
    }

    // --- isAssetTypeCodeChanged ---

    @Test
    public void testIsAssetTypeCodeChanged_same() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        oldAsset.setCapitalAssetTypeCode("EQ");
        newAsset.setCapitalAssetTypeCode("EQ");

        assertFalse(service.isAssetTypeCodeChanged(oldAsset, newAsset));
    }

    @Test
    public void testIsAssetTypeCodeChanged_different() {
        Asset oldAsset = new Asset();
        Asset newAsset = new Asset();
        oldAsset.setCapitalAssetTypeCode("EQ");
        newAsset.setCapitalAssetTypeCode("BD");

        assertTrue(service.isAssetTypeCodeChanged(oldAsset, newAsset));
    }

    // --- isCapitalAssetNumberDuplicate ---

    @Test
    public void testIsCapitalAssetNumberDuplicate_sameNumbers() {
        assertTrue(service.isCapitalAssetNumberDuplicate(100L, 100L));
    }

    @Test
    public void testIsCapitalAssetNumberDuplicate_differentNumbers() {
        assertFalse(service.isCapitalAssetNumberDuplicate(100L, 200L));
    }

    @Test
    public void testIsCapitalAssetNumberDuplicate_firstNull() {
        assertFalse(service.isCapitalAssetNumberDuplicate(null, 100L));
    }

    @Test
    public void testIsCapitalAssetNumberDuplicate_secondNull() {
        assertFalse(service.isCapitalAssetNumberDuplicate(100L, null));
    }

    @Test
    public void testIsCapitalAssetNumberDuplicate_bothNull() {
        assertFalse(service.isCapitalAssetNumberDuplicate(null, null));
    }

    // --- setAssetSummaryFields ---

    @Test
    public void testSetAssetSummaryFields_populatesFields() {
        Asset asset = new Asset();
        when(paymentSummaryService.calculateFederalContribution(asset)).thenReturn(new KualiDecimal(500));
        when(paymentSummaryService.calculatePrimaryAccumulatedDepreciation(asset)).thenReturn(new KualiDecimal(100));
        when(paymentSummaryService.calculatePrimaryBookValue(asset)).thenReturn(new KualiDecimal(400));

        service.setAssetSummaryFields(asset);

        assertEquals(new KualiDecimal(500), asset.getFederalContribution());
        assertEquals(new KualiDecimal(100), asset.getAccumulatedDepreciation());
        assertEquals(new KualiDecimal(400), asset.getBookValue());
    }

    @Test
    public void testSetAssetSummaryFields_nullAsset() {
        service.setAssetSummaryFields(null);
        verifyNoInteractions(paymentSummaryService);
    }

    // --- isObjectSubTypeCompatible ---

    @Test
    public void testIsObjectSubTypeCompatible_nullList() {
        assertTrue(service.isObjectSubTypeCompatible(null));
    }

    @Test
    public void testIsObjectSubTypeCompatible_singleElement() {
        assertTrue(service.isObjectSubTypeCompatible(Arrays.asList("CM")));
    }

    @Test
    public void testIsObjectSubTypeCompatible_emptyList() {
        assertTrue(service.isObjectSubTypeCompatible(new ArrayList<String>()));
    }

    @Test
    public void testIsObjectSubTypeCompatible_compatibleTypes() {
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.OBJECT_SUB_TYPE_GROUPS)))
                .thenReturn(Arrays.asList("CM,CF,C1", "BD,BF"));

        assertTrue(service.isObjectSubTypeCompatible(Arrays.asList("CM", "CF")));
    }

    @Test
    public void testIsObjectSubTypeCompatible_incompatibleTypes() {
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.OBJECT_SUB_TYPE_GROUPS)))
                .thenReturn(Arrays.asList("CM,CF,C1", "BD,BF"));

        assertFalse(service.isObjectSubTypeCompatible(Arrays.asList("CM", "BD")));
    }

    // --- setFiscalPeriod ---

    @Test
    public void testSetFiscalPeriod_withCreateDate() {
        Asset asset = new Asset();
        Date createDate = Date.valueOf("2024-01-15");
        asset.setCreateDate(createDate);

        UniversityDate uDate = new UniversityDate();
        uDate.setUniversityFiscalYear(2024);
        uDate.setUniversityFiscalAccountingPeriod("07");

        Map<String, Object> keys = new HashMap<>();
        keys.put(KFSPropertyConstants.UNIVERSITY_DATE, createDate);
        when(businessObjectService.findByPrimaryKey(eq(UniversityDate.class), eq(keys))).thenReturn(uDate);

        service.setFiscalPeriod(asset);

        assertEquals(Integer.valueOf(2024), asset.getFinancialDocumentPostingYear());
        assertEquals("07", asset.getFinancialDocumentPostingPeriodCode());
    }

    @Test
    public void testSetFiscalPeriod_nullCreateDate() {
        Asset asset = new Asset();
        asset.setCreateDate(null);

        service.setFiscalPeriod(asset);

        assertNull(asset.getFinancialDocumentPostingYear());
        verifyNoInteractions(businessObjectService);
    }

    @Test
    public void testSetFiscalPeriod_noUniversityDateFound() {
        Asset asset = new Asset();
        Date createDate = Date.valueOf("2024-01-15");
        asset.setCreateDate(createDate);

        when(businessObjectService.findByPrimaryKey(eq(UniversityDate.class), ArgumentMatchers.<java.util.Map<String, Object>>any())).thenReturn(null);

        service.setFiscalPeriod(asset);

        assertNull(asset.getFinancialDocumentPostingYear());
    }

    // --- isAssetMovableCheckByPayment(String) ---

    @Test
    public void testIsAssetMovableCheckByPayment_nullSubType() {
        assertTrue(service.isAssetMovableCheckByPayment((String) null));
    }

    @Test
    public void testIsAssetMovableCheckByPayment_movableSubType() {
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.MOVABLE_EQUIPMENT_OBJECT_SUB_TYPES)))
                .thenReturn(Arrays.asList("CM", "CF"));

        assertTrue(service.isAssetMovableCheckByPayment("CM"));
    }

    @Test
    public void testIsAssetMovableCheckByPayment_nonMovableSubType() {
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.MOVABLE_EQUIPMENT_OBJECT_SUB_TYPES)))
                .thenReturn(Arrays.asList("CM", "CF"));
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.NON_MOVABLE_EQUIPMENT_OBJECT_SUB_TYPES)))
                .thenReturn(Arrays.asList("BD", "BF"));

        assertFalse(service.isAssetMovableCheckByPayment("BD"));
    }

    @Test
    public void testIsAssetMovableCheckByPayment_unknownSubType_throws() {
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.MOVABLE_EQUIPMENT_OBJECT_SUB_TYPES)))
                .thenReturn(Arrays.asList("CM", "CF"));
        when(parameterService.getParameterValuesAsString(eq(Asset.class), eq(CamsConstants.Parameters.NON_MOVABLE_EQUIPMENT_OBJECT_SUB_TYPES)))
                .thenReturn(Arrays.asList("BD", "BF"));

        try {
            service.isAssetMovableCheckByPayment("ZZ");
            fail("Expected ValidationException");
        } catch (org.kuali.rice.krad.exception.ValidationException e) {
            // expected
        }
    }

    // --- getCurrentRouteLevels ---

    @Test
    public void testGetCurrentRouteLevels() {
        WorkflowDocument workflowDoc = mock(WorkflowDocument.class);
        Set<String> expected = new HashSet<>(Arrays.asList("Account", "Org"));
        when(workflowDoc.getCurrentNodeNames()).thenReturn(expected);

        assertEquals(expected, service.getCurrentRouteLevels(workflowDoc));
    }

    // --- isDocumentEnrouting ---

    @Test
    public void testIsDocumentEnrouting_true() {
        Document document = mock(Document.class);
        DocumentHeader docHeader = mock(DocumentHeader.class);
        WorkflowDocument wfDoc = mock(WorkflowDocument.class);
        when(document.getDocumentHeader()).thenReturn(docHeader);
        when(docHeader.getWorkflowDocument()).thenReturn(wfDoc);
        when(wfDoc.isEnroute()).thenReturn(true);

        assertTrue(service.isDocumentEnrouting(document));
    }

    @Test
    public void testIsDocumentEnrouting_false() {
        Document document = mock(Document.class);
        DocumentHeader docHeader = mock(DocumentHeader.class);
        WorkflowDocument wfDoc = mock(WorkflowDocument.class);
        when(document.getDocumentHeader()).thenReturn(docHeader);
        when(docHeader.getWorkflowDocument()).thenReturn(wfDoc);
        when(wfDoc.isEnroute()).thenReturn(false);

        assertFalse(service.isDocumentEnrouting(document));
    }
}
