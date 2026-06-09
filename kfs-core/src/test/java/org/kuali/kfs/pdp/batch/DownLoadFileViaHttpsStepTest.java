package org.kuali.kfs.pdp.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class DownLoadFileViaHttpsStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        DownLoadFileViaHttpsStep step = new DownLoadFileViaHttpsStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteWithoutConfigThrows() throws Exception {
        DownLoadFileViaHttpsStep step = new DownLoadFileViaHttpsStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // Expected: missing configuration
        }
    }
}
