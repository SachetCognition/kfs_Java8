package org.kuali.kfs.module.cg.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class CloseBatchStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        CloseBatchStep step = new CloseBatchStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteWithoutServicesThrows() throws Exception {
        CloseBatchStep step = new CloseBatchStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected exception");
        } catch (Exception e) {
            // Expected: missing service dependencies
        }
    }
}
