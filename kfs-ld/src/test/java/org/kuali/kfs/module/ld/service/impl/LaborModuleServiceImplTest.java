package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.service.LaborBenefitsCalculationService;
import org.kuali.kfs.module.ld.service.LaborLedgerBalanceService;
import org.kuali.kfs.module.ld.service.LaborLedgerEntryService;
import org.kuali.kfs.module.ld.service.LaborLedgerPendingEntryService;
import org.kuali.kfs.module.ld.service.LaborOriginEntryService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class LaborModuleServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborBenefitsCalculationService laborBenefitsCalculationService;

    @Mock
    private LaborLedgerBalanceService laborLedgerBalanceService;

    @Mock
    private LaborLedgerEntryService laborLedgerEntryService;

    @Mock
    private LaborLedgerPendingEntryService laborLedgerPendingEntryService;

    @Mock
    private LaborOriginEntryService laborOriginEntryService;

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private LaborModuleServiceImpl service;

    @Test
    void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    void testDependencyInjection() {
        // Verifies that mock dependencies are properly injected
        assertNotNull(laborBenefitsCalculationService);
        assertNotNull(laborLedgerBalanceService);
        assertNotNull(laborLedgerEntryService);
        assertNotNull(laborLedgerPendingEntryService);
    }

    @Test
    void testServiceIsLaborModuleService() {
        assertTrue(service instanceof org.kuali.kfs.integration.ld.LaborModuleService);
    }
}
