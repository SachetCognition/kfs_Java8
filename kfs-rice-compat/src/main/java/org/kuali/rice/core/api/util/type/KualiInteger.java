package org.kuali.rice.core.api.util.type;

import java.math.BigDecimal;
import java.math.BigInteger;

public class KualiInteger extends Number implements Comparable<KualiInteger> {
    public static final KualiInteger ZERO = new KualiInteger(0);
    
    private long value;
    
    public KualiInteger(long value) { this.value = value; }
    public KualiInteger(String value) { this.value = Long.parseLong(value); }
    public KualiInteger(BigInteger value) { this.value = value.longValue(); }
    
    public long longValue() { return value; }
    public int intValue() { return (int) value; }
    public float floatValue() { return (float) value; }
    public double doubleValue() { return (double) value; }
    
    public KualiInteger add(KualiInteger other) { return new KualiInteger(value + other.value); }
    public KualiInteger subtract(KualiInteger other) { return new KualiInteger(value - other.value); }
    public KualiInteger multiply(KualiInteger other) { return new KualiInteger(value * other.value); }
    public KualiInteger negated() { return new KualiInteger(-value); }
    public KualiInteger abs() { return new KualiInteger(Math.abs(value)); }
    
    public boolean isZero() { return value == 0; }
    public boolean isPositive() { return value > 0; }
    public boolean isNegative() { return value < 0; }
    public boolean isGreaterThan(KualiInteger other) { return value > other.value; }
    public boolean isLessThan(KualiInteger other) { return value < other.value; }
    public boolean isGreaterEqual(KualiInteger other) { return value >= other.value; }
    public boolean isLessEqual(KualiInteger other) { return value <= other.value; }
    public boolean isNonZero() { return value != 0; }
    
    public int compareTo(KualiInteger other) { return Long.compare(value, other.value); }
    
    public BigDecimal bigDecimalValue() { return BigDecimal.valueOf(value); }
    
    public String toString() { return String.valueOf(value); }
    public boolean equals(Object o) { return o instanceof KualiInteger && ((KualiInteger) o).value == value; }
    public int hashCode() { return Long.hashCode(value); }
}
