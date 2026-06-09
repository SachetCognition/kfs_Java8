package org.kuali.kfs.module.bc.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiInteger;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetConstructionCalculatedSalaryFoundationTrackerTest extends KfsUnitTestBase {

    @Test
    void constructor_initializesDefaults() {
        BudgetConstructionCalculatedSalaryFoundationTracker tracker = new BudgetConstructionCalculatedSalaryFoundationTracker();
        assertThat(tracker.getCsfAmount()).isNull();
        assertThat(tracker.getCsfTimePercent()).isNull();
        assertThat(tracker.getCsfFullTimeEmploymentQuantity()).isNull();
    }

    @Test
    void setAndGetProperties() {
        BudgetConstructionCalculatedSalaryFoundationTracker tracker = new BudgetConstructionCalculatedSalaryFoundationTracker();

        tracker.setUniversityFiscalYear(2024);
        tracker.setChartOfAccountsCode("UA");
        tracker.setAccountNumber("1234567");
        tracker.setSubAccountNumber("-----");
        tracker.setFinancialObjectCode("5000");
        tracker.setFinancialSubObjectCode("000");
        tracker.setPositionNumber("POS001");
        tracker.setEmplid("EMP001");
        tracker.setCsfAmount(new KualiInteger(50000));
        tracker.setCsfTimePercent(new BigDecimal("100.00"));
        tracker.setCsfFullTimeEmploymentQuantity(new BigDecimal("1.00000"));
        tracker.setCsfFundingStatusCode("A");

        assertThat(tracker.getUniversityFiscalYear()).isEqualTo(2024);
        assertThat(tracker.getChartOfAccountsCode()).isEqualTo("UA");
        assertThat(tracker.getAccountNumber()).isEqualTo("1234567");
        assertThat(tracker.getSubAccountNumber()).isEqualTo("-----");
        assertThat(tracker.getFinancialObjectCode()).isEqualTo("5000");
        assertThat(tracker.getFinancialSubObjectCode()).isEqualTo("000");
        assertThat(tracker.getPositionNumber()).isEqualTo("POS001");
        assertThat(tracker.getEmplid()).isEqualTo("EMP001");
        assertThat(tracker.getCsfAmount()).isEqualTo(new KualiInteger(50000));
        assertThat(tracker.getCsfTimePercent()).isEqualByComparingTo(new BigDecimal("100.00"));
        assertThat(tracker.getCsfFullTimeEmploymentQuantity()).isEqualByComparingTo(new BigDecimal("1.00000"));
        assertThat(tracker.getCsfFundingStatusCode()).isEqualTo("A");
    }
}
