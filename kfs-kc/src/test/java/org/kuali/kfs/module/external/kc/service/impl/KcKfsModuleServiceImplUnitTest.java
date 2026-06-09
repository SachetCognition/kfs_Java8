package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.datadictionary.BusinessObjectEntry;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.mockito.Mock;

class KcKfsModuleServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private DataDictionaryService dataDictionaryService;
    @Mock private ConfigurationService configurationService;
    @Mock private DataDictionary dataDictionary;

    private KcKfsModuleServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new KcKfsModuleServiceImpl();
        service.setDataDictionaryService(dataDictionaryService);
        service.setConfigurationService(configurationService);
    }

    @Test
    void testGetInquiryUrl_withTrailingSlash() {
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://kc.example.com/");
        String url = service.getInquiryUrl(Object.class);
        assertTrue(url.startsWith("http://kc.example.com/"));
        assertTrue(url.contains("kr/"));
        assertTrue(url.endsWith("inquiry.do"));
    }

    @Test
    void testGetInquiryUrl_withoutTrailingSlash() {
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://kc.example.com");
        String url = service.getInquiryUrl(Object.class);
        assertTrue(url.contains("http://kc.example.com/kr/"));
    }

    @Test
    void testListPrimaryKeyFieldNames_noEntry() {
        when(dataDictionaryService.getDataDictionary()).thenReturn(dataDictionary);
        when(dataDictionary.getBusinessObjectEntry(anyString())).thenReturn(null);

        KcFinancialSystemModuleConfiguration config = new KcFinancialSystemModuleConfiguration();
        Map<Class, Class> map = new HashMap<Class, Class>();
        map.put(String.class, String.class);
        config.setExternalizableBusinessObjectImplementations(map);
        service.setModuleConfiguration(config);

        List result = service.listPrimaryKeyFieldNames(String.class);
        assertNull(result);
    }

    @Test
    void testListPrimaryKeyFieldNames_withEntry() {
        BusinessObjectEntry entry = mock(BusinessObjectEntry.class);
        when(entry.getPrimaryKeys()).thenReturn(Arrays.asList("id", "code"));
        when(dataDictionaryService.getDataDictionary()).thenReturn(dataDictionary);
        when(dataDictionary.getBusinessObjectEntry(anyString())).thenReturn(entry);

        KcFinancialSystemModuleConfiguration config = new KcFinancialSystemModuleConfiguration();
        Map<Class, Class> map = new HashMap<Class, Class>();
        map.put(String.class, String.class);
        config.setExternalizableBusinessObjectImplementations(map);
        service.setModuleConfiguration(config);

        List result = service.listPrimaryKeyFieldNames(String.class);
        assertEquals(2, result.size());
        assertTrue(result.contains("id"));
    }

    @Test
    void testGetUrlParameters() {
        KcFinancialSystemModuleConfiguration config = new KcFinancialSystemModuleConfiguration();
        Map<String, String> paramMapping = new HashMap<>();
        paramMapping.put("TestClass.kfsParam", "kcParam");
        config.setKfsToKcInquiryUrlParameterMapping(paramMapping);

        Map<String, String> classMapping = new HashMap<>();
        classMapping.put("TestClass", "KcTestClass");
        config.setKfsToKcInquiryUrlClassMapping(classMapping);

        service.setModuleConfiguration(config);

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put("kfsParam", new String[]{"value1"});

        Properties props = service.getUrlParameters("TestClass", parameters);
        assertEquals("value1", props.getProperty("kcParam"));
        assertEquals("KcTestClass", props.getProperty("businessObjectClassName"));
    }
}
