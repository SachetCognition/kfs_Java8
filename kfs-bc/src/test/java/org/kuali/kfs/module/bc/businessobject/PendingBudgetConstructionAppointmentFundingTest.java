package org.kuali.kfs.module.bc.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiInteger;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PendingBudgetConstructionAppointmentFundingTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_fieldsAreNull() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        assertThat(funding.getUniversityFiscalYear()).isNull();
        assertThat(funding.getChartOfAccountsCode()).isNull();
        assertThat(funding.getAccountNumber()).isNull();
        assertThat(funding.getPositionNumber()).isNull();
        assertThat(funding.getEmplid()).isNull();
    }

    @Test
    void setAndGetUniversityFiscalYear() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setUniversityFiscalYear(2024);
        assertThat(funding.getUniversityFiscalYear()).isEqualTo(2024);
    }

    @Test
    void setAndGetChartOfAccountsCode() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setChartOfAccountsCode("UA");
        assertThat(funding.getChartOfAccountsCode()).isEqualTo("UA");
    }

    @Test
    void setAndGetAccountNumber() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setAccountNumber("1234567");
        assertThat(funding.getAccountNumber()).isEqualTo("1234567");
    }

    @Test
    void setAndGetPositionNumber() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setPositionNumber("POS001");
        assertThat(funding.getPositionNumber()).isEqualTo("POS001");
    }

    @Test
    void setAndGetEmplid() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setEmplid("EMP001");
        assertThat(funding.getEmplid()).isEqualTo("EMP001");
    }

    @Test
    void setAndGetAppointmentRequestedCsfAmount() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        KualiInteger amount = new KualiInteger(50000);
        funding.setAppointmentRequestedCsfAmount(amount);
        assertThat(funding.getAppointmentRequestedCsfAmount()).isEqualTo(amount);
    }

    @Test
    void setAndGetAppointmentRequestedCsfFteQuantity() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        BigDecimal fte = new BigDecimal("1.00000");
        funding.setAppointmentRequestedCsfFteQuantity(fte);
        assertThat(funding.getAppointmentRequestedCsfFteQuantity()).isEqualByComparingTo(fte);
    }

    @Test
    void setAndGetAppointmentRequestedCsfTimePercent() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        BigDecimal percent = new BigDecimal("100.00");
        funding.setAppointmentRequestedCsfTimePercent(percent);
        assertThat(funding.getAppointmentRequestedCsfTimePercent()).isEqualByComparingTo(percent);
    }

    @Test
    void setAndGetAppointmentRequestedAmount() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        KualiInteger amount = new KualiInteger(75000);
        funding.setAppointmentRequestedAmount(amount);
        assertThat(funding.getAppointmentRequestedAmount()).isEqualTo(amount);
    }

    @Test
    void setAndGetAppointmentRequestedTimePercent() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        BigDecimal percent = new BigDecimal("50.00");
        funding.setAppointmentRequestedTimePercent(percent);
        assertThat(funding.getAppointmentRequestedTimePercent()).isEqualByComparingTo(percent);
    }

    @Test
    void setAndGetAppointmentRequestedFteQuantity() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        BigDecimal fte = new BigDecimal("0.50000");
        funding.setAppointmentRequestedFteQuantity(fte);
        assertThat(funding.getAppointmentRequestedFteQuantity()).isEqualByComparingTo(fte);
    }

    @Test
    void setAndGetAppointmentFundingDeleteIndicator() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setAppointmentFundingDeleteIndicator(true);
        assertThat(funding.isAppointmentFundingDeleteIndicator()).isTrue();
    }

    @Test
    void setAndGetActive() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setActive(true);
        assertThat(funding.isActive()).isTrue();

        funding.setActive(false);
        assertThat(funding.isActive()).isFalse();
    }

    @Test
    void setAndGetSubAccountNumber() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setSubAccountNumber("SUB01");
        assertThat(funding.getSubAccountNumber()).isEqualTo("SUB01");
    }

    @Test
    void setAndGetFinancialObjectCode() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setFinancialObjectCode("5000");
        assertThat(funding.getFinancialObjectCode()).isEqualTo("5000");
    }

    @Test
    void setAndGetFinancialSubObjectCode() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setFinancialSubObjectCode("001");
        assertThat(funding.getFinancialSubObjectCode()).isEqualTo("001");
    }

    @Test
    void setAndGetAppointmentFundingDurationCode() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setAppointmentFundingDurationCode("NONE");
        assertThat(funding.getAppointmentFundingDurationCode()).isEqualTo("NONE");
    }

    @Test
    void setAndGetPositionChangeIndicators() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        funding.setPositionObjectChangeIndicator(true);
        assertThat(funding.isPositionObjectChangeIndicator()).isTrue();

        funding.setPositionSalaryChangeIndicator(true);
        assertThat(funding.isPositionSalaryChangeIndicator()).isTrue();
    }

    @Test
    void setAndGetAppointmentRequestedPayRate() {
        PendingBudgetConstructionAppointmentFunding funding = new PendingBudgetConstructionAppointmentFunding();
        BigDecimal rate = new BigDecimal("45.50");
        funding.setAppointmentRequestedPayRate(rate);
        assertThat(funding.getAppointmentRequestedPayRate()).isEqualByComparingTo(rate);
    }
}
