package org.kuali.kfs.sys.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class PurgeTempFilesStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        PurgeTempFilesStep step = new PurgeTempFilesStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteWithoutConfigThrows() throws Exception {
        PurgeTempFilesStep step = new PurgeTempFilesStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // Expected: missing configuration
        }
    }
}
