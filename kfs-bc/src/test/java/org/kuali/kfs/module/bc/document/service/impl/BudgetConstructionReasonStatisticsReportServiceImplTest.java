package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionReasonStatisticsReportDao;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionOrganizationReportsService;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionReportsServiceHelper;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.PersistenceService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionReasonStatisticsReportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionReasonStatisticsReportDao budgetConstructionReasonStatisticsReportDao;

    @Mock
    private BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService;

    @Mock
    private BudgetConstructionReportsServiceHelper budgetConstructionReportsServiceHelper;

    @Mock
    private ConfigurationService kualiConfigurationService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private PersistenceService persistenceServiceOjb;

    @InjectMocks
    private BudgetConstructionReasonStatisticsReportServiceImpl service;

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    public void testGetPersistenceServiceOjb() {
        assertSame(persistenceServiceOjb, service.getPersistenceServiceOjb());
    }
}
