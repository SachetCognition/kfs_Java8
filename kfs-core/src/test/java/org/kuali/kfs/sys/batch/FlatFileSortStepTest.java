package org.kuali.kfs.sys.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class FlatFileSortStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        FlatFileSortStep step = new FlatFileSortStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteWithoutConfigThrows() throws Exception {
        FlatFileSortStep step = new FlatFileSortStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // Expected: missing configuration
        }
    }
}
