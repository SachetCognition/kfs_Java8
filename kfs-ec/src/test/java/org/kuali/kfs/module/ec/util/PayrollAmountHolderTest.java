package org.kuali.kfs.module.ec.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class PayrollAmountHolderTest extends KfsUnitTestBase {

    @Nested
    @DisplayName("Constructor and getters/setters")
    class ConstructorAndAccessors {

        @Test
        void shouldInitializeViaConstructor() {
            PayrollAmountHolder holder = new PayrollAmountHolder(
                    new KualiDecimal(10000), new KualiDecimal(2000), 20);

            assertThat(holder.getTotalAmount()).isEqualTo(new KualiDecimal(10000));
            assertThat(holder.getAccumulatedAmount()).isEqualTo(new KualiDecimal(2000));
            assertThat(holder.getAccumulatedPercent()).isEqualTo(20);
        }

        @Test
        void shouldSetAndGetPayrollAmount() {
            PayrollAmountHolder holder = new PayrollAmountHolder(KualiDecimal.ZERO, KualiDecimal.ZERO, 0);
            holder.setPayrollAmount(new KualiDecimal(5000));
            assertThat(holder.getPayrollAmount()).isEqualTo(new KualiDecimal(5000));
        }

        @Test
        void shouldSetAndGetPayrollPercent() {
            PayrollAmountHolder holder = new PayrollAmountHolder(KualiDecimal.ZERO, KualiDecimal.ZERO, 0);
            holder.setPayrollPercent(50);
            assertThat(holder.getPayrollPercent()).isEqualTo(50);
        }

        @Test
        void shouldSetAndGetTotalAmount() {
            PayrollAmountHolder holder = new PayrollAmountHolder(KualiDecimal.ZERO, KualiDecimal.ZERO, 0);
            holder.setTotalAmount(new KualiDecimal(20000));
            assertThat(holder.getTotalAmount()).isEqualTo(new KualiDecimal(20000));
        }

        @Test
        void shouldSetAndGetAccumulatedAmount() {
            PayrollAmountHolder holder = new PayrollAmountHolder(KualiDecimal.ZERO, KualiDecimal.ZERO, 0);
            holder.setAccumulatedAmount(new KualiDecimal(3000));
            assertThat(holder.getAccumulatedAmount()).isEqualTo(new KualiDecimal(3000));
        }

        @Test
        void shouldSetAndGetAccumulatedPercent() {
            PayrollAmountHolder holder = new PayrollAmountHolder(KualiDecimal.ZERO, KualiDecimal.ZERO, 0);
            holder.setAccumulatedPercent(30);
            assertThat(holder.getAccumulatedPercent()).isEqualTo(30);
        }
    }

    @Nested
    @DisplayName("calculatePayrollPercent")
    class CalculatePayrollPercent {

        @Test
        void shouldCalculatePercentCorrectly() {
            PayrollAmountHolder holder = new PayrollAmountHolder(
                    new KualiDecimal(10000), KualiDecimal.ZERO, 0);
            holder.setPayrollAmount(new KualiDecimal(2500));

            PayrollAmountHolder.calculatePayrollPercent(holder);

            assertThat(holder.getPayrollPercent()).isEqualTo(25);
            assertThat(holder.getAccumulatedAmount()).isEqualTo(new KualiDecimal(2500));
            assertThat(holder.getAccumulatedPercent()).isEqualTo(25);
        }

        @Test
        void shouldHandleZeroTotalAmount() {
            PayrollAmountHolder holder = new PayrollAmountHolder(
                    KualiDecimal.ZERO, KualiDecimal.ZERO, 0);
            holder.setPayrollAmount(new KualiDecimal(1000));

            PayrollAmountHolder.calculatePayrollPercent(holder);

            assertThat(holder.getPayrollPercent()).isNull();
        }

        @Test
        void shouldHandleFullAmount() {
            PayrollAmountHolder holder = new PayrollAmountHolder(
                    new KualiDecimal(5000), KualiDecimal.ZERO, 0);
            holder.setPayrollAmount(new KualiDecimal(5000));

            PayrollAmountHolder.calculatePayrollPercent(holder);

            assertThat(holder.getPayrollPercent()).isEqualTo(100);
        }

        @Test
        void shouldAccumulateAcrossMultipleCalls() {
            PayrollAmountHolder holder = new PayrollAmountHolder(
                    new KualiDecimal(10000), KualiDecimal.ZERO, 0);

            holder.setPayrollAmount(new KualiDecimal(3000));
            PayrollAmountHolder.calculatePayrollPercent(holder);
            assertThat(holder.getAccumulatedPercent()).isEqualTo(30);

            holder.setPayrollAmount(new KualiDecimal(7000));
            PayrollAmountHolder.calculatePayrollPercent(holder);
            assertThat(holder.getAccumulatedPercent()).isEqualTo(100);
        }
    }

    @Nested
    @DisplayName("recalculatePayrollAmount")
    class RecalculatePayrollAmount {

        @Test
        void shouldRecalculateCorrectly() {
            KualiDecimal result = PayrollAmountHolder.recalculatePayrollAmount(
                    new KualiDecimal(10000), 25);
            assertThat(result).isEqualTo(new KualiDecimal(2500));
        }

        @Test
        void shouldReturnZeroForZeroPercent() {
            KualiDecimal result = PayrollAmountHolder.recalculatePayrollAmount(
                    new KualiDecimal(10000), 0);
            assertThat(result).isEqualTo(KualiDecimal.ZERO);
        }

        @Test
        void shouldReturnFullAmountFor100Percent() {
            KualiDecimal result = PayrollAmountHolder.recalculatePayrollAmount(
                    new KualiDecimal(5000), 100);
            assertThat(result).isEqualTo(new KualiDecimal(5000));
        }
    }

    @Nested
    @DisplayName("recalculateEffortPercent")
    class RecalculateEffortPercent {

        @Test
        void shouldRecalculatePercentCorrectly() {
            Double result = PayrollAmountHolder.recalculateEffortPercent(
                    new KualiDecimal(10000), new KualiDecimal(2500));
            assertThat(result).isCloseTo(25.0, within(0.01));
        }

        @Test
        void shouldReturn100ForFullAmount() {
            Double result = PayrollAmountHolder.recalculateEffortPercent(
                    new KualiDecimal(5000), new KualiDecimal(5000));
            assertThat(result).isCloseTo(100.0, within(0.01));
        }

        @Test
        void shouldReturn0ForZeroPayroll() {
            Double result = PayrollAmountHolder.recalculateEffortPercent(
                    new KualiDecimal(10000), KualiDecimal.ZERO);
            assertThat(result).isCloseTo(0.0, within(0.01));
        }
    }

    @Nested
    @DisplayName("recalculateEffortPercentAsString")
    class RecalculateEffortPercentAsString {

        @Test
        void shouldReturnFormattedString() {
            String result = PayrollAmountHolder.recalculateEffortPercentAsString(
                    new KualiDecimal(10000), new KualiDecimal(2500));
            assertThat(result).isEqualTo("25.0000%");
        }

        @Test
        void shouldReturnZeroPercentForZeroTotal() {
            String result = PayrollAmountHolder.recalculateEffortPercentAsString(
                    KualiDecimal.ZERO, new KualiDecimal(2500));
            assertThat(result).isEqualTo("0.0000%");
        }

        @Test
        void shouldReturnZeroPercentForZeroPayroll() {
            String result = PayrollAmountHolder.recalculateEffortPercentAsString(
                    new KualiDecimal(10000), KualiDecimal.ZERO);
            assertThat(result).isEqualTo("0.0000%");
        }
    }
}
