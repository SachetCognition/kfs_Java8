package org.kuali.kfs.module.tem.batch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants.MEAL_CODE;
import org.kuali.kfs.module.tem.TemConstants.PerDiemParameter;
import org.kuali.kfs.module.tem.batch.businessobject.DefaultMealBreakDownStrategy;
import org.kuali.kfs.module.tem.businessobject.PerDiem;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@DisplayName("DefaultMealBreakDownStrategy")
class DefaultMealBreakDownStrategyTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private DefaultMealBreakDownStrategy strategy;

    private void stubMealBreakdownParams() {
        when(parameterService.getSubParameterValueAsString(
                eq(PerDiemLoadStep.class), eq(PerDiemParameter.CONUS_MEAL_BREAKDOWN),
                eq(MEAL_CODE.BREAKFAST.mealCode)))
                .thenReturn("20");
        when(parameterService.getSubParameterValueAsString(
                eq(PerDiemLoadStep.class), eq(PerDiemParameter.CONUS_MEAL_BREAKDOWN),
                eq(MEAL_CODE.LUNCH.mealCode)))
                .thenReturn("30");
        when(parameterService.getSubParameterValueAsString(
                eq(PerDiemLoadStep.class), eq(PerDiemParameter.CONUS_MEAL_BREAKDOWN),
                eq(MEAL_CODE.DINNER.mealCode)))
                .thenReturn("40");
    }

    @Test
    @DisplayName("should break down meals and incidentals with provided amount")
    void testBreakDownWithAmount() {
        stubMealBreakdownParams();
        PerDiem perDiem = new PerDiem();
        KualiDecimal mealsAndIncidentals = new KualiDecimal(100);

        strategy.breakDown(perDiem, mealsAndIncidentals);

        assertThat(perDiem.getBreakfast()).isEqualTo(new KualiDecimal(20));
        assertThat(perDiem.getLunch()).isEqualTo(new KualiDecimal(30));
        assertThat(perDiem.getDinner()).isEqualTo(new KualiDecimal(40));
        assertThat(perDiem.getIncidentals()).isEqualTo(new KualiDecimal(10));
    }

    @Test
    @DisplayName("should break down using perDiem's mealsAndIncidentals")
    void testBreakDownUsingPerDiemValue() {
        stubMealBreakdownParams();
        PerDiem perDiem = new PerDiem();
        perDiem.setMealsAndIncidentals(new KualiDecimal(50));

        strategy.breakDown(perDiem);

        assertThat(perDiem.getBreakfast()).isEqualTo(new KualiDecimal(10));
        assertThat(perDiem.getLunch()).isEqualTo(new KualiDecimal(15));
        assertThat(perDiem.getDinner()).isEqualTo(new KualiDecimal(20));
        assertThat(perDiem.getIncidentals()).isEqualTo(new KualiDecimal(5));
    }

    @Test
    @DisplayName("should throw exception for null mealsAndIncidentals")
    void testBreakDownWithNull() {
        PerDiem perDiem = new PerDiem();

        assertThatThrownBy(() -> strategy.breakDown(perDiem, null))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("should throw exception for negative mealsAndIncidentals")
    void testBreakDownWithNegativeAmount() {
        PerDiem perDiem = new PerDiem();

        assertThatThrownBy(() -> strategy.breakDown(perDiem, new KualiDecimal(-10)))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("should handle zero mealsAndIncidentals")
    void testBreakDownWithZero() {
        stubMealBreakdownParams();
        PerDiem perDiem = new PerDiem();

        strategy.breakDown(perDiem, KualiDecimal.ZERO);

        assertThat(perDiem.getBreakfast()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiem.getLunch()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiem.getDinner()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiem.getIncidentals()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    @DisplayName("should set and get parameterService")
    void testSetParameterService() {
        strategy.setParameterService(parameterService);
        assertThat(strategy.getParameterService()).isSameAs(parameterService);
    }
}
