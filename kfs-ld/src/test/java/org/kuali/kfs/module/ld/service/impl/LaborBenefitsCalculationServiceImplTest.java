package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.ld.LaborLedgerObject;
import org.kuali.kfs.module.ld.LaborConstants;
import org.kuali.kfs.module.ld.businessobject.BenefitsCalculation;
import org.kuali.kfs.module.ld.businessobject.LaborObject;
import org.kuali.kfs.module.ld.businessobject.PositionObjectBenefit;
import org.kuali.kfs.module.ld.service.LaborPositionObjectBenefitService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LaborBenefitsCalculationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private LaborPositionObjectBenefitService laborPositionObjectBenefitService;

    @InjectMocks
    private LaborBenefitsCalculationServiceImpl service;

    @Test
    void testGetBenefitsCalculationDelegatesToBusinessObjectService() {
        BenefitsCalculation expected = new BenefitsCalculation();
        when(businessObjectService.findByPrimaryKey(eq(BenefitsCalculation.class), any(Map.class)))
                .thenReturn(expected);

        BenefitsCalculation result = service.getBenefitsCalculation(2024, "BL", "HI");

        assertThat(result).isSameAs(expected);
        verify(businessObjectService).findByPrimaryKey(eq(BenefitsCalculation.class), any(Map.class));
    }

    @Test
    void testGetBenefitsCalculationWithRateCategoryCode() {
        BenefitsCalculation expected = new BenefitsCalculation();
        when(businessObjectService.findByPrimaryKey(eq(BenefitsCalculation.class), any(Map.class)))
                .thenReturn(expected);

        BenefitsCalculation result = service.getBenefitsCalculation(2024, "BL", "HI", "RC");

        assertThat(result).isSameAs(expected);
    }

    @Test
    void testCalculateFringeBenefitReturnsZeroForNullSalary() {
        LaborObject laborObject = new LaborObject();
        KualiDecimal result = service.calculateFringeBenefit((LaborLedgerObject) laborObject, null, "1234567", "-----");
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testCalculateFringeBenefitReturnsZeroForZeroSalary() {
        LaborObject laborObject = new LaborObject();
        KualiDecimal result = service.calculateFringeBenefit((LaborLedgerObject) laborObject, KualiDecimal.ZERO, "1234567", "-----");
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testCalculateFringeBenefitReturnsZeroForNullLaborObject() {
        KualiDecimal result = service.calculateFringeBenefit((LaborLedgerObject) null, new KualiDecimal(1000), "1234567", "-----");
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testCalculateFringeBenefitReturnsZeroForNonSalaryCode() {
        LaborObject laborObject = new LaborObject();
        laborObject.setFinancialObjectFringeOrSalaryCode("F");

        KualiDecimal result = service.calculateFringeBenefit((LaborLedgerObject) laborObject, new KualiDecimal(1000), "1234567", "-----");
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testCalculateFringeBenefitForSalaryCodeWithNoPositionBenefits() {
        LaborObject laborObject = new LaborObject();
        laborObject.setFinancialObjectFringeOrSalaryCode(LaborConstants.SalaryExpenseTransfer.LABOR_LEDGER_SALARY_CODE);
        laborObject.setUniversityFiscalYear(2024);
        laborObject.setChartOfAccountsCode("BL");
        laborObject.setFinancialObjectCode("5000");

        when(laborPositionObjectBenefitService.getActivePositionObjectBenefits(2024, "BL", "5000"))
                .thenReturn(Collections.emptyList());

        KualiDecimal result = service.calculateFringeBenefit((LaborLedgerObject) laborObject, new KualiDecimal(1000), "1234567", "-----");
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testCalculateFringeBenefitForPositionObjectBenefitReturnsZeroForNullPOB() {
        KualiDecimal result = service.calculateFringeBenefit((PositionObjectBenefit) null, new KualiDecimal(1000), "1234567", "-----");
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testCalculateFringeBenefitForPositionObjectBenefitReturnsZeroForNullSalary() {
        PositionObjectBenefit pob = new PositionObjectBenefit();
        KualiDecimal result = service.calculateFringeBenefit(pob, null, "1234567", "-----");
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testCalculateFringeBenefitForPositionObjectBenefitReturnsZeroForZeroSalary() {
        PositionObjectBenefit pob = new PositionObjectBenefit();
        KualiDecimal result = service.calculateFringeBenefit(pob, KualiDecimal.ZERO, "1234567", "-----");
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }
}
