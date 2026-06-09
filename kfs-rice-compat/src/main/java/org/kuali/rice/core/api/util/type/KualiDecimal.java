package org.kuali.rice.core.api.util.type;

import java.math.BigDecimal;

public class KualiDecimal extends AbstractKualiDecimal<KualiDecimal> implements Comparable<KualiDecimal>, java.io.Serializable {
    public static final KualiDecimal ZERO = new KualiDecimal(0);

    public KualiDecimal() { super(BigDecimal.ZERO); }
    public KualiDecimal(double value) { super(new BigDecimal(value)); }
    public KualiDecimal(String value) { super(new BigDecimal(value)); }
    public KualiDecimal(int value) { super(new BigDecimal(value)); }
    public KualiDecimal(long value) { super(new BigDecimal(value)); }
    public KualiDecimal(BigDecimal value) { super(value); }

    public KualiDecimal add(KualiDecimal addend) {
        return new KualiDecimal(this.value.add(addend.value));
    }
    public KualiDecimal subtract(KualiDecimal subtrahend) {
        return new KualiDecimal(this.value.subtract(subtrahend.value));
    }
    public KualiDecimal multiply(KualiDecimal multiplier) {
        return new KualiDecimal(this.value.multiply(multiplier.value));
    }
    public KualiDecimal multiply(BigDecimal multiplier) {
        return new KualiDecimal(this.value.multiply(multiplier));
    }
    public KualiDecimal divide(KualiDecimal divisor) {
        if (divisor == null || divisor.isZero()) return ZERO;
        return new KualiDecimal(this.value.divide(divisor.value, 2, BigDecimal.ROUND_HALF_UP));
    }
    public KualiDecimal mod(KualiDecimal modulus) {
        if (modulus == null || modulus.isZero()) return ZERO;
        return new KualiDecimal(this.value.remainder(modulus.value));
    }
    public KualiDecimal negated() {
        return new KualiDecimal(this.value.negate());
    }
    public KualiDecimal abs() {
        return new KualiDecimal(this.value.abs());
    }
    public boolean isZero() { return value.compareTo(BigDecimal.ZERO) == 0; }
    public boolean isPositive() { return value.compareTo(BigDecimal.ZERO) > 0; }
    public boolean isNegative() { return value.compareTo(BigDecimal.ZERO) < 0; }
    public boolean isGreaterThan(KualiDecimal other) { return value.compareTo(other.value) > 0; }
    public boolean isLessThan(KualiDecimal other) { return value.compareTo(other.value) < 0; }
    public boolean isGreaterEqual(KualiDecimal other) { return value.compareTo(other.value) >= 0; }
    public boolean isLessEqual(KualiDecimal other) { return value.compareTo(other.value) <= 0; }
    public boolean isNonZero() { return !isZero(); }
    public double doubleValue() { return value.doubleValue(); }
    public float floatValue() { return value.floatValue(); }
    public int intValue() { return value.intValue(); }
    public long longValue() { return value.longValue(); }
    public BigDecimal bigDecimalValue() { return value; }
    public int compareTo(KualiDecimal other) {
        return this.value.compareTo(other.value);
    }
    public boolean equals(Object obj) {
        if (obj instanceof KualiDecimal) return this.value.compareTo(((KualiDecimal) obj).value) == 0;
        return false;
    }
    public int hashCode() { return value.hashCode(); }
    public String toString() { return value.toString(); }
}
