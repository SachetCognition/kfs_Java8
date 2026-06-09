package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.WebServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.external.kc.dto.SponsorCriteriaDto;
import org.kuali.kfs.module.external.kc.dto.SponsorDTO;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kra.external.sponsor.SponsorWebService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
class SponsorServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private SponsorWebService webService;
    @Mock private ConfigurationService configurationService;

    private SponsorServiceImpl service;

    @BeforeEach
    void setUp() {
        service = spy(new SponsorServiceImpl());
        service.configurationService = configurationService;
        doReturn(webService).when(service).getWebService();
    }

    @Test
    void testFindMatching_webServiceException() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        when(webService.getMatchingSponsors(any(SponsorCriteriaDto.class))).thenThrow(new WebServiceException("down"));
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://localhost");

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_nullResult() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        when(webService.getMatchingSponsors(any(SponsorCriteriaDto.class))).thenReturn(null);

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_emptyResult() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        when(webService.getMatchingSponsors(any(SponsorCriteriaDto.class))).thenReturn(new ArrayList<SponsorDTO>());

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetSearchResults_emptyResult() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        when(webService.getMatchingSponsors(any(SponsorCriteriaDto.class))).thenReturn(new ArrayList<SponsorDTO>());

        List<? extends BusinessObject> result = service.getSearchResults(fieldValues);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetSearchResults_webServiceException() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        when(webService.getMatchingSponsors(any(SponsorCriteriaDto.class))).thenThrow(new WebServiceException("down"));
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://localhost");

        List<? extends BusinessObject> result = service.getSearchResults(fieldValues);
        assertTrue(result.isEmpty());
    }
}
