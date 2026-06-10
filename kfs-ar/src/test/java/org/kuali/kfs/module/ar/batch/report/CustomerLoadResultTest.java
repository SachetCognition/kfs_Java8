package org.kuali.kfs.module.ar.batch.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerLoadResultTest extends KfsUnitTestBase {

    private CustomerLoadResult result;

    @BeforeEach
    void setUp() {
        result = new CustomerLoadResult("testfile.xml", "Test Corp");
    }

    @Test
    void testDefaultConstructor() {
        CustomerLoadResult r = new CustomerLoadResult();
        assertThat(r.getMessages()).isNotNull().isEmpty();
    }

    @Test
    void testParameterizedConstructor() {
        assertThat(result.getFilename()).isEqualTo("testfile.xml");
        assertThat(result.getCustomerName()).isEqualTo("Test Corp");
        assertThat(result.getResult()).isEqualTo(CustomerLoadResult.ResultCode.INCOMPLETE);
        assertThat(result.getMessages()).isNotNull().isEmpty();
    }

    @Test
    void testSetSuccessResult() {
        result.setSuccessResult();
        assertThat(result.getResult()).isEqualTo(CustomerLoadResult.ResultCode.SUCCESS);
        assertThat(result.getResultString()).isEqualTo("SUCCESS");
    }

    @Test
    void testSetFailureResult() {
        result.setFailureResult();
        assertThat(result.getResult()).isEqualTo(CustomerLoadResult.ResultCode.FAILURE);
        assertThat(result.getResultString()).isEqualTo("FAILURES");
    }

    @Test
    void testSetErrorResult() {
        result.setErrorResult();
        assertThat(result.getResult()).isEqualTo(CustomerLoadResult.ResultCode.ERROR);
        assertThat(result.getResultString()).isEqualTo("ERROR");
    }

    @Test
    void testGetResultCodeStringIncomplete() {
        assertThat(CustomerLoadResult.getResultCodeString(CustomerLoadResult.ResultCode.INCOMPLETE))
                .isEqualTo("INCOMPLETE");
    }

    @Test
    void testGetEntryTypeStringInfo() {
        assertThat(CustomerLoadResult.getEntryTypeString(CustomerLoadResult.EntryType.INFO))
                .isEqualTo("INFO");
    }

    @Test
    void testGetEntryTypeStringError() {
        assertThat(CustomerLoadResult.getEntryTypeString(CustomerLoadResult.EntryType.ERROR))
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
    void testMultipleMessages() {
        result.addInfoMessage("Step 1 complete");
        result.addErrorMessage("Step 2 failed");
        result.addInfoMessage("Step 3 skipped");

        assertThat(result.getMessages()).hasSize(3);
    }

    @Test
    void testWorkflowDocId() {
        result.setWorkflowDocId("WF12345");
        assertThat(result.getWorkflowDocId()).isEqualTo("WF12345");
    }

    @Test
    void testFilename() {
        result.setFilename("another.xml");
        assertThat(result.getFilename()).isEqualTo("another.xml");
    }

    @Test
    void testCustomerName() {
        result.setCustomerName("New Corp");
        assertThat(result.getCustomerName()).isEqualTo("New Corp");
    }
}
