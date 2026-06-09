package org.kuali.kfs.module.bc.document.service.impl;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionDocumentAccountObjectDetailReportDao;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionReportsServiceHelper;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionDocumentAccountObjectDetailReportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionDocumentAccountObjectDetailReportDao budgetConstructionDocumentAccountObjectDetailReportDao;

    @Mock
    private ConfigurationService kualiConfigurationService;

    @Mock
    private BudgetConstructionReportsServiceHelper budgetConstructionReportsServiceHelper;

    @InjectMocks
    private BudgetConstructionDocumentAccountObjectDetailReportServiceImpl service;

    @Test
    public void testUpdateDocumentAccountObjectDetailReportTable() {
        service.updateDocumentAccountObjectDetailReportTable("user1", "DOC1", 2024, "UA", "1234567", "-----");
        verify(budgetConstructionDocumentAccountObjectDetailReportDao)
                .updateDocumentAccountObjectDetailReportTable("user1", "DOC1", 2024, "UA", "1234567", "-----");
    }
}
