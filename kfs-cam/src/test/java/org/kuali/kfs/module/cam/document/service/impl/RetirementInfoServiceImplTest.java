package org.kuali.kfs.module.cam.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.businessobject.Asset;
import org.kuali.kfs.module.cam.businessobject.AssetRetirementGlobal;
import org.kuali.kfs.module.cam.businessobject.AssetRetirementGlobalDetail;
import org.kuali.kfs.module.cam.document.service.AssetService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.kfs.sys.businessobject.FinancialSystemDocumentHeader;

public class RetirementInfoServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;

    @Mock
    private AssetService assetService;

    @InjectMocks
    private RetirementInfoServiceImpl service;

    // --- setRetirementInfo ---

    @Test
    public void testSetRetirementInfo_notRetired_doesNothing() {
        Asset asset = new Asset();
        when(assetService.isAssetRetired(asset)).thenReturn(false);

        service.setRetirementInfo(asset);

        assertNull(asset.getRetirementInfo());
    }

    @Test
    public void testSetRetirementInfo_retired_setsLatestApprovedRecord() {
        Asset asset = new Asset();
        when(assetService.isAssetRetired(asset)).thenReturn(true);

        // Create two retirement details with different dates
        AssetRetirementGlobal retirementGlobal1 = mock(AssetRetirementGlobal.class);
        FinancialSystemDocumentHeader header1 = new FinancialSystemDocumentHeader();
        header1.setFinancialDocumentStatusCode(KFSConstants.DocumentStatusCodes.APPROVED);
        when(retirementGlobal1.getDocumentHeader()).thenReturn(header1);
        when(retirementGlobal1.getRetirementDate()).thenReturn(Date.valueOf("2024-01-15"));

        AssetRetirementGlobalDetail detail1 = new AssetRetirementGlobalDetail();
        detail1.setAssetRetirementGlobal(retirementGlobal1);

        AssetRetirementGlobal retirementGlobal2 = mock(AssetRetirementGlobal.class);
        FinancialSystemDocumentHeader header2 = new FinancialSystemDocumentHeader();
        header2.setFinancialDocumentStatusCode(KFSConstants.DocumentStatusCodes.APPROVED);
        when(retirementGlobal2.getDocumentHeader()).thenReturn(header2);
        when(retirementGlobal2.getRetirementDate()).thenReturn(Date.valueOf("2024-06-15"));

        AssetRetirementGlobalDetail detail2 = new AssetRetirementGlobalDetail();
        detail2.setAssetRetirementGlobal(retirementGlobal2);

        List<AssetRetirementGlobalDetail> history = new ArrayList<>();
        history.add(detail1);
        history.add(detail2);
        asset.setAssetRetirementHistory(history);

        service.setRetirementInfo(asset);

        assertSame(detail2, asset.getRetirementInfo());
    }

    @Test
    public void testSetRetirementInfo_retired_noApprovedRecords() {
        Asset asset = new Asset();
        when(assetService.isAssetRetired(asset)).thenReturn(true);

        AssetRetirementGlobal retirementGlobal = mock(AssetRetirementGlobal.class);
        FinancialSystemDocumentHeader header = new FinancialSystemDocumentHeader();
        header.setFinancialDocumentStatusCode("S"); // saved, not approved
        when(retirementGlobal.getDocumentHeader()).thenReturn(header);

        AssetRetirementGlobalDetail detail = new AssetRetirementGlobalDetail();
        detail.setAssetRetirementGlobal(retirementGlobal);

        List<AssetRetirementGlobalDetail> history = new ArrayList<>();
        history.add(detail);
        asset.setAssetRetirementHistory(history);

        service.setRetirementInfo(asset);

        assertNull(asset.getRetirementInfo());
    }

    @Test
    public void testSetRetirementInfo_retired_emptyHistory() {
        Asset asset = new Asset();
        when(assetService.isAssetRetired(asset)).thenReturn(true);
        asset.setAssetRetirementHistory(new ArrayList<AssetRetirementGlobalDetail>());

        service.setRetirementInfo(asset);

        assertNull(asset.getRetirementInfo());
    }

    @Test
    public void testSetRetirementInfo_retired_nullRetirementGlobal() {
        Asset asset = new Asset();
        when(assetService.isAssetRetired(asset)).thenReturn(true);

        AssetRetirementGlobalDetail detail = new AssetRetirementGlobalDetail();
        detail.setAssetRetirementGlobal(null);

        List<AssetRetirementGlobalDetail> history = new ArrayList<>();
        history.add(detail);
        asset.setAssetRetirementHistory(history);

        service.setRetirementInfo(asset);

        assertNull(asset.getRetirementInfo());
    }

    // --- setMergeHistory ---

    @Test
    public void testSetMergeHistory_withMergedRetirements() {
        Asset asset = new Asset();

        AssetRetirementGlobal mergeGlobal = new AssetRetirementGlobal();
        mergeGlobal.setRetirementReasonCode(CamsConstants.AssetRetirementReasonCode.MERGED);

        AssetRetirementGlobalDetail mergeDetail1 = new AssetRetirementGlobalDetail();
        AssetRetirementGlobalDetail mergeDetail2 = new AssetRetirementGlobalDetail();
        List<AssetRetirementGlobalDetail> details = new ArrayList<>();
        details.add(mergeDetail1);
        details.add(mergeDetail2);
        mergeGlobal.setAssetRetirementGlobalDetails(details);

        AssetRetirementGlobal nonMergeGlobal = new AssetRetirementGlobal();
        nonMergeGlobal.setRetirementReasonCode("T"); // theft
        nonMergeGlobal.setAssetRetirementGlobalDetails(new ArrayList<AssetRetirementGlobalDetail>());

        List<AssetRetirementGlobal> globals = new ArrayList<>();
        globals.add(mergeGlobal);
        globals.add(nonMergeGlobal);
        asset.setRetirementGlobals(globals);

        service.setMergeHistory(asset);

        assertNotNull(asset.getMergeHistory());
        assertEquals(2, asset.getMergeHistory().size());
    }

    @Test
    public void testSetMergeHistory_noMergedRetirements() {
        Asset asset = new Asset();

        AssetRetirementGlobal nonMergeGlobal = new AssetRetirementGlobal();
        nonMergeGlobal.setRetirementReasonCode("T");
        nonMergeGlobal.setAssetRetirementGlobalDetails(new ArrayList<AssetRetirementGlobalDetail>());

        List<AssetRetirementGlobal> globals = new ArrayList<>();
        globals.add(nonMergeGlobal);
        asset.setRetirementGlobals(globals);

        service.setMergeHistory(asset);

        assertTrue(asset.getMergeHistory() == null || asset.getMergeHistory().isEmpty());
    }

    @Test
    public void testSetMergeHistory_emptyList() {
        Asset asset = new Asset();
        asset.setRetirementGlobals(new ArrayList<AssetRetirementGlobal>());

        service.setMergeHistory(asset);

        assertTrue(asset.getMergeHistory() == null || asset.getMergeHistory().isEmpty());
    }
}
