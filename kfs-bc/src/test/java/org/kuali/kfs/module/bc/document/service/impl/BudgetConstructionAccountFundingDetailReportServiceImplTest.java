package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionAccountFundingDetailReportDao;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionReportsServiceHelper;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionAccountFundingDetailReportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionAccountFundingDetailReportDao budgetConstructionAccountFundingDetailReportDao;

    @Mock
    private BudgetConstructionReportsServiceHelper budgetConstructionReportsServiceHelper;

    @Mock
    private ConfigurationService kualiConfigurationService;

    @InjectMocks
    private BudgetConstructionAccountFundingDetailReportServiceImpl service;

    @Test
    public void testUpdateAccountFundingDetailTable() {
        service.updateAccountFundingDetailTable("user1");
        verify(budgetConstructionAccountFundingDetailReportDao).updateReportsAccountFundingDetailTable("user1");
    }

    @Test
    public void testSetters() {
        service.setConfigurationService(kualiConfigurationService);
        service.setBudgetConstructionAccountFundingDetailReportDao(budgetConstructionAccountFundingDetailReportDao);
        service.setBudgetConstructionReportsServiceHelper(budgetConstructionReportsServiceHelper);
        assertNotNull(service);
    }
}
