package org.kuali.kfs.module.purap.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class PurapMassRequisitionStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        PurapMassRequisitionStep step = new PurapMassRequisitionStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteRequiresSpringContext() throws Exception {
        PurapMassRequisitionStep step = new PurapMassRequisitionStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected: Spring not initialized
        }
    }
}
