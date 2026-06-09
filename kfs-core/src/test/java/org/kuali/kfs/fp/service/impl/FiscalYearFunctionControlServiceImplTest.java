package org.kuali.kfs.fp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.businessobject.FiscalYearFunctionControl;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class FiscalYearFunctionControlServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private FiscalYearFunctionControlServiceImpl service;

    @Test
    void isBaseAmountChangeAllowedReturnsTrueWhenActive() {
        FiscalYearFunctionControl control = new FiscalYearFunctionControl();
        control.setUniversityFiscalYear(2024);
        control.setFinancialSystemFunctionControlCode("BASEAD");
        control.setFinancialSystemFunctionActiveIndicator(true);

        when(businessObjectService.findByPrimaryKey(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(control);

        assertThat(service.isBaseAmountChangeAllowed(2024)).isTrue();
    }

    @Test
    void isBaseAmountChangeAllowedReturnsFalseWhenInactive() {
        FiscalYearFunctionControl control = new FiscalYearFunctionControl();
        control.setUniversityFiscalYear(2024);
        control.setFinancialSystemFunctionControlCode("BASEAD");
        control.setFinancialSystemFunctionActiveIndicator(false);

        when(businessObjectService.findByPrimaryKey(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(control);

        assertThat(service.isBaseAmountChangeAllowed(2024)).isFalse();
    }

    @Test
    void isBaseAmountChangeAllowedReturnsFalseWhenNotFound() {
        when(businessObjectService.findByPrimaryKey(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(null);

        assertThat(service.isBaseAmountChangeAllowed(2024)).isFalse();
    }

    @Test
    void getBudgetAdjustmentAllowedYearsReturnsMatchingControls() {
        Collection<FiscalYearFunctionControl> controls = new ArrayList<>();
        FiscalYearFunctionControl control1 = new FiscalYearFunctionControl();
        control1.setUniversityFiscalYear(2024);
        control1.setFinancialSystemFunctionControlCode("BAACTV");
        controls.add(control1);

        FiscalYearFunctionControl control2 = new FiscalYearFunctionControl();
        control2.setUniversityFiscalYear(2025);
        control2.setFinancialSystemFunctionControlCode("BAACTV");
        controls.add(control2);

        when(businessObjectService.findMatching(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(controls);

        List result = service.getBudgetAdjustmentAllowedYears();
        assertThat(result).hasSize(2);
    }

    @Test
    void getBudgetAdjustmentAllowedYearsReturnsEmptyListWhenNone() {
        when(businessObjectService.findMatching(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(new ArrayList<>());

        List result = service.getBudgetAdjustmentAllowedYears();
        assertThat(result).isEmpty();
    }

    @Test
    void getActiveBudgetYearReturnsYearIntegers() {
        Collection<FiscalYearFunctionControl> controls = new ArrayList<>();
        FiscalYearFunctionControl control1 = new FiscalYearFunctionControl();
        control1.setUniversityFiscalYear(2024);
        controls.add(control1);

        FiscalYearFunctionControl control2 = new FiscalYearFunctionControl();
        control2.setUniversityFiscalYear(2025);
        controls.add(control2);

        when(businessObjectService.findMatching(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(controls);

        List<Integer> years = service.getActiveBudgetYear();
        assertThat(years).containsExactly(2024, 2025);
    }

    @Test
    void getActiveBudgetYearReturnsEmptyListWhenNone() {
        when(businessObjectService.findMatching(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(new ArrayList<>());

        List<Integer> years = service.getActiveBudgetYear();
        assertThat(years).isEmpty();
    }

    @Test
    void isBudgetConstructionActiveReturnsTrueWhenActive() {
        FiscalYearFunctionControl control = new FiscalYearFunctionControl();
        control.setFinancialSystemFunctionActiveIndicator(true);

        when(businessObjectService.findByPrimaryKey(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(control);

        assertThat(service.isBudgetConstructionActive(2024)).isTrue();
    }

    @Test
    void isBudgetConstructionActiveReturnsFalseWhenNotFound() {
        when(businessObjectService.findByPrimaryKey(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(null);

        assertThat(service.isBudgetConstructionActive(2024)).isFalse();
    }

    @Test
    void isBudgetUpdateAllowedDelegatesToGetActiveInd() {
        FiscalYearFunctionControl control = new FiscalYearFunctionControl();
        control.setFinancialSystemFunctionActiveIndicator(true);

        when(businessObjectService.findByPrimaryKey(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(control);

        assertThat(service.isBudgetUpdateAllowed(2024)).isTrue();
    }

    @Test
    void isBudgetGeneralLedgerUpdateAllowedDelegatesToGetActiveInd() {
        FiscalYearFunctionControl control = new FiscalYearFunctionControl();
        control.setFinancialSystemFunctionActiveIndicator(true);

        when(businessObjectService.findByPrimaryKey(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(control);

        assertThat(service.isBudgetGeneralLedgerUpdateAllowed(2024)).isTrue();
    }

    @Test
    void isApplicationUpdateFromHumanResourcesAllowedDelegatesToGetActiveInd() {
        FiscalYearFunctionControl control = new FiscalYearFunctionControl();
        control.setFinancialSystemFunctionActiveIndicator(true);

        when(businessObjectService.findByPrimaryKey(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(control);

        assertThat(service.isApplicationUpdateFromHumanResourcesAllowed(2024)).isTrue();
    }

    @Test
    void isBatchUpdateFromHumanResourcesAllowedDelegatesToGetActiveInd() {
        FiscalYearFunctionControl control = new FiscalYearFunctionControl();
        control.setFinancialSystemFunctionActiveIndicator(true);

        when(businessObjectService.findByPrimaryKey(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(control);

        assertThat(service.isBatchUpdateFromHumanResourcesAllowed(2024)).isTrue();
    }

    @Test
    void isBatchUpdateFromPayrollAllowedDelegatesToGetActiveInd() {
        FiscalYearFunctionControl control = new FiscalYearFunctionControl();
        control.setFinancialSystemFunctionActiveIndicator(true);

        when(businessObjectService.findByPrimaryKey(eq(FiscalYearFunctionControl.class), any()))
                .thenReturn(control);

        assertThat(service.isBatchUpdateFromPayrollAllowed(2024)).isTrue();
    }

    @Test
    void setAndGetBusinessObjectService() {
        BusinessObjectService newBos = org.mockito.Mockito.mock(BusinessObjectService.class);
        service.setBusinessObjectService(newBos);
        assertThat(service.getBusinessObjectService()).isSameAs(newBos);
    }
}
