package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class LaborObjectTest extends KfsUnitTestBase {

    private LaborObject laborObject;

    @BeforeEach
    void setUp() {
        laborObject = new LaborObject();
    }

    @Test
    void testSetAndGetUniversityFiscalYear() {
        laborObject.setUniversityFiscalYear(2024);
        assertThat(laborObject.getUniversityFiscalYear()).isEqualTo(2024);
    }

    @Test
    void testSetAndGetChartOfAccountsCode() {
        laborObject.setChartOfAccountsCode("BL");
        assertThat(laborObject.getChartOfAccountsCode()).isEqualTo("BL");
    }

    @Test
    void testSetAndGetFinancialObjectCode() {
        laborObject.setFinancialObjectCode("5000");
        assertThat(laborObject.getFinancialObjectCode()).isEqualTo("5000");
    }

    @Test
    void testSetAndGetDetailPositionRequiredIndicator() {
        laborObject.setDetailPositionRequiredIndicator(true);
        assertThat(laborObject.isDetailPositionRequiredIndicator()).isTrue();
    }

    @Test
    void testSetAndGetFinancialObjectHoursRequiredIndicator() {
        laborObject.setFinancialObjectHoursRequiredIndicator(true);
        assertThat(laborObject.isFinancialObjectHoursRequiredIndicator()).isTrue();
    }

    @Test
    void testSetAndGetFinancialObjectPayTypeCode() {
        laborObject.setFinancialObjectPayTypeCode("S");
        assertThat(laborObject.getFinancialObjectPayTypeCode()).isEqualTo("S");
    }

    @Test
    void testSetAndGetFinancialObjectFringeOrSalaryCode() {
        laborObject.setFinancialObjectFringeOrSalaryCode("S");
        assertThat(laborObject.getFinancialObjectFringeOrSalaryCode()).isEqualTo("S");
    }

    @Test
    void testSetAndGetPositionObjectGroupCode() {
        laborObject.setPositionObjectGroupCode("AC");
        assertThat(laborObject.getPositionObjectGroupCode()).isEqualTo("AC");
    }

    @Test
    void testSetAndGetActive() {
        laborObject.setActive(true);
        assertThat(laborObject.isActive()).isTrue();
        laborObject.setActive(false);
        assertThat(laborObject.isActive()).isFalse();
    }

    @Test
    void testSetAndGetFinancialObject() {
        ObjectCode oc = new ObjectCode();
        laborObject.setFinancialObject(oc);
        assertThat(laborObject.getFinancialObject()).isSameAs(oc);
    }

    @Test
    void testSetAndGetChartOfAccounts() {
        Chart chart = new Chart();
        laborObject.setChartOfAccounts(chart);
        assertThat(laborObject.getChartOfAccounts()).isSameAs(chart);
    }

    @Test
    void testSetAndGetPositionObjectGroup() {
        PositionObjectGroup pog = new PositionObjectGroup();
        laborObject.setPositionObjectGroup(pog);
        assertThat(laborObject.getPositionObjectGroup()).isSameAs(pog);
    }

    @Test
    void testDefaultValuesAreNull() {
        assertThat(laborObject.getUniversityFiscalYear()).isNull();
        assertThat(laborObject.getChartOfAccountsCode()).isNull();
        assertThat(laborObject.getFinancialObjectCode()).isNull();
    }
}
