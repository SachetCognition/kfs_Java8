package org.kuali.kfs.module.tem.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PerDiemExpense Business Object")
class PerDiemExpenseTest extends KfsUnitTestBase {

    private PerDiemExpense perDiemExpense;

    @BeforeEach
    void setUp() {
        perDiemExpense = new PerDiemExpense();
    }

    @Test
    @DisplayName("should initialize with default values")
    void testDefaultConstruction() {
        assertThat(perDiemExpense.getBreakfast()).isTrue();
        assertThat(perDiemExpense.getLunch()).isTrue();
        assertThat(perDiemExpense.getDinner()).isTrue();
        assertThat(perDiemExpense.getPersonal()).isFalse();
        assertThat(perDiemExpense.getBreakfastValue()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiemExpense.getLunchValue()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiemExpense.getDinnerValue()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiemExpense.getIncidentalsValue()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiemExpense.getLodging()).isEqualTo(KualiDecimal.ZERO);
        assertThat(perDiemExpense.getMiles()).isEqualTo(0);
    }

    @Test
    @DisplayName("should set and get id")
    void testId() {
        perDiemExpense.setId(1);
        assertThat(perDiemExpense.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("should set and get documentNumber")
    void testDocumentNumber() {
        perDiemExpense.setDocumentNumber("DOC001");
        assertThat(perDiemExpense.getDocumentNumber()).isEqualTo("DOC001");
    }

    @Test
    @DisplayName("should set and get countryState")
    void testCountryState() {
        perDiemExpense.setCountryState("CA");
        assertThat(perDiemExpense.getCountryState()).isEqualTo("CA");
    }

    @Test
    @DisplayName("should set and get county")
    void testCounty() {
        perDiemExpense.setCounty("Los Angeles");
        assertThat(perDiemExpense.getCounty()).isEqualTo("Los Angeles");
    }

    @Test
    @DisplayName("should set and get primaryDestination")
    void testPrimaryDestination() {
        perDiemExpense.setPrimaryDestination("San Francisco");
        assertThat(perDiemExpense.getPrimaryDestination()).isEqualTo("San Francisco");
    }

    @Test
    @DisplayName("should set and get breakfast flag")
    void testBreakfast() {
        perDiemExpense.setBreakfast(Boolean.FALSE);
        assertThat(perDiemExpense.getBreakfast()).isFalse();
    }

    @Test
    @DisplayName("should set and get lunch flag")
    void testLunch() {
        perDiemExpense.setLunch(Boolean.FALSE);
        assertThat(perDiemExpense.getLunch()).isFalse();
    }

    @Test
    @DisplayName("should set and get dinner flag")
    void testDinner() {
        perDiemExpense.setDinner(Boolean.FALSE);
        assertThat(perDiemExpense.getDinner()).isFalse();
    }

    @Test
    @DisplayName("should set and get personal flag")
    void testPersonal() {
        perDiemExpense.setPersonal(Boolean.TRUE);
        assertThat(perDiemExpense.getPersonal()).isTrue();
    }

    @Test
    @DisplayName("should set and get breakfastValue")
    void testBreakfastValue() {
        KualiDecimal value = new KualiDecimal(12.50);
        perDiemExpense.setBreakfastValue(value);
        assertThat(perDiemExpense.getBreakfastValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("should set and get lunchValue")
    void testLunchValue() {
        KualiDecimal value = new KualiDecimal(15.00);
        perDiemExpense.setLunchValue(value);
        assertThat(perDiemExpense.getLunchValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("should set and get dinnerValue")
    void testDinnerValue() {
        KualiDecimal value = new KualiDecimal(25.00);
        perDiemExpense.setDinnerValue(value);
        assertThat(perDiemExpense.getDinnerValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("should set and get incidentalsValue")
    void testIncidentalsValue() {
        KualiDecimal value = new KualiDecimal(10.00);
        perDiemExpense.setIncidentalsValue(value);
        assertThat(perDiemExpense.getIncidentalsValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("should set and get lodging")
    void testLodging() {
        KualiDecimal value = new KualiDecimal(150.00);
        perDiemExpense.setLodging(value);
        assertThat(perDiemExpense.getLodging()).isEqualTo(value);
    }

    @Test
    @DisplayName("should set and get miles")
    void testMiles() {
        perDiemExpense.setMiles(250);
        assertThat(perDiemExpense.getMiles()).isEqualTo(250);
    }

    @Test
    @DisplayName("should set and get accommodationTypeCode")
    void testAccommodationTypeCode() {
        perDiemExpense.setAccommodationTypeCode("HOTEL");
        assertThat(perDiemExpense.getAccommodationTypeCode()).isEqualTo("HOTEL");
    }

    @Test
    @DisplayName("should set and get accommodationName")
    void testAccommodationName() {
        perDiemExpense.setAccommodationName("Hilton Garden Inn");
        assertThat(perDiemExpense.getAccommodationName()).isEqualTo("Hilton Garden Inn");
    }

    @Test
    @DisplayName("should set and get accommodationPhoneNum")
    void testAccommodationPhoneNum() {
        perDiemExpense.setAccommodationPhoneNum("555-123-4567");
        assertThat(perDiemExpense.getAccommodationPhoneNum()).isEqualTo("555-123-4567");
    }

    @Test
    @DisplayName("should set and get accommodationAddress")
    void testAccommodationAddress() {
        perDiemExpense.setAccommodationAddress("123 Main St");
        assertThat(perDiemExpense.getAccommodationAddress()).isEqualTo("123 Main St");
    }

    @Test
    @DisplayName("should set and get prorated flag when not custom per diem")
    void testProrated() {
        perDiemExpense.setPrimaryDestinationId(100);
        assertThat(perDiemExpense.isProrated()).isFalse();
        perDiemExpense.setProrated(true);
        assertThat(perDiemExpense.isProrated()).isTrue();
    }

    @Test
    @DisplayName("should return false for prorated when custom per diem")
    void testProratedCustomPerDiem() {
        perDiemExpense.setProrated(true);
        assertThat(perDiemExpense.isProrated()).isFalse();
    }

    @Test
    @DisplayName("should set and get primaryDestinationId")
    void testPrimaryDestinationId() {
        perDiemExpense.setPrimaryDestinationId(42);
        assertThat(perDiemExpense.getPrimaryDestinationId()).isEqualTo(42);
    }

    @Test
    @DisplayName("should set and get mileageRateExpenseTypeCode")
    void testMileageRateExpenseTypeCode() {
        perDiemExpense.setMileageRateExpenseTypeCode("PRIV");
        assertThat(perDiemExpense.getMileageRateExpenseTypeCode()).isEqualTo("PRIV");
    }
}
