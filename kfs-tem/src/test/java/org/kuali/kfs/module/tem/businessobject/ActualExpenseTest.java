package org.kuali.kfs.module.tem.businessobject;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ActualExpense Business Object")
class ActualExpenseTest extends KfsUnitTestBase {

    private ActualExpense expense;

    @BeforeEach
    void setUp() {
        expense = new ActualExpense();
    }

    @Test
    @DisplayName("should initialize with default values")
    void testDefaultConstruction() {
        assertThat(expense.getMileageOtherRate()).isEqualTo(BigDecimal.ZERO);
        assertThat(expense.getRentalCarInsurance()).isFalse();
        assertThat(expense.getAirfareIndicator()).isFalse();
        assertThat(expense.getMileageIndicator()).isFalse();
        assertThat(expense.getRentalCarIndicator()).isFalse();
        assertThat(expense.getLodgingIndicator()).isFalse();
        assertThat(expense.getLodgingAllowanceIndicator()).isFalse();
    }

    @Test
    @DisplayName("should have EXPENSE_ACTUAL as default expense line type")
    void testDefaultExpenseLineTypeCode() {
        assertThat(expense.getExpenseLineTypeCode()).isEqualTo(TemConstants.EXPENSE_ACTUAL);
    }

    @Test
    @DisplayName("should set and get airfareSourceCode")
    void testAirfareSourceCode() {
        expense.setAirfareSourceCode("AGENCY");
        assertThat(expense.getAirfareSourceCode()).isEqualTo("AGENCY");
    }

    @Test
    @DisplayName("should set and get classOfServiceCode")
    void testClassOfServiceCode() {
        expense.setClassOfServiceCode("COACH");
        assertThat(expense.getClassOfServiceCode()).isEqualTo("COACH");
    }

    @Test
    @DisplayName("should set and get miles")
    void testMiles() {
        expense.setMiles(150);
        assertThat(expense.getMiles()).isEqualTo(150);
    }

    @Test
    @DisplayName("should set and get mileageOtherRate")
    void testMileageOtherRate() {
        BigDecimal rate = new BigDecimal("0.575");
        expense.setMileageOtherRate(rate);
        assertThat(expense.getMileageOtherRate()).isEqualTo(rate);
    }

    @Test
    @DisplayName("should set and get rentalCarInsurance")
    void testRentalCarInsurance() {
        expense.setRentalCarInsurance(Boolean.TRUE);
        assertThat(expense.getRentalCarInsurance()).isTrue();
    }

    @Test
    @DisplayName("should set and get airfareIndicator")
    void testAirfareIndicator() {
        expense.setAirfareIndicator(Boolean.TRUE);
        assertThat(expense.getAirfareIndicator()).isTrue();
    }

    @Test
    @DisplayName("should set and get mileageIndicator")
    void testMileageIndicator() {
        expense.setMileageIndicator(Boolean.TRUE);
        assertThat(expense.getMileageIndicator()).isTrue();
    }

    @Test
    @DisplayName("should set and get rentalCarIndicator")
    void testRentalCarIndicator() {
        expense.setRentalCarIndicator(Boolean.TRUE);
        assertThat(expense.getRentalCarIndicator()).isTrue();
    }

    @Test
    @DisplayName("should set and get lodgingIndicator")
    void testLodgingIndicator() {
        expense.setLodgingIndicator(Boolean.TRUE);
        assertThat(expense.getLodgingIndicator()).isTrue();
    }

    @Test
    @DisplayName("should set and get lodgingAllowanceIndicator")
    void testLodgingAllowanceIndicator() {
        expense.setLodgingAllowanceIndicator(Boolean.TRUE);
        assertThat(expense.getLodgingAllowanceIndicator()).isTrue();
    }

    @Test
    @DisplayName("should inherit abstract expense fields")
    void testInheritedFields() {
        expense.setDocumentNumber("DOC123");
        expense.setDocumentLineNumber(1);
        expense.setExpenseAmount(new KualiDecimal(500.00));
        expense.setNonReimbursable(Boolean.TRUE);
        expense.setDescription("Conference travel");

        assertThat(expense.getDocumentNumber()).isEqualTo("DOC123");
        assertThat(expense.getDocumentLineNumber()).isEqualTo(1);
        assertThat(expense.getExpenseAmount()).isEqualTo(new KualiDecimal(500.00));
        assertThat(expense.getNonReimbursable()).isTrue();
        assertThat(expense.getDescription()).isEqualTo("Conference travel");
    }

    @Test
    @DisplayName("should set and get classOfService")
    void testClassOfService() {
        ClassOfService cos = new ClassOfService();
        expense.setClassOfService(cos);
        assertThat(expense.getClassOfService()).isSameAs(cos);
    }

    @Test
    @DisplayName("should set and get expenseLineTypeCode")
    void testExpenseLineTypeCode() {
        expense.setExpenseLineTypeCode("IMPORTED");
        assertThat(expense.getExpenseLineTypeCode()).isEqualTo("IMPORTED");
    }

    @Test
    @DisplayName("getDefaultTabOpen should return true when expense details not empty")
    void testDefaultTabOpenWithDetails() {
        expense.addExpenseDetails(new ActualExpense());
        assertThat(expense.getDefaultTabOpen()).isTrue();
    }

    @Test
    @DisplayName("getDefaultTabOpen should return true when mileageIndicator is true")
    void testDefaultTabOpenWithMileage() {
        expense.setMileageIndicator(Boolean.TRUE);
        assertThat(expense.getDefaultTabOpen()).isTrue();
    }

    @Test
    @DisplayName("getDefaultTabOpen should return true when airfareIndicator is true")
    void testDefaultTabOpenWithAirfare() {
        expense.setAirfareIndicator(Boolean.TRUE);
        assertThat(expense.getDefaultTabOpen()).isTrue();
    }

    @Test
    @DisplayName("getDefaultTabOpen should return true when rentalCarIndicator is true")
    void testDefaultTabOpenWithRentalCar() {
        expense.setRentalCarIndicator(Boolean.TRUE);
        assertThat(expense.getDefaultTabOpen()).isTrue();
    }

    @Test
    @DisplayName("getDefaultTabOpen should return false when no indicators set and no details")
    void testDefaultTabOpenNoIndicators() {
        assertThat(expense.getDefaultTabOpen()).isFalse();
    }
}
