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
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.integration.cg.dto.HashMapElement;
import org.kuali.kfs.integration.cg.dto.KcObjectCode;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

class KcObjectCodeServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;
    @Mock private ObjectCodeService objectCodeService;
    @InjectMocks private KcObjectCodeServiceImpl service;

    private ObjectCode createObjectCode(String code, String name) {
        ObjectCode oc = new ObjectCode();
        oc.setFinancialObjectCode(code);
        oc.setFinancialObjectCodeName(name);
        return oc;
    }

    @Test
    void testLookupObjectCodes_nullCriteria() {
        ObjectCode oc = createObjectCode("1234", "Test Object");
        when(businessObjectService.findAll(ObjectCode.class)).thenReturn(Arrays.asList(oc));

        List<KcObjectCode> result = service.lookupObjectCodes(null);
        assertEquals(1, result.size());
        assertEquals("1234", result.get(0).getObjectCodeName());
        assertEquals("Test Object", result.get(0).getDescription());
    }

    @Test
    void testLookupObjectCodes_emptyCriteria() {
        ObjectCode oc = createObjectCode("5678", "Another Object");
        when(businessObjectService.findAll(ObjectCode.class)).thenReturn(Arrays.asList(oc));

        List<KcObjectCode> result = service.lookupObjectCodes(new ArrayList<HashMapElement>());
        assertEquals(1, result.size());
        assertEquals("5678", result.get(0).getObjectCodeName());
    }

    @Test
    void testLookupObjectCodes_withCriteria() {
        HashMapElement element = new HashMapElement();
        element.setKey("chartOfAccountsCode");
        element.setValue("BL");
        List<HashMapElement> criteria = Arrays.asList(element);

        ObjectCode oc = createObjectCode("4321", "Filtered Object");
        when(businessObjectService.findMatching(eq(ObjectCode.class), any(Map.class))).thenReturn((Collection) Arrays.asList(oc));

        List<KcObjectCode> result = service.lookupObjectCodes(criteria);
        assertEquals(1, result.size());
        assertEquals("4321", result.get(0).getObjectCodeName());
    }

    @Test
    void testGetObjectCode() {
        ObjectCode oc = createObjectCode("9999", "FY Object");
        when(objectCodeService.getByPrimaryId(2024, "BL", "9999")).thenReturn(oc);

        KcObjectCode result = service.getObjectCode("2024", "BL", "9999");
        assertNotNull(result);
        assertEquals("9999", result.getObjectCodeName());
        assertEquals("FY Object", result.getDescription());
    }

    @Test
    void testLookupObjectCodes_noResults() {
        when(businessObjectService.findAll(ObjectCode.class)).thenReturn((Collection) Collections.emptyList());

        List<KcObjectCode> result = service.lookupObjectCodes(null);
        assertTrue(result.isEmpty());
    }
}
