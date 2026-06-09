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
import org.kuali.kfs.module.external.kc.businessobject.LetterOfCreditFund;
import org.kuali.kfs.module.external.kc.dto.AwardMethodOfPaymentDTO;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kra.external.awardpayment.AwardPaymentWebService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
class LetterOfCreditFundServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private AwardPaymentWebService webService;
    @Mock private ConfigurationService configurationService;

    private LetterOfCreditFundServiceImpl service;

    @BeforeEach
    void setUp() {
        service = spy(new LetterOfCreditFundServiceImpl());
        service.configurationService = configurationService;
        doReturn(webService).when(service).getWebService();
    }

    @Test
    void testFindByPrimaryKey() {
        Map<String, Object> keys = new HashMap<>();
        keys.put("letterOfCreditFundCode", "FUND1");

        AwardMethodOfPaymentDTO dto = new AwardMethodOfPaymentDTO();
        dto.setMethodOfPaymentCode("FUND1");
        dto.setDescription("Fund 1");
        when(webService.getMethodOfPayment("FUND1")).thenReturn(dto);

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertNotNull(result);
        assertTrue(result instanceof LetterOfCreditFund);
        LetterOfCreditFund fund = (LetterOfCreditFund) result;
        assertEquals("FUND1", fund.getLetterOfCreditFundCode());
        assertEquals("Fund 1", fund.getLetterOfCreditFundDescription());
    }

    @Test
    void testFindMatching_byFundGroupCode() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put("letterOfCreditFundGroupCode", "GROUP1");

        AwardMethodOfPaymentDTO dto = new AwardMethodOfPaymentDTO();
        dto.setMethodOfPaymentCode("FUND1");
        dto.setDescription("Fund 1");
        when(webService.getMatchingMethodOfPaymentsForBasisOfPayment("GROUP1")).thenReturn(Arrays.asList(dto));

        Collection result = service.findMatching(fieldValues);
        assertEquals(1, result.size());
    }

    @Test
    void testFindMatching_byFundCodeAndDescription() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put("letterOfCreditFundCode", "FUND1");
        fieldValues.put("letterOfCreditFundDescription", "Fund 1");

        AwardMethodOfPaymentDTO dto = new AwardMethodOfPaymentDTO();
        dto.setMethodOfPaymentCode("FUND1");
        dto.setDescription("Fund 1");
        when(webService.getMatchingMethodOfPayments(any(org.kuali.kfs.module.external.kc.dto.AwardMethodOfPaymentDTO.class))).thenReturn(Arrays.asList(dto));

        Collection result = service.findMatching(fieldValues);
        assertEquals(1, result.size());
    }

    @Test
    void testFindMatching_webServiceException() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(webService.getMatchingMethodOfPayments(any(org.kuali.kfs.module.external.kc.dto.AwardMethodOfPaymentDTO.class))).thenThrow(new WebServiceException("down"));
        when(configurationService.getPropertyValueAsString(anyString())).thenReturn("http://localhost");

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_nullResult() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(webService.getMatchingMethodOfPayments(any(org.kuali.kfs.module.external.kc.dto.AwardMethodOfPaymentDTO.class))).thenReturn(null);

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFundFromDTO() {
        AwardMethodOfPaymentDTO dto = new AwardMethodOfPaymentDTO();
        dto.setMethodOfPaymentCode("PAY1");
        dto.setDescription("Payment 1");

        LetterOfCreditFund fund = service.fundFromDTO(dto);
        assertEquals("PAY1", fund.getLetterOfCreditFundCode());
        assertEquals("Payment 1", fund.getLetterOfCreditFundDescription());
    }
}
