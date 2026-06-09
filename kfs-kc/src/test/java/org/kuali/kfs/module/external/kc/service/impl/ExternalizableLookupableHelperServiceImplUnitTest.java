package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.external.kc.service.ExternalizableLookupableBusinessObjectService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;
import org.kuali.rice.krad.bo.BusinessObject;

class ExternalizableLookupableHelperServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private ExternalizableLookupableBusinessObjectService eboLookupableService;

    private ExternalizableLookupableHelperServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ExternalizableLookupableHelperServiceImpl();
        service.setEboLookupableService(eboLookupableService);
    }

    @Test
    void testGetSearchResults_delegatesToEboLookupableService() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("key", "value");
        List expected = new ArrayList();
        when(eboLookupableService.getSearchResults(fieldValues)).thenReturn(expected);

        List<? extends BusinessObject> result = service.getSearchResults(fieldValues);
        assertSame(expected, result);
        verify(eboLookupableService).getSearchResults(fieldValues);
    }

    @Test
    void testGetSearchResults_emptyFieldValues() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        List expected = Collections.emptyList();
        when(eboLookupableService.getSearchResults(fieldValues)).thenReturn(expected);

        List<? extends BusinessObject> result = service.getSearchResults(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAndSetEboLookupableService() {
        assertSame(eboLookupableService, service.getEboLookupableService());
    }
}
