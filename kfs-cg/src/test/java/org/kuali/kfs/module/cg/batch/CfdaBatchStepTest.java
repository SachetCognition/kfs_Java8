package org.kuali.kfs.module.cg.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class CfdaBatchStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        CfdaBatchStep step = new CfdaBatchStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteWithoutServicesThrows() throws Exception {
        CfdaBatchStep step = new CfdaBatchStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected exception");
        } catch (Exception e) {
            // Expected: missing service dependencies
        }
    }
}
