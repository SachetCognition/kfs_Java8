package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.WebServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.external.kc.KcConstants;
import org.kuali.kfs.module.external.kc.businessobject.BillingFrequency;
import org.kuali.kfs.module.external.kc.businessobject.BillingFrequencyMapping;
import org.kuali.kfs.module.external.kc.dto.FrequencyDto;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kra.external.frequency.FrequencyWebService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
class BillingFrequencyServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private FrequencyWebService webService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private ConfigurationService configurationService;

    private BillingFrequencyServiceImpl service;

    @BeforeEach
    void setUp() {
        service = spy(new BillingFrequencyServiceImpl());
        service.businessObjectService = businessObjectService;
        service.configurationService = configurationService;
        doReturn(webService).when(service).getWebService();
    }

    @Test
    void testFindByPrimaryKey() {
        Map<String, Object> keys = new HashMap<>();
        keys.put(KcConstants.BillingFrequencyService.FREQUENCY, "MONTHLY");

        FrequencyDto dto = new FrequencyDto();
        dto.setFrequencyCode("MONTHLY");
        dto.setDescription("Monthly");
        when(webService.getFrequency("MONTHLY")).thenReturn(dto);

        BillingFrequencyMapping mapping = new BillingFrequencyMapping();
        mapping.setFrequency("MNTH");
        mapping.setGracePeriodDays(30);
        mapping.setActive(true);
        when(businessObjectService.findMatching(eq(BillingFrequencyMapping.class), any(Map.class)))
                .thenReturn(Arrays.asList(mapping));

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertNotNull(result);
        assertTrue(result instanceof BillingFrequency);
        BillingFrequency freq = (BillingFrequency) result;
        assertEquals("MONTHLY", freq.getKcFrequencyCode());
        assertEquals("MNTH", freq.getFrequency());
    }

    @Test
    void testFindByPrimaryKey_nullDTO() {
        Map<String, Object> keys = new HashMap<>();
        keys.put(KcConstants.BillingFrequencyService.FREQUENCY, "UNKNOWN");

        when(webService.getFrequency("UNKNOWN")).thenReturn(null);

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertNull(result);
    }

    @Test
    void testFindMatching_emptyFieldValues() {
        Map<String, Object> fieldValues = new HashMap<>();
        FrequencyDto dto = new FrequencyDto();
        dto.setFrequencyCode("ANNUAL");
        dto.setDescription("Annually");
        when(webService.findAll()).thenReturn(Arrays.asList(dto));

        BillingFrequencyMapping mapping = new BillingFrequencyMapping();
        mapping.setFrequency("ANNL");
        mapping.setActive(true);
        when(businessObjectService.findMatching(eq(BillingFrequencyMapping.class), any(Map.class)))
                .thenReturn(Arrays.asList(mapping));

        Collection result = service.findMatching(fieldValues);
        assertEquals(1, result.size());
    }

    @Test
    void testFindMatching_withFieldValues() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(KcConstants.BillingFrequencyService.FREQUENCY, "QUARTERLY");
        fieldValues.put(KcConstants.BillingFrequencyService.FREQUENCY_DESCRIPTION, "Quarterly");

        when(webService.findMatching("QUARTERLY", "Quarterly")).thenReturn(new ArrayList<FrequencyDto>());

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_webServiceException() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(webService.findAll()).thenThrow(new WebServiceException("down"));
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://localhost");

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBillingFrequency_noMapping() {
        FrequencyDto dto = new FrequencyDto();
        dto.setFrequencyCode("X");
        dto.setDescription("Unknown");

        BillingFrequency freq = service.createBillingFrequency(dto, null);
        assertEquals("X", freq.getKcFrequencyCode());
        assertFalse(freq.isActive());
    }

    @Test
    void testCreateBillingFrequency_withMapping() {
        FrequencyDto dto = new FrequencyDto();
        dto.setFrequencyCode("M");
        dto.setDescription("Monthly");

        BillingFrequencyMapping mapping = new BillingFrequencyMapping();
        mapping.setFrequency("MNTH");
        mapping.setGracePeriodDays(10);
        mapping.setActive(true);

        BillingFrequency freq = service.createBillingFrequency(dto, mapping);
        assertEquals("MNTH", freq.getFrequency());
        assertEquals(10, freq.getGracePeriodDays());
        assertTrue(freq.isActive());
    }

    @Test
    void testGetFrequencyMapping_found() {
        BillingFrequencyMapping mapping = new BillingFrequencyMapping();
        when(businessObjectService.findMatching(eq(BillingFrequencyMapping.class), any(Map.class)))
                .thenReturn(Arrays.asList(mapping));

        BillingFrequencyMapping result = service.getFrequencyMapping("CODE");
        assertSame(mapping, result);
    }

    @Test
    void testGetFrequencyMapping_notFound() {
        when(businessObjectService.findMatching(eq(BillingFrequencyMapping.class), any(Map.class)))
                .thenReturn(new ArrayList<BillingFrequencyMapping>());

        BillingFrequencyMapping result = service.getFrequencyMapping("NOPE");
        assertNull(result);
    }
}
