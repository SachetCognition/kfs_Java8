package org.kuali.kfs.module.tem.document;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.ActualExpense;
import org.kuali.kfs.module.tem.businessobject.ImportedExpense;
import org.kuali.kfs.module.tem.businessobject.PerDiemExpense;
import org.kuali.kfs.module.tem.businessobject.TransportationModeDetail;
import org.kuali.kfs.module.tem.businessobject.TravelAdvance;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("TravelAuthorizationDocument")
class TravelAuthorizationDocumentTest extends KfsUnitTestBase {

    private TravelAuthorizationDocument document;

    @BeforeEach
    void setUp() {
        document = Mockito.mock(TravelAuthorizationDocument.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    @DisplayName("should set and get actual expenses")
    void testActualExpenses() {
        List<ActualExpense> expenses = new ArrayList<>();
        ActualExpense expense = new ActualExpense();
        expense.setExpenseAmount(new KualiDecimal(100));
        expenses.add(expense);

        document.setActualExpenses(expenses);
        when(document.getActualExpenses()).thenCallRealMethod();
        assertThat(document.getActualExpenses()).hasSize(1);
    }

    @Test
    @DisplayName("should set and get per diem expenses")
    void testPerDiemExpenses() {
        List<PerDiemExpense> expenses = new ArrayList<>();
        PerDiemExpense expense = new PerDiemExpense();
        expense.setCountryState("CA");
        expenses.add(expense);

        document.setPerDiemExpenses(expenses);
        assertThat(document.getPerDiemExpenses()).hasSize(1);
    }

    @Test
    @DisplayName("should set and get imported expenses")
    void testImportedExpenses() {
        List<ImportedExpense> expenses = new ArrayList<>();
        expenses.add(new ImportedExpense());
        document.setImportedExpenses(expenses);
        assertThat(document.getImportedExpenses()).hasSize(1);
    }

    @Test
    @DisplayName("should set and get transportation modes")
    void testTransportationModes() {
        List<TransportationModeDetail> modes = new ArrayList<>();
        modes.add(new TransportationModeDetail());
        document.setTransportationModes(modes);
        assertThat(document.getTransportationModes()).hasSize(1);
    }

    @Test
    @DisplayName("should set and get travel advance")
    void testTravelAdvance() {
        TravelAdvance advance = new TravelAdvance();
        advance.setTravelAdvanceRequested(new KualiDecimal(500));
        document.setTravelAdvance(advance);
        assertThat(document.getTravelAdvance()).isNotNull();
        assertThat(document.getTravelAdvance().getTravelAdvanceRequested())
                .isEqualTo(new KualiDecimal(500));
    }
}
