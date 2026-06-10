package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class PositionObjectBenefitTest extends KfsUnitTestBase {

    private PositionObjectBenefit positionObjectBenefit;

    @BeforeEach
    void setUp() {
        positionObjectBenefit = new PositionObjectBenefit();
    }

    @Test
    void testSetAndGetUniversityFiscalYear() {
        positionObjectBenefit.setUniversityFiscalYear(2024);
        assertThat(positionObjectBenefit.getUniversityFiscalYear()).isEqualTo(2024);
    }

    @Test
    void testSetAndGetChartOfAccountsCode() {
        positionObjectBenefit.setChartOfAccountsCode("BL");
        assertThat(positionObjectBenefit.getChartOfAccountsCode()).isEqualTo("BL");
    }

    @Test
    void testSetAndGetFinancialObjectCode() {
        positionObjectBenefit.setFinancialObjectCode("5000");
        assertThat(positionObjectBenefit.getFinancialObjectCode()).isEqualTo("5000");
    }

    @Test
    void testSetAndGetFinancialObjectBenefitsTypeCode() {
        positionObjectBenefit.setFinancialObjectBenefitsTypeCode("HI");
        assertThat(positionObjectBenefit.getFinancialObjectBenefitsTypeCode()).isEqualTo("HI");
    }

    @Test
    void testSetAndGetChartOfAccounts() {
        Chart chart = new Chart();
        positionObjectBenefit.setChartOfAccounts(chart);
        assertThat(positionObjectBenefit.getChartOfAccounts()).isSameAs(chart);
    }

    @Test
    void testSetAndGetActive() {
        positionObjectBenefit.setActive(true);
        assertThat(positionObjectBenefit.isActive()).isTrue();
        positionObjectBenefit.setActive(false);
        assertThat(positionObjectBenefit.isActive()).isFalse();
    }

    @Test
    void testSetAndGetFinancialObject() {
        ObjectCode oc = new ObjectCode();
        positionObjectBenefit.setFinancialObject(oc);
        assertThat(positionObjectBenefit.getFinancialObject()).isSameAs(oc);
    }

    @Test
    void testSetAndGetBenefitsCalculation() {
        BenefitsCalculation bc = new BenefitsCalculation();
        positionObjectBenefit.setBenefitsCalculation(bc);
        assertThat(positionObjectBenefit.getBenefitsCalculation()).isSameAs(bc);
    }

    @Test
    void testSetAndGetFinancialObjectBenefitsType() {
        BenefitsType bt = new BenefitsType();
        positionObjectBenefit.setFinancialObjectBenefitsType(bt);
        assertThat(positionObjectBenefit.getFinancialObjectBenefitsType()).isSameAs(bt);
    }

    @Test
    void testSetAndGetLaborObject() {
        LaborObject lo = new LaborObject();
        positionObjectBenefit.setLaborObject(lo);
        assertThat(positionObjectBenefit.getLaborObject()).isSameAs(lo);
    }

    @Test
    void testSetAndGetLaborBenefitRateCategoryCode() {
        positionObjectBenefit.setLaborBenefitRateCategoryCode("RC");
        assertThat(positionObjectBenefit.getLaborBenefitRateCategoryCode()).isEqualTo("RC");
    }
}
