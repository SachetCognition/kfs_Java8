package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
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
import org.kuali.kfs.module.external.kc.dto.AwardAccountDTO;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kra.external.award.AwardAccountService;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;
import org.mockito.Mock;

class AwardAccountServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private AwardAccountService webService;

    private AwardAccountServiceImpl service;

    @BeforeEach
    void setUp() {
        service = spy(new AwardAccountServiceImpl());
        doReturn(webService).when(service).getWebService();
    }

    @Test
    void testFindMatching_withValidAccountAndChart() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(KFSPropertyConstants.ACCOUNT_NUMBER, "1234567");
        fieldValues.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, "BL");

        AwardAccountDTO dto = new AwardAccountDTO();
        dto.setErrorMessage(null);
        when(webService.getAwardAccounts("1234567", "BL")).thenReturn(Arrays.asList(dto));

        Collection result = service.findMatching(fieldValues);
        assertEquals(1, result.size());
    }

    @Test
    void testFindMatching_blankAccountNumber() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(KFSPropertyConstants.ACCOUNT_NUMBER, "");
        fieldValues.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, "BL");

        when(webService.getAwardAccounts(null, "BL")).thenReturn(new ArrayList<AwardAccountDTO>());

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_blankChartCode() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(KFSPropertyConstants.ACCOUNT_NUMBER, "1234567");
        fieldValues.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, "");

        when(webService.getAwardAccounts("1234567", null)).thenReturn(new ArrayList<AwardAccountDTO>());

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_webServiceException() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(KFSPropertyConstants.ACCOUNT_NUMBER, "1234567");
        fieldValues.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, "BL");

        when(webService.getAwardAccounts("1234567", "BL")).thenThrow(new WebServiceException("down"));

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_skipsErrorDTOs() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(KFSPropertyConstants.ACCOUNT_NUMBER, "1234567");
        fieldValues.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, "BL");

        AwardAccountDTO goodDto = new AwardAccountDTO();
        goodDto.setErrorMessage(null);
        AwardAccountDTO badDto = new AwardAccountDTO();
        badDto.setErrorMessage("Error occurred");

        when(webService.getAwardAccounts("1234567", "BL")).thenReturn(Arrays.asList(goodDto, badDto));

        Collection result = service.findMatching(fieldValues);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByPrimaryKey_returnsFirst() {
        Map<String, Object> keys = new HashMap<>();
        keys.put(KFSPropertyConstants.ACCOUNT_NUMBER, "1234567");
        keys.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, "BL");

        AwardAccountDTO dto = new AwardAccountDTO();
        dto.setErrorMessage(null);
        when(webService.getAwardAccounts("1234567", "BL")).thenReturn(Arrays.asList(dto));

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertNotNull(result);
    }

    @Test
    void testFindByPrimaryKey_noResults() {
        Map<String, Object> keys = new HashMap<>();
        keys.put(KFSPropertyConstants.ACCOUNT_NUMBER, "1234567");
        keys.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, "BL");

        when(webService.getAwardAccounts("1234567", "BL")).thenReturn(new ArrayList<AwardAccountDTO>());

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertNull(result);
    }

    @Test
    void testFindMatching_nullResult() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(KFSPropertyConstants.ACCOUNT_NUMBER, "1234567");
        fieldValues.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, "BL");

        when(webService.getAwardAccounts("1234567", "BL")).thenReturn(null);

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }
}
