package org.kuali.kfs.module.bc.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class BudgetConstructionUpdateBatchStepTest extends KfsUnitTestBase {

    @Test
    public void testStepClassExists() {
        try {
            Class.forName("org.kuali.kfs.module.bc.batch.BudgetConstructionUpdateBatchStep");
        } catch (ClassNotFoundException e) {
            fail("Step class not found");
        }
    }
}
