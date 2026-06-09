package org.kuali.kfs.sys.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class FlatFileTransactionInformationTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_emptyMessages() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation();
        assertThat(info.getMessages()).isEmpty();
        assertThat(info.getFlatFileDataIdentifier()).isNull();
        assertThat(info.getResult()).isNull();
    }

    @Test
    void parameterizedConstructor_setsIdentifierAndIncompleteResult() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation("TX-001");
        assertThat(info.getFlatFileDataIdentifier()).isEqualTo("TX-001");
        assertThat(info.getResult()).isEqualTo(FlatFileTransactionInformation.ResultCode.INCOMPLETE);
        assertThat(info.getMessages()).isEmpty();
    }

    @Test
    void setSuccessResult() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation("TX-001");
        info.setSuccessResult();
        assertThat(info.getResult()).isEqualTo(FlatFileTransactionInformation.ResultCode.SUCCESS);
    }

    @Test
    void setFailureResult() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation("TX-001");
        info.setFailureResult();
        assertThat(info.getResult()).isEqualTo(FlatFileTransactionInformation.ResultCode.FAILURE);
    }

    @Test
    void setErrorResult() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation("TX-001");
        info.setErrorResult();
        assertThat(info.getResult()).isEqualTo(FlatFileTransactionInformation.ResultCode.ERROR);
    }

    @Test
    void getResultString_withSuccessResult() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation("TX-001");
        info.setSuccessResult();
        assertThat(info.getResultString()).isEqualTo("SUCCESS");
    }

    @Test
    void getResultString_withNullResult() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation();
        assertThat(info.getResultString()).isEqualTo("UNKNOWN");
    }

    @Test
    void addErrorMessage() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation("TX-001");
        info.addErrorMessage("Something went wrong");
        assertThat(info.getMessages()).hasSize(1);
        assertThat(info.getMessages().get(0)[0]).isEqualTo("ERROR");
        assertThat(info.getMessages().get(0)[1]).isEqualTo("Something went wrong");
    }

    @Test
    void addInfoMessage() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation("TX-001");
        info.addInfoMessage("Processing complete");
        assertThat(info.getMessages()).hasSize(1);
        assertThat(info.getMessages().get(0)[0]).isEqualTo("INFO");
        assertThat(info.getMessages().get(0)[1]).isEqualTo("Processing complete");
    }

    @Test
    void addWarnMessage() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation("TX-001");
        info.addWarnMessage("Watch out");
        assertThat(info.getMessages()).hasSize(1);
        assertThat(info.getMessages().get(0)[0]).isEqualTo("WARN");
        assertThat(info.getMessages().get(0)[1]).isEqualTo("Watch out");
    }

    @Test
    void multipleMessages_appendedInOrder() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation("TX-001");
        info.addInfoMessage("first");
        info.addErrorMessage("second");
        info.addWarnMessage("third");
        assertThat(info.getMessages()).hasSize(3);
        assertThat(info.getMessages().get(0)[1]).isEqualTo("first");
        assertThat(info.getMessages().get(1)[1]).isEqualTo("second");
        assertThat(info.getMessages().get(2)[1]).isEqualTo("third");
    }

    @Test
    void setFlatFileDataIdentifier() {
        FlatFileTransactionInformation info = new FlatFileTransactionInformation();
        info.setFlatFileDataIdentifier("NEW-ID");
        assertThat(info.getFlatFileDataIdentifier()).isEqualTo("NEW-ID");
    }

    // ──────────────────── static helpers ────────────────────────────

    @Test
    void getEntryTypeString_INFO() {
        assertThat(FlatFileTransactionInformation.getEntryTypeString(
                FlatFileTransactionInformation.EntryType.INFO)).isEqualTo("INFO");
    }

    @Test
    void getEntryTypeString_ERROR() {
        assertThat(FlatFileTransactionInformation.getEntryTypeString(
                FlatFileTransactionInformation.EntryType.ERROR)).isEqualTo("ERROR");
    }

    @Test
    void getEntryTypeString_WARN() {
        assertThat(FlatFileTransactionInformation.getEntryTypeString(
                FlatFileTransactionInformation.EntryType.WARN)).isEqualTo("WARN");
    }

    @Test
    void getEntryTypeString_null_returnsUNKNOWN() {
        assertThat(FlatFileTransactionInformation.getEntryTypeString(null)).isEqualTo("UNKNOWN");
    }

    @Test
    void getResultCodeString_allValues() {
        assertThat(FlatFileTransactionInformation.getResultCodeString(
                FlatFileTransactionInformation.ResultCode.SUCCESS)).isEqualTo("SUCCESS");
        assertThat(FlatFileTransactionInformation.getResultCodeString(
                FlatFileTransactionInformation.ResultCode.FAILURE)).isEqualTo("FAILURE");
        assertThat(FlatFileTransactionInformation.getResultCodeString(
                FlatFileTransactionInformation.ResultCode.ERROR)).isEqualTo("ERROR");
        assertThat(FlatFileTransactionInformation.getResultCodeString(
                FlatFileTransactionInformation.ResultCode.INCOMPLETE)).isEqualTo("INCOMPLETE");
    }

    @Test
    void getResultCodeString_null_returnsUNKNOWN() {
        assertThat(FlatFileTransactionInformation.getResultCodeString(null)).isEqualTo("UNKNOWN");
    }
}
