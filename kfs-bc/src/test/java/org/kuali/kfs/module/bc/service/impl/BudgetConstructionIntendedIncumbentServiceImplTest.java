package org.kuali.kfs.module.bc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionIntendedIncumbent;
import org.kuali.kfs.module.bc.service.HumanResourcesPayrollService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionIntendedIncumbentServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private HumanResourcesPayrollService humanResourcesPayrollService;

    @InjectMocks
    private BudgetConstructionIntendedIncumbentServiceImpl service;

    @Test
    public void testGetByPrimaryId_found() {
        BudgetConstructionIntendedIncumbent expected = new BudgetConstructionIntendedIncumbent();
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionIntendedIncumbent.class), any(Map.class)))
                .thenReturn(expected);

        BudgetConstructionIntendedIncumbent result = service.getByPrimaryId("EMP001");
        assertSame(expected, result);
    }

    @Test
    public void testGetByPrimaryId_notFound() {
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionIntendedIncumbent.class), any(Map.class)))
                .thenReturn(null);

        BudgetConstructionIntendedIncumbent result = service.getByPrimaryId("INVALID");
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
