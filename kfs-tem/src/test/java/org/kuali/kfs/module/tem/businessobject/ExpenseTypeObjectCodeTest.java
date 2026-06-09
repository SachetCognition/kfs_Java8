package org.kuali.kfs.module.tem.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ExpenseTypeObjectCode Business Object")
class ExpenseTypeObjectCodeTest extends KfsUnitTestBase {

    private ExpenseTypeObjectCode etoc;

    @BeforeEach
    void setUp() {
        etoc = new ExpenseTypeObjectCode();
    }

    @Test
    @DisplayName("should set and get expenseTypeCode")
    void testExpenseTypeCode() {
        etoc.setExpenseTypeCode("AIRFARE");
        assertThat(etoc.getExpenseTypeCode()).isEqualTo("AIRFARE");
    }

    @Test
    @DisplayName("should set and get tripTypeCode")
    void testTripTypeCode() {
        etoc.setTripTypeCode("IN");
        assertThat(etoc.getTripTypeCode()).isEqualTo("IN");
    }

    @Test
    @DisplayName("should set and get travelerTypeCode")
    void testTravelerTypeCode() {
        etoc.setTravelerTypeCode("EMP");
        assertThat(etoc.getTravelerTypeCode()).isEqualTo("EMP");
    }

    @Test
    @DisplayName("should set and get documentTypeName")
    void testDocumentTypeName() {
        etoc.setDocumentTypeName("TravelAuthorization");
        assertThat(etoc.getDocumentTypeName()).isEqualTo("TravelAuthorization");
    }

    @Test
    @DisplayName("should set and get financialObjectCode")
    void testFinancialObjectCode() {
        etoc.setFinancialObjectCode("6000");
        assertThat(etoc.getFinancialObjectCode()).isEqualTo("6000");
    }

    @Test
    @DisplayName("should set and get expenseTypeObjectCodeId")
    void testId() {
        etoc.setExpenseTypeObjectCodeId(100L);
        assertThat(etoc.getExpenseTypeObjectCodeId()).isEqualTo(100L);
    }

    @Test
    @DisplayName("should set and get maximumAmount")
    void testMaximumAmount() {
        etoc.setMaximumAmount(new org.kuali.rice.core.api.util.type.KualiDecimal(500));
        assertThat(etoc.getMaximumAmount()).isEqualTo(new org.kuali.rice.core.api.util.type.KualiDecimal(500));
    }

    @Test
    @DisplayName("should set and get expenseType")
    void testExpenseType() {
        ExpenseType type = new ExpenseType();
        etoc.setExpenseType(type);
        assertThat(etoc.getExpenseType()).isSameAs(type);
    }

    @Test
    @DisplayName("should set and get noteRequired flag")
    void testNoteRequired() {
        etoc.setNoteRequired(true);
        assertThat(etoc.isNoteRequired()).isTrue();
    }

    @Test
    @DisplayName("should set and get receiptRequired flag")
    void testReceiptRequired() {
        etoc.setReceiptRequired(true);
        assertThat(etoc.isReceiptRequired()).isTrue();
    }
}
