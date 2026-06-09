package org.kuali.kfs.module.bc.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class OjbBudgetConstructionFTEConversionTest extends KfsUnitTestBase {

    private final OjbBudgetConstructionFTEConversion conversion = new OjbBudgetConstructionFTEConversion();

    @Test
    void javaToSql_withBigDecimal_returnsSameValue() {
        BigDecimal input = new BigDecimal("1.23456");
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
        Object result = conversion.javaToSql("not a BigDecimal");
        assertThat(result).isNull();
    }

    @Test
    void sqlToJava_withBigDecimal_roundsTo5DecimalPlaces() {
        BigDecimal input = new BigDecimal("1.234567890");
        Object result = conversion.sqlToJava(input);
        assertThat(result).isInstanceOf(BigDecimal.class);
        assertThat(((BigDecimal) result).scale()).isEqualTo(5);
        assertThat((BigDecimal) result).isEqualByComparingTo(new BigDecimal("1.23457"));
    }

    @Test
    void sqlToJava_withBigDecimal_fewerDecimals_padsToScale5() {
        BigDecimal input = new BigDecimal("1.5");
        Object result = conversion.sqlToJava(input);
        assertThat(((BigDecimal) result).scale()).isEqualTo(5);
        assertThat((BigDecimal) result).isEqualByComparingTo(new BigDecimal("1.50000"));
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
    void sqlToJava_withZero_returnsScaled() {
        BigDecimal input = BigDecimal.ZERO;
        Object result = conversion.sqlToJava(input);
        assertThat(((BigDecimal) result).scale()).isEqualTo(5);
    }
}
