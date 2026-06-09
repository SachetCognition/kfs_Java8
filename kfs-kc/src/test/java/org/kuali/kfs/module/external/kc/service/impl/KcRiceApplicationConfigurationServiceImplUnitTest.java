package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

class KcRiceApplicationConfigurationServiceImplUnitTest extends KfsUnitTestBase {

    private final KcRiceApplicationConfigurationServiceImpl service = new KcRiceApplicationConfigurationServiceImpl();

    @Test
    void testGetBaseHelpUrl_returnsNull() {
        assertNull(service.getBaseHelpUrl("org.kuali.kfs.SomeClass"));
    }

    @Test
    void testGetBaseInquiryUrl_returnsNull() {
        assertNull(service.getBaseInquiryUrl("org.kuali.kfs.SomeClass"));
    }

    @Test
    void testGetBaseLookupUrl_returnsNull() {
        assertNull(service.getBaseLookupUrl("org.kuali.kfs.SomeClass"));
    }

    @Test
    void testGetBaseHelpUrl_nullInput() {
        assertNull(service.getBaseHelpUrl(null));
    }

    @Test
    void testGetBaseInquiryUrl_nullInput() {
        assertNull(service.getBaseInquiryUrl(null));
    }

    @Test
    void testGetBaseLookupUrl_nullInput() {
        assertNull(service.getBaseLookupUrl(null));
    }
}
