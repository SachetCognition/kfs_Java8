package org.kuali.kfs.module.cam.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class KualiDecimalUtilsTest extends KfsUnitTestBase {

    // ── allocateByQuantity ──────────────────────────────────────────

    @Test
    @DisplayName("allocateByQuantity: null amount returns null")
    void allocateByQuantity_nullAmount_returnsNull() {
        assertThat(KualiDecimalUtils.allocateByQuantity(null, 3)).isNull();
    }

    @Test
    @DisplayName("allocateByQuantity: zero divisor returns single-element array with original amount")
    void allocateByQuantity_zeroDivisor_returnsSingleElement() {
        KualiDecimal amount = new KualiDecimal(100);
        KualiDecimal[] result = KualiDecimalUtils.allocateByQuantity(amount, 0);
        assertThat(result).hasSize(1);
        assertThat(result[0]).isEqualTo(amount);
    }

    @Test
    @DisplayName("allocateByQuantity: even division produces equal amounts")
    void allocateByQuantity_evenDivision() {
        KualiDecimal amount = new KualiDecimal(100);
        KualiDecimal[] result = KualiDecimalUtils.allocateByQuantity(amount, 4);
        assertThat(result).hasSize(4);
        for (KualiDecimal v : result) {
            assertThat(v).isEqualTo(new KualiDecimal(25));
        }
    }

    @Test
    @DisplayName("allocateByQuantity: uneven division sums to original amount")
    void allocateByQuantity_unevenDivision_sumsToOriginal() {
        KualiDecimal amount = new KualiDecimal(100);
        int divisor = 3;
        KualiDecimal[] result = KualiDecimalUtils.allocateByQuantity(amount, divisor);
        assertThat(result).hasSize(divisor);

        KualiDecimal sum = KualiDecimal.ZERO;
        for (KualiDecimal v : result) {
            sum = sum.add(v);
        }
        assertThat(sum).isEqualTo(amount);
    }

    @Test
    @DisplayName("allocateByQuantity: single divisor returns whole amount")
    void allocateByQuantity_singleDivisor() {
        KualiDecimal amount = new KualiDecimal(99.99);
        KualiDecimal[] result = KualiDecimalUtils.allocateByQuantity(amount, 1);
        assertThat(result).hasSize(1);
        assertThat(result[0]).isEqualTo(amount);
    }

    @Test
    @DisplayName("allocateByQuantity: negative amount distributes correctly")
    void allocateByQuantity_negativeAmount() {
        KualiDecimal amount = new KualiDecimal(-100);
        KualiDecimal[] result = KualiDecimalUtils.allocateByQuantity(amount, 4);
        assertThat(result).hasSize(4);
        KualiDecimal sum = KualiDecimal.ZERO;
        for (KualiDecimal v : result) {
            sum = sum.add(v);
        }
        assertThat(sum).isEqualTo(amount);
    }

    // ── allocateByRatio ─────────────────────────────────────────────

    @Test
    @DisplayName("allocateByRatio: null ratios returns single-element array")
    void allocateByRatio_nullRatios_returnsSingleElement() {
        KualiDecimal amount = new KualiDecimal(100);
        KualiDecimal[] result = KualiDecimalUtils.allocateByRatio(amount, null);
        assertThat(result).hasSize(1);
        assertThat(result[0]).isEqualTo(amount);
    }

    @Test
    @DisplayName("allocateByRatio: empty ratios returns single-element array")
    void allocateByRatio_emptyRatios() {
        KualiDecimal amount = new KualiDecimal(100);
        KualiDecimal[] result = KualiDecimalUtils.allocateByRatio(amount, new double[0]);
        assertThat(result).hasSize(1);
        assertThat(result[0]).isEqualTo(amount);
    }

    @Test
    @DisplayName("allocateByRatio: null amount returns null")
    void allocateByRatio_nullAmount() {
        assertThat(KualiDecimalUtils.allocateByRatio(null, new double[]{0.5, 0.5})).isNull();
    }

    @Test
    @DisplayName("allocateByRatio: equal ratios sum to original amount")
    void allocateByRatio_equalRatios_sumToOriginal() {
        KualiDecimal amount = new KualiDecimal(100);
        double[] ratios = {0.5, 0.5};
        KualiDecimal[] result = KualiDecimalUtils.allocateByRatio(amount, ratios);
        assertThat(result).hasSize(2);
        KualiDecimal sum = KualiDecimal.ZERO;
        for (KualiDecimal v : result) {
            sum = sum.add(v);
        }
        assertThat(sum).isEqualTo(amount);
    }

    @Test
    @DisplayName("allocateByRatio: unequal ratios sum to original amount")
    void allocateByRatio_unequalRatios_sumToOriginal() {
        KualiDecimal amount = new KualiDecimal(1000);
        double[] ratios = {0.333, 0.333, 0.334};
        KualiDecimal[] result = KualiDecimalUtils.allocateByRatio(amount, ratios);
        assertThat(result).hasSize(3);
        KualiDecimal sum = KualiDecimal.ZERO;
        for (KualiDecimal v : result) {
            sum = sum.add(v);
        }
        assertThat(sum).isEqualTo(amount);
    }

    // ── safeMultiply ────────────────────────────────────────────────

    @Test
    @DisplayName("safeMultiply: null value returns null")
    void safeMultiply_nullValue() {
        assertThat(KualiDecimalUtils.safeMultiply(null, 2.0)).isNull();
    }

    @Test
    @DisplayName("safeMultiply: non-null value multiplied correctly")
    void safeMultiply_nonNullValue() {
        KualiDecimal result = KualiDecimalUtils.safeMultiply(new KualiDecimal(50), 2.0);
        assertThat(result).isEqualTo(new KualiDecimal(100));
    }

    @Test
    @DisplayName("safeMultiply: multiplier of zero returns zero")
    void safeMultiply_zeroMultiplier() {
        KualiDecimal result = KualiDecimalUtils.safeMultiply(new KualiDecimal(50), 0.0);
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    @DisplayName("safeMultiply: fractional multiplier")
    void safeMultiply_fractionalMultiplier() {
        KualiDecimal result = KualiDecimalUtils.safeMultiply(new KualiDecimal(100), 0.5);
        assertThat(result).isEqualTo(new KualiDecimal(50));
    }

    // ── safeSubtract ────────────────────────────────────────────────

    @Test
    @DisplayName("safeSubtract: null subtrahend returns value")
    void safeSubtract_nullSubtrahend() {
        KualiDecimal value = new KualiDecimal(100);
        assertThat(KualiDecimalUtils.safeSubtract(value, null)).isEqualTo(value);
    }

    @Test
    @DisplayName("safeSubtract: null value returns null")
    void safeSubtract_nullValue() {
        assertThat(KualiDecimalUtils.safeSubtract(null, new KualiDecimal(50))).isNull();
    }

    @Test
    @DisplayName("safeSubtract: both null returns null")
    void safeSubtract_bothNull() {
        assertThat(KualiDecimalUtils.safeSubtract(null, null)).isNull();
    }

    @Test
    @DisplayName("safeSubtract: normal subtraction")
    void safeSubtract_normalSubtraction() {
        KualiDecimal result = KualiDecimalUtils.safeSubtract(new KualiDecimal(100), new KualiDecimal(30));
        assertThat(result).isEqualTo(new KualiDecimal(70));
    }

    @Test
    @DisplayName("safeSubtract: result is negative")
    void safeSubtract_negativeResult() {
        KualiDecimal result = KualiDecimalUtils.safeSubtract(new KualiDecimal(30), new KualiDecimal(100));
        assertThat(result).isEqualTo(new KualiDecimal(-70));
    }
}
