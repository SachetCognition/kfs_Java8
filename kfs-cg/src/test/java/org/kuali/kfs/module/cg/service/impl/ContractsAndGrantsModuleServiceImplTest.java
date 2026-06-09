/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 *
 * Copyright 2005-2014 The Kuali Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.module.cg.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.module.cg.CGConstants;
import org.kuali.kfs.module.cg.businessobject.Agency;
import org.kuali.kfs.module.cg.businessobject.Award;
import org.kuali.kfs.module.cg.businessobject.AwardAccount;
import org.kuali.kfs.module.cg.service.AgencyService;
import org.kuali.kfs.module.cg.service.AwardService;
import org.kuali.kfs.module.cg.service.CfdaService;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.impl.KfsParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ContractsAndGrantsModuleServiceImplTest extends KfsUnitTestBase {

    @Mock
    private AwardService awardService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private AgencyService agencyService;

    @Mock
    private CfdaService cfdaService;

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private ContractsAndGrantsModuleServiceImpl service;

    @Test
    public void testGetProjectDirectorForAccount_found() {
        Person mockPerson = org.mockito.Mockito.mock(Person.class);
        AwardAccount awardAccount = createAwardAccountWithDirector(100L, mockPerson);

        Collection<AwardAccount> proposals = new ArrayList<>();
        proposals.add(awardAccount);
        when(businessObjectService.findMatchingOrderBy(
                eq(AwardAccount.class), any(Map.class), eq(KFSPropertyConstants.PROPOSAL_NUMBER), eq(false)))
                .thenReturn(proposals);

        Person result = service.getProjectDirectorForAccount("BL", "1234567");

        assertNotNull(result);
        assertEquals(mockPerson, result);
    }

    @Test
    public void testGetProjectDirectorForAccount_notFound() {
        when(businessObjectService.findMatchingOrderBy(
                eq(AwardAccount.class), any(Map.class), eq(KFSPropertyConstants.PROPOSAL_NUMBER), eq(false)))
                .thenReturn(Collections.emptyList());

        Person result = service.getProjectDirectorForAccount("BL", "9999999");

        assertNull(result);
    }

    @Test
    public void testGetProjectDirectorForAccount_nullCollection() {
        when(businessObjectService.findMatchingOrderBy(
                eq(AwardAccount.class), any(Map.class), eq(KFSPropertyConstants.PROPOSAL_NUMBER), eq(false)))
                .thenReturn(null);

        Person result = service.getProjectDirectorForAccount("BL", "9999999");

        assertNull(result);
    }

    @Test
    public void testGetProjectDirectorForAccount_withAccountObject_null() {
        Person result = service.getProjectDirectorForAccount((Account) null);

        assertNull(result);
    }

    @Test
    public void testGetParentUnits() {
        List<String> result = service.getParentUnits("UNIT1");

        assertNull(result);
    }

    @Test
    public void testGetAllAccountResponsibilityIds() {
        when(parameterService.getParameterValueAsString(
                KfsParameterConstants.CHART_ALL.class, CGConstants.MAXIMUM_ACCOUNT_RESPONSIBILITY_ID))
                .thenReturn("5");

        List<Integer> result = service.getAllAccountReponsiblityIds();

        assertNotNull(result);
        assertEquals(5, result.size());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testGetAllAccountResponsibilityIds_singleId() {
        when(parameterService.getParameterValueAsString(
                KfsParameterConstants.CHART_ALL.class, CGConstants.MAXIMUM_ACCOUNT_RESPONSIBILITY_ID))
                .thenReturn("1");

        List<Integer> result = service.getAllAccountReponsiblityIds();

        assertEquals(1, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
    }

    @Test
    public void testHasValidAccountResponsibilityIdIfNotNull_null() {
        Account account = new Account();
        account.setContractsAndGrantsAccountResponsibilityId(null);

        boolean result = service.hasValidAccountReponsiblityIdIfNotNull(account);

        assertTrue(result);
    }

    @Test
    public void testHasValidAccountResponsibilityIdIfNotNull_validId() {
        Account account = new Account();
        account.setContractsAndGrantsAccountResponsibilityId(3);

        when(parameterService.getParameterValueAsString(
                KfsParameterConstants.CHART_ALL.class, CGConstants.MAXIMUM_ACCOUNT_RESPONSIBILITY_ID))
                .thenReturn("5");

        boolean result = service.hasValidAccountReponsiblityIdIfNotNull(account);

        assertTrue(result);
    }

    @Test
    public void testHasValidAccountResponsibilityIdIfNotNull_minBoundary() {
        Account account = new Account();
        account.setContractsAndGrantsAccountResponsibilityId(1);

        when(parameterService.getParameterValueAsString(
                KfsParameterConstants.CHART_ALL.class, CGConstants.MAXIMUM_ACCOUNT_RESPONSIBILITY_ID))
                .thenReturn("5");

        boolean result = service.hasValidAccountReponsiblityIdIfNotNull(account);

        assertTrue(result);
    }

    @Test
    public void testHasValidAccountResponsibilityIdIfNotNull_maxBoundary() {
        Account account = new Account();
        account.setContractsAndGrantsAccountResponsibilityId(5);

        when(parameterService.getParameterValueAsString(
                KfsParameterConstants.CHART_ALL.class, CGConstants.MAXIMUM_ACCOUNT_RESPONSIBILITY_ID))
                .thenReturn("5");

        boolean result = service.hasValidAccountReponsiblityIdIfNotNull(account);

        assertTrue(result);
    }

    @Test
    public void testHasValidAccountResponsibilityIdIfNotNull_tooLow() {
        Account account = new Account();
        account.setContractsAndGrantsAccountResponsibilityId(0);

        boolean result = service.hasValidAccountReponsiblityIdIfNotNull(account);

        assertFalse(result);
    }

    @Test
    public void testHasValidAccountResponsibilityIdIfNotNull_tooHigh() {
        Account account = new Account();
        account.setContractsAndGrantsAccountResponsibilityId(6);

        when(parameterService.getParameterValueAsString(
                KfsParameterConstants.CHART_ALL.class, CGConstants.MAXIMUM_ACCOUNT_RESPONSIBILITY_ID))
                .thenReturn("5");

        boolean result = service.hasValidAccountReponsiblityIdIfNotNull(account);

        assertFalse(result);
    }

    @Test
    public void testIsAwardedByFederalAgency_federalAgencyType() {
        Agency agency = new Agency();
        agency.setAgencyTypeCode("F");

        Award award = new Award();
        award.setAgency(agency);
        award.setFederalPassThroughIndicator(false);

        AwardAccount awardAccount = new AwardAccount();
        awardAccount.setProposalNumber(100L);
        awardAccount.setAward(award);

        Collection<AwardAccount> awardAccounts = new ArrayList<>();
        awardAccounts.add(awardAccount);
        when(businessObjectService.findMatching(eq(AwardAccount.class), any(Map.class))).thenReturn(awardAccounts);

        Collection<String> federalTypeCodes = Arrays.asList("F", "G");
        boolean result = service.isAwardedByFederalAgency("BL", "1234567", federalTypeCodes);

        assertTrue(result);
    }

    @Test
    public void testIsAwardedByFederalAgency_federalPassThrough() {
        Agency agency = new Agency();
        agency.setAgencyTypeCode("S");

        Award award = new Award();
        award.setAgency(agency);
        award.setFederalPassThroughIndicator(true);

        AwardAccount awardAccount = new AwardAccount();
        awardAccount.setProposalNumber(100L);
        awardAccount.setAward(award);

        Collection<AwardAccount> awardAccounts = new ArrayList<>();
        awardAccounts.add(awardAccount);
        when(businessObjectService.findMatching(eq(AwardAccount.class), any(Map.class))).thenReturn(awardAccounts);

        Collection<String> federalTypeCodes = Arrays.asList("F", "G");
        boolean result = service.isAwardedByFederalAgency("BL", "1234567", federalTypeCodes);

        assertTrue(result);
    }

    @Test
    public void testIsAwardedByFederalAgency_nonFederal() {
        Agency agency = new Agency();
        agency.setAgencyTypeCode("S");

        Award award = new Award();
        award.setAgency(agency);
        award.setFederalPassThroughIndicator(false);

        AwardAccount awardAccount = new AwardAccount();
        awardAccount.setProposalNumber(100L);
        awardAccount.setAward(award);

        Collection<AwardAccount> awardAccounts = new ArrayList<>();
        awardAccounts.add(awardAccount);
        when(businessObjectService.findMatching(eq(AwardAccount.class), any(Map.class))).thenReturn(awardAccounts);

        Collection<String> federalTypeCodes = Arrays.asList("F", "G");
        boolean result = service.isAwardedByFederalAgency("BL", "1234567", federalTypeCodes);

        assertFalse(result);
    }

    @Test
    public void testIsAwardedByFederalAgency_noAwardAccounts() {
        when(businessObjectService.findMatching(eq(AwardAccount.class), any(Map.class)))
                .thenReturn(Collections.emptyList());

        Collection<String> federalTypeCodes = Arrays.asList("F", "G");
        boolean result = service.isAwardedByFederalAgency("BL", "9999999", federalTypeCodes);

        assertFalse(result);
    }

    @Test
    public void testGetProposalNumberForAccountAndProjectDirector_matchingDirector() {
        Person mockPerson = org.mockito.Mockito.mock(Person.class);
        when(mockPerson.getPrincipalId()).thenReturn("DIR001");

        AwardAccount awardAccount = createAwardAccountWithDirector(100L, mockPerson);

        Collection<AwardAccount> proposals = new ArrayList<>();
        proposals.add(awardAccount);
        when(businessObjectService.findMatchingOrderBy(
                eq(AwardAccount.class), any(Map.class), eq(KFSPropertyConstants.PROPOSAL_NUMBER), eq(false)))
                .thenReturn(proposals);

        String result = service.getProposalNumberForAccountAndProjectDirector("BL", "1234567", "DIR001");

        assertEquals("100", result);
    }

    @Test
    public void testGetProposalNumberForAccountAndProjectDirector_nonMatchingDirector() {
        Person mockPerson = org.mockito.Mockito.mock(Person.class);
        when(mockPerson.getPrincipalId()).thenReturn("DIR001");

        AwardAccount awardAccount = createAwardAccountWithDirector(100L, mockPerson);

        Collection<AwardAccount> proposals = new ArrayList<>();
        proposals.add(awardAccount);
        when(businessObjectService.findMatchingOrderBy(
                eq(AwardAccount.class), any(Map.class), eq(KFSPropertyConstants.PROPOSAL_NUMBER), eq(false)))
                .thenReturn(proposals);

        String result = service.getProposalNumberForAccountAndProjectDirector("BL", "1234567", "DIR999");

        assertNull(result);
    }

    @Test
    public void testGetProposalNumberForAccountAndProjectDirector_noProposals() {
        when(businessObjectService.findMatchingOrderBy(
                eq(AwardAccount.class), any(Map.class), eq(KFSPropertyConstants.PROPOSAL_NUMBER), eq(false)))
                .thenReturn(Collections.emptyList());

        String result = service.getProposalNumberForAccountAndProjectDirector("BL", "1234567", "DIR001");

        assertNull(result);
    }

    private AwardAccount createAwardAccountWithDirector(final Long proposalNumber, final Person director) {
        AwardAccount awardAccount = new AwardAccount() {
            private Person projectDirector = director;
            private Long proposalNum = proposalNumber;

            @Override
            public Person getProjectDirector() {
                return projectDirector;
            }

            @Override
            public Long getProposalNumber() {
                return proposalNum;
            }
        };
        return awardAccount;
    }
}
