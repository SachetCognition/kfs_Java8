package org.kuali.kfs.module.cg.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.batch.service.MaintenanceDocumentNotesMigrationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MoveAgencyAndAwardMaintDocNotesToBusinessObjectsStepTest extends KfsUnitTestBase {

    @Mock
    private MaintenanceDocumentNotesMigrationService maintenanceDocumentNotesMigrationService;

    @InjectMocks
    private MoveAgencyAndAwardMaintDocNotesToBusinessObjectsStep step;

    @BeforeEach
    public void setUp() {
        step.setMaintenanceDocumentNotesMigrationService(maintenanceDocumentNotesMigrationService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(maintenanceDocumentNotesMigrationService).moveAgencyMaintenanceDocumentNotesToBusinessObjects();
        verify(maintenanceDocumentNotesMigrationService).moveAwardMaintenanceDocumentNotesToBusinessObjects();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(maintenanceDocumentNotesMigrationService, atLeastOnce()).moveAgencyMaintenanceDocumentNotesToBusinessObjects();
    }

}
