package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.module.external.kc.businessobject.AwardAccount;
import org.kuali.kfs.module.external.kc.businessobject.Award;
import org.kuali.kfs.module.external.kc.service.ExternalizableBusinessObjectService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.impl.KfsParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
class ContractsAndGrantsModuleServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;
    @Mock private ExternalizableBusinessObjectService awardAccountService;
    @Mock private PersonService personService;
    @Mock private ParameterService parameterService;
    @InjectMocks private ContractsAndGrantsModuleServiceImpl service;

    private AwardAccount createAwardAccount(String principalId, Long proposalNumber) {
        AwardAccount aa = mock(AwardAccount.class);
        when(aa.getPrincipalId()).thenReturn(principalId);
        when(aa.getProposalNumber()).thenReturn(proposalNumber);
        Award award = mock(Award.class);
        when(award.getProposalNumber()).thenReturn(proposalNumber);
        when(aa.getAward()).thenReturn(award);
        return aa;
    }

    @Test
    void testDetermineAwardAccountForProjectDirector_nullList() {
        assertNull(service.determineAwardAccountForProjectDirector(null));
    }

    @Test
    void testDetermineAwardAccountForProjectDirector_emptyList() {
        assertNull(service.determineAwardAccountForProjectDirector(new ArrayList<AwardAccount>()));
    }

    @Test
    void testDetermineAwardAccountForProjectDirector_singleWithPrincipal() {
        AwardAccount aa = createAwardAccount("USER1", 100L);
        List<AwardAccount> list = new ArrayList<>(Arrays.asList(aa));
        AwardAccount result = service.determineAwardAccountForProjectDirector(list);
        assertSame(aa, result);
    }

    @Test
    void testDetermineAwardAccountForProjectDirector_skipsBlankPrincipalId() {
        AwardAccount aa1 = createAwardAccount("", 200L);
        AwardAccount aa2 = createAwardAccount("USER2", 100L);
        List<AwardAccount> list = new ArrayList<>(Arrays.asList(aa1, aa2));
        AwardAccount result = service.determineAwardAccountForProjectDirector(list);
        assertEquals("USER2", result.getPrincipalId());
    }

    @Test
    void testDetermineAwardAccountForFederalAgency_nullList() {
        assertNull(service.determineAwardAccountForFederalAgency(null));
    }

    @Test
    void testDetermineAwardAccountForFederalAgency_emptyList() {
        assertNull(service.determineAwardAccountForFederalAgency(new ArrayList<AwardAccount>()));
    }

    @Test
    void testDetermineAwardAccountForFederalAgency_returnsFirst() {
        AwardAccount aa1 = createAwardAccount("X", 300L);
        AwardAccount aa2 = createAwardAccount("Y", 200L);
        List<AwardAccount> list = new ArrayList<>(Arrays.asList(aa1, aa2));
        AwardAccount result = service.determineAwardAccountForFederalAgency(list);
        assertNotNull(result);
    }

    @Test
    void testGetProjectDirectorForAccount_nullAccount() {
        Person result = service.getProjectDirectorForAccount((Account) null);
        assertNull(result);
    }

    @Test
    void testGetProjectDirectorForAccount_withAccount() {
        Account account = new Account();
        account.setChartOfAccountsCode("BL");
        account.setAccountNumber("1234567");

        AwardAccount aa = createAwardAccount("PDIR1", 100L);
        when(awardAccountService.findMatching(any(Map.class))).thenReturn(Arrays.asList(aa));

        Person person = mock(Person.class);
        when(personService.getPerson("PDIR1")).thenReturn(person);

        Person result = service.getProjectDirectorForAccount(account);
        assertSame(person, result);
    }

    @Test
    void testGetAllAccountReponsiblityIds() {
        when(parameterService.getParameterValueAsString(any(Class.class), anyString())).thenReturn("3");

        List<Integer> ids = service.getAllAccountReponsiblityIds();
        assertEquals(3, ids.size());
        assertEquals(Arrays.asList(1, 2, 3), ids);
    }

    @Test
    void testHasValidAccountReponsiblityIdIfNotNull_nullId() {
        Account account = new Account();
        account.setContractsAndGrantsAccountResponsibilityId(null);
        assertTrue(service.hasValidAccountReponsiblityIdIfNotNull(account));
    }

    @Test
    void testHasValidAccountReponsiblityIdIfNotNull_validId() {
        when(parameterService.getParameterValueAsString(any(Class.class), anyString())).thenReturn("5");
        Account account = new Account();
        account.setContractsAndGrantsAccountResponsibilityId(3);
        assertTrue(service.hasValidAccountReponsiblityIdIfNotNull(account));
    }

    @Test
    void testHasValidAccountReponsiblityIdIfNotNull_invalidId() {
        when(parameterService.getParameterValueAsString(any(Class.class), anyString())).thenReturn("5");
        Account account = new Account();
        account.setContractsAndGrantsAccountResponsibilityId(10);
        assertFalse(service.hasValidAccountReponsiblityIdIfNotNull(account));
    }

    @Test
    void testGetProposalNumberForAccountAndProjectDirector_noAwardAccount() {
        when(awardAccountService.findMatching(any(Map.class))).thenReturn(new ArrayList());

        String result = service.getProposalNumberForAccountAndProjectDirector("BL", "1234567", "USER1");
        assertNull(result);
    }

    @Test
    void testGetProposalNumberForAccountAndProjectDirector_matchingDirector() {
        AwardAccount aa = createAwardAccount("USER1", 100L);
        Award award = aa.getAward();
        when(award.getAwardNumber()).thenReturn("AWD-001");
        when(awardAccountService.findMatching(any(Map.class))).thenReturn(Arrays.asList(aa));

        String result = service.getProposalNumberForAccountAndProjectDirector("BL", "1234567", "USER1");
        assertEquals("AWD-001", result);
    }

    @Test
    void testGetProposalNumberForAccountAndProjectDirector_nonMatchingDirector() {
        AwardAccount aa = createAwardAccount("USER2", 100L);
        when(awardAccountService.findMatching(any(Map.class))).thenReturn(Arrays.asList(aa));

        String result = service.getProposalNumberForAccountAndProjectDirector("BL", "1234567", "USER1");
        assertNull(result);
    }
}
