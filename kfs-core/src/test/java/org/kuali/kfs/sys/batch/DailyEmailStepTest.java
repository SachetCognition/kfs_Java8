package org.kuali.kfs.sys.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class DailyEmailStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        DailyEmailStep step = new DailyEmailStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteRequiresSpringContext() throws Exception {
        DailyEmailStep step = new DailyEmailStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected: Spring not initialized
        }
    }
}
