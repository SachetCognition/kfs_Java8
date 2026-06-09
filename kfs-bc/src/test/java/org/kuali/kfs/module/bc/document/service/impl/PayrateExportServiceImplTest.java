package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPayRateHolding;
import org.kuali.kfs.module.bc.document.dataaccess.PayrateExportDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class PayrateExportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectServiceMock businessObjectService;

    @Mock
    private PayrateExportDao payrateExportDao;

    @InjectMocks
    private PayrateExportServiceImpl service;

    interface BusinessObjectServiceMock extends org.kuali.rice.krad.service.BusinessObjectService {}

    @Test
    public void testIsValidPositionUnionCode_delegatesToDao() {
        when(payrateExportDao.isValidPositionUnionCode("UNION1")).thenReturn(true);
        assertTrue(service.isValidPositionUnionCode("UNION1"));
    }

    @Test
    public void testIsValidPositionUnionCode_invalid() {
        when(payrateExportDao.isValidPositionUnionCode("INVALID")).thenReturn(false);
        assertFalse(service.isValidPositionUnionCode("INVALID"));
    }

    @Test
    public void testBuildExportFile_emptyResults() {
        List<BudgetConstructionPayRateHolding> emptyList = new ArrayList<>();
        when(businessObjectService.findMatching(eq(BudgetConstructionPayRateHolding.class), any(Map.class)))
                .thenReturn(emptyList);

        StringBuilder result = service.buildExportFile(2024, "UNION1", "20240101", "user1");

        assertNotNull(result);
        assertTrue(result.toString().contains("Export complete"));
        assertTrue(result.toString().contains("Export Count: 0"));
        verify(businessObjectService).deleteMatching(eq(BudgetConstructionPayRateHolding.class), any(Map.class));
        verify(payrateExportDao).buildPayRateHoldingRows(2024, "UNION1", "user1");
    }
}
