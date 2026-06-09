package org.kuali.kfs.module.ar.batch;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerInvoiceDocumentBatchStepTest extends KfsUnitTestBase {

    @Test
    public void testStepInstantiation() {
        CustomerInvoiceDocumentBatchStep step = new CustomerInvoiceDocumentBatchStep();
        assertNotNull(step);
    }

    @Test
    public void testExecuteWithoutServicesThrows() throws Exception {
        CustomerInvoiceDocumentBatchStep step = new CustomerInvoiceDocumentBatchStep();
        try {
            step.execute("testJob", new Date());
            fail("Expected exception");
        } catch (Exception e) {
            // Expected: missing service dependencies
        }
    }
}
