package org.kuali.kfs.gl.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class LedgerEntryForReportingTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_initializesAmountsToZero() {
        LedgerEntryForReporting entry = new LedgerEntryForReporting();

        assertThat(entry.getCreditAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(entry.getDebitAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(entry.getNoDCAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(entry.getRecordCount()).isZero();
        assertThat(entry.getCreditCount()).isZero();
        assertThat(entry.getDebitCount()).isZero();
        assertThat(entry.getNoDCCount()).isZero();
    }

    @Test
    void parameterizedConstructor_setsFieldsAndInitializesAmounts() {
        LedgerEntryForReporting entry = new LedgerEntryForReporting(2024, "01", "AC", "GN");

        assertThat(entry.getFiscalYear()).isEqualTo(2024);
        assertThat(entry.getPeriod()).isEqualTo("01");
        assertThat(entry.getBalanceType()).isEqualTo("AC");
        assertThat(entry.getOriginCode()).isEqualTo("GN");
        assertThat(entry.getCreditAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(entry.getDebitAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(entry.getNoDCAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void add_combinesAmountsAndCounts() {
        LedgerEntryForReporting entry1 = new LedgerEntryForReporting(2024, "01", "AC", "GN");
        entry1.setCreditAmount(new KualiDecimal(100));
        entry1.setCreditCount(2);
        entry1.setDebitAmount(new KualiDecimal(50));
        entry1.setDebitCount(1);

        LedgerEntryForReporting entry2 = new LedgerEntryForReporting(2024, "01", "AC", "GN");
        entry2.setCreditAmount(new KualiDecimal(200));
        entry2.setCreditCount(3);
        entry2.setDebitAmount(new KualiDecimal(75));
        entry2.setDebitCount(2);
        entry2.setNoDCAmount(new KualiDecimal(25));
        entry2.setNoDCCount(1);

        entry1.add(entry2);

        assertThat(entry1.getCreditAmount()).isEqualTo(new KualiDecimal(300));
        assertThat(entry1.getCreditCount()).isEqualTo(5);
        assertThat(entry1.getDebitAmount()).isEqualTo(new KualiDecimal(125));
        assertThat(entry1.getDebitCount()).isEqualTo(3);
        assertThat(entry1.getNoDCAmount()).isEqualTo(new KualiDecimal(25));
        assertThat(entry1.getNoDCCount()).isEqualTo(1);
        assertThat(entry1.getRecordCount()).isEqualTo(9);
    }

    @Test
    void buildLedgerEntry_withCreditCode_populatesCreditFields() {
        Object[] summary = {2024, "01", "AC", "GN", KFSConstants.GL_CREDIT_CODE, new KualiDecimal(500), 3};

        LedgerEntryForReporting entry = LedgerEntryForReporting.buildLedgerEntry(summary);

        assertThat(entry.getFiscalYear()).isEqualTo(2024);
        assertThat(entry.getPeriod()).isEqualTo("01");
        assertThat(entry.getBalanceType()).isEqualTo("AC");
        assertThat(entry.getOriginCode()).isEqualTo("GN");
        assertThat(entry.getCreditAmount()).isEqualTo(new KualiDecimal(500));
        assertThat(entry.getCreditCount()).isEqualTo(3);
        assertThat(entry.getDebitAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(entry.getDebitCount()).isZero();
    }

    @Test
    void buildLedgerEntry_withDebitCode_populatesDebitFields() {
        Object[] summary = {2024, "02", "CB", "01", KFSConstants.GL_DEBIT_CODE, new KualiDecimal(750), 5};

        LedgerEntryForReporting entry = LedgerEntryForReporting.buildLedgerEntry(summary);

        assertThat(entry.getDebitAmount()).isEqualTo(new KualiDecimal(750));
        assertThat(entry.getDebitCount()).isEqualTo(5);
        assertThat(entry.getCreditAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void buildLedgerEntry_withNoDCCode_populatesNoDCFields() {
        Object[] summary = {2024, "03", "AC", "GN", "N", new KualiDecimal(250), 2};

        LedgerEntryForReporting entry = LedgerEntryForReporting.buildLedgerEntry(summary);

        assertThat(entry.getNoDCAmount()).isEqualTo(new KualiDecimal(250));
        assertThat(entry.getNoDCCount()).isEqualTo(2);
        assertThat(entry.getCreditAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(entry.getDebitAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void buildLedgerEntry_withNullValues_handlesGracefully() {
        Object[] summary = {null, null, null, null, null, null, null};

        LedgerEntryForReporting entry = LedgerEntryForReporting.buildLedgerEntry(summary);

        assertThat(entry.getFiscalYear()).isNull();
        assertThat(entry.getPeriod()).isEqualTo("  ");
        assertThat(entry.getBalanceType()).isEqualTo("  ");
        assertThat(entry.getOriginCode()).isEqualTo("  ");
        assertThat(entry.getNoDCAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(entry.getNoDCCount()).isZero();
    }

    @Test
    void settersAndGetters_workCorrectly() {
        LedgerEntryForReporting entry = new LedgerEntryForReporting();
        entry.setFiscalYear(2025);
        entry.setPeriod("05");
        entry.setBalanceType("EX");
        entry.setOriginCode("01");

        assertThat(entry.getFiscalYear()).isEqualTo(2025);
        assertThat(entry.getPeriod()).isEqualTo("05");
        assertThat(entry.getBalanceType()).isEqualTo("EX");
        assertThat(entry.getOriginCode()).isEqualTo("01");
    }
}
