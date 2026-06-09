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
import org.kuali.kfs.module.external.kc.businessobject.LetterOfCreditFundGroup;
import org.kuali.kfs.module.external.kc.dto.AwardBasisOfPaymentDTO;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kra.external.awardpayment.AwardPaymentWebService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
class LetterOfCreditFundGroupServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private AwardPaymentWebService webService;
    @Mock private ConfigurationService configurationService;

    private LetterOfCreditFundGroupServiceImpl service;

    @BeforeEach
    void setUp() {
        service = spy(new LetterOfCreditFundGroupServiceImpl());
        service.configurationService = configurationService;
        doReturn(webService).when(service).getWebService();
    }

    @Test
    void testFindByPrimaryKey() {
        Map<String, Object> keys = new HashMap<>();
        keys.put("letterOfCreditFundGroupCode", "LOC1");

        AwardBasisOfPaymentDTO dto = new AwardBasisOfPaymentDTO();
        dto.setBasisOfPaymentCode("LOC1");
        dto.setDescription("Letter of Credit Group 1");
        when(webService.getBasisOfPayment("LOC1")).thenReturn(dto);

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertNotNull(result);
        assertTrue(result instanceof LetterOfCreditFundGroup);
        LetterOfCreditFundGroup fg = (LetterOfCreditFundGroup) result;
        assertEquals("LOC1", fg.getLetterOfCreditFundGroupCode());
        assertEquals("Letter of Credit Group 1", fg.getLetterOfCreditFundGroupDescription());
    }

    @Test
    void testFindMatching_withResults() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put("letterOfCreditFundGroupCode", "LOC1");

        AwardBasisOfPaymentDTO dto = new AwardBasisOfPaymentDTO();
        dto.setBasisOfPaymentCode("LOC1");
        dto.setDescription("Test");
        when(webService.getMatchingBasisOfPayments(any(AwardBasisOfPaymentDTO.class))).thenReturn(Arrays.asList(dto));

        Collection result = service.findMatching(fieldValues);
        assertEquals(1, result.size());
    }

    @Test
    void testFindMatching_webServiceException() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(webService.getMatchingBasisOfPayments(any(AwardBasisOfPaymentDTO.class))).thenThrow(new WebServiceException("down"));
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://localhost");

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_nullResult() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(webService.getMatchingBasisOfPayments(any(AwardBasisOfPaymentDTO.class))).thenReturn(null);

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFundGroupFromDTO() {
        AwardBasisOfPaymentDTO dto = new AwardBasisOfPaymentDTO();
        dto.setBasisOfPaymentCode("CODE");
        dto.setDescription("Desc");

        LetterOfCreditFundGroup fg = service.fundGroupFromDTO(dto);
        assertEquals("CODE", fg.getLetterOfCreditFundGroupCode());
        assertEquals("Desc", fg.getLetterOfCreditFundGroupDescription());
    }
}
