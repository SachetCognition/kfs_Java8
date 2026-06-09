package org.kuali.kfs.module.bc.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPosition;
import org.kuali.kfs.module.bc.businessobject.PendingBudgetConstructionAppointmentFunding;
import org.kuali.kfs.module.bc.businessobject.Position;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionDao;
import org.kuali.kfs.module.bc.exception.BudgetPositionAlreadyExistsException;
import org.kuali.kfs.module.bc.service.HumanResourcesPayrollService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BudgetConstructionPositionServiceImplTest extends KfsUnitTestBase {

    @Mock
    private HumanResourcesPayrollService humanResourcesPayrollService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private BudgetConstructionDao budgetConstructionDao;

    @InjectMocks
    private BudgetConstructionPositionServiceImpl service;

    private Position createFullyStubbed() {
        Position pos = mock(Position.class);
        lenient().when(pos.getUniversityFiscalYear()).thenReturn(2024);
        lenient().when(pos.getPositionNumber()).thenReturn("POS001");
        lenient().when(pos.isBudgetedPosition()).thenReturn(true);
        lenient().when(pos.isConfidentialPosition()).thenReturn(false);
        lenient().when(pos.getIuDefaultObjectCode()).thenReturn("OBJ");
        lenient().when(pos.getIuNormalWorkMonths()).thenReturn(12);
        lenient().when(pos.getIuPayMonths()).thenReturn(12);
        lenient().when(pos.getIuPositionType()).thenReturn("P");
        lenient().when(pos.getJobCode()).thenReturn("JOB1");
        lenient().when(pos.getJobCodeDescription()).thenReturn("Desc");
        lenient().when(pos.getPositionDepartmentIdentifier()).thenReturn("DEPT");
        lenient().when(pos.getPositionDescription()).thenReturn("Position Desc");
        lenient().when(pos.getPositionEffectiveStatus()).thenReturn("A");
        lenient().when(pos.getPositionFullTimeEquivalency()).thenReturn(BigDecimal.ONE);
        lenient().when(pos.getPositionGradeDefault()).thenReturn("G1");
        lenient().when(pos.getPositionRegularTemporary()).thenReturn("R");
        lenient().when(pos.getPositionSalaryPlanDefault()).thenReturn("SAL");
        lenient().when(pos.getPositionStandardHoursDefault()).thenReturn(new BigDecimal("40.00"));
        lenient().when(pos.getPositionStatus()).thenReturn("A");
        lenient().when(pos.getPositionUnionCode()).thenReturn("UC");
        lenient().when(pos.getResponsibilityCenterCode()).thenReturn("RC");
        lenient().when(pos.getSetidDepartment()).thenReturn("SD");
        lenient().when(pos.getSetidJobCode()).thenReturn("SJ");
        lenient().when(pos.getSetidSalary()).thenReturn("SS");
        return pos;
    }

    @Test
    void getByPrimaryId_returnsPosition() {
        BudgetConstructionPosition expected = new BudgetConstructionPosition();
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionPosition.class), anyMap()))
                .thenReturn(expected);

        BudgetConstructionPosition result = service.getByPrimaryId("2024", "POS001");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryId_returnsNull_whenNotFound() {
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionPosition.class), anyMap()))
                .thenReturn(null);

        BudgetConstructionPosition result = service.getByPrimaryId("2024", "UNKNOWN");
        assertThat(result).isNull();
    }

    @Test
    void isBudgetablePosition_returnsTrue_whenBudgetedAndEffective() {
        BudgetConstructionPosition position = new BudgetConstructionPosition();
        position.setBudgetedPosition(true);
        position.setPositionEffectiveStatus("A");

        assertThat(service.isBudgetablePosition(position)).isTrue();
    }

    @Test
    void isBudgetablePosition_returnsFalse_whenNotBudgeted() {
        BudgetConstructionPosition position = new BudgetConstructionPosition();
        position.setBudgetedPosition(false);
        position.setPositionEffectiveStatus("A");

        assertThat(service.isBudgetablePosition(position)).isFalse();
    }

    @Test
    void isBudgetablePosition_returnsFalse_whenNotEffective() {
        BudgetConstructionPosition position = new BudgetConstructionPosition();
        position.setBudgetedPosition(true);
        position.setPositionEffectiveStatus("I");

        assertThat(service.isBudgetablePosition(position)).isFalse();
    }

    @Test
    void isBudgetablePosition_returnsFalse_whenNull() {
        assertThat(service.isBudgetablePosition(null)).isFalse();
    }

    @Test
    void pullNewPositionFromExternal_throwsWhenPositionAlreadyExists() {
        BudgetConstructionPosition existingPosition = new BudgetConstructionPosition();
        Position externalPosition = createFullyStubbed();

        when(humanResourcesPayrollService.getPosition(2024, "POS001")).thenReturn(externalPosition);
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionPosition.class), anyMap()))
                .thenReturn(existingPosition);

        assertThatThrownBy(() -> service.pullNewPositionFromExternal(2024, "POS001"))
                .isInstanceOf(BudgetPositionAlreadyExistsException.class);
    }

    @Test
    void pullNewPositionFromExternal_savesNewPosition_whenNotExists() {
        Position externalPosition = createFullyStubbed();

        when(humanResourcesPayrollService.getPosition(2024, "POS001")).thenReturn(externalPosition);
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionPosition.class), anyMap()))
                .thenReturn(null);

        service.pullNewPositionFromExternal(2024, "POS001");
        verify(businessObjectService).save(any(BudgetConstructionPosition.class));
    }

    @Test
    void refreshPositionFromExternal_updatesExistingPosition() {
        BudgetConstructionPosition existingPosition = new BudgetConstructionPosition();
        Position externalPosition = createFullyStubbed();

        when(humanResourcesPayrollService.getPosition(2024, "POS001")).thenReturn(externalPosition);
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionPosition.class), anyMap()))
                .thenReturn(existingPosition);

        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        when(budgetConstructionDao.getAllFundingForPosition(2024, "POS001")).thenReturn(fundings);

        service.refreshPositionFromExternal(2024, "POS001");
        verify(businessObjectService).save(any(BudgetConstructionPosition.class));
    }

    @Test
    void refreshPositionFromExternal_updatesFundingIndicators_forNonDeletedLines() {
        BudgetConstructionPosition existingPosition = new BudgetConstructionPosition();
        Position externalPosition = createFullyStubbed();

        when(humanResourcesPayrollService.getPosition(2024, "POS001")).thenReturn(externalPosition);
        when(businessObjectService.findByPrimaryKey(eq(BudgetConstructionPosition.class), anyMap()))
                .thenReturn(existingPosition);

        PendingBudgetConstructionAppointmentFunding activeFunding = new PendingBudgetConstructionAppointmentFunding();
        activeFunding.setAppointmentFundingDeleteIndicator(false);
        activeFunding.setVersionNumber(1L);

        PendingBudgetConstructionAppointmentFunding deletedFunding = new PendingBudgetConstructionAppointmentFunding();
        deletedFunding.setAppointmentFundingDeleteIndicator(true);
        deletedFunding.setVersionNumber(1L);

        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(activeFunding);
        fundings.add(deletedFunding);
        when(budgetConstructionDao.getAllFundingForPosition(2024, "POS001")).thenReturn(fundings);

        service.refreshPositionFromExternal(2024, "POS001");

        assertThat(activeFunding.isPositionObjectChangeIndicator()).isTrue();
        assertThat(activeFunding.isPositionSalaryChangeIndicator()).isTrue();
    }
}
