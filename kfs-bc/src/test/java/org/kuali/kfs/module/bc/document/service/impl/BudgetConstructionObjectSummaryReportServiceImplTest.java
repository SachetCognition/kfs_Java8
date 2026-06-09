package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionObjectSummaryReportDao;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionReportsServiceHelper;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionObjectSummaryReportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionObjectSummaryReportDao budgetConstructionObjectSummaryReportDao;

    @Mock
    private ConfigurationService kualiConfigurationService;

    @Mock
    private BudgetConstructionReportsServiceHelper budgetConstructionReportsServiceHelper;

    @InjectMocks
    private BudgetConstructionObjectSummaryReportServiceImpl service;

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    public void testSetters() {
        service.setBudgetConstructionObjectSummaryReportDao(budgetConstructionObjectSummaryReportDao);
        service.setConfigurationService(kualiConfigurationService);
        service.setBudgetConstructionReportsServiceHelper(budgetConstructionReportsServiceHelper);
        assertNotNull(service);
    }
}
