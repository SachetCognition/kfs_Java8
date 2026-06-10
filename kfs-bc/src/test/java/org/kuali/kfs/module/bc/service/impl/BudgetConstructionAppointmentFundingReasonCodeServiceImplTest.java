package org.kuali.kfs.module.bc.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.BCPropertyConstants;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionAppointmentFundingReasonCode;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BudgetConstructionAppointmentFundingReasonCodeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private BudgetConstructionAppointmentFundingReasonCodeServiceImpl service;

    @Test
    void getByPrimaryId_returnsExpectedReasonCode() {
        String reasonCode = "RC01";
        BudgetConstructionAppointmentFundingReasonCode expected = new BudgetConstructionAppointmentFundingReasonCode();

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(BCPropertyConstants.APPOINTMENT_FUNDING_REASON_CODE, reasonCode);

        when(businessObjectService.findByPrimaryKey(BudgetConstructionAppointmentFundingReasonCode.class, primaryKeys))
                .thenReturn(expected);

        BudgetConstructionAppointmentFundingReasonCode result = service.getByPrimaryId(reasonCode);
        assertThat(result).isSameAs(expected);
        verify(businessObjectService).findByPrimaryKey(BudgetConstructionAppointmentFundingReasonCode.class, primaryKeys);
    }

    @Test
    void getByPrimaryId_returnsNullWhenNotFound() {
        String reasonCode = "INVALID";
        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(BCPropertyConstants.APPOINTMENT_FUNDING_REASON_CODE, reasonCode);

        when(businessObjectService.findByPrimaryKey(BudgetConstructionAppointmentFundingReasonCode.class, primaryKeys))
                .thenReturn(null);

        BudgetConstructionAppointmentFundingReasonCode result = service.getByPrimaryId(reasonCode);
        assertThat(result).isNull();
    }
}
