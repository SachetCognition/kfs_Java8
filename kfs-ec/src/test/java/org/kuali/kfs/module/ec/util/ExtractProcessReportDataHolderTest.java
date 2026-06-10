package org.kuali.kfs.module.ec.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportDefinition;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ExtractProcessReportDataHolderTest extends KfsUnitTestBase {

    @Nested
    @DisplayName("Default constructor")
    class DefaultConstructor {

        @Test
        void shouldInitializeWithNullReportDefinition() {
            ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder();
            assertThat(holder.getReportDefinition()).isNull();
        }

        @Test
        void shouldInitializeWithEmptyStatistics() {
            ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder();
            assertThat(holder.getBasicStatistics()).isEmpty();
        }

        @Test
        void shouldInitializeWithEmptyLedgerBalances() {
            ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder();
            assertThat(holder.getLedgerBalancesWithMessage()).isEmpty();
        }
    }

    @Nested
    @DisplayName("Constructor with report definition")
    class ParameterizedConstructor {

        @Test
        void shouldSetReportDefinition() {
            EffortCertificationReportDefinition reportDef = new EffortCertificationReportDefinition();
            reportDef.setEffortCertificationReportNumber("A01");
            ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder(reportDef);
            assertThat(holder.getReportDefinition()).isSameAs(reportDef);
        }
    }

    @Nested
    @DisplayName("Getter/Setter tests")
    class GetterSetterTests {

        @Test
        void shouldSetAndGetReportDefinition() {
            ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder();
            EffortCertificationReportDefinition reportDef = new EffortCertificationReportDefinition();
            reportDef.setEffortCertificationReportNumber("A01");
            holder.setReportDefinition(reportDef);
            assertThat(holder.getReportDefinition()).isSameAs(reportDef);
        }

        @Test
        void shouldSetAndGetBasicStatistics() {
            ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder();
            Map<String, Integer> stats = new HashMap<>();
            stats.put("totalRecords", 100);
            holder.setBasicStatistics(stats);
            assertThat(holder.getBasicStatistics()).containsEntry("totalRecords", 100);
        }

        @Test
        void shouldSetAndGetLedgerBalancesWithMessage() {
            ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder();
            List<LedgerBalanceWithMessage> balances = new ArrayList<>();
            balances.add(new LedgerBalanceWithMessage("E001", "John", "msg"));
            holder.setLedgerBalancesWithMessage(balances);
            assertThat(holder.getLedgerBalancesWithMessage()).hasSize(1);
        }
    }

    @Nested
    @DisplayName("updateBasicStatistics")
    class UpdateStatistics {

        @Test
        void shouldIncrementExistingCounter() {
            ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder();
            holder.updateBasicStatistics("processedCount", 1);
            holder.updateBasicStatistics("processedCount", 1);
            assertThat(holder.getBasicStatistics()).containsEntry("processedCount", 2);
        }

        @Test
        void shouldCreateNewEntryIfNotExists() {
            ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder();
            holder.updateBasicStatistics("newStat", 5);
            assertThat(holder.getBasicStatistics()).containsEntry("newStat", 5);
        }

        @Test
        void shouldAccumulateMultipleUpdates() {
            ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder();
            holder.updateBasicStatistics("count", 10);
            holder.updateBasicStatistics("count", 20);
            holder.updateBasicStatistics("count", 30);
            assertThat(holder.getBasicStatistics()).containsEntry("count", 60);
        }
    }

    @Nested
    @DisplayName("getReportData")
    class GetReportData {

        @Test
        void shouldPopulateReportDataFromDefinition() {
            EffortCertificationReportDefinition reportDef = new EffortCertificationReportDefinition();
            reportDef.setUniversityFiscalYear(2024);
            reportDef.setEffortCertificationReportNumber("A01");
            reportDef.setEffortCertificationReportBeginPeriodCode("01");
            reportDef.setEffortCertificationReportBeginFiscalYear(2024);
            reportDef.setEffortCertificationReportEndPeriodCode("06");
            reportDef.setEffortCertificationReportEndFiscalYear(2024);

            ExtractProcessReportDataHolder holder = new ExtractProcessReportDataHolder(reportDef);

            Map<String, Object> data = holder.getReportData();
            assertThat(data).containsEntry(ExtractProcessReportDataHolder.REPORT_YEAR, 2024);
            assertThat(data).containsEntry(ExtractProcessReportDataHolder.REPORT_NUMBER, "A01");
            assertThat(data).containsKey(ExtractProcessReportDataHolder.KEY_OF_STATISTICS_ENTRY);
            assertThat(data).containsKey(ExtractProcessReportDataHolder.KEY_OF_ERRORS_ENTRY);
        }
    }

    @Nested
    @DisplayName("Static constants")
    class StaticConstants {

        @Test
        void shouldHaveExpectedKeys() {
            assertThat(ExtractProcessReportDataHolder.KEY_OF_STATISTICS_ENTRY).isEqualTo("statistics");
            assertThat(ExtractProcessReportDataHolder.KEY_OF_ERRORS_ENTRY).isEqualTo("errors");
            assertThat(ExtractProcessReportDataHolder.REPORT_YEAR).isEqualTo("reportYear");
            assertThat(ExtractProcessReportDataHolder.REPORT_NUMBER).isEqualTo("reportNumber");
        }
    }
}
