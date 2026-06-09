package org.kuali.kfs.gl.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class DemergerReportDataTest extends KfsUnitTestBase {

    private DemergerReportData reportData;

    @BeforeEach
    void setUp() {
        reportData = new DemergerReportData();
    }

    @Test
    void defaultConstructor_initializesAllCountsToZero() {
        assertThat(reportData.getErrorTransactionsRead()).isZero();
        assertThat(reportData.getValidTransactionsRead()).isZero();
        assertThat(reportData.getErrorTransactionsSaved()).isZero();
        assertThat(reportData.getValidTransactionsSaved()).isZero();
        assertThat(reportData.getOffsetTransactionsBypassed()).isZero();
        assertThat(reportData.getCapitalizationTransactionsBypassed()).isZero();
        assertThat(reportData.getLiabilityTransactionsBypassed()).isZero();
        assertThat(reportData.getTransferTransactionsBypassed()).isZero();
        assertThat(reportData.getCostShareTransactionsBypassed()).isZero();
        assertThat(reportData.getCostShareEncumbranceTransactionsBypassed()).isZero();
    }

    @Test
    void incrementErrorTransactionsRead_incrementsByOne() {
        reportData.incrementErrorTransactionsRead();
        reportData.incrementErrorTransactionsRead();

        assertThat(reportData.getErrorTransactionsRead()).isEqualTo(2);
    }

    @Test
    void incrementValidTransactionsRead_incrementsByOne() {
        reportData.incrementValidTransactionsRead();

        assertThat(reportData.getValidTransactionsRead()).isEqualTo(1);
    }

    @Test
    void incrementErrorTransactionsSaved_incrementsByOne() {
        reportData.incrementErrorTransactionsSaved();
        reportData.incrementErrorTransactionsSaved();
        reportData.incrementErrorTransactionsSaved();

        assertThat(reportData.getErrorTransactionsSaved()).isEqualTo(3);
    }

    @Test
    void incrementValidTransactionsSaved_incrementsByOne() {
        reportData.incrementValidTransactionsSaved();

        assertThat(reportData.getValidTransactionsSaved()).isEqualTo(1);
    }

    @Test
    void incrementOffsetTransactionsBypassed_incrementsByOne() {
        reportData.incrementOffsetTransactionsBypassed();

        assertThat(reportData.getOffsetTransactionsBypassed()).isEqualTo(1);
    }

    @Test
    void incrementCapitalizationTransactionsBypassed_incrementsByOne() {
        reportData.incrementCapitalizationTransactionsBypassed();

        assertThat(reportData.getCapitalizationTransactionsBypassed()).isEqualTo(1);
    }

    @Test
    void incrementLiabilityTransactionsBypassed_incrementsByOne() {
        reportData.incrementLiabilityTransactionsBypassed();

        assertThat(reportData.getLiabilityTransactionsBypassed()).isEqualTo(1);
    }

    @Test
    void incrementTransferTransactionsBypassed_incrementsByOne() {
        reportData.incrementTransferTransactionsBypassed();

        assertThat(reportData.getTransferTransactionsBypassed()).isEqualTo(1);
    }

    @Test
    void incrementCostShareTransactionsBypassed_incrementsByOne() {
        reportData.incrementCostShareTransactionsBypassed();

        assertThat(reportData.getCostShareTransactionsBypassed()).isEqualTo(1);
    }

    @Test
    void incrementCostShareEncumbranceTransactionsBypassed_incrementsByOne() {
        reportData.incrementCostShareEncumbranceTransactionsBypassed();

        assertThat(reportData.getCostShareEncumbranceTransactionsBypassed()).isEqualTo(1);
    }

    @Test
    void incorporateReportData_addsAllFieldsFromAnotherReport() {
        DemergerReportData other = new DemergerReportData();
        other.incrementErrorTransactionsRead();
        other.incrementErrorTransactionsRead();
        other.incrementValidTransactionsRead();
        other.incrementValidTransactionsRead();
        other.incrementValidTransactionsRead();
        other.incrementErrorTransactionsSaved();
        other.incrementValidTransactionsSaved();
        other.incrementOffsetTransactionsBypassed();
        other.incrementCapitalizationTransactionsBypassed();
        other.incrementLiabilityTransactionsBypassed();
        other.incrementTransferTransactionsBypassed();
        other.incrementCostShareTransactionsBypassed();
        other.incrementCostShareEncumbranceTransactionsBypassed();

        reportData.incrementErrorTransactionsRead();
        reportData.incorporateReportData(other);

        assertThat(reportData.getErrorTransactionsRead()).isEqualTo(3);
        assertThat(reportData.getValidTransactionsRead()).isEqualTo(3);
        assertThat(reportData.getErrorTransactionsSaved()).isEqualTo(1);
        assertThat(reportData.getValidTransactionsSaved()).isEqualTo(1);
        assertThat(reportData.getOffsetTransactionsBypassed()).isEqualTo(1);
        assertThat(reportData.getCapitalizationTransactionsBypassed()).isEqualTo(1);
        assertThat(reportData.getLiabilityTransactionsBypassed()).isEqualTo(1);
        assertThat(reportData.getTransferTransactionsBypassed()).isEqualTo(1);
        assertThat(reportData.getCostShareTransactionsBypassed()).isEqualTo(1);
        assertThat(reportData.getCostShareEncumbranceTransactionsBypassed()).isEqualTo(1);
    }

    @Test
    void incorporateReportData_withEmptyReport_doesNotChangeValues() {
        reportData.incrementErrorTransactionsRead();
        reportData.incrementValidTransactionsRead();

        DemergerReportData emptyReport = new DemergerReportData();
        reportData.incorporateReportData(emptyReport);

        assertThat(reportData.getErrorTransactionsRead()).isEqualTo(1);
        assertThat(reportData.getValidTransactionsRead()).isEqualTo(1);
    }
}
