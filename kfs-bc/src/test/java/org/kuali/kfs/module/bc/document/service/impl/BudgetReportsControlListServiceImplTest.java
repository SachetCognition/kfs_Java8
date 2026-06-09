package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.BCConstants.Report.BuildMode;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionObjectPick;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPullup;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionReasonCodePick;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionSubFundPick;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetReportsControlListDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.PersistenceService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetReportsControlListServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetReportsControlListDao budgetReportsControlListDao;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private PersistenceService persistenceServiceOjb;

    @InjectMocks
    private BudgetReportsControlListServiceImpl service;

    @Test
    public void testUpdateReportsControlList() {
        service.updateReportsControlList("user1", 2024, "UA", "UNIV", BuildMode.PBGL);
        verify(budgetReportsControlListDao).updateReportControlList("user1", 2024, "UA", "UNIV", BuildMode.PBGL);
    }

    @Test
    public void testUpdateReportSubFundGroupSelectList() {
        service.updateReportSubFundGroupSelectList("user1");
        verify(budgetReportsControlListDao).updateReportsSubFundGroupSelectList("user1");
    }

    @Test
    public void testUpdateReportObjectCodeSelectList() {
        service.updateReportObjectCodeSelectList("user1");
        verify(budgetReportsControlListDao).updateReportsObjectCodeSelectList("user1");
    }

    @Test
    public void testUpdateReportReasonCodeSelectList() {
        service.updateReportReasonCodeSelectList("user1");
        verify(budgetReportsControlListDao).updateReportsReasonCodeSelectList("user1");
    }

    @Test
    public void testRetrieveSelectedOrganziations() {
        Collection<BudgetConstructionPullup> expected = new ArrayList<>();
        when(businessObjectService.findMatching(eq(BudgetConstructionPullup.class), any(Map.class)))
                .thenReturn(expected);
        Collection<BudgetConstructionPullup> result = service.retrieveSelectedOrganziations("user1");
        assertSame(expected, result);
    }

    @Test
    public void testRetrieveSubFundList() {
        Collection<BudgetConstructionSubFundPick> expected = new ArrayList<>();
        when(businessObjectService.findMatching(eq(BudgetConstructionSubFundPick.class), any(Map.class)))
                .thenReturn(expected);
        Collection<BudgetConstructionSubFundPick> result = service.retrieveSubFundList("user1");
        assertSame(expected, result);
        verify(persistenceServiceOjb).clearCache();
    }

    @Test
    public void testRetrieveObjectCodeList() {
        Collection<BudgetConstructionObjectPick> expected = new ArrayList<>();
        when(businessObjectService.findMatching(eq(BudgetConstructionObjectPick.class), any(Map.class)))
                .thenReturn(expected);
        Collection<BudgetConstructionObjectPick> result = service.retrieveObjectCodeList("user1");
        assertSame(expected, result);
        verify(persistenceServiceOjb).clearCache();
    }

    @Test
    public void testRetrieveReasonCodeList() {
        Collection<BudgetConstructionReasonCodePick> expected = new ArrayList<>();
        when(businessObjectService.findMatching(eq(BudgetConstructionReasonCodePick.class), any(Map.class)))
                .thenReturn(expected);
        Collection<BudgetConstructionReasonCodePick> result = service.retrieveReasonCodeList("user1");
        assertSame(expected, result);
        verify(persistenceServiceOjb).clearCache();
    }

    @Test
    public void testUpdateObjectCodeSelectFlags() {
        List<BudgetConstructionObjectPick> list = new ArrayList<>();
        service.updateObjectCodeSelectFlags(list);
        verify(budgetReportsControlListDao).updateObjectCodeSelectFlags(list);
    }

    @Test
    public void testUpdateReasonCodeSelectFlags() {
        List<BudgetConstructionReasonCodePick> list = new ArrayList<>();
        service.updateReasonCodeSelectFlags(list);
        verify(budgetReportsControlListDao).updateReasonCodeSelectFlags(list);
    }

    @Test
    public void testUpdateSubFundSelectFlags() {
        List<BudgetConstructionSubFundPick> list = new ArrayList<>();
        service.updateSubFundSelectFlags(list);
        verify(budgetReportsControlListDao).updateSubFundSelectFlags(list);
    }

    @Test
    public void testGetPersistenceServiceOjb() {
        assertSame(persistenceServiceOjb, service.getPersistenceServiceOjb());
    }
}
