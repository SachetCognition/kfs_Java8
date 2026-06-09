package org.kuali.kfs.module.cab.businessobject;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BatchParametersTest extends KfsUnitTestBase {

    private BatchParameters params;

    @BeforeEach
    void setUp() {
        params = new BatchParameters();
    }

    @Test
    @DisplayName("default constructor: all fields null")
    void defaultConstructor() {
        assertThat(params.getLastRunTime()).isNull();
        assertThat(params.getLastRunDate()).isNull();
        assertThat(params.getExcludedChartCodes()).isNull();
        assertThat(params.getExcludedSubFundCodes()).isNull();
        assertThat(params.getIncludedFinancialBalanceTypeCodes()).isNull();
        assertThat(params.getExcludedFiscalPeriods()).isNull();
        assertThat(params.getExcludedDocTypeCodes()).isNull();
        assertThat(params.getIncludedFinancialObjectSubTypeCodes()).isNull();
        assertThat(params.getCapitalizationLimitAmount()).isNull();
    }

    @Test
    @DisplayName("lastRunTime getter/setter")
    void lastRunTime() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        params.setLastRunTime(ts);
        assertThat(params.getLastRunTime()).isEqualTo(ts);
    }

    @Test
    @DisplayName("lastRunDate getter/setter")
    void lastRunDate() {
        Date date = Date.valueOf("2024-06-01");
        params.setLastRunDate(date);
        assertThat(params.getLastRunDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("excludedChartCodes getter/setter")
    void excludedChartCodes() {
        Collection<String> codes = Arrays.asList("UA", "BL");
        params.setExcludedChartCodes(codes);
        assertThat(params.getExcludedChartCodes()).containsExactly("UA", "BL");
    }

    @Test
    @DisplayName("excludedSubFundCodes getter/setter")
    void excludedSubFundCodes() {
        Collection<String> codes = Arrays.asList("HIEDUA", "AUXEN");
        params.setExcludedSubFundCodes(codes);
        assertThat(params.getExcludedSubFundCodes()).containsExactly("HIEDUA", "AUXEN");
    }

    @Test
    @DisplayName("includedFinancialBalanceTypeCodes getter/setter")
    void includedFinancialBalanceTypeCodes() {
        Collection<String> codes = Arrays.asList("AC", "CB");
        params.setIncludedFinancialBalanceTypeCodes(codes);
        assertThat(params.getIncludedFinancialBalanceTypeCodes()).containsExactly("AC", "CB");
    }

    @Test
    @DisplayName("excludedFiscalPeriods getter/setter")
    void excludedFiscalPeriods() {
        Collection<String> periods = Arrays.asList("13", "BB");
        params.setExcludedFiscalPeriods(periods);
        assertThat(params.getExcludedFiscalPeriods()).containsExactly("13", "BB");
    }

    @Test
    @DisplayName("excludedDocTypeCodes getter/setter")
    void excludedDocTypeCodes() {
        Collection<String> docTypes = Arrays.asList("GEC", "SB");
        params.setExcludedDocTypeCodes(docTypes);
        assertThat(params.getExcludedDocTypeCodes()).containsExactly("GEC", "SB");
    }

    @Test
    @DisplayName("includedFinancialObjectSubTypeCodes getter/setter")
    void includedFinancialObjectSubTypeCodes() {
        Collection<String> codes = Arrays.asList("CM", "CF");
        params.setIncludedFinancialObjectSubTypeCodes(codes);
        assertThat(params.getIncludedFinancialObjectSubTypeCodes()).containsExactly("CM", "CF");
    }

    @Test
    @DisplayName("capitalizationLimitAmount getter/setter")
    void capitalizationLimitAmount() {
        BigDecimal limit = new BigDecimal("5000.00");
        params.setCapitalizationLimitAmount(limit);
        assertThat(params.getCapitalizationLimitAmount()).isEqualTo(limit);
    }
}
