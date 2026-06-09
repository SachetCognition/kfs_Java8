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
import org.kuali.kfs.module.external.kc.KcConstants;
import org.kuali.kfs.module.external.kc.businessobject.CfdaDTO;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kra.external.Cfda.service.CfdaNumberService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;
import org.mockito.Mock;

class CfdaServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private CfdaNumberService webService;
    @Mock private ConfigurationService configurationService;

    private CfdaServiceImpl service;

    @BeforeEach
    void setUp() {
        service = spy(new CfdaServiceImpl());
        service.configurationService = configurationService;
        doReturn(webService).when(service).getWebService();
    }

    @Test
    void testFindMatching_withAllowableCriteria() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        for (String key : KcConstants.Cfda.KC_ALLOWABLE_CRITERIA_PARAMETERS) {
            fieldValues.put(key, "testValue");
            break;
        }

        CfdaDTO dto = new CfdaDTO();
        when(webService.lookupCfda(any(List.class))).thenReturn(Arrays.asList(dto));

        Collection result = service.findMatching(fieldValues);
        assertEquals(1, result.size());
    }

    @Test
    void testFindMatching_webServiceException() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        when(webService.lookupCfda(any(List.class))).thenThrow(new WebServiceException("down"));
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://localhost");

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_nullResult() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        when(webService.lookupCfda(any(List.class))).thenReturn(null);

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindByPrimaryKey_noResults() {
        Map<String, Object> keys = new HashMap<String, Object>();
        when(webService.lookupCfda(any(List.class))).thenReturn(new ArrayList<CfdaDTO>());

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertNull(result);
    }

    @Test
    void testGetSearchResults_delegatesToFindMatching() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        when(webService.lookupCfda(any(List.class))).thenReturn(new ArrayList<CfdaDTO>());

        List<? extends BusinessObject> result = service.getSearchResults(fieldValues);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
