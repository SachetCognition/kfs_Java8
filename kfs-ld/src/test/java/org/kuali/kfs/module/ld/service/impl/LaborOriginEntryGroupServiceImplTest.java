package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.service.LaborOriginEntryGroupService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class LaborOriginEntryGroupServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private LaborOriginEntryGroupServiceImpl service;

    @Test
    void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    void testServiceImplementsInterface() {
        assertTrue(service instanceof LaborOriginEntryGroupService);
    }
}
