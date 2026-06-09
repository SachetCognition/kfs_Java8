package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

class KcFinancialSystemModuleConfigurationUnitTest extends KfsUnitTestBase {

    @Test
    void testDefaultConstructor() {
        KcFinancialSystemModuleConfiguration config = new KcFinancialSystemModuleConfiguration();
        assertNull(config.getExternalizableBusinessObjectServiceImplementations());
        assertNull(config.getKfsToKcInquiryUrlClassMapping());
        assertNull(config.getKfsToKcInquiryUrlParameterMapping());
    }

    @Test
    void testSetAndGetExternalizableBusinessObjectServiceImplementations() {
        KcFinancialSystemModuleConfiguration config = new KcFinancialSystemModuleConfiguration();
        Map<Class, String> impls = new HashMap<>();
        impls.put(String.class, "testService");
        config.setExternalizableBusinessObjectServiceImplementations(impls);

        Map<Class, String> result = config.getExternalizableBusinessObjectServiceImplementations();
        assertNotNull(result);
        assertEquals("testService", result.get(String.class));
    }

    @Test
    void testGetExternalizableBusinessObjectServiceImplementations_returnsUnmodifiable() {
        KcFinancialSystemModuleConfiguration config = new KcFinancialSystemModuleConfiguration();
        Map<Class, String> impls = new HashMap<>();
        impls.put(String.class, "testService");
        config.setExternalizableBusinessObjectServiceImplementations(impls);

        Map<Class, String> result = config.getExternalizableBusinessObjectServiceImplementations();
        try {
            result.put(Integer.class, "other");
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    @Test
    void testSetAndGetKfsToKcInquiryUrlClassMapping() {
        KcFinancialSystemModuleConfiguration config = new KcFinancialSystemModuleConfiguration();
        Map<String, String> mapping = new HashMap<>();
        mapping.put("kfsClass", "kcClass");
        config.setKfsToKcInquiryUrlClassMapping(mapping);

        assertSame(mapping, config.getKfsToKcInquiryUrlClassMapping());
    }

    @Test
    void testSetAndGetKfsToKcInquiryUrlParameterMapping() {
        KcFinancialSystemModuleConfiguration config = new KcFinancialSystemModuleConfiguration();
        Map<String, String> mapping = new HashMap<>();
        mapping.put("kfsParam", "kcParam");
        config.setKfsToKcInquiryUrlParameterMapping(mapping);

        assertSame(mapping, config.getKfsToKcInquiryUrlParameterMapping());
    }

    @Test
    void testGetExternalizableBusinessObjectServiceImplementations_nullReturnsNull() {
        KcFinancialSystemModuleConfiguration config = new KcFinancialSystemModuleConfiguration();
        config.setExternalizableBusinessObjectServiceImplementations(null);
        assertNull(config.getExternalizableBusinessObjectServiceImplementations());
    }
}
