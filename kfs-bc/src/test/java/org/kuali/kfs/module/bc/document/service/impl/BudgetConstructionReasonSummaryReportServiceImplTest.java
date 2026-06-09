package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionSalarySummaryReportDao;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionOrganizationReportsService;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionReportsServiceHelper;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionReasonSummaryReportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionSalarySummaryReportDao budgetConstructionSalarySummaryReportDao;

    @Mock
    private BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService;

    @Mock
    private BudgetConstructionReportsServiceHelper budgetConstructionReportsServiceHelper;

    @Mock
    private ConfigurationService kualiConfigurationService;

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private BudgetConstructionReasonSummaryReportServiceImpl service;

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    public void testSetters() {
        service.setBudgetConstructionSalarySummaryReportDao(budgetConstructionSalarySummaryReportDao);
        service.setBudgetConstructionOrganizationReportsService(budgetConstructionOrganizationReportsService);
        service.setConfigurationService(kualiConfigurationService);
        service.setBusinessObjectService(businessObjectService);
        service.setBudgetConstructionReportsServiceHelper(budgetConstructionReportsServiceHelper);
        assertNotNull(service);
    }
}
