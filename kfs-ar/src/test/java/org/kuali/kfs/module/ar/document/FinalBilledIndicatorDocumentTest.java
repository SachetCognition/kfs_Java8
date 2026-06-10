package org.kuali.kfs.module.ar.document;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.FinalBilledIndicatorEntry;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalBilledIndicatorDocumentTest extends KfsUnitTestBase {

    @Test
    void testFinalBilledIndicatorEntryList() {
        List<FinalBilledIndicatorEntry> entries = new ArrayList<>();
        FinalBilledIndicatorEntry entry1 = new FinalBilledIndicatorEntry();
        entry1.setInvoiceDocumentNumber("INV001");
        entries.add(entry1);

        FinalBilledIndicatorEntry entry2 = new FinalBilledIndicatorEntry();
        entry2.setInvoiceDocumentNumber("INV002");
        entries.add(entry2);

        assertThat(entries).hasSize(2);
        assertThat(entries.get(0).getInvoiceDocumentNumber()).isEqualTo("INV001");
        assertThat(entries.get(1).getInvoiceDocumentNumber()).isEqualTo("INV002");
    }

    @Test
    void testFinalBilledIndicatorEntryRemoval() {
        List<FinalBilledIndicatorEntry> entries = new ArrayList<>();
        FinalBilledIndicatorEntry entry = new FinalBilledIndicatorEntry();
        entry.setInvoiceDocumentNumber("INV001");
        entries.add(entry);
        entries.remove(0);
        assertThat(entries).isEmpty();
    }

    @Test
    void testEntryDocumentIdPropagation() {
        FinalBilledIndicatorEntry entry = new FinalBilledIndicatorEntry();
        entry.setDocumentId("DOC001");
        entry.setInvoiceDocumentNumber("INV001");
        assertThat(entry.getDocumentId()).isEqualTo("DOC001");
        assertThat(entry.getInvoiceDocumentNumber()).isEqualTo("INV001");
    }
}
