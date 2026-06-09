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
import org.kuali.kfs.integration.cg.ContractsAndGrantsUnit;
import org.kuali.kfs.module.external.kc.KcConstants;
import org.kuali.kfs.module.external.kc.businessobject.UnitDTO;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kra.external.unit.service.InstitutionalUnitService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;
import org.mockito.Mock;

class UnitServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private InstitutionalUnitService webService;
    @Mock private ConfigurationService configurationService;

    private UnitServiceImpl service;

    @BeforeEach
    void setUp() {
        service = spy(new UnitServiceImpl());
        service.configurationService = configurationService;
        doReturn(webService).when(service).getWebService();
    }

    @Test
    void testFindByPrimaryKey() {
        Map<String, Object> keys = new HashMap<String, Object>();
        keys.put("unitNumber", "000001");

        UnitDTO unit = new UnitDTO();
        when(webService.getUnit("000001")).thenReturn(unit);

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertSame(unit, result);
    }

    @Test
    void testFindMatching_withAllowableCriteria() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        for (String key : KcConstants.Unit.KC_ALLOWABLE_CRITERIA_PARAMETERS) {
            fieldValues.put(key, "testValue");
        }

        when(webService.lookupUnits(any(List.class))).thenReturn(new ArrayList());

        Collection result = service.findMatching(fieldValues);
        assertNotNull(result);
    }

    @Test
    void testFindMatching_emptyValueSkipped() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        for (String key : KcConstants.Unit.KC_ALLOWABLE_CRITERIA_PARAMETERS) {
            fieldValues.put(key, "");
        }

        when(webService.lookupUnits(any(List.class))).thenReturn(new ArrayList());

        Collection result = service.findMatching(fieldValues);
        assertNotNull(result);
    }

    @Test
    void testFindMatching_webServiceException() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        when(webService.lookupUnits(any(List.class))).thenThrow(new WebServiceException("down"));
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://localhost");

        Collection result = service.findMatching(fieldValues);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_nullResult() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        when(webService.lookupUnits(any(List.class))).thenReturn(null);

        Collection result = service.findMatching(fieldValues);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetSearchResults_delegatesToFindMatching() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        when(webService.lookupUnits(any(List.class))).thenReturn(new ArrayList());

        List<? extends BusinessObject> result = service.getSearchResults(fieldValues);
        assertNotNull(result);
    }
}
