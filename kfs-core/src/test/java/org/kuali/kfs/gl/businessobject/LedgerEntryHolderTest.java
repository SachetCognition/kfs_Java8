package org.kuali.kfs.gl.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class LedgerEntryHolderTest extends KfsUnitTestBase {

    private LedgerEntryHolder holder;

    @BeforeEach
    void setUp() {
        holder = new LedgerEntryHolder();
    }

    @Test
    void constructor_initializesEmptyCollections() {
        assertThat(holder.getLedgerEntries()).isEmpty();
        assertThat(holder.getSubtotals()).isEmpty();
        assertThat(holder.getGrandTotal()).isNotNull();
    }

    @Test
    void insertLedgerEntry_withCalculateTotal_addsEntryAndUpdatesTotals() {
        LedgerEntryForReporting entry = new LedgerEntryForReporting(2024, "01", "AC", "GN");
        entry.setCreditAmount(new KualiDecimal(100));
        entry.setCreditCount(1);

        holder.insertLedgerEntry(entry, true);

        assertThat(holder.getLedgerEntries()).hasSize(1);
        assertThat(holder.getSubtotals()).hasSize(1);
        assertThat(holder.getGrandTotal().getCreditAmount()).isEqualTo(new KualiDecimal(100));
    }

    @Test
    void insertLedgerEntry_withoutCalculateTotal_addsEntryWithoutTotals() {
        LedgerEntryForReporting entry = new LedgerEntryForReporting(2024, "01", "AC", "GN");
        entry.setDebitAmount(new KualiDecimal(50));
        entry.setDebitCount(1);

        holder.insertLedgerEntry(entry, false);

        assertThat(holder.getLedgerEntries()).hasSize(1);
        assertThat(holder.getSubtotals()).isEmpty();
        assertThat(holder.getGrandTotal().getDebitAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void insertLedgerEntry_duplicateKey_mergesEntries() {
        LedgerEntryForReporting entry1 = new LedgerEntryForReporting(2024, "01", "AC", "GN");
        entry1.setCreditAmount(new KualiDecimal(100));
        entry1.setCreditCount(1);

        LedgerEntryForReporting entry2 = new LedgerEntryForReporting(2024, "01", "AC", "GN");
        entry2.setCreditAmount(new KualiDecimal(200));
        entry2.setCreditCount(2);

        holder.insertLedgerEntry(entry1, true);
        holder.insertLedgerEntry(entry2, true);

        assertThat(holder.getLedgerEntries()).hasSize(1);
        LedgerEntryForReporting merged = (LedgerEntryForReporting) holder.getLedgerEntries().get("AC-GN-2024-01");
        assertThat(merged.getCreditAmount()).isEqualTo(new KualiDecimal(300));
        assertThat(merged.getCreditCount()).isEqualTo(3);
    }

    @Test
    void insertLedgerEntry_differentKeys_createsMultipleEntries() {
        LedgerEntryForReporting entry1 = new LedgerEntryForReporting(2024, "01", "AC", "GN");
        entry1.setCreditAmount(new KualiDecimal(100));
        entry1.setCreditCount(1);

        LedgerEntryForReporting entry2 = new LedgerEntryForReporting(2024, "02", "CB", "01");
        entry2.setDebitAmount(new KualiDecimal(200));
        entry2.setDebitCount(1);

        holder.insertLedgerEntry(entry1, true);
        holder.insertLedgerEntry(entry2, true);

        assertThat(holder.getLedgerEntries()).hasSize(2);
        assertThat(holder.getSubtotals()).hasSize(2);
    }

    @Test
    void insertLedgerEntry_sameBalanceType_groupsUnderOneSubtotal() {
        LedgerEntryForReporting entry1 = new LedgerEntryForReporting(2024, "01", "AC", "GN");
        entry1.setCreditAmount(new KualiDecimal(100));
        entry1.setCreditCount(1);

        LedgerEntryForReporting entry2 = new LedgerEntryForReporting(2024, "02", "AC", "01");
        entry2.setDebitAmount(new KualiDecimal(200));
        entry2.setDebitCount(1);

        holder.insertLedgerEntry(entry1, true);
        holder.insertLedgerEntry(entry2, true);

        assertThat(holder.getSubtotals()).hasSize(1);
        LedgerEntryForReporting subtotal = (LedgerEntryForReporting) holder.getSubtotals().get("AC");
        assertThat(subtotal.getCreditAmount()).isEqualTo(new KualiDecimal(100));
        assertThat(subtotal.getDebitAmount()).isEqualTo(new KualiDecimal(200));
    }

    @Test
    void grandTotal_accumulatesAcrossAllEntries() {
        LedgerEntryForReporting entry1 = new LedgerEntryForReporting(2024, "01", "AC", "GN");
        entry1.setCreditAmount(new KualiDecimal(100));
        entry1.setCreditCount(1);

        LedgerEntryForReporting entry2 = new LedgerEntryForReporting(2024, "02", "CB", "01");
        entry2.setCreditAmount(new KualiDecimal(200));
        entry2.setCreditCount(2);

        holder.insertLedgerEntry(entry1, true);
        holder.insertLedgerEntry(entry2, true);

        assertThat(holder.getGrandTotal().getCreditAmount()).isEqualTo(new KualiDecimal(300));
        assertThat(holder.getGrandTotal().getCreditCount()).isEqualTo(3);
    }
}
