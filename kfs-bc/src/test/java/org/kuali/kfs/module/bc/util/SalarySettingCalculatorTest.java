package org.kuali.kfs.module.bc.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionCalculatedSalaryFoundationTracker;
import org.kuali.kfs.module.bc.businessobject.PendingBudgetConstructionAppointmentFunding;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.core.api.util.type.KualiInteger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SalarySettingCalculatorTest extends KfsUnitTestBase {

    private PendingBudgetConstructionAppointmentFunding createFunding(
            KualiInteger csfAmount, BigDecimal csfTimePercent, BigDecimal csfFteQty,
            KualiInteger requestedAmount, BigDecimal requestedTimePercent, BigDecimal requestedFteQty) {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setAppointmentRequestedCsfAmount(csfAmount);
        funding.setAppointmentRequestedCsfTimePercent(csfTimePercent);
        funding.setAppointmentRequestedCsfFteQuantity(csfFteQty);
        funding.setAppointmentRequestedAmount(requestedAmount);
        funding.setAppointmentRequestedTimePercent(requestedTimePercent);
        funding.setAppointmentRequestedFteQuantity(requestedFteQty);
        return funding;
    }

    @Test
    void getAppointmentRequestedCsfAmountTotal_sumsAmounts() {
        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(createFunding(new KualiInteger(10000), null, null, null, null, null));
        fundings.add(createFunding(new KualiInteger(20000), null, null, null, null, null));

        KualiInteger total = SalarySettingCalculator.getAppointmentRequestedCsfAmountTotal(fundings);
        assertThat(total).isEqualTo(new KualiInteger(30000));
    }

    @Test
    void getAppointmentRequestedCsfAmountTotal_withNulls_skipsNull() {
        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(createFunding(new KualiInteger(5000), null, null, null, null, null));
        fundings.add(createFunding(null, null, null, null, null, null));

        KualiInteger total = SalarySettingCalculator.getAppointmentRequestedCsfAmountTotal(fundings);
        assertThat(total).isEqualTo(new KualiInteger(5000));
    }

    @Test
    void getAppointmentRequestedCsfAmountTotal_emptyList_returnsZero() {
        KualiInteger total = SalarySettingCalculator.getAppointmentRequestedCsfAmountTotal(Collections.emptyList());
        assertThat(total).isEqualTo(KualiInteger.ZERO);
    }

    @Test
    void getAppointmentRequestedCsfTimePercentTotal_sumsPercents() {
        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(createFunding(null, new BigDecimal("50.00"), null, null, null, null));
        fundings.add(createFunding(null, new BigDecimal("25.00"), null, null, null, null));

        BigDecimal total = SalarySettingCalculator.getAppointmentRequestedCsfTimePercentTotal(fundings);
        assertThat(total).isEqualByComparingTo(new BigDecimal("75.00"));
    }

    @Test
    void getAppointmentRequestedCsfTimePercentTotal_withNulls_skipsNull() {
        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(createFunding(null, new BigDecimal("100.00"), null, null, null, null));
        fundings.add(createFunding(null, null, null, null, null, null));

        BigDecimal total = SalarySettingCalculator.getAppointmentRequestedCsfTimePercentTotal(fundings);
        assertThat(total).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void getAppointmentRequestedCsfFteQuantityTotal_sumsQuantities() {
        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(createFunding(null, null, new BigDecimal("1.00000"), null, null, null));
        fundings.add(createFunding(null, null, new BigDecimal("0.50000"), null, null, null));

        BigDecimal total = SalarySettingCalculator.getAppointmentRequestedCsfFteQuantityTotal(fundings);
        assertThat(total).isEqualByComparingTo(new BigDecimal("1.50000"));
    }

    @Test
    void getAppointmentRequestedAmountTotal_sumsAmounts() {
        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(createFunding(null, null, null, new KualiInteger(30000), null, null));
        fundings.add(createFunding(null, null, null, new KualiInteger(45000), null, null));

        KualiInteger total = SalarySettingCalculator.getAppointmentRequestedAmountTotal(fundings);
        assertThat(total).isEqualTo(new KualiInteger(75000));
    }

    @Test
    void getAppointmentRequestedAmountTotal_emptyList_returnsZero() {
        KualiInteger total = SalarySettingCalculator.getAppointmentRequestedAmountTotal(Collections.emptyList());
        assertThat(total).isEqualTo(KualiInteger.ZERO);
    }

    @Test
    void getAppointmentRequestedTimePercentTotal_sumsPercents() {
        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(createFunding(null, null, null, null, new BigDecimal("60.00"), null));
        fundings.add(createFunding(null, null, null, null, new BigDecimal("40.00"), null));

        BigDecimal total = SalarySettingCalculator.getAppointmentRequestedTimePercentTotal(fundings);
        assertThat(total).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void getAppointmentRequestedFteQuantityTotal_sumsQuantities() {
        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(createFunding(null, null, null, null, null, new BigDecimal("0.75")));
        fundings.add(createFunding(null, null, null, null, null, new BigDecimal("0.25")));

        BigDecimal total = SalarySettingCalculator.getAppointmentRequestedFteQuantityTotal(fundings);
        assertThat(total).isEqualByComparingTo(BigDecimal.ONE);
    }

    @Test
    void getPercentChange_calculatesCorrectly() {
        KualiInteger baseAmount = new KualiInteger(50000);
        KualiInteger requestedAmount = new KualiInteger(55000);

        KualiDecimal percentChange = SalarySettingCalculator.getPercentChange(baseAmount, requestedAmount);
        assertThat(percentChange).isNotNull();
        assertThat(percentChange).isEqualTo(new KualiDecimal(10));
    }

    @Test
    void getPercentChange_withNullBase_returnsNull() {
        KualiDecimal result = SalarySettingCalculator.getPercentChange(null, new KualiInteger(100));
        assertThat(result).isNull();
    }

    @Test
    void getPercentChange_withNullRequested_returnsNull() {
        KualiDecimal result = SalarySettingCalculator.getPercentChange(new KualiInteger(100), null);
        assertThat(result).isNull();
    }

    @Test
    void getPercentChange_withZeroBase_returnsNull() {
        KualiDecimal result = SalarySettingCalculator.getPercentChange(KualiInteger.ZERO, new KualiInteger(100));
        assertThat(result).isNull();
    }

    @Test
    void getPercentChange_sameAmount_returnsZero() {
        KualiInteger amount = new KualiInteger(50000);
        KualiDecimal result = SalarySettingCalculator.getPercentChange(amount, amount);
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void getPercentChange_decrease() {
        KualiInteger baseAmount = new KualiInteger(100);
        KualiInteger requestedAmount = new KualiInteger(75);

        KualiDecimal result = SalarySettingCalculator.getPercentChange(baseAmount, requestedAmount);
        assertThat(result).isEqualTo(new KualiDecimal(-25));
    }

    @Test
    void getEffectiveAppointmentFundings_excludesPurgedAndExcluded() {
        PendingBudgetConstructionAppointmentFunding normal = new PendingBudgetConstructionAppointmentFunding();
        normal.setExcludedFromTotal(false);
        normal.setPurged(false);

        PendingBudgetConstructionAppointmentFunding excluded = new PendingBudgetConstructionAppointmentFunding();
        excluded.setExcludedFromTotal(true);
        excluded.setPurged(false);

        PendingBudgetConstructionAppointmentFunding purged = new PendingBudgetConstructionAppointmentFunding();
        purged.setExcludedFromTotal(false);
        purged.setPurged(true);

        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(normal);
        fundings.add(excluded);
        fundings.add(purged);

        List<PendingBudgetConstructionAppointmentFunding> effective =
                SalarySettingCalculator.getEffectiveAppointmentFundings(fundings);

        assertThat(effective).hasSize(1);
        assertThat(effective.get(0)).isSameAs(normal);
    }

    @Test
    void getEffectiveAppointmentFundings_includesDeletedNotExcluded() {
        PendingBudgetConstructionAppointmentFunding deleted = new PendingBudgetConstructionAppointmentFunding();
        deleted.setAppointmentFundingDeleteIndicator(true);
        deleted.setExcludedFromTotal(false);
        deleted.setPurged(false);

        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(deleted);

        List<PendingBudgetConstructionAppointmentFunding> effective =
                SalarySettingCalculator.getEffectiveAppointmentFundings(fundings);

        assertThat(effective).hasSize(1);
    }

    @Test
    void getEffectiveAppointmentFundings_emptyList() {
        List<PendingBudgetConstructionAppointmentFunding> effective =
                SalarySettingCalculator.getEffectiveAppointmentFundings(Collections.emptyList());
        assertThat(effective).isEmpty();
    }

    @Test
    void getCsfAmountTotal_sumsCsfTrackerAmounts() {
        PendingBudgetConstructionAppointmentFunding f1 = new PendingBudgetConstructionAppointmentFunding();
        BudgetConstructionCalculatedSalaryFoundationTracker tracker1 = new BudgetConstructionCalculatedSalaryFoundationTracker();
        tracker1.setCsfAmount(new KualiInteger(10000));
        f1.setBcnCalculatedSalaryFoundationTracker(new ArrayList<>());
        f1.getBcnCalculatedSalaryFoundationTracker().add(tracker1);

        PendingBudgetConstructionAppointmentFunding f2 = new PendingBudgetConstructionAppointmentFunding();
        BudgetConstructionCalculatedSalaryFoundationTracker tracker2 = new BudgetConstructionCalculatedSalaryFoundationTracker();
        tracker2.setCsfAmount(new KualiInteger(20000));
        f2.setBcnCalculatedSalaryFoundationTracker(new ArrayList<>());
        f2.getBcnCalculatedSalaryFoundationTracker().add(tracker2);

        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(f1);
        fundings.add(f2);

        KualiInteger total = SalarySettingCalculator.getCsfAmountTotal(fundings);
        assertThat(total).isEqualTo(new KualiInteger(30000));
    }

    @Test
    void getCsfAmountTotal_skipsNullTracker() {
        PendingBudgetConstructionAppointmentFunding f1 = new PendingBudgetConstructionAppointmentFunding();

        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(f1);

        KualiInteger total = SalarySettingCalculator.getCsfAmountTotal(fundings);
        assertThat(total).isEqualTo(KualiInteger.ZERO);
    }

    @Test
    void getCsfTimePercentTotal_sumsCsfTrackerTimePercents() {
        PendingBudgetConstructionAppointmentFunding f1 = new PendingBudgetConstructionAppointmentFunding();
        BudgetConstructionCalculatedSalaryFoundationTracker tracker1 = new BudgetConstructionCalculatedSalaryFoundationTracker();
        tracker1.setCsfTimePercent(new BigDecimal("50.00"));
        f1.setBcnCalculatedSalaryFoundationTracker(new ArrayList<>());
        f1.getBcnCalculatedSalaryFoundationTracker().add(tracker1);

        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(f1);

        BigDecimal total = SalarySettingCalculator.getCsfTimePercentTotal(fundings);
        assertThat(total).isEqualByComparingTo(new BigDecimal("50.00"));
    }

    @Test
    void getCsfFullTimeEmploymentQuantityTotal_sumsFteQuantities() {
        PendingBudgetConstructionAppointmentFunding f1 = new PendingBudgetConstructionAppointmentFunding();
        BudgetConstructionCalculatedSalaryFoundationTracker tracker1 = new BudgetConstructionCalculatedSalaryFoundationTracker();
        tracker1.setCsfFullTimeEmploymentQuantity(new BigDecimal("0.75"));
        f1.setBcnCalculatedSalaryFoundationTracker(new ArrayList<>());
        f1.getBcnCalculatedSalaryFoundationTracker().add(tracker1);

        List<PendingBudgetConstructionAppointmentFunding> fundings = new ArrayList<>();
        fundings.add(f1);

        BigDecimal total = SalarySettingCalculator.getCsfFullTimeEmploymentQuantityTotal(fundings);
        assertThat(total).isEqualByComparingTo(new BigDecimal("0.75"));
    }

}
