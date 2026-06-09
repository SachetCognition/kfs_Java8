package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.TemPropertyConstants;
import org.kuali.kfs.module.tem.businessobject.CreditCardAgency;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CreditCardAgencyServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private CreditCardAgencyServiceImpl creditCardAgencyService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Test
    void testGetCorpCreditCardAgencyList() {
        Map<String, String> key = new HashMap();
        key.put(TemPropertyConstants.TRAVEL_CARD_TYPE_CODE, TemConstants.TRAVEL_TYPE_CORP);

        List<CreditCardAgency> agencies = new ArrayList();
        CreditCardAgency agency = new CreditCardAgency();
        agency.setTravelCardTypeCode("CORP");
        agencies.add(agency);

        when(businessObjectService.findMatching(CreditCardAgency.class, key)).thenReturn(agencies);

        List<CreditCardAgency> result = creditCardAgencyService.getCorpCreditCardAgencyList();

        assertEquals(1, result.size());
        assertEquals("CORP", result.get(0).getTravelCardTypeCode());
    }

    @Test
    void testGetCorpCreditCardAgencyList_empty() {
        Map<String, String> key = new HashMap();
        key.put(TemPropertyConstants.TRAVEL_CARD_TYPE_CODE, TemConstants.TRAVEL_TYPE_CORP);

        when(businessObjectService.findMatching(CreditCardAgency.class, key)).thenReturn(new java.util.ArrayList());

        List<CreditCardAgency> result = creditCardAgencyService.getCorpCreditCardAgencyList();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCreditCardAgencyByCode_found() {
        Map<String, String> criteria = new HashMap();
        criteria.put(TemPropertyConstants.CREDIT_CARD_AGENCY_CODE, "VISA");

        CreditCardAgency agency = new CreditCardAgency();
        List<CreditCardAgency> list = new ArrayList();
        list.add(agency);

        when(businessObjectService.findMatching(CreditCardAgency.class, criteria)).thenReturn(list);

        CreditCardAgency result = creditCardAgencyService.getCreditCardAgencyByCode("VISA");

        assertSame(agency, result);
    }

    @Test
    void testGetCreditCardAgencyByCode_notFound() {
        Map<String, String> criteria = new HashMap();
        criteria.put(TemPropertyConstants.CREDIT_CARD_AGENCY_CODE, "VISA");

        when(businessObjectService.findMatching(CreditCardAgency.class, criteria)).thenReturn(new java.util.ArrayList());

        CreditCardAgency result = creditCardAgencyService.getCreditCardAgencyByCode("VISA");

        assertNull(result);
    }

    @Test
    void testGetCreditCardAgencyByCode_nullList() {
        Map<String, String> criteria = new HashMap();
        criteria.put(TemPropertyConstants.CREDIT_CARD_AGENCY_CODE, "VISA");

        when(businessObjectService.findMatching(CreditCardAgency.class, criteria)).thenReturn(null);

        CreditCardAgency result = creditCardAgencyService.getCreditCardAgencyByCode("VISA");

        assertNull(result);
    }
}
