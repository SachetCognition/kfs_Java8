package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.ld.LaborLedgerObject;
import org.kuali.kfs.integration.ld.LaborModuleService;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPosition;
import org.kuali.kfs.module.bc.businessobject.PendingBudgetConstructionAppointmentFunding;
import org.kuali.kfs.module.bc.document.service.BenefitsCalculationService;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionProcessorService;
import org.kuali.kfs.module.bc.document.service.BudgetDocumentService;
import org.kuali.kfs.module.bc.document.service.LockService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kns.service.DocumentHelperService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class SalarySettingServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ConfigurationService kualiConfigurationService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private LaborModuleService laborModuleService;

    @Mock
    private BudgetDocumentService budgetDocumentService;

    @Mock
    private BenefitsCalculationService benefitsCalculationService;

    @Mock
    private OptionsService optionsService;

    @Mock
    private LockService lockService;

    @Mock
    private DocumentHelperService documentHelperService;

    @Mock
    private DocumentService documentService;

    @Mock
    private BudgetConstructionProcessorService budgetConstructionProcessorService;

    @InjectMocks
    private SalarySettingServiceImpl service;

    @Test
    public void testIsSalarySettingDisabled() {
        assertFalse(service.isSalarySettingDisabled());
    }

    @Test
    public void testCalculateFteQuantity_allNull() {
        assertEquals(BigDecimal.ZERO, service.calculateFteQuantity(null, null, null));
    }

    @Test
    public void testCalculateFteQuantity_nullPayMonth() {
        assertEquals(BigDecimal.ZERO, service.calculateFteQuantity(null, 12, BigDecimal.valueOf(100)));
    }

    @Test
    public void testCalculateFteQuantity_nullFundingMonth() {
        assertEquals(BigDecimal.ZERO, service.calculateFteQuantity(12, null, BigDecimal.valueOf(100)));
    }

    @Test
    public void testCalculateFteQuantity_nullTimePercent() {
        assertEquals(BigDecimal.ZERO, service.calculateFteQuantity(12, 12, null));
    }

    @Test
    public void testCalculateFteQuantity_fullTimeFullYear() {
        BigDecimal result = service.calculateFteQuantity(12, 12, BigDecimal.valueOf(100));
        assertEquals(0, BigDecimal.ONE.setScale(5).compareTo(result));
    }

    @Test
    public void testCalculateFteQuantity_halfTime() {
        BigDecimal result = service.calculateFteQuantity(12, 12, BigDecimal.valueOf(50));
        assertEquals(0, new BigDecimal("0.50000").compareTo(result));
    }

    @Test
    public void testCalculateFteQuantity_halfYear() {
        BigDecimal result = service.calculateFteQuantity(12, 6, BigDecimal.valueOf(100));
        assertEquals(0, new BigDecimal("0.50000").compareTo(result));
    }

    @Test
    public void testCalculateCSFFteQuantity_allNull() {
        assertEquals(BigDecimal.ZERO, service.calculateCSFFteQuantity(null, null, null));
    }

    @Test
    public void testCalculateCSFFteQuantity_fullTimeFullYear() {
        BigDecimal result = service.calculateCSFFteQuantity(12, 12, BigDecimal.valueOf(100));
        assertEquals(0, BigDecimal.ONE.setScale(5).compareTo(result));
    }

    @Test
    public void testCalculateCSFFteQuantity_partTime() {
        BigDecimal result = service.calculateCSFFteQuantity(12, 9, BigDecimal.valueOf(100));
        assertEquals(0, new BigDecimal("0.75000").compareTo(result));
    }

    @Test
    public void testCalculateFteQuantityFromAppointmentFunding_nullPosition() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getBudgetConstructionPosition()).thenReturn(null);

        assertEquals(BigDecimal.ZERO, service.calculateFteQuantityFromAppointmentFunding(funding));
    }

    @Test
    public void testCalculateFteQuantityFromAppointmentFunding_withPosition() {
        BudgetConstructionPosition position = mock(BudgetConstructionPosition.class);
        when(position.getIuPayMonths()).thenReturn(12);

        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getBudgetConstructionPosition()).thenReturn(position);
        when(funding.getAppointmentFundingMonth()).thenReturn(12);
        when(funding.getAppointmentRequestedTimePercent()).thenReturn(BigDecimal.valueOf(100));

        BigDecimal result = service.calculateFteQuantityFromAppointmentFunding(funding);
        assertEquals(0, BigDecimal.ONE.setScale(5).compareTo(result));
    }

    @Test
    public void testCalculateCSFFteQuantityFromAppointmentFunding_nullPosition() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getBudgetConstructionPosition()).thenReturn(null);

        assertEquals(BigDecimal.ZERO, service.calculateCSFFteQuantityFromAppointmentFunding(funding));
    }

    @Test
    public void testIsHourlyPaidObject_nullLaborObject() {
        when(laborModuleService.retrieveLaborLedgerObject(2024, "UA", "5000")).thenReturn(null);
        assertFalse(service.isHourlyPaidObject(2024, "UA", "5000"));
    }

    @Test
    public void testCanBeVacant_singleFunding() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getEmplid()).thenReturn("12345");
        assertFalse(service.canBeVacant(funding));
    }

    @Test
    public void testCanBeVacant_vacantEmplid() {
        PendingBudgetConstructionAppointmentFunding funding = mock(PendingBudgetConstructionAppointmentFunding.class);
        when(funding.getEmplid()).thenReturn("VACANT");
        assertFalse(service.canBeVacant(funding));
    }
}
