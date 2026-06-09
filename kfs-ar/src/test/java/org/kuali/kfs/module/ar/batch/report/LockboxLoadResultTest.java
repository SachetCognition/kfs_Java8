package org.kuali.kfs.module.ar.batch.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class LockboxLoadResultTest extends KfsUnitTestBase {

    private LockboxLoadResult result;

    @BeforeEach
    void setUp() {
        result = new LockboxLoadResult("lockbox.dat", "LB001");
    }

    @Test
    void testDefaultConstructor() {
        LockboxLoadResult r = new LockboxLoadResult();
        assertThat(r.getMessages()).isNotNull().isEmpty();
    }

    @Test
    void testParameterizedConstructor() {
        assertThat(result.getFilename()).isEqualTo("lockbox.dat");
        assertThat(result.getLockboxNumber()).isEqualTo("LB001");
        assertThat(result.getResult()).isEqualTo(LockboxLoadResult.ResultCode.INCOMPLETE);
        assertThat(result.getMessages()).isNotNull().isEmpty();
    }

    @Test
    void testSetSuccessResult() {
        result.setSuccessResult();
        assertThat(result.getResult()).isEqualTo(LockboxLoadResult.ResultCode.SUCCESS);
        assertThat(result.getResultString()).isEqualTo("SUCCESS");
    }

    @Test
    void testSetFailureResult() {
        result.setFailureResult();
        assertThat(result.getResult()).isEqualTo(LockboxLoadResult.ResultCode.FAILURE);
        assertThat(result.getResultString()).isEqualTo("FAILURES");
    }

    @Test
    void testSetErrorResult() {
        result.setErrorResult();
        assertThat(result.getResult()).isEqualTo(LockboxLoadResult.ResultCode.ERROR);
        assertThat(result.getResultString()).isEqualTo("ERROR");
    }

    @Test
    void testGetResultCodeStringIncomplete() {
        assertThat(LockboxLoadResult.getResultCodeString(LockboxLoadResult.ResultCode.INCOMPLETE))
                .isEqualTo("INCOMPLETE");
    }

    @Test
    void testGetEntryTypeStringInfo() {
        assertThat(LockboxLoadResult.getEntryTypeString(LockboxLoadResult.EntryType.INFO))
                .isEqualTo("INFO");
    }

    @Test
    void testGetEntryTypeStringError() {
        assertThat(LockboxLoadResult.getEntryTypeString(LockboxLoadResult.EntryType.ERROR))
                .isEqualTo("ERROR");
    }

    @Test
    void testAddErrorMessage() {
        result.addErrorMessage("Something went wrong");
        assertThat(result.getMessages()).hasSize(1);
        assertThat(result.getMessages().get(0)[0]).isEqualTo("ERROR");
        assertThat(result.getMessages().get(0)[1]).isEqualTo("Something went wrong");
    }

    @Test
    void testAddInfoMessage() {
        result.addInfoMessage("Processing started");
        assertThat(result.getMessages()).hasSize(1);
        assertThat(result.getMessages().get(0)[0]).isEqualTo("INFO");
        assertThat(result.getMessages().get(0)[1]).isEqualTo("Processing started");
    }

    @Test
    void testSetFilename() {
        result.setFilename("new_file.dat");
        assertThat(result.getFilename()).isEqualTo("new_file.dat");
    }

    @Test
    void testSetLockboxNumber() {
        result.setLockboxNumber("LB999");
        assertThat(result.getLockboxNumber()).isEqualTo("LB999");
    }
}
