package org.kuali.kfs.module.bc.document.service.impl;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.OrganizationSalarySettingSearchDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class OrganizationSalarySettingSearchServiceImplTest extends KfsUnitTestBase {

    @Mock
    private OrganizationSalarySettingSearchDao organizationSalarySettingSearchDao;

    @InjectMocks
    private OrganizationSalarySettingSearchServiceImpl service;

    @Test
    public void testBuildIntendedIncumbentSelect() {
        service.buildIntendedIncumbentSelect("user1", 2024);
        verify(organizationSalarySettingSearchDao).buildIntendedIncumbentSelect("user1", 2024);
    }

    @Test
    public void testCleanIntendedIncumbentSelect() {
        service.cleanIntendedIncumbentSelect("user1");
        verify(organizationSalarySettingSearchDao).cleanIntendedIncumbentSelect("user1");
    }

    @Test
    public void testBuildPositionSelect() {
        service.buildPositionSelect("user1", 2024);
        verify(organizationSalarySettingSearchDao).buildPositionSelect("user1", 2024);
    }

    @Test
    public void testCleanPositionSelect() {
        service.cleanPositionSelect("user1");
        verify(organizationSalarySettingSearchDao).cleanPositionSelect("user1");
    }
}
