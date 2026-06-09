package org.kuali.kfs.module.ec.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class EffortCertificationExtractStepTest extends KfsUnitTestBase {

    @Test
    public void testStepClassExists() {
        try {
            Class.forName("org.kuali.kfs.module.ec.batch.EffortCertificationExtractStep");
        } catch (ClassNotFoundException e) {
            fail("Step class not found");
        }
    }
}
