package org.kuali.kfs.coa.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Organization;
import org.kuali.kfs.coa.service.ChartService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class OrganizationServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService boService;
    @Mock private ParameterService parameterService;
    @Mock private ChartService chartService;

    @InjectMocks
    private OrganizationServiceImpl orgService;

    @Nested
    @DisplayName("getByPrimaryId")
    class GetByPrimaryIdTests {

        @Test
        void returnsOrgWhenFound() {
            Organization expected = new Organization();
            expected.setChartOfAccountsCode("BL");
            expected.setOrganizationCode("CHEM");
            when(boService.findByPrimaryKey(eq(Organization.class), any())).thenReturn(expected);

            Organization result = orgService.getByPrimaryId("BL", "CHEM");

            assertThat(result).isNotNull();
            assertThat(result.getOrganizationCode()).isEqualTo("CHEM");
        }

        @Test
        void returnsNullWhenNotFound() {
            when(boService.findByPrimaryKey(eq(Organization.class), any())).thenReturn(null);

            assertThat(orgService.getByPrimaryId("XX", "XXXX")).isNull();
        }
    }

    @Nested
    @DisplayName("getByPrimaryIdWithCaching")
    class CachingTests {

        @Test
        void delegatesToGetByPrimaryId() {
            Organization expected = new Organization();
            expected.setOrganizationCode("PHYS");
            when(boService.findByPrimaryKey(eq(Organization.class), any())).thenReturn(expected);

            Organization result = orgService.getByPrimaryIdWithCaching("BL", "PHYS");

            assertThat(result.getOrganizationCode()).isEqualTo("PHYS");
        }
    }

    @Nested
    @DisplayName("getActiveAccountsByOrg")
    class ActiveAccountsTests {

        @Test
        void throwsOnBlankChartCode() {
            assertThatThrownBy(() -> orgService.getActiveAccountsByOrg("", "CHEM"))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void throwsOnBlankOrgCode() {
            assertThatThrownBy(() -> orgService.getActiveAccountsByOrg("BL", ""))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void returnsActiveAccounts() {
            Account acct = new Account();
            acct.setActive(true);
            when(boService.findMatching(eq(Account.class), any()))
                .thenReturn(Collections.singletonList(acct));

            List<Account> result = orgService.getActiveAccountsByOrg("BL", "CHEM");

            assertThat(result).hasSize(1);
        }
    }

    @Nested
    @DisplayName("getActiveChildOrgs")
    class ActiveChildOrgsTests {

        @Test
        void throwsOnBlankChartCode() {
            assertThatThrownBy(() -> orgService.getActiveChildOrgs("", "ARSC"))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void throwsOnBlankOrgCode() {
            assertThatThrownBy(() -> orgService.getActiveChildOrgs("BL", ""))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void returnsChildOrgs() {
            Organization child = new Organization();
            child.setOrganizationCode("CHEM");
            when(boService.findMatching(eq(Organization.class), any()))
                .thenReturn(Collections.singletonList(child));

            List<Organization> result = orgService.getActiveChildOrgs("BL", "ARSC");

            assertThat(result).hasSize(1);
        }
    }

    @Nested
    @DisplayName("getActiveOrgsByType")
    class ActiveOrgsByTypeTests {

        @Test
        void throwsOnBlankTypeCode() {
            assertThatThrownBy(() -> orgService.getActiveOrgsByType(""))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void returnsOrgsByType() {
            Organization org = new Organization();
            org.setOrganizationTypeCode("D");
            when(boService.findMatching(eq(Organization.class), any()))
                .thenReturn(Collections.singletonList(org));

            List<Organization> result = orgService.getActiveOrgsByType("D");

            assertThat(result).hasSize(1);
        }
    }

    @Nested
    @DisplayName("isParentOrganization")
    class IsParentOrgTests {

        @Test
        void returnsFalseWhenChildChartBlank() {
            assertThat(orgService.isParentOrganization("", "CHEM", "BL", "ARSC")).isFalse();
        }

        @Test
        void returnsFalseWhenChildOrgBlank() {
            assertThat(orgService.isParentOrganization("BL", "", "BL", "ARSC")).isFalse();
        }

        @Test
        void returnsFalseWhenParentChartBlank() {
            assertThat(orgService.isParentOrganization("BL", "CHEM", "", "ARSC")).isFalse();
        }

        @Test
        void returnsFalseWhenParentOrgBlank() {
            assertThat(orgService.isParentOrganization("BL", "CHEM", "BL", "")).isFalse();
        }

        @Test
        void returnsTrueWhenSameOrg() {
            // When child and parent are the same, isParentOrganization short-circuits to true
            // but it first loads the cache via boService
            when(boService.findMatching(eq(Organization.class), any()))
                .thenReturn(Collections.emptyList());

            assertThat(orgService.isParentOrganization("BL", "CHEM", "BL", "CHEM")).isTrue();
        }
    }

    @Nested
    @DisplayName("flushParentOrgCache")
    class FlushCacheTests {

        @Test
        void doesNotThrow() {
            orgService.flushParentOrgCache();
            // No exception means success
        }
    }
}
