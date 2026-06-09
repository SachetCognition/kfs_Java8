package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Organization;
import org.kuali.kfs.coa.service.OrganizationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kim.api.identity.Person;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionProcessorServiceImplTest extends KfsUnitTestBase {

    @Mock
    private OrganizationService organizationService;

    @InjectMocks
    private BudgetConstructionProcessorServiceImpl service;

    @Test
    public void testIsOrgProcessor_organizationOverload_exceptionHandling() {
        Organization organization = mock(Organization.class);
        Person person = mock(Person.class);
        when(organization.getChartOfAccountsCode()).thenThrow(new RuntimeException("Test exception"));

        boolean result = service.isOrgProcessor(organization, person);
        assertFalse(result);
    }

    @Test
    public void testSetOrganizationService() {
        service.setOrganizationService(organizationService);
        assertNotNull(service);
    }
}
