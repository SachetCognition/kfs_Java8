package org.kuali.kfs.module.tem.businessobject;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ImportedExpense Business Object")
class ImportedExpenseTest extends KfsUnitTestBase {

    private ImportedExpense expense;

    @BeforeEach
    void setUp() {
        expense = new ImportedExpense();
    }

    @Test
    @DisplayName("should have EXPENSE_IMPORTED as default line type code")
    void testDefaultExpenseLineTypeCode() {
        assertThat(expense.getExpenseLineTypeCode()).isEqualTo(TemConstants.EXPENSE_IMPORTED);
    }

    @Test
    @DisplayName("should set and get cardType")
    void testCardType() {
        expense.setCardType("CORP");
        assertThat(expense.getCardType()).isEqualTo("CORP");
    }

    @Test
    @DisplayName("should set inherited fields from AbstractExpense")
    void testInheritedFields() {
        expense.setDocumentNumber("DOC001");
        expense.setExpenseAmount(new KualiDecimal(250.50));
        expense.setDescription("Hotel charge");
        expense.setExpenseDate(Date.valueOf("2024-06-15"));

        assertThat(expense.getDocumentNumber()).isEqualTo("DOC001");
        assertThat(expense.getExpenseAmount()).isEqualTo(new KualiDecimal(250.50));
        assertThat(expense.getDescription()).isEqualTo("Hotel charge");
        assertThat(expense.getExpenseDate()).isEqualTo(Date.valueOf("2024-06-15"));
    }

    @Test
    @DisplayName("should set and get nonReimbursable flag")
    void testNonReimbursable() {
        expense.setNonReimbursable(Boolean.TRUE);
        assertThat(expense.getNonReimbursable()).isTrue();
    }

    @Test
    @DisplayName("should set and get taxable flag")
    void testTaxable() {
        expense.setTaxable(Boolean.TRUE);
        assertThat(expense.getTaxable()).isTrue();
    }

    @Test
    @DisplayName("should set and get currencyRate")
    void testCurrencyRate() {
        BigDecimal rate = new BigDecimal("1.25");
        expense.setCurrencyRate(rate);
        assertThat(expense.getCurrencyRate()).isEqualByComparingTo(rate);
    }

    @Test
    @DisplayName("should set and get missingReceipt")
    void testMissingReceipt() {
        expense.setMissingReceipt(Boolean.TRUE);
        assertThat(expense.getMissingReceipt()).isTrue();
    }
}
