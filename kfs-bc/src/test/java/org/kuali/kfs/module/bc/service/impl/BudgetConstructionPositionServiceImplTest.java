package org.kuali.kfs.module.bc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPosition;
import org.kuali.kfs.module.bc.service.HumanResourcesPayrollService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionPositionServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private HumanResourcesPayrollService humanResourcesPayrollService;

    @InjectMocks
    private BudgetConstructionPositionServiceImpl service;

    @Test
    public void testGetByPrimaryId_found() {
        BudgetConstructionPosition expected = new BudgetConstructionPosition();
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionPosition.class), any(Map.class)))
                .thenReturn(expected);

        BudgetConstructionPosition result = service.getByPrimaryId("POS001", "2024");
        assertSame(expected, result);
    }

    @Test
    public void testGetByPrimaryId_notFound() {
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionPosition.class), any(Map.class)))
                .thenReturn(null);

        BudgetConstructionPosition result = service.getByPrimaryId("INVALID", "2024");
        assertNull(result);
    }

    @Test
    public void testSetBusinessObjectService() {
        service.setBusinessObjectService(businessObjectService);
        assertNotNull(service);
    }

    @Test
    public void testSetHumanResourcesPayrollService() {
        service.setHumanResourcesPayrollService(humanResourcesPayrollService);
        assertNotNull(service);
    }
}
