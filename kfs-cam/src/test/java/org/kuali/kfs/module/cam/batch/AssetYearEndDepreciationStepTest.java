package org.kuali.kfs.module.cam.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cam.batch.service.AssetDepreciationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AssetYearEndDepreciationStepTest extends KfsUnitTestBase {

    @Mock
    private AssetDepreciationService assetDepreciationService;

    @InjectMocks
    private AssetYearEndDepreciationStep step;

    @BeforeEach
    public void setUp() {
        step.setAssetDepreciationService(assetDepreciationService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
    }

}
