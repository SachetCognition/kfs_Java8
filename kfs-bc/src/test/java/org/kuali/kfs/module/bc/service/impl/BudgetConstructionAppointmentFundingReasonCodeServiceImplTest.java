package org.kuali.kfs.module.bc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionAppointmentFundingReasonCode;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionAppointmentFundingReasonCodeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private BudgetConstructionAppointmentFundingReasonCodeServiceImpl service;

    @Test
    public void testGetByPrimaryId_found() {
        BudgetConstructionAppointmentFundingReasonCode expected = new BudgetConstructionAppointmentFundingReasonCode();
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionAppointmentFundingReasonCode.class), any(Map.class)))
                .thenReturn(expected);

        BudgetConstructionAppointmentFundingReasonCode result = service.getByPrimaryId("RC01");
        assertSame(expected, result);
    }

    @Test
    public void testGetByPrimaryId_notFound() {
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionAppointmentFundingReasonCode.class), any(Map.class)))
                .thenReturn(null);

        BudgetConstructionAppointmentFundingReasonCode result = service.getByPrimaryId("INVALID");
        assertNull(result);
    }
}
