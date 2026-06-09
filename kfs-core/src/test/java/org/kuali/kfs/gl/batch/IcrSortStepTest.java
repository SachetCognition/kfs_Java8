package org.kuali.kfs.gl.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class IcrSortStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        IcrSortStep step = new IcrSortStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteRequiresSpringContext() throws Exception {
        IcrSortStep step = new IcrSortStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected: Spring not initialized
        }
    }
}
