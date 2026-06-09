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
import org.kuali.kfs.module.external.kc.businessobject.InstrumentType;
import org.kuali.kfs.module.external.kc.dto.AwardTypeDTO;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kra.external.awardtype.AwardTypeWebService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
class AwardTypeServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private AwardTypeWebService webService;
    @Mock private ConfigurationService configurationService;

    private AwardTypeServiceImpl service;

    @BeforeEach
    void setUp() {
        service = spy(new AwardTypeServiceImpl());
        service.configurationService = configurationService;
        doReturn(webService).when(service).getWebService();
    }

    @Test
    void testFindByPrimaryKey() {
        Map<String, Object> keys = new HashMap<String, Object>();
        keys.put("instrumentTypeCode", 5);

        AwardTypeDTO dto = new AwardTypeDTO();
        dto.setAwardTypeCode(5);
        dto.setDescription("Grant");
        when(webService.getAwardType(5)).thenReturn(dto);

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertNotNull(result);
        assertTrue(result instanceof InstrumentType);
        InstrumentType type = (InstrumentType) result;
        assertEquals("5", type.getInstrumentTypeCode());
        assertEquals("Grant", type.getInstrumentTypeDescription());
    }

    @Test
    void testFindMatching_withResults() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("instrumentTypeCode", "5");
        fieldValues.put("instrumentTypeDescription", "Grant");

        AwardTypeDTO dto = new AwardTypeDTO();
        dto.setAwardTypeCode(5);
        dto.setDescription("Grant");
        when(webService.findMatching(any(List.class))).thenReturn(Arrays.asList(dto));

        Collection result = service.findMatching(fieldValues);
        assertEquals(1, result.size());
    }

    @Test
    void testFindMatching_webServiceException() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        when(webService.findMatching(any(List.class))).thenThrow(new WebServiceException("down"));
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://localhost");

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_nullResult() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        when(webService.findMatching(any(List.class))).thenReturn(null);

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testTypeFromDTO() {
        AwardTypeDTO dto = new AwardTypeDTO();
        dto.setAwardTypeCode(10);
        dto.setDescription("Contract");

        InstrumentType type = service.typeFromDTO(dto);
        assertEquals("10", type.getInstrumentTypeCode());
        assertEquals("Contract", type.getInstrumentTypeDescription());
    }
}
