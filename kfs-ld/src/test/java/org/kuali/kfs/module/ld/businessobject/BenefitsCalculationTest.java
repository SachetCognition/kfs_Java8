package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiPercent;

import static org.assertj.core.api.Assertions.assertThat;

class BenefitsCalculationTest extends KfsUnitTestBase {

    private BenefitsCalculation benefitsCalculation;

    @BeforeEach
    void setUp() {
        benefitsCalculation = new BenefitsCalculation();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(benefitsCalculation).isNotNull();
    }

    @Test
    void testSetAndGetUniversityFiscalYear() {
        benefitsCalculation.setUniversityFiscalYear(2024);
        assertThat(benefitsCalculation.getUniversityFiscalYear()).isEqualTo(2024);
    }

    @Test
    void testSetAndGetChartOfAccountsCode() {
        benefitsCalculation.setChartOfAccountsCode("BL");
        assertThat(benefitsCalculation.getChartOfAccountsCode()).isEqualTo("BL");
    }

    @Test
    void testSetAndGetPositionBenefitTypeCode() {
        benefitsCalculation.setPositionBenefitTypeCode("HI");
        assertThat(benefitsCalculation.getPositionBenefitTypeCode()).isEqualTo("HI");
    }

    @Test
    void testSetAndGetPositionFringeBenefitPercent() {
        KualiPercent percent = new KualiPercent(25.5);
        benefitsCalculation.setPositionFringeBenefitPercent(percent);
        assertThat(benefitsCalculation.getPositionFringeBenefitPercent()).isEqualTo(percent);
    }

    @Test
    void testSetAndGetPositionFringeBenefitObjectCode() {
        benefitsCalculation.setPositionFringeBenefitObjectCode("5100");
        assertThat(benefitsCalculation.getPositionFringeBenefitObjectCode()).isEqualTo("5100");
    }

    @Test
    void testSetAndGetPositionFringeBenefitObject() {
        ObjectCode objCode = new ObjectCode();
        benefitsCalculation.setPositionFringeBenefitObject(objCode);
        assertThat(benefitsCalculation.getPositionFringeBenefitObject()).isSameAs(objCode);
    }

    @Test
    void testSetAndGetChartOfAccounts() {
        Chart chart = new Chart();
        benefitsCalculation.setChartOfAccounts(chart);
        assertThat(benefitsCalculation.getChartOfAccounts()).isSameAs(chart);
    }

    @Test
    void testSetAndGetPositionBenefitType() {
        BenefitsType bt = new BenefitsType();
        benefitsCalculation.setPositionBenefitType(bt);
        assertThat(benefitsCalculation.getPositionBenefitType()).isSameAs(bt);
    }

    @Test
    void testSetAndGetActive() {
        benefitsCalculation.setActive(true);
        assertThat(benefitsCalculation.isActive()).isTrue();

        benefitsCalculation.setActive(false);
        assertThat(benefitsCalculation.isActive()).isFalse();
    }

    @Test
    void testSetAndGetLaborBenefitRateCategoryCode() {
        benefitsCalculation.setLaborBenefitRateCategoryCode("RC");
        assertThat(benefitsCalculation.getLaborBenefitRateCategoryCode()).isEqualTo("RC");
    }

    @Test
    void testSetAndGetAccountCodeOffset() {
        benefitsCalculation.setAccountCodeOffset("1234567");
        assertThat(benefitsCalculation.getAccountCodeOffset()).isEqualTo("1234567");
    }

    @Test
    void testSetAndGetObjectCodeOffset() {
        benefitsCalculation.setObjectCodeOffset("5200");
        assertThat(benefitsCalculation.getObjectCodeOffset()).isEqualTo("5200");
    }

    @Test
    void testSetAndGetLaborObject() {
        LaborObject lo = new LaborObject();
        benefitsCalculation.setLaborObject(lo);
        assertThat(benefitsCalculation.getLaborObject()).isSameAs(lo);
    }

    @Test
    void testSetAndGetLaborBenefitRateCategory() {
        LaborBenefitRateCategory lbrc = new LaborBenefitRateCategory();
        benefitsCalculation.setLaborBenefitRateCategory(lbrc);
        assertThat(benefitsCalculation.getLaborBenefitRateCategory()).isSameAs(lbrc);
    }

    @Test
    void testSetAndGetLaborAccountOffset() {
        Account account = new Account();
        benefitsCalculation.setLaborAccountOffset(account);
        assertThat(benefitsCalculation.getLaborAccountOffset()).isSameAs(account);
    }

    @Test
    void testSetAndGetLaborObjectCodeOffset() {
        ObjectCode oc = new ObjectCode();
        benefitsCalculation.setLaborObjectCodeOffset(oc);
        assertThat(benefitsCalculation.getLaborObjectCodeOffset()).isSameAs(oc);
    }
}
