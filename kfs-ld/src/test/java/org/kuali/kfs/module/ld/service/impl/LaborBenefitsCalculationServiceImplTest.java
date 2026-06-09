package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.BenefitsCalculation;
import org.kuali.kfs.module.ld.businessobject.LaborObject;
import org.kuali.kfs.module.ld.businessobject.PositionObjectBenefit;
import org.kuali.kfs.module.ld.service.LaborPositionObjectBenefitService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
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
    void testGetBenefitsCalculation_threeArgs() {
        BenefitsCalculation expected = new BenefitsCalculation();
        when(businessObjectService.findByPrimaryKey(eq(BenefitsCalculation.class), any(Map.class))).thenReturn(expected);

        BenefitsCalculation result = service.getBenefitsCalculation(2014, "BL", "MX");
        assertSame(expected, result);
        verify(businessObjectService).findByPrimaryKey(eq(BenefitsCalculation.class), any(Map.class));
    }

    @Test
    void testGetBenefitsCalculation_fourArgs() {
        BenefitsCalculation expected = new BenefitsCalculation();
        when(businessObjectService.findByPrimaryKey(eq(BenefitsCalculation.class), any(Map.class))).thenReturn(expected);

        BenefitsCalculation result = service.getBenefitsCalculation(2014, "BL", "MX", "RC");
        assertSame(expected, result);
    }

    @Test
    void testGetBenefitsCalculation_returnsNull() {
        when(businessObjectService.findByPrimaryKey(eq(BenefitsCalculation.class), any(Map.class))).thenReturn(null);

        BenefitsCalculation result = service.getBenefitsCalculation(2014, "BL", "MX");
        assertNull(result);
    }

    @Test
    void testCalculateFringeBenefit_nullSalary() {
        LaborObject laborObject = new LaborObject();
        KualiDecimal result = service.calculateFringeBenefit(laborObject, null, "1234567", "sub1");
        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    void testCalculateFringeBenefit_zeroSalary() {
        LaborObject laborObject = new LaborObject();
        KualiDecimal result = service.calculateFringeBenefit(laborObject, KualiDecimal.ZERO, "1234567", "sub1");
        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    void testCalculateFringeBenefit_nullLaborObject() {
        KualiDecimal result = service.calculateFringeBenefit((LaborObject) null, new KualiDecimal(1000), "1234567", "sub1");
        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    void testCalculateFringeBenefit_nonSalaryCode() {
        LaborObject laborObject = new LaborObject();
        laborObject.setFinancialObjectFringeOrSalaryCode("F");

        KualiDecimal result = service.calculateFringeBenefit(laborObject, new KualiDecimal(1000), "1234567", "sub1");
        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    void testCalculateFringeBenefit_salaryCodeNoPositionBenefits() {
        LaborObject laborObject = new LaborObject();
        laborObject.setFinancialObjectFringeOrSalaryCode("S");
        laborObject.setUniversityFiscalYear(2014);
        laborObject.setChartOfAccountsCode("BL");
        laborObject.setFinancialObjectCode("5000");

        Collection<PositionObjectBenefit> emptyBenefits = new ArrayList<PositionObjectBenefit>();
        when(laborPositionObjectBenefitService.getActivePositionObjectBenefits(2014, "BL", "5000")).thenReturn(emptyBenefits);

        KualiDecimal result = service.calculateFringeBenefit(laborObject, new KualiDecimal(1000), "1234567", "sub1");
        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    void testCalculateFringeBenefit_positionObjectBenefit_nullSalary() {
        PositionObjectBenefit pob = new PositionObjectBenefit();
        KualiDecimal result = service.calculateFringeBenefit(pob, null, "1234567", "sub1");
        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    void testCalculateFringeBenefit_positionObjectBenefit_zeroSalary() {
        PositionObjectBenefit pob = new PositionObjectBenefit();
        KualiDecimal result = service.calculateFringeBenefit(pob, KualiDecimal.ZERO, "1234567", "sub1");
        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    void testCalculateFringeBenefit_positionObjectBenefit_nullBenefit() {
        KualiDecimal result = service.calculateFringeBenefit((PositionObjectBenefit) null, new KualiDecimal(1000), "1234567", "sub1");
        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    void testCalculateFringeBenefit_byObjectCode_nullLaborObject() {
        when(businessObjectService.retrieve(any(LaborObject.class))).thenReturn(null);

        KualiDecimal result = service.calculateFringeBenefit(2014, "BL", "5000", new KualiDecimal(1000), "1234567", "sub1");
        assertEquals(KualiDecimal.ZERO, result);
    }
}
