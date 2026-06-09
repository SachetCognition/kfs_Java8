package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPosition;
import org.kuali.kfs.module.bc.businessobject.PendingBudgetConstructionAppointmentFunding;
import org.kuali.kfs.module.bc.document.service.SalarySettingService;
import org.kuali.kfs.module.bc.service.HumanResourcesPayrollService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiInteger;
import org.kuali.rice.krad.util.MessageMap;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class SalarySettingRuleHelperServiceImplTest extends KfsUnitTestBase {

    @Mock
    private SalarySettingService salarySettingService;

    @Mock
    private HumanResourcesPayrollService humanResourcesPayrollService;

    @InjectMocks
    private SalarySettingRuleHelperServiceImpl service;

    @Test
    public void testHasValidRequestedAmount_nullAmount() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getAppointmentRequestedAmount()).thenReturn(null);
        MessageMap errorMap = new MessageMap();
        assertFalse(service.hasValidRequestedAmount(funding, errorMap));
    }

    @Test
    public void testHasValidRequestedAmount_negativeAmount() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getAppointmentRequestedAmount()).thenReturn(new KualiInteger(-100));
        MessageMap errorMap = new MessageMap();
        assertFalse(service.hasValidRequestedAmount(funding, errorMap));
    }

    @Test
    public void testHasValidRequestedAmount_validAmount() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getAppointmentRequestedAmount()).thenReturn(new KualiInteger(50000));
        MessageMap errorMap = new MessageMap();
        assertTrue(service.hasValidRequestedAmount(funding, errorMap));
    }

    @Test
    public void testHasValidRequestedFteQuantity_null() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getAppointmentRequestedFteQuantity()).thenReturn(null);
        MessageMap errorMap = new MessageMap();
        assertFalse(service.hasValidRequestedFteQuantity(funding, errorMap));
    }

    @Test
    public void testHasValidRequestedFteQuantity_negative() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getAppointmentRequestedFteQuantity()).thenReturn(new BigDecimal("-0.5"));
        MessageMap errorMap = new MessageMap();
        assertFalse(service.hasValidRequestedFteQuantity(funding, errorMap));
    }

    @Test
    public void testHasValidRequestedFteQuantity_valid() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getAppointmentRequestedFteQuantity()).thenReturn(new BigDecimal("1.0"));
        MessageMap errorMap = new MessageMap();
        assertTrue(service.hasValidRequestedFteQuantity(funding, errorMap));
    }

    @Test
    public void testHasValidRequestedTimePercent_null() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getAppointmentRequestedTimePercent()).thenReturn(null);
        MessageMap errorMap = new MessageMap();
        assertFalse(service.hasValidRequestedTimePercent(funding, errorMap));
    }

    @Test
    public void testHasValidRequestedTimePercent_negative() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getAppointmentRequestedTimePercent()).thenReturn(new BigDecimal("-10"));
        MessageMap errorMap = new MessageMap();
        assertFalse(service.hasValidRequestedTimePercent(funding, errorMap));
    }

    @Test
    public void testHasValidRequestedTimePercent_valid() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getAppointmentRequestedTimePercent()).thenReturn(new BigDecimal("50"));
        when(funding.getAppointmentRequestedAmount()).thenReturn(new KualiInteger(50000));
        when(funding.getAppointmentRequestedPayRate()).thenReturn(BigDecimal.ZERO);
        MessageMap errorMap = new MessageMap();
        assertTrue(service.hasValidRequestedTimePercent(funding, errorMap));
    }

    @Test
    public void testHasValidRequestedFundingMonth_null() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getAppointmentFundingMonth()).thenReturn(null);
        MessageMap errorMap = new MessageMap();
        assertFalse(service.hasValidRequestedFundingMonth(funding, errorMap));
    }

    @Test
    public void testHasValidRequestedFundingMonth_valid() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getAppointmentFundingMonth()).thenReturn(12);
        when(funding.getAppointmentFundingDurationCode()).thenReturn("NONE");
        BudgetConstructionPosition position = mock(BudgetConstructionPosition.class);
        when(position.getIuNormalWorkMonths()).thenReturn(12);
        when(funding.getBudgetConstructionPosition()).thenReturn(position);
        MessageMap errorMap = new MessageMap();
        assertTrue(service.hasValidRequestedFundingMonth(funding, errorMap));
    }

    @Test
    public void testHasNoExistingLine_emptyList() {
        List<PendingBudgetConstructionAppointmentFunding> list = new ArrayList<PendingBudgetConstructionAppointmentFunding>();
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        MessageMap errorMap = new MessageMap();
        assertTrue(service.hasNoExistingLine(list, funding, errorMap));
    }
}
