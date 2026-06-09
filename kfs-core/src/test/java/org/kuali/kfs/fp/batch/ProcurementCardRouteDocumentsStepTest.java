package org.kuali.kfs.fp.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class ProcurementCardRouteDocumentsStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        ProcurementCardRouteDocumentsStep step = new ProcurementCardRouteDocumentsStep();
        assertNotNull(step);
    }
}
