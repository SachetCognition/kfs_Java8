package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionAccountObjectDetailReportDao;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionOrganizationReportsService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.PersistenceService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionAccountObjectDetailReportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionAccountObjectDetailReportDao budgetConstructionAccountObjectDetailReportDao;

    @Mock
    private ConfigurationService kualiConfigurationService;

    @Mock
    private BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private PersistenceService persistenceServiceOjb;

    @InjectMocks
    private BudgetConstructionAccountObjectDetailReportServiceImpl service;

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    public void testSetters() {
        service.setBudgetConstructionAccountObjectDetailReportDao(budgetConstructionAccountObjectDetailReportDao);
        service.setConfigurationService(kualiConfigurationService);
        service.setBudgetConstructionOrganizationReportsService(budgetConstructionOrganizationReportsService);
        assertNotNull(service);
    }
}
