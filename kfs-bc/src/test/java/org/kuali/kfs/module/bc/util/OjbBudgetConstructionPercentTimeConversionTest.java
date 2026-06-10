package org.kuali.kfs.module.bc.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class OjbBudgetConstructionPercentTimeConversionTest extends KfsUnitTestBase {

    private final OjbBudgetConstructionPercentTimeConversion conversion = new OjbBudgetConstructionPercentTimeConversion();

    @Test
    void javaToSql_withBigDecimal_returnsSameValue() {
        BigDecimal input = new BigDecimal("75.50");
        Object result = conversion.javaToSql(input);
        assertThat(result).isEqualTo(input);
    }

    @Test
    void javaToSql_withNull_returnsNull() {
        Object result = conversion.javaToSql(null);
        assertThat(result).isNull();
    }

    @Test
    void javaToSql_withNonBigDecimal_returnsNull() {
        Object result = conversion.javaToSql("string");
        assertThat(result).isNull();
    }

    @Test
    void sqlToJava_withBigDecimal_roundsTo2DecimalPlaces() {
        BigDecimal input = new BigDecimal("75.555");
        Object result = conversion.sqlToJava(input);
        assertThat(result).isInstanceOf(BigDecimal.class);
        assertThat(((BigDecimal) result).scale()).isEqualTo(2);
        assertThat((BigDecimal) result).isEqualByComparingTo(new BigDecimal("75.56"));
    }

    @Test
    void sqlToJava_withBigDecimal_exactScale_returnsUnchanged() {
        BigDecimal input = new BigDecimal("100.00");
        Object result = conversion.sqlToJava(input);
        assertThat(((BigDecimal) result).scale()).isEqualTo(2);
        assertThat((BigDecimal) result).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void sqlToJava_withNull_returnsNull() {
        Object result = conversion.sqlToJava(null);
        assertThat(result).isNull();
    }

    @Test
    void sqlToJava_withNonBigDecimal_returnsNull() {
        Object result = conversion.sqlToJava(42);
        assertThat(result).isNull();
    }

    @Test
    void sqlToJava_roundsHalfUp() {
        BigDecimal input = new BigDecimal("50.125");
        Object result = conversion.sqlToJava(input);
        assertThat((BigDecimal) result).isEqualByComparingTo(new BigDecimal("50.13"));
    }
}
