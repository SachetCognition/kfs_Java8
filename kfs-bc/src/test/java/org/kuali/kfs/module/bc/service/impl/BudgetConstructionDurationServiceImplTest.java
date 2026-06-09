package org.kuali.kfs.module.bc.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.BCPropertyConstants;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionDuration;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BudgetConstructionDurationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private BudgetConstructionDurationServiceImpl service;

    @Test
    void getByPrimaryId_returnsExpectedDuration() {
        String durationCode = "NONE";
        BudgetConstructionDuration expected = new BudgetConstructionDuration();

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(BCPropertyConstants.APPOINTMENT_DURATION_CODE, durationCode);

        when(businessObjectService.findByPrimaryKey(BudgetConstructionDuration.class, primaryKeys)).thenReturn(expected);

        BudgetConstructionDuration result = service.getByPrimaryId(durationCode);
        assertThat(result).isSameAs(expected);
        verify(businessObjectService).findByPrimaryKey(BudgetConstructionDuration.class, primaryKeys);
    }

    @Test
    void getByPrimaryId_returnsNullWhenNotFound() {
        String durationCode = "INVALID";
        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(BCPropertyConstants.APPOINTMENT_DURATION_CODE, durationCode);

        when(businessObjectService.findByPrimaryKey(BudgetConstructionDuration.class, primaryKeys)).thenReturn(null);

        BudgetConstructionDuration result = service.getByPrimaryId(durationCode);
        assertThat(result).isNull();
    }

    @Test
    void setBusinessObjectService_setsField() {
        BudgetConstructionDurationServiceImpl svc = new BudgetConstructionDurationServiceImpl();
        svc.setBusinessObjectService(businessObjectService);
        // verify indirectly - if it throws NPE, the setter didn't work
        Map<String, Object> keys = new HashMap<>();
        keys.put(BCPropertyConstants.APPOINTMENT_DURATION_CODE, "X");
        when(businessObjectService.findByPrimaryKey(BudgetConstructionDuration.class, keys)).thenReturn(null);
        assertThat(svc.getByPrimaryId("X")).isNull();
    }
}
