package org.kuali.kfs.module.cam.businessobject;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AssetRepairHistoryTest extends KfsUnitTestBase {

    private AssetRepairHistory history;

    @BeforeEach
    void setUp() {
        history = new AssetRepairHistory();
    }

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        assertThat(history.getCapitalAssetNumber()).isNull();
        assertThat(history.getIncidentDate()).isNull();
        assertThat(history.getProblemDescription()).isNull();
        assertThat(history.getRepairContactName()).isNull();
        assertThat(history.getRepairNoteText()).isNull();
        assertThat(history.getEstimatedRepairDate()).isNull();
        assertThat(history.getRepairDate()).isNull();
        assertThat(history.getRepairAmount()).isNull();
        assertThat(history.getRepairSolutionDescription()).isNull();
        assertThat(history.isActive()).isFalse();
    }

    @Test
    @DisplayName("capitalAssetNumber getter/setter")
    void capitalAssetNumber() {
        history.setCapitalAssetNumber(12345L);
        assertThat(history.getCapitalAssetNumber()).isEqualTo(12345L);
    }

    @Test
    @DisplayName("incidentDate getter/setter")
    void incidentDate() {
        Date date = Date.valueOf("2024-03-15");
        history.setIncidentDate(date);
        assertThat(history.getIncidentDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("problemDescription getter/setter")
    void problemDescription() {
        history.setProblemDescription("Screen cracked");
        assertThat(history.getProblemDescription()).isEqualTo("Screen cracked");
    }

    @Test
    @DisplayName("repairContactName getter/setter")
    void repairContactName() {
        history.setRepairContactName("Jane Smith");
        assertThat(history.getRepairContactName()).isEqualTo("Jane Smith");
    }

    @Test
    @DisplayName("repairNoteText getter/setter")
    void repairNoteText() {
        history.setRepairNoteText("Sent to manufacturer");
        assertThat(history.getRepairNoteText()).isEqualTo("Sent to manufacturer");
    }

    @Test
    @DisplayName("repair dates: estimated and actual")
    void repairDates() {
        Date estimated = Date.valueOf("2024-04-01");
        Date actual = Date.valueOf("2024-03-28");
        history.setEstimatedRepairDate(estimated);
        history.setRepairDate(actual);
        assertThat(history.getEstimatedRepairDate()).isEqualTo(estimated);
        assertThat(history.getRepairDate()).isEqualTo(actual);
    }

    @Test
    @DisplayName("repairAmount getter/setter")
    void repairAmount() {
        KualiDecimal amount = new KualiDecimal(350.00);
        history.setRepairAmount(amount);
        assertThat(history.getRepairAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("repairSolutionDescription getter/setter")
    void repairSolutionDescription() {
        history.setRepairSolutionDescription("Replaced screen assembly");
        assertThat(history.getRepairSolutionDescription()).isEqualTo("Replaced screen assembly");
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        history.setActive(true);
        assertThat(history.isActive()).isTrue();
    }
}
