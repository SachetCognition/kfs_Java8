package org.kuali.kfs.vnd.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.vnd.batch.service.VendorExcludeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VendorMatchStepTest extends KfsUnitTestBase {

    @Mock
    private VendorExcludeService vendorExcludeService;

    @InjectMocks
    private VendorMatchStep step;

    @BeforeEach
    public void setUp() {
        step.setVendorExcludeService(vendorExcludeService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        when(vendorExcludeService.matchVendors()).thenReturn(true);
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(vendorExcludeService).matchVendors();
    }

    @Test
    public void testExecuteReturnsFalse() throws Exception {
        when(vendorExcludeService.matchVendors()).thenReturn(false);
        boolean result = step.execute("testJob", new Date());
        assertFalse(result);
    }

}
