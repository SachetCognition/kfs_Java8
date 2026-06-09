package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionReportsServiceHelper;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionAccountMonthlyDetailReportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ConfigurationService kualiConfigurationService;

    @Mock
    private BudgetConstructionReportsServiceHelper budgetConstructionReportsServiceHelper;

    @InjectMocks
    private BudgetConstructionAccountMonthlyDetailReportServiceImpl service;

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    public void testSetConfigurationService() {
        service.setConfigurationService(kualiConfigurationService);
        assertNotNull(service);
    }

    @Test
    public void testSetBudgetConstructionReportsServiceHelper() {
        service.setBudgetConstructionReportsServiceHelper(budgetConstructionReportsServiceHelper);
        assertNotNull(service);
    }
}
