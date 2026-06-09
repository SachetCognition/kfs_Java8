package org.kuali.kfs.sys.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class ReportAggregationStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        ReportAggregationStep step = new ReportAggregationStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteRequiresSpringContext() throws Exception {
        ReportAggregationStep step = new ReportAggregationStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected: Spring not initialized
        }
    }
}
