package org.kuali.kfs.fp.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class DvToPdpExtractStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        DvToPdpExtractStep step = new DvToPdpExtractStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteWithoutServicesThrows() throws Exception {
        DvToPdpExtractStep step = new DvToPdpExtractStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected: services not injected
        }
    }
}
