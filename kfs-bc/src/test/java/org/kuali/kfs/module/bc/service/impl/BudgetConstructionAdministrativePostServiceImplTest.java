package org.kuali.kfs.module.bc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionAdministrativePost;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionAdministrativePostServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private BudgetConstructionAdministrativePostServiceImpl service;

    @Test
    public void testGetByPrimaryId_found() {
        BudgetConstructionAdministrativePost expected = new BudgetConstructionAdministrativePost();
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionAdministrativePost.class), any(Map.class)))
                .thenReturn(expected);

        BudgetConstructionAdministrativePost result = service.getByPrimaryId("EMP001", "POS001");
        assertSame(expected, result);
    }

    @Test
    public void testGetByPrimaryId_notFound() {
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionAdministrativePost.class), any(Map.class)))
                .thenReturn(null);

        BudgetConstructionAdministrativePost result = service.getByPrimaryId("EMP999", "POS999");
        assertNull(result);
    }
}
