package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.document.Document;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class FinalBilledIndicatorEntryTest extends KfsUnitTestBase {

    private FinalBilledIndicatorEntry entry;

    @BeforeEach
    void setUp() {
        entry = new FinalBilledIndicatorEntry();
    }

    @Test
    void testId() {
        entry.setId(100L);
        assertThat(entry.getId()).isEqualTo(100L);
    }

    @Test
    void testInvoiceDocumentNumber() {
        entry.setInvoiceDocumentNumber("INV001");
        assertThat(entry.getInvoiceDocumentNumber()).isEqualTo("INV001");
    }

    @Test
    void testDocumentId() {
        entry.setDocumentId("DOC001");
        assertThat(entry.getDocumentId()).isEqualTo("DOC001");
    }

    @Test
    void testInvoiceDocument() {
        Document mockDoc = mock(Document.class);
        entry.setInvoiceDocument(mockDoc);
        assertThat(entry.getInvoiceDocument()).isEqualTo(mockDoc);
    }

    @Test
    void testGetDocumentReturnsInvoiceDocument() {
        Document mockDoc = mock(Document.class);
        entry.setInvoiceDocument(mockDoc);
        assertThat(entry.getDocument()).isEqualTo(mockDoc);
    }

    @Test
    void testSetDocumentSetsInvoiceDocument() {
        Document mockDoc = mock(Document.class);
        entry.setDocument(mockDoc);
        assertThat(entry.getInvoiceDocument()).isEqualTo(mockDoc);
    }

    @Test
    void testToStringMapper() {
        entry.setId(1L);
        entry.setInvoiceDocumentNumber("INV001");
        entry.setDocumentId("DOC001");

        var map = entry.toStringMapper_RICE20_REFACTORME();
        assertThat(map).containsKey("id");
        assertThat(map).containsKey("invoiceDocumentNumber");
        assertThat(map).containsKey("documentId");
    }
}
