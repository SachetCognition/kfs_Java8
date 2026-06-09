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
import org.kuali.kfs.integration.cg.dto.HashMapElement;
import org.kuali.kfs.module.external.kc.KcConstants;
import org.kuali.kfs.module.external.kc.businessobject.BudgetCategoryDTO;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kra.external.budget.service.BudgetCategoryService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;
import org.mockito.Mock;

class BudgetCategoryServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private BudgetCategoryService webService;
    @Mock private ConfigurationService configurationService;

    private BudgetCategoryServiceImpl service;

    @BeforeEach
    void setUp() {
        service = spy(new BudgetCategoryServiceImpl());
        service.configurationService = configurationService;
        doReturn(webService).when(service).getWebService();
    }

    @Test
    void testFindMatching_withAllowableCriteria() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        for (String key : KcConstants.BudgetCategory.KC_ALLOWABLE_CRITERIA_PARAMETERS) {
            fieldValues.put(key, "testValue");
            break;
        }

        when(webService.lookupBudgetCategories(any(List.class))).thenReturn(new ArrayList<BudgetCategoryDTO>());

        Collection result = service.findMatching(fieldValues);
        assertNotNull(result);
    }

    @Test
    void testFindMatching_emptyFieldValues() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        List<HashMapElement> nullList = null;
        when(webService.lookupBudgetCategories(nullList)).thenReturn(new ArrayList<BudgetCategoryDTO>());

        Collection result = service.findMatching(fieldValues);
        assertNotNull(result);
    }

    @Test
    void testFindMatching_webServiceException() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        List<HashMapElement> nullList = null;
        when(webService.lookupBudgetCategories(nullList)).thenThrow(new WebServiceException("down"));
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://localhost");

        Collection result = service.findMatching(fieldValues);
        assertNotNull(result);
    }

    @Test
    void testFindByPrimaryKey_noResults() {
        Map<String, Object> keys = new HashMap<String, Object>();
        List<HashMapElement> nullList = null;
        when(webService.lookupBudgetCategories(nullList)).thenReturn(new ArrayList<BudgetCategoryDTO>());

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertNull(result);
    }

    @Test
    void testFindByPrimaryKey_withResult() {
        Map<String, Object> keys = new HashMap<String, Object>();
        BudgetCategoryDTO dto = new BudgetCategoryDTO();
        List<HashMapElement> nullList = null;
        when(webService.lookupBudgetCategories(nullList)).thenReturn(Arrays.asList(dto));

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertNotNull(result);
    }
}
