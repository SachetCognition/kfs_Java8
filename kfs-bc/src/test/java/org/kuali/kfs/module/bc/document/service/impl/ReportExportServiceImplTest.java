package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.ReportDumpDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ReportExportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ReportDumpDao reportDumpDao;

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private ReportExportServiceImpl service;

    @Test
    public void testUpdateAccountDump() {
        service.updateAccountDump("user1");
        verify(reportDumpDao).updateAccountDump("user1");
    }

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }
}
