package org.kuali.kfs.module.cab.batch;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.gl.businessobject.Entry;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ExtractProcessLogTest extends KfsUnitTestBase {

    private ExtractProcessLog log;

    @BeforeEach
    void setUp() {
        log = new ExtractProcessLog();
    }

    @Test
    @DisplayName("default state: success is true, counts are zero")
    void defaultState() {
        assertThat(log.isSuccess()).isTrue();
        assertThat(log.getTotalGlCount()).isZero();
        assertThat(log.getNonPurApGlCount()).isZero();
        assertThat(log.getPurApGlCount()).isZero();
        assertThat(log.getIgnoredGLEntries()).isNull();
        assertThat(log.getDuplicateGLEntries()).isNull();
        assertThat(log.getMismatchedGLEntries()).isNull();
    }

    @Test
    @DisplayName("addIgnoredGLEntry initializes list on first call")
    void addIgnoredGLEntry_initializesListOnFirstCall() {
        Entry entry = new Entry();
        log.addIgnoredGLEntry(entry);
        assertThat(log.getIgnoredGLEntries()).hasSize(1).containsExactly(entry);
    }

    @Test
    @DisplayName("addIgnoredGLEntries initializes and adds collection")
    void addIgnoredGLEntries_initializesAndAddsCollection() {
        Entry e1 = new Entry();
        Entry e2 = new Entry();
        log.addIgnoredGLEntries(Arrays.asList(e1, e2));
        assertThat(log.getIgnoredGLEntries()).hasSize(2);
    }

    @Test
    @DisplayName("addDuplicateGLEntry initializes list on first call")
    void addDuplicateGLEntry_initializesListOnFirstCall() {
        Entry entry = new Entry();
        log.addDuplicateGLEntry(entry);
        assertThat(log.getDuplicateGLEntries()).hasSize(1).containsExactly(entry);
    }

    @Test
    @DisplayName("addDuplicateGLEntries initializes and adds collection")
    void addDuplicateGLEntries_initializesAndAddsCollection() {
        Entry e1 = new Entry();
        Entry e2 = new Entry();
        log.addDuplicateGLEntries(Arrays.asList(e1, e2));
        assertThat(log.getDuplicateGLEntries()).hasSize(2);
    }

    @Test
    @DisplayName("addMismatchedGLEntry initializes list on first call")
    void addMismatchedGLEntry_initializesListOnFirstCall() {
        Entry entry = new Entry();
        log.addMismatchedGLEntry(entry);
        assertThat(log.getMismatchedGLEntries()).hasSize(1).containsExactly(entry);
    }

    @Test
    @DisplayName("addMismatchedGLEntries initializes and adds collection")
    void addMismatchedGLEntries_initializesAndAddsCollection() {
        log.addMismatchedGLEntries(Arrays.asList(new Entry(), new Entry(), new Entry()));
        assertThat(log.getMismatchedGLEntries()).hasSize(3);
    }

    @Test
    @DisplayName("timestamp setters/getters work correctly")
    void timestampSettersGetters() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        log.setStartTime(now);
        log.setFinishTime(now);
        log.setLastExtractTime(now);
        assertThat(log.getStartTime()).isEqualTo(now);
        assertThat(log.getFinishTime()).isEqualTo(now);
        assertThat(log.getLastExtractTime()).isEqualTo(now);
    }

    @Test
    @DisplayName("success flag can be toggled")
    void successFlagToggle() {
        log.setSuccess(false);
        assertThat(log.isSuccess()).isFalse();
    }

    @Test
    @DisplayName("errorMessage getter/setter")
    void errorMessage() {
        log.setErrorMessage("Something went wrong");
        assertThat(log.getErrorMessage()).isEqualTo("Something went wrong");
    }

    @Test
    @DisplayName("counts getter/setter work for totalGlCount, nonPurApGlCount, purApGlCount")
    void counts() {
        log.setTotalGlCount(100);
        log.setNonPurApGlCount(60);
        log.setPurApGlCount(40);
        assertThat(log.getTotalGlCount()).isEqualTo(100);
        assertThat(log.getNonPurApGlCount()).isEqualTo(60);
        assertThat(log.getPurApGlCount()).isEqualTo(40);
    }

    @Test
    @DisplayName("getStatusMessage: returns 'Success' when success=true and statusMessage is null")
    void getStatusMessage_successTrue_nullStatus() {
        assertThat(log.getStatusMessage()).isEqualTo("Success");
    }

    @Test
    @DisplayName("getStatusMessage: returns error message when success=false")
    void getStatusMessage_successFalse() {
        log.setSuccess(false);
        log.setErrorMessage("DB error");
        assertThat(log.getStatusMessage()).isEqualTo("DB error");
    }

    @Test
    @DisplayName("getStatusMessage: returns empty when success=false and no error message")
    void getStatusMessage_successFalse_noErrorMessage() {
        log.setSuccess(false);
        assertThat(log.getStatusMessage()).isEmpty();
    }

    @Test
    @DisplayName("getStatusMessage: returns explicit status message when set")
    void getStatusMessage_explicitStatusMessage() {
        log.setStatusMessage("Custom status");
        assertThat(log.getStatusMessage()).isEqualTo("Custom status");
    }

    @Test
    @DisplayName("multiple addIgnoredGLEntry calls accumulate entries")
    void multipleAddIgnoredGLEntry_accumulates() {
        log.addIgnoredGLEntry(new Entry());
        log.addIgnoredGLEntry(new Entry());
        log.addIgnoredGLEntry(new Entry());
        assertThat(log.getIgnoredGLEntries()).hasSize(3);
    }

    @Test
    @DisplayName("addIgnoredGLEntries followed by addIgnoredGLEntry accumulates")
    void addIgnoredGLEntries_thenAddSingle() {
        log.addIgnoredGLEntries(Collections.singletonList(new Entry()));
        log.addIgnoredGLEntry(new Entry());
        assertThat(log.getIgnoredGLEntries()).hasSize(2);
    }
}
