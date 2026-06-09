package org.kuali.kfs.sys.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class BatchContainerStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        BatchContainerStep step = new BatchContainerStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteWithoutConfigThrows() throws Exception {
        BatchContainerStep step = new BatchContainerStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // Expected: missing configuration
        }
    }
}
