package org.kuali.kfs.module.tem.businessobject;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PerDiem Business Object")
class PerDiemTest extends KfsUnitTestBase {

    private PerDiem perDiem;

    @BeforeEach
    void setUp() {
        perDiem = new PerDiem();
    }

    @Test
    @DisplayName("should initialize with zero meal amounts")
    void testDefaultConstruction() {
        assertThat(perDiem.getBreakfast()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiem.getLunch()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiem.getDinner()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiem.getLodging()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiem.getIncidentals()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiem.getMealsAndIncidentals()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    @DisplayName("should set and get id")
    void testId() {
        perDiem.setId(42);
        assertThat(perDiem.getId()).isEqualTo(42);
    }

    @Test
    @DisplayName("should set and get breakfast amount")
    void testBreakfast() {
        KualiDecimal amount = new KualiDecimal(12.50);
        perDiem.setBreakfast(amount);
        assertThat(perDiem.getBreakfast()).isEqualTo(amount);
    }

    @Test
    @DisplayName("should set and get lunch amount")
    void testLunch() {
        KualiDecimal amount = new KualiDecimal(15.00);
        perDiem.setLunch(amount);
        assertThat(perDiem.getLunch()).isEqualTo(amount);
    }

    @Test
    @DisplayName("should set and get dinner amount")
    void testDinner() {
        KualiDecimal amount = new KualiDecimal(25.00);
        perDiem.setDinner(amount);
        assertThat(perDiem.getDinner()).isEqualTo(amount);
    }

    @Test
    @DisplayName("should set and get lodging amount")
    void testLodging() {
        KualiDecimal amount = new KualiDecimal(150.00);
        perDiem.setLodging(amount);
        assertThat(perDiem.getLodging()).isEqualTo(amount);
    }

    @Test
    @DisplayName("should set and get incidentals amount")
    void testIncidentals() {
        KualiDecimal amount = new KualiDecimal(10.00);
        perDiem.setIncidentals(amount);
        assertThat(perDiem.getIncidentals()).isEqualTo(amount);
    }

    @Test
    @DisplayName("should set and get mealsAndIncidentals amount")
    void testMealsAndIncidentals() {
        KualiDecimal amount = new KualiDecimal(62.50);
        perDiem.setMealsAndIncidentals(amount);
        assertThat(perDiem.getMealsAndIncidentals()).isEqualTo(amount);
    }

    @Test
    @DisplayName("should set and get primaryDestinationId")
    void testPrimaryDestinationId() {
        perDiem.setPrimaryDestinationId(100);
        assertThat(perDiem.getPrimaryDestinationId()).isEqualTo(100);
    }

    @Test
    @DisplayName("should set and get effectiveFromDate")
    void testEffectiveFromDate() {
        Date date = Date.valueOf("2024-01-01");
        perDiem.setEffectiveFromDate(date);
        assertThat(perDiem.getEffectiveFromDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("should set and get effectiveToDate")
    void testEffectiveToDate() {
        Date date = Date.valueOf("2024-12-31");
        perDiem.setEffectiveToDate(date);
        assertThat(perDiem.getEffectiveToDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("should set and get seasonBeginMonthAndDay")
    void testSeasonBeginMonthAndDay() {
        perDiem.setSeasonBeginMonthAndDay("10/01");
        assertThat(perDiem.getSeasonBeginMonthAndDay()).isEqualTo("10/01");
    }

    @Test
    @DisplayName("should set and get conusIndicator")
    void testConusIndicator() {
        perDiem.setConusIndicator("Y");
        assertThat(perDiem.getConusIndicator()).isEqualTo("Y");
    }

    @Test
    @DisplayName("should set and get lineNumber")
    void testLineNumber() {
        perDiem.setLineNumber(5);
        assertThat(perDiem.getLineNumber()).isEqualTo(5);
    }

    @Test
    @DisplayName("should set and get loadDate")
    void testLoadDate() {
        Date date = Date.valueOf("2024-06-15");
        perDiem.setLoadDate(date);
        assertThat(perDiem.getLoadDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("should set and get primaryDestination")
    void testPrimaryDestination() {
        PrimaryDestination dest = new PrimaryDestination();
        perDiem.setPrimaryDestination(dest);
        assertThat(perDiem.getPrimaryDestination()).isSameAs(dest);
    }
}
