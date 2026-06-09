package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Organization;
import org.kuali.kfs.coa.service.ChartService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class OrganizationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;
    @Mock
    private ChartService chartService;
    @Mock
    private BusinessObjectService boService;

    @InjectMocks
    private OrganizationServiceImpl organizationService;

    @Test
    void getByPrimaryId_returnsOrganization() {
        Organization expected = new Organization();
        expected.setChartOfAccountsCode("BL");
        expected.setOrganizationCode("CHEM");
        when(boService.findByPrimaryKey(eq(Organization.class), any(Map.class))).thenReturn(expected);

        Organization result = organizationService.getByPrimaryId("BL", "CHEM");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryId_notFound_returnsNull() {
        when(boService.findByPrimaryKey(eq(Organization.class), any(Map.class))).thenReturn(null);

        Organization result = organizationService.getByPrimaryId("XX", "ZZ");
        assertThat(result).isNull();
    }

    @Test
    void getByPrimaryIdWithCaching_delegatesToGetByPrimaryId() {
        Organization expected = new Organization();
        when(boService.findByPrimaryKey(eq(Organization.class), any(Map.class))).thenReturn(expected);

        Organization result = organizationService.getByPrimaryIdWithCaching("BL", "CHEM");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getActiveAccountsByOrg_returnsMatchingAccounts() {
        Account account = new Account();
        List<Account> accounts = Arrays.asList(account);
        when(boService.findMatching(eq(Account.class), any(Map.class))).thenReturn(accounts);

        List<Account> result = organizationService.getActiveAccountsByOrg("BL", "CHEM");
        assertThat(result).hasSize(1);
    }

    @Test
    void getActiveAccountsByOrg_noResults_returnsEmptyList() {
        when(boService.findMatching(eq(Account.class), any(Map.class)))
                .thenReturn(Collections.<Account>emptyList());

        List<Account> result = organizationService.getActiveAccountsByOrg("XX", "ZZ");
        assertThat(result).isEmpty();
    }
}
