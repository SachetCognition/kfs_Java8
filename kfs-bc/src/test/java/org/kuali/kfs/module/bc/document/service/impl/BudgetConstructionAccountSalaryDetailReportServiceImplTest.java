package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionReportsServiceHelper;
import org.kuali.kfs.module.bc.document.service.SalarySettingService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionAccountSalaryDetailReportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ConfigurationService kualiConfigurationService;

    @Mock
    private BudgetConstructionReportsServiceHelper budgetConstructionReportsServiceHelper;

    @Mock
    private SalarySettingService salarySettingService;

    @InjectMocks
    private BudgetConstructionAccountSalaryDetailReportServiceImpl service;

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    public void testSetters() {
        service.setConfigurationService(kualiConfigurationService);
        service.setBudgetConstructionReportsServiceHelper(budgetConstructionReportsServiceHelper);
        service.setSalarySettingService(salarySettingService);
        assertNotNull(service);
    }
}
