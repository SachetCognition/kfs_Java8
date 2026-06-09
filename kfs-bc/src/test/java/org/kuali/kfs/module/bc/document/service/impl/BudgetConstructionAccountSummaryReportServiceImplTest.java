package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionAccountSummaryReportDao;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionReportsServiceHelper;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionAccountSummaryReportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionAccountSummaryReportDao budgetConstructionAccountSummaryReportDao;

    @Mock
    private ConfigurationService kualiConfigurationService;

    @Mock
    private BudgetConstructionReportsServiceHelper budgetConstructionReportsServiceHelper;

    @InjectMocks
    private BudgetConstructionAccountSummaryReportServiceImpl service;

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    public void testSetters() {
        service.setBudgetConstructionAccountSummaryReportDao(budgetConstructionAccountSummaryReportDao);
        service.setConfigurationService(kualiConfigurationService);
        service.setBudgetConstructionReportsServiceHelper(budgetConstructionReportsServiceHelper);
        assertNotNull(service);
    }
}
