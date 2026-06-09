package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class CfdaUpdateResultsTest extends KfsUnitTestBase {

    private CfdaUpdateResults results;

    @BeforeEach
    void setUp() {
        results = new CfdaUpdateResults();
    }

    @Test
    void testDefaultValues() {
        assertThat(results.getNumberOfRecordsRetrievedFromWebSite()).isZero();
        assertThat(results.getNumberOfRecordsInKfsDatabase()).isZero();
        assertThat(results.getNumberOfRecrodsNotUpdatedForHistoricalPurposes()).isZero();
        assertThat(results.getNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite()).isZero();
        assertThat(results.getNumberOfRecordsReActivated()).isZero();
        assertThat(results.getNumberOfRecordsNotUpdatedBecauseManual()).isZero();
        assertThat(results.getNumberOfRecordsUpdatedBecauseAutomatic()).isZero();
        assertThat(results.getNumberOfRecordsNewlyAddedFromWebSite()).isZero();
        assertThat(results.getMessage()).isNull();
    }

    @Test
    void testNumberOfRecordsRetrievedFromWebSite() {
        results.setNumberOfRecordsRetrievedFromWebSite(100);
        assertThat(results.getNumberOfRecordsRetrievedFromWebSite()).isEqualTo(100);
    }

    @Test
    void testNumberOfRecordsInKfsDatabase() {
        results.setNumberOfRecordsInKfsDatabase(50);
        assertThat(results.getNumberOfRecordsInKfsDatabase()).isEqualTo(50);
    }

    @Test
    void testNumberOfRecrodsNotUpdatedForHistoricalPurposes() {
        results.setNumberOfRecrodsNotUpdatedForHistoricalPurposes(5);
        assertThat(results.getNumberOfRecrodsNotUpdatedForHistoricalPurposes()).isEqualTo(5);
    }

    @Test
    void testNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite() {
        results.setNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite(3);
        assertThat(results.getNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite()).isEqualTo(3);
    }

    @Test
    void testNumberOfRecordsReActivated() {
        results.setNumberOfRecordsReActivated(2);
        assertThat(results.getNumberOfRecordsReActivated()).isEqualTo(2);
    }

    @Test
    void testNumberOfRecordsNotUpdatedBecauseManual() {
        results.setNumberOfRecordsNotUpdatedBecauseManual(10);
        assertThat(results.getNumberOfRecordsNotUpdatedBecauseManual()).isEqualTo(10);
    }

    @Test
    void testNumberOfRecordsUpdatedBecauseAutomatic() {
        results.setNumberOfRecordsUpdatedBecauseAutomatic(25);
        assertThat(results.getNumberOfRecordsUpdatedBecauseAutomatic()).isEqualTo(25);
    }

    @Test
    void testNumberOfRecordsNewlyAddedFromWebSite() {
        results.setNumberOfRecordsNewlyAddedFromWebSite(7);
        assertThat(results.getNumberOfRecordsNewlyAddedFromWebSite()).isEqualTo(7);
    }

    @Test
    void testMessage() {
        results.setMessage("Update completed successfully");
        assertThat(results.getMessage()).isEqualTo("Update completed successfully");
    }

    @Test
    void testMessageNull() {
        results.setMessage(null);
        assertThat(results.getMessage()).isNull();
    }
}
