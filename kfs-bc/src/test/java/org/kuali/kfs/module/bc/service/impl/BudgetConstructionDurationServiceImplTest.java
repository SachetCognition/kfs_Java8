package org.kuali.kfs.module.bc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionDuration;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionDurationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private BudgetConstructionDurationServiceImpl service;

    @Test
    public void testGetByPrimaryId_found() {
        BudgetConstructionDuration expected = new BudgetConstructionDuration();
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionDuration.class), any(Map.class)))
                .thenReturn(expected);

        BudgetConstructionDuration result = service.getByPrimaryId("12M");
        assertSame(expected, result);
    }

    @Test
    public void testGetByPrimaryId_notFound() {
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionDuration.class), any(Map.class)))
                .thenReturn(null);

        BudgetConstructionDuration result = service.getByPrimaryId("INVALID");
        assertNull(result);
    }
}
