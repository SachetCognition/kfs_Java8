package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.WebServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.external.kc.businessobject.Award;
import org.kuali.kfs.module.external.kc.dto.AwardDTO;
import org.kuali.kfs.module.external.kc.dto.AwardFieldValuesDto;
import org.kuali.kfs.module.external.kc.dto.AwardSearchCriteriaDto;
import org.kuali.kfs.module.external.kc.dto.SponsorDTO;
import org.kuali.kfs.module.external.kc.service.AccountDefaultsService;
import org.kuali.kfs.module.external.kc.service.BillingFrequencyService;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kra.external.award.AwardWebService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
class AwardServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private AwardWebService webService;
    @Mock private AccountDefaultsService accountDefaultsService;
    @Mock private BillingFrequencyService billingFrequencyService;
    @Mock private ConfigurationService configurationService;
    @Mock private ParameterService parameterService;
    @Mock private PersonService personService;

    private AwardServiceImpl service;

    @BeforeEach
    void setUp() {
        service = spy(new AwardServiceImpl());
        service.accountDefaultsService = accountDefaultsService;
        service.billingFrequencyService = billingFrequencyService;
        service.configurationService = configurationService;
        service.parameterService = parameterService;
        service.personService = personService;
        doReturn(webService).when(service).getWebService();
    }

    private AwardDTO createMinimalDTO(long id, String number) {
        AwardDTO dto = new AwardDTO();
        dto.setAwardId(id);
        dto.setAwardNumber(number);
        dto.setSponsor(new SponsorDTO());
        return dto;
    }

    @Test
    void testFindByPrimaryKey() {
        Map<String, Object> keys = new HashMap<String, Object>();
        keys.put("proposalNumber", 123L);

        AwardDTO dto = createMinimalDTO(123L, "AWD-001");
        when(webService.getAward(123L)).thenReturn(dto);

        Award stubAward = new Award();
        doReturn(stubAward).when(service).awardFromDTO(dto);

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertSame(stubAward, result);
    }

    @Test
    void testFindByPrimaryKey_nullDTO() {
        final Map<String, Object> keys = new HashMap<String, Object>();
        keys.put("proposalNumber", 999L);

        when(webService.getAward(999L)).thenReturn(null);

        assertThrows(NullPointerException.class, new org.junit.jupiter.api.function.Executable() {
            public void execute() {
                service.findByPrimaryKey(keys);
            }
        });
    }

    @Test
    void testFindMatching_withStringProposalNumber() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(KFSPropertyConstants.PROPOSAL_NUMBER, "456");

        when(webService.getMatchingAwards(any(AwardFieldValuesDto.class))).thenReturn(new ArrayList<AwardDTO>());

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_withLongProposalNumber() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(KFSPropertyConstants.PROPOSAL_NUMBER, 456L);

        AwardDTO dto = createMinimalDTO(456L, "AWD-002");
        when(webService.getMatchingAwards(any(AwardFieldValuesDto.class))).thenReturn(Arrays.asList(dto));

        Award stubAward = new Award();
        doReturn(stubAward).when(service).awardFromDTO(any(AwardDTO.class));

        Collection result = service.findMatching(fieldValues);
        assertEquals(1, result.size());
    }

    @Test
    void testFindMatching_webServiceException() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        when(webService.getMatchingAwards(any(AwardFieldValuesDto.class))).thenThrow(new WebServiceException("down"));
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://localhost");

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_nullResult() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        when(webService.getMatchingAwards(any(AwardFieldValuesDto.class))).thenReturn(null);

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetSearchResults_emptyResult() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        when(webService.searchAwards(any(AwardSearchCriteriaDto.class))).thenReturn(null);

        List<? extends BusinessObject> result = service.getSearchResults(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetSearchResults_withResults() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        AwardDTO dto = createMinimalDTO(789L, "AWD-003");
        when(webService.searchAwards(any(AwardSearchCriteriaDto.class))).thenReturn(Arrays.asList(dto));

        Award stubAward = new Award();
        doReturn(stubAward).when(service).awardFromDTO(any(AwardDTO.class));

        List<? extends BusinessObject> result = service.getSearchResults(fieldValues);
        assertEquals(1, result.size());
    }

    @Test
    void testGetSearchResults_webServiceException() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        when(webService.searchAwards(any(AwardSearchCriteriaDto.class))).thenThrow(new WebServiceException("error"));
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://localhost");

        List<? extends BusinessObject> result = service.getSearchResults(fieldValues);
        assertTrue(result.isEmpty());
    }
}
