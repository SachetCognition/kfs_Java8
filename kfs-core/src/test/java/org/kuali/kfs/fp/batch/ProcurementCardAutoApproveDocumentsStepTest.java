package org.kuali.kfs.fp.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class ProcurementCardAutoApproveDocumentsStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        ProcurementCardAutoApproveDocumentsStep step = new ProcurementCardAutoApproveDocumentsStep();
        assertNotNull(step);
    }
}
