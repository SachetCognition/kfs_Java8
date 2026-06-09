package org.kuali.kfs.module.tem.document;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.ActualExpense;
import org.kuali.kfs.module.tem.businessobject.PerDiemExpense;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TravelReimbursementDocument")
class TravelReimbursementDocumentTest extends KfsUnitTestBase {

    private TravelReimbursementDocument document;

    @BeforeEach
    void setUp() {
        document = Mockito.mock(TravelReimbursementDocument.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    @DisplayName("should set and get actual expenses")
    void testActualExpenses() {
        List<ActualExpense> expenses = new ArrayList<>();
        expenses.add(new ActualExpense());
        document.setActualExpenses(expenses);
        assertThat(document.getActualExpenses()).hasSize(1);
    }

    @Test
    @DisplayName("should set and get per diem expenses")
    void testPerDiemExpenses() {
        List<PerDiemExpense> expenses = new ArrayList<>();
        expenses.add(new PerDiemExpense());
        document.setPerDiemExpenses(expenses);
        assertThat(document.getPerDiemExpenses()).hasSize(1);
    }
}
