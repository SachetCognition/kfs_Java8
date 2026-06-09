package org.kuali.kfs.module.cam.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.businessobject.BarcodeInventoryErrorDetail;
import org.kuali.kfs.module.cam.document.BarcodeInventoryErrorDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class BarcodeInventoryErrorDetailPredicateTest extends KfsUnitTestBase {

    @Mock
    private BarcodeInventoryErrorDocument doc;

    private BarcodeInventoryErrorDetailPredicate predicate;

    @BeforeEach
    void setUp() {
        predicate = new BarcodeInventoryErrorDetailPredicate(doc);
    }

    private BarcodeInventoryErrorDetail createErrorDetail() {
        BarcodeInventoryErrorDetail detail = new BarcodeInventoryErrorDetail();
        detail.setErrorCorrectionStatusCode(CamsConstants.BarCodeInventoryError.STATUS_CODE_ERROR);
        detail.setAssetTagNumber("TAG001");
        detail.setCampusCode("BL");
        detail.setBuildingCode("BLDG1");
        detail.setBuildingRoomNumber("101");
        detail.setBuildingSubRoomNumber("A");
        detail.setAssetConditionCode("G");
        detail.setUploadScanIndicator(true);
        return detail;
    }

    @Test
    @DisplayName("evaluate: non-error status returns false")
    void evaluate_nonErrorStatus_returnsFalse() {
        BarcodeInventoryErrorDetail detail = createErrorDetail();
        detail.setErrorCorrectionStatusCode("C");

        assertThat(predicate.evaluate(detail)).isFalse();
    }

    @Test
    @DisplayName("evaluate: error status with no filter criteria returns true")
    void evaluate_errorStatusNoFilter_returnsTrue() {
        when(doc.getCurrentTagNumber()).thenReturn(null);
        when(doc.getCurrentScanCode()).thenReturn(null);
        when(doc.getCurrentCampusCode()).thenReturn(null);
        when(doc.getCurrentBuildingNumber()).thenReturn(null);
        when(doc.getCurrentRoom()).thenReturn(null);
        when(doc.getCurrentSubroom()).thenReturn(null);
        when(doc.getCurrentConditionCode()).thenReturn(null);

        BarcodeInventoryErrorDetail detail = createErrorDetail();
        assertThat(predicate.evaluate(detail)).isTrue();
    }

    @Test
    @DisplayName("evaluate: matching tag number returns true")
    void evaluate_matchingTagNumber_returnsTrue() {
        when(doc.getCurrentTagNumber()).thenReturn("TAG001");
        when(doc.getCurrentScanCode()).thenReturn(null);
        when(doc.getCurrentCampusCode()).thenReturn(null);
        when(doc.getCurrentBuildingNumber()).thenReturn(null);
        when(doc.getCurrentRoom()).thenReturn(null);
        when(doc.getCurrentSubroom()).thenReturn(null);
        when(doc.getCurrentConditionCode()).thenReturn(null);

        BarcodeInventoryErrorDetail detail = createErrorDetail();
        assertThat(predicate.evaluate(detail)).isTrue();
    }

    @Test
    @DisplayName("evaluate: non-matching tag number returns false")
    void evaluate_nonMatchingTagNumber_returnsFalse() {
        when(doc.getCurrentTagNumber()).thenReturn("TAG999");
        when(doc.getCurrentScanCode()).thenReturn(null);
        when(doc.getCurrentCampusCode()).thenReturn(null);
        when(doc.getCurrentBuildingNumber()).thenReturn(null);
        when(doc.getCurrentRoom()).thenReturn(null);
        when(doc.getCurrentSubroom()).thenReturn(null);
        when(doc.getCurrentConditionCode()).thenReturn(null);

        BarcodeInventoryErrorDetail detail = createErrorDetail();
        assertThat(predicate.evaluate(detail)).isFalse();
    }

    @Test
    @DisplayName("evaluate: matching campus code returns true")
    void evaluate_matchingCampusCode() {
        when(doc.getCurrentTagNumber()).thenReturn(null);
        when(doc.getCurrentScanCode()).thenReturn(null);
        when(doc.getCurrentCampusCode()).thenReturn("BL");
        when(doc.getCurrentBuildingNumber()).thenReturn(null);
        when(doc.getCurrentRoom()).thenReturn(null);
        when(doc.getCurrentSubroom()).thenReturn(null);
        when(doc.getCurrentConditionCode()).thenReturn(null);

        BarcodeInventoryErrorDetail detail = createErrorDetail();
        assertThat(predicate.evaluate(detail)).isTrue();
    }

    @Test
    @DisplayName("evaluate: non-matching campus code returns false")
    void evaluate_nonMatchingCampusCode() {
        when(doc.getCurrentTagNumber()).thenReturn(null);
        when(doc.getCurrentScanCode()).thenReturn(null);
        when(doc.getCurrentCampusCode()).thenReturn("IN");
        when(doc.getCurrentBuildingNumber()).thenReturn(null);
        when(doc.getCurrentRoom()).thenReturn(null);
        when(doc.getCurrentSubroom()).thenReturn(null);
        when(doc.getCurrentConditionCode()).thenReturn(null);

        BarcodeInventoryErrorDetail detail = createErrorDetail();
        assertThat(predicate.evaluate(detail)).isFalse();
    }

    @Test
    @DisplayName("evaluate: non-matching building number returns false")
    void evaluate_nonMatchingBuildingNumber() {
        when(doc.getCurrentTagNumber()).thenReturn(null);
        when(doc.getCurrentScanCode()).thenReturn(null);
        when(doc.getCurrentCampusCode()).thenReturn(null);
        when(doc.getCurrentBuildingNumber()).thenReturn("BLDG99");
        when(doc.getCurrentRoom()).thenReturn(null);
        when(doc.getCurrentSubroom()).thenReturn(null);
        when(doc.getCurrentConditionCode()).thenReturn(null);

        BarcodeInventoryErrorDetail detail = createErrorDetail();
        assertThat(predicate.evaluate(detail)).isFalse();
    }

    @Test
    @DisplayName("evaluate: non-matching room returns false")
    void evaluate_nonMatchingRoom() {
        when(doc.getCurrentTagNumber()).thenReturn(null);
        when(doc.getCurrentScanCode()).thenReturn(null);
        when(doc.getCurrentCampusCode()).thenReturn(null);
        when(doc.getCurrentBuildingNumber()).thenReturn(null);
        when(doc.getCurrentRoom()).thenReturn("999");
        when(doc.getCurrentSubroom()).thenReturn(null);
        when(doc.getCurrentConditionCode()).thenReturn(null);

        BarcodeInventoryErrorDetail detail = createErrorDetail();
        assertThat(predicate.evaluate(detail)).isFalse();
    }

    @Test
    @DisplayName("evaluate: non-matching condition code returns false")
    void evaluate_nonMatchingConditionCode() {
        when(doc.getCurrentTagNumber()).thenReturn(null);
        when(doc.getCurrentScanCode()).thenReturn(null);
        when(doc.getCurrentCampusCode()).thenReturn(null);
        when(doc.getCurrentBuildingNumber()).thenReturn(null);
        when(doc.getCurrentRoom()).thenReturn(null);
        when(doc.getCurrentSubroom()).thenReturn(null);
        when(doc.getCurrentConditionCode()).thenReturn("P");

        BarcodeInventoryErrorDetail detail = createErrorDetail();
        assertThat(predicate.evaluate(detail)).isFalse();
    }

    @Test
    @DisplayName("evaluate: non-BarcodeInventoryErrorDetail object returns true")
    void evaluate_nonDetailObject_returnsTrue() {
        assertThat(predicate.evaluate("not a detail")).isTrue();
    }

    @Test
    @DisplayName("execute: replaces campus code when new value is set")
    void execute_replacesCampusCode() {
        when(doc.getCurrentTagNumber()).thenReturn(null);
        when(doc.getCurrentScanCode()).thenReturn(null);
        when(doc.getCurrentCampusCode()).thenReturn(null);
        when(doc.getCurrentBuildingNumber()).thenReturn(null);
        when(doc.getCurrentRoom()).thenReturn(null);
        when(doc.getCurrentSubroom()).thenReturn(null);
        when(doc.getCurrentConditionCode()).thenReturn(null);
        when(doc.getNewCampusCode()).thenReturn("IN");
        when(doc.getNewBuildingNumber()).thenReturn("NEWBLDG");
        when(doc.getNewRoom()).thenReturn("200");
        when(doc.getNewSubroom()).thenReturn("B");
        when(doc.getNewConditionCode()).thenReturn("E");

        BarcodeInventoryErrorDetail detail = createErrorDetail();
        predicate.execute(detail);

        assertThat(detail.getCampusCode()).isEqualTo("IN");
        assertThat(detail.getBuildingCode()).isEqualTo("NEWBLDG");
        assertThat(detail.getBuildingRoomNumber()).isEqualTo("200");
        assertThat(detail.getBuildingSubRoomNumber()).isEqualTo("B");
        assertThat(detail.getAssetConditionCode()).isEqualTo("E");
    }
}
