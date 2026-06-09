package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.ChartService;
import org.kuali.kfs.coa.service.OrganizationService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kim.api.role.RoleService;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class FinancialSystemUserServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ChartService chartService;
    @Mock
    private OrganizationService organizationService;
    @Mock
    private RoleService roleService;

    private FinancialSystemUserServiceImpl financialSystemUserService;

    @BeforeEach
    void setUp() {
        financialSystemUserService = new FinancialSystemUserServiceImpl();
        financialSystemUserService.chartService = chartService;
        financialSystemUserService.organizationService = organizationService;
        financialSystemUserService.roleService = roleService;
    }

    @Test
    void isActiveFinancialSystemUser_blankPrincipalId_returnsFalse() {
        boolean result = financialSystemUserService.isActiveFinancialSystemUser("");
        assertThat(result).isFalse();
    }

    @Test
    void isActiveFinancialSystemUser_nullPrincipalId_returnsFalse() {
        boolean result = financialSystemUserService.isActiveFinancialSystemUser(null);
        assertThat(result).isFalse();
    }

    @SuppressWarnings("unchecked")
    @Test
    void isActiveFinancialSystemUser_validUser_delegatesToRoleService() {
        when(roleService.getRoleIdByNamespaceCodeAndName(
                KFSConstants.CoreModuleNamespaces.KFS,
                KFSConstants.SysKimApiConstants.KFS_USER_ROLE_NAME))
                .thenReturn("ROLE_123");
        when(roleService.principalHasRole(eq("user1"), any(List.class), any(Map.class)))
                .thenReturn(true);

        boolean result = financialSystemUserService.isActiveFinancialSystemUser("user1");
        assertThat(result).isTrue();
    }
}
