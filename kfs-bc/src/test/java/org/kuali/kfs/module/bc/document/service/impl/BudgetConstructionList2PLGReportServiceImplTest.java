package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.service.BudgetConstructionOrganizationReportsService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.PersistenceService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionList2PLGReportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService;

    @Mock
    private ConfigurationService kualiConfigurationService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private PersistenceService persistenceServiceOjb;

    @InjectMocks
    private BudgetConstructionList2PLGReportServiceImpl service;

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    public void testSetters() {
        service.setBudgetConstructionOrganizationReportsService(budgetConstructionOrganizationReportsService);
        service.setConfigurationService(kualiConfigurationService);
        service.setBusinessObjectService(businessObjectService);
        service.setPersistenceServiceOjb(persistenceServiceOjb);
        assertNotNull(service);
    }

    @Test
    public void testGetPersistenceServiceOjb() {
        assertEquals(persistenceServiceOjb, service.getPersistenceServiceOjb());
    }
}
