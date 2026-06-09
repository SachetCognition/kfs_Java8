package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.cg.ContractsAndGrantsBillingAward;
import org.kuali.kfs.module.external.kc.businessobject.Award;
import org.kuali.kfs.module.external.kc.dto.AwardFieldValuesDto;
import org.kuali.kfs.module.external.kc.service.ExternalizableLookupableBusinessObjectService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ContractsAndGrantsModuleBillingServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private ExternalizableLookupableBusinessObjectService awardService;
    @InjectMocks private ContractsAndGrantsModuleBillingServiceImpl service;

    @Test
    void testLookupAwards_delegatesToAwardService() {
        Map<String, String> fieldValues = new HashMap<>();
        List awards = new ArrayList();
        when(awardService.getSearchResults(fieldValues)).thenReturn(awards);

        List<?> result = service.lookupAwards(fieldValues, false);
        assertSame(awards, result);
    }

    @Test
    void testUpdateAwardIfNecessary_nullProposalNumber() {
        ContractsAndGrantsBillingAward currentAward = mock(ContractsAndGrantsBillingAward.class);
        ContractsAndGrantsBillingAward result = service.updateAwardIfNecessary(null, currentAward);
        assertNull(result);
    }

    @Test
    void testUpdateAwardIfNecessary_currentAwardMatches() {
        ContractsAndGrantsBillingAward currentAward = mock(ContractsAndGrantsBillingAward.class);
        when(currentAward.getProposalNumber()).thenReturn(123L);

        ContractsAndGrantsBillingAward result = service.updateAwardIfNecessary(123L, currentAward);
        assertSame(currentAward, result);
        verifyNoInteractions(awardService);
    }

    @Test
    void testUpdateAwardIfNecessary_currentAwardDifferent() {
        ContractsAndGrantsBillingAward currentAward = mock(ContractsAndGrantsBillingAward.class);
        when(currentAward.getProposalNumber()).thenReturn(456L);

        Award freshAward = mock(Award.class);
        when(awardService.findByPrimaryKey(any(Map.class))).thenReturn(freshAward);

        ContractsAndGrantsBillingAward result = service.updateAwardIfNecessary(123L, currentAward);
        assertSame(freshAward, result);
    }

    @Test
    void testUpdateAwardIfNecessary_currentAwardNull() {
        Award freshAward = mock(Award.class);
        when(awardService.findByPrimaryKey(any(Map.class))).thenReturn(freshAward);

        ContractsAndGrantsBillingAward result = service.updateAwardIfNecessary(123L, null);
        assertSame(freshAward, result);
    }

    @Test
    void testBuildSearchDto() {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("accountNumber", "1234567");
        criteria.put("chartOfAccountsCode", "BL");
        criteria.put("proposalNumber", 99L);

        AwardFieldValuesDto dto = service.buildSearchDto(criteria);
        assertEquals("1234567", dto.getAccountNumber());
        assertEquals("BL", dto.getChartOfAccounts());
        assertEquals(Long.valueOf(99), dto.getAwardId());
    }
}
