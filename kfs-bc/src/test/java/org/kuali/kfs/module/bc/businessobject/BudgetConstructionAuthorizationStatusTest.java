package org.kuali.kfs.module.bc.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetConstructionAuthorizationStatusTest extends KfsUnitTestBase {

    @Test
    void constructor_initializesEmptyMaps() {
        BudgetConstructionAuthorizationStatus status = new BudgetConstructionAuthorizationStatus();

        assertThat(status.getEditingMode()).isNotNull().isEmpty();
        assertThat(status.getDocumentActions()).isNotNull().isEmpty();
    }

    @Test
    void setAndGetEditingMode() {
        BudgetConstructionAuthorizationStatus status = new BudgetConstructionAuthorizationStatus();
        Map<String, String> editingMode = new HashMap<>();
        editingMode.put("viewOnly", "TRUE");

        status.setEditingMode(editingMode);
        assertThat(status.getEditingMode()).containsEntry("viewOnly", "TRUE");
    }

    @Test
    void setAndGetDocumentActions() {
        BudgetConstructionAuthorizationStatus status = new BudgetConstructionAuthorizationStatus();
        Map<String, String> documentActions = new HashMap<>();
        documentActions.put("canEdit", "TRUE");
        documentActions.put("canSave", "TRUE");

        status.setDocumentActions(documentActions);
        assertThat(status.getDocumentActions()).hasSize(2);
        assertThat(status.getDocumentActions()).containsEntry("canEdit", "TRUE");
    }
}
