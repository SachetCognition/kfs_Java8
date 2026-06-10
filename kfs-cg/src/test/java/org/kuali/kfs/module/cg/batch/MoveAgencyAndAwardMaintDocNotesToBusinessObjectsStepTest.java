package org.kuali.kfs.module.cg.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.batch.service.MaintenanceDocumentNotesMigrationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class MoveAgencyAndAwardMaintDocNotesToBusinessObjectsStepTest extends KfsUnitTestBase {

    @Mock
    private MaintenanceDocumentNotesMigrationService maintenanceDocumentNotesMigrationService;

    private MoveAgencyAndAwardMaintDocNotesToBusinessObjectsStep step;

    @BeforeEach
    void setUp() {
        step = new MoveAgencyAndAwardMaintDocNotesToBusinessObjectsStep();
        step.setMaintenanceDocumentNotesMigrationService(maintenanceDocumentNotesMigrationService);
    }

    @Test
    void testExecuteCallsBothMigrationMethods() throws InterruptedException {
        boolean result = step.execute("testJob", new Date());

        assertThat(result).isTrue();
        verify(maintenanceDocumentNotesMigrationService).moveAgencyMaintenanceDocumentNotesToBusinessObjects();
        verify(maintenanceDocumentNotesMigrationService).moveAwardMaintenanceDocumentNotesToBusinessObjects();
    }

    @Test
    void testGetMaintenanceDocumentNotesMigrationService() {
        assertThat(step.getMaintenanceDocumentNotesMigrationService())
                .isSameAs(maintenanceDocumentNotesMigrationService);
    }
}
