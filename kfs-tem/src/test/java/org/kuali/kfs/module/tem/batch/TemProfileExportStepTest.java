package org.kuali.kfs.module.tem.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class TemProfileExportStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        TemProfileExportStep step = new TemProfileExportStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteWithoutServicesThrows() throws Exception {
        TemProfileExportStep step = new TemProfileExportStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected exception");
        } catch (Exception e) {
            // Expected: missing service dependencies
        }
    }
}
