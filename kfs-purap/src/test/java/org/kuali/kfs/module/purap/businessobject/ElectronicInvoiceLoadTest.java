package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.document.ElectronicInvoiceRejectDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ElectronicInvoiceLoadTest extends KfsUnitTestBase {

    @Test
    void defaultConstructorCreatesEmptyCollections() {
        ElectronicInvoiceLoad load = new ElectronicInvoiceLoad();
        assertThat(load.getInvoiceLoadSummaries()).isNotNull().isEmpty();
        assertThat(load.getRejectFilesToMove()).isNotNull().isEmpty();
        assertThat(load.getRejectDocuments()).isNotNull().isEmpty();
    }

    @Test
    void insertInvoiceLoadSummary() {
        ElectronicInvoiceLoad load = new ElectronicInvoiceLoad();
        ElectronicInvoiceLoadSummary summary = new ElectronicInvoiceLoadSummary("123456789");
        load.insertInvoiceLoadSummary(summary);
        assertThat(load.getInvoiceLoadSummaries()).hasSize(1);
        assertThat(load.getInvoiceLoadSummaries()).containsKey("123456789");
    }

    @Test
    void addRejectFileToMove() {
        ElectronicInvoiceLoad load = new ElectronicInvoiceLoad();
        File file = new File("/tmp/test.xml");
        load.addRejectFileToMove(file, "/tmp/reject");
        assertThat(load.getRejectFilesToMove()).hasSize(1);
    }

    @Test
    void containsRejectsWhenEmpty() {
        ElectronicInvoiceLoad load = new ElectronicInvoiceLoad();
        assertThat(load.containsRejects()).isFalse();
    }

    @Test
    void containsRejectsAfterAdding() {
        ElectronicInvoiceLoad load = new ElectronicInvoiceLoad();
        ElectronicInvoiceRejectDocument rejectDoc = mock(ElectronicInvoiceRejectDocument.class);
        load.addInvoiceReject(rejectDoc);
        assertThat(load.containsRejects()).isTrue();
        assertThat(load.getRejectDocuments()).hasSize(1);
    }
}
